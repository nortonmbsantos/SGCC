package br.edu.utfpr.sgcc.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "condominium_resident")
@Table(name = "condominium_resident")
public class CondominiumResident {

	@Id
	private int id;
	private int idResident;
	private int idCondominium;
	private boolean active;

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
}
