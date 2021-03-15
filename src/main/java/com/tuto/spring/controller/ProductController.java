package com.tuto.spring.controller;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tuto.spring.model.Base;
import com.tuto.spring.model.Category;
import com.tuto.spring.model.Product;
import com.tuto.spring.model.Report;
import com.tuto.spring.model.StringResult;
import com.tuto.spring.service.BaseService;
import com.tuto.spring.service.ReportService;

@RestController
@RequestMapping("/service/product")
@CrossOrigin
public class ProductController {
	
	@Autowired
	@Qualifier("baseService")
	BaseService baseService;
	
	@Autowired
	@Qualifier("reportService")
	ReportService reportService;
	
	@Transactional
	@RequestMapping(value="/saveProduct", method = RequestMethod.POST, headers = "Accept=application/json")
	public Base saveProduct(@RequestBody Product prod){
		Base savedProduct = null;
		try{
			System.out.println(prod.getId()+" "+prod.getName());
			savedProduct = this.baseService.save(prod);
		} catch(Exception e){
			if(savedProduct == null)
				savedProduct = new Category();
			savedProduct.setError(e.getMessage());
		}
		return savedProduct;
	}
	
	@RequestMapping(value="deleteProd/{id}", method = RequestMethod.GET)
	public Boolean deleteProduct(@PathVariable Long id){
		Boolean resp = false;
		try{
				this.baseService.delete(Product.class, id);
				resp = true;
			
		}catch(Exception e){
			e.printStackTrace();
			resp = false;
		}finally{
			return resp;
		}
		
	}
	
	@RequestMapping(value="/getAllProducts", method = RequestMethod.GET)
	public List<Category> getAllProducts(){
		return (List)baseService.getAll(Product.class);
	}
	
	
	@RequestMapping(value="/printProduct", method = RequestMethod.POST)
	public StringResult getAllProducts(@RequestBody Report  report) throws SQLException {
		StringResult reportName = null;
		
			reportName = this.reportService.createReport(report);
		
		return reportName;
	}

}
