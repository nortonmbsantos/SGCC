package br.edu.utfpr.sgcc.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity(name = "users")
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	@Size(min = 6, message = "Usuário deve ter no mínimo 6 caracteres")
	private String userName;
	@Size(min = 8, message = "Senha deve ter no mínimo 8 caracteres")
	private String password;
	@Size(min = 1, message = "Nome não deve ser vazio")
	private String firstName;
	@Transient
	@Size(min = 8, message = "Senha deve ter no mínimo 8 caracteres")
	private String confirmPassword;
	private boolean active;
	@NotNull(message = "Este campo não pode ser vazio")
	private String roles;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if (password.length() >= 8) {
			this.password = new BCryptPasswordEncoder().encode(password);
		} else {
			this.password = "";
		}
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		if (confirmPassword.length() >= 8) {
			this.confirmPassword = confirmPassword;
		} else {
			this.confirmPassword = "";
		}
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

}
