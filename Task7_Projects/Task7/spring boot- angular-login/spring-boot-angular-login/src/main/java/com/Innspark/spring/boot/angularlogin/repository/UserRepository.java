package com.Innspark.spring.boot.angularlogin.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Innspark.spring.boot.angularlogin.entity.Group;
import com.Innspark.spring.boot.angularlogin.entity.User;
import org.springframework.web.bind.annotation.RequestParam;


//Repository for user
@Repository
public interface UserRepository extends JpaRepository<User, String>  {
	
	Optional<User> findByUsername(String username);
	Optional<User> findByName(String name);

	//************Added by Mohit****************
	
	   @Query("SELECT u FROM User u JOIN u.groups g WHERE g.name IN :groupNames")
	    List<User> findByGroups(@Param("groupNames") List<String> groupNames);
	}



