/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.sgcc.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author norto
 */
@Entity(name = "condominium_fee")
@Table(name = "condominium_fee")
public class CondominiumFee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "id_condominium")
	private int idCondominium;
	@Column(name = "closing_date")
	@NotNull(message = "Data inválida")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date closingDate;
	private boolean finished;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdCondominium() {
		return idCondominium;
	}

	public void setIdCondominium(int idCondominium) {
		this.idCondominium = idCondominium;
	}

	public Date getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

}
