package com.tunisiana.tutorials.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.tunisiana.tutorials.dao.UserDao;
import com.tunisiana.tutorials.model.User;

@Repository("userDao")
@SuppressWarnings("unchecked")
public class UserDaoImpl extends GenericDaoImpl<User, Integer> implements UserDao {

	@Autowired
	public UserDaoImpl(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
		this.setSessionFactory(sessionFactory);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tunisiana.tutorials.dao.UserDao#loadByUsername(java.lang.String)
	 */
	public User loadByUsername(String username) {
		DetachedCriteria userCriteria = DetachedCriteria.forClass(User.class);
		userCriteria.add(Restrictions.eq("username", username));
		List<User> users = (List<User>) getHibernateTemplate().findByCriteria(userCriteria);
		if (users.size() != 0) {
			return users.get(0);
		}
		return null;
	}
}
