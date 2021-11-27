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
import br.edu.utfpr.sgcc.models.CondominiumEntryRequest;
import br.edu.utfpr.sgcc.models.CondominiumResident;
import br.edu.utfpr.sgcc.models.MyUserDetails;
import br.edu.utfpr.sgcc.models.Result;
import br.edu.utfpr.sgcc.service.BookingService;
import br.edu.utfpr.sgcc.service.CondominiumEntryRequestService;
import br.edu.utfpr.sgcc.service.CondominiumFeeService;
import br.edu.utfpr.sgcc.service.CondominiumResidentService;
import br.edu.utfpr.sgcc.service.CondominiumService;
import br.edu.utfpr.sgcc.service.FeeService;

@Controller
public class CondominiumController {
	@GetMapping("/user/condominiuns")
	public ModelAndView listCondominiuns() {
		ModelAndView modelsAndView = new ModelAndView("user/condominium/condominiuns");
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		CondominiumService service = new CondominiumService();
		modelsAndView.addObject("condominiuns", service.list(user.getId()));
		return modelsAndView;
	}

	@GetMapping("/user/condominium")
	public ModelAndView show(@RequestParam int id) {
		ModelAndView modelsAndView = new ModelAndView("user/condominium/condominium");
		CondominiumService service = new CondominiumService();
		Condominium condominium = service.returnById(id);
		if (condominium != null) {
			MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			if (condominium.getIdUser() == user.getId()) {
				modelsAndView.addObject("condominium", condominium);
				modelsAndView.addObject("format", new SimpleDateFormat("dd/MM/yyyy"));
				CondominiumFeeService condominiumFeeService = new CondominiumFeeService();

				modelsAndView.addObject("reportByClosingDate",
						condominiumFeeService.reportByClosingDate(condominium.getId()));
				modelsAndView.addObject("reportByFeeType", condominiumFeeService.reportByFeeType(condominium.getId()));
				modelsAndView.addObject("acceptedBookings",
						new BookingService().acceptedBookingsByCondominium(condominium.getId()));
				modelsAndView.addObject("feesDueDate", new FeeService().dahsboardFeesDueDate(condominium.getId()));
				modelsAndView.addObject("reportLastFeeValue",
						condominiumFeeService.reportLastCondominiumFeeTotalValue(condominium.getId()));
				modelsAndView.addObject("reportAverageFeeType",
						new CondominiumFeeService().reportAverageFeeType(condominium.getId()));
			} else {
				modelsAndView = new ModelAndView("errors/accessdenied");
			}
		}
		return modelsAndView;
	}

	@GetMapping("/resident/condominiuns")
	public ModelAndView listCondominiunsResident() {
		ModelAndView modelsAndView = new ModelAndView("resident/condominiuns");
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		CondominiumService service = new CondominiumService();
		modelsAndView.addObject("condominiuns", service.listForResidents(user.getId()));
		return modelsAndView;
	}

	@GetMapping("/resident/condominium")
	public ModelAndView showResident(@RequestParam int id) {
		ModelAndView modelsAndView = new ModelAndView("resident/condominium");
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		CondominiumResidentService condominiumResidentService = new CondominiumResidentService();
		CondominiumResident condominiumResident = condominiumResidentService
				.returnByResidentAndCondominium(user.getId(), id);

		if (condominiumResident != null) {
			CondominiumService condominiumService = new CondominiumService();
			Condominium condominium = condominiumService.returnById(id);
			if (condominium != null) {
				modelsAndView.addObject("condominium", condominium);
				modelsAndView.addObject("format", new SimpleDateFormat("dd/MM/yyyy"));
				CondominiumFeeService condominiumFeeService = new CondominiumFeeService();

				modelsAndView.addObject("reportByClosingDate",
						condominiumFeeService.reportByClosingDate(condominium.getId()));
				modelsAndView.addObject("reportByFeeType", condominiumFeeService.reportByFeeType(condominium.getId()));
				modelsAndView.addObject("acceptedBookings",
						new BookingService().acceptedBookingsByCondominium(condominium.getId()));
				modelsAndView.addObject("feesDueDate", new FeeService().dahsboardFeesDueDate(condominium.getId()));
				modelsAndView.addObject("reportLastFeeValue",
						condominiumFeeService.reportLastCondominiumFeeTotalValue(condominium.getId()));
				modelsAndView.addObject("reportAverageFeeType",
						new CondominiumFeeService().reportAverageFeeType(condominium.getId()));
			} else {
				modelsAndView = new ModelAndView("errors/accessdenied");
			}
		} else {
			modelsAndView = new ModelAndView("errors/accessdenied");
		}
		return modelsAndView;
	}

//	@GetMapping("/user/condominium/resident/new")
//	public ModelAndView addCondominiumResident(@RequestParam int idCondominium) {
//		ModelAndView modelsAndView = new ModelAndView("user/condominium/resident/new");
//		modelsAndView.addObject("condominium", new CondominiumService().returnById(idCondominium));
//		return modelsAndView;
//	}
//
//	@PostMapping("/user/condominium/resident/add")
//	public ModelAndView addCondominiumResidentForm(@RequestParam String residentUserName,
//			@RequestParam int idCondominium) {
//		UserService service = new UserService();
//		User resident = service.returnByUserName(residentUserName);
//		if (resident != null) {
//			if (resident.getRoles().equals("ROLE_RESIDENT")) {
//				CondominiumEntryRequest cer = new CondominiumEntryRequest();
//				cer.setIdCondominium(idCondominium);
//				cer.setIdResident(resident.getId());
//				cer.setRequestDate(new Date());
//				CondominiumEntryRequestService requestService = new CondominiumEntryRequestService();
//				if (requestService.insert(cer)) {
//					return new ModelAndView("user/dashboard").addObject("result",
//							new Result("Solicita��o para " + residentUserName + " enviada com sucesso", "success"));
//				} else {
//					return new ModelAndView("user/condominium/resident/new").addObject("result",
//							new Result("Falha ao enviar solicita��o ao usu�rio", "error"));
//				}
//			} else {
//				return new ModelAndView("user/condominium/resident/new").addObject("result",
//						new Result("Usu�rio indispon�vel", "error"));
//			}
//		} else {
//			return new ModelAndView("user/condominium/resident/new").addObject("result",
//					new Result("Usu�rio n�o encontrado", "error"));
//		}
//	}

	@PostMapping("user/condominium/accept")
	public ModelAndView acceptEntry(@ModelAttribute CondominiumEntryRequest cRequest,
			final RedirectAttributes redirectAttributes) {
		CondominiumEntryRequestService service = new CondominiumEntryRequestService();
		cRequest = service.returnById(cRequest.getId());
		CondominiumService condominiumService = new CondominiumService();
		Condominium condominium = condominiumService.returnById(cRequest.getIdCondominium());
		ModelAndView modelsAndView = new ModelAndView(
				"redirect:/user/condominium/entries?idcondominium=" + condominium.getId());
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (condominium.getIdUser() == user.getId()) {
			if (service.accept(cRequest.getId())) {

				redirectAttributes.addFlashAttribute("result", new Result("Acesso ao condomínio liberado", "success"));
			} else {
				redirectAttributes.addFlashAttribute("result",
						new Result("Falha ao liberar acesso ao condomínio", "error"));
			}
		} else {
			return new ModelAndView("errors/accessdenied");
		}

		return modelsAndView;
	}

	@PostMapping("user/condominium/refuse")
	public ModelAndView refuseEntry(@ModelAttribute CondominiumEntryRequest cRequest,
			final RedirectAttributes redirectAttributes) {

		CondominiumEntryRequestService service = new CondominiumEntryRequestService();
		cRequest = service.returnById(cRequest.getId());
		CondominiumService condominiumService = new CondominiumService();
		Condominium condominium = condominiumService.returnById(cRequest.getIdCondominium());
		ModelAndView modelsAndView = new ModelAndView(
				"redirect:/user/condominium/entries?idcondominium=" + condominium.getId());
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (condominium.getIdUser() == user.getId()) {
			if (service.refuse(cRequest.getId())) {
				redirectAttributes.addFlashAttribute("result", new Result("Acesso ao condomínio recusado", "success"));
			} else {
				redirectAttributes.addFlashAttribute("result",
						new Result("Falha ao recusar acesso ao condomínio", "error"));
			}
		}

		return modelsAndView;
	}

	@GetMapping("/user/condominium/new")
	public ModelAndView addCondominium() {
		ModelAndView modelsAndView = new ModelAndView("user/condominium/form");
		Condominium condominium = new Condominium();
		modelsAndView.addObject("condominium", condominium);
		return modelsAndView;
	}

	@PostMapping("/user/condominium/add")
	public ModelAndView addCondominiumForm(@ModelAttribute @Valid Condominium condominium, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		ModelAndView modelsAndView = new ModelAndView("user/condominium/new");
		CondominiumService service = new CondominiumService();
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		condominium.setIdUser(user.getId());
		if (result.hasErrors()) {
			return new ModelAndView("user/condominium/new");
		}
		condominium = service.insert(condominium);
		if (condominium != null) {
			modelsAndView = new ModelAndView("redirect:/user/condominium?id=" + condominium.getId());
			redirectAttributes.addFlashAttribute("result", new Result("Condomínio criado com sucesso", "success"));
		} else {
			redirectAttributes.addFlashAttribute("result", new Result("Falha ao cadastrar o condomínio", "success"));
		}
		return modelsAndView;
	}

	@GetMapping("/user/condominium/update")
	public ModelAndView updateCondominium(@RequestParam int id) {
		ModelAndView modelsAndView = new ModelAndView("user/condominium/form");
		Condominium condominium = new CondominiumService().returnById(id);
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (condominium.getIdUser() == user.getId()) {
			modelsAndView.addObject("condominium", condominium);
		} else {
			return new ModelAndView("errors/accessdenied");
		}
		return modelsAndView;
	}

	@PostMapping("/user/condominium/form")
	public ModelAndView updateCondominiumForm(@ModelAttribute @Valid Condominium condominium, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		ModelAndView modelsAndView = new ModelAndView("redirect:/user/condominium?id=" + condominium.getId());
		CondominiumService service = new CondominiumService();
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Condominium condominiumVal = service.returnById(condominium.getId());
		if (condominium.getIdUser() == condominiumVal.getIdUser()) {
			if (user.getId() == condominiumVal.getIdUser()) {
				if (result.hasErrors()) {
					return new ModelAndView("user/condominium/update");
				} else {
					service.update(condominium);
					redirectAttributes.addFlashAttribute("result",
							new Result("Condominio atualizado com sucesso", "success"));
				}
				return modelsAndView;
			} else {
				return new ModelAndView("errors/accessdenied");
			}
		} else {
			return new ModelAndView("errors/accessdenied");
		}
	}

	@GetMapping("/user/condominium/entries")
	public ModelAndView condominiumEntries(@RequestParam int idcondominium) {
		ModelAndView modelsAndView = new ModelAndView("user/condominium/resident/entries");
		CondominiumService service = new CondominiumService();
		Condominium condominium = service.returnById(idcondominium);
		if (condominium != null) {
			MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			if (condominium.getIdUser() == user.getId()) {
				modelsAndView.addObject("condominium", condominium);
				CondominiumEntryRequestService condominiumEntryRequestService = new CondominiumEntryRequestService();

				modelsAndView.addObject("entries",
						condominiumEntryRequestService.listAvailableByCondominium(idcondominium));
			} else {
				modelsAndView = new ModelAndView("errors/accessdenied");
			}
		}
		return modelsAndView;
	}

}
