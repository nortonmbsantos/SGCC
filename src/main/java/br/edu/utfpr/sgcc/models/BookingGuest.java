/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.sgcc.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author norto
 */
@Entity(name = "booking_guest")
@Table(name = "booking_guest")
public class BookingGuest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	@Column(name = "id_guest")
	private int idGuest;
	@NotNull
	@Column(name = "id_booking")
	private int idBooking;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdGuest() {
		return idGuest;
	}

	public void setIdGuest(int idGuest) {
		this.idGuest = idGuest;
	}

	public int getIdBooking() {
		return idBooking;
	}

	public void setIdBooking(int idBooking) {
		this.idBooking = idBooking;
	}

}
