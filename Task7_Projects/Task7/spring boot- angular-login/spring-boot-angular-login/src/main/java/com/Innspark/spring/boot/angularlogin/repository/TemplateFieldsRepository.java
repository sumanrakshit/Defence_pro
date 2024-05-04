package com.Innspark.spring.boot.angularlogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Innspark.spring.boot.angularlogin.entity.TemplateFields;
import java.util.*;
public interface TemplateFieldsRepository  extends JpaRepository<TemplateFields, String>{
	
	//TemplateFields findByDecoyId(String decoyId);
	List<TemplateFields> findByDecoyId(String decoyId);
	
}
