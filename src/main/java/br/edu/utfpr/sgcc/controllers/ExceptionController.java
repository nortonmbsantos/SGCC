package br.edu.utfpr.sgcc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	@ExceptionHandler(NullPointerException.class)
	public ModelAndView handleNullPointerException(NullPointerException ex) {
		// Do something additional if required
		return new ModelAndView("error/exception");
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception ex) {
		// Do something additional if required
		return new ModelAndView("error/exception");
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ModelAndView handleResourceNotFoundException() {
		return new ModelAndView("error/404");
	}
}
