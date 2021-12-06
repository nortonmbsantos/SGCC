package br.edu.utfpr.sgcc.service;

import java.util.List;

import javax.persistence.Query;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.edu.utfpr.sgcc.daos.CondominiumDAOImpl;
import br.edu.utfpr.sgcc.daos.CondominiumEntryRequestDAOImpl;
import br.edu.utfpr.sgcc.daos.CondominiumResidentDAOImpl;
import br.edu.utfpr.sgcc.models.Condominium;
import br.edu.utfpr.sgcc.models.CondominiumEntryRequest;
import br.edu.utfpr.sgcc.models.CondominiumResident;

public class CondominiumResidentService {
	CondominiumResidentDAOImpl daos;

	public CondominiumResidentService() {
		daos = new CondominiumResidentDAOImpl();
	}

	public List<Condominium> returnCondominiunsByResident(int id_resident) {
		return daos.returnCondominiunsByResident(id_resident);
	}

	public CondominiumResident returnByResidentAndCondominium(int idResident, int idCondominium) {
		CondominiumResident cr = daos.returnByResidentAndCondominium(idResident, idCondominium);
		if (cr != null) {
			cr.setResident(new UserService().returnById(cr.getIdResident()));
		}
		return cr;
	}

	public CondominiumResident returnByResidentAndCondominium(CondominiumResident condominiumResident) {
		return returnByResidentAndCondominium(condominiumResident.getIdResident(),
				condominiumResident.getIdCondominium());
	}

	public boolean save(CondominiumResident resident) {
		return daos.save(resident);
	}

	public int returnCount(int id) {
		return daos.returnCount(id);
	}

	public List<CondominiumResident> returnUserByCondominium(int id) {
		return returnUserByCondominium(id, 1);
	}

	public List<CondominiumResident> returnUserByCondominium(int id, String name) {
		return returnUserByCondominium(id, 1, 20, name);
	}

	public List<CondominiumResident> returnUserByCondominium(int id, int page) {
		return returnUserByCondominium(id, page, 20);
	}

	public List<CondominiumResident> returnUserByCondominium(int id, int page, int results) {
		return returnUserByCondominium(id, page, results, "");
	}

	public List<CondominiumResident> returnUserByCondominium(int id, int page, int results, String name) {
		List<CondominiumResident> residents = daos.returnUserByCondominium(id, page, results, name);

		UserService userService = new UserService();

		for (CondominiumResident r : residents) {
			r.setResident(userService.returnById(r.getIdResident()));
		}

		return residents;
	}

	public boolean block(int id_resident, int id_condominium) {
		return daos.block(id_resident, id_condominium);
	}

	public boolean unblock(int id_resident, int id_condominium) {
		return daos.unblock(id_resident, id_condominium);
	}

	public static void main(String[] args) {
		CondominiumResidentService service = new CondominiumResidentService();
		service.returnUserByCondominium(1);
	}

}
