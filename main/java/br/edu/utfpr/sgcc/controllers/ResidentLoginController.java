package br.edu.utfpr.sgcc.controllers;

import java.util.Date;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import br.edu.utfpr.sgcc.models.Condominium;
import br.edu.utfpr.sgcc.models.CondominiumEntryRequest;
import br.edu.utfpr.sgcc.models.MyUserDetails;
import br.edu.utfpr.sgcc.models.Resident;
import br.edu.utfpr.sgcc.models.Result;
import br.edu.utfpr.sgcc.service.BookingService;
import br.edu.utfpr.sgcc.service.CondominiumEntryRequestService;
import br.edu.utfpr.sgcc.service.CondominiumResidentService;
import br.edu.utfpr.sgcc.service.CondominiumService;
import br.edu.utfpr.sgcc.service.ResidentService;
import br.edu.utfpr.sgcc.service.WarningService;

@Controller
@SessionAttributes({ "resident" })
public class ResidentLoginController {

	@GetMapping("/resident/login")
	public ModelAndView loginAdmin() {
		ModelAndView modelsAndView = new ModelAndView("resident/login");
		Resident resident = new Resident();
		modelsAndView.addObject("resident", resident);
		return modelsAndView;
	}

	@GetMapping("/resident/dashboard")
	public ModelAndView getDashboard() {
		ModelAndView modelsAndView = new ModelAndView("resident/dashboard");
		MyUserDetails resident = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		modelsAndView.addObject("resident", resident);
		
		CondominiumResidentService condominiumResidentService = new CondominiumResidentService();
		modelsAndView.addObject("condominiuns", condominiumResidentService.returnCondominiunsByResident(resident.getId()));
		
		BookingService bookingService = new BookingService();
		modelsAndView.addObject("bookings", bookingService.pendingBookingsByResident(resident.getId()));
		
		
		WarningService warningService = new WarningService();
		modelsAndView.addObject("warnings", warningService.returnByResident(resident.getId()));

		return modelsAndView;
	}

	@PostMapping("/resident/login")
	public ModelAndView loginAdminForm(@ModelAttribute Resident resident) {
		ResidentService service = new ResidentService();
		Resident residentLogin = service.login(resident);
		ModelAndView modelsAndView;
		if (null != residentLogin) {
			modelsAndView = new ModelAndView("redirect:/resident/dashboard");

			modelsAndView.addObject("resident", residentLogin);
		} else {
			modelsAndView = new ModelAndView("resident/login");
		}
		return modelsAndView;
	}

	@GetMapping("/resident/condominium/entry/new")
	public ModelAndView addCondominiumResident() {
		ModelAndView modelsAndView = new ModelAndView("resident/entryCondominium");
		return modelsAndView;
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
			if (cerVal == null || !cerVal.isAccepted()) {
				if (requestService.insert(cer)) {
					return new ModelAndView("resident/dashboard").addObject("result", new Result(
							"Solicitação para " + condominium.getName() + " enviada com sucesso", "success"));
				} else {
					return new ModelAndView("resident/entryCondominium").addObject("result",
							new Result("Falha ao enviar solicitação ao condomínio", "error"));
				}
			} else if(cerVal.isAccepted()) {
				return new ModelAndView("resident/dashboard").addObject("result", new Result(
						"Solicitação para " + condominium.getName() + " já aceita", "success"));
			} else {
				return new ModelAndView("resident/entryCondominium").addObject("result",
						new Result("Falha ao enviar solicitação ao condomínio", "error"));
			}
		} else {
			return new ModelAndView("resident/entryCondominium").addObject("result",
					new Result("Condomínio não encontrado", "error"));
		}
	}
}
