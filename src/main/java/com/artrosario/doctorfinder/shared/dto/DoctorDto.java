package com.artrosario.doctorfinder.shared.dto;

import java.io.Serializable;
import java.util.List;

import com.artrosario.doctorfinder.response.model.Insurances;
import com.artrosario.doctorfinder.response.model.Practices;
import com.artrosario.doctorfinder.response.model.Profile;
import com.artrosario.doctorfinder.response.model.Specialties;

public class DoctorDto implements Serializable {

	private static final long serialVersionUID = 901243035506226384L;
	private long id;
	private String uid;
	private List<Practices> practices;
    private Profile profile;
    private List<Insurances> insurances;
    private List<Specialties> specialties;
    private UserDto favorites;
    
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public List<Practices> getPractices() {
		return practices;
	}
	public void setPractices(List<Practices> practices) {
		this.practices = practices;
	}
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
	public List<Insurances> getInsurances() {
		return insurances;
	}
	public void setInsurances(List<Insurances> insurances) {
		this.insurances = insurances;
	}
	public List<Specialties> getSpecialties() {
		return specialties;
	}
	public void setSpecialties(List<Specialties> specialties) {
		this.specialties = specialties;
	}
	public UserDto getFavorites() {
		return favorites;
	}
	public void setFavorites(UserDto favorites) {
		this.favorites = favorites;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	

}
