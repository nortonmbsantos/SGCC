package br.edu.utfpr.sgcc.daos;

import java.io.Serializable;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.edu.utfpr.sgcc.config.DBConfig;
import br.edu.utfpr.sgcc.models.PasswordReset;

public class PasswordResetDAOImpl {

	SessionFactory factory = DBConfig.getSessionFactory();

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
			Query query = session.createQuery("from password_reset p where p.idUser = :idUser ");
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
	
	public PasswordReset returnByValidationHash(String validationHash) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("from password_reset p where p.validationHash = :validationHash ");
			query.setParameter("validationHash", validationHash);
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
			Query query = session.createQuery("update password_reset set used = true, usedDate = now() where id = :id");
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
