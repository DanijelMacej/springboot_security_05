package com.dao;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.dto.UserRegistrationDto;
import com.model.Role;
import com.model.User;
import com.repository.IUserReposirory;

@Repository
public class UserDao implements IUserDao {
	
	
	@Autowired
	IUserReposirory repository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public User save(UserRegistrationDto userRegistrationDto) {
		
		userRegistrationDto.setAcountNonLocked(true);
		userRegistrationDto.setFailde_attempts(0);
		userRegistrationDto.setLock_time(null);
		
		String encode = passwordEncoder.encode(userRegistrationDto.getPassword());
		userRegistrationDto.setPassword(encode);
		User user = new User(userRegistrationDto.getFirstName(), userRegistrationDto.getLastname(), userRegistrationDto.getEmail(), userRegistrationDto.getPassword(),userRegistrationDto.isAcountNonLocked(),userRegistrationDto.getFailde_attempts(),userRegistrationDto.getLock_time(), Arrays.asList(new Role("ROLE_USER")));
		repository.save(user);
		
		return user;
	}
	
	
	
	
	
	
	
	
	
	

}
