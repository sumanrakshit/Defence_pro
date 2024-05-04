package com.Innspark.spring.boot.angularlogin.payload;

import java.util.List;


//When new User add  pass AddRequest as requestBody
public class AddRequest {
	private String username;
	private String name;
	private String role;
	private List<String > group;
	private String creator_id;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
//	public String getPrivelege() {
//		return privelege;
//	}
//	public void setPrivelege(String privelege) {
//		this.privelege = privelege;
//	}
	
	public List<String> getGroup() {
		return group;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
//	public int getAdduserid() {
//		return adduserid;
//	}
//	public void setAdduserid(int adduserid) {
//		this.adduserid = adduserid;
//	}
	
	public void setGroup(List<String> group) {
		this.group = group;
	}
	public String getCreator_id() {
		return creator_id;
	}
	public void setCreator_id(String creator_id) {
		this.creator_id = creator_id;
	}
	

	
	
}
