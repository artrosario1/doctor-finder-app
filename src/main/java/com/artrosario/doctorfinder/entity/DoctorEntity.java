package com.artrosario.doctorfinder.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.artrosario.doctorfinder.response.model.Insurances;
import com.artrosario.doctorfinder.response.model.Practices;
import com.artrosario.doctorfinder.response.model.Profile;
import com.artrosario.doctorfinder.response.model.Specialties;

@Entity(name="favorites")
public class DoctorEntity implements Serializable {

	private static final long serialVersionUID = 9063348298657168692L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=50, nullable=false)
	private String uid;

	@ManyToOne
	@JoinColumn(name="users_id")
	private UserEntity favorites;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserEntity getFavorites() {
		return favorites;
	}

	public void setFavorites(UserEntity favorites) {
		this.favorites = favorites;
	}


}
