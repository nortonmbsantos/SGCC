package br.edu.utfpr.sgcc.controllers;

import java.text.SimpleDateFormat;

import javax.validation.Valid;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.utfpr.sgcc.models.Admin;
import br.edu.utfpr.sgcc.models.Booking;
import br.edu.utfpr.sgcc.models.CommomArea;
import br.edu.utfpr.sgcc.models.Condominium;
import br.edu.utfpr.sgcc.models.CondominiumFee;
import br.edu.utfpr.sgcc.models.Fee;
import br.edu.utfpr.sgcc.models.MyUserDetails;
import br.edu.utfpr.sgcc.models.Resident;
import br.edu.utfpr.sgcc.models.Result;
import br.edu.utfpr.sgcc.service.BookingService;
import br.edu.utfpr.sgcc.service.CommomAreaService;
import br.edu.utfpr.sgcc.service.CondominiumFeeService;
import br.edu.utfpr.sgcc.service.CondominiumService;
import br.edu.utfpr.sgcc.service.FeeService;
import br.edu.utfpr.sgcc.service.FeeTypeService;

@Controller
public class BookingController {
	@GetMapping("/user/condominium/commomarea/bookings")
	public ModelAndView list(@RequestParam int id_commom_area) {
		ModelAndView modelAndView = new ModelAndView("user/condominium/commomarea/bookings");

		BookingService bookingService = new BookingService();
		CommomArea commomArea = new CommomAreaService().returnById(id_commom_area);
		modelAndView.addObject("commomArea", commomArea);
		modelAndView.addObject("condominium", new CondominiumService().returnById(commomArea.getIdCondominium()));
		modelAndView.addObject("pending", bookingService.pendingBookingsByArea(id_commom_area));
		return modelAndView;
	}

	@GetMapping("/resident/condominium/booking/new")
	public ModelAndView addCondominium(@RequestParam int idCondominium) {
		ModelAndView modelAndView = new ModelAndView("resident/newbooking");
		Booking booking = new Booking();
		MyUserDetails resident = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		modelAndView.addObject("resident", resident);
		modelAndView.addObject("booking", booking);
		CommomAreaService commomAreaService = new CommomAreaService();
		modelAndView.addObject("commomareas", commomAreaService.list(idCondominium)); // TODO não deve ser hardcoded o
																						// numero 1
		return modelAndView;
	}

	@PostMapping("/resident/condominium/booking/add")
	public ModelAndView formAddCondominium(@ModelAttribute @Valid Booking booking, BindingResult result, final RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("redirect:/resident/dashboard");
		BookingService service = new BookingService();
		CommomAreaService commomAreaService = new CommomAreaService();
		CommomArea commomArea = commomAreaService.returnById(booking.getId_commom_area());
		MyUserDetails resident = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (resident.hasAuthority("RESIDENT")) {
//		TODO mudar como resident funciona para adaptar ao sistema com Spring Security e o User.
//		if (resident.getIdCondominium() == commomArea.getIdCondominium()) {
//		}
			if (result.hasErrors()) {
				return new ModelAndView("resident/newbooking");
			}
			if (service.insert(booking)) {
				redirectAttributes.addFlashAttribute("result", new Result("Reserva realizada com sucesso", "success"));
			} else {
				redirectAttributes.addFlashAttribute("result", new Result("Falha ao realizar reserva", "error"));
			}
		} else {
			redirectAttributes.addFlashAttribute("result", new Result("Usuário não tem permissão para esta ação", "error"));
		}

		return modelAndView;
	}

	@PostMapping("/user/condominium/commomarea/booking/accept")
	public ModelAndView formBookingAccept(@RequestParam int id, final RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("redirect:/user/dashboard");
		BookingService bookingService = new BookingService();
		Booking bookingValidation = bookingService.returnById(id);
		CommomArea commomAreaValidation = new CommomAreaService().returnById(bookingValidation.getId_commom_area());
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
		return modelAndView;
	}

	@PostMapping("/user/condominium/commomarea/booking/refuse")
	public ModelAndView formBookingRefuse(@RequestParam int id, final RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("redirect:/user/dashboard");
		BookingService bookingService = new BookingService();
		Booking bookingValidation = bookingService.returnById(id);
		CommomArea commomAreaValidation = new CommomAreaService().returnById(bookingValidation.getId_commom_area());
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
		return modelAndView;
	}

}
