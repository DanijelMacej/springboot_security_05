package com.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.customvalidator.EmailCustomValidator;
import com.customvalidator.FirstNameCustomValidator;
import com.customvalidator.PasswordCustomValidator;
import com.dto.UserRegistrationDto;
import com.service.IUserService;

@Controller
//@RequestMapping("/registration")
public class UserRegistrationController {
	
	
	@Autowired
	IUserService iUserService;
	
	
	@ModelAttribute("userRegistrationDto")
	public UserRegistrationDto getUserRegistrationDto() {
		
		UserRegistrationDto dto = new UserRegistrationDto();
		return dto;
	}
	
//	@GetMapping
	@RequestMapping(value ="/registration" ,method = RequestMethod.GET)
	public String showRegistrationForm(Model model) {
		
		if (!model.containsAttribute("userRegistrationDto")) {
			
			model.addAttribute("userRegistrationDto", new UserRegistrationDto());
		}
		
		//naziv stranice u templates, kada je strana u templatesu ne moramo praviti viewResolver vec ce spring automatski prepoznati gde je strana
		return "thymeleaf/registration";
	}
	
//	@PostMapping
	@RequestMapping(value ="/registrationProcess" , method = RequestMethod.POST)
	public String registrationUserAccount(@Valid @ModelAttribute("userRegistrationDto") UserRegistrationDto userRegistrationDto,BindingResult result,RedirectAttributes redirectAttributes) {
		
	
		
		if (result.hasErrors()) {
			
			List<ObjectError> allErrors = result.getAllErrors();
			
			for (ObjectError objectError : allErrors) {
				
				System.out.println(objectError);
				
			}
			
			redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDto", result);

			redirectAttributes.addFlashAttribute("userRegistrationDto", userRegistrationDto);
			return "redirect:/registration";
			
		}
		
		
		
		iUserService.save(userRegistrationDto);
		
		return "redirect:/registration?success";
		
	}
	
	
	    @InitBinder
	    public void InitBinder(WebDataBinder binder) {
	    	
	    	FirstNameCustomValidator customValidator = new FirstNameCustomValidator();
	    	binder.addValidators(customValidator);
	    	
	    	PasswordCustomValidator passwordCustomValidator = new PasswordCustomValidator();
	    	binder.addValidators(passwordCustomValidator);
	    	
	    	EmailCustomValidator emailCustomValidator = new EmailCustomValidator();
	    	binder.addValidators(emailCustomValidator);
	    }
	

}
