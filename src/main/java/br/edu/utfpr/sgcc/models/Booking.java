package br.edu.utfpr.sgcc.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "booking")
@Table(name = "booking")
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "id_commom_area")
	private int idCommomArea;
	@Column(name = "id_resident")
	private int idResident;
	@Column(name = "booking_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date bookingDate;
	@Column(name = "paid_out")
	private boolean paidOut;
	@Column(name = "paid_out_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date paidOutDate;
	private boolean status;
	@Column(name = "status_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date statusDate;
	@Transient
	private String resident_name;
	@Transient
	private String commomarea_name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdCommomArea() {
		return idCommomArea;
	}

	public void setIdCommomArea(int idCommomArea) {
		this.idCommomArea = idCommomArea;
	}

	public int getIdResident() {
		return idResident;
	}

	public void setIdResident(int idResident) {
		this.idResident = idResident;
	}

	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public boolean isPaidOut() {
		return paidOut;
	}

	public void setPaidOut(boolean paidOut) {
		this.paidOut = paidOut;
	}

	public Date getPaidOutDate() {
		return paidOutDate;
	}

	public void setPaidOutDate(Date paidOutDate) {
		this.paidOutDate = paidOutDate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Date getStatusDate() {
		return statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	public String getResident_name() {
		return resident_name;
	}

	public void setResident_name(String resident_name) {
		this.resident_name = resident_name;
	}

	public String getCommomarea_name() {
		return commomarea_name;
	}

	public void setCommomarea_name(String commomarea_name) {
		this.commomarea_name = commomarea_name;
	}

}
