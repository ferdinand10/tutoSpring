package com.tuto.spring.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuto.spring.dao.BaseDao;
import com.tuto.spring.dao.UserDao;
import com.tuto.spring.model.Base;
import com.tuto.spring.model.User;

@Service(value="userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	BaseService baseService;
	
	@Transactional
	public Base save(User entity){
		User user = entity;
		String error = "Error";
		try{
			user = (User) baseService.save(entity);
			if(user == null){
				error = "L'utilistteur n'a pas été enrégistré";
			}
			error = "Success";
		}catch(Exception e){
			error = e.getMessage();
		}
		user.setError(error);
		return baseService.save(entity);
	}
	@Transactional
	public User getUser(String email, String password){
		User user = null;
		String error = "Success";
		try{
			user = userDao.getUser(email, password);
			if(user == null){
				error = "L'utilisateur ou mot de passe invalide";
			}
		}catch(Exception e){
			error = e.getMessage();
		}if(user == null)
			user = new User();
		user.setError(error);
		return user;
	}
}
