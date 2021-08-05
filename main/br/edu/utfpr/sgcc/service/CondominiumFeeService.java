package br.edu.utfpr.sgcc.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.edu.utfpr.sgcc.daos.CondominiumDAOImpl;
import br.edu.utfpr.sgcc.daos.CondominiumFeeDAOImpl;
import br.edu.utfpr.sgcc.daos.FeeDAOImpl;
import br.edu.utfpr.sgcc.models.CondominiumFee;
import br.edu.utfpr.sgcc.models.Fee;
import br.edu.utfpr.sgcc.models.Report;

public class CondominiumFeeService {
	CondominiumFeeDAOImpl dao;

	public CondominiumFeeService() {
//		dao = context.getBean("CondominiumFeeDAOBean", CondominiumFeeDAOImpl.class);
		dao = new CondominiumFeeDAOImpl();
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

	public List<CondominiumFee> returnByCondominiumId(int id, int page, int results) {
		return dao.returnByCondominiumId(id, page, results);
	}

	public List<CondominiumFee> returnByCondominiumId(int id) {
		return returnByCondominiumId(id, 1, 10);
	}

	public List<Report> reportByClosingDate(int id) {
		return dao.reportByClosingDate(id);
	}

	public List<Report> reportByFeeType(int id) {
		return dao.reportByFeeType(id);
	}

	public List<CondominiumFee> returnActives(int idCondominium) {
		return dao.returnActives(idCondominium);
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

	public int returnCount(int id) {
		return dao.returnCount(id);
	}

	public List<Report> reportSumFeeType(int idCondominiumFee) {
		return dao.reportSumByFeeType(idCondominiumFee);
	}

	public List<Report> reportAverageFeeType(int idCondominium) {
		return dao.reportAverageFeeType(idCondominium);
	}

	public List<Report> reportsCondominiumFeeType(int idCondominium, int idCondominiumFee) {
		return dao.reportsCondominiumFeeType(idCondominium, idCondominiumFee);
	}

}
