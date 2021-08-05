package br.edu.utfpr.sgcc.service;

import java.util.List;

import javax.persistence.Query;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.edu.utfpr.sgcc.daos.AdminDAOImpl;
import br.edu.utfpr.sgcc.daos.CommomAreaDAOImpl;
import br.edu.utfpr.sgcc.daos.CondominiumDAOImpl;
import br.edu.utfpr.sgcc.models.CommomArea;
import br.edu.utfpr.sgcc.models.Condominium;

public class CommomAreaService {
	CommomAreaDAOImpl daos;
	
	public CommomAreaService() {
//		daos = context.getBean("CommomAreaDAOBean", CommomAreaDAOImpl.class);
		daos = new CommomAreaDAOImpl();
	}
	
	public CommomArea returnById(int id) {
		return daos.returnById(id);
	}
	
	public List<CommomArea> list(int idCondominium) {
		return daos.list(idCondominium);
	}
	
	public boolean insert(CommomArea area) {
		return daos.insert(area);
	}

	public boolean update(CommomArea area) {
		return daos.update(area);
	}
	
}
