package com.tuto.spring.service;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.tuto.spring.model.Base;
import com.tuto.spring.model.Report;
import com.tuto.spring.model.StringResult;
import com.tuto.spring.util.Constant;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

import java.io.*;
import java.sql.*;
import java.util.*;

@Service(value="reportService")
public class ReportServiceImpl implements ReportService{
	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	DataSource datasource;
	public StringResult createReport(Report report) throws SQLException{
		try{
			Resource resource = resourceLoader.getResource("classpath:/reports/" + report.getName() + ".jasper");
			System.out.println(resource);
			InputStream reportStream = resource.getInputStream();
			Map parameters = new HashMap();
			StringResult reportName = new StringResult();
			parameters.put("REPORT_FOLDER", resourceLoader.getResource("classpath:/reports").getFile().getAbsolutePath()
					+ java.io.File.separator);
			parameters.put("REPORT_LOCALE", Constant.LOCALE);
			
			OutputStream ouputStream = new ByteArrayOutputStream();
			Connection conn = this.datasource.getConnection();
			byte[] reportBytes = JasperRunManager.runReportToPdf(reportStream, parameters, conn); 
			reportName.setName(report.getName() + "_" + System.currentTimeMillis() + ".pdf");
			FileOutputStream fileOuputStream = new FileOutputStream(Constant.REPORT_RESULT_FOLDER + reportName.getName());
			fileOuputStream.write(reportBytes);
			fileOuputStream.close();
			return reportName;
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
