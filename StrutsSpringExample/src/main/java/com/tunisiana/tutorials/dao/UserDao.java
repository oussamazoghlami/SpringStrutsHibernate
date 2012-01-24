package com.tunisiana.tutorials.dao;

import com.tunisiana.tutorials.model.User;

/**
 * User Dao interface
 * 
 * @author oussama.zoghlami.ext
 * 
 */
public interface UserDao extends GenericDao<User, Integer> {

	/**
	 * Method allowing to load the user associated to a given username
	 * 
	 * @param username
	 * @return
	 */
	public User loadByUsername(String username);
}
