package com.artrosario.doctorfinder.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.artrosario.doctorfinder.entity.DoctorEntity;
import com.artrosario.doctorfinder.entity.UserEntity;
import com.artrosario.doctorfinder.exception.UserServiceException;
import com.artrosario.doctorfinder.repos.DoctorRepository;
import com.artrosario.doctorfinder.repos.UserRepository;
import com.artrosario.doctorfinder.response.model.DoctorResponse;
import com.artrosario.doctorfinder.response.model.DoctorsResponse;
import com.artrosario.doctorfinder.service.DoctorService;
import com.artrosario.doctorfinder.shared.dto.DoctorDto;
import com.artrosario.doctorfinder.shared.dto.UserDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class DoctorServiceImpl implements DoctorService {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	DoctorRepository doctorRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<DoctorDto> getFavoriteDoctors(String userId) {
		List<DoctorDto> returnValue = new ArrayList<>();
		ModelMapper modelMapper = new ModelMapper();

		UserEntity userEntity = userRepository.findByUserId(userId);
		if(userEntity == null) return returnValue;
		
		Iterable<DoctorEntity> doctors = doctorRepository.findAllByFavorites(userEntity);
		for(DoctorEntity doctorEntity:doctors) {
			returnValue.add(modelMapper.map(doctorEntity, DoctorDto.class));
		}

		return returnValue;
	}

	@Override
	public DoctorsResponse getDoctors(String url) {
		
		ResponseEntity<String> resp = restTemplate.getForEntity(url, String.class);
	    Gson gson = new GsonBuilder().create();
	    DoctorsResponse doctors = gson.fromJson(resp.getBody(), DoctorsResponse.class);
	    
		
		return doctors;
	}

	@Override
	public DoctorResponse getDoctorByUid(String uid) {
		UriComponentsBuilder builder;
		builder = UriComponentsBuilder.newInstance()
				.scheme("https").host("api.betterdoctor.com")
				.path("2016-03-01/")
				.path("doctors/")
		        .path(uid)
		        
		        .queryParam("user_key", "CODE_SAMPLES_KEY_9d3608187");
		String url = builder.toUriString();
		
		
		ResponseEntity<String> resp = restTemplate.getForEntity(url, String.class);
	    Gson gson = new GsonBuilder().create();
	    DoctorResponse doctor = gson.fromJson(resp.getBody(), DoctorResponse.class);
		return doctor;
	}

	@Override
	public DoctorDto createFavorite(DoctorDto doctor,UserDto user) {
		
		UserEntity userEntity = new UserEntity();
		DoctorEntity doctorEntity = new DoctorEntity();
		BeanUtils.copyProperties(doctor, doctorEntity);
	    BeanUtils.copyProperties(user, userEntity);
		doctorEntity.setFavorites(userEntity);
		DoctorEntity storedFavorites = doctorRepository.save(doctorEntity);
		DoctorDto returnValue = new DoctorDto();
		BeanUtils.copyProperties(storedFavorites, returnValue);
		
		return returnValue;
	}

	@Override
	public DoctorDto deleteFavorite(String uid) {
		
		DoctorEntity doctorEntity = doctorRepository.findByUid(uid);
		
		doctorRepository.delete(doctorEntity);		
		return null;
	}

}
