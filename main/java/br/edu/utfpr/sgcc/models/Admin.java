package br.edu.utfpr.sgcc.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.edu.utfpr.sgcc.config.Encryptor;

/**
 *
 * @author norto
 */
@Entity(name = "admin")
@Table(name = "admin")
public class Admin {

	@Id
	private int id;
	@NotNull
	@Size(min = 3, message = "Nome inválido")
	private String firstName;
	@NotNull
	@Size(min = 1, message = "Sobrenome inválido")
	private String lastName;
	private int countryCode;
	@NotNull
	@Email(message="Email inválido")
	private String email;
	@NotNull
	@Size(min = 1, message = "Documento inválido")
	private String document;
	@NotNull
	@Size(min = 8, message = "Senha deve possuir 8 ou mais caracteres")
	private String password;
	private String confirmPassword;
	private Date birthdate;
	private Date createdAt;

	public Admin() {
	}

	public Admin(int id, String firstName, String lastName, int countryCode, String email, String password, Date date,
			Date createdAt) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.countryCode = countryCode;
		this.email = email;
		this.password = password;
		this.birthdate = date;
		this.createdAt = createdAt;
	}

	public Admin(String firstName, String lastName, int countryCode, String email, String password, Date date,
			Date createdAt) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.countryCode = countryCode;
		this.email = email;
		this.password = password;
		this.birthdate = date;
		this.createdAt = createdAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(int countryCode) {
		this.countryCode = countryCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = Encryptor.encrypt(password);
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date date) {
		this.birthdate = date;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", countryCode="
				+ countryCode + ", email=" + email + ", password=" + password + ", date=" + birthdate + ", createdAt="
				+ createdAt + "]";
	}

}
