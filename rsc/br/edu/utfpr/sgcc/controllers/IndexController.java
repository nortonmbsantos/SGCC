package br.edu.utfpr.sgcc.controllers;

import java.sql.SQLException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import br.edu.utfpr.sgcc.config.DataBaseConfig;
import br.edu.utfpr.sgcc.daos.AdminDAOImpl;
import br.edu.utfpr.sgcc.models.Admin;

@Controller
public class IndexController {
	@GetMapping("/")
	public ModelAndView welcome() throws SQLException {
		ModelAndView modelAndView = new ModelAndView("index");
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataBaseConfig.class);
		
		return modelAndView;
	}
}
