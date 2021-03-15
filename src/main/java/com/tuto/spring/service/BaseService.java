package com.tuto.spring.service;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Service;

import com.tuto.spring.model.Base;

@Service(value="baseService")
public interface BaseService {
	public Base save(Base entity);
	public void delete(Class cl, Long id);
	public void delete(Base entity);
	public Base find(Class cl, Long id);
	public List<Base> getAll(Class cl);
	public List<Base> findByParendId(Class cl, String col, Long id);
	public Session getConnection();

}
