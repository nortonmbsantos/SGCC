package br.edu.utfpr.sgcc.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.edu.utfpr.sgcc.config.DataBaseConfig;
import br.edu.utfpr.sgcc.daos.AdminDAOImpl;
import br.edu.utfpr.sgcc.daos.ResidentDAOImpl;
import br.edu.utfpr.sgcc.daos.WarningDAOImpl;
import br.edu.utfpr.sgcc.models.Admin;
import br.edu.utfpr.sgcc.models.Resident;
import br.edu.utfpr.sgcc.models.Warning;

public class WarningService {
	WarningDAOImpl dao;
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataBaseConfig.class);
	
	public WarningService() {
//		dao = context.getBean("WarningDAOBean", WarningDAOImpl.class);
		dao = new WarningDAOImpl();
	}
	
	public Warning returnById(int id) {
		return dao.returnById(id);
	}


	public List<Warning> returnByResident(int id_resident) {
		return dao.returnByResident(id_resident);
	}

	public List<Warning> returnByCondominiumFee(int id_resident, int id_condominium_fee) {
		return dao.returnByCondominiumFee(id_resident, id_condominium_fee);
	}


	public boolean insert(Warning warning) {
		return dao.insert(warning);
	}
}
