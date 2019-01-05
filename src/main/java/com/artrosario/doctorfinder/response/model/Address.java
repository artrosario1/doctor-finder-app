package com.artrosario.doctorfinder.response.model;

import com.google.gson.annotations.SerializedName;

public class Address {
    @SerializedName("city")
    String city;
    @SerializedName("state")
    String state;
    @SerializedName("state_long")
    String state_long;

    @SerializedName("street")
    String street;

    @SerializedName("zip")
    String zip;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState_long() {
		return state_long;
	}

	public void setState_long(String state_long) {
		this.state_long = state_long;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Override
	public String toString() {
		return "Address [city=" + city + ", state=" + state + ", state_long=" + state_long + ", street=" + street
				+ ", zip=" + zip + "]";
	}
    
    
}
