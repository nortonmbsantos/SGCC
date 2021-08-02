package br.edu.utfpr.sgcc.service;

import java.util.List;

import javax.persistence.Query;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.edu.utfpr.sgcc.config.DataBaseConfig;
import br.edu.utfpr.sgcc.daos.AdminDAOImpl;
import br.edu.utfpr.sgcc.daos.CommomAreaDAOImpl;
import br.edu.utfpr.sgcc.daos.CondominiumDAOImpl;
import br.edu.utfpr.sgcc.models.CommomArea;
import br.edu.utfpr.sgcc.models.Condominium;

public class CommomAreaService {
	CommomAreaDAOImpl dao;
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataBaseConfig.class);
	
	public CommomAreaService() {
//		dao = context.getBean("CommomAreaDAOBean", CommomAreaDAOImpl.class);
		dao = new CommomAreaDAOImpl();
	}
	
	public CommomArea returnById(int id) {
		return dao.returnById(id);
	}
	
	public List<CommomArea> list(int idCondominium) {
		return dao.list(idCondominium);
	}
	
	public boolean insert(CommomArea area) {
		return dao.insert(area);
	}

	public boolean update(CommomArea area) {
		return dao.update(area);
	}
	
}
