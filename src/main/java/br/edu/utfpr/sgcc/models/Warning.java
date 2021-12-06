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
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author norto
 */
@Entity(name = "warning")
@Table(name = "warning")
public class Warning {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "id_resident")
	private int idResident;
	@NotNull
	@Size(min = 3, message = "Descrição inválida")
	private String description;
	private double value;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "warning_date")
	private Date warningDate;
	@Column(name = "id_condominium_fee")
	private int idCondominiumFee;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Date getWarningDate() {
		return warningDate;
	}

	public void setWarningDate(Date warningDate) {
		this.warningDate = warningDate;
	}

	public int getIdCondominiumFee() {
		return idCondominiumFee;
	}

	public void setIdCondominiumFee(int idCondominiumFee) {
		this.idCondominiumFee = idCondominiumFee;
	}

}
