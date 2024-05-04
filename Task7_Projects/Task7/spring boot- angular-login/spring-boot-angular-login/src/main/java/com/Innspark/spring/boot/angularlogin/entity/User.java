package com.Innspark.spring.boot.angularlogin.entity;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*@NoArgsConstructor
@AllArgsConstructor
>>>>>>> Stashed changes
@Getter
@Setter*/
@Entity
@Table(name="userinfo")

//User has properties id, username, password(system generate), privelege(Role), isFirstLogin(By default true), group(List of group Link with many to many relationship) 
public class User {
	
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int id;
	private String id;
	
	@Column(nullable = false, unique = true)
	private String username;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	 private String password;
	@Column(nullable=true)
	private String role;
	@Column(nullable = false)
	private boolean is_firstLogin;
	@Column(nullable = false)
	private String ipaddress;
	@Column(nullable = false)
	private Long created_date;
	@Column(nullable = false)
	private String creator_id;
	
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name="user_groups", joinColumns = @JoinColumn(name="user_id"),
	inverseJoinColumns = @JoinColumn(name="group_id"))
	@Column(nullable = false)
	private Set<Group> groups=new HashSet<>();
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
		

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Set<Group> getGroups() {
		return groups;
	}
	public void setGroups(Set<Group> groups) {
		this.groups = groups;
		
	}
	public User( String username, String password) {
		super();
		
		this.username = username;
		this.password = password;
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

	
	
public Long getCreated_date() {
		return created_date;
	}
	public boolean isIs_firstLogin() {
	return is_firstLogin;
}
public void setIs_firstLogin(boolean is_firstLogin) {
	this.is_firstLogin = is_firstLogin;
}
public String getIpaddress() {
	return ipaddress;
}
public void setIpaddress(String ipaddress) {
	this.ipaddress = ipaddress;
}
	public void setCreated_date(Long created_date) {
		this.created_date = created_date;
	}
//	public int getCreator_id() {
//		return creator_id;
//	}
//	public void setCreator_id(int creator_id) {
//		this.creator_id = creator_id;
//	}
	
	

//public User(int id, String username, String name, String password, String role, boolean is_firstLogin,
//			String ipaddress, Long created_date, int creator_id, Set<Group> groups) {
//		super();
//		this.id = id;
//		this.username = username;
//		this.name = name;
//		this.password = password;
//		this.role = role;
//		this.is_firstLogin = is_firstLogin;
//		this.ipaddress = ipaddress;
//		this.created_date = created_date;
//		this.creator_id = creator_id;
//		this.groups = groups;
//	}
	
	

	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCreator_id() {
		return creator_id;
	}
	public void setCreator_id(String creator_id) {
		this.creator_id = creator_id;
	}
//	public User(String id, String username, String name, String password, String role, boolean is_firstLogin,
//		String ipaddress, Long created_date, int creator_id, Set<Group> groups) {
//	super();
//	this.id = id;
//	this.username = username;
//	this.name = name;
//	this.password = password;
//	this.role = role;
//	this.is_firstLogin = is_firstLogin;
//	this.ipaddress = ipaddress;
//	this.created_date = created_date;
//	this.creator_id = creator_id;
//	this.groups = groups;
//}
	
	
	
	
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", name=" + name + ", password=" + password + ", role="
				+ role + ", is_firstLogin=" + is_firstLogin + ", ipaddress=" + ipaddress + ", created_date="
				+ created_date + ", creator_id=" + creator_id + ", groups=" + groups + "]";
	}
public User(String id, String username, String name, String password, String role, boolean is_firstLogin,
		String ipaddress, Long created_date, String creator_id, Set<Group> groups) {
	super();
	this.id = id;
	this.username = username;
	this.name = name;
	this.password = password;
	this.role = role;
	this.is_firstLogin = is_firstLogin;
	this.ipaddress = ipaddress;
	this.created_date = created_date;
	this.creator_id = creator_id;
	this.groups = groups;
}
	
	
	
	

	

	
	 
	 
	

}
