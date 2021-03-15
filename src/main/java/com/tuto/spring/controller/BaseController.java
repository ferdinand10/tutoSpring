package com.tuto.spring.controller;

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
import com.tuto.spring.service.BaseService;

@RestController
@RequestMapping("/service/base")
@CrossOrigin
public class BaseController {
	
	@Autowired
	@Qualifier("baseService")
	BaseService baseService;
	
	@Transactional
	@RequestMapping(value="/saveCategory", method = RequestMethod.POST, headers = "Accept=application/json")
	public Base saveCategory(@RequestBody Category cat){
		Base savedCategory = null;
		try{
			System.out.println(cat.getId());
			savedCategory = this.baseService.save(cat);
		} catch(Exception e){
			if(savedCategory == null)
				savedCategory = new Category();
			savedCategory.setError(e.getMessage());
		}
		return savedCategory;
	}
	
	@RequestMapping(value="deleteCate/{id}", method = RequestMethod.GET)
	public Boolean deleteCategory(@PathVariable Long id){
		Boolean resp = false;
		try{
			List<Product> prods = (List)baseService.findByParendId(Product.class, "category", id);
			if(prods.size() > 0){
				System.out.println("impossible");
				resp = false;
			}
			else
			{
				this.baseService.delete(Category.class, id);
				resp = true;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			resp = false;
		}finally{
			return resp;
		}
		
	}
	@RequestMapping(value="/getAllCategories", method = RequestMethod.GET)
	public List<Category> getAllCategories(){
		return (List)baseService.getAll(Category.class);
	}
	

}
