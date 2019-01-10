package com.artrosario.doctorfinder.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.artrosario.doctorfinder.entity.UserEntity;
import com.artrosario.doctorfinder.exception.UserServiceException;
import com.artrosario.doctorfinder.repos.UserRepository;
import com.artrosario.doctorfinder.service.UserService;
import com.artrosario.doctorfinder.shared.dto.UserDto;
import com.artrosario.doctorfinder.utils.Utils;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	Utils utils;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	/**
	 * Get Users from Database
	 */
	@Override
	public List<UserDto> getUsers() {
		List<UserDto> returnValue = new ArrayList<>();
		Iterable<UserEntity> users = userRepository.findAll();
		for(UserEntity userEntity:users) {
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(userEntity, userDto);
			returnValue.add(userDto);
		}
		return returnValue;
	}
	
	/**
	 * Use email to return new User with encrypted password for security
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		UserEntity userEntity = userRepository.findByEmail(email);
		
		if(userEntity == null) throw new UsernameNotFoundException(email);
		
		return new User(userEntity.getEmail(),userEntity.getEncryptedPassword(), new ArrayList<>());
  
	}

	/**
	 * Get user from database by email
	 */
	@Override
	public UserDto getUser(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		if(userEntity == null) throw new UsernameNotFoundException(email);

		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;
	}
	
	/**
	 * Get user in database by UserId
	 */
	@Override
	public UserDto getUserByUserId(String userId) {
		UserDto returnValue = new UserDto();
		UserEntity userEntity = userRepository.findByUserId(userId);
		if(userEntity == null) throw new UsernameNotFoundException("No record found with userId: "+ userId);
		
		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;
	}
	
	/**
	 * Create a new user in database using Dto provided
	 */
	@Override
	public UserDto createUser(UserDto user) {
		
		/**
		 * Checks if email is already  in database
		 */
		if(userRepository.findByEmail(user.getEmail()) != null) throw new UserServiceException("Record already exists");
		
		
		/**
		 * Map dto properties into user entity
		 */
		UserEntity userEntity = new UserEntity();
		BeanUtils.copyProperties(user, userEntity);
		//ModelMapper modelMapper = new ModelMapper();
		//UserEntity userEntity = modelMapper.map(user, UserEntity.class);
	
		/**
		 * Create a separate UserId to put into database for added security
		 */
		String publicUserId = utils.generateUserId(30);
		
		/**
		 * Create public userId and encryptPassword
		 */
		userEntity.setUserId(publicUserId);
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		/**
		 * Store in database
		 */
		UserEntity storedUserDetails = userRepository.save(userEntity);
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(storedUserDetails, returnValue);
		
		//UserDto returnValue = modelMapper.map(storedUserDetails, UserDto.class);

		return returnValue;
	}
	
	
	/**
	 * Update user details with userId and dto
	 */
	@Override
	public UserDto updateUser(String userId, UserDto userDto) {
		/**
		 * Find in database the user entity with provided userId 
		 */
		UserDto returnValue = new UserDto();
		UserEntity userEntity = userRepository.findByUserId(userId);
		/**
		 * Check if it exists
		 */
		if(userEntity == null) throw new UserServiceException("No record found with userId: "+ userId);
		
		/**
		 * if it does, map userEntity details into dto to return
		 */
		userEntity.setFirstName(userDto.getFirstName());
		userEntity.setLastName(userDto.getLastName());
		UserEntity updatedUserDetails = userRepository.save(userEntity);
		BeanUtils.copyProperties(updatedUserDetails, returnValue);

		return returnValue;
	}
	
	/**
	 * Delete user in database
	 */
	@Override
	public void deleteUser(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		
		if(userEntity == null) throw new UserServiceException("No record found with userId: "+ userId);
		userRepository.delete(userEntity);		
	}




}
