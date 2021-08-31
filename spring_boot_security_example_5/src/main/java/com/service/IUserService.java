package com.service;

import com.dto.UserRegistrationDto;
import com.model.User;

public interface IUserService {
	
	
	User save(UserRegistrationDto dto);
	
	
	void  getUserAttempt(User user);
	
	void LockUser(User user);
	
	boolean unlockTime(User user);

}
