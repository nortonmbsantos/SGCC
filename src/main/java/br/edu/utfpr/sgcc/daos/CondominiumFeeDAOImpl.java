package br.edu.utfpr.sgcc.daos;

import java.math.BigInteger;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.edu.utfpr.sgcc.config.DBConfig;
import br.edu.utfpr.sgcc.models.CondominiumFee;
import br.edu.utfpr.sgcc.models.Report;
import br.edu.utfpr.sgcc.service.CondominiumFeeService;

public class CondominiumFeeDAOImpl {

	SessionFactory factory = DBConfig.getSessionFactory();

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
				"from condominium_fee f where f.idCondominium = :idCondominium order by f.id desc limit 1");
		query.setParameter("idCondominium", id_condominium);

		return (CondominiumFee) query.getSingleResult();
	}

	public List<CondominiumFee> returnByCondominiumId(int id, int page, int results) {
		Session session = null;
		try {
			session = factory.getCurrentSession();

			session.beginTransaction();
			Query query = session.createQuery(
					"from condominium_fee f where f.idCondominium = :idCondominium order by f.finished, f.closingDate desc");
			query.setParameter("idCondominium", id);
			query.setFirstResult((page - 1) * results);
			query.setMaxResults(results);
			@SuppressWarnings("unchecked")
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

	public List<CondominiumFee> returnActives(int idCondominium) {
		Session session = null;
		try {
			session = factory.getCurrentSession();

			session.beginTransaction();
			Query query = session.createQuery(
					"from condominium_fee f where f.idCondominium = :idCondominium and f.finished = 0 order by f.closingDate desc");
			query.setParameter("idCondominium", idCondominium);
			@SuppressWarnings("unchecked")
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

			int count = Integer.valueOf(String.valueOf(query.getSingleResult()));
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
			Query query = session.createSQLQuery("SELECT cf.closing_date, SUM(f.value) as value FROM fee f "
					+ "inner join condominium_fee cf on cf.id = f.id_condominium_fee where cf.id_condominium = :idCondominium group by id_condominium_fee order by cf.closing_date desc ");
			query.setParameter("idCondominium", id);
			@SuppressWarnings("unchecked")
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

	public List<Report> reportAverageFeeType(int idCondominium) {
		Session session = null;
		try {
			session = factory.getCurrentSession();

			session.beginTransaction();
			Query query = session.createSQLQuery(
					"SELECT AVG(f.value) as average, ft.description, ft.id, count(ft.id) FROM fee f INNER JOIN fee_type ft ON ft.id = f.id_fee_type INNER JOIN condominium_fee cf ON cf.id = f.id_condominium_fee WHERE cf.id_condominium = :idCondominium GROUP BY f.id_fee_type ORDER BY average DESC ");
			query.setParameter("idCondominium", idCondominium);
			@SuppressWarnings("unchecked")
			List<Object[]> report = query.getResultList();
			List<Report> reports = new ArrayList<Report>();

			for (Object[] obj : report) {
				Report r = new Report();
				r.setValue((double) obj[0]);
				r.setTitle((String) obj[1]);
				r.setId((int) obj[2]);
				r.setQuantity(((BigInteger) obj[3]).intValue());
				reports.add(r);
			}
			return reports;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return new ArrayList<Report>();
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	public List<Report> reportSumByFeeType(int idCondominiumFee) {
		Session session = null;
		try {
			session = factory.getCurrentSession();

			session.beginTransaction();
			Query query = session.createSQLQuery(
					"SELECT SUM(f.value), ft.description, ft.id FROM fee f INNER JOIN fee_type ft ON ft.id = f.id_fee_type WHERE f.id_condominium_fee = :idCondominiumFee GROUP BY f.id_fee_type ");
			query.setParameter("idCondominiumFee", idCondominiumFee);
			@SuppressWarnings("unchecked")
			List<Object[]> report = query.getResultList();
			List<Report> reports = new ArrayList<Report>();

			for (Object[] obj : report) {
				Report r = new Report();
				r.setValue((double) obj[0]);
				r.setTitle((String) obj[1]);
				r.setId((int) obj[2]);
				reports.add(r);
			}
			return reports;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return new ArrayList<Report>();
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	public List<Report> reportsCondominiumFeeType(int idCondominium, int idCondominiumFee) {
		List<Report> reportSumByFeeType = this.reportSumByFeeType(idCondominiumFee);
		List<Report> reportAverageFeeType = this.reportAverageFeeType(idCondominium);
		
		List<Report> reports = new ArrayList<Report>();
		for (Report r : reportSumByFeeType) {
			for (Report r2 : reportAverageFeeType) {
				if (r.getId() == r2.getId()) {
					Report report = new Report();
					report.setValue(r.getValue());
					report.setTitle(r.getTitle());
					int val = Math.round(((float) r.getValue() / (float) r2.getValue()) * 100);

					if (val >= 120) {
						report.setDescription("Valor maior do que a média");
					} else if (val >= 80) {
						report.setDescription("Valor dentro da média");
					} else if (val < 80) {
						report.setDescription("Valor abaixo da média");
					}
					reports.add(report);
				}
			}
		}

		return reports;
	}

	public List<Report> reportByFeeType(int id) {
		Session session = null;
		try {
			session = factory.getCurrentSession();

			session.beginTransaction();
			Query query = session.createSQLQuery(
					"SELECT ft.description, sum(f.value) from condominium_fee cf INNER JOIN fee f on f.id_condominium_fee = cf.id INNER JOIN fee_type ft on f.id_fee_type = ft.id WHERE cf.id_condominium = :idCondominium GROUP BY ft.id ");
			query.setParameter("idCondominium", id);
			@SuppressWarnings("unchecked")
			List<Object[]> report = query.getResultList();
			List<Report> reports = new ArrayList<Report>();

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
					"SELECT cf.closing_date, sum(f.value) from condominium_fee cf INNER JOIN fee f on f.id_condominium_fee = cf.id WHERE cf.id_condominium = :idCondominium GROUP BY f.id_condominium_fee ORDER BY cf.closing_date DESC LIMIT 1 ");
			query.setParameter("idCondominium", id);
			@SuppressWarnings("unchecked")
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
					"from condominium_fee f where f.idCondominium = :idCondominium and ((MONTH(f.closingDate) = MONTH(:date)) and (YEAR(f.closingDate) = YEAR(:date)))");
			query.setParameter("idCondominium", idCondominium);
			query.setParameter("date", date);
			@SuppressWarnings("unchecked")
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
