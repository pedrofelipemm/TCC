package br.com.mendes.model.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

public interface DAO<T, K extends Serializable> extends Serializable {

	public abstract Session getSession();

	public abstract Long countBy();

	public abstract void add(T entity);

	public abstract void remove(T entity);

	@SuppressWarnings("unchecked")
	public abstract void removeByCod(K... cod);

	public abstract void update(T entity);

	public abstract void saveUpdateGetEntity(T entity);

	public abstract T getByCod(K cod);

	public abstract List<T> getAll();

	public abstract void flush();

	public abstract Criteria createCriteria();

	public abstract List<T> getListByFilter(T entity);

	public abstract T getByFilter(T entity);
}