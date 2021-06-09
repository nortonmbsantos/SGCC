package br.edu.utfpr.sgcc.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import br.edu.utfpr.sgcc.models.Condominium;
import br.edu.utfpr.sgcc.models.CondominiumEntryRequest;
import br.edu.utfpr.sgcc.models.CondominiumResident;
import br.edu.utfpr.sgcc.models.Fee;
import br.edu.utfpr.sgcc.models.User;
import br.edu.utfpr.sgcc.service.UserService;

public class CondominiumResidentDAOImpl {

	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
			.addAnnotatedClass(CondominiumResident.class).buildSessionFactory();

	private DataSource dataSource;

	public CondominiumResidentDAOImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public boolean save(CondominiumResident resident) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.saveOrUpdate(resident);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return false;
		}
	}

	public List<Condominium> returnCondominiunsByResident(int id_resident) {
		Session session = null;
		try {
			session = factory.getCurrentSession();

			session.beginTransaction();
			Query query = session.createSQLQuery(
					"SELECT c.id, c.name, c.description FROM condominium_resident cr inner join condominium c on c.id = cr.id_condominium WHERE cr.id_resident = :idResident and cr.active = 1");
			query.setParameter("idResident", id_resident);
			List<Object[]> objects = query.getResultList();
			List<Condominium> condominiuns = new ArrayList<>();

			for (Object[] o : objects) {
				Condominium c = new Condominium();
				c.setId((int) o[0]);
				c.setName((String) o[1]);
				c.setDescription((String) o[2]);

				condominiuns.add(c);
			}

			return condominiuns;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	public CondominiumResident returnByResidentAndCondominium(int id_resident, int id_condominium) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery(
					"from condominium_resident cr where cr.idCondominium = :idCondominium and cr.idResident = :idResident ");
			query.setParameter("idCondominium", id_condominium);
			query.setParameter("idResident", id_resident);
			CondominiumResident resident = (CondominiumResident) query.getSingleResult();

			return resident;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	public List<CondominiumResident> returnUserByCondominium(int id, int page, int results, String name) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("from condominium_resident u where u.idCondominium = :idCondominium");
			query.setParameter("idCondominium", id);
			query.setFirstResult((page - 1) * results);
			query.setMaxResults(results);
			List<CondominiumResident> residents = (List<CondominiumResident>) query.getResultList();

			UserService userService = new UserService();

			for (CondominiumResident r : residents) {
				r.setResident(userService.returnById(r.getIdResident()));
			}

			return residents;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	public int returnCount(int id) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createSQLQuery(
					"SELECT count(id) as total FROM condominium_resident WHERE id_condominium = :idCondominium");
			query.setParameter("idCondominium", id);

			int count = Integer.valueOf(String.valueOf(query.getSingleResult()));
			return count;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return 0;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	public boolean block(int id_resident, int id_condominium) {
		Session session = null;
		try {
			CondominiumResident condominiumResident = returnByResidentAndCondominium(id_resident, id_condominium);
			condominiumResident.setActive(false);
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.update(condominiumResident);
			session.getTransaction().commit();

			return true;
		} catch (Exception e) {
			e.printStackTrace(System.err);
		} finally {
			if (null != session) {
				session.close();
			}
		}

		return false;
	}

	public boolean unblock(int id_resident, int id_condominium) {
		Session session = null;
		try {
			CondominiumResident condominiumResident = returnByResidentAndCondominium(id_resident, id_condominium);
			condominiumResident.setActive(true);
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.update(condominiumResident);
			session.getTransaction().commit();

			return true;
		} catch (Exception e) {
			e.printStackTrace(System.err);
		} finally {
			if (null != session) {
				session.close();
			}
		}
		return false;

	}

}
