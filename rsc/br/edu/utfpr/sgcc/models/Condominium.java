package br.edu.utfpr.sgcc.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
	@GeneratedValue (strategy = GenerationType.IDENTITY)
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

}
