package br.edu.utfpr.sgcc.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.format.annotation.DateTimeFormat;

import br.edu.utfpr.sgcc.service.BookingGuestService;
import br.edu.utfpr.sgcc.service.BookingService;
import br.edu.utfpr.sgcc.service.GuestService;

public class BookingRequest {
	private int idBooking;
	private int idCommomArea;
	private int idResident;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date bookingDate;
	private boolean status;
	private List<Guest> guests;
	
	public int getIdBooking() {
		return idBooking;
	}

	public void setIdBooking(int idBooking) {
		this.idBooking = idBooking;
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<Guest> getGuests() {
		return guests;
	}

	public void setGuests(List<Guest> guests) {
		this.guests = guests;
	}

//	public static void main(String[] args) {
//		BookingService bookingService = new BookingService();
//		GuestService guestService = new GuestService();
//		BookingGuestService bookingGuestService = new BookingGuestService();
//
//
//		BookingRequest br = new BookingRequest();
//		List<Guest> guests = new ArrayList<Guest>();
//		Guest guest = new Guest();
//		guest.setCpf("09853127695");
//		guest.setName("Norton");
//		guest.setPhone("41991213013");
//		Guest guest2 = new Guest();
//		guest2.setCpf("09853127693");
//		guest2.setName("Paulo");
//		guest2.setPhone("41991212113");
//		guests.add(guest);
//		guests.add(guest2);
//		br.setBookingDate(new Date());
//		br.setIdCommomArea(1);
//		br.setIdResident(10);
//		br.setGuests(guests);
//
//		Booking booking = new Booking();
//		booking.setBookingDate(new Date());
//		booking.setIdCommomArea(br.getIdCommomArea());
//		booking.setIdResident(br.getIdResident());
//
//		bookingService.insert(booking);
//
//		guestService.insert(br.getGuests());
//
//		List<BookingGuest> bGuests = new ArrayList<>();
//
//		for (Guest g : br.getGuests()) {
//			BookingGuest bguest = new BookingGuest();
//			bguest.setIdBooking(booking.getId());
//			bguest.setIdGuest(guestService.returnByCpf(g.getCpf()).getId());
//			bGuests.add(bguest);
//		}
//
//		bookingGuestService.insert(bGuests);
//
//	}
}
