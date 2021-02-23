package br.edu.utfpr.sgcc.controllers;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.MaskFormatter;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import br.edu.utfpr.sgcc.config.DataBaseConfig;
import br.edu.utfpr.sgcc.config.Encryptor;
import br.edu.utfpr.sgcc.daos.AdminDAOImpl;
import br.edu.utfpr.sgcc.models.Admin;
import br.edu.utfpr.sgcc.models.Condominium;
import br.edu.utfpr.sgcc.models.CondominiumEntryRequest;
import br.edu.utfpr.sgcc.models.CondominiumFee;
import br.edu.utfpr.sgcc.models.Fee;
import br.edu.utfpr.sgcc.models.MyUserDetails;
import br.edu.utfpr.sgcc.models.Resident;
import br.edu.utfpr.sgcc.models.Result;
import br.edu.utfpr.sgcc.models.User;
import br.edu.utfpr.sgcc.service.AdminService;
import br.edu.utfpr.sgcc.service.BookingService;
import br.edu.utfpr.sgcc.service.CondominiumEntryRequestService;
import br.edu.utfpr.sgcc.service.CondominiumFeeService;
import br.edu.utfpr.sgcc.service.CondominiumResidentService;
import br.edu.utfpr.sgcc.service.CondominiumService;
import br.edu.utfpr.sgcc.service.FeeService;
import br.edu.utfpr.sgcc.service.FeeTypeService;
import br.edu.utfpr.sgcc.service.ResidentService;
import br.edu.utfpr.sgcc.service.UserService;
import br.edu.utfpr.sgcc.service.WarningService;

@Controller
public class ResidentLoginController {

	@GetMapping("/resident/login")
	public ModelAndView loginAdmin() {
		ModelAndView modelAndView = new ModelAndView("resident/login");
		Resident resident = new Resident();
		modelAndView.addObject("resident", resident);
		return modelAndView;
	}

	@GetMapping("/resident/dashboard")
	public ModelAndView getDashboard() {
		ModelAndView modelAndView = new ModelAndView("resident/dashboard");
		MyUserDetails resident = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		modelAndView.addObject("resident", resident);
		
		CondominiumResidentService condominiumResidentService = new CondominiumResidentService();
		modelAndView.addObject("condominiuns", condominiumResidentService.returnCondominiunsByResident(resident.getId()));
		
		BookingService bookingService = new BookingService();
		modelAndView.addObject("bookings", bookingService.pendingBookingsByResident(resident.getId()));
		
		
		WarningService warningService = new WarningService();
		modelAndView.addObject("warnings", warningService.returnByResident(resident.getId()));

		return modelAndView;
	}

	@PostMapping("/resident/login")
	public ModelAndView loginAdminForm(@ModelAttribute Resident resident) {
		ResidentService service = new ResidentService();
		Resident residentLogin = service.login(resident);
		ModelAndView modelAndView;
		if (null != residentLogin) {
			modelAndView = new ModelAndView("redirect:/resident/dashboard");

			modelAndView.addObject("resident", residentLogin);
		} else {
			modelAndView = new ModelAndView("resident/login");
		}
		return modelAndView;
	}

	@GetMapping("/resident/condominium/entry/new")
	public ModelAndView addCondominiumResident() {
		ModelAndView modelAndView = new ModelAndView("resident/entryCondominium");
		return modelAndView;
	}

	@PostMapping("/resident/condominium/entry/add")
	public ModelAndView addCondominiumResidentForm(@RequestParam String code) {
		CondominiumService service = new CondominiumService();
		Condominium condominium = service.returnByCode(code);
		if (condominium != null) {
			MyUserDetails resident = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();

			CondominiumEntryRequest cer = new CondominiumEntryRequest();
			cer.setIdCondominium(condominium.getId());
			cer.setIdResident(resident.getId());
			cer.setRequestDate(new Date());
			CondominiumEntryRequestService requestService = new CondominiumEntryRequestService();
			CondominiumEntryRequest cerVal = requestService.returnByCondominiumAndResident(cer.getIdCondominium(), cer.getIdResident());
			if (cerVal.isAccepted() || cerVal.getResponseDate() == null) {
				if (requestService.insert(cer)) {
					return new ModelAndView("resident/dashboard").addObject("result", new Result(
							"Solicitação para " + condominium.getName() + " enviada com sucesso", "success"));
				} else {
					return new ModelAndView("resident/entryCondominium").addObject("result",
							new Result("Falha ao enviar solicitação ao condomínio", "error"));
				}
			} else {
				return new ModelAndView("resident/dashboard").addObject("result", new Result(
						"Solicitação para " + condominium.getName() + " enviada com sucesso", "success"));
			}
		} else {
			return new ModelAndView("resident/entryCondominium").addObject("result",
					new Result("Condomínio não encontrado", "error"));
		}
	}
}
