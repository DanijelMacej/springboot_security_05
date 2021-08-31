package com;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.model.User;
import com.repository.IUserReposirory;
import com.service.IUserService;
import com.service.UserService;
@Component
public class CustomLoginFuliarHandler extends SimpleUrlAuthenticationFailureHandler {

	@Autowired
	IUserService service;
	@Autowired
	IUserReposirory  repository;

	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
	
		
		String email = request.getParameter("myemail");
		
		User findbyeEmail = repository.findbye(email);
		
		if (findbyeEmail !=null) {
			if (findbyeEmail.isAcountNonLocked()) {
				if (findbyeEmail.getFailde_attempts()< UserService.MAX_FAILED_ATTEMP - 1) {
					service.getUserAttempt(findbyeEmail);
				}else {
					service.LockUser(findbyeEmail);
					exception = new LockedException("Tri puta ste pogresili logovanje, nalog ce biti zakljucan na 15 min");
				}
				
			}else if (!findbyeEmail.isAcountNonLocked()) {
				if (service.unlockTime(findbyeEmail)) {
					exception = new LockedException("Vas nalog je sada otkljucan");

				}
			}
			
		}
	
		
		
		  super.setDefaultFailureUrl("/mylogin?error");
		super.onAuthenticationFailure(request, response, exception);
	}
	
	
	
	

}