/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.sgcc.models;

import java.util.Calendar;
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
@Entity(name = "fee")
@Table(name = "fee")
public class Fee {
	@Id
	private int id;
	private int idCondominiumFee;
	@NotNull(message = "Valor inválido")
	private double value;
	@NotNull(message = "Data inválida")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dueDate;
	@NotNull(message = "Data inválida")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date payDate;
	@NotNull(message = "Parcela inválida")
	private int installments;
	private int currentInstallment;
	private int father;
	private boolean monthly;
	@NotNull
	@Size(min = 3, message = "Descrição inválida")
	private String description;
	private int idFeeType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdCondominiumFee() {
		return idCondominiumFee;
	}

	public void setIdCondominiumFee(int idCondominiumFee) {
		this.idCondominiumFee = idCondominiumFee;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public int getInstallments() {
		return installments;
	}

	public void setInstallments(int installments) {
		this.installments = installments;
	}

	public int getCurrentInstallment() {
		return currentInstallment;
	}

	public void setCurrentInstallment(int currentInstallment) {
		this.currentInstallment = currentInstallment;
	}

	public boolean isMonthly() {
		return monthly;
	}

	public void setMonthly(boolean monthly) {
		this.monthly = monthly;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIdFeeType() {
		return idFeeType;
	}

	public void setIdFeeType(int idFeeType) {
		this.idFeeType = idFeeType;
	}

	public int getFather() {
		return father;
	}

	public void setFather(int father) {
		this.father = father;
	}
	

}
