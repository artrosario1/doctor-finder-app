package com.artrosario.doctorfinder.response.model;

import com.google.gson.annotations.SerializedName;

public class Practices {

    @SerializedName("lat")
    Double lat;
    @SerializedName("lon")
    Double lon;
    @SerializedName("distance")
    Double distance;
    @SerializedName("visit_address")
    Address address;
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLon() {
		return lon;
	}
	public void setLon(Double lon) {
		this.lon = lon;
	}
	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Practices [lat=" + lat + ", lon=" + lon + ", distance=" + distance + "]";
	}
    
}
