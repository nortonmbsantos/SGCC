package br.edu.utfpr.sgcc.service;

import java.io.Serializable;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import br.edu.utfpr.sgcc.daos.PasswordResetDAOImpl;
import br.edu.utfpr.sgcc.models.Condominium;
import br.edu.utfpr.sgcc.models.PasswordReset;

public class PasswordResetService {
	PasswordResetDAOImpl daos;
	
	
	public PasswordResetService() {
//		daos = context.getBean("AdminDAOBean", AdminDAOImpl.class);
		daos = new PasswordResetDAOImpl();
	}
	
	public PasswordReset returnById(int id) {
		return daos.returnById(id);
	}
	
	public PasswordReset returnByIdUser(int idUser) {
		return daos.returnByIdUser(idUser);
	}
	
	public PasswordReset insert(PasswordReset passwordReset) {
		passwordReset = daos.insert(passwordReset);
		return passwordReset; 
	}

	public void updateUsed(PasswordReset passwordReset) {
		daos.updateUsed(passwordReset);
	}
	
	public Serializable insertSerializable(PasswordReset passwordReset) {
		return daos.insertSerializable(passwordReset);
	}

	public PasswordReset returnByValidationHash(String validationHash) {
		return daos.returnByValidationHash(validationHash);
	}
}
