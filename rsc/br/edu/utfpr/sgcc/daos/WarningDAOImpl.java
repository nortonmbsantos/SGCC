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

import br.edu.utfpr.sgcc.config.Encryptor;
import br.edu.utfpr.sgcc.models.Admin;
import br.edu.utfpr.sgcc.models.Fee;
import br.edu.utfpr.sgcc.models.Resident;
import br.edu.utfpr.sgcc.models.Warning;

public class WarningDAOImpl implements WarningDAO {

	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Warning.class)
			.buildSessionFactory();

	private DataSource dataSource;

	public WarningDAOImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Warning returnById(int id) {
		Session session = null;
		try {

			session = factory.getCurrentSession();
			session.beginTransaction();
			Warning warning = session.get(Warning.class, id);
			return warning;
		} catch (Exception e) {
			return null;
			// TODO: handle exception
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	public List<Warning> returnByResident(int id_resident) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("from warning w where w.idResident = :idResident");
			query.setParameter("idResident", id_resident);
			List<Warning> warnings = (List<Warning>) query.getResultList();

			return warnings;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	public boolean insert(Warning warning) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.save(warning);
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
