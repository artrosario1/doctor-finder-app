package com.artrosario.doctorfinder.response.model;

import com.google.gson.annotations.SerializedName;

public class Profile {

    @SerializedName("first_name")
    String firstName;

    @SerializedName("middle_name")
    String middleName;

    @SerializedName("last_name")
    String lastName;

    @SerializedName("title")
    String title;

    @SerializedName("image_url")
    String imageUrl;

    @SerializedName("gender")
    String gender;
    
    @SerializedName("bio")
    String bio;


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	@Override
	public String toString() {
		return "Profile [firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName + ", title="
				+ title + ", imageUrl=" + imageUrl + ", gender=" + gender + ", bio=" + bio + "]";
	}


    
}
