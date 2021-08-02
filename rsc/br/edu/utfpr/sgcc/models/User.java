package br.edu.utfpr.sgcc.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.swing.text.MaskFormatter;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity(name = "users")
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	@Size(min = 6, message = "Usuário deve ter no mínimo 6 caracteres")
	@Column(name = "user_name", unique = true)
	private String userName;
	@Size(min = 8, message = "Senha deve ter no mínimo 8 caracteres")
	private String password;
	@Size(min = 1, message = "Nome não deve ser vazio")
	@Column(name = "first_name")
	private String firstName;
	@NotNull(message = "Este campo não pode ser vazio")
	@Email(message = "Email inválido")
	private String email;
	@Transient
	@Size(min = 8, message = "Senha deve ter no mínimo 8 caracteres")
	private String confirmPassword;
	private boolean active;
	@NotNull(message = "Este campo não pode ser vazio")
	private String roles;

	private String street;

	@Column(name = "street_number")
	private String streetNumber;
	private String city;

	private String cep;
	private String state;
	@Column(name = "number_complement")
	private String numberComplement;
	private String neighborhood;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if (password.length() >= 8) {
			this.password = new BCryptPasswordEncoder().encode(password);
		} else {
			this.password = "";
		}
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		if (confirmPassword.length() >= 8) {
			this.confirmPassword = confirmPassword;
		} else {
			this.confirmPassword = "";
		}
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCep() {
		try {
			MaskFormatter mask = new MaskFormatter("##.###-###");
			mask.setValueContainsLiteralCharacters(false);
			return mask.valueToString(cep);			
		}catch (Exception e) {
			e.printStackTrace(System.err);
			return cep;
		}
	}

	public void setCep(String cep) {
		this.cep = cep.trim().replace("-", "").replace(".", "");
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getNumberComplement() {
		return numberComplement;
	}

	public void setNumberComplement(String numberComplement) {
		this.numberComplement = numberComplement;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

}
