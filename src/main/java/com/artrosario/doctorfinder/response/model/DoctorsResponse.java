package com.artrosario.doctorfinder.response.model;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class DoctorsResponse {
    @SerializedName("data")
    private ArrayList<DoctorRest> data;

    public ArrayList<DoctorRest> getData() {
        return data;
    }

	public void setData(ArrayList<DoctorRest> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "DoctorsResponse [data=" + data + "]";
	}


}
