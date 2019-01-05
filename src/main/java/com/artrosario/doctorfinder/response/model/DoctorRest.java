package com.artrosario.doctorfinder.response.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class DoctorRest {
	@SerializedName("uid")
	private String uid;
	
    @SerializedName("practices")
	private List<Practices> practices;
    
    @SerializedName("profile")
    private Profile profile;
    
    @SerializedName("insurances")
    private List<Insurances> insurances;
    
    @SerializedName("ratings")
    private List<Ratings> ratings;
    
    @SerializedName("specialties")
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
	public List<Specialties> getSpecialties() {
		return specialties;
	}
	public void setSpecialties(List<Specialties> specialties) {
		this.specialties = specialties;
	}
	public List<Ratings> getRatings() {
		return ratings;
	}
	public void setRatings(List<Ratings> ratings) {
		this.ratings = ratings;
	}
	@Override
	public String toString() {
		return "DoctorRest [uid=" + uid + ", practices=" + practices + ", profile=" + profile + ", insurances="
				+ insurances + ", ratings=" + ratings + ", specialties=" + specialties + "]";
	}
	



}
