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
	CondominiumFeeDAOImpl daos;

	public CondominiumFeeService() {
//		daos = context.getBean("CondominiumFeeDAOBean", CondominiumFeeDAOImpl.class);
		daos = new CondominiumFeeDAOImpl();
	}

	public CondominiumFee returnById(int id) {
		return daos.returnById(id);
	}

	public boolean closeCondominiumFee(CondominiumFee condominiumFee) {
		return daos.closeCondominiumFee(condominiumFee);
	}

	public CondominiumFee returnLastIdByIdCondominium(int id) {
		return daos.returnById(id);
	}

	public List<CondominiumFee> returnByCondominiumId(int id, int page, int results) {
		return daos.returnByCondominiumId(id, page, results);
	}

	public List<CondominiumFee> returnByCondominiumId(int id) {
		return returnByCondominiumId(id, 1, 10);
	}

	public List<Report> reportByClosingDate(int id) {
		return daos.reportByClosingDate(id);
	}

	public List<Report> reportByFeeType(int id) {
		return daos.reportByFeeType(id);
	}

	public List<CondominiumFee> returnActives(int idCondominium) {
		return daos.returnActives(idCondominium);
	}

	public boolean insert(CondominiumFee fee) {
		return daos.insert(fee);
	}

	public boolean save(CondominiumFee fee) {
		return daos.save(fee);
	}

	public Report reportLastCondominiumFeeTotalValue(int id) {
		return daos.reportLastCondominiumFeeTotalValue(id);
	}

	public boolean isValidMonth(Date date, int idCondominium) {
		return daos.isValidMonth(date, idCondominium);
	}

	public int returnCount(int id) {
		return daos.returnCount(id);
	}

	public List<Report> reportSumFeeType(int idCondominiumFee) {
		return daos.reportSumByFeeType(idCondominiumFee);
	}

	public List<Report> reportAverageFeeType(int idCondominium) {
		return daos.reportAverageFeeType(idCondominium);
	}

	public List<Report> reportsCondominiumFeeType(int idCondominium, int idCondominiumFee) {
		return daos.reportsCondominiumFeeType(idCondominium, idCondominiumFee);
	}

}
