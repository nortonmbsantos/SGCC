package br.edu.utfpr.sgcc.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;


/**
 *
 * @author norto
 */
@Entity(name = "password_reset")
@Table(name = "password_reset")
public class PasswordReset {

	@Id
	private int id;
	@Column(name = "id_user")
	private int idUser;
	private boolean used;
	@Column(name = "validation_hash")
	private String validationHash;
	@Column(name = "created_at")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	@Column(name = "used_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date usedDate;
	@Transient
	private String password;
	@Transient
	private String confirmPassword;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public String getValidationHash() {
		return validationHash;
	}

	public void setValidationHash(String validationHash) {
		this.validationHash = validationHash;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUsedDate() {
		return usedDate;
	}

	public void setUsedDate(Date usedDate) {
		this.usedDate = usedDate;
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


}
