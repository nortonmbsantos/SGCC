package br.edu.utfpr.sgcc.service;

import java.util.List;

import javax.persistence.Query;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.edu.utfpr.sgcc.config.DataBaseConfig;
import br.edu.utfpr.sgcc.daos.CondominiumDAOImpl;
import br.edu.utfpr.sgcc.daos.FeeDAOImpl;
import br.edu.utfpr.sgcc.models.Fee;

public class FeeService {
	FeeDAOImpl dao;
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataBaseConfig.class);

	public FeeService() {
//		dao = context.getBean("FeeDAOBean", FeeDAOImpl.class);
		dao = new FeeDAOImpl(context.getBean("DAOBean", DataSource.class));
	}

	public Fee returnById(int id) {
		return dao.returnById(id);
	}

	public List<Fee> returnByCondominiumFeeId(int id) {
		return dao.returnByCondominiumFeeId(id);
	}

	public List<Fee> returnUnfinishedInstalments(int id_condominium) {
		return dao.returnUnfinishedInstalments(id_condominium);
	}

	public List<Fee> returnFathers(int id_condominium) {
		return dao.returnFathers(id_condominium);
	}

	public boolean insert(Fee fee) {
		return dao.insert(fee);
	}

	public boolean save(Fee fee) {
		return dao.save(fee);
	}

	public boolean update(Fee fee) {
		return dao.update(fee);
	}

}
