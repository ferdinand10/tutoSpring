package com.tuto.spring.service;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import com.tuto.spring.model.Base;
import com.tuto.spring.model.User;

@Service(value="userService")
public interface UserService {
	public Base save(User entity);
	public User getUser(String email, String password);
}
