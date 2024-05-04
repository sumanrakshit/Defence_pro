package com.Innspark.spring.boot.angularlogin.service;

import java.util.List;

import java.util.Optional;

import java.util.Set;

import javax.security.auth.login.AccountNotFoundException;

import com.Innspark.spring.boot.angularlogin.entity.*;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Repository;


import com.Innspark.spring.boot.angularlogin.entity.Group;
import com.Innspark.spring.boot.angularlogin.entity.Previlege;
import com.Innspark.spring.boot.angularlogin.entity.Role;
import com.Innspark.spring.boot.angularlogin.entity.Template;
import com.Innspark.spring.boot.angularlogin.entity.User;

import com.Innspark.spring.boot.angularlogin.security.services.UserDetailsImpl;
import com.Innspark.spring.boot.angularlogin.payload.AddRequest;




@Repository
public interface UserService {
	
	public User addUser(AddRequest user);
//	public Boolean loginUser(String username, String password);
	public UserDetailsImpl loginUser(User user)  ;
	public List<User> getAllUser();
	public User getById(String id);
	public User updateUser( String id,AddRequest request);
	public String deleteuser(String id);
	
	public void changePassword(String id , User user);
	
	public List<Role> getAllRole();
	public Role addRoles(Role role);
	public String editrole(String id, Role role);
	public String deleterole(String id);
	public Set<String> getUniqueRole();
	public Role getUserPrivilege(String role,String group);
	
	public Previlege addPrivilege(Previlege previlege);
	public List<Previlege> getAllPvg();
	
	
	public boolean isPayload(String role, String group);
	public boolean isRoleAccess(String role, String group);
	public boolean isUserAccess(String role, String group);
	
	
	
	public Template addTemplate(Template template);
	public Template getTemplate();

	
	//************Added by Mohit****************
	public List<User> getUserByOrg(List<String> orgName);
	
	



    List<User> fetchListuser(List<String> Org);
}
