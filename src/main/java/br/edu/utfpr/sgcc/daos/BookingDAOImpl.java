package br.edu.utfpr.sgcc.daos;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import br.edu.utfpr.sgcc.config.DBConfig;
import br.edu.utfpr.sgcc.models.Booking;
import br.edu.utfpr.sgcc.models.Report;
import br.edu.utfpr.sgcc.service.UserService;

public class BookingDAOImpl {

	SessionFactory factory =  DBConfig.getSessionFactory();

	public BookingDAOImpl() {
	}

	public Booking returnById(int id) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Booking Booking = session.get(Booking.class, id);
			return Booking;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	public List<Booking> returnByCondominiumBookingId(int id) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("from booking f where f.id_condominium_booking = :idCondominiumBooking ");
			query.setParameter("idCondominiumBooking", id);
			@SuppressWarnings("unchecked")
			List<Booking> bookings = (List<Booking>) query.getResultList();

			return bookings;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	public boolean acceptBooking(int id) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Booking booking = this.returnById(id);
			booking.setStatus(true);
			booking.setStatusDate(new java.util.Date());
			session.update(booking);
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

	public boolean refuseBooking(int id) {
		Session session = null;
		try {
			Booking booking = this.returnById(id);
			booking.setStatus(false);
			booking.setStatusDate(new java.util.Date());
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.update(booking);
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

	public List<Booking> reportByBookingType(int id) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("as type from booking f where f.id_commom_area = :idCommomArea");
			query.setParameter("idCommomArea", id);
			@SuppressWarnings("unchecked")
			List<Booking> bookings = (List<Booking>) query.getResultList();

			return bookings;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	public boolean insert(Booking booking) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			int id = (Integer) session.save(booking);
			booking.setId(id);
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

	public List<Booking> pendingBookings(int id_condominium) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery(
					"FROM booking b inner join commom_area c ON c.id = b.id_commom_area WHERE c.id_condominium = :idCondominium and status_date is null");
			query.setParameter("idCondominium", id_condominium);
			@SuppressWarnings("unchecked")
			List<Booking> bookings = (List<Booking>) query.getResultList();

			return bookings;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}
	

	public List<Booking> acceptedBookingsByArea(int idCommomArea) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Query query = session
					.createQuery("FROM booking b WHERE b.idCommomArea = :idCommomArea and status = 1");
			query.setParameter("idCommomArea", idCommomArea);
			@SuppressWarnings("unchecked")
			List<Booking> bookings = (List<Booking>) query.getResultList();
			
			return bookings;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}
	
	
	public List<Booking> pendingBookingsByArea(int idCommomArea) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Query query = session
					.createQuery("FROM booking b WHERE b.idCommomArea = :idCommomArea and statusDate is null");
			query.setParameter("idCommomArea", idCommomArea);
			@SuppressWarnings("unchecked")
			List<Booking> bookings = (List<Booking>) query.getResultList();

			return bookings;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	public static void main(String[] args) {
		BookingDAOImpl bs = new BookingDAOImpl();
		for(Booking b : bs.pendingBookingsByArea(1)) {
			System.out.println(b.getId());
		}
		
	}
	
	public List<Booking> acceptedBookingsByCondominium(int id_condominium) {
		Session session = null;
		try {
			session = factory.getCurrentSession();

			session.beginTransaction();
			Query query = session.createSQLQuery(
					"SELECT b.booking_date, ca.name, r.first_name FROM booking b inner join commom_area ca on ca.id = b.id_commom_area inner join users r on r.id = b.id_resident WHERE ca.id_condominium = :idCondominium and status = 1 and booking_date >= now() order by b.booking_date");
			query.setParameter("idCondominium", id_condominium);
			@SuppressWarnings("unchecked")
			List<Object[]> objects = query.getResultList();
			List<Booking> bookings = new ArrayList<>();

			for (Object[] o : objects) {
				Booking b = new Booking();
				b.setBookingDate((Date) o[0]);
				b.setCommomAreaName((String) o[1]);
				b.setResidentName((String) o[2]);
				bookings.add(b);
			}

			return bookings;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	public List<Booking> pendingBookingsByResident(int id_resident) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery(
					"FROM booking b WHERE b.id_resident = :idResident AND b.booking_date >= date(now()) order by b.booking_date desc");
			query.setParameter("idResident", id_resident);
			@SuppressWarnings("unchecked")
			List<Booking> bookings = (List<Booking>) query.getResultList();
			UserService userService = new UserService();
			for (Booking b : bookings) {
				b.setResidentName(userService.returnById(b.getIdResident()).getFirstName());
			}

			return bookings;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	public List<Booking> bookingsByResident(int idResident) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery(
					"FROM booking b WHERE b.idResident = :idResident order by b.bookingDate desc");
			query.setParameter("idResident", idResident);
			@SuppressWarnings("unchecked")
			List<Booking> bookings = (List<Booking>) query.getResultList();
			
			return bookings;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	public List<Booking> bookingsByResidentAndCondominium(int idResident, int idCondominium) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery(
					"SELECT b FROM booking b inner join commom_area ca on ca.id = b.idCommomArea WHERE b.idResident = :idResident  and ca.idCondominium = :idCondominium order by b.bookingDate desc");
			query.setParameter("idResident", idResident);
			query.setParameter("idCondominium", idCondominium);
			@SuppressWarnings("unchecked")
			List<Booking> bookings = (List<Booking>) query.getResultList();
			
			return bookings;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}
	
	public List<Report> countPendingBookings(int id_condominium) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createSQLQuery(
					"SELECT c.id as title, count(b.id) as value FROM booking b inner join commom_area c ON c.id = b.id_commom_area WHERE c.id_condominium = :idCondominium and b.status_date is null");
			query.setParameter("idCondominium", id_condominium);
			@SuppressWarnings("unchecked")
			List<Object[]> report = query.getResultList();
			List<Report> reports = new ArrayList<Report>();

			for (Object[] obj : report) {
				Report r = new Report();
				if (obj[0] == null) {
					r.setTitle("0");
				} else {
					r.setTitle(String.valueOf(obj[0]));
				}
				r.setValue(Double.valueOf(String.valueOf(obj[1])));
				reports.add(r);
			}
			return reports;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

}
