package com.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.dto.UserRegistrationDto;
import com.model.Role;
import com.model.User;

public class MyCustomUserDetails implements UserDetails{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user;
	

	

	public MyCustomUserDetails(User user) {
		
		this.user =user;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
	    List<SimpleGrantedAuthority> collectRole = user.getRoles().stream().map(role -> new  SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

		return collectRole;
	}

	@Override
	public String getPassword() {
	String password = user.getPassword();
		return password;
	}

	@Override
	public String getUsername() {
	   String email = user.getEmail();
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		boolean acountNonLocked = user.isAcountNonLocked();
		return acountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
   //boolean metoda iz entity klase User
	public boolean hasRole(String roleName) {
		return user.hasRole(roleName);
	}
	
		
		
		 
	    

      
	
		
	}


