package br.edu.utfpr.sgcc.controllers;

import java.text.SimpleDateFormat;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.utfpr.sgcc.models.Result;
import br.edu.utfpr.sgcc.models.Warning;
import br.edu.utfpr.sgcc.service.CondominiumFeeService;
import br.edu.utfpr.sgcc.service.ResidentService;
import br.edu.utfpr.sgcc.service.WarningService;

@Controller
public class WarningController {
	@GetMapping("/user/condominium/warning/new")
	public ModelAndView addWarning(@RequestParam int id_resident, @RequestParam int id_condominium) {
		ModelAndView modelAndView = new ModelAndView("user/condominium/warning/form");
		ResidentService residentService = new ResidentService();
		modelAndView.addObject("resident", residentService.returnById(id_resident));
		modelAndView.addObject("condominiumFees", new CondominiumFeeService().returnActives(id_condominium));
		modelAndView.addObject("warning", new Warning());
		return modelAndView;
	}

	@PostMapping("/user/condominium/warning/new")
	public ModelAndView addWarningForm(@ModelAttribute @Valid Warning warning, BindingResult result, final RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("redirect:/user/dashboard");
		WarningService service = new WarningService();
		
		if (result.hasErrors()) {
			return new ModelAndView("user/condominium/warning/new");
		}
		if (service.insert(warning)) {
			redirectAttributes.addFlashAttribute("result", new Result("Reserva realizada com sucesso", "success"));
		} else {
			redirectAttributes.addFlashAttribute("result", new Result("Falha ao realizar reserva", "error"));
		}
		return modelAndView;
	}

	@GetMapping("/user/condominium/warnings")
	public ModelAndView residentWarnings(@RequestParam int id_resident) {
		ModelAndView modelAndView = new ModelAndView("user/condominium/warning/warnings");
		ResidentService residentService = new ResidentService();
		modelAndView.addObject("resident", residentService.returnById(id_resident));

		modelAndView.addObject("format", new SimpleDateFormat("dd/MM/yyyy"));
		WarningService warningService = new WarningService();
		modelAndView.addObject("warnings", warningService.returnByResident(id_resident));

		return modelAndView;
	}

}
