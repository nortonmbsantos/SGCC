package br.edu.utfpr.sgcc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.utfpr.sgcc.config.Encryptor;
import br.edu.utfpr.sgcc.models.Condominium;
import br.edu.utfpr.sgcc.models.MyUserDetails;
import br.edu.utfpr.sgcc.models.PasswordReset;
import br.edu.utfpr.sgcc.models.Result;
import br.edu.utfpr.sgcc.models.User;
import br.edu.utfpr.sgcc.service.CondominiumService;
import br.edu.utfpr.sgcc.service.PasswordResetService;
import br.edu.utfpr.sgcc.service.UserService;

import java.util.Date;
import java.util.Random;

import javax.security.auth.callback.ConfirmationCallback;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import br.edu.utfpr.sgcc.models.MyUserDetails;
import br.edu.utfpr.sgcc.models.User;

@Controller
@SessionAttributes({ "condominiuns", "user" })
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

	@GetMapping("/update")
	public ModelAndView updateUser() {
		ModelAndView modelAndView = new ModelAndView("user/update");
		modelAndView.addObject("user", new User());
		return modelAndView;
	}

	@PostMapping("/update")
	public ModelAndView update(@ModelAttribute @Valid User user, BindingResult result) {
		UserService service = new UserService();

		if (!new BCryptPasswordEncoder().matches(user.getConfirmPassword(), user.getPassword())) {
			result.addError(new FieldError("user", "password", "Senhas devem ser iguais"));
		}
		if (result.hasErrors()) {
			return new ModelAndView("register").addObject("result",
					new Result("Falha ao se cadastrar, verifique suas informações", "error"));
		}
		service.update(user);
		return new ModelAndView("login").addObject("result",
				new Result("Usuário cadastrado com sucesso, entre com suas credenciais", "success"));
	}

	@GetMapping("/register")
	public ModelAndView addCondominium() {
		ModelAndView modelAndView = new ModelAndView("register");
		modelAndView.addObject("user", new User());
		return modelAndView;
	}

	@GetMapping("/login")
	public ModelAndView loginMyUserDetails(@RequestParam(value = "error", required = false) String error,
			final RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView("login");
		if (error != null) {
			if (error.equals("true")) {
//				modelAndView.addObject("errorMessge", "Erro ao autenticar, verifique suas credenciais");
				modelAndView.addObject("result", new Result("Erro ao autenticar, verifique suas credenciais", "error"));
//				redirectAttributes.addFlashAttribute("result", new Result("Erro ao autenticar, verifique suas credenciais", "error"));
			}
		}
		return modelAndView;
	}

	@GetMapping("/user/dashboard")
	public ModelAndView getDashboard() {
		ModelAndView modelAndView = new ModelAndView("user/dashboard");
		CondominiumService condominiumService = new CondominiumService();
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		modelAndView.addObject("condominiuns", condominiumService.listSidebar(user.getId()));
		modelAndView.addObject("user", user);
		return modelAndView;
	}

	@GetMapping("/user")
	public ModelAndView user() {
		ModelAndView modelAndView = new ModelAndView("user/dashboard");
		return modelAndView;
	}

	@GetMapping("/user/forgotpassword")
	public ModelAndView forgotPassword() {
		ModelAndView modelAndView = new ModelAndView("forgot_password");
		return modelAndView;
	}

	@PostMapping("/user/forgotpassword")
	public ModelAndView forgotPasswordForm(@RequestParam String username, final RedirectAttributes redirectAttributes) {
		UserService userService = new UserService();
		User user = userService.returnByUserName(username);

		if (user != null) {
			Encryptor encryptor = new Encryptor();
			String hash = encryptor
					.encrypt(new Date().getTime() + user.getId() + user.getEmail() + new Random().nextInt(1000));
			PasswordReset ps = new PasswordReset();
			ps.setIdUser(user.getId());
			ps.setUsed(false);
			ps.setValidationHash(hash);
			PasswordResetService passwordResetService = new PasswordResetService();
			if (passwordResetService.insert(ps) != null) {
//			Enviar email link para resgate de senha
				ModelAndView modelAndView = new ModelAndView("redirect:/");
				redirectAttributes.addFlashAttribute("result",
						new Result("Email enviado para mudança de senha", "success"));
				return modelAndView;
			} else {
				ModelAndView modelAndView = new ModelAndView("forgot_password");
				modelAndView.addObject("result",
						new Result("Falha ao gerar solicitação de recuperação de senha", "error"));
				return modelAndView;
			}
		} else {
			ModelAndView modelAndView = new ModelAndView("forgot_password");
			modelAndView.addObject("result", new Result("Não foi possível localizar usuário", "error"));
			return modelAndView;
		}
	}

	@GetMapping("/user/resetpassword")
	public ModelAndView resetPassword(@RequestParam String email, @RequestParam String validationHash) {
		ModelAndView modelAndView = new ModelAndView("reset_password");
		modelAndView.addObject("validationHash", validationHash);
		modelAndView.addObject("email", email);
		return modelAndView;
	}

	@PostMapping("/user/resetpassword")
	public ModelAndView resetPasswordForm(@RequestParam String email, @RequestParam String validationHash,
			@RequestParam String password, @RequestParam String confirmPassword) {
		ModelAndView modelAndView = new ModelAndView("reset_password");
		modelAndView.addObject("validationHash", validationHash);
		modelAndView.addObject("email", email);
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
								modelAndView = new ModelAndView("login");
								modelAndView.addObject("result",
										new Result("Email enviado para mudança de senha", "success"));
								return modelAndView;
							}
							modelAndView.addObject("result",
									new Result("Falha ao atualizar senha de usuário", "error"));
							return modelAndView;
						}
					}
					modelAndView.addObject("result", new Result("Não foi possível localizar usuário", "error"));
					return modelAndView;
				}
				modelAndView.addObject("result", new Result("Não foi possível validar usuário", "error"));
				return modelAndView;
			}
			modelAndView.addObject("result", new Result("Senhas devem ser iguais", "error"));
			return modelAndView;
		}
		modelAndView.addObject("result", new Result("Senha deve mínimo de 8 caracteres", "error"));
		return modelAndView;
	}

	@GetMapping("/user/active")
	public ModelAndView activeUser() {
		ModelAndView modelAndView = new ModelAndView("forgot_password");
		return modelAndView;
	}

	@PostMapping("/user/active")
	public ModelAndView activeUserForm(@RequestParam String login) {
		UserService userService = new UserService();
		User user = userService.returnByUserName(login);

		if (user != null) {
//			Criar resgate de senha
//			Enviar email link para resgate de senha
			ModelAndView modelAndView = new ModelAndView("forgot_password");
			modelAndView.addObject("result", new Result("Email enviado para mudança de senha", "success"));
			return modelAndView;

		} else {
			ModelAndView modelAndView = new ModelAndView("forgot_password");
			modelAndView.addObject("result", new Result("Não foi possível localizar usuário", "error"));
			return modelAndView;
		}
	}
}
