package com.artrosario.doctorfinder.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.artrosario.doctorfinder.shared.dto.UserDto;

public interface UserService extends UserDetailsService{
	UserDto createUser(UserDto user);
	List<UserDto> getUsers();
	UserDto getUser(String email);
	UserDto getUserByUserId(String userId);
	UserDto updateUser(String userId, UserDto userDto);
	void deleteUser(String userId);
}
