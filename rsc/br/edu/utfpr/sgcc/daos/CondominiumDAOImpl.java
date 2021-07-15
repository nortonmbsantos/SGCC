package br.edu.utfpr.sgcc.daos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.edu.utfpr.sgcc.models.Admin;
import br.edu.utfpr.sgcc.models.Condominium;
import br.edu.utfpr.sgcc.models.User;

public class CondominiumDAOImpl extends BaseDAO {

	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Condominium.class)
			.buildSessionFactory();

	public CondominiumDAOImpl(DataSource dataSource) {
		super(dataSource);
	}

	public Condominium returnById(int id) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Condominium condominium = session.get(Condominium.class, id);
			return condominium;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	public Condominium returnByCode(String code) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("from condominium c where c.code = :code ");
			query.setParameter("code", code);
			Condominium condominium = (Condominium) query.getSingleResult();
			return condominium;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	public List<Condominium> list(int idUser) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("from condominium a where idUser = :idUser ");
			query.setParameter("idUser", idUser);
			List<Condominium> condominiuns = (List<Condominium>) query.getResultList();

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

	public List<Condominium> listSidebar(int idUser) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("from condominium a where idUser = :idUser ");
			query.setParameter("idUser", idUser);
			query.setMaxResults(5);
			List<Condominium> condominiuns = (List<Condominium>) query.getResultList();
			
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

	public Condominium insert(Condominium condominium) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Integer id = (Integer) session.save(condominium);
			session.getTransaction().commit();
			return condominium;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}

	}

	public Serializable insertSerializable(Condominium condominium) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Serializable serializable = session.save(condominium);
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

	public boolean update(Condominium condominium) {
		Session session = null;
		try {
			Condominium condominiumVal = this.returnById(condominium.getId());
			condominium.setIdUser(condominiumVal.getIdUser());
			condominium.setCode(condominiumVal.getCode());
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.update(condominium);
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

	public void updateCode(Condominium condominium) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("update condominium set code = substring(md5(:id),1,6) where id = :id");
			query.setParameter("id", condominium.getId());
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
