package br.edu.utfpr.sgcc.daos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import br.edu.utfpr.sgcc.config.DBConfig;
import br.edu.utfpr.sgcc.models.BookingGuest;
import br.edu.utfpr.sgcc.models.Fee;
import br.edu.utfpr.sgcc.models.Guest;

public class BookingGuestDAOImpl {

	SessionFactory factory = DBConfig.getSessionFactory();

	public BookingGuestDAOImpl() {
	}

	public boolean insert(BookingGuest booking) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.save(booking);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return false;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	public boolean insert(List<BookingGuest> guests) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			for (BookingGuest guest : guests) {
				session.saveOrUpdate(guest);
			}
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return false;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	public boolean removeAllFromBooking(int idBooking) {
		Session session = null;
		try {
			session = factory.getCurrentSession();

			session.beginTransaction();
			Query query = session.createSQLQuery("DELETE FROM booking_guest WHERE id_booking = :idBooking");
			query.setParameter("idBooking", idBooking);
			query.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return false;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	
	
}
