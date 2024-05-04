package com.Innspark.spring.boot.angularlogin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Innspark.spring.boot.angularlogin.entity.Group;
import com.Innspark.spring.boot.angularlogin.entity.Role;
import com.Innspark.spring.boot.angularlogin.entity.User;
 
//Repository for Role

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
	Role findByNameAndOrg(String name,String org);


}
