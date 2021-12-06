package br.edu.utfpr.sgcc.service;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


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
		boolean create = condominium.getId() > 0 ? false : true;
		boolean valid = daos.update(condominium);
		
		if(valid) {
			if(create) {
				this.updateCode(condominium);
			}
		} 
		
		return valid;
	}

	public Condominium saveOrUpdate(Condominium condominium) {
		boolean create = condominium.getId() > 0 ? false : true;
		boolean valid = daos.update(condominium);
		
		if(valid) {
			if(create) {
				this.updateCode(condominium);
			}
			return condominium;
		} else {
			return null;
		}
	}
	
	public static void main(String[] args) {
		Condominium condominium = new CondominiumService().returnById(1);
		condominium.setId(0);
		condominium.setName("Novo condominio teste");
		String descrition = "Criado " + new SimpleDateFormat("dd-MM-yyyy-HH-mm-ss").format(new Date());
		condominium.setDescription(descrition);
		new CondominiumService().update(condominium);
	}
}
