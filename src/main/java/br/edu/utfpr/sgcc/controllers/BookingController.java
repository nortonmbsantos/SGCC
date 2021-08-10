package br.edu.utfpr.sgcc.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.edu.utfpr.sgcc.models.Booking;
import br.edu.utfpr.sgcc.models.BookingGuest;
import br.edu.utfpr.sgcc.models.BookingRequest;
import br.edu.utfpr.sgcc.models.CommomArea;
import br.edu.utfpr.sgcc.models.Condominium;
import br.edu.utfpr.sgcc.models.Guest;
import br.edu.utfpr.sgcc.models.MyUserDetails;
import br.edu.utfpr.sgcc.models.Result;
import br.edu.utfpr.sgcc.service.BookingGuestService;
import br.edu.utfpr.sgcc.service.BookingService;
import br.edu.utfpr.sgcc.service.CommomAreaService;
import br.edu.utfpr.sgcc.service.CondominiumService;
import br.edu.utfpr.sgcc.service.GuestService;

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
		return modelsAndView;
	}

	@GetMapping("/resident/condominium/booking/new")
	public ModelAndView addCondominium(@RequestParam int idCondominium) {
		ModelAndView modelsAndView = new ModelAndView("resident/newbooking");
		BookingRequest bookingRequest = new BookingRequest();
		MyUserDetails resident = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		modelsAndView.addObject("resident", resident);
		modelsAndView.addObject("bookingRequest", bookingRequest);
		CommomAreaService commomAreaService = new CommomAreaService();
		modelsAndView.addObject("commomareas", commomAreaService.list(idCondominium)); 
		return modelsAndView;
	}
//	@GetMapping("/resident/condominium/booking/new")
//	public ModelAndView addCondominium(@RequestParam int idCondominium) {
//		ModelAndView modelsAndView = new ModelAndView("resident/newbookingOld");
//		Booking booking = new Booking();
//		MyUserDetails resident = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		modelsAndView.addObject("resident", resident);
//		modelsAndView.addObject("booking", booking);
//		CommomAreaService commomAreaService = new CommomAreaService();
//		modelsAndView.addObject("commomareas", commomAreaService.list(idCondominium)); 
//		return modelsAndView;
//	}
//
//	@PostMapping("/resident/condominium/booking/add")
//	public ModelAndView formAddCondominium(@ModelAttribute @Valid Booking booking, BindingResult result,
//			final RedirectAttributes redirectAttributes) {
//		ModelAndView modelsAndView = new ModelAndView("redirect:/resident/dashboard");
//		BookingService service = new BookingService();
//		CommomAreaService commomAreaService = new CommomAreaService();
//		CommomArea commomArea = commomAreaService.returnById(booking.getIdCommomArea());
//		MyUserDetails resident = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		if (resident.hasAuthority("RESIDENT")) {
////		TODO mudar como resident funciona para adaptar ao sistema com Spring Security e o User.
////		if (resident.getIdCondominium() == commomArea.getIdCondominium()) {
////		}
//			if (result.hasErrors()) {
//				return new ModelAndView("resident/newbooking");
//			}
//			if (service.insert(booking)) {
//				redirectAttributes.addFlashAttribute("result", new Result("Reserva realizada com sucesso", "success"));
//			} else {
//				redirectAttributes.addFlashAttribute("result", new Result("Falha ao realizar reserva", "error"));
//			}
//		} else {
//			redirectAttributes.addFlashAttribute("result",
//					new Result("Usuário não tem permissão para esta ação", "error"));
//		}
//
//		return modelsAndView;
//	}

	@PostMapping("/resident/condominium/booking/add")
	public ModelAndView formAddCondominium(@ModelAttribute @Valid BookingRequest bookingRequest,
			final RedirectAttributes redirectAttributes) {
		ModelAndView modelsAndView = new ModelAndView("redirect:/resident/dashboard");
		MyUserDetails resident = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		bookingRequest.setIdResident(resident.getId());
		BookingService bookingService = new BookingService();
		GuestService guestService = new GuestService();
		BookingGuestService bookingGuestService = new BookingGuestService();
		Booking booking = new Booking();
		booking.setBookingDate(bookingRequest.getBookingDate());
		booking.setIdCommomArea(bookingRequest.getIdCommomArea());
		booking.setIdResident(bookingRequest.getIdResident());

		bookingService.insert(booking);

		guestService.insert(bookingRequest.getGuests());

		List<BookingGuest> bGuests = new ArrayList<>();

		for (Guest g : bookingRequest.getGuests()) {
			BookingGuest bguest = new BookingGuest();
			bguest.setIdBooking(booking.getId());
			bguest.setIdGuest(guestService.returnByCpf(g.getCpf()).getId());
			bGuests.add(bguest);
		}

		bookingGuestService.insert(bGuests);

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
