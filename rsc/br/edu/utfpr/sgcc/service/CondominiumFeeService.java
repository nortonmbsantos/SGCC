package br.edu.utfpr.sgcc.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.edu.utfpr.sgcc.config.DataBaseConfig;
import br.edu.utfpr.sgcc.daos.CondominiumDAOImpl;
import br.edu.utfpr.sgcc.daos.CondominiumFeeDAOImpl;
import br.edu.utfpr.sgcc.daos.FeeDAOImpl;
import br.edu.utfpr.sgcc.models.CondominiumFee;
import br.edu.utfpr.sgcc.models.Fee;
import br.edu.utfpr.sgcc.models.Report;

public class CondominiumFeeService {
	CondominiumFeeDAOImpl dao;
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataBaseConfig.class);

	public CondominiumFeeService() {
//		dao = context.getBean("CondominiumFeeDAOBean", CondominiumFeeDAOImpl.class);
		dao = new CondominiumFeeDAOImpl(context.getBean("DAOBean", DataSource.class));
	}

	public CondominiumFee returnById(int id) {
		return dao.returnById(id);
	}

	public boolean closeCondominiumFee(CondominiumFee condominiumFee) {
		return dao.closeCondominiumFee(condominiumFee);
	}

	public CondominiumFee returnLastIdByIdCondominium(int id) {
		return dao.returnById(id);
	}
	
	public List<CondominiumFee> returnByCondominiumId(int id) {
		return dao.returnByCondominiumId(id);
	}

	public List<Report> reportByClosingDate(int id) {
		return dao.reportByClosingDate(id);
	}
	
	public List<Report> reportByFeeType(int id) {
		return dao.reportByFeeType(id);
	}

	public boolean insert(CondominiumFee fee) {
		return dao.insert(fee);
	}

	public boolean save(CondominiumFee fee) {
		return dao.save(fee);
	}

	public Report reportLastCondominiumFeeTotalValue(int id) {
		return dao.reportLastCondominiumFeeTotalValue(id);
	}
	
	public boolean isValidMonth(Date date, int idCondominium) {
		return dao.isValidMonth(date, idCondominium);
	}
}
