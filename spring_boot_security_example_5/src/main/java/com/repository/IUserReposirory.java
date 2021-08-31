package com.repository;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.model.User;

@Repository
public interface IUserReposirory extends JpaRepository<User, Long> {
	
	  @Query("SELECT u FROM User u where u.email = ?1")
	  public   User findbye(String email);
	  
	  
	  @Query("UPDATE User u SET u.failde_attempts = ?1 WHERE u.email = ?2")
	  @Modifying
	  public  void updatefailde_attempts (int fields_attempts,String email);
	  
	  
	  @Query("UPDATE User u SET  u.acount_non_locked =?1,  u.failde_attempts = ?2 , u.lock_time =?3 WHERE u.email = ?4")
	  @Modifying
	  public void updateUser(boolean accountnonlocked,int fields_attempts, Date locktime, String email );
	  
	  
	  
	  
	  
	
	  
	  

}
