package com.tunisiana.tutorials.util;

import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tunisiana.tutorials.dao.UserDao;
import com.tunisiana.tutorials.model.UserAuthority;

public class App {

	public static void main(String[] args) {
		// test the spring context
		ApplicationContext appContext = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		UserDao userDao = (UserDao) appContext.getBean("userDao");
		Set<UserAuthority> authorities = userDao.loadByUsername("oussama").getUserAuthorithies();
		for (UserAuthority authority :  authorities) {
			System.out.println(authority.getAuthority().getRole());
		}
	}
}
