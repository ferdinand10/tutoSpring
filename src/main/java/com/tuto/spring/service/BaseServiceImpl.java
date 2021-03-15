package com.tuto.spring.service;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuto.spring.dao.BaseDao;
import com.tuto.spring.model.Base;

@Service(value="baseService")
public class BaseServiceImpl implements BaseService {
	
	@Autowired
	BaseDao<Base, String> baseDao;

	@Override
	public Base save(Base entity) {
		Base saveBase = entity;
		try{
			entity.setCreatedDate(new Date());
			if(entity.getId() == null){
				saveBase = this.baseDao.persist(entity);
			}else{
				saveBase = this.baseDao.merge(entity);
			}
			saveBase.setError("Success");
		}catch(Exception e){
			e.printStackTrace();
			saveBase.setError(e.getMessage());
		}
		return saveBase;
	}

	@Override
	public void delete(Class cl, Long id) {
		this.baseDao.delete(cl, id);
		
	}

	@Override
	public void delete(Base entity) {
		this.baseDao.delete(entity);
		
	}

	@Override
	public Base find(Class cl, Long id) {
		return (Base)this.baseDao.find(cl, id);
	}
	
	

	@Override
	public List<Base> getAll(Class cl) {
		
		return this.baseDao.getAll(cl);
	}
	public List<Base> findByParendId(Class cl, String col, Long id){
		return this.baseDao.findByParendId(cl, col, id);
	}

	@Override
	public Session getConnection() {
		return this.baseDao.getConnection();
	}

}
