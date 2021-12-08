package br.edu.utfpr.sgcc.controllers;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.validation.Valid;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.edu.utfpr.sgcc.models.CommomArea;
import br.edu.utfpr.sgcc.models.Condominium;
import br.edu.utfpr.sgcc.models.MyUserDetails;
import br.edu.utfpr.sgcc.models.Result;
import br.edu.utfpr.sgcc.service.BookingService;
import br.edu.utfpr.sgcc.service.CommomAreaService;
import br.edu.utfpr.sgcc.service.CondominiumFeeService;
import br.edu.utfpr.sgcc.service.CondominiumService;

@Controller
public class CommomAreaController {
	@GetMapping("/user/condominium/commomareas")
	public ModelAndView listCondominiuns(@RequestParam int id_condominium,
			@RequestParam(required = false) String filter, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int results) {
		ModelAndView modelsAndView = new ModelAndView("user/condominium/commomarea/commomareas");
		CommomAreaService service = new CommomAreaService();
		CondominiumService condominiumService = new CondominiumService();
		Condominium condominium = condominiumService.returnById(id_condominium);
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (condominium.getIdUser() == user.getId()) {
			modelsAndView.addObject("commomAreas", service.list(id_condominium, filter, page, results));
			int totalData = service.returnCount(id_condominium, filter);
			HashMap<String, String> map = new HashMap<>();
			map.put("id_condominium", String.valueOf(id_condominium));
			map.put("results", String.valueOf(results));
			if (filter != null && !filter.isEmpty()) {
				map.put("filter", String.valueOf(filter));
			}
			modelsAndView.addObject("map", map);
			modelsAndView.addObject("filter", filter);
			modelsAndView.addObject("totalPages", ((int) Math.ceil(((double) totalData) / results)));
			modelsAndView.addObject("currentPage", page);
			modelsAndView.addObject("condominium", condominium);
			modelsAndView.addObject("pendingBookings", new BookingService().countPendingBookings(id_condominium));
		} else {
			modelsAndView = new ModelAndView("errors/accessdenied");
		}
		return modelsAndView;
	}

	@PostMapping("/user/condominium/commomareas")
	public ModelAndView listCondominiunsPost(@RequestParam int id_condominium,
			@RequestParam(required = false) String filter, @RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "10") int results) {
		ModelAndView modelsAndView = new ModelAndView("user/condominium/commomarea/commomareas");
		CommomAreaService service = new CommomAreaService();
		CondominiumService condominiumService = new CondominiumService();
		Condominium condominium = condominiumService.returnById(id_condominium);
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (condominium.getIdUser() == user.getId()) {
			modelsAndView.addObject("commomAreas", service.list(id_condominium, filter, page, results));
			int totalData = service.returnCount(id_condominium, filter);
			HashMap<String, String> map = new HashMap<>();
			map.put("id_condominium", String.valueOf(id_condominium));
			map.put("results", String.valueOf(results));
			if (filter != null && !filter.isEmpty()) {
				map.put("filter", String.valueOf(filter));
			}
			modelsAndView.addObject("map", map);
			modelsAndView.addObject("filter", filter);
			modelsAndView.addObject("totalPages", ((int) Math.ceil(((double) totalData) / results)));
			modelsAndView.addObject("currentPage", page);
			modelsAndView.addObject("condominium", condominium);
			modelsAndView.addObject("pendingBookings", new BookingService().countPendingBookings(id_condominium));
		} else {
			modelsAndView = new ModelAndView("errors/accessdenied");
		}
		return modelsAndView;
	}

	@GetMapping("/user/condominium/commomarea")
	public ModelAndView show(@RequestParam int id) {
		ModelAndView modelsAndView = new ModelAndView("user/condominium/commomarea/commomarea");
		CommomAreaService areaService = new CommomAreaService();
		CommomArea commomArea = areaService.returnById(id);
		Condominium condominium = null;
		
		if (commomArea != null) {
			CondominiumService service = new CondominiumService();
			condominium = service.returnById(commomArea.getIdCondominium());
			MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (condominium != null && condominium.getIdUser() == user.getId()) {
				if (condominium.getIdUser() == user.getId()) {
					modelsAndView.addObject("commomArea", commomArea);
					modelsAndView.addObject("condominium", condominium);
					CondominiumFeeService condominiumFeeService = new CondominiumFeeService();
				} else {
					condominium = null;
				}
			}
		}
		if (condominium == null) {
			modelsAndView = new ModelAndView("errors/accessdenied");
		}
		return modelsAndView;
	}

	@GetMapping("/user/condominium/commomarea/new")
	public ModelAndView addCondominium(@RequestParam int id_condominium) {
		ModelAndView modelsAndView = new ModelAndView("user/condominium/commomarea/form");
		CommomArea commomArea = new CommomArea();
		modelsAndView.addObject("commomArea", commomArea);
		CondominiumService condominiumService = new CondominiumService();
		modelsAndView.addObject("condominium", condominiumService.returnById(id_condominium));
		return modelsAndView;
	}

	@PostMapping("/user/condominium/commomarea/add")
	public ModelAndView addCondominiumForm(@ModelAttribute @Valid CommomArea commomArea, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		if(commomArea.getName() == null || !commomArea.getName().isEmpty() || commomArea.getName().length() < 3)  {
			result.addError(new FieldError("commomArea", "name", "Nome inválido"));
		}
		
		
		if (result.hasErrors()) {
			return new ModelAndView("user/condominium/commomarea/form").addObject("result", new Result("Falha ao cadastrar área comum", "error"));
		}
		ModelAndView modelsAndView = new ModelAndView("user/condominium/commomarea/form");
		CommomAreaService service = new CommomAreaService();
		CondominiumService condominiumService = new CondominiumService();
		Condominium condominium = condominiumService.returnById(commomArea.getIdCondominium());
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (condominium.getIdUser() == user.getId()) {
			if (service.insert(commomArea)) {
				modelsAndView = new ModelAndView(
						"redirect:/user/condominium/commomareas?id_condominium=" + condominium.getId());
				redirectAttributes.addFlashAttribute("result",
						new Result("Área comum cadastrada com sucesso", "success"));
			} else {
				modelsAndView.addObject("result", new Result("Falha ao cadastrar área comum", "error"));
			}
		} else {
			return new ModelAndView("errors/accessdenied");
		}
		return modelsAndView;
	}

	@GetMapping("/user/condominium/commomarea/update")
	public ModelAndView updateArea(@RequestParam int id) {
		ModelAndView modelsAndView = new ModelAndView("user/condominium/commomarea/form");
		CommomArea commomArea = new CommomAreaService().returnById(id);
		modelsAndView.addObject("commomArea", commomArea);
		CondominiumService condominiumService = new CondominiumService();
		Condominium condominium = condominiumService.returnById(commomArea.getIdCondominium());
		modelsAndView.addObject("condominium", condominium);
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (condominium.getIdUser() == user.getId()) {
			return modelsAndView;
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
			ModelAndView modelsAndView = new ModelAndView(
					"redirect:/user/condominium/commomareas?id_condominium=" + condominium.getId());
			if (result.hasErrors()) {
				return new ModelAndView("user/condominium/commomarea/form");
			}
			service.update(commomArea);
			return modelsAndView;
		} else {
			return new ModelAndView("errors/accessdenied");
		}
	}
}
