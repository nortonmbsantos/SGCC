package br.edu.utfpr.sgcc.service;

import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.edu.utfpr.sgcc.config.DataBaseConfig;
import br.edu.utfpr.sgcc.daos.AdminDAOImpl;
import br.edu.utfpr.sgcc.models.Admin;

public class AdminService {
	AdminDAOImpl dao;
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataBaseConfig.class);
	
	public AdminService() {
//		dao = context.getBean("AdminDAOBean", AdminDAOImpl.class);
		dao = new AdminDAOImpl(context.getBean("DAOBean", DataSource.class));
		
	}
	
	public Admin returnById(int id) {
		return dao.returnById(id);
	}

	public Admin returnByEmail(String email) {
		return dao.returnByEmail(email);
	}

	public Admin login(Admin admin) {
		return dao.login(admin);
	}

	public boolean insert(Admin admin) {
		return dao.insert(admin);
	}
}
