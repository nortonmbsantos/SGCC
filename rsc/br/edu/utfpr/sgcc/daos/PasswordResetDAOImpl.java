package br.edu.utfpr.sgcc.daos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.edu.utfpr.sgcc.models.PasswordReset;

public class PasswordResetDAOImpl extends BaseDAO {

	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(PasswordReset.class)
			.buildSessionFactory();

	public PasswordResetDAOImpl(DataSource dataSource) {
		super(dataSource);
	}

	public PasswordReset returnById(int id) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			PasswordReset passwordReset = session.get(PasswordReset.class, id);
			return passwordReset;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	public PasswordReset returnByIdUser(int idUser) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("from password_reset p where p.id_user = :idUser ");
			query.setParameter("idUser", idUser);
			PasswordReset passwordReset = (PasswordReset) query.getSingleResult();
			return passwordReset;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	public PasswordReset insert(PasswordReset passwordReset) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Integer id = (Integer) session.save(passwordReset);
			session.getTransaction().commit();
			passwordReset.setId(id);
			return passwordReset;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}

	}

	public Serializable insertSerializable(PasswordReset passwordReset) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Serializable serializable = session.save(passwordReset);
			session.getTransaction().commit();
			return serializable;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}

	}

	public void updateUsed(PasswordReset passwordReset) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("update password_reset set used = true where id = :id");
			query.setParameter("id", passwordReset.getId());
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace(System.err);
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

}
