package br.edu.utfpr.sgcc.controllers;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

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
import br.edu.utfpr.sgcc.models.Report;
import br.edu.utfpr.sgcc.models.Result;
import br.edu.utfpr.sgcc.service.CondominiumFeeService;
import br.edu.utfpr.sgcc.service.CondominiumResidentService;
import br.edu.utfpr.sgcc.service.CondominiumService;
import br.edu.utfpr.sgcc.service.FeeService;
import br.edu.utfpr.sgcc.service.WarningService;

@Controller
public class CondominiumFeeController {

	@GetMapping("/user/condominium/condominiumfee/report")
	public ModelAndView reportByClosingDate() {
		ModelAndView modelsAndView = new ModelAndView("user/condominium/fee/addFee");
		CondominiumFeeService condominiumFeeService = new CondominiumFeeService();
		List<Report> reports = condominiumFeeService.reportByClosingDate(1);

		modelsAndView.addObject("reports", reports);
		return modelsAndView;
	}

	@GetMapping("/user/condominium/condominiumfees")
	public ModelAndView returnByCondominiumId(@RequestParam int id_condominium,
			@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "20") int results) {
		ModelAndView modelsAndView = new ModelAndView("user/condominium/condominiumfee/condominiumfees");

		CondominiumService condominiumService = new CondominiumService();
		Condominium condominium = condominiumService.returnById(id_condominium);
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (condominium.getIdUser() == user.getId()) {

			CondominiumFeeService condominiumFeeService = new CondominiumFeeService();
			List<CondominiumFee> fees = condominiumFeeService.returnByCondominiumId(id_condominium, page, results);
			int totalData = condominiumFeeService.returnCount(id_condominium);
			modelsAndView.addObject("format", new SimpleDateFormat("dd/MM/yyyy"));
			modelsAndView.addObject("totalPages", ((int) Math.ceil(((double) totalData) / results)));
			modelsAndView.addObject("currentPage", page);
			HashMap<String, String> map = new HashMap<>();
			map.put("id_condominium", String.valueOf(id_condominium));
			map.put("results", String.valueOf(results));
			modelsAndView.addObject("map", map);
			modelsAndView.addObject("condominiumFees", fees);
			modelsAndView.addObject("condominium", condominium);
		} else {
			modelsAndView = new ModelAndView("errors/accessdenied");
		}
		return modelsAndView;
	}

	@PostMapping("/user/condominium/condominiumfees")
	public ModelAndView returnByCondominiumIdPost(@RequestParam int id_condominium,
			@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "20") int results) {
		ModelAndView modelsAndView = new ModelAndView("user/condominium/condominiumfee/condominiumfees");

		CondominiumService condominiumService = new CondominiumService();
		Condominium condominium = condominiumService.returnById(id_condominium);
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (condominium.getIdUser() == user.getId()) {

			CondominiumFeeService condominiumFeeService = new CondominiumFeeService();
			List<CondominiumFee> fees = condominiumFeeService.returnByCondominiumId(id_condominium, page, results);
			int totalData = condominiumFeeService.returnCount(id_condominium);
			modelsAndView.addObject("format", new SimpleDateFormat("dd/MM/yyyy"));
			modelsAndView.addObject("totalPages", Math.ceil(((double) totalData) / results));
			HashMap<String, String> map = new HashMap<>();
			map.put("id_condominium", String.valueOf(id_condominium));
			map.put("results", String.valueOf(results));
			modelsAndView.addObject("map", map);
			modelsAndView.addObject("currentPage", page);
			modelsAndView.addObject("condominiumFees", fees);
			modelsAndView.addObject("condominium", condominium);
		} else {
			modelsAndView = new ModelAndView("errors/accessdenied");
		}
		return modelsAndView;
	}

	@GetMapping("/user/condominium/condominiumfee/closing")
	public ModelAndView confirmClosing(@RequestParam int id_condominium_fee) {
		ModelAndView modelsAndView = new ModelAndView("user/condominium/condominiumfee/confirmfinishing");

		CondominiumFeeService condominiumFeeService = new CondominiumFeeService();
		CondominiumFee condominiumFee = condominiumFeeService.returnById(id_condominium_fee);
		CondominiumService condominiumService = new CondominiumService();
		Condominium condominium = condominiumService.returnById(condominiumFee.getIdCondominium());
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (condominium.getIdUser() == user.getId()) {
			modelsAndView.addObject("condominiumFee", condominiumFee);
			modelsAndView.addObject("condominium", condominium);
		} else {
			modelsAndView = new ModelAndView("errors/accessdenied");
		}
		return modelsAndView;
	}

	@PostMapping("/user/condominium/condominiumfee/closing")
	public ModelAndView formConfirmClosing(@RequestParam int id, final RedirectAttributes redirectAttributes) {
		ModelAndView modelsAndView;

		CondominiumFeeService condominiumFeeService = new CondominiumFeeService();
		CondominiumFee condominiumFee = condominiumFeeService.returnById(id);

		if (condominiumFee != null) {
			CondominiumService condominiumService = new CondominiumService();
			Condominium condominium = condominiumService.returnById(condominiumFee.getIdCondominium());
			MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if (condominium.getIdUser() == user.getId()) {
				modelsAndView = new ModelAndView(
						"redirect:/user/condominium/condominiumfees?id_condominium=" + condominium.getId());
				if (condominiumFeeService.closeCondominiumFee(condominiumFee)) {
					redirectAttributes.addFlashAttribute("result",
							new Result("Período fechado com sucesso", "success"));
				} else {
					redirectAttributes.addFlashAttribute("result", new Result("Falha ao fechar período", "error"));
				}
			} else {
				modelsAndView = new ModelAndView("errors/accessdenied");
			}
		} else {
			modelsAndView = new ModelAndView("errors/accessdenied");
		}
		return modelsAndView;
	}

	@GetMapping("/resident/condominium/condominiumfees")
	public ModelAndView returnByCondominiumIdByResident(@RequestParam int idCondominium) {
		ModelAndView modelsAndView = new ModelAndView("resident/condominiumFees");

		CondominiumResidentService condominiumResidentService = new CondominiumResidentService();
		MyUserDetails resident = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		CondominiumResident condominiumResident = condominiumResidentService
				.returnByResidentAndCondominium(resident.getId(), idCondominium);

		if (condominiumResident != null) {
			Condominium condominium = new CondominiumService().returnById(idCondominium);
			CondominiumFeeService condominiumFeeService = new CondominiumFeeService();
			List<CondominiumFee> fees = condominiumFeeService.returnByCondominiumId(idCondominium);
			modelsAndView.addObject("format", new SimpleDateFormat("dd/MM/yyyy"));
			modelsAndView.addObject("condominiumFees", fees);
			modelsAndView.addObject("condominium", condominium);
		} else {
			modelsAndView = new ModelAndView("errors/accessdenied");
		}
		return modelsAndView;
	}

	@GetMapping("/user/condominium/condominiumfee")
	public ModelAndView returnCondominiumFee(@RequestParam int idCondominiumFee) {
		ModelAndView modelsAndView = new ModelAndView("user/condominium/condominiumfee/condominiumfee");
		CondominiumFeeService condominiumFeeService = new CondominiumFeeService();
		CondominiumFee condominiumFee = condominiumFeeService.returnById(idCondominiumFee);

		CondominiumService condominiumService = new CondominiumService();
		Condominium condominium = condominiumService.returnById(condominiumFee.getIdCondominium());
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (condominium.getIdUser() == user.getId()) {

			modelsAndView.addObject("fees", new FeeService().returnByCondominiumFeeId(idCondominiumFee));
			modelsAndView.addObject("condominiumFee", condominiumFee);
			modelsAndView.addObject("condominium", condominium);
			modelsAndView.addObject("reportSumByFeeType", condominiumFeeService.reportSumFeeType(idCondominiumFee));
			modelsAndView.addObject("reportsCondominiumFeeType", condominiumFeeService.reportsCondominiumFeeType(condominium.getId(), idCondominiumFee));

		} else {
			modelsAndView = new ModelAndView("errors/accessdenied");
		}
		return modelsAndView;
	}

	@GetMapping("/resident/condominium/condominiumfee")
	public ModelAndView returnCondominiumFeeForResident(@RequestParam int idCondominiumFee) {
		ModelAndView modelsAndView = new ModelAndView("resident/viewCondominiumFee");
		CondominiumFeeService condominiumFeeService = new CondominiumFeeService();
		CondominiumFee condominiumFee = condominiumFeeService.returnById(idCondominiumFee);

		CondominiumService condominiumService = new CondominiumService();
		Condominium condominium = condominiumService.returnById(condominiumFee.getIdCondominium());
		MyUserDetails resident = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		CondominiumResident condominiumResident = new CondominiumResidentService()
				.returnByResidentAndCondominium(resident.getId(), condominium.getId());

		if (condominiumResident != null) {
			if (condominiumResident.getIdResident() == resident.getId()) {

				modelsAndView.addObject("fees", new FeeService().returnByCondominiumFeeId(idCondominiumFee));
				modelsAndView.addObject("condominiumFee", condominiumFee);
				modelsAndView.addObject("condominium", condominium);
				modelsAndView.addObject("warnings",
						new WarningService().returnByCondominiumFee(resident.getId(), idCondominiumFee));
			} else {
				modelsAndView = new ModelAndView("errors/accessdenied");
			}
		} else {
			modelsAndView = new ModelAndView("errors/accessdenied");
		}
		return modelsAndView;
	}

	@GetMapping("/user/condominium/condominiumfee/form")
	public ModelAndView addFee(@RequestParam int id_condominium, @RequestParam int id) {
		ModelAndView modelsAndView = new ModelAndView("user/condominium/condominiumfee/form");
		CondominiumService condominiumService = new CondominiumService();
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		Condominium condominium = condominiumService.returnById(id_condominium);

		if (condominium.getIdUser() == user.getId()) {
			modelsAndView.addObject("condominium", condominium);
			CondominiumFee fee;
			if (id <= 0) {
				fee = new CondominiumFee();
				fee.setIdCondominium(id_condominium);
			} else {
				CondominiumFeeService feeService = new CondominiumFeeService();
				fee = feeService.returnById(id);
			}
			modelsAndView.addObject("condominiumFee", fee);
			return modelsAndView;
		} else {
			return new ModelAndView("errors/accessdenied");
		}
	}

	@PostMapping("/user/condominium/condominiumfee/form")
	public ModelAndView addFeeForm(@ModelAttribute @Valid CondominiumFee condominiumFee, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return new ModelAndView("user/condominium/condominiumfee/form");
		}
		ModelAndView modelsAndView;
		CondominiumService condominiumService = new CondominiumService();
		Condominium condominium = condominiumService.returnById(condominiumFee.getIdCondominium());
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (condominium.getIdUser() == user.getId()) {

			CondominiumFeeService condominiumFeeService = new CondominiumFeeService();
			if (condominiumFeeService.isValidMonth(new java.sql.Date(condominiumFee.getClosingDate().getTime()),
					condominium.getId())) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(condominiumFee.getClosingDate());

				condominiumFee.setFinished(false);
				boolean bolresult = condominiumFeeService.insert(condominiumFee);
				modelsAndView = new ModelAndView("redirect:/user/dashboard");
				if (bolresult) {
					redirectAttributes.addFlashAttribute("result",
							new Result("Taxa cadastrada com sucesso", "success"));
				} else {
					redirectAttributes.addFlashAttribute("result", new Result("Falha ao cadastrar taxa", "error"));
				}
			} else {
				modelsAndView = new ModelAndView("redirect:/user/dashboard");// TODO não deveria voltar ao dashboard
																			// quando da
				// errado
				redirectAttributes.addFlashAttribute("result",
						new Result("Data de fechamento já cadastrada no condomínio", "error"));
			}
		} else {
			modelsAndView = new ModelAndView("errors/accessdenied");
		}
		return modelsAndView;
	}

}
