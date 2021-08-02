package br.edu.utfpr.sgcc.service;

import java.util.List;

import javax.persistence.Query;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.edu.utfpr.sgcc.config.DataBaseConfig;
import br.edu.utfpr.sgcc.daos.FeeTypeDAOImpl;
import br.edu.utfpr.sgcc.models.FeeType;

public class FeeTypeService {
	FeeTypeDAOImpl dao;
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataBaseConfig.class);

	public FeeTypeService() {
//		dao = context.getBean("FeeTypeDAOBean", FeeTypeDAOImpl.class);
		dao = new FeeTypeDAOImpl();
	}

	public FeeType returnById(int id) {
		return dao.returnById(id);
	}

	public List<FeeType> list() {
		return dao.list();
	}

}
