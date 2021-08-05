package br.edu.utfpr.sgcc.service;

import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import br.edu.utfpr.sgcc.daos.UserDAOImpl;
import br.edu.utfpr.sgcc.models.User;

public class UserService {
	UserDAOImpl dao;
	

	public UserService() {
		dao = new UserDAOImpl();
	}

	public User returnById(int id) {
		return dao.returnById(id);
	}

	public User returnByUserName(String userName) {
		return dao.returnByUserName(userName);
	}

	public boolean insert(User user) {
		return dao.insert(user);
	}

	public boolean update(User user) {
		return dao.update(user);
	}
	
	public boolean updatePassword(User user) {
		return dao.update(user);
	}

	public User returnByEmail(String email) {
		return dao.returnByEmail(email);
	}
}
