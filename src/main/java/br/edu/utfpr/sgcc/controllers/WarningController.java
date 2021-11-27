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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.utfpr.sgcc.models.Condominium;
import br.edu.utfpr.sgcc.models.CondominiumFee;
import br.edu.utfpr.sgcc.models.MyUserDetails;
import br.edu.utfpr.sgcc.models.Result;
import br.edu.utfpr.sgcc.models.Warning;
import br.edu.utfpr.sgcc.service.CondominiumFeeService;
import br.edu.utfpr.sgcc.service.CondominiumResidentService;
import br.edu.utfpr.sgcc.service.CondominiumService;
import br.edu.utfpr.sgcc.service.ResidentService;
import br.edu.utfpr.sgcc.service.WarningService;

@Controller
public class WarningController {
	@GetMapping("/user/condominium/warning/new")
	public ModelAndView addWarning(@RequestParam int id_resident, @RequestParam int id_condominium) {
		ModelAndView modelsAndView = new ModelAndView("user/condominium/warning/form");
//		ResidentService residentService = new ResidentService();
		CondominiumResidentService residentService = new CondominiumResidentService();
		modelsAndView.addObject("resident", residentService.returnByResidentAndCondominium(id_resident, id_condominium));
		modelsAndView.addObject("condominiumFees", new CondominiumFeeService().returnActives(id_condominium));
		modelsAndView.addObject("warning", new Warning());
		return modelsAndView;
	}

	@PostMapping("/user/condominium/warning/new")
	public ModelAndView addWarningForm(@ModelAttribute @Valid Warning warning, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		ModelAndView modelsAndView;
		WarningService service = new WarningService();
		int idCondominium = 0;
		CondominiumFee condominiumFee = new CondominiumFeeService().returnById(warning.getIdCondominiumFee());

		if (condominiumFee != null) {

			Condominium condominium = new CondominiumService().returnById(condominiumFee.getIdCondominium());
			MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			if (condominium != null && condominium.getIdUser() == user.getId()) {
				idCondominium = condominium.getId();
				if (result.hasErrors()) {
					return new ModelAndView("user/condominium/warning/form");
				}

				if (service.insert(warning)) {
					redirectAttributes.addFlashAttribute("result",
							new Result("Multa cadastrada com sucesso", "success"));
				} else {
					redirectAttributes.addFlashAttribute("result", new Result("Falha ao cadastrar multa", "error"));
				}

			} else {
				return new ModelAndView("user/condominium/warning/form").addObject("result",
						new Result("Falha ao localizar periodo selecionado", "error"));
			}
		} else {
			return new ModelAndView("errors/accessdenied");
		}

		return new ModelAndView("redirect:/user/condominium?id=" + idCondominium);
	}

	@GetMapping("/user/condominium/warnings")
	public ModelAndView residentWarnings(@RequestParam int id_resident) {
		ModelAndView modelsAndView = new ModelAndView("user/condominium/warning/warnings");
		ResidentService residentService = new ResidentService();
		modelsAndView.addObject("resident", residentService.returnById(id_resident));

		modelsAndView.addObject("format", new SimpleDateFormat("dd/MM/yyyy"));
		WarningService warningService = new WarningService();
		modelsAndView.addObject("warnings", warningService.returnByResident(id_resident));

		return modelsAndView;
	}

}
