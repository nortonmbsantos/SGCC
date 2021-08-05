package br.edu.utfpr.sgcc.service;

import java.util.List;

import br.edu.utfpr.sgcc.daos.ResidentDAOImpl;
import br.edu.utfpr.sgcc.models.Resident;

public class ResidentService {
	ResidentDAOImpl daos;

	public ResidentService() {
		daos = new ResidentDAOImpl();
	}

	public Resident returnById(int id) {
		return daos.returnById(id);
	}

	public Resident returnByLogin(String login) {
		return daos.returnByLogin(login);
	}

	public List<Resident> returnByCondominium(int idCondominium) {
		return daos.returnByCondominium(idCondominium);
	}

	public Resident login(Resident resident) {
		return daos.login(resident);
	}

	public boolean insert(Resident resident) {
		return daos.insert(resident);
	}
}
