package br.edu.utfpr.sgcc.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "condominium_entry_request")
@Table(name = "condominium_entry_request")
public class CondominiumEntryRequest {

	@Id
	private int id;
	private int idResident;
	private int idCondominium;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date requestDate;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date responseDate;
	private boolean accepted;
	@Transient
	private User user;
	@Transient
	private Condominium condominium;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdResident() {
		return idResident;
	}

	public void setIdResident(int idResident) {
		this.idResident = idResident;
	}

	public int getIdCondominium() {
		return idCondominium;
	}

	public void setIdCondominium(int idCondominium) {
		this.idCondominium = idCondominium;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public Date getResponseDate() {
		return responseDate;
	}

	public void setResponseDate(Date responseDate) {
		this.responseDate = responseDate;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Condominium getCondominium() {
		return condominium;
	}

	public void setCondominium(Condominium condominium) {
		this.condominium = condominium;
	}

}
