package com.tuto.spring.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import com.tuto.spring.model.Base;

public interface BaseDao<E, K> {
	
	public E persist(E entity);
	public E merge(E entity);
	public void delete(E entity);
	public void delete(Class cl, Long id);
	public E find(Class cl, Long id);
	public List<E> getAll(Class cl);
	public List<Base> findByParendId(Class cl, String col, Long id);
	public Session getConnection();
}
