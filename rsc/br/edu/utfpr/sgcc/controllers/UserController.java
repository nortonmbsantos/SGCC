package br.edu.utfpr.sgcc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.utfpr.sgcc.models.Condominium;
import br.edu.utfpr.sgcc.models.MyUserDetails;
import br.edu.utfpr.sgcc.models.Result;
import br.edu.utfpr.sgcc.models.User;
import br.edu.utfpr.sgcc.service.CondominiumService;
import br.edu.utfpr.sgcc.service.UserService;

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
	public ModelAndView loginMyUserDetails(@RequestParam(value = "error", required = false) String error) {
		ModelAndView modelAndView = new ModelAndView("login");
		if (error != null) {
			if (error.equals("true")) {
				modelAndView.addObject("errorMessge", "Erro ao autenticar, verifique suas credenciais");
			}
		} 
		return modelAndView;
	}

	@GetMapping("/user/dashboard")
	public ModelAndView getDashboard() {
		ModelAndView modelAndView = new ModelAndView("user/dashboard");
		CondominiumService condominiumService = new CondominiumService();
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		modelAndView.addObject("condominiuns", condominiumService.list(user.getId()));
		modelAndView.addObject("user", user);
		return modelAndView;
	}

	@GetMapping("/user")
	public ModelAndView user() {
		ModelAndView modelAndView = new ModelAndView("user/dashboard");
		return modelAndView;
	}
}
