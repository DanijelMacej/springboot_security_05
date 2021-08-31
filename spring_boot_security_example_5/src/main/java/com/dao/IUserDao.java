package com.dao;

import com.dto.UserRegistrationDto;
import com.model.User;

public interface IUserDao {
	
	User save (UserRegistrationDto userRegistrationDto);
	
	
	
	

}
