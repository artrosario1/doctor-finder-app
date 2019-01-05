package com.artrosario.doctorfinder.response.model;

import com.google.gson.annotations.SerializedName;

public class Insurances {
	
    @SerializedName("insurance_provider")
    InsuranceProvider insuranceProvider;

	public InsuranceProvider getInsuranceProvider() {
		return insuranceProvider;
	}

	public void setInsuranceProvider(InsuranceProvider insuranceProvider) {
		this.insuranceProvider = insuranceProvider;
	}

	@Override
	public String toString() {
		return "Insurances [insuranceProvider=" + insuranceProvider + "]";
	}
    
    

}
