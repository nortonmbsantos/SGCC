package br.edu.utfpr.sgcc.service;

import java.util.List;

import javax.persistence.Query;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import br.edu.utfpr.sgcc.daos.FeeTypeDAOImpl;
import br.edu.utfpr.sgcc.models.FeeType;

public class FeeTypeService {
	FeeTypeDAOImpl daos;
	

	public FeeTypeService() {
//		daos = context.getBean("FeeTypeDAOBean", FeeTypeDAOImpl.class);
		daos = new FeeTypeDAOImpl();
	}

	public FeeType returnById(int id) {
		return daos.returnById(id);
	}

	public List<FeeType> list() {
		return daos.list();
	}

}
