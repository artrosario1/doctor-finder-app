package com.artrosario.doctorfinder.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.artrosario.doctorfinder.entity.DoctorEntity;
import com.artrosario.doctorfinder.entity.UserEntity;

@Repository
public interface DoctorRepository extends  CrudRepository<DoctorEntity, Long> {
	List<DoctorEntity> findAllByFavorites(UserEntity userEntity);
	DoctorEntity findByUid(String uid);

	
}
