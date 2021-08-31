package com;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.service.MyUserDetailServiceImpl;

@Configuration
@EnableWebSecurity

public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter{

	
	@Autowired
	MyUserDetailServiceImpl myUserDetailService;
	
	@Autowired
	CustomLoginSuccessHandler customLoginSuccessHandler;
	
	@Autowired
	CustomLoginFuliarHandler customFuliarHandler;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
		auth.authenticationProvider(authenticationProvider());
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http.authorizeRequests()
		.antMatchers("/registration").permitAll()
		/*.antMatchers("/home").permitAll()
		.antMatchers("/admin").hasRole("ADMIN")*/
		.and()
		.formLogin()
		.loginPage("/mylogin")
		.loginProcessingUrl("/loginProcess")
		.usernameParameter("myemail")
		.passwordParameter("myPassword")

	//	.successForwardUrl("/loginProcess")
		.successHandler(customLoginSuccessHandler)
		.failureHandler(customFuliarHandler)
		
		
		.permitAll()
		
		.and().exceptionHandling().accessDeniedPage("/unauthorized")
		.and()
		.logout()
		.invalidateHttpSession(true)
		.clearAuthentication(true)
		//kada korisnik klikne na logout poslace ga mylogin stranu
		.logoutRequestMatcher( new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/mylogin?logout")
		.permitAll();
	}
	
	
	@Bean
	PasswordEncoder passwordEncoder() {
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		
		return bCryptPasswordEncoder;
	}
	
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider  authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(myUserDetailService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
	
	
	
	@Bean
	
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("message");
		return messageSource;
	}
	
	

	
	


}
