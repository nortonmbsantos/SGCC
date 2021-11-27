package br.edu.utfpr.sgcc.daos;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.edu.utfpr.sgcc.config.DBConfig;
import br.edu.utfpr.sgcc.models.CondominiumEntryRequest;
import br.edu.utfpr.sgcc.service.UserService;

public class CondominiumEntryRequestDAOImpl {

	SessionFactory factory = DBConfig.getSessionFactory();

	public CondominiumEntryRequest returnById(int id) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			CondominiumEntryRequest request = session.get(CondominiumEntryRequest.class, id);
			session.getTransaction().commit();
			return request;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	public CondominiumEntryRequest returnByCondominiumAndResident(int idCondominium, int idResident) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("from condominium_entry_request a where idResident = :idResident and idCondominium = :idCondominium  ORDER by id desc limit 1");
			query.setParameter("idResident", idResident);
			query.setParameter("idCondominium", idCondominium);
			CondominiumEntryRequest request = (CondominiumEntryRequest) query.getSingleResult();

			return request;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	public List<CondominiumEntryRequest> list(int idResident) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("from condominium_entry_request a where idResident = :idResident ");
			query.setParameter("idResident", idResident);
			@SuppressWarnings("unchecked")
			List<CondominiumEntryRequest> requests = (List<CondominiumEntryRequest>) query.getResultList();

			return requests;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	public List<CondominiumEntryRequest> listByCondominium(int idCondominium) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("from condominium_entry_request a where idCondominium = :idCondominium ");
			query.setParameter("idCondominium", idCondominium);
			@SuppressWarnings("unchecked")
			List<CondominiumEntryRequest> requests = (List<CondominiumEntryRequest>) query.getResultList();

			return requests;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}
	
	public boolean accept(int id) {
		Session session = null;
		try {
			CondominiumEntryRequest request = this.returnById(id);
			request.setResponseDate(new Date());
			request.setAccepted(true);
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.update(request);
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
	
	public boolean refuse(int id) {
		Session session = null;
		try {
			CondominiumEntryRequest request = this.returnById(id);
			request.setResponseDate(new Date());
			request.setAccepted(false);
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.update(request);
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
	
	public List<CondominiumEntryRequest> listAvailableByCondominium(int idCondominium) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery(
					"from condominium_entry_request a where idCondominium = :idCondominium and responseDate = null ");
			query.setParameter("idCondominium", idCondominium);
			@SuppressWarnings("unchecked")
			List<CondominiumEntryRequest> requests = (List<CondominiumEntryRequest>) query.getResultList();

			return requests;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	public boolean insert(CondominiumEntryRequest request) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.save(request);
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
