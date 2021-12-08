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

	public List<Guest> returnGuests() {
		return daos.returnGuests();
	}

	public List<Guest> returnGuests(int idBooking) {
		return daos.returnGuests(idBooking);
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

	public String returnGuestsForScript() {
		List<Guest> guests = this.returnGuests();
		StringBuilder sb = new StringBuilder();

		for (Guest g : guests) {
			sb.append("{ label: '").append(g.getName()).append("', value: '").append(g.getName()).append("', cpf: '")
					.append(g.getCpf()).append("', id: '").append(g.getId()).append("', phone:'").append(g.getPhone()).append("'  },");
		}

		return sb.toString();

	}

}
