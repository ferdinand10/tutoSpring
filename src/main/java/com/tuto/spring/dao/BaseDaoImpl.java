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
@Repository
public class BaseDaoImpl<E, K> implements BaseDao<E, K> {

	@Autowired
	private EntityManager entityManager;

	@Override
	public E persist(E entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public E merge(E entity) {
		entityManager.merge(entity);
		return entity;
	}

	@Transactional
	public void delete(E entity) {
		if(entity != null)
			entityManager.remove(entity);
		
	}

	@Transactional
	public void delete(Class cl, Long id) {
		this.delete(this.find(cl, id));
		
	}

	@Override
	public E find(Class cl, Long id) {
		return (E)entityManager.find(cl, id);
	}

	
	@Override
	public List<E> getAll(Class cl) {
		CriteriaQuery<E> criteria = entityManager.getCriteriaBuilder().createQuery(cl);
		criteria.select(criteria.from(cl));
		List<E> resultList = entityManager.createQuery(criteria).getResultList();
		return resultList;
	}

	
	@Override
	public List<Base> findByParendId(Class cl, String col, Long id) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Object> cq = cb.createQuery(cl);
		Root<Object> from = cq.from(cl);
		cq.select(from).where(cb.equal(from.get(col).get("id"), id));
		Query query = entityManager.createQuery(cq);
		List<Base> list = (List<Base>)query.getResultList();
		return list;
	}

	public Session getConnection() {
		return entityManager.unwrap(Session.class);
	}
	
	
	

}
