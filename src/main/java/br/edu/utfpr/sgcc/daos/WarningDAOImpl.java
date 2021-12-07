package br.edu.utfpr.sgcc.daos;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.edu.utfpr.sgcc.config.DBConfig;
import br.edu.utfpr.sgcc.models.Warning;

public class WarningDAOImpl implements WarningDAO {

	SessionFactory factory = DBConfig.getSessionFactory();


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
			@SuppressWarnings("unchecked")
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

	public List<Warning> returnByCondominiumResident(int id_resident, int id_condominium) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("SELECT w FROM warning w INNER JOIN condominium_fee cf on cf.id = w.idCondominiumFee WHERE w.idResident = :idResident  and cf.idCondominium = :idCondominium");
			query.setParameter("idResident", id_resident);
			query.setParameter("idCondominium", id_condominium);
			@SuppressWarnings("unchecked")
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

	public List<Warning> returnByCondominiumFee(int id_resident, int id_condominium_fee) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("from warning w where w.idResident = :idResident and w.idCondominiumFee = :idCondominiumFee");
			query.setParameter("idResident", id_resident);
			query.setParameter("idCondominiumFee", id_condominium_fee);
			@SuppressWarnings("unchecked")
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

	public boolean remove(Warning warning) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.remove(warning);
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

	public boolean update(Warning warning) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.saveOrUpdate(warning);
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
