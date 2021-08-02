package br.edu.utfpr.sgcc.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.edu.utfpr.sgcc.config.DataBaseConfig;
import br.edu.utfpr.sgcc.daos.AdminDAOImpl;
import br.edu.utfpr.sgcc.daos.CondominiumDAOImpl;
import br.edu.utfpr.sgcc.models.Condominium;

public class CondominiumService {
	CondominiumDAOImpl dao;
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataBaseConfig.class);
	
	public CondominiumService() {
//		dao = context.getBean("CondominiumDAOBean", CondominiumDAOImpl.class);
		dao = new CondominiumDAOImpl();
	}
	
	public Condominium returnById(int id) {
		return dao.returnById(id);
	}
	
	public Condominium returnByCode(String code) {
		return dao.returnByCode(code);
	}
	
	public List<Condominium> list(int idAdmin) {
		return dao.list(idAdmin);
	}
	
	
	public List<Condominium> listSidebar(int idAdmin) {
		return dao.listSidebar(idAdmin);
	}
	
	public Condominium insert(Condominium condominium) {
		condominium = dao.insert(condominium);
		if(condominium != null) {
			updateCode(condominium);
		}
		return condominium; 
	}

	public void updateCode(Condominium condominium) {
		dao.updateCode(condominium);
	}
	
	public Serializable insertSerializable(Condominium condominium) {
		return dao.insertSerializable(condominium);
	}
	
	public boolean update(Condominium condominium) {
		return dao.update(condominium);
	}
	
}
