package br.edu.utfpr.sgcc.daos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;

import br.edu.utfpr.sgcc.config.DBConfig;
import br.edu.utfpr.sgcc.models.Guest;
import br.edu.utfpr.sgcc.service.GuestService;

public class GuestDAOImpl {

	SessionFactory factory = DBConfig.getSessionFactory();

	public GuestDAOImpl() {
	}

	public Guest returnById(int id) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Guest guest = session.get(Guest.class, id);
			return guest;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	public Guest returnByCPF(String cpf) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Guest guest = session.get(Guest.class, cpf);
			return guest;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	/**
	 * @param text
	 * @return
	 */
	public List<Guest> returnByNameOrCPF(String text) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery(
					"from guest g where g.name like concat(concat('%', :text), '%') or  g.cpf like concat(concat('%', :text), '%') ");
			query.setParameter("text", text);
			@SuppressWarnings("unchecked")
			List<Guest> guests = (List<Guest>) query.getResultList();

			return guests;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	public List<Guest> insert(List<Guest> guests) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			for (Guest guest : guests) {
				session.saveOrUpdate(guest);
			}
			session.getTransaction().commit();
			return guests;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return guests;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	public static void main(String[] args) {
		GuestService service = new GuestService();
//		for (Guest g : service.returnByNameOrCPF("Jo�o")) {
//			System.out.println(g.getName() + " - " + g.getCpf());
//		}
		List<Guest> guests = new ArrayList<Guest>();
		Guest g1 = new Guest();
		g1.setName("Norton");
		g1.setCpf("09853127695");
		g1.setPhone("419912131");
		guests.add(g1);
		Guest g2 = new Guest();
		g2.setName("Marcos");
		g2.setCpf("09853145456");
		g2.setPhone("419912131");
		guests.add(g2);
		service.insert(guests);

	}

	public List<Guest> reportByGuestType(int id) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("as type from guest f where f.id_commom_area = :idCommomArea");
			query.setParameter("idCommomArea", id);
			@SuppressWarnings("unchecked")
			List<Guest> guests = (List<Guest>) query.getResultList();
			return guests;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	public boolean insert(Guest guest) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.save(guest);
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

	public List<Guest> pendingGuests(int id_condominium) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery(
					"FROM guest b inner join commom_area c ON c.id = b.id_commom_area WHERE c.id_condominium = :idCondominium and status_date is null");
			query.setParameter("idCondominium", id_condominium);
			@SuppressWarnings("unchecked")
			List<Guest> guests = (List<Guest>) query.getResultList();

			return guests;
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
