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
//		ModelAndView modelsAndView = new ModelAndView("user/login");
//		Admin admin = new Admin();
//		modelsAndView.addObject("admin", admin);
//		return modelsAndView;
//	}

//	@PostMapping("/user/login")
//	public ModelAndView loginAdminForm(@ModelAttribute Admin admin) {
//		AdminService service = new AdminService();
//		Admin adminLogin = service.login(admin);
//		ModelAndView modelsAndView;
//		if (null != adminLogin) {
//			modelsAndView = new ModelAndView("redirect:/user/dashboard");
//			CondominiumService condominiumService = new CondominiumService();
//			modelsAndView.addObject("condominiuns", condominiumService.list(adminLogin.getId()));
//			modelsAndView.addObject("result", new Result("Login efetuado com sucesso", "success"));
//
//			modelsAndView.addObject("admin", adminLogin);
//		} else {
//			modelsAndView = new ModelAndView("user/login");
//		}
//		return modelsAndView;
//	}
//
//	@PostMapping("/user/logout")
//	public ModelAndView logout(@ModelAttribute Admin admin) {
//		ModelAndView modelsAndView = new ModelAndView("user/logout");
//		modelsAndView.addObject("admin", null);
//
//		return modelsAndView;
//	}
//
//	@GetMapping("/user/new")
//	public ModelAndView addAdmin() {
//		ModelAndView modelsAndView = new ModelAndView("user/new");
//		Admin admin = new Admin();
//		modelsAndView.addObject("admin", admin);
//		return modelsAndView;
//	}
//
//	@PostMapping("/user/add")
//	public ModelAndView addAdminForm(@ModelAttribute @Valid Admin admin, BindingResult result) {
//		// TODO This should not be here
//		ModelAndView modelsAndView = new ModelAndView("redirect:/user/dashboard");
//		AdminService service = new AdminService();
//		service.insert(admin);
//		if (result.hasErrors()) {
//			return new ModelAndView("user/new");
//		}
//
//		return modelsAndView;
//	}


	
    @GetMapping("/admin")
    public ModelAndView admin() {
    	ModelAndView modelsAndView = new ModelAndView("admin/helloAdmin");
    	modelsAndView.addObject("user" , (MyUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		return modelsAndView;
    }

}
