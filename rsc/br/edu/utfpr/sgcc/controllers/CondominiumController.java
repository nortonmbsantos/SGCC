package br.edu.utfpr.sgcc.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.utfpr.sgcc.models.Admin;
import br.edu.utfpr.sgcc.models.Condominium;
import br.edu.utfpr.sgcc.models.CondominiumEntryRequest;
import br.edu.utfpr.sgcc.models.MyUserDetails;
import br.edu.utfpr.sgcc.models.Report;
import br.edu.utfpr.sgcc.models.Result;
import br.edu.utfpr.sgcc.models.User;
import br.edu.utfpr.sgcc.service.CondominiumEntryRequestService;
import br.edu.utfpr.sgcc.service.CondominiumFeeService;
import br.edu.utfpr.sgcc.service.CondominiumService;
import br.edu.utfpr.sgcc.service.UserService;

@Controller
public class CondominiumController {
	@GetMapping("/user/condominiuns")
	public ModelAndView listCondominiuns() {
		ModelAndView modelAndView = new ModelAndView("user/condominium/condominiuns");
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		CondominiumService service = new CondominiumService();
		modelAndView.addObject("condominiuns", service.list(user.getId()));
		return modelAndView;
	}

	@GetMapping("/user/condominium")
	public ModelAndView show(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView("user/condominium/condominium");
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
				modelAndView = new ModelAndView("errors/accessdenied");
			}
		}
		return modelAndView;
	}

//	@GetMapping("/user/condominium/resident/new")
//	public ModelAndView addCondominiumResident(@RequestParam int idCondominium) {
//		ModelAndView modelAndView = new ModelAndView("user/condominium/resident/new");
//		modelAndView.addObject("condominium", new CondominiumService().returnById(idCondominium));
//		return modelAndView;
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
//							new Result("Solicitação para " + residentUserName + " enviada com sucesso", "success"));
//				} else {
//					return new ModelAndView("user/condominium/resident/new").addObject("result",
//							new Result("Falha ao enviar solicitação ao usuário", "error"));
//				}
//			} else {
//				return new ModelAndView("user/condominium/resident/new").addObject("result",
//						new Result("Usuário indisponível", "error"));
//			}
//		} else {
//			return new ModelAndView("user/condominium/resident/new").addObject("result",
//					new Result("Usuário não encontrado", "error"));
//		}
//	}

	@PostMapping("user/condominium/accept")
	public ModelAndView acceptEntry(@ModelAttribute CondominiumEntryRequest cRequest, final RedirectAttributes redirectAttributes) {
		CondominiumEntryRequestService service = new CondominiumEntryRequestService();
		cRequest = service.returnById(cRequest.getId());
		CondominiumService condominiumService = new CondominiumService();
		Condominium condominium = condominiumService.returnById(cRequest.getIdCondominium());
		ModelAndView modelAndView = new ModelAndView(
				"redirect:/user/condominium/entries?idcondominium=" + condominium.getId());
		modelAndView.addObject("condominium", condominium);
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (condominium.getIdUser() == user.getId()) {
			if (service.accept(cRequest.getId())) {
				
				redirectAttributes.addFlashAttribute("result", new Result("Acesso ao condomínio liberado", "success"));
			} else {
				redirectAttributes.addFlashAttribute("result", new Result("Falha ao liberar acesso ao condomínio", "error"));
			}
		} else {
			return new ModelAndView("errors/accessdenied");
		}

		return modelAndView;
	}

	@PostMapping("user/condominium/refuse")
	public ModelAndView refuseEntry(@ModelAttribute CondominiumEntryRequest cRequest, final RedirectAttributes redirectAttributes) {

		CondominiumEntryRequestService service = new CondominiumEntryRequestService();
		cRequest = service.returnById(cRequest.getId());
		CondominiumService condominiumService = new CondominiumService();
		Condominium condominium = condominiumService.returnById(cRequest.getIdCondominium());
		ModelAndView modelAndView = new ModelAndView(
				"redirect:/user/condominium/entries?idcondominium=" + condominium.getId());
		modelAndView.addObject("condominium", condominium);
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (condominium.getIdUser() == user.getId()) {
			if (service.refuse(cRequest.getId())) {
				redirectAttributes.addFlashAttribute("result", new Result("Acesso ao condomínio recusado", "success"));
			} else {
				redirectAttributes.addFlashAttribute("result", new Result("Falha ao recusar acesso ao condomínio", "error"));
			}
		}

		return modelAndView;
	}

	@GetMapping("/user/condominium/new")
	public ModelAndView addCondominium() {
		ModelAndView modelAndView = new ModelAndView("user/condominium/new");
		Condominium condominium = new Condominium();
		modelAndView.addObject("condominium", condominium);
		return modelAndView;
	}

	@PostMapping("/user/condominium/add")
	public ModelAndView addCondominiumForm(@ModelAttribute @Valid Condominium condominium, BindingResult result, final RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("user/condominium/new");
		CondominiumService service = new CondominiumService();
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		condominium.setIdUser(user.getId());
		if (result.hasErrors()) {
			return new ModelAndView("user/condominium/new");
		}
		condominium = service.insert(condominium);
		if (condominium != null) {
			modelAndView = new ModelAndView("redirect:/user/condominium?id=" + condominium.getId());
			redirectAttributes.addFlashAttribute("result", new Result("Condomínio criado com sucesso", "success"));
		} else {
			redirectAttributes.addFlashAttribute("result", new Result("Falha ao cadastrar o condomínio", "success"));			
		}
		return modelAndView;
	}

	@GetMapping("/user/condominium/update")
	public ModelAndView updateCondominium(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView("user/condominium/update");
		Condominium condominium = new CondominiumService().returnById(id);
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (condominium.getIdUser() == user.getId()) {
			modelAndView.addObject("condominium", condominium);
		} else {
			return new ModelAndView("errors/accessdenied");
		}
		return modelAndView;
	}

	@PostMapping("/user/condominium/update")
	public ModelAndView updateCondominiumForm(@ModelAttribute @Valid Condominium condominium, BindingResult result, final RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("redirect:/user/condominiuns");
		CondominiumService service = new CondominiumService();
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Condominium condominiumVal = service.returnById(condominium.getId());
		if (condominium.getIdUser() == condominiumVal.getIdUser()) {
			if (user.getId() == condominiumVal.getIdUser()) {
				if (result.hasErrors()) {
					return new ModelAndView("user/condominium/update");
				} else {
					service.update(condominium);
					redirectAttributes.addFlashAttribute("result", new Result("Condominio atualizado com sucesso", "success"));
				}
				return modelAndView;
			} else {
				return new ModelAndView("errors/accessdenied");
			}
		} else {
			return new ModelAndView("errors/accessdenied");
		}
	}

	@GetMapping("/user/condominium/entries")
	public ModelAndView condominiumEntries(@RequestParam int idcondominium) {
		ModelAndView modelAndView = new ModelAndView("user/condominium/resident/entries");
		CondominiumService service = new CondominiumService();
		Condominium condominium = service.returnById(idcondominium);
		if (condominium != null) {
			MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

			if (condominium.getIdUser() == user.getId()) {
				modelAndView.addObject("condominium", condominium);
				CondominiumEntryRequestService condominiumEntryRequestService = new CondominiumEntryRequestService();

				modelAndView.addObject("entries",
						condominiumEntryRequestService.listAvailableByCondominium(idcondominium));
			} else {
				modelAndView = new ModelAndView("errors/accessdenied");
			}
		}
		return modelAndView;
	}

}
