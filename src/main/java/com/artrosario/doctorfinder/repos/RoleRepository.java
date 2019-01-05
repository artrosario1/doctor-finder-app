package com.artrosario.doctorfinder.repos;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.artrosario.doctorfinder.entity.RoleEntity;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Long>  {
	Optional<RoleEntity> findById(Long Id);

}
