package br.edu.utfpr.sgcc.daos;

import java.util.List;

import br.edu.utfpr.sgcc.models.Booking;

public interface BookingDAO {
	public Booking returnById(int id);

	public List<Booking> returnByIdCondominium(int id);

	public boolean insert(Booking booking);
}
