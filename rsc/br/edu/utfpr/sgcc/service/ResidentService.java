package br.edu.utfpr.sgcc.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.edu.utfpr.sgcc.config.DataBaseConfig;
import br.edu.utfpr.sgcc.daos.AdminDAOImpl;
import br.edu.utfpr.sgcc.daos.ResidentDAOImpl;
import br.edu.utfpr.sgcc.models.Admin;
import br.edu.utfpr.sgcc.models.Resident;

public class ResidentService {
	ResidentDAOImpl dao;
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataBaseConfig.class);
	
	public ResidentService() {
//		dao = context.getBean("ResidentDAOBean", ResidentDAOImpl.class);
		dao = new ResidentDAOImpl(context.getBean("DAOBean", DataSource.class));
	}
	
	public Resident returnById(int id) {
		return dao.returnById(id);
	}

	public Resident returnByLogin(String login) {
		return dao.returnByLogin(login);
	}
	
	public List<Resident> returnByCondominium(int idCondominium) {
		return dao.returnByCondominium(idCondominium);
	}

	public Resident login(Resident resident) {
		return dao.login(resident);
	}

	public boolean insert(Resident resident) {
		return dao.insert(resident);
	}
}
