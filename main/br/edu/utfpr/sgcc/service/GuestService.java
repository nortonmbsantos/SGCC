package br.edu.utfpr.sgcc.service;

import java.util.List;

import br.edu.utfpr.sgcc.daos.GuestDAOImpl;
import br.edu.utfpr.sgcc.models.Guest;

public class GuestService {
	private GuestDAOImpl dao;
//	

	public GuestService() {
//		dao = context.getBean("GuestDAOBean", GuestDAOImpl.class);
//		dao = new GuestDAOImpl(context.getBean("DAOBean", DataSource.class));
		dao = new GuestDAOImpl();
	}

	public Guest returnById(int id) {
		return dao.returnById(id);
	}

	public List<Guest> pendingGuests(int id_condominium) {
		return dao.pendingGuests(id_condominium);
	}
	
	public List<Guest> returnByNameOrCPF(String text) {
		return dao.returnByNameOrCPF(text);
	}

	public List<Guest> insert(List<Guest> guests) {
		return dao.insert(guests);
	}

	public boolean insert(Guest guest) {
		return dao.insert(guest);
	}

}
