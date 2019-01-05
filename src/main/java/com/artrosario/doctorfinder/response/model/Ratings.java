package com.artrosario.doctorfinder.response.model;

import com.google.gson.annotations.SerializedName;

public class Ratings {
	@SerializedName("rating")
	private double rating;

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "Ratings [rating=" + rating + "]";
	}
	
	
	
}
