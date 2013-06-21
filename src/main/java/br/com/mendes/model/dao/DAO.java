package br.com.mendes.model.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

public interface DAO<T, K extends Serializable>  extends Serializable  {
	
	Session getSession();
	
	void add(T entity);
	
	void remove(T entity);
		
	void removeByCod(K... cod);
	
	void update(T entity);
	
	T saveUpdateGetEntity(T entity);
	
	T getByCod(K cod);
	
	List<T> getAll();

	void flush();
		
	Criteria createCriteria();
	
	List<T> getListByFilter(T entity);
	
	T getByFilter(T entity);
}