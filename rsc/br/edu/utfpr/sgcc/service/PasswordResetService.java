package br.edu.utfpr.sgcc.service;

import java.io.Serializable;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.edu.utfpr.sgcc.config.DataBaseConfig;
import br.edu.utfpr.sgcc.daos.AdminDAOImpl;
import br.edu.utfpr.sgcc.daos.PasswordResetDAOImpl;
import br.edu.utfpr.sgcc.models.Admin;
import br.edu.utfpr.sgcc.models.Condominium;
import br.edu.utfpr.sgcc.models.PasswordReset;

public class PasswordResetService {
	PasswordResetDAOImpl dao;
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataBaseConfig.class);
	
	public PasswordResetService() {
//		dao = context.getBean("AdminDAOBean", AdminDAOImpl.class);
		dao = new PasswordResetDAOImpl();
	}
	
	public PasswordReset returnById(int id) {
		return dao.returnById(id);
	}
	
	public PasswordReset returnByIdUser(int idUser) {
		return dao.returnByIdUser(idUser);
	}
	
	public PasswordReset insert(PasswordReset passwordReset) {
		passwordReset = dao.insert(passwordReset);
		return passwordReset; 
	}

	public void updateUsed(PasswordReset passwordReset) {
		dao.updateUsed(passwordReset);
	}
	
	public Serializable insertSerializable(PasswordReset passwordReset) {
		return dao.insertSerializable(passwordReset);
	}

	public PasswordReset returnByValidationHash(String validationHash) {
		return dao.returnByValidationHash(validationHash);
	}
}
