package com.artrosario.doctorfinder.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity(name="doctors")
public class DoctorEntity implements Serializable {

	private static final long serialVersionUID = 9063348298657168692L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(length=50, nullable=false)
	private String uid;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="users_id", nullable=true)
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
