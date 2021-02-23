package br.edu.utfpr.sgcc.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "booking")
@Table(name = "booking")
public class Booking {
	@Id
	private int id;
	private int id_commom_area;
	private int id_resident;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date booking_date;
	private boolean paid_out;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date paid_out_date;
	private boolean status;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date status_date;
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

	public int getId_commom_area() {
		return id_commom_area;
	}

	public void setId_commom_area(int id_commom_area) {
		this.id_commom_area = id_commom_area;
	}

	public int getId_resident() {
		return id_resident;
	}

	public void setId_resident(int id_resident) {
		this.id_resident = id_resident;
	}

	public Date getBooking_date() {
		return booking_date;
	}

	public void setBooking_date(Date booking_date) {
		this.booking_date = booking_date;
	}

	public boolean isPaid_out() {
		return paid_out;
	}

	public void setPaid_out(boolean paid_out) {
		this.paid_out = paid_out;
	}

	public Date getPaid_out_date() {
		return paid_out_date;
	}

	public void setPaid_out_date(Date paid_out_date) {
		this.paid_out_date = paid_out_date;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Date getStatus_date() {
		return status_date;
	}

	public void setStatus_date(Date status_date) {
		this.status_date = status_date;
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
