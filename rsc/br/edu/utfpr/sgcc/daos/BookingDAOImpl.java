package br.edu.utfpr.sgcc.daos;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import br.edu.utfpr.sgcc.models.Booking;
import br.edu.utfpr.sgcc.models.Report;
import br.edu.utfpr.sgcc.service.MyUserDetailsService;
import br.edu.utfpr.sgcc.service.UserService;

public class BookingDAOImpl {

	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Booking.class)
			.buildSessionFactory();

	private DataSource dataSource;

	public BookingDAOImpl(DataSource dataSource) {
		this.dataSource = dataSource;
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
			Query query = session.createQuery("from booking f where f.idCondominiumBooking = :idCondominiumBooking ");
			query.setParameter("idCondominiumBooking", id);
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
			booking.setStatus_date(new java.util.Date());
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
			booking.setStatus_date(new java.util.Date());
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

	public List<Booking> pendingBookings(int id_condominium) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery(
					"FROM booking b inner join commom_area c ON c.id = b.id_commom_area WHERE c.idCondominium = :idCondominium and status_date is null");
			query.setParameter("idCondominium", id_condominium);
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

	public List<Booking> pendingBookingsByArea(int id_commom_area) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Query query = session
					.createQuery("FROM booking b WHERE b.id_commom_area = :idCommomArea and status_date is null");
			query.setParameter("idCommomArea", id_commom_area);
			List<Booking> bookings = (List<Booking>) query.getResultList();
			UserService userService = new UserService();
			for (Booking b : bookings) {
				b.setResident_name(userService.returnById(b.getId_resident()).getFirstName());
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
			List<Booking> bookings = (List<Booking>) query.getResultList();
			UserService userService = new UserService();
			for (Booking b : bookings) {
				b.setResident_name(userService.returnById(b.getId_resident()).getFirstName());
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

	public List<Report> countPendingBookings(int id_condominium) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createSQLQuery(
					"SELECT c.id as title, count(b.id) as value FROM booking b inner join commom_area c ON c.id = b.id_commom_area WHERE c.idCondominium = :idCondominium and b.status_date is null");
			query.setParameter("idCondominium", id_condominium);
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
