package br.edu.utfpr.sgcc.controllers;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.MaskFormatter;
import javax.validation.Valid;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.utfpr.sgcc.config.DataBaseConfig;
import br.edu.utfpr.sgcc.config.Encryptor;
import br.edu.utfpr.sgcc.daos.AdminDAOImpl;
import br.edu.utfpr.sgcc.models.Admin;
import br.edu.utfpr.sgcc.models.Condominium;
import br.edu.utfpr.sgcc.models.CondominiumFee;
import br.edu.utfpr.sgcc.models.CondominiumResident;
import br.edu.utfpr.sgcc.models.Fee;
import br.edu.utfpr.sgcc.models.MyUserDetails;
import br.edu.utfpr.sgcc.models.Resident;
import br.edu.utfpr.sgcc.models.Result;
import br.edu.utfpr.sgcc.models.User;
import br.edu.utfpr.sgcc.service.AdminService;
import br.edu.utfpr.sgcc.service.CondominiumFeeService;
import br.edu.utfpr.sgcc.service.CondominiumResidentService;
import br.edu.utfpr.sgcc.service.CondominiumService;
import br.edu.utfpr.sgcc.service.FeeService;
import br.edu.utfpr.sgcc.service.FeeTypeService;
import br.edu.utfpr.sgcc.service.ResidentService;
import br.edu.utfpr.sgcc.service.UserService;
import br.edu.utfpr.sgcc.service.WarningService;

@Controller
public class ResidentController {

	@GetMapping("/user/condominium/resident")
	public ModelAndView residentView(@RequestParam int id_resident) throws ParseException {
		ModelAndView modelAndView = new ModelAndView("user/condominium/resident/resident");
		ResidentService residentService = new ResidentService();
		Resident resident = residentService.returnById(id_resident);
		modelAndView.addObject("resident", resident);

		CondominiumService condominiumService = new CondominiumService();
		modelAndView.addObject("condominium", condominiumService.returnById(resident.getIdCondominium()));

		modelAndView.addObject("format", new SimpleDateFormat("dd/MM/yyyy"));
		WarningService warningService = new WarningService();
		modelAndView.addObject("warnings", warningService.returnByResident(id_resident));

		return modelAndView;
	}

	@GetMapping("/user/condominium/residents")
	public ModelAndView residentList(@RequestParam int id_condominium, @RequestParam(defaultValue = "") String name,
			@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "20") int results) {
		ModelAndView modelAndView = new ModelAndView("user/condominium/resident/residents");

		CondominiumService condominiumService = new CondominiumService();
		Condominium condominium = condominiumService.returnById(id_condominium);
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (condominium.getIdUser() == user.getId()) {

			CondominiumResidentService residentService = new CondominiumResidentService();
			int totalData = residentService.returnCount(id_condominium);
			modelAndView.addObject("totalPages", ((int) Math.ceil(((double) totalData) / results)));
			modelAndView.addObject("currentPage", page);
			HashMap<String, String> map = new HashMap<>();
			map.put("id_condominium", String.valueOf(id_condominium));
			map.put("results", String.valueOf(results));
			modelAndView.addObject("map", map);
			modelAndView.addObject("residents", residentService.returnUserByCondominium(id_condominium, page, results));
			modelAndView.addObject("condominium", condominium);
		} else {
			modelAndView = new ModelAndView("errors/accessdenied");
		}
		return modelAndView;
	}

	@PostMapping("/user/condominium/residents")
	public ModelAndView residentListPost(@RequestParam int id_condominium, @RequestParam(defaultValue = "") String name,
			@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "20") int results) {
		ModelAndView modelAndView = new ModelAndView("user/condominium/resident/residents");
		
		CondominiumService condominiumService = new CondominiumService();
		Condominium condominium = condominiumService.returnById(id_condominium);
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (condominium.getIdUser() == user.getId()) {
			
			CondominiumResidentService residentService = new CondominiumResidentService();
			int totalData = residentService.returnCount(id_condominium);
			modelAndView.addObject("totalPages", ((int) Math.ceil(((double) totalData) / results)));
			modelAndView.addObject("currentPage", page);
			HashMap<String, String> map = new HashMap<>();
			map.put("id_condominium", String.valueOf(id_condominium));
			map.put("results", String.valueOf(results));
			modelAndView.addObject("map", map);
			modelAndView.addObject("residents", residentService.returnUserByCondominium(id_condominium, page, results));
			modelAndView.addObject("condominium", condominium);
		} else {
			modelAndView = new ModelAndView("errors/accessdenied");
		}
		return modelAndView;
	}

	@GetMapping("/user/condominium/resident/block")
	public ModelAndView residentBlock(@RequestParam int id_condominium, @RequestParam int id_resident,
			final RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("user/condominium/resident/block");
		CondominiumResidentService residentService = new CondominiumResidentService();

		CondominiumService condominiumService = new CondominiumService();
		Condominium condominium = condominiumService.returnById(id_condominium);

		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if ((condominium.getIdUser() == user.getId())) {
			modelAndView.addObject("condominium", condominium);
			CondominiumResident condominiumResident = residentService.returnByResidentAndCondominium(id_resident,
					id_condominium);
			if (condominiumResident != null) {
				modelAndView.addObject("condominiumResident", condominiumResident);
				User resident = new UserService().returnById(id_resident);
				modelAndView.addObject("resident", resident);
				return modelAndView;
			} else {
				modelAndView = new ModelAndView(
						"redirect:/user/condominium/residents?id_condominium=" + id_condominium);
				redirectAttributes.addFlashAttribute("result", new Result("Falha ao localizar morador", "error"));
				return modelAndView;
			}
		} else {
			return new ModelAndView("errors/accessdenied");
		}
	}

	@PostMapping("/user/condominium/resident/block")
	public ModelAndView formBlockResident(@ModelAttribute @Valid CondominiumResident resident, BindingResult result,
			final RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return new ModelAndView("redirect:/user/condominium/resident/block?id_resident=" + resident.getIdResident()
					+ "&id_condominium=" + resident.getIdCondominium());
		}

		ModelAndView modelAndView = new ModelAndView(
				"redirect:/user/condominium/residents?id_condominium=" + resident.getIdCondominium());
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		CondominiumService condominiumService = new CondominiumService();
		Condominium condominium = condominiumService.returnById(resident.getIdCondominium());

		if (condominium.getIdUser() == user.getId()) {
			CondominiumResidentService condominiumResidentService = new CondominiumResidentService();
			resident = condominiumResidentService.returnByResidentAndCondominium(resident);

			if (resident != null) {
				if (resident.isActive()) {
					boolean bolresult = condominiumResidentService.block(resident.getIdResident(),
							resident.getIdCondominium());

					if (bolresult) {
						redirectAttributes.addFlashAttribute("result",
								new Result("Morador inativado com sucesso", "success"));
					} else {
						redirectAttributes.addFlashAttribute("result",
								new Result("Falhar ao inativar morador", "error"));
					}
				} else {
					modelAndView = new ModelAndView(
							"redirect:/user/condominium/residents?id_condominium=" + resident.getIdCondominium());
					redirectAttributes.addFlashAttribute("result", new Result("Morador já inativo", "success"));
					return modelAndView;
				}
			} else {
				return new ModelAndView("errors/accessdenied");
			}
		} else {
			return new ModelAndView("errors/accessdenied");
		}
		return modelAndView;
	}

	@GetMapping("/user/condominium/resident/unblock")
	public ModelAndView residentUnblock(@RequestParam int id_condominium, @RequestParam int id_resident,
			final RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("user/condominium/resident/unblock");
		CondominiumResidentService residentService = new CondominiumResidentService();

		CondominiumService condominiumService = new CondominiumService();
		Condominium condominium = condominiumService.returnById(id_condominium);

		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if ((condominium.getIdUser() == user.getId())) {
			modelAndView.addObject("condominium", condominium);
			CondominiumResident condominiumResident = residentService.returnByResidentAndCondominium(id_resident,
					id_condominium);
			if (condominiumResident != null) {
				modelAndView.addObject("condominiumResident", condominiumResident);
				User resident = new UserService().returnById(id_resident);
				modelAndView.addObject("resident", resident);
				return modelAndView;
			} else {
				modelAndView = new ModelAndView(
						"redirect:/user/condominium/residents?id_condominium=" + id_condominium);
				redirectAttributes.addFlashAttribute("result", new Result("Falha ao localizar morador", "error"));
				return modelAndView;
			}
		} else {
			return new ModelAndView("errors/accessdenied");
		}
	}

	@PostMapping("/user/condominium/resident/unblock")
	public ModelAndView formUnblockResident(@ModelAttribute @Valid CondominiumResident resident, BindingResult result,
			final RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return new ModelAndView("redirect:/user/condominium/resident/unblock?id_resident="
					+ resident.getIdResident() + "&id_condominium=" + resident.getIdCondominium());
		}

		ModelAndView modelAndView = new ModelAndView(
				"redirect:/user/condominium/residents?id_condominium=" + resident.getIdCondominium());

		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		CondominiumService condominiumService = new CondominiumService();
		Condominium condominium = condominiumService.returnById(resident.getIdCondominium());

		if (condominium.getIdUser() == user.getId()) {
			CondominiumResidentService condominiumResidentService = new CondominiumResidentService();
			resident = condominiumResidentService.returnByResidentAndCondominium(resident);

			if (resident != null) {
				if (!resident.isActive()) {
					boolean bolresult = new CondominiumResidentService().unblock(resident.getIdResident(),
							resident.getIdCondominium());

					if (bolresult) {
						redirectAttributes.addFlashAttribute("result",
								new Result("Morador ativado com sucesso", "success"));
					} else {
						redirectAttributes.addFlashAttribute("result", new Result("Falhar ao ativar morador", "error"));
					}

				} else {
					modelAndView = new ModelAndView(
							"redirect:/user/condominium/residents?id_condominium=" + resident.getIdCondominium());
					redirectAttributes.addFlashAttribute("result", new Result("Morador já inativo", "success"));
					return modelAndView;
				}
			} else {
				return new ModelAndView("errors/accessdenied");
			}
		} else {
			return new ModelAndView("errors/accessdenied");
		}
		return modelAndView;
	}

}
