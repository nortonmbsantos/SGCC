/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.sgcc.models;

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
@Entity(name = "guest")
@Table(name = "guest")
public class Guest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull
	@Size(min = 3, message = "Nome inválido")
	private String name;

	@NotNull
	@Size(min = 3, message = "Cpf inválido")
	private String cpf;

	@NotNull
	@Size(min = 3, message = "Telefone inválido")
	private String phone;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		try {
			MaskFormatter mask = new MaskFormatter("###.###.###-##");
			mask.setValueContainsLiteralCharacters(false);
			return mask.valueToString(cpf);
		} catch (Exception e) {
			return cpf;
		}
	}

	public void setCpf(String cpf) {
		this.cpf = cpf.replace("-", "").replace(".", "").replace(" ", "");
	}

	public String getPhone() {
		try {
			MaskFormatter mask;
			if (phone.length() == 10) {
				mask = new MaskFormatter("(##) ####-####");
			} else {
				mask = new MaskFormatter("(##) #####-####");
			}
			mask.setValueContainsLiteralCharacters(false);
			return mask.valueToString(phone);
		} catch (Exception e) {
			return phone;
		}
	}

	public void setPhone(String phone) {
		this.phone = phone.replace("-", "").replace(".", "").replace(" ", "");
	}

}
