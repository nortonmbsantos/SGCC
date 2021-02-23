package br.edu.utfpr.sgcc.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionController {

//	@ExceptionHandler(Exception.class)
//	public ModelAndView exceptionHandler(Exception ex) {
////		if(ex.getMessage().equals("Missing session attribute 'admin' of type Admin")) {
////			return  new ModelAndView("redirect:/admin/login");
////		}
////		if(ex.getMessage().equals("Missing session attribute 'resident' of type Resident")) {
////			return  new ModelAndView("redirect:/resident/login");
////		}
//		return  new ModelAndView("errors/accessdenied");
//	}
//	
}
