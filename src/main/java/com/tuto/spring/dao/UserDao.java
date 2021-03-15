package com.tuto.spring.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuto.spring.model.Base;
import com.tuto.spring.model.User;

public interface UserDao {
	
	public User getUser(String emmail, String password);
	
}
