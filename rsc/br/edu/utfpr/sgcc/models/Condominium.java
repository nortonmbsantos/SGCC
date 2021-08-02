package br.edu.utfpr.sgcc.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.swing.text.MaskFormatter;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author norto
 */
@Entity(name = "condominium")
@Table(name = "condominium")
public class Condominium {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "id_user")
	private int idUser;
	@NotNull
	@Size(min = 3, message = "Nome inválido")
	private String name;
	@NotNull
	@Size(min = 3, message = "Descrição inválida")
	private String description;
	private String code;
	private boolean residential;
	
	private String street;
	
	@Size(min = 3, message = "Número inválido")
	@Column(name = "street_number")
	private String streetNumber;
	private String city;
	
	@Size(min = 3, message = "CEP inválido")
	private String cep;
	private String state;
	@Column(name = "number_complement")
	private String numberComplement;
	private String neighborhood;

	public Condominium() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isResidential() {
		return residential;
	}

	public void setResidential(boolean residential) {
		this.residential = residential;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
