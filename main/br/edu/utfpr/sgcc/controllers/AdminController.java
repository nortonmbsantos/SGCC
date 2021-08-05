package br.edu.utfpr.sgcc.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import br.edu.utfpr.sgcc.models.MyUserDetails;

@Controller
@SessionAttributes({ "user" })
public class AdminController {
//	@GetMapping("/user/login")
//	public ModelAndView loginAdmin() {
//		ModelAndView modelAndView = new ModelAndView("user/login");
//		Admin admin = new Admin();
//		modelAndView.addObject("admin", admin);
//		return modelAndView;
//	}

//	@PostMapping("/user/login")
//	public ModelAndView loginAdminForm(@ModelAttribute Admin admin) {
//		AdminService service = new AdminService();
//		Admin adminLogin = service.login(admin);
//		ModelAndView modelAndView;
//		if (null != adminLogin) {
//			modelAndView = new ModelAndView("redirect:/user/dashboard");
//			CondominiumService condominiumService = new CondominiumService();
//			modelAndView.addObject("condominiuns", condominiumService.list(adminLogin.getId()));
//			modelAndView.addObject("result", new Result("Login efetuado com sucesso", "success"));
//
//			modelAndView.addObject("admin", adminLogin);
//		} else {
//			modelAndView = new ModelAndView("user/login");
//		}
//		return modelAndView;
//	}
//
//	@PostMapping("/user/logout")
//	public ModelAndView logout(@ModelAttribute Admin admin) {
//		ModelAndView modelAndView = new ModelAndView("user/logout");
//		modelAndView.addObject("admin", null);
//
//		return modelAndView;
//	}
//
//	@GetMapping("/user/new")
//	public ModelAndView addAdmin() {
//		ModelAndView modelAndView = new ModelAndView("user/new");
//		Admin admin = new Admin();
//		modelAndView.addObject("admin", admin);
//		return modelAndView;
//	}
//
//	@PostMapping("/user/add")
//	public ModelAndView addAdminForm(@ModelAttribute @Valid Admin admin, BindingResult result) {
//		// TODO This should not be here
//		ModelAndView modelAndView = new ModelAndView("redirect:/user/dashboard");
//		AdminService service = new AdminService();
//		service.insert(admin);
//		if (result.hasErrors()) {
//			return new ModelAndView("user/new");
//		}
//
//		return modelAndView;
//	}


	
    @GetMapping("/admin")
    public ModelAndView admin() {
    	ModelAndView modelAndView = new ModelAndView("admin/helloAdmin");
    	modelAndView.addObject("user" , (MyUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		return modelAndView;
    }

}
