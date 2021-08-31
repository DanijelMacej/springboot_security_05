package com.customvalidator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.dto.UserRegistrationDto;

public class FirstNameCustomValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
	
		return UserRegistrationDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		
		String firstname = ((UserRegistrationDto)target).getFirstName();
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "emptyFirstNameError");
		
		int min = 2;
		int max = 40;
		
		if (firstname.length()<min || firstname.length()>max) {
			
			errors.rejectValue("firstName", "firstNamelengthError");
			
		}
		
		//Samo string prolazi
		String s1 = firstname.trim();
		
		 if (!s1.matches("[a-zA-Z]*")) {
				
			  errors.rejectValue("firstName", "firstNameOnlyString");
		
		 }

	}

}
