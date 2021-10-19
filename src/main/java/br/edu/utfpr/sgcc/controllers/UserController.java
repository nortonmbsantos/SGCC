package br.edu.utfpr.sgcc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.utfpr.sgcc.config.Encryptor;
import br.edu.utfpr.sgcc.models.MyUserDetails;
import br.edu.utfpr.sgcc.models.PasswordReset;
import br.edu.utfpr.sgcc.models.Result;
import br.edu.utfpr.sgcc.models.User;
import br.edu.utfpr.sgcc.models.UserUpdatePassword;
import br.edu.utfpr.sgcc.service.CondominiumService;
import br.edu.utfpr.sgcc.service.PasswordResetService;
import br.edu.utfpr.sgcc.service.UserService;

import java.util.Date;
import java.util.Random;

import javax.validation.Valid;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({ "condominiunsSideBar", "user" })
public class UserController {

	@PostMapping("/register")
	public ModelAndView register(@ModelAttribute @Valid User user, BindingResult result) {
		UserService service = new UserService();

		if (!new BCryptPasswordEncoder().matches(user.getConfirmPassword(), user.getPassword())) {
			result.addError(new FieldError("user", "password", "Senhas devem ser iguais"));
		}
		if (result.hasErrors()) {
			return new ModelAndView("register").addObject("result",
					new Result("Falha ao se cadastrar, verifique suas informações", "error"));
		}
		service.insert(user);
		return new ModelAndView("login").addObject("result",
				new Result("Usuário cadastrado com sucesso, entre com suas credenciais", "success"));
	}

	@GetMapping("/user/update")
	public ModelAndView updateUser() {
		ModelAndView modelsAndView = new ModelAndView("user/update");
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User myUser = new UserService().returnById(user.getId());
		modelsAndView.addObject("user", myUser);
		return modelsAndView;
	}

	@PostMapping("/user/update")
	public ModelAndView update(@ModelAttribute @Valid User user, BindingResult result,
			final RedirectAttributes redirectAttributes) {
		UserService service = new UserService();

//		if (!new BCryptPasswordEncoder().matches(user.getConfirmPassword(), user.getPassword())) {
//			result.addError(new FieldError("user", "password", "Senhas devem ser iguais"));
//		}

		if (result.hasErrors()) {
			return new ModelAndView("user/update").addObject("result",
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
			return new ModelAndView("redirect:/user/dashboard");
		} else {
			redirectAttributes.addFlashAttribute("result", new Result("Falha ao atualizar usuário", "error"));
			return new ModelAndView("redirect:/user/dashboard");
		}
	}

	@GetMapping("/user/updatepassword")
	public ModelAndView updateUserPassword() {
		ModelAndView modelsAndView = new ModelAndView("user/updatepassword");

		modelsAndView.addObject("userUpdatePassword", new UserUpdatePassword());
		return modelsAndView;
	}

	@PostMapping("/user/updatepassword")
	public ModelAndView updatePassword(@ModelAttribute @Valid UserUpdatePassword userUpdatePassword,
			BindingResult result, final RedirectAttributes redirectAttributes) {
		UserService service = new UserService();

		if (result.hasErrors()) {
			return new ModelAndView("user/updatepassword").addObject("result",
					new Result("Falha ao se senha", "error"));
		}

		MyUserDetails myUserDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();

		
		User user = service.returnByUserName(myUserDetails.getUsername());
		
		if (!new BCryptPasswordEncoder().matches(userUpdatePassword.getPassword(), user.getPassword())) {
			result.addError(new FieldError("user", "password", "Senha atual inválida"));
		}
		
		if(!userUpdatePassword.getNewPassword().equals(userUpdatePassword.getConfirmPassword())) {
			result.addError(new FieldError("userUpdatePassword", "newPassword", "Senhas devem ser iguais"));
		}
		
		if (result.hasErrors()) {
			return new ModelAndView("user/updatepassword").addObject("result",
					new Result("Falha ao alterar senha", "error"));
		}

		user.setPassword(userUpdatePassword.getNewPassword());
//		update password
		if (service.updatePassword(user)) {
			redirectAttributes.addFlashAttribute("result",
					new Result("Você atualizou seus dados com sucesso", "success"));
			return new ModelAndView("redirect:/user/dashboard");
		} else {
			redirectAttributes.addFlashAttribute("result", new Result("Falha ao atualizar usuário", "error"));
			return new ModelAndView("redirect:/user/dashboard");
		}
	}

	public static void main(String[] args) {
		System.out.print(new BCryptPasswordEncoder().encode("norton123"));
		
	}
	
	@GetMapping("/register")
	public ModelAndView addCondominium() {
		ModelAndView modelsAndView = new ModelAndView("register");
		modelsAndView.addObject("user", new User());
		return modelsAndView;
	}

	@GetMapping("/login")
	public ModelAndView loginMyUserDetails(@RequestParam(value = "error", required = false) String error,
			final RedirectAttributes redirectAttributes) {
		ModelAndView modelsAndView = new ModelAndView("login");
		if (error != null) {
			if (error.equals("true")) {
//				modelsAndView.addObject("errorMessge", "Erro ao autenticar, verifique suas credenciais");
				modelsAndView.addObject("result",
						new Result("Erro ao autenticar, verifique suas credenciais", "error"));
//				redirectAttributes.addFlashAttribute("result", new Result("Erro ao autenticar, verifique suas credenciais", "error"));
			}
		}
		return modelsAndView;
	}

	@GetMapping("/user/dashboard")
	public ModelAndView getDashboard() {
		ModelAndView modelsAndView = new ModelAndView("user/dashboard");
		CondominiumService condominiumService = new CondominiumService();
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		modelsAndView.addObject("condominiunsSideBar", condominiumService.listSidebar(user.getId()));
		modelsAndView.addObject("user", user);
		return modelsAndView;
	}

	@GetMapping("/user")
	public ModelAndView user() {
		ModelAndView modelsAndView = new ModelAndView("user/dashboard");
		return modelsAndView;
	}

	@GetMapping("/user/forgotpassword")
	public ModelAndView forgotPassword() {
		ModelAndView modelsAndView = new ModelAndView("forgot_password");
		return modelsAndView;
	}

	@PostMapping("/user/forgotpassword")
	public ModelAndView forgotPasswordForm(@RequestParam String username, final RedirectAttributes redirectAttributes) {
		UserService userService = new UserService();
		User user = userService.returnByUserName(username);

		if (user != null) {
			String hash = Encryptor
					.encrypt(new Date().getTime() + user.getId() + user.getEmail() + new Random().nextInt(1000));
			PasswordReset ps = new PasswordReset();
			ps.setIdUser(user.getId());
			ps.setUsed(false);
			ps.setValidationHash(hash);
			PasswordResetService passwordResetService = new PasswordResetService();
			if (passwordResetService.insert(ps) != null) {
//			Enviar email link para resgate de senha
				ModelAndView modelsAndView = new ModelAndView("redirect:/");
				redirectAttributes.addFlashAttribute("result",
						new Result("Email enviado para mudança de senha", "success"));
				return modelsAndView;
			} else {
				ModelAndView modelsAndView = new ModelAndView("forgot_password");
				modelsAndView.addObject("result",
						new Result("Falha ao gerar solicitação de recuperação de senha", "error"));
				return modelsAndView;
			}
		} else {
			ModelAndView modelsAndView = new ModelAndView("forgot_password");
			modelsAndView.addObject("result", new Result("Não foi possível localizar usuário", "error"));
			return modelsAndView;
		}
	}

	@GetMapping("/user/resetpassword")
	public ModelAndView resetPassword(@RequestParam String email, @RequestParam String validationHash) {
		ModelAndView modelsAndView = new ModelAndView("reset_password");
		modelsAndView.addObject("validationHash", validationHash);
		modelsAndView.addObject("email", email);
		return modelsAndView;
	}

	@PostMapping("/user/resetpassword")
	public ModelAndView resetPasswordForm(@RequestParam String email, @RequestParam String validationHash,
			@RequestParam String password, @RequestParam String confirmPassword) {
		ModelAndView modelsAndView = new ModelAndView("reset_password");
		modelsAndView.addObject("validationHash", validationHash);
		modelsAndView.addObject("email", email);
		if (password.length() >= 8) {
			if (password.equals(confirmPassword)) {

				PasswordResetService passwordResetService = new PasswordResetService();
				PasswordReset passwordReset = passwordResetService.returnByValidationHash(validationHash);

				if (passwordReset != null) {
					UserService userService = new UserService();
					User user = userService.returnById(passwordReset.getIdUser());

					if (user != null) {
						if (user.getEmail().equals(email)) {
//					atualiza senha
							user.setPassword(password);
							if (userService.updatePassword(user)) {
								passwordReset.setUsed(true);
								passwordReset.setUsedDate(new Date());
								passwordResetService.updateUsed(passwordReset);
								modelsAndView = new ModelAndView("login");
								modelsAndView.addObject("result",
										new Result("Email enviado para mudança de senha", "success"));
								return modelsAndView;
							}
							modelsAndView.addObject("result",
									new Result("Falha ao atualizar senha de usuário", "error"));
							return modelsAndView;
						}
					}
					modelsAndView.addObject("result", new Result("Não foi possível localizar usuário", "error"));
					return modelsAndView;
				}
				modelsAndView.addObject("result", new Result("Não foi possível validar usuário", "error"));
				return modelsAndView;
			}
			modelsAndView.addObject("result", new Result("Senhas devem ser iguais", "error"));
			return modelsAndView;
		}
		modelsAndView.addObject("result", new Result("Senha deve mínimo de 8 caracteres", "error"));
		return modelsAndView;
	}

	@GetMapping("/user/active")
	public ModelAndView activeUser() {
		ModelAndView modelsAndView = new ModelAndView("forgot_password");
		return modelsAndView;
	}

	@PostMapping("/user/active")
	public ModelAndView activeUserForm(@RequestParam String login) {
		UserService userService = new UserService();
		User user = userService.returnByUserName(login);

		if (user != null) {
//			Criar resgate de senha
//			Enviar email link para resgate de senha
			ModelAndView modelsAndView = new ModelAndView("forgot_password");
			modelsAndView.addObject("result", new Result("Email enviado para mudança de senha", "success"));
			return modelsAndView;

		} else {
			ModelAndView modelsAndView = new ModelAndView("forgot_password");
			modelsAndView.addObject("result", new Result("Não foi possível localizar usuário", "error"));
			return modelsAndView;
		}
	}
}
