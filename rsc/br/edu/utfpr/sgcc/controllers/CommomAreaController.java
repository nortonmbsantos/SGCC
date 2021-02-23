package br.edu.utfpr.sgcc.controllers;

import java.text.SimpleDateFormat;
import java.util.List;

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

import br.edu.utfpr.sgcc.models.Admin;
import br.edu.utfpr.sgcc.models.CommomArea;
import br.edu.utfpr.sgcc.models.Condominium;
import br.edu.utfpr.sgcc.models.MyUserDetails;
import br.edu.utfpr.sgcc.models.Report;
import br.edu.utfpr.sgcc.models.Result;
import br.edu.utfpr.sgcc.service.BookingService;
import br.edu.utfpr.sgcc.service.CommomAreaService;
import br.edu.utfpr.sgcc.service.CondominiumFeeService;
import br.edu.utfpr.sgcc.service.CondominiumService;

@Controller
public class CommomAreaController {
	@GetMapping("/user/condominium/commomareas")
	public ModelAndView listCondominiuns(@RequestParam int id_condominium) {
		ModelAndView modelAndView = new ModelAndView("user/condominium/commomarea/commomareas");
		CommomAreaService service = new CommomAreaService();
		CondominiumService condominiumService = new CondominiumService();
		Condominium condominium = condominiumService.returnById(id_condominium);
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (condominium.getIdUser() == user.getId()) {
			modelAndView.addObject("commomAreas", service.list(id_condominium));
			modelAndView.addObject("condominium", condominium);
			modelAndView.addObject("pendingBookings", new BookingService().countPendingBookings(id_condominium));
		} else {
			modelAndView = new ModelAndView("errors/accessdenied");
		}
		return modelAndView;
	}

	@GetMapping("/user/condominium/commomarea")
	public ModelAndView show(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView("user/condominium/commomarea/commomarea");
		CondominiumService service = new CondominiumService();
		Condominium condominium = service.returnById(id);
		if (condominium != null) {
			MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (condominium.getIdUser() == user.getId()) {
				modelAndView.addObject("condominium", condominium);
				modelAndView.addObject("format", new SimpleDateFormat("dd/MM/yyyy"));
				CondominiumFeeService condominiumFeeService = new CondominiumFeeService();

				modelAndView.addObject("reportByClosingDate",
						condominiumFeeService.reportByClosingDate(condominium.getId()));
				modelAndView.addObject("reportByFeeType", condominiumFeeService.reportByFeeType(condominium.getId()));
				modelAndView.addObject("reportLastFeeValue",
						condominiumFeeService.reportLastCondominiumFeeTotalValue(condominium.getId()));
			} else {
				condominium = null;
			}
		}
		if (condominium == null) {
			modelAndView = new ModelAndView("errors/accessdenied");
		}
		return modelAndView;
	}

	@GetMapping("/user/condominium/commomarea/new")
	public ModelAndView addCondominium(@RequestParam int id_condominium) {
		ModelAndView modelAndView = new ModelAndView("user/condominium/commomarea/new");
		CommomArea commomArea = new CommomArea();
		modelAndView.addObject("commomArea", commomArea);
		CondominiumService condominiumService = new CondominiumService();
		modelAndView.addObject("condominium", condominiumService.returnById(id_condominium));
		return modelAndView;
	}

	@PostMapping("/user/condominium/commomarea/add")
	public ModelAndView addCondominiumForm(@ModelAttribute @Valid CommomArea commomArea, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView("user/condominium/commomarea/new");
		CommomAreaService service = new CommomAreaService();
		CondominiumService condominiumService = new CondominiumService();
		Condominium condominium = condominiumService.returnById(commomArea.getIdCondominium());
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (condominium.getIdUser() == user.getId()) {
			if (service.insert(commomArea)) {
				modelAndView.addObject("result", new Result("Área comum cadastrada com sucesso", "success"));
			} else {
				modelAndView.addObject("result", new Result("Falha ao cadastrar área comum", "error"));
			}
		}
		if (result.hasErrors()) {
			return new ModelAndView("user/condominium/commomarea/new");
		}
		return modelAndView;
	}

	@GetMapping("/user/condominium/commomarea/update")
	public ModelAndView updateArea(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView("user/condominium/commomarea/update");
		CommomArea commomArea = new CommomAreaService().returnById(id);
		modelAndView.addObject("commomArea", commomArea);
		CondominiumService condominiumService = new CondominiumService();
		Condominium condominium = condominiumService.returnById(commomArea.getIdCondominium());
		modelAndView.addObject("condominium", condominium);
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (condominium.getIdUser() == user.getId()) {
			return modelAndView;
		} else {
			return new ModelAndView("errors/accessdenied");

		}
	}

	@PostMapping("/user/condominium/commomarea/update")
	public ModelAndView updateForm(@ModelAttribute @Valid CommomArea commomArea, BindingResult result) {
		CommomAreaService service = new CommomAreaService();
		CondominiumService condominiumService = new CondominiumService();
		CommomArea areaVal = service.returnById(commomArea.getId());
		Condominium condominium = condominiumService.returnById(areaVal.getIdCondominium());
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (user.getId() == condominium.getIdUser()) {
			ModelAndView modelAndView = new ModelAndView(
					"redirect:/user/condominium/commomareas?id_condominium=" + condominium.getId());
			if (result.hasErrors()) {
				return new ModelAndView("user/condominium/commomarea/update");
			}
			service.update(commomArea);
			return modelAndView;
		} else {
			return new ModelAndView("errors/accessdenied");
		}
	}
}
