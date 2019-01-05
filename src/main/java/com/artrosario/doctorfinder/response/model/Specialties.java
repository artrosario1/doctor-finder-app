package com.artrosario.doctorfinder.response.model;

import com.google.gson.annotations.SerializedName;

public class Specialties {
    @SerializedName("uid")
    String uid;

    @SerializedName("name")
    String name;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Specialties [uid=" + uid + ", name=" + name + "]";
	}
    
    

}
