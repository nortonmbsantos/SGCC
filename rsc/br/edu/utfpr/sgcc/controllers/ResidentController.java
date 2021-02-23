package br.edu.utfpr.sgcc.controllers;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.MaskFormatter;
import javax.validation.Valid;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import br.edu.utfpr.sgcc.config.DataBaseConfig;
import br.edu.utfpr.sgcc.config.Encryptor;
import br.edu.utfpr.sgcc.daos.AdminDAOImpl;
import br.edu.utfpr.sgcc.models.Admin;
import br.edu.utfpr.sgcc.models.Fee;
import br.edu.utfpr.sgcc.models.Resident;
import br.edu.utfpr.sgcc.service.AdminService;
import br.edu.utfpr.sgcc.service.CondominiumService;
import br.edu.utfpr.sgcc.service.FeeTypeService;
import br.edu.utfpr.sgcc.service.ResidentService;
import br.edu.utfpr.sgcc.service.WarningService;

@Controller
public class ResidentController {
	
	
	@GetMapping("/user/condominium/resident")
	public ModelAndView residentView(@RequestParam int id_resident) throws ParseException {
		ModelAndView modelAndView = new ModelAndView("user/condominium/resident/resident");
		ResidentService residentService = new ResidentService();
		Resident resident = residentService.returnById(id_resident);
		modelAndView.addObject("resident", resident);
		
		CondominiumService condominiumService = new CondominiumService();
		modelAndView.addObject("condominium", condominiumService.returnById(resident.getIdCondominium()));
		
		modelAndView.addObject("format", new SimpleDateFormat("dd/MM/yyyy"));
		WarningService warningService = new WarningService();
		modelAndView.addObject("warnings", warningService.returnByResident(id_resident));
		
		return modelAndView;
	}
	
	@GetMapping("/user/condominium/residents")
	public ModelAndView residentList(@RequestParam int id_condominium) {
		ModelAndView modelAndView = new ModelAndView("user/condominium/resident/residents");
		ResidentService residentService = new ResidentService();
		modelAndView.addObject("residents", residentService.returnByCondominium(id_condominium));
		CondominiumService condominiumService = new CondominiumService();
		modelAndView.addObject("condominium", condominiumService.returnById(id_condominium));
		return modelAndView;
	}
	
}
