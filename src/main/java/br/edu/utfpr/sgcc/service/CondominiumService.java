package br.edu.utfpr.sgcc.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import br.edu.utfpr.sgcc.daos.AdminDAOImpl;
import br.edu.utfpr.sgcc.daos.CondominiumDAOImpl;
import br.edu.utfpr.sgcc.models.Condominium;

public class CondominiumService {
	CondominiumDAOImpl daos;
	
	
	public CondominiumService() {
//		daos = context.getBean("CondominiumDAOBean", CondominiumDAOImpl.class);
		daos = new CondominiumDAOImpl();
	}
	
	public Condominium returnById(int id) {
		return daos.returnById(id);
	}
	
	public Condominium returnByCode(String code) {
		return daos.returnByCode(code);
	}
	
	public List<Condominium> list(int idAdmin) {
		return daos.list(idAdmin);
	}
	
	public List<Condominium> listForResidents(int idResident) {
		return daos.listForResidents(idResident);
	}
	
	
	public List<Condominium> listSidebar(int idAdmin) {
		return daos.listSidebar(idAdmin);
	}
	
	public Condominium insert(Condominium condominium) {
		condominium = daos.insert(condominium);
		if(condominium != null) {
			updateCode(condominium);
		}
		return condominium; 
	}

	public void updateCode(Condominium condominium) {
		daos.updateCode(condominium);
	}
	
	public Serializable insertSerializable(Condominium condominium) {
		return daos.insertSerializable(condominium);
	}
	
	public boolean update(Condominium condominium) {
		return daos.update(condominium);
	}
	
	public static void main(String[] args) {
		List<Condominium> cons = new CondominiumService().listForResidents(7);
		for(Condominium c : cons) {
			System.out.println(c.getName());
		}
	}
}
