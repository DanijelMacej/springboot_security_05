package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.model.User;
import com.repository.IUserReposirory;
@Service
public class MyUserDetailServiceImpl implements UserDetailsService {

	
	@Autowired
	IUserReposirory repository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User findByEmail = repository.findbye(email);
		if (findByEmail==null) {
		    
			throw new UsernameNotFoundException("invalid username/password");
			
		}
		
	
	System.out.println(findByEmail.getRoles());
		return new MyCustomUserDetails(findByEmail) ;
	}

}
