package com.model;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private boolean acount_non_locked;
	private int failde_attempts;
	private Date lock_time;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	
	@JoinTable(
			      //name of table
			name = "users_roles",
			joinColumns = @JoinColumn(
				//foreign key(users_roles)	         //id primary user table
				name = "user_id", referencedColumnName = "id"),   
			                                    //foreign key (users_roles)     //id primary role table
			inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	
	private Collection<Role>roles;

	
	
	public User(String firstname, String lastname, String email,String password,boolean acountNonLocked,int faild_attempt,Date lock_time ,Collection<Role> roles) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.acount_non_locked = acountNonLocked;
		this.failde_attempts = faild_attempt;
		this.lock_time = lock_time;
		this.roles = roles;
	}

    
	

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}




	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
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


	public Collection<Role> getRoles() {
		return roles;
	}


	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
public boolean isAcountNonLocked() {
		return acount_non_locked;
	}




	public void setAcountNonLocked(boolean acountNonLocked) {
		this.acount_non_locked = acountNonLocked;
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




public boolean hasRole( String roleName) {
		
		Iterator<Role>iterator = roles.iterator();
		
		while (iterator.hasNext()) {
			Role rol = iterator.next();
			if (rol.getName().equals(roleName)) {
				return true;
			}
			}
		return false;

			
		}


}

	
	
