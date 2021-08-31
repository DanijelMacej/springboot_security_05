package com.customvalidator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.dto.UserRegistrationDto;

public class EmailCustomValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return UserRegistrationDto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		String email = ((UserRegistrationDto)target).getEmail();
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "emptyEmailError");
		
		
		if (!email.matches( ".+@.+\\..+")) {
			
			errors.rejectValue("email", "notValidMail");
			
		}
		
	}

}
