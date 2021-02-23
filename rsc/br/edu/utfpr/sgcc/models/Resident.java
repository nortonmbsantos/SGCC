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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import br.edu.utfpr.sgcc.config.Encryptor;

/**
 *
 * @author norto
 */
@Entity(name = "resident")
@Table(name = "resident")
public class Resident {

	@Id
	private int id;
	@NotNull
	@Size(min = 3, message = "Nome inv�lido")
	private String name;
	private int idCondominium;
	private boolean active;
	private String login;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthDate;
	@NotNull
	@Size(min = 3, message = "Documento inv�lido")
	private String document;
	private String documentType;
	@NotNull
	@Email(message="Email inv�lido")
	@Size(min = 3, message = "Email inv�lido")
	private String email;
	@NotNull
	@Size(min = 8, message = "Telefone inv�lido")
	private String phoneNumber;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date entryDate;
	@NotNull
	@Size(min = 8, message = "Senha inv�lida, deve possuir no m�nimo 8 caracteres")
	private String password;
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = Encryptor.encrypt(password);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
