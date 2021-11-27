package br.edu.utfpr.sgcc.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.edu.utfpr.sgcc.models.Booking;
import br.edu.utfpr.sgcc.models.BookingGuest;
import br.edu.utfpr.sgcc.models.BookingRequest;
import br.edu.utfpr.sgcc.models.CommomArea;
import br.edu.utfpr.sgcc.models.Condominium;
import br.edu.utfpr.sgcc.models.CondominiumResident;
import br.edu.utfpr.sgcc.models.Guest;
import br.edu.utfpr.sgcc.models.MyUserDetails;
import br.edu.utfpr.sgcc.models.Result;
import br.edu.utfpr.sgcc.service.BookingGuestService;
import br.edu.utfpr.sgcc.service.BookingService;
import br.edu.utfpr.sgcc.service.CommomAreaService;
import br.edu.utfpr.sgcc.service.CondominiumResidentService;
import br.edu.utfpr.sgcc.service.CondominiumService;
import br.edu.utfpr.sgcc.service.GuestService;
import br.edu.utfpr.sgcc.service.UserService;

@Controller
public class BookingController {
	@GetMapping("/user/condominium/commomarea/bookings")
	public ModelAndView list(@RequestParam int id_commom_area) {
		ModelAndView modelsAndView = new ModelAndView("user/condominium/commomarea/bookings");

		BookingService bookingService = new BookingService();
		CommomArea commomArea = new CommomAreaService().returnById(id_commom_area);
		modelsAndView.addObject("commomArea", commomArea);
		modelsAndView.addObject("condominium", new CondominiumService().returnById(commomArea.getIdCondominium()));
		modelsAndView.addObject("pending", bookingService.pendingBookingsByArea(id_commom_area));
		modelsAndView.addObject("accepted", bookingService.acceptedBookingsByArea(id_commom_area));

		return modelsAndView;
	}

	@GetMapping("/resident/condominium/booking/list")
	public ModelAndView listBooking(@RequestParam(defaultValue = "0") int idCondominium,
			final RedirectAttributes redirectAttributes) {
		ModelAndView modelsAndView = new ModelAndView("resident/bookings");
		MyUserDetails resident = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		BookingService bookingService = new BookingService();
		if (idCondominium > 0) {
			CondominiumResidentService condominiumResidentService = new CondominiumResidentService();
			CondominiumResident cr = condominiumResidentService.returnByResidentAndCondominium(resident.getId(),
					idCondominium);
			if (cr != null && cr.isActive()) {
				CondominiumService condominiumService = new CondominiumService();
				
				modelsAndView.addObject("condominium", condominiumService.returnById(idCondominium));
				modelsAndView.addObject("bookings", bookingService.bookingsByResidentAndCondominium(resident.getId(), idCondominium));
			} else {
				modelsAndView = new ModelAndView("error/accessdenied");
			}
		} else {
			modelsAndView.addObject("bookings", bookingService.bookingsByResident(resident.getId()));			
		}
		return modelsAndView;

	}

	@GetMapping("/resident/condominium/booking/new")
	public ModelAndView addCondominium(@RequestParam int idCondominium, final RedirectAttributes redirectAttributes) {
		ModelAndView modelsAndView = new ModelAndView("resident/newbooking");
		MyUserDetails resident = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		CondominiumResidentService condominiumResidentService = new CondominiumResidentService();
		CondominiumResident cr = condominiumResidentService.returnByResidentAndCondominium(resident.getId(),
				idCondominium);
		BookingRequest bookingRequest = new BookingRequest();

		if (cr != null && cr.isActive()) {
			modelsAndView.addObject("resident", resident);
			modelsAndView.addObject("bookingRequest", bookingRequest);
			CommomAreaService commomAreaService = new CommomAreaService();
			modelsAndView.addObject("commomareas", commomAreaService.list(idCondominium));
			modelsAndView.addObject("guestForScript", new GuestService().returnGuestsForScript());
		} else {
			modelsAndView = new ModelAndView("error/accessdenied");
		}
		return modelsAndView;

	}

	@PostMapping("/resident/condominium/booking/add")
	public ModelAndView formAddCondominium(@ModelAttribute @Valid BookingRequest bookingRequest, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		ModelAndView modelsAndView = new ModelAndView("redirect:/resident/dashboard");
		MyUserDetails resident = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		CommomAreaService commomAreaService = new CommomAreaService();
		CommomArea commomArea = commomAreaService.returnById(bookingRequest.getIdCommomArea());

		if (commomArea != null) {
			if(bookingRequest.getGuests() != null && !bookingRequest.getGuests().isEmpty()) {
			for (Guest g : bookingRequest.getGuests()) {
				for (Guest g2 : bookingRequest.getGuests()) {
					if (g != g2) {
						if (g.getCpf() == g2.getCpf()) {
							ObjectError oe = new FieldError("bookingRequest",
									"guests[" + bookingRequest.getGuests().indexOf(g) + "].cpf",
									"Os cpfs de " + g.getName() + " e " + g2.getName() + " não devem ser iguais");
							result.addError(oe);
						}
					}
				}
			}
		}

			if (result.hasErrors()) {
				modelsAndView = new ModelAndView("resident/newbooking");
				modelsAndView.addObject("result",
						new Result("Falha ao solicitar reserva, preencha os campos corretamente", "error"));
				modelsAndView.addObject("bookingRequest", bookingRequest);
				modelsAndView.addObject("commomareas", commomAreaService.list(commomArea.getIdCondominium()));
				modelsAndView.addObject("guestForScript", new GuestService().returnGuestsForScript());
				return modelsAndView;
			}

			CondominiumResident cr = new CondominiumResidentService().returnByResidentAndCondominium(resident.getId(),
					commomArea.getIdCondominium());

			if (cr != null && cr.isActive()) {

				bookingRequest.setIdResident(resident.getId());
				BookingService bookingService = new BookingService();
				GuestService guestService = new GuestService();
				BookingGuestService bookingGuestService = new BookingGuestService();
				Booking booking = new Booking();
				booking.setBookingDate(bookingRequest.getBookingDate());
				booking.setIdCommomArea(bookingRequest.getIdCommomArea());
				booking.setIdResident(bookingRequest.getIdResident());

				if (bookingService.insert(booking)) {

					if (bookingRequest.getGuests() != null && !bookingRequest.getGuests().isEmpty()) {

//						add guests
//						verificar se possui duplicatas na lista e remover

						List<Guest> guests = new ArrayList<Guest>();

						for (Guest g : bookingRequest.getGuests()) {
							if (g.getCpf() == null && g.getName() == null && g.getPhone() == null) {
								bookingRequest.getGuests().remove(g);
							} else {
								if (g.getId() > 0) {
									Guest guest = guestService.returnById(g.getId());
									if (guest != null) {
										g.setId(guest.getId());
									} else {
										g.setId(0);
									}
								}
								guests.add(g);
							}
						}

						guestService.insert(bookingRequest.getGuests());

//						add guests_bookings

						List<BookingGuest> bGuests = new ArrayList<>();

						for (Guest g : bookingRequest.getGuests()) {
							BookingGuest bguest = new BookingGuest();
							bguest.setIdBooking(booking.getId());
							bguest.setIdGuest(guestService.returnByCpf(g.getCpf()).getId());
							bGuests.add(bguest);
						}

						bookingGuestService.insert(bGuests);

					}
				} else {
					redirectAttributes.addFlashAttribute("result", new Result("Falha ao solicitar reserva", "error"));
					modelsAndView = new ModelAndView("resident/newbooking");
				}
			} else {
				redirectAttributes.addFlashAttribute("result", new Result("Falha ao solicitar reserva", "error"));
				modelsAndView = new ModelAndView("resident/newbooking");
			}
		} else {
			redirectAttributes.addFlashAttribute("result", new Result("Falha ao solicitar reserva", "error"));
			modelsAndView = new ModelAndView("resident/newbooking");
		}
		
		redirectAttributes.addFlashAttribute("result", new Result("Reserva solicitada com sucesso", "success"));
		return modelsAndView;
	}

	@PostMapping("/user/condominium/commomarea/booking/accept")
	public ModelAndView formBookingAccept(@RequestParam int id, final RedirectAttributes redirectAttributes) {
		ModelAndView modelsAndView = new ModelAndView("redirect:/user/dashboard");
		BookingService bookingService = new BookingService();
		Booking bookingValidation = bookingService.returnById(id);
		CommomArea commomAreaValidation = new CommomAreaService().returnById(bookingValidation.getIdCommomArea());
		Condominium condominiumValidation = new CondominiumService()
				.returnById(commomAreaValidation.getIdCondominium());
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		Result result;
		if (condominiumValidation.getIdUser() == user.getId()) {
			if (bookingService.accept(id)) {
				result = new Result("Reserva aceita com sucesso", "success");
			} else {
				result = new Result("Falha ao aceitar reserva", "error");
			}
		} else {
			result = new Result("Você não possui acesso à esta reserva", "error");
		}
		redirectAttributes.addFlashAttribute("result", result);
		return modelsAndView;
	}

	@PostMapping("/user/condominium/commomarea/booking/refuse")
	public ModelAndView formBookingRefuse(@RequestParam int id, final RedirectAttributes redirectAttributes) {
		ModelAndView modelsAndView = new ModelAndView("redirect:/user/dashboard");
		BookingService bookingService = new BookingService();
		Booking bookingValidation = bookingService.returnById(id);
		CommomArea commomAreaValidation = new CommomAreaService().returnById(bookingValidation.getIdCommomArea());
		Condominium condominiumValidation = new CondominiumService()
				.returnById(commomAreaValidation.getIdCondominium());
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		Result result;
		if (condominiumValidation.getIdUser() == user.getId()) {
			if (bookingService.refuse(id)) {
				result = new Result("Reserva recusada com sucesso", "success");
			} else {
				result = new Result("Falha ao recusar reserva", "error");
			}
		} else {
			result = new Result("Você não possui acesso à esta reserva", "error");
		}
		redirectAttributes.addFlashAttribute("result", result);
		return modelsAndView;
	}

}
