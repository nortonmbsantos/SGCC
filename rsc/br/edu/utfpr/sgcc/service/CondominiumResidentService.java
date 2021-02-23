package br.edu.utfpr.sgcc.service;

import java.util.List;

import javax.persistence.Query;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.edu.utfpr.sgcc.config.DataBaseConfig;
import br.edu.utfpr.sgcc.daos.AdminDAOImpl;
import br.edu.utfpr.sgcc.daos.CondominiumDAOImpl;
import br.edu.utfpr.sgcc.daos.CondominiumEntryRequestDAOImpl;
import br.edu.utfpr.sgcc.daos.CondominiumResidentDAOImpl;
import br.edu.utfpr.sgcc.models.Condominium;
import br.edu.utfpr.sgcc.models.CondominiumEntryRequest;
import br.edu.utfpr.sgcc.models.CondominiumResident;

public class CondominiumResidentService {
	CondominiumResidentDAOImpl dao;
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataBaseConfig.class);

	public CondominiumResidentService() {
		dao = new CondominiumResidentDAOImpl(context.getBean("DAOBean", DataSource.class));
	}

	public List<Condominium> returnCondominiunsByResident(int id_resident) {
		return dao.returnCondominiunsByResident(id_resident);
	}
	
	
	public CondominiumResident returnByResidentAndCondominium(int id_resident, int id_condominium) {
		return dao.returnByResidentAndCondominium(id_resident, id_condominium);
	}
	

	public boolean save(CondominiumResident resident) {
		return dao.save(resident);
	}

}
