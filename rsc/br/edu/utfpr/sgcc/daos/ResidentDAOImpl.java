package br.edu.utfpr.sgcc.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import br.edu.utfpr.sgcc.config.DBConfig;
import br.edu.utfpr.sgcc.config.Encryptor;
import br.edu.utfpr.sgcc.models.Admin;
import br.edu.utfpr.sgcc.models.CondominiumResident;
import br.edu.utfpr.sgcc.models.Fee;
import br.edu.utfpr.sgcc.models.Resident;

public class ResidentDAOImpl implements ResidentDAO {

	SessionFactory factory = DBConfig.getSessionFactory();

	private DataSource dataSource;

	public ResidentDAOImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Resident returnById(int id) {
		Session session = null;
		try {

			session = factory.getCurrentSession();
			session.beginTransaction();
			Resident resident = session.get(Resident.class, id);
			return resident;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public Resident returnByLogin(String login) {
		List<Resident> residents = new ArrayList<Resident>();

		residents = factory.getCurrentSession().createQuery("from resident where login=?").setParameter(0, login)
				.list();

		if (residents.size() > 0) {
			return residents.get(0);
		} else {
			return null;
		}
	}

	public List<Resident> returnByCondominium(int id) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("from resident r where r.idCondominium = :idCondominium");
			query.setParameter("idCondominium", id);
			List<Resident> residents = (List<Resident>) query.getResultList();

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

	
	public Resident login(Resident resident) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("from resident a where a.login = :login and a.password = :password ");
			query.setParameter("login", resident.getLogin());
			query.setParameter("password", resident.getPassword());
			Resident residentLogin = (Resident) query.getSingleResult();
			return residentLogin;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	public boolean insert(Resident resident) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.save(resident);
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

}
