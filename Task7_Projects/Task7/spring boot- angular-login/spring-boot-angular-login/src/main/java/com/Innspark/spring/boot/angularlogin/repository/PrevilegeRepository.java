package com.Innspark.spring.boot.angularlogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Innspark.spring.boot.angularlogin.entity.Previlege;


//Repository for Privilege

@Repository
public interface PrevilegeRepository extends JpaRepository<Previlege, Integer> {
	

}
