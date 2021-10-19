package br.edu.utfpr.sgcc.service;

import java.util.List;

import javax.persistence.Query;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import br.edu.utfpr.sgcc.daos.CondominiumDAOImpl;
import br.edu.utfpr.sgcc.daos.FeeDAOImpl;
import br.edu.utfpr.sgcc.models.Fee;

public class FeeService {
	FeeDAOImpl daos;
	

	public FeeService() {
//		daos = context.getBean("FeeDAOBean", FeeDAOImpl.class);
		daos = new FeeDAOImpl();
	}

	public Fee returnById(int id) {
		return daos.returnById(id);
	}

	public List<Fee> returnByCondominiumFeeId(int id) {
		return daos.returnByCondominiumFeeId(id);
	}

	public List<Fee> returnUnfinishedInstalments(int id_condominium) {
		return daos.returnUnfinishedInstalments(id_condominium);
	}

	public List<Fee> returnFathers(int id_condominium) {
		return daos.returnFathers(id_condominium);
	}

	public Fee returnLastByFather(int id_father) {
		return daos.returnLastByFather(id_father);
	}

	public boolean insert(Fee fee) {
		return daos.insert(fee);
	}

	public boolean saveOrUpdate(Fee fee) {
		return daos.saveOrUpdate(fee);
	}

	public boolean save(Fee fee) {
		fee = daos.save(fee);
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
		return daos.update(fee);
	}

	public boolean updateFather(Fee fee) {
		return daos.updateFather(fee);
	}

	public List<Fee> dahsboardFeesDueDate(int idCondominium) {
		return daos.dahsboardFeesDueDate(idCondominium);
	}

}
