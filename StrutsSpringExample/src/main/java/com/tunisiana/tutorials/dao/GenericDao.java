package com.tunisiana.tutorials.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Interface containing all the reusable Dao methods
 * 
 * @author oussama.zoghlami.ext
 * 
 * @param <T>
 * @param <Id>
 */
public interface GenericDao<T, Id extends Serializable> {

	/**
	 * Get an entity by its id
	 * 
	 * @param id
	 * @return
	 */
	public T getById(Id id);

	/**
	 * get all a class entities
	 * 
	 * @return
	 */
	public List<T> getAll();

	/**
	 * save an entity
	 * 
	 * @param entity
	 */
	public void save(T entity);

	/**
	 * update an entity
	 * 
	 * @param entity
	 */
	public void update(T entity);

	/**
	 * save/update an entity
	 * 
	 * @param entity
	 * @return
	 */
	public T saveOrUpdate(T entity);

	/**
	 * delete an entity
	 * 
	 * @param entity
	 */
	public void delete(T entity);

	/**
	 * delete an entity by its id
	 * 
	 * @param id
	 */
	public void deleteById(Id id);

	/**
	 * load a class entities located between a given start and end rows
	 * 
	 * @param startRow
	 * @param maxRows
	 * @return
	 */
	public List<T> getByRows(int startRow, int maxRows);

	/**
	 * get the total number of a class entities
	 * 
	 * @return
	 */
	public int getTotalRows();

}