/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.sgcc.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author norto
 */
@Entity(name = "condominium_fee")
@Table(name = "condominium_fee")
public class CondominiumFee {
	@Id
	private int id;
	private int id_condominium;
	@NotNull(message="Data inválida")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date closingDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_condominium() {
		return id_condominium;
	}

	public void setId_condominium(int id_condominium) {
		this.id_condominium = id_condominium;
	}

	public Date getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}

}
