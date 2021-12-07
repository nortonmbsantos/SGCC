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
import br.edu.utfpr.sgcc.models.CondominiumResident;
import br.edu.utfpr.sgcc.models.MyUserDetails;
import br.edu.utfpr.sgcc.models.Result;
import br.edu.utfpr.sgcc.models.Warning;
import br.edu.utfpr.sgcc.service.CondominiumFeeService;
import br.edu.utfpr.sgcc.service.CondominiumResidentService;
import br.edu.utfpr.sgcc.service.CondominiumService;
import br.edu.utfpr.sgcc.service.WarningService;

@Controller
public class WarningController {
	@GetMapping("/user/condominium/warning/new")
	public ModelAndView addWarning(@RequestParam int id_resident, @RequestParam int id_condominium) {
		ModelAndView modelsAndView = new ModelAndView("user/condominium/warning/form");
//		ResidentService residentService = new ResidentService();

		CondominiumService condominiumService = new CondominiumService();
		Condominium condominium = condominiumService.returnById(id_condominium);
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (condominium != null && condominium.getIdUser() == user.getId()) {
			CondominiumResidentService condominiumResidentService = new CondominiumResidentService();
			CondominiumResident condominiumResident = condominiumResidentService
					.returnByResidentAndCondominium(id_resident, id_condominium);

			if (condominiumResident != null && condominiumResident.isActive()) {
				CondominiumResidentService residentService = new CondominiumResidentService();
				modelsAndView.addObject("resident",
						residentService.returnByResidentAndCondominium(id_resident, id_condominium));
				modelsAndView.addObject("condominiumFees", new CondominiumFeeService().returnActives(id_condominium));
				modelsAndView.addObject("warning", new Warning());
			} else {
				modelsAndView = new ModelAndView("errors/accessdenied").addObject("result",
						new Result("Morador não existente ou não está ativo", "error"));
			}
		} else {
			modelsAndView = new ModelAndView("errors/accessdenied").addObject("result",
					new Result("Morador não existente ou não está ativo", "error"));
		}
		return modelsAndView;
	}

	@PostMapping("/user/condominium/warning/new")
	public ModelAndView addWarningForm(@ModelAttribute @Valid Warning warning, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		ModelAndView modelsAndView;
		WarningService service = new WarningService();
		int idCondominium = 0;
		CondominiumFee condominiumFee = new CondominiumFeeService().returnById(warning.getIdCondominiumFee());
		CondominiumResidentService condominiumResidentService = new CondominiumResidentService();
		CondominiumResident condominiumResident = condominiumResidentService
				.returnByResidentAndCondominium(warning.getIdResident(), condominiumFee.getIdCondominium());

			if (condominiumFee != null) {
				Condominium condominium = new CondominiumService().returnById(condominiumFee.getIdCondominium());
				MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
						.getPrincipal();

				if ((condominium != null && condominium.getIdUser() == user.getId()) && condominiumResident != null) {
					idCondominium = condominium.getId();
					if (result.hasErrors()) {
						return new ModelAndView("user/condominium/warning/form");
					}

					if (service.insert(warning)) {
						redirectAttributes.addFlashAttribute("result",
								new Result("Multa cadastrada com sucesso", "success"));
					} else {
						return new ModelAndView("user/condominium/warning/form")
								.addObject("resident", condominiumResident)
								.addObject("condominiumFees", new CondominiumFeeService().returnActives(condominium.getId()))
								.addObject("result", new Result("Falha ao inserir multa", "error"));
					}
				} else {
					return new ModelAndView("error/accessdenied");					
				}
	
		} else {
			return new ModelAndView("user/condominium/warning/form")
					.addObject("resident", condominiumResident)
					.addObject("condominiumFees", new CondominiumFeeService().returnActives(condominiumFee.getIdCondominium()))
					.addObject("result", new Result("Falha ao localizar periodo selecionado", "error"));
		}

		return new ModelAndView("redirect:/user/condominium/resident/warnings?id_condominium=" + idCondominium
				+ "&id_resident=" + warning.getIdResident());
	}

	@GetMapping("/user/condominium/warning/update")
	public ModelAndView updateWarning(@RequestParam int id) {
		ModelAndView modelsAndView = new ModelAndView("user/condominium/warning/form");
//		ResidentService residentService = new ResidentService();
		WarningService warningService = new WarningService();

		Warning warning = warningService.returnById(id);

		if (warning != null) {
			CondominiumFeeService condominiumFeeService = new CondominiumFeeService();

			CondominiumFee condominiumFee = condominiumFeeService.returnById(warning.getIdCondominiumFee());

			CondominiumService condominiumService = new CondominiumService();
			Condominium condominium = condominiumService.returnById(condominiumFee.getIdCondominium());
			MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			if (condominium != null && condominium.getIdUser() == user.getId()) {
				CondominiumResidentService condominiumResidentService = new CondominiumResidentService();
				CondominiumResident condominiumResident = condominiumResidentService
						.returnByResidentAndCondominium(warning.getIdResident(), condominiumFee.getIdCondominium());

				if (condominiumResident != null && condominiumResident.isActive()) {
					CondominiumResidentService residentService = new CondominiumResidentService();
					modelsAndView.addObject("resident", residentService.returnByResidentAndCondominium(
							warning.getIdResident(), condominiumFee.getIdCondominium()));
					modelsAndView.addObject("condominiumFees",
							condominiumFeeService.returnActives(condominiumFee.getIdCondominium()));
					modelsAndView.addObject("warning", warning);
				} else {
					modelsAndView = new ModelAndView("errors/accessdenied").addObject("result",
							new Result("Morador não existente ou não está ativo", "error"));
				}
			} else {
				modelsAndView = new ModelAndView("errors/accessdenied").addObject("result",
						new Result("Morador não existente ou não está ativo", "error"));
			}
		} else {
			modelsAndView = new ModelAndView("errors/accessdenied").addObject("result",
					new Result("Multa não encontrada", "error"));
		}
		return modelsAndView;
	}

	@PostMapping("/user/condominium/warning/update")
	public ModelAndView updateWarningForm(@ModelAttribute @Valid Warning warning, BindingResult result,
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

				if (service.update(warning)) {
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

		return new ModelAndView("redirect:/user/condominium/resident/warnings?id_condominium=" + idCondominium
				+ "&id_resident=" + warning.getIdResident());
	}

	@GetMapping("/user/condominium/warnings")
	public ModelAndView residentWarnings(@RequestParam int id_resident) {
		ModelAndView modelsAndView = new ModelAndView("user/condominium/warning/warnings");

		modelsAndView.addObject("format", new SimpleDateFormat("dd/MM/yyyy"));
		WarningService warningService = new WarningService();
		modelsAndView.addObject("warnings", warningService.returnByResident(id_resident));

		return modelsAndView;
	}

	@GetMapping("/user/condominium/resident/warnings")
	public ModelAndView condominiumResidentWarnings(@RequestParam int id_resident, @RequestParam int id_condominium) {
		ModelAndView modelsAndView = new ModelAndView("user/condominium/warning/warnings");

		CondominiumService condominiumService = new CondominiumService();
		Condominium condominium = condominiumService.returnById(id_condominium);
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (condominium != null && condominium.getIdUser() == user.getId()) {
			CondominiumResidentService condominiumResidentService = new CondominiumResidentService();
			CondominiumResident condominiumResident = condominiumResidentService
					.returnByResidentAndCondominium(id_resident, id_condominium);

			if (condominiumResident != null && condominiumResident.isActive()) {

				modelsAndView.addObject("format", new SimpleDateFormat("dd/MM/yyyy"));
				modelsAndView.addObject("resident", condominiumResident);
				WarningService warningService = new WarningService();
				modelsAndView.addObject("warnings",
						warningService.returnByCondominiumResident(id_resident, id_condominium));

			} else {
				modelsAndView = new ModelAndView("errors/accessdenied").addObject("result",
						new Result("Morador não existente ou não está ativo", "error"));
			}
		} else {
			modelsAndView = new ModelAndView("errors/accessdenied").addObject("result",
					new Result("Morador não existente ou não está ativo", "error"));
		}

		return modelsAndView;
	}

	@PostMapping("/user/condominium/resident/warning/remove")
	public ModelAndView removeWarningForm(@RequestParam int id, final RedirectAttributes redirectAttributes) {
		ModelAndView modelsAndView = new ModelAndView("user/condominium/warning/warnings");
		WarningService warningService = new WarningService();
		Warning warning = warningService.returnById(id);
		
		if(warning != null) {
			CondominiumService condominiumService = new CondominiumService();
			CondominiumFee condominiumFee = new CondominiumFeeService().returnById(warning.getIdCondominiumFee());
			Condominium condominium = condominiumService.returnById(condominiumFee.getIdCondominium());
			MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (condominium != null && condominium.getIdUser() == user.getId()) {
				
				if(warningService.remove(warning)) {
					redirectAttributes.addFlashAttribute("result", new Result("Multa removida com sucesso","success"));
					return new ModelAndView("redirect:/user/condominium/resident/warnings?id_resident="+warning.getIdResident() + "&id_condominium=" + condominium.getId());					
				} else {					
					redirectAttributes.addFlashAttribute("result", new Result("Falha ao remover multa","success"));
					return new ModelAndView("redirect:/user/condominium/resident/warnings?id_resident="+warning.getIdResident() + "&id_condominium=" + condominium.getId());					
				}
			} else {
				modelsAndView = new ModelAndView("errors/accessdenied");			
			}
		} else {			
			modelsAndView = new ModelAndView("errors/accessdenied").addObject("result",
					new Result("Multa não existente ou não está ativo", "error"));
		}		
		return modelsAndView;
	}

}
