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
import br.edu.utfpr.sgcc.models.User;

public class UserDAOImpl implements UserDAO {

	SessionFactory factory = DBConfig.getSessionFactory();

	public User returnById(int id) {
		Session session = null;
		
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			User user = session.get(User.class, id);
			return user;

		} catch (Exception e) {
			return null;
			// TODO: handle exception
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	public User returnByUserName(String userName) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("from users a where a.userName = :userName ");
			query.setParameter("userName", userName);
			User userLogin = (User) query.getSingleResult();
			return userLogin;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}
	
	public User returnByEmail(String email) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("from users a where a.email = :email ");
			query.setParameter("email", email);
			User userLogin = (User) query.getSingleResult();
			return userLogin;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	public User login(User user) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("from users a where a.userName = :userName and a.password = :password ");
			query.setParameter("userName", user.getUserName());
			query.setParameter("password", user.getPassword());
			User userLogin = (User) query.getSingleResult();
			return userLogin;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	public boolean insert(User user) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.save(user);
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

	public boolean update(User user) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.update(user);
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
