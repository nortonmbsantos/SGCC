package br.edu.utfpr.sgcc.daos;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import br.edu.utfpr.sgcc.config.DBConfig;
import br.edu.utfpr.sgcc.models.Admin;

public class AdminDAOImpl implements AdminDAO {

	SessionFactory factory = DBConfig.getSessionFactory();


	public Admin returnById(int id) {
		Session session = null;
		
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Admin admin = session.get(Admin.class, id);
			return admin;

		} catch (Exception e) {
			return null;
			// TODO: handle exception
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public Admin returnByEmail(String email) {
		List<Admin> admins = new ArrayList<Admin>();

		admins = factory.getCurrentSession().createQuery("from admin where email=?").setParameter(0, email).list();

		if (admins.size() > 0) {
			return admins.get(0);
		} else {
			return null;
		}
	}

	public Admin login(Admin admin) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("from admin a where a.email = :email and a.password = :password ");
			query.setParameter("email", admin.getEmail());
			query.setParameter("password", admin.getPassword());
			Admin adminLogin = (Admin) query.getSingleResult();
			return adminLogin;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	public boolean insert(Admin admin) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.save(admin);
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
