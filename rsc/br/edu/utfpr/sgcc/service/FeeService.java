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
		dao = new FeeDAOImpl();
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

	public Fee returnLastByFather(int id_father) {
		return dao.returnLastByFather(id_father);
	}

	public boolean insert(Fee fee) {
		return dao.insert(fee);
	}

	public boolean saveOrUpdate(Fee fee) {
		return dao.saveOrUpdate(fee);
	}

	public boolean save(Fee fee) {
		fee = dao.save(fee);
		if(fee != null) {
			if(fee.getFather() == 0) {
				this.updateFather(fee);
			}
			
			return true;
		} else {
			return false;
		}
		
	}

	public boolean update(Fee fee) {
		return dao.update(fee);
	}

	public boolean updateFather(Fee fee) {
		return dao.updateFather(fee);
	}

}
