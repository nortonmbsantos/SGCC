package br.edu.utfpr.sgcc.daos;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.edu.utfpr.sgcc.config.DBConfig;
import br.edu.utfpr.sgcc.models.CommomArea;

public class CommomAreaDAOImpl {

	SessionFactory factory = DBConfig.getSessionFactory();

	public CommomArea returnById(int id) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			CommomArea area = session.get(CommomArea.class, id);
			return area;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	public List<CommomArea> list(int idCondominium) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("from commom_area a where idCondominium = :idCondominium ");
			query.setParameter("idCondominium", idCondominium);
			@SuppressWarnings("unchecked")
			List<CommomArea> areas = (List<CommomArea>) query.getResultList();

			return areas;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	public boolean insert(CommomArea area) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.save(area);
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

	public boolean update(CommomArea area) {
		Session session = null;
		try {
			CommomArea areaVal = this.returnById(area.getId());
			areaVal.setName(area.getName());
			areaVal.setBookingFee(area.getBookingFee());
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.update(areaVal);
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
