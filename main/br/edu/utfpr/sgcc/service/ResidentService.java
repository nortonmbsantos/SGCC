package br.edu.utfpr.sgcc.service;

import java.util.List;

import br.edu.utfpr.sgcc.daos.ResidentDAOImpl;
import br.edu.utfpr.sgcc.models.Resident;

public class ResidentService {
	ResidentDAOImpl dao;

	public ResidentService() {
		dao = new ResidentDAOImpl();
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
