package com.dto;

import java.util.Date;

//klasa koja nam sluzi iskljucivo za registraciju gde cemo validirati formu
//entity klasa ne moze sluziti za registraciju ona nam sluzi da jpaRepository komunicira sa bazom
//jer je to hibernatow interface
//
public class UserRegistrationDto {
	
	
	
	private String firstName;
	private String lastname;
	private String email;
	private String password;
	private boolean acountNonLocked;
	private int failde_attempts;
	private Date lock_time;

	
	
	
	
	
	
	public UserRegistrationDto() {
	
	
	}
	/*public UserRegistrationDto(String firstname, String lastname, String email, String password,boolean acountNonLocked, int fields_attempt, Date lock_time) {
		super();
		this.firstName = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.acountNonLocked = acountNonLocked;
		this.failde_attempts = fields_attempt;
		this.lock_time = lock_time;
	}*/
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAcountNonLocked() {
		return acountNonLocked;
	}
	public void setAcountNonLocked(boolean acountNonLocked) {
		this.acountNonLocked = acountNonLocked;
	}
	public int getFailde_attempts() {
		return failde_attempts;
	}
	public void setFailde_attempts(int failde_attempts) {
		this.failde_attempts = failde_attempts;
	}
	public Date getLock_time() {
		return lock_time;
	}
	public void setLock_time(Date lock_time) {
		this.lock_time = lock_time;
	}
	
	
	

}
