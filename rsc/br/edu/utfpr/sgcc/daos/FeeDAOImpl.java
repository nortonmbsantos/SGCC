package br.edu.utfpr.sgcc.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import br.edu.utfpr.sgcc.models.Condominium;
import br.edu.utfpr.sgcc.models.Fee;
import br.edu.utfpr.sgcc.service.FeeService;

public class FeeDAOImpl {

	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Fee.class)
			.buildSessionFactory();

	private DataSource dataSource;

	public FeeDAOImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Fee returnById(int id) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Fee Fee = session.get(Fee.class, id);
			return Fee;

		} catch (Exception e) {
			return null;
			// TODO: handle exception
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}

	public List<Fee> returnByCondominiumFeeId(int id) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("from fee f where f.idCondominiumFee = :idCondominiumFee ");
			query.setParameter("idCondominiumFee", id);
			List<Fee> fees = (List<Fee>) query.getResultList();

			return fees;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		}
	}

	public List<Fee> returnUnfinishedInstalments(int id_condominium) {
		Session session = null;
		try {
			session = factory.getCurrentSession();

			session.beginTransaction();
			Query query = session.createSQLQuery(
					"select  id, id_condominium_fee, value, due_date, pay_date, installments, current_installment, description, id_fee_type, father "
							+ "from fee where id in (select MAX(f.id) from fee f inner join condominium_fee cf on cf.id = f.id_condominium_fee where cf.id_condominium = :idCondominium group by f.father) and installments > current_installment");
			query.setParameter("idCondominium", id_condominium);
			List<Object[]> objects = query.getResultList();
			List<Fee> fees = new ArrayList<>();

			for (Object[] o : objects) {
				Fee f = new Fee();
				f.setId((int) o[0]);
				f.setIdCondominiumFee((int) o[1]);
				f.setValue((double) o[2]);
				f.setDueDate((Date) o[3]);
				f.setPayDate((Date) o[4]);
				f.setInstallments((int) o[5]);
				f.setCurrentInstallment((int) o[6]);
				f.setDescription((String) o[7]);
				f.setIdFeeType((int) o[8]);
				f.setFather((int) o[9]);

				fees.add(f);
			}

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

	public List<Fee> returnFathers(int id_condominium) {
		Session session = null;
		try {
			session = factory.getCurrentSession();

			session.beginTransaction();
			Query query = session.createSQLQuery(
					"select  id, id_condominium_fee, value, due_date, pay_date, installments, current_installment, description, id_fee_type, father "
							+ "from fee where id in (select MAX(f.id) from fee f inner join condominium_fee cf on cf.id = f.id_condominium_fee where cf.id_condominium = :idCondominium group by f.father) and installments > current_installment");
			query.setParameter("idCondominium", id_condominium);
			List<Object[]> objects = query.getResultList();
			List<Fee> fees = new ArrayList<>();

			for (Object[] o : objects) {
				Fee f = new Fee();
				f.setId((int) o[0]);
				f.setIdCondominiumFee((int) o[1]);
				f.setValue((double) o[2]);
				f.setDueDate((Date) o[3]);
				f.setPayDate((Date) o[4]);
				f.setInstallments((int) o[5]);
				f.setCurrentInstallment((int) o[6]);
				f.setDescription((String) o[7]);
				f.setIdFeeType((int) o[8]);
				f.setFather((int) o[9]);

				fees.add(f);
			}

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



	public List<Fee> reportByFeeType(int id) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("as type from fee f where f.idCondominium = :idCondominium");
			query.setParameter("idCondominium", id);
			List<Fee> fees = (List<Fee>) query.getResultList();

			return fees;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		}
	}

	public boolean insert(Fee fee) {
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
		}
	}

	public boolean saveOrUpdate(Fee fee) {
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
		}
	}

	public Fee save(Fee fee) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			int idFee = (Integer) session.save(fee);
			session.getTransaction().commit();
			fee.setId(idFee);
			return fee;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}
	

	public boolean updateFather(Fee fee) {
		Session session = null;
		try {
			fee.setFather(fee.getId());
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.update(fee);
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

	public boolean update(Fee fee) {
		Session session = null;
		try {
			Fee feeValidation = returnById(fee.getId());
			feeValidation.setCurrentInstallment(fee.getCurrentInstallment());
			feeValidation.setDescription(fee.getDescription());
			feeValidation.setDueDate(fee.getDueDate());
			feeValidation.setIdFeeType(fee.getIdFeeType());
			feeValidation.setInstallments(fee.getInstallments());
			feeValidation.setPayDate(fee.getPayDate());
			feeValidation.setValue(fee.getValue());
			feeValidation.setFather(fee.getFather());
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.update(feeValidation);
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

	public Fee returnLastByFather(int id_father) {
		Session session = null;
		try {
			session = factory.getCurrentSession();
			session.beginTransaction();
			Query query = session.createQuery("from fee f where f.father = :father order by f.id desc ");
			query.setMaxResults(1);
			query.setParameter("father", id_father);
			Fee fee = (Fee) query.getSingleResult();

			return fee;
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		} finally {
			if (null != session) {
				session.close();
			}
		}
	}
	
	public static void main(String[] args) {
		FeeService service = new FeeService();
		System.out.println(service.returnLastByFather(10).toString());
		
		//		List<Fee> fees = service.returnUnfinishedInstalments(1);
//		for (Fee f : fees) {
//			System.out.println(f.getDescription());
//		}
	}

}
