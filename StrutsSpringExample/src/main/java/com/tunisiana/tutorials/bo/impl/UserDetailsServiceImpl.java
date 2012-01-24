package com.tunisiana.tutorials.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tunisiana.tutorials.dao.UserDao;
import com.tunisiana.tutorials.model.User;
import com.tunisiana.tutorials.util.SpringSecurityUtil;

/**
 * Spring Security User Details
 * 
 * @author oussama.zoghlami.ext
 * 
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserDao userDao;

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException,
			DataAccessException {
		User user = userDao.loadByUsername(username);
		if (null == user) {
			throw new UsernameNotFoundException("user not found !!!");
		}

		return SpringSecurityUtil.getSpringSecurityUser(user);
	}

}
