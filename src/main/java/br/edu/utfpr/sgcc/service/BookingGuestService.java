package br.edu.utfpr.sgcc.service;

import java.util.List;

import br.edu.utfpr.sgcc.daos.BookingGuestDAOImpl;
import br.edu.utfpr.sgcc.models.BookingGuest;

public class BookingGuestService {
	BookingGuestDAOImpl daos;
//	

	public BookingGuestService() {
		daos = new BookingGuestDAOImpl();
	}


	public boolean insert(BookingGuest guest) {
		return daos.insert(guest);
	}

	public boolean insert(List<BookingGuest> guests) {
		return daos.insert(guests);
	}

}
