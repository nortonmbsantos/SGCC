package br.edu.utfpr.sgcc.models;

import javax.validation.constraints.Size;

public class UserUpdatePassword {

	private int id;
	private String login;
	private String password;
	private String confirmPassword;
	@Size(min = 8, message = "Senha deve ter no mínimo 8 caracteres")
	private String newPassword;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
