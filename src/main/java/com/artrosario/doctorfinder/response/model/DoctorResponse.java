package com.artrosario.doctorfinder.response.model;

import java.util.ArrayList;

import com.google.gson.annotations.SerializedName;

public class DoctorResponse {
    @SerializedName("data")
    private DoctorRest data;

    public DoctorRest getData() {
        return data;
    }

	public void setData(DoctorRest data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "DoctorResponse [data=" + data + "]";
	}


    
}
