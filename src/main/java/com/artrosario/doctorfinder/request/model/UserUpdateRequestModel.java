package com.artrosario.doctorfinder.request.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.core.userdetails.UserDetails;

public class UserUpdateRequestModel{
	@NotBlank(message="First name cannot be blank")
	@Size(min=2, message="First Name must not be less than 2 characters")
	private String firstName;
	@NotBlank(message="Last name cannot be blank")
	@Size(min=2, message="Last Name must not be less than 2 characters")
	private String lastName;
	@NotBlank(message="Email cannot be blank")
	@Email
	private String email;

	private String password;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
}
