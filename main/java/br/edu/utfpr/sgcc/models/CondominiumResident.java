package br.edu.utfpr.sgcc.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity(name = "condominium_resident")
@Table(name = "condominium_resident")
public class CondominiumResident {

	@Id
	private int id;
	@Column(name = "id_resident")
	private int idResident;
	@Column(name = "id_condominium")
	private int idCondominium;
	private boolean active;
	@Transient
	private User resident;
	

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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public User getResident() {
		return resident;
	}

	public void setResident(User resident) {
		this.resident = resident;
	}
	
	
}
