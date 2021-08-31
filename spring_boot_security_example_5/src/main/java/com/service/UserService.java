package com.service;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dao.IUserDao;
import com.dto.UserRegistrationDto;
import com.model.User;
import com.repository.IUserReposirory;

@Service
@Transactional
public class UserService implements IUserService {
	
	public static final int MAX_FAILED_ATTEMP = 3;
	
	private static final int LOCK_TIME_DURACTION = 15 * 60 * 1000;

	@Autowired
	IUserReposirory repository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	IUserDao dao;
	
	@Override
	public User save(UserRegistrationDto dto) {
		
		User user = dao.save(dto);
		return user;
	}

	@Override
	public void getUserAttempt(User user) {
		
	int attempts = user.getFailde_attempts() + 1;
			
	repository.updatefailde_attempts(attempts, user.getEmail());
			
			
			}

	@Override
	public void LockUser(User user) {
		user.setAcountNonLocked(false);
		user.setLock_time(new Date());
		repository.save(user);
		
	}

	@Override
	public boolean unlockTime(User user) {
	
		 long locktime = user.getLock_time().getTime();
		 long currentTimeMillis = System.currentTimeMillis();
		 
		 if (locktime + LOCK_TIME_DURACTION < currentTimeMillis) {
			 
			 user.setAcountNonLocked(true);
			 user.setFailde_attempts(0);
			 user.setLock_time(null);
			 
			// repository.save(user);
			 repository.updateUser(user.isAcountNonLocked(), user.getFailde_attempts(), user.getLock_time(), user.getEmail());
			 return true;
			
		}
		
		
		return false;
	}
	
	
	
	
	
	
	
	
	
		
	}

	
	
	
	
	
	


