package com.tuto.spring.service;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import com.tuto.spring.model.Base;
import com.tuto.spring.model.Report;
import com.tuto.spring.model.StringResult;

@Service(value="reportService")
public interface ReportService {
	
	public StringResult createReport(Report report) throws SQLException;

}
