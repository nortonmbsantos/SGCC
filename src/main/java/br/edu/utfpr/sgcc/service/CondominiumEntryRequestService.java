package br.edu.utfpr.sgcc.service;

import java.util.List;

import javax.persistence.Query;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.edu.utfpr.sgcc.daos.CondominiumDAOImpl;
import br.edu.utfpr.sgcc.daos.CondominiumEntryRequestDAOImpl;
import br.edu.utfpr.sgcc.models.Condominium;
import br.edu.utfpr.sgcc.models.CondominiumEntryRequest;
import br.edu.utfpr.sgcc.models.CondominiumResident;

public class CondominiumEntryRequestService {
	CondominiumEntryRequestDAOImpl daos;

	public CondominiumEntryRequestService() {
//		daos = context.getBean("CondominiumDAOBean", CondominiumDAOImpl.class);
		daos = new CondominiumEntryRequestDAOImpl();
	}

	public CondominiumEntryRequest returnById(int id) {
		return daos.returnById(id);
	}

	public CondominiumEntryRequest returnByCondominiumAndResident(int idCondominium, int idResident) {
		return daos.returnByCondominiumAndResident(idCondominium, idResident);
	}

	public boolean accept(int id) {
		boolean result = daos.accept(id);
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
		return daos.refuse(id);
	}

	public List<CondominiumEntryRequest> list(int idResident) {
		return daos.list(idResident);
	}

	public List<CondominiumEntryRequest> listByCondominium(int idCondominium) {
		return daos.list(idCondominium);
	}

	public List<CondominiumEntryRequest> listAvailableByCondominium(int idCondominium) {
		List<CondominiumEntryRequest> entries = daos.listAvailableByCondominium(idCondominium);
		
		
		UserService s = new UserService();
		for (CondominiumEntryRequest r : entries) {
			r.setUser(s.returnById(r.getIdResident()));
		}
		
		return entries;
	}

	public boolean insert(CondominiumEntryRequest request) {
		return daos.insert(request);
	}

}
