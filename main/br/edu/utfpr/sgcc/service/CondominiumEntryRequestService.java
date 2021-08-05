package br.edu.utfpr.sgcc.service;

import java.util.List;

import javax.persistence.Query;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.edu.utfpr.sgcc.daos.AdminDAOImpl;
import br.edu.utfpr.sgcc.daos.CondominiumDAOImpl;
import br.edu.utfpr.sgcc.daos.CondominiumEntryRequestDAOImpl;
import br.edu.utfpr.sgcc.models.Condominium;
import br.edu.utfpr.sgcc.models.CondominiumEntryRequest;
import br.edu.utfpr.sgcc.models.CondominiumResident;

public class CondominiumEntryRequestService {
	CondominiumEntryRequestDAOImpl dao;

	public CondominiumEntryRequestService() {
//		dao = context.getBean("CondominiumDAOBean", CondominiumDAOImpl.class);
		dao = new CondominiumEntryRequestDAOImpl();
	}

	public CondominiumEntryRequest returnById(int id) {
		return dao.returnById(id);
	}

	public CondominiumEntryRequest returnByCondominiumAndResident(int idCondominium, int idResident) {
		return dao.returnByCondominiumAndResident(idCondominium, idResident);
	}

	public boolean accept(int id) {
		boolean result = dao.accept(id);
		if (result) {
			CondominiumEntryRequest request = this.returnById(id);
			CondominiumResidentService condominiumResidentService = new CondominiumResidentService();
			CondominiumResident resident = new CondominiumResident();
			resident.setIdCondominium(request.getIdCondominium());
			resident.setIdResident(request.getIdResident());
			resident.setActive(true);
			condominiumResidentService.save(resident);
		}
		return result;
	}

	public boolean refuse(int id) {
		return dao.refuse(id);
	}

	public List<CondominiumEntryRequest> list(int idResident) {
		return dao.list(idResident);
	}

	public List<CondominiumEntryRequest> listByCondominium(int idCondominium) {
		return dao.list(idCondominium);
	}

	public List<CondominiumEntryRequest> listAvailableByCondominium(int idCondominium) {
		return dao.listAvailableByCondominium(idCondominium);
	}

	public boolean insert(CondominiumEntryRequest request) {
		return dao.insert(request);
	}

}
