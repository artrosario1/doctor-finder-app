package com.artrosario.doctorfinder.request.model;

import java.util.List;

import com.artrosario.doctorfinder.response.model.Insurances;
import com.artrosario.doctorfinder.response.model.Practices;
import com.artrosario.doctorfinder.response.model.Profile;
import com.artrosario.doctorfinder.response.model.Ratings;
import com.artrosario.doctorfinder.response.model.Specialties;

public class DoctorCreateRequestModel {

	private String uid;
	
	private List<Practices> practices;
    
    private Profile profile;
    
    private List<Insurances> insurances;
    
    private List<Ratings> ratings;
    
    private List<Specialties> specialties;
    
    

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

	public List<Ratings> getRatings() {
		return ratings;
	}

	public void setRatings(List<Ratings> ratings) {
		this.ratings = ratings;
	}

	public List<Specialties> getSpecialties() {
		return specialties;
	}

	public void setSpecialties(List<Specialties> specialties) {
		this.specialties = specialties;
	}

	@Override
	public String toString() {
		return "DoctorCreateRequestModel [uid=" + uid + ", practices=" + practices + ", profile=" + profile
				+ ", insurances=" + insurances + ", ratings=" + ratings + ", specialties=" + specialties + "]";
	}
    
    
    
}
