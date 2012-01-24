package com.tunisiana.tutorials.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tunisiana.tutorials.dao.GenericDao;

/**
 * Generic Dao implementation
 * 
 * @author oussama.zoghlami.ext
 * 
 * @param <T>
 * @param <Id>
 */
@SuppressWarnings("unchecked")
public abstract class GenericDaoImpl<T, Id extends Serializable> extends HibernateDaoSupport
		implements GenericDao<T, Id> {

	private Class<T> persistentClass;

	public GenericDaoImpl() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tunisiana.tutorials.dao.GenericDao#getById(java.io.Serializable)
	 */
	public T getById(Id id) {
		return (T) getHibernateTemplate().get(getPersistentClass(), id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tunisiana.tutorials.dao.GenericDao#save(java.lang.Object)
	 */
	public void save(T entity) {
		getHibernateTemplate().save(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tunisiana.tutorials.dao.GenericDao#update(java.lang.Object)
	 */
	public void update(T entity) {
		getHibernateTemplate().update(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tunisiana.tutorials.dao.GenericDao#saveOrUpdate(java.lang.Object)
	 */
	public T saveOrUpdate(T entity) {
		getHibernateTemplate().merge(entity);
		return entity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tunisiana.tutorials.dao.GenericDao#delete(java.lang.Object)
	 */
	public void delete(T entity) {
		getHibernateTemplate().delete(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tunisiana.tutorials.dao.GenericDao#deleteById(java.io.Serializable)
	 */
	public void deleteById(Id id) {
		getHibernateTemplate().delete(getById(id));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tunisiana.tutorials.dao.GenericDao#getAll()
	 */
	public List<T> getAll() {
		return getHibernateTemplate().loadAll(getPersistentClass());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tunisiana.tutorials.dao.GenericDao#getByRows(int, int)
	 */
	public List<T> getByRows(int startRow, int maxRows) {
		DetachedCriteria c = DetachedCriteria.forClass(getPersistentClass());
		return getHibernateTemplate().findByCriteria(c, startRow, maxRows);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tunisiana.tutorials.dao.GenericDao#getTotalRows()
	 */
	public int getTotalRows() {
		Criteria criteria = this.getSession().createCriteria(getPersistentClass());
		criteria.setProjection(Projections.rowCount());
		return (Integer) criteria.uniqueResult();
	}
}
