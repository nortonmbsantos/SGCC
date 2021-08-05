package br.edu.utfpr.sgcc.service;

import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.edu.utfpr.sgcc.daos.AdminDAOImpl;
import br.edu.utfpr.sgcc.models.Admin;

public class AdminService {
	AdminDAOImpl daos;
	
	public AdminService() {
//		daos = context.getBean("AdminDAOBean", AdminDAOImpl.class);
		daos = new AdminDAOImpl();
		
	}
	
	public Admin returnById(int id) {
		return daos.returnById(id);
	}

	public Admin returnByEmail(String email) {
		return daos.returnByEmail(email);
	}

	public Admin login(Admin admin) {
		return daos.login(admin);
	}

	public boolean insert(Admin admin) {
		return daos.insert(admin);
	}
}
