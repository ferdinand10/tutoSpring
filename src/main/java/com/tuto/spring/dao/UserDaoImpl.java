package com.tuto.spring.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tuto.spring.model.Base;
import com.tuto.spring.model.User;
@Repository
public class UserDaoImpl implements UserDao{
	
	@Autowired
	private EntityManager entityManager;
	
	public User getUser(String email, String password){
		User user = null;
		if(email == null)
			email ="";
		if(password == null)
			password = "";
		List list = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email"
				+ " AND u.password = :password").setParameter("email", email)
				.setParameter("password", password).getResultList();
		if(list.size()>0)
			user = (User)list.get(0);
		else{
			
		}
		return user;
	}


}
