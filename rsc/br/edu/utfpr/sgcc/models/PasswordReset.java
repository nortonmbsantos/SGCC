package br.edu.utfpr.sgcc.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;

import br.edu.utfpr.sgcc.config.Encryptor;

/**
 *
 * @author norto
 */
@Entity(name = "passwordreset")
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
	private Date createdAt;
	@Column(name = "used_date")
	private Date usedDate;

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


}
