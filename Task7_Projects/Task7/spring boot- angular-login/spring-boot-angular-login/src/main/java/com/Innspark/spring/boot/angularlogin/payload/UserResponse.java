package com.Innspark.spring.boot.angularlogin.payload;

import java.util.List;
import java.util.Set;

import com.Innspark.spring.boot.angularlogin.entity.Group;


//Userreponse Return user data when user login with authentication  

public class UserResponse {
	private String id;
	private String username;
	private String role;
	private boolean is_FirstLogin;
	private List<String> group;
	private List<String> privilege;
	private String token;
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
//	public boolean isFirstLogin() {
//		return isFirstLogin;
//	}
//	public void setFirstLogin(boolean isFirstLogin) {
//		this.isFirstLogin = isFirstLogin;
//	}
	
	
	
	public String getRole() {
		return role;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
//	public boolean isFirstLogin() {
//		return isFirstLogin;
//	}
//	public void setFirstLogin(boolean isFirstLogin) {
//		this.isFirstLogin = isFirstLogin;
//	}
	
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isIs_FirstLogin() {
		return is_FirstLogin;
	}
	public void setIs_FirstLogin(boolean is_FirstLogin) {
		this.is_FirstLogin = is_FirstLogin;
	}
	public List<String> getPrivilege() {
		return privilege;
	}
	public void setPrivilege(List<String> privilege) {
		this.privilege = privilege;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public List<String> getGroup() {
		return group;
	}
	public void setGroup(List<String> group) {
		this.group = group;
	}
	
	
	
	public UserResponse(String id, String username, String role, boolean is_FirstLogin, List<String> group,
			List<String> privilege, String token) {
		super();
		this.id = id;
		this.username = username;
		this.role = role;
		this.is_FirstLogin = is_FirstLogin;
		this.group = group;
		this.privilege = privilege;
		this.token = token;
	}
	@Override
	public String toString() {
		return "UserResponse [id=" + id + ", username=" + username + ", role=" + role + ", is_FirstLogin="
				+ is_FirstLogin + ", group=" + group + ", privilege=" + privilege + ", token=" + token + "]";
	}
	
	
	
	
	
	
}
