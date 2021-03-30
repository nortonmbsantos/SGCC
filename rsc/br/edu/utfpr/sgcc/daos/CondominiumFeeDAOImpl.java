package br.edu.utfpr.sgcc.daos;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import br.edu.utfpr.sgcc.models.Condominium;
import br.edu.utfpr.sgcc.models.CondominiumFee;
import br.edu.utfpr.sgcc.models.Fee;
import br.edu.utfpr.sgcc.models.Report;
import br.edu.utfpr.sgcc.service.CondominiumFeeService;

public class CondominiumFeeDAOImpl {

	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(CondominiumFee.class)
			.buildSessionFactory();

	private DataSource dataSource;

	public CondominiumFeeDAOImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public CondominiumFee returnById(int id) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			CondominiumFee condominiumFee = session.get(CondominiumFee.class, id);
			return condominiumFee;

		} catch (Exception e) {
			// TODO: handle exception
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	public CondominiumFee returnLastIdByIdCondominium(int id_condominium) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery(
				"from condominium_fee f where f.id_condominium = :idCondominium order by f.id desc limit 1");
		query.setParameter("idCondominium", id_condominium);

		return (CondominiumFee) query.getSingleResult();
	}

	public List<CondominiumFee> returnByCondominiumId(int id, int page, int results) {
		Session session = null;
		try {
			session = factory.getCurrentSession();

			session.beginTransaction();
			Query query = session.createQuery(
					"from condominium_fee f where f.id_condominium = :idCondominium order by f.closingDate desc");
			query.setParameter("idCondominium", id);
			query.setFirstResult((page - 1) * results);
			query.setMaxResults(results);
			List<CondominiumFee> fees = (List<CondominiumFee>) query.getResultList();

			return fees;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	public List<CondominiumFee> returnActives(int idCondominium){
		Session session = null;
		try {
			session = factory.getCurrentSession();

			session.beginTransaction();
			Query query = session.createQuery(
					"from condominium_fee f where f.id_condominium = :idCondominium and f.finished = 0 order by f.closingDate desc");
			query.setParameter("idCondominium", idCondominium);
			List<CondominiumFee> fees = (List<CondominiumFee>) query.getResultList();

			return fees;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}
		
	}
	
	public int returnCount(int id) {
		Session session = null;
		try {
			session = factory.getCurrentSession();

			session.beginTransaction();
			Query query = session.createSQLQuery(
					"SELECT count(id) as total FROM condominium_fee WHERE id_condominium = :idCondominium");
			query.setParameter("idCondominium", id);

			int count =  Integer.valueOf(String.valueOf(query.getSingleResult()));
			return count;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return 0;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}
public static void main(String[] args) {
	System.out.println(new CondominiumFeeService().returnCount(1)); 
	
}
	public List<Report> reportByClosingDate(int id) {
		Session session = null;
		try {
			session = factory.getCurrentSession();

			session.beginTransaction();
			Query query = session.createSQLQuery("SELECT cf.closingDate, SUM(f.value) as value FROM fee f "
					+ "inner join condominium_fee cf on cf.id = f.idCondominiumFee where cf.id_condominium = :idCondominium group by idCondominiumFee order by cf.closingDate desc ");
			query.setParameter("idCondominium", id);
			List<Object[]> report = query.getResultList();
			List<Report> reports = new ArrayList<Report>();

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			for (Object[] obj : report) {
				Report r = new Report();
				r.setTitle(sdf.format((Date) obj[0]));
				r.setValue((double) obj[1]);
				reports.add(r);
			}
			return reports;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	public List<Report> reportByFeeType(int id) {
		Session session = null;
		try {
			session = factory.getCurrentSession();

			session.beginTransaction();
			Query query = session.createSQLQuery(
					"SELECT ft.description, sum(f.value) from condominium_fee cf INNER JOIN fee f on f.idCondominiumFee = cf.id INNER JOIN fee_type ft on f.idFeeType = ft.id WHERE cf.id_condominium = :idCondominium GROUP BY ft.id ");
			query.setParameter("idCondominium", id);
			List<Object[]> report = query.getResultList();
			List<Report> reports = new ArrayList<Report>();

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			for (Object[] obj : report) {
				Report r = new Report();
				r.setTitle((String) obj[0]);
				r.setValue((double) obj[1]);
				reports.add(r);
			}
			return reports;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	public Report reportLastCondominiumFeeTotalValue(int id) {
		Session session = null;
		try {
			session = factory.getCurrentSession();

			session.beginTransaction();
			Query query = session.createSQLQuery(
					"SELECT cf.closingDate, sum(f.value) from condominium_fee cf INNER JOIN fee f on f.idCondominiumFee = cf.id WHERE cf.id_condominium = :idCondominium GROUP BY f.idCondominiumFee ORDER BY cf.closingDate DESC LIMIT 1 ");
			query.setParameter("idCondominium", id);
			List<Object[]> reportsObject = query.getResultList();

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Report report = new Report();
			for (Object[] obj : reportsObject) {
				report.setTitle(sdf.format((Date) obj[0]));
				report.setValue((double) obj[1]);
			}

			return report;

		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	public boolean isValidMonth(Date date, int idCondominium) {
		Session session = null;
		try {
			session = factory.getCurrentSession();

			session.beginTransaction();
			Query query = session.createQuery(
					"from condominium_fee f where f.id_condominium = :idCondominium and MONTH(f.closingDate) = MONTH(:date)");
			query.setParameter("idCondominium", idCondominium);
			query.setParameter("date", date);
			List<CondominiumFee> fees = (List<CondominiumFee>) query.getResultList();

			if (fees.size() >= 1) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return false;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	public boolean insert(CondominiumFee fee) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.save(fee);
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

	public boolean save(CondominiumFee fee) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.saveOrUpdate(fee);
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

	public boolean closeCondominiumFee(CondominiumFee condominiumFee) {
		Session session = null;
		try {
			condominiumFee.setFinished(true);
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.saveOrUpdate(condominiumFee);
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
