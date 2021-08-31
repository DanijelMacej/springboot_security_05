package com;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.service.MyCustomUserDetails;

@Component
public class CustomLoginSuccessHandler  extends SavedRequestAwareAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
	
		    MyCustomUserDetails userDetails = (MyCustomUserDetails) authentication.getPrincipal();
		    
		
              System.out.println("CustomLoginSuccessHandler");
		  
		     String contextPath = request.getContextPath();
		   
		    if (userDetails.hasRole("ROLE_USER")) {
		    	System.out.println(userDetails.hasRole("ROLE_USER"));
	    	//	response.sendRedirect("/spring_boot_security_example_5/home");
	    	      contextPath += "/home";
	    	     
                  
			}
		    
		    
		    if (userDetails.hasRole("ROLE_ADMIN")) {
		    	   
		    	//   response.sendRedirect("/spring_boot_security_example_5/admin");
		    	 contextPath += "/admin";
			}
		    
		    response.sendRedirect(contextPath);
		    
		

	}
	
	
	
	

}
