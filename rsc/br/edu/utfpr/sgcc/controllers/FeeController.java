package br.edu.utfpr.sgcc.controllers;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.validation.Valid;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.utfpr.sgcc.models.Admin;
import br.edu.utfpr.sgcc.models.Condominium;
import br.edu.utfpr.sgcc.models.CondominiumFee;
import br.edu.utfpr.sgcc.models.Fee;
import br.edu.utfpr.sgcc.models.MyUserDetails;
import br.edu.utfpr.sgcc.models.Resident;
import br.edu.utfpr.sgcc.models.Result;
import br.edu.utfpr.sgcc.service.CondominiumFeeService;
import br.edu.utfpr.sgcc.service.CondominiumService;
import br.edu.utfpr.sgcc.service.FeeService;
import br.edu.utfpr.sgcc.service.FeeTypeService;

@Controller
public class FeeController {
	@GetMapping("/user/condominium/fees")
	public ModelAndView listCondominiumFees(@RequestParam int id_condominium, @RequestParam int id_condominium_fee) {
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		CondominiumService condominiumService = new CondominiumService();
		Condominium condominium = condominiumService.returnById(id_condominium);
		CondominiumFee condominiumFee = new CondominiumFeeService().returnById(id_condominium_fee);
		if (condominium.getIdUser() == user.getId() && condominiumFee.getId_condominium() == condominium.getId()) {
			ModelAndView modelAndView = new ModelAndView("user/condominium/fee/fees");
			FeeService feeService = new FeeService();
			modelAndView.addObject("fees", feeService.returnByCondominiumFeeId(id_condominium_fee));
			modelAndView.addObject("format", new SimpleDateFormat("dd/MM/yyyy"));
			modelAndView.addObject("condominium", condominium);
			return modelAndView;
		}
		return new ModelAndView("user/condominium/fee/fees"); // TODO retornar página de erro
	}

	@GetMapping("/user/condominium/fee")
	public ModelAndView showFee(@RequestParam int id_fee) {
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		CondominiumService condominiumService = new CondominiumService();
		Fee fee = new FeeService().returnById(id_fee);
		CondominiumFee condominiumFee = new CondominiumFeeService().returnById(fee.getIdCondominiumFee());

		Condominium condominium = condominiumService.returnById(condominiumFee.getId_condominium());
		if (condominium.getIdUser() == user.getId() && condominiumFee.getId_condominium() == condominium.getId()) {
			ModelAndView modelAndView = new ModelAndView("user/condominium/fee/fee");
			FeeService feeService = new FeeService();
			modelAndView.addObject("format", new SimpleDateFormat("dd/MM/yyyy"));
			modelAndView.addObject("condominiumFee", condominiumFee);
			modelAndView.addObject("condominium", condominium);
			return modelAndView;
		}
		return new ModelAndView("user/condominium/fee/fee"); // TODO retornar página de erro
	}

	@GetMapping("/resident/viewCondominiumFees")
	public ModelAndView listResidentCondominiumFees(@RequestParam int id_condominium_fee) {
		ModelAndView modelAndView = new ModelAndView("resident/viewCondominiumFee");
		FeeService feeService = new FeeService();
		CondominiumService condominiumService = new CondominiumService();
		modelAndView.addObject("fees", feeService.returnByCondominiumFeeId(id_condominium_fee));
		modelAndView.addObject("format", new SimpleDateFormat("dd/MM/yyyy"));
		return modelAndView;
	}

	@GetMapping("/user/condominium/fee/forminstalment")
	public ModelAndView formFeeInstallment(@RequestParam int id_condominium_fee, @RequestParam int id_fee) {
		ModelAndView modelAndView = new ModelAndView("user/condominium/fee/forminstalment");
		CondominiumService condominiumService = new CondominiumService();

		CondominiumFeeService condominiumFeeService = new CondominiumFeeService();
		CondominiumFee condominiumFee = condominiumFeeService.returnById(id_condominium_fee);
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		Condominium condominium = condominiumService.returnById(condominiumFee.getId_condominium());
		if (condominium.getIdUser() == user.getId()) {
			modelAndView.addObject("condominium", condominium);
			modelAndView.addObject("idCondominiumFee", id_condominium_fee);
			Fee fee;
			FeeService feeService = new FeeService();
			if (id_fee <= 0) {
				fee = new Fee();
				fee.setIdCondominiumFee(id_condominium_fee);
			} else {
				fee = feeService.returnById(id_fee);
			}
			modelAndView.addObject("fee", fee);
			FeeTypeService feeTypeService = new FeeTypeService();
			modelAndView.addObject("feeTypes", feeTypeService.list());
			modelAndView.addObject("fathers", feeService.returnFathers(condominium.getId()));

			return modelAndView;
		} else {
			return new ModelAndView("errors/accessdenied");
		}
	}

	@PostMapping("/user/condominium/fee/forminstalment")
	public ModelAndView feeFormInstalmentSend(@ModelAttribute @Valid Fee fee, BindingResult result,
			final RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return new ModelAndView("user/condominium/fee/forminstalment");
		}

		ModelAndView modelAndView = new ModelAndView("redirect:/user/dashboard");
		FeeService feeService = new FeeService();
		CondominiumService condominiumService = new CondominiumService();

		CondominiumFeeService condominiumFeeService = new CondominiumFeeService();
		CondominiumFee condominiumFee = condominiumFeeService.returnById(fee.getIdCondominiumFee());
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		Condominium condominium = condominiumService.returnById(condominiumFee.getId_condominium());

		if (condominium.getIdUser() == user.getId()) {
			if (!condominiumFee.isFinished()) {
				Fee lastByFather = feeService.returnLastByFather(fee.getFather());
				fee.setDescription(lastByFather.getDescription());
				fee.setCurrentInstallment(lastByFather.getCurrentInstallment() + 1);
				fee.setInstallments(lastByFather.getInstallments());
				fee.setMonthly(lastByFather.isMonthly());
				fee.setIdFeeType(lastByFather.getIdFeeType());

				boolean bolresult = feeService.save(fee);

				if (bolresult) {
					redirectAttributes.addFlashAttribute("result",
							new Result("Taxa cadastrada com sucesso", "success"));
				} else {
					redirectAttributes.addFlashAttribute("result", new Result("Falhar ao cadastrar taxa", "error"));
				}

			} else {
				return new ModelAndView("user/condominium/fee/forminstalment").addObject("result", new Result("Não foi possível realizar ação: Taxa de condomínio fechada", "error"));
			}
		} else {
			return new ModelAndView("errors/accessdenied");
		}
		return modelAndView;
	}

	@GetMapping("/user/condominium/fee/form")
	public ModelAndView formFee(@RequestParam int id_condominium_fee, @RequestParam int id_fee) {
		ModelAndView modelAndView = new ModelAndView("user/condominium/fee/form");
		CondominiumService condominiumService = new CondominiumService();

		CondominiumFeeService condominiumFeeService = new CondominiumFeeService();
		CondominiumFee condominiumFee = condominiumFeeService.returnById(id_condominium_fee);
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		Condominium condominium = condominiumService.returnById(condominiumFee.getId_condominium());
		if (condominium.getIdUser() == user.getId()) {
			modelAndView.addObject("condominium", condominium);
			modelAndView.addObject("idCondominiumFee", id_condominium_fee);
			Fee fee;
			FeeService feeService = new FeeService();
			if (id_fee <= 0) {
				fee = new Fee();
				fee.setIdCondominiumFee(id_condominium_fee);
				fee.setCurrentInstallment(1);
				fee.setInstallments(1);
			} else {
				fee = feeService.returnById(id_fee);
			}
			modelAndView.addObject("fee", fee);
			FeeTypeService feeTypeService = new FeeTypeService();
			modelAndView.addObject("feeTypes", feeTypeService.list());
			modelAndView.addObject("fathers", feeService.returnFathers(condominium.getId()));

			return modelAndView;
		} else {
			return new ModelAndView("errors/accessdenied");
		}
	}

	@PostMapping("/user/condominium/fee/form")
	public ModelAndView feeFormSend(@ModelAttribute @Valid Fee fee, BindingResult result,
			final RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			return new ModelAndView("user/condominium/fee/form");
		}

		ModelAndView modelAndView = new ModelAndView("redirect:/user/dashboard");
		FeeService feeService = new FeeService();
		CondominiumService condominiumService = new CondominiumService();

		CondominiumFeeService condominiumFeeService = new CondominiumFeeService();
		CondominiumFee condominiumFee = condominiumFeeService.returnById(fee.getIdCondominiumFee());
		MyUserDetails user = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		Condominium condominium = condominiumService.returnById(condominiumFee.getId_condominium());

		if (condominium.getIdUser() == user.getId()) {
			if (fee.getId() <= 0) {
				fee.setCurrentInstallment(1);
				boolean bolresult = feeService.save(fee);
				if (bolresult) {
					redirectAttributes.addFlashAttribute("result",
							new Result("Taxa cadastrada com sucesso", "success"));
				} else {
					redirectAttributes.addFlashAttribute("result", new Result("Falhar ao cadastrar taxa", "error"));
				}
			} else {
				boolean bolresult = feeService.saveOrUpdate(fee);
				if (bolresult) {
					redirectAttributes.addFlashAttribute("result", new Result("Taxa alterada com sucesso", "success"));
				} else {
					redirectAttributes.addFlashAttribute("result", new Result("Falhar ao alterar taxa", "error"));
				}
			}
		} else {
			return new ModelAndView("errors/accessdenied");
		}
		return modelAndView;
	}

}
