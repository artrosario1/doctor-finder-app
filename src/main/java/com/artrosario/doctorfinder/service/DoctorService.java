package com.artrosario.doctorfinder.service;

import java.util.List;

import com.artrosario.doctorfinder.response.model.DoctorResponse;
import com.artrosario.doctorfinder.response.model.DoctorsResponse;
import com.artrosario.doctorfinder.shared.dto.DoctorDto;
import com.artrosario.doctorfinder.shared.dto.UserDto;

public interface DoctorService {
	List<DoctorDto> getFavoriteDoctors(String userId);
	DoctorsResponse getDoctors(String url);
	DoctorResponse getDoctorByUid(String uid);
	DoctorDto createFavorite(DoctorDto doctor, UserDto userDto);
	DoctorDto deleteFavorite(String uid);
	
}
