package br.edu.utfpr.sgcc.service;

import java.util.List;

import br.edu.utfpr.sgcc.daos.GuestDAOImpl;
import br.edu.utfpr.sgcc.models.Guest;

public class GuestService {
	private GuestDAOImpl daos;
//	

	public GuestService() {
//		daos = context.getBean("GuestDAOBean", GuestDAOImpl.class);
//		daos = new GuestDAOImpl(context.getBean("DAOBean", DataSource.class));
		daos = new GuestDAOImpl();
	}

	public Guest returnById(int id) {
		return daos.returnById(id);
	}

	public Guest returnByCpf(String cpf) {
		return daos.returnByCPF(cpf);
	}

	public List<Guest> pendingGuests(int id_condominium) {
		return daos.pendingGuests(id_condominium);
	}
	
	public List<Guest> returnByNameOrCPF(String text) {
		return daos.returnByNameOrCPF(text);
	}

	public List<Guest> insert(List<Guest> guests) {
		return daos.insert(guests);
	}

	public boolean insert(Guest guest) {
		return daos.insert(guest);
	}

}
