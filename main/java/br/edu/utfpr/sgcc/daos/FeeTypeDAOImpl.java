package br.edu.utfpr.sgcc.daos;

import java.util.List;

import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import br.edu.utfpr.sgcc.config.DBConfig;
import br.edu.utfpr.sgcc.models.FeeType;

public class FeeTypeDAOImpl {

	SessionFactory factory = DBConfig.getSessionFactory();

	public FeeType returnById(int id) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			FeeType feeType = session.get(FeeType.class, id);
			return feeType;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	public List<FeeType> list() {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("from fee_type");
			@SuppressWarnings("unchecked")
			List<FeeType> feeTypes = (List<FeeType>) query.getResultList();

			return feeTypes;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

}
