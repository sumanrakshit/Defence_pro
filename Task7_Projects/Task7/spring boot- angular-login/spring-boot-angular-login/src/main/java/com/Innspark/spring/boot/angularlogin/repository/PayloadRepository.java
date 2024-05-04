package com.Innspark.spring.boot.angularlogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Innspark.spring.boot.angularlogin.entity.Payload;



@Repository
public interface PayloadRepository extends JpaRepository<Payload, Integer>{
	
}
