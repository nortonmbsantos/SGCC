package br.edu.utfpr.sgcc.service;

import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.edu.utfpr.sgcc.config.DataBaseConfig;
import br.edu.utfpr.sgcc.daos.UserDAOImpl;
import br.edu.utfpr.sgcc.models.User;

public class UserService {
	UserDAOImpl dao;
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataBaseConfig.class);

	public UserService() {
		dao = new UserDAOImpl(context.getBean("DAOBean", DataSource.class));
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
		User userVal = this.returnById(user.getId());
		userVal.setFirstName(user.getFirstName());
		return dao.update(userVal);
	}

}
