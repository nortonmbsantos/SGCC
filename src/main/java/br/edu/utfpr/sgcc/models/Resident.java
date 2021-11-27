/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.sgcc.models;

import java.util.Date;

import javax.persistence.Column;
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
	@Size(min = 3, message = "Nome inválido")
	private String name;
	@Column(name = "id_condominium")
	private int idCondominium;
	private boolean active;
	private String login;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "birth_date")
	private Date birthDate;
	@NotNull
	@Size(min = 3, message = "Documento inválido")
	private String document;
	@Column(name = "document_type")
	private String documentType;
	@NotNull
	@Email(message="Email inválido")
	@Size(min = 3, message = "Email inválido")
	private String email;
	@NotNull
	@Size(min = 8, message = "Telefone inválido")
	@Column(name = "phone_number")
	private String phoneNumber;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "entry_date")
	private Date entryDate;
	@NotNull
	@Size(min = 8, message = "Senha inválida, deve possuir no mínimo 8 caracteres")
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
