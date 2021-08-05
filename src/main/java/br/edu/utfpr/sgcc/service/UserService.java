package br.edu.utfpr.sgcc.service;

import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import br.edu.utfpr.sgcc.daos.UserDAOImpl;
import br.edu.utfpr.sgcc.models.User;

public class UserService {
	UserDAOImpl daos;
	

	public UserService() {
		daos = new UserDAOImpl();
	}

	public User returnById(int id) {
		return daos.returnById(id);
	}

	public User returnByUserName(String userName) {
		return daos.returnByUserName(userName);
	}

	public boolean insert(User user) {
		return daos.insert(user);
	}

	public boolean update(User user) {
		return daos.update(user);
	}
	
	public boolean updatePassword(User user) {
		return daos.update(user);
	}

	public User returnByEmail(String email) {
		return daos.returnByEmail(email);
	}
}
