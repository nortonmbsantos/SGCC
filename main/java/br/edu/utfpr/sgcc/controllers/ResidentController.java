package br.edu.utfpr.sgcc.controllers;

import java.text.ParseException;
import java.util.HashMap;

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
import br.edu.utfpr.sgcc.models.CondominiumResident;
import br.edu.utfpr.sgcc.models.MyUserDetails;
import br.edu.utfpr.sgcc.models.Result;
import br.edu.utfpr.sgcc.models.User;
import br.edu.utfpr.sgcc.service.BookingService;
import br.edu.utfpr.sgcc.service.CondominiumResidentService;
import br.edu.utfpr.sgcc.service.CondominiumService;
import br.edu.utfpr.sgcc.service.UserService;
import br.edu.utfpr.sgcc.service.WarningService;

@Controller
public class ResidentController {

	@GetMapping("/user/condominium/resident")
	public ModelAndView residentView(@RequestParam int id_resident, @RequestParam int id_condominium)
			throws ParseException {
		ModelAndView modelsAndView = new ModelAndView("user/condominium/resident/resident");
		
		modelsAndView.addObject("condominium", new CondominiumService().returnById(id_condominium));
		CondominiumResidentService condominiumResidentService = new CondominiumResidentService();

		CondominiumResident resident = condominiumResidentService.returnByResidentAndCondominium(id_resident,
				id_condominium);

		if (resident != null) {
			UserService userService = new UserService();
			User userResident = userService.returnById(resident.getIdResident());
			resident.setResident(userResident);

			modelsAndView.addObject("resident", resident);

			WarningService warningService = new WarningService();
			modelsAndView.addObject("warnings", warningService.returnByResident(id_resident));
			BookingService bookingService = new BookingService();
			modelsAndView.addObject("bookings", bookingService.bookingsByResident(id_resident));
			
			
		}
		return modelsAndView;
	}

	@GetMapping("/user/condominium/residents")
	public ModelAndView residentList(@RequestParam int id_condominium, @RequestParam(defaultValue = "") String name,
			@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "20") int results) {
		ModelAndView modelsAndView = new ModelAndView("user/condominium/resident/residents");

		CondominiumService condominiumService = new CondominiumService();
		Condominium condominium = condominiumService.returnById(id_condominium);
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (condominium.getIdUser() == user.getId()) {

			CondominiumResidentService residentService = new CondominiumResidentService();
			int totalData = residentService.returnCount(id_condominium);
			modelsAndView.addObject("totalPages", ((int) Math.ceil(((double) totalData) / results)));
			modelsAndView.addObject("currentPage", page);
			HashMap<String, String> map = new HashMap<>();
			map.put("id_condominium", String.valueOf(id_condominium));
			map.put("results", String.valueOf(results));
			modelsAndView.addObject("map", map);
			modelsAndView.addObject("residents", residentService.returnUserByCondominium(id_condominium, page, results));
			modelsAndView.addObject("condominium", condominium);
		} else {
			modelsAndView = new ModelAndView("errors/accessdenied");
		}
		return modelsAndView;
	}

	@PostMapping("/user/condominium/residents")
	public ModelAndView residentListPost(@RequestParam int id_condominium, @RequestParam(defaultValue = "") String name,
			@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "20") int results) {
		ModelAndView modelsAndView = new ModelAndView("user/condominium/resident/residents");

		CondominiumService condominiumService = new CondominiumService();
		Condominium condominium = condominiumService.returnById(id_condominium);
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (condominium.getIdUser() == user.getId()) {

			CondominiumResidentService residentService = new CondominiumResidentService();
			int totalData = residentService.returnCount(id_condominium);
			modelsAndView.addObject("totalPages", ((int) Math.ceil(((double) totalData) / results)));
			modelsAndView.addObject("currentPage", page);
			HashMap<String, String> map = new HashMap<>();
			map.put("id_condominium", String.valueOf(id_condominium));
			map.put("results", String.valueOf(results));
			modelsAndView.addObject("map", map);
			modelsAndView.addObject("residents", residentService.returnUserByCondominium(id_condominium, page, results));
			modelsAndView.addObject("condominium", condominium);
		} else {
			modelsAndView = new ModelAndView("errors/accessdenied");
		}
		return modelsAndView;
	}

	@GetMapping("/user/condominium/resident/block")
	public ModelAndView residentBlock(@RequestParam int id_condominium, @RequestParam int id_resident,
			final RedirectAttributes redirectAttributes) {
		ModelAndView modelsAndView = new ModelAndView("user/condominium/resident/block");
		CondominiumResidentService residentService = new CondominiumResidentService();

		CondominiumService condominiumService = new CondominiumService();
		Condominium condominium = condominiumService.returnById(id_condominium);

		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if ((condominium.getIdUser() == user.getId())) {
			modelsAndView.addObject("condominium", condominium);
			CondominiumResident condominiumResident = residentService.returnByResidentAndCondominium(id_resident,
					id_condominium);
			if (condominiumResident != null) {
				modelsAndView.addObject("condominiumResident", condominiumResident);
				User resident = new UserService().returnById(id_resident);
				modelsAndView.addObject("resident", resident);
				return modelsAndView;
			} else {
				modelsAndView = new ModelAndView(
						"redirect:/user/condominium/residents?id_condominium=" + id_condominium);
				redirectAttributes.addFlashAttribute("result", new Result("Falha ao localizar morador", "error"));
				return modelsAndView;
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

		ModelAndView modelsAndView = new ModelAndView(
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
					modelsAndView = new ModelAndView(
							"redirect:/user/condominium/residents?id_condominium=" + resident.getIdCondominium());
					redirectAttributes.addFlashAttribute("result", new Result("Morador já inativo", "success"));
					return modelsAndView;
				}
			} else {
				return new ModelAndView("errors/accessdenied");
			}
		} else {
			return new ModelAndView("errors/accessdenied");
		}
		return modelsAndView;
	}

	@GetMapping("/user/condominium/resident/unblock")
	public ModelAndView residentUnblock(@RequestParam int id_condominium, @RequestParam int id_resident,
			final RedirectAttributes redirectAttributes) {
		ModelAndView modelsAndView = new ModelAndView("user/condominium/resident/unblock");
		CondominiumResidentService residentService = new CondominiumResidentService();

		CondominiumService condominiumService = new CondominiumService();
		Condominium condominium = condominiumService.returnById(id_condominium);

		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if ((condominium.getIdUser() == user.getId())) {
			modelsAndView.addObject("condominium", condominium);
			CondominiumResident condominiumResident = residentService.returnByResidentAndCondominium(id_resident,
					id_condominium);
			if (condominiumResident != null) {
				modelsAndView.addObject("condominiumResident", condominiumResident);
				User resident = new UserService().returnById(id_resident);
				modelsAndView.addObject("resident", resident);
				return modelsAndView;
			} else {
				modelsAndView = new ModelAndView(
						"redirect:/user/condominium/residents?id_condominium=" + id_condominium);
				redirectAttributes.addFlashAttribute("result", new Result("Falha ao localizar morador", "error"));
				return modelsAndView;
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

		ModelAndView modelsAndView = new ModelAndView(
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
					modelsAndView = new ModelAndView(
							"redirect:/user/condominium/residents?id_condominium=" + resident.getIdCondominium());
					redirectAttributes.addFlashAttribute("result", new Result("Morador já inativo", "success"));
					return modelsAndView;
				}
			} else {
				return new ModelAndView("errors/accessdenied");
			}
		} else {
			return new ModelAndView("errors/accessdenied");
		}
		return modelsAndView;
	}

}
