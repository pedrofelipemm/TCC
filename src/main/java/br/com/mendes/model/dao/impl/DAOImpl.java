package br.com.mendes.model.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.mendes.model.dao.DAO;

public abstract class DAOImpl<T, K extends Serializable> implements DAO<T, K>{

	private static final long serialVersionUID = -7793329376577351690L;

	protected Class<T> entityClass;

	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	public DAOImpl() {
		
		ParameterizedType genericSuperclass = (ParameterizedType) getClass()
				.getGenericSuperclass();
		this.entityClass = (Class<T>) genericSuperclass
				.getActualTypeArguments()[0];
	}

	
	public void add(T entity) {
		getSession().save(entity);		
	}
	
	public void remove(T entity) {
		getSession().delete(entity);
		getSession().flush();
	}
		
	public void removeByCod(K... cod) {		
		String hql  = "DELETE FROM "+ entityClass.getCanonicalName() + " WHERE id in (:cods)" ;
		
		Query query = getSession().createQuery(hql);
		query.setParameterList("cods", cod);		
		query.executeUpdate();
		getSession().flush();
	}
	
	public void update(T entity) {
		getSession().update(entity);
	}
	
	@SuppressWarnings("unchecked")
	public T saveUpdateGetEntity(T entity) {
		return (T) getSession().merge(entity);
	}
	
	@SuppressWarnings("unchecked")
	public T getByCod(K cod) {
		return (T) getSession().get(entityClass, cod);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		
		return getSession().createCriteria(this.entityClass).list();
	}

	public void flush() {
		this.getSession().flush();
	}
		
	public Criteria createCriteria() {
		return getSession().createCriteria(entityClass);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getListByFilter(T entity) {
		Criteria criteria = createCriteria();
		criteria.add(Example.create(entity));
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public T getByFilter(T entity) {
		Criteria criteria = createCriteria();
		criteria.add(Example.create(entity));
		return (T) criteria.uniqueResult();
	}
}