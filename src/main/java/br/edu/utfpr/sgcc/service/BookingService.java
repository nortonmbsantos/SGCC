package br.edu.utfpr.sgcc.service;

import java.util.List;

import javax.persistence.Query;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.edu.utfpr.sgcc.daos.BookingDAOImpl;
import br.edu.utfpr.sgcc.models.Booking;
import br.edu.utfpr.sgcc.models.CommomArea;
import br.edu.utfpr.sgcc.models.Report;

public class BookingService {
	BookingDAOImpl daos;
//	

	public BookingService() {
//		daos = context.getBean("BookingDAOBean", BookingDAOImpl.class);
//		daos = new BookingDAOImpl(context.getBean("DAOBean", DataSource.class));
		daos = new BookingDAOImpl();
	}

	public Booking returnById(int id) {
		return daos.returnById(id);
	}

	public List<Booking> pendingBookings(int id_condominium) {
		return daos.pendingBookings(id_condominium);
	}

	public List<Booking> pendingBookingsByArea(int id_commom_area) {
		List<Booking> bookings = daos.pendingBookingsByArea(id_commom_area);
		UserService userService = new UserService();
		for (Booking b : bookings) {
			b.setResidentName(userService.returnById(b.getIdResident()).getFirstName());
		}

		
		return bookings;
	}

	public List<Booking> acceptedBookingsByArea(int idCommomArea) {
		List<Booking> bookings = daos.acceptedBookingsByArea(idCommomArea);
		UserService userService = new UserService();
		for (Booking b : bookings) {
			b.setResidentName(userService.returnById(b.getIdResident()).getFirstName());
		}

		
		return bookings;
	}

	public List<Booking> pendingBookingsByResident(int id_resident) {
		return daos.pendingBookingsByResident(id_resident);
	}

	public List<Booking> acceptedBookingsByCondominium(int id_condominium) {
		return daos.acceptedBookingsByCondominium(id_condominium);
	}

	public List<Booking> bookingsByResidentAndCondominium(int idResident, int idCondominium) {
		List<Booking> bookings = daos.bookingsByResidentAndCondominium(idResident, idCondominium);
		CommomAreaService commomAreaService = new CommomAreaService();
		for(Booking b : bookings) {
			CommomArea c = commomAreaService.returnById(b.getIdCommomArea());
			b.setCommomArea(c);
		}
		return bookings;
	}

	public List<Booking> bookingsByResident(int idResident) {
		List<Booking> bookings = daos.bookingsByResident(idResident);
		
		CommomAreaService commomAreaService = new CommomAreaService();
		CondominiumService condominiumService = new CondominiumService();
		for(Booking b : bookings) {
			CommomArea c = commomAreaService.returnById(b.getIdCommomArea());
			c.setCondominium(condominiumService.returnById(c.getIdCondominium()));
			b.setCommomArea(c);
		}
		return bookings;
	}

	public List<Report> countPendingBookings(int id_condominium) {
		return daos.countPendingBookings(id_condominium);
	}

	public boolean accept(int id) {
		return daos.acceptBooking(id);
	}

	public boolean refuse(int id) {
		return daos.refuseBooking(id);
	}

	public List<Booking> returnByCondominiumBookingId(int id) {
		return daos.returnByCondominiumBookingId(id);
	}

	public boolean insert(Booking booking) {
		return daos.insert(booking);
	}

	public static void main(String[] args) {
		BookingService s = new BookingService();

		for (Booking b : s.bookingsByResident(10)) {
			System.out.println(b.getCommomAreaName() + " - " + b.getBookingDate());
		}
	}
}
