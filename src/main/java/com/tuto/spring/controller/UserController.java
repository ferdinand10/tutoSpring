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
import com.tuto.spring.model.User;
import com.tuto.spring.service.BaseService;
import com.tuto.spring.service.UserService;

@RestController
@RequestMapping("/service/user")
@CrossOrigin
public class UserController {
	
	@Autowired
	@Qualifier("userService")
	UserService userService;
	
	@Autowired
	@Qualifier("baseService")
	BaseService baseService;
	
	
	//@Transactional
	@RequestMapping(value="/login", method = RequestMethod.POST, headers = "Accept=application/json")
	public Base login(@RequestBody User user){
	return this.userService.getUser(user.getEmail(), user.getPassword());
	}
	
	@RequestMapping(value="/register", method = RequestMethod.POST, headers = "Accept=application/json")
	public Base regiser(@RequestBody User user){
		Base savedUser = null;
		try{
			savedUser =	this.userService.save(user);
				
			
		}catch(Exception e){
			if(savedUser==null)
				savedUser = new User();
			savedUser.setError(e.getMessage());
		}
		return savedUser;
		
	}

//	@RequestMapping(value="/getAllProducts", method = RequestMethod.GET)
//	public List<Category> getAllProducts(){
//		return (List)baseService.getAll(Product.class);
//	}
	

}
