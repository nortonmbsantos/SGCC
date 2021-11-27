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

	
    @GetMapping("/admin")
    public ModelAndView admin() {
    	ModelAndView modelsAndView = new ModelAndView("admin/helloAdmin");
    	modelsAndView.addObject("user" , (MyUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		return modelsAndView;
    }

}
