package br.edu.utfpr.sgcc.controllers;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.utfpr.sgcc.models.Condominium;
import br.edu.utfpr.sgcc.models.CondominiumEntryRequest;
import br.edu.utfpr.sgcc.models.MyUserDetails;
import br.edu.utfpr.sgcc.models.Result;
import br.edu.utfpr.sgcc.models.User;
import br.edu.utfpr.sgcc.models.UserUpdatePassword;
import br.edu.utfpr.sgcc.service.BookingService;
import br.edu.utfpr.sgcc.service.CondominiumEntryRequestService;
import br.edu.utfpr.sgcc.service.CondominiumResidentService;
import br.edu.utfpr.sgcc.service.CondominiumService;
import br.edu.utfpr.sgcc.service.UserService;
import br.edu.utfpr.sgcc.service.WarningService;

@Controller
@SessionAttributes({ "user" })
public class ResidentLoginController {

	@GetMapping("/resident/dashboard")
	public ModelAndView getDashboard() {
		ModelAndView modelsAndView = new ModelAndView("resident/dashboard");
		MyUserDetails resident = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		modelsAndView.addObject("user", resident);

		CondominiumResidentService condominiumResidentService = new CondominiumResidentService();
		modelsAndView.addObject("condominiuns",
				condominiumResidentService.returnCondominiunsByResident(resident.getId()));

		BookingService bookingService = new BookingService();
		modelsAndView.addObject("bookings", bookingService.pendingBookingsByResident(resident.getId()));

		WarningService warningService = new WarningService();
		modelsAndView.addObject("warnings", warningService.returnByResident(resident.getId()));

		return modelsAndView;
	}

	@GetMapping("/resident/condominium/entry/new")
	public ModelAndView addCondominiumResident() {
		ModelAndView modelsAndView = new ModelAndView("resident/entryCondominium");
		return modelsAndView;
	}

	@PostMapping("/resident/condominium/entry/add")
	public ModelAndView addCondominiumResidentForm(@RequestParam String code,
			final RedirectAttributes redirectAttributes) {
		CondominiumService service = new CondominiumService();
		Condominium condominium = service.returnByCode(code);
		if (condominium != null) {
			MyUserDetails resident = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();

			CondominiumEntryRequest cer = new CondominiumEntryRequest();
			cer.setIdCondominium(condominium.getId());
			cer.setIdResident(resident.getId());
			cer.setRequestDate(new Date());
			CondominiumEntryRequestService requestService = new CondominiumEntryRequestService();
			CondominiumEntryRequest cerVal = requestService.returnByCondominiumAndResident(cer.getIdCondominium(),
					cer.getIdResident());

			if (cerVal != null) {
				if (cerVal.getResponseDate() != null) {
					if (cerVal.isAccepted()) {
						redirectAttributes.addFlashAttribute("result", new Result(
								"O usuário já faz parte do condomínio: " + condominium.getName(), "success"));
						return new ModelAndView("redirect:/resident/dashboard");
					} else {
						if (requestService.insert(cer)) {
							redirectAttributes.addFlashAttribute("result", new Result(
									"Solicitação para " + condominium.getName() + " enviada com sucesso", "success"));
							return new ModelAndView("redirect:/resident/dashboard");
						} else {
							return new ModelAndView("resident/entryCondominium").addObject("result",
									new Result("Falha ao enviar solicitação ao condomínio", "error"));
						}
					}
				} else {
					redirectAttributes.addFlashAttribute("result", new Result(
							"O usuário já possui uma solicitação pendente para " + condominium.getName(), "success"));
					return new ModelAndView("redirect:/resident/dashboard");
				}
			} else {
				if (requestService.insert(cer)) {
					redirectAttributes.addFlashAttribute("result", new Result(
							"Solicitação para " + condominium.getName() + " enviada com sucesso", "success"));
					return new ModelAndView("redirect:/resident/dashboard");
				} else {
					return new ModelAndView("resident/entryCondominium").addObject("result",
							new Result("Falha ao enviar solicitação ao condomínio", "error"));
				}
			}
//			if (cerVal == null || (!cerVal.isAccepted() && cerVal.getResponseDate() != null)) {
//				if (requestService.insert(cer)) {
//					redirectAttributes.addFlashAttribute("result", new Result(
//							"Solicitação para " + condominium.getName() + " enviada com sucesso", "success"));
//					return new ModelAndView("redirect:/resident/dashboard");
//				} else {
//					return new ModelAndView("resident/entryCondominium").addObject("result",
//							new Result("Falha ao enviar solicitação ao condomínio", "error"));
//				}
//			} else if (cerVal != null && cerVal.getResponseDate() == null) {
//				if (cerVal.isAccepted()) {
//					redirectAttributes.addFlashAttribute("result",
//							new Result("Você já participa de " + condominium.getName(), "success"));
//					return new ModelAndView("redirect:/resident/dashboard");
//				} else {
//					redirectAttributes.addFlashAttribute("result", new Result(
//							"Solicitação para " + condominium.getName() + " enviada com sucesso", "success"));
//							return new ModelAndView("redirect:/resident/dashboard");
//				}
//			} else {
//				return new ModelAndView("resident/entryCondominium").addObject("result",
//						new Result("Falha ao enviar solicitação ao condomínio", "error"));
//			}
		} else {
			return new ModelAndView("resident/entryCondominium").addObject("result",
					new Result("Condomínio não encontrado", "error"));
		}

	}

	@GetMapping("/resident/update")
	public ModelAndView updateUser() {
		ModelAndView modelsAndView = new ModelAndView("resident/update");
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User myUser = new UserService().returnById(user.getId());
		modelsAndView.addObject("user", myUser);
		return modelsAndView;
	}

	@PostMapping("/resident/update")
	public ModelAndView update(@ModelAttribute @Valid User user, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		UserService service = new UserService();

//		if (!new BCryptPasswordEncoder().matches(user.getConfirmPassword(), user.getPassword())) {
//			result.addError(new FieldError("user", "password", "Senhas devem ser iguais"));
//		}

		if (result.hasErrors()) {
			return new ModelAndView("resident/update").addObject("result",
					new Result("Falha ao se atualizar, verifique suas informações", "error"));
		}
//		MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		User myUser = new UserService().returnById(user.getId());
//		user.setUserName(myUser.getUserName());
//		user.setActive(myUser.isActive());
//		user.setPassword(user.getPassword());
//		user.setRoles(myUser.getRoles());
//		user.setEmail(myUser.getEmail());
//		user.setId(myUser.getId());
		if (service.update(user)) {
			redirectAttributes.addFlashAttribute("result",
					new Result("Você atualizou seus dados com sucesso", "success"));
			return new ModelAndView("redirect:/resident/dashboard");
		} else {
			redirectAttributes.addFlashAttribute("result", new Result("Falha ao atualizar usuário", "error"));
			return new ModelAndView("redirect:/resident/dashboard");
		}
	}

	@GetMapping("/resident/updatepassword")
	public ModelAndView updateUserPassword() {
		ModelAndView modelsAndView = new ModelAndView("resident/updatepassword");

		modelsAndView.addObject("userUpdatePassword", new UserUpdatePassword());
		return modelsAndView;
	}

	@PostMapping("/resident/updatepassword")
	public ModelAndView updatePassword(@ModelAttribute @Valid UserUpdatePassword userUpdatePassword,
			BindingResult result, final RedirectAttributes redirectAttributes) {
		UserService service = new UserService();

		if (result.hasErrors()) {
			return new ModelAndView("resident/updatepassword").addObject("result",
					new Result("Falha ao se senha", "error"));
		}

		MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();

		User user = service.returnByUserName(myUserDetails.getUsername());

		if (!new BCryptPasswordEncoder().matches(userUpdatePassword.getPassword(), user.getPassword())) {
			result.addError(new FieldError("userUpdatePassword", "password", "Senha atual inválida"));
		}

		if (!userUpdatePassword.getNewPassword().equals(userUpdatePassword.getConfirmPassword())) {
			result.addError(new FieldError("userUpdatePassword", "newPassword", "Senhas devem ser iguais"));
		}

		if (result.hasErrors()) {
			return new ModelAndView("resident/updatepassword").addObject("result",
					new Result("Falha ao alterar senha", "error"));
		}

		user.setPassword(userUpdatePassword.getNewPassword());
//		update password
		if (service.updatePassword(user)) {
			redirectAttributes.addFlashAttribute("result",
					new Result("Você atualizou seus dados com sucesso", "success"));
			return new ModelAndView("redirect:/resident/dashboard");
		} else {
			redirectAttributes.addFlashAttribute("result", new Result("Falha ao atualizar usuário", "error"));
			return new ModelAndView("redirect:/resident/dashboard");
		}
	}

	public static void main(String[] args) {
		System.out.print(new BCryptPasswordEncoder().encode("norton123"));

	}

}
