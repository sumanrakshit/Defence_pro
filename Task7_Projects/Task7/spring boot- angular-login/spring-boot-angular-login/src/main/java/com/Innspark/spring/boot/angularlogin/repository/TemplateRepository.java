package com.Innspark.spring.boot.angularlogin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Innspark.spring.boot.angularlogin.entity.Template;

@Repository
public interface TemplateRepository extends JpaRepository<Template, String> {
	
    Template findByName(String name);
    
}
