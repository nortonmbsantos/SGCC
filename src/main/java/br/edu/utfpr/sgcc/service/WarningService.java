package br.edu.utfpr.sgcc.service;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import br.edu.utfpr.sgcc.daos.AdminDAOImpl;
import br.edu.utfpr.sgcc.daos.ResidentDAOImpl;
import br.edu.utfpr.sgcc.daos.WarningDAOImpl;
import br.edu.utfpr.sgcc.models.Admin;
import br.edu.utfpr.sgcc.models.Resident;
import br.edu.utfpr.sgcc.models.Warning;

public class WarningService {
	WarningDAOImpl daos;
	
	
	public WarningService() {
//		daos = context.getBean("WarningDAOBean", WarningDAOImpl.class);
		daos = new WarningDAOImpl();
	}
	
	public Warning returnById(int id) {
		return daos.returnById(id);
	}


	public List<Warning> returnByResident(int id_resident) {
		return daos.returnByResident(id_resident);
	}

	public List<Warning> returnByCondominiumFee(int id_resident, int id_condominium_fee) {
		return daos.returnByCondominiumFee(id_resident, id_condominium_fee);
	}


	public boolean insert(Warning warning) {
		return daos.insert(warning);
	}
}
