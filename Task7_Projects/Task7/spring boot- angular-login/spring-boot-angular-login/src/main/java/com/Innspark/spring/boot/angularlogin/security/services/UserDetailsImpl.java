package com.Innspark.spring.boot.angularlogin.security.services;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.Innspark.spring.boot.angularlogin.entity.Group;
import com.Innspark.spring.boot.angularlogin.entity.User;



//Create Own UserDetailsImpl class from UserDetails for set in the token more user details properties
public class UserDetailsImpl implements UserDetails{
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String username;
	private String name;
	private String password;
	private String role;
	private  boolean isFirstLogin;
	
	private Collection<? extends GrantedAuthority> authorities;
//	private GrantedAuthority authority;
	
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}
	
	public UserDetailsImpl(String id, String name,String username, String password, String role, boolean isFirstLogin,  Collection<? extends GrantedAuthority> authorities)
	{
		
		this.id=id;
		this.name=name;
		this.username=username;
		this.password=password;
		this.role=role;
		this.isFirstLogin=isFirstLogin; 
		this.authorities=authorities;
//		this.authorities=authorities;
				
		
	}
	
	
	public static UserDetailsImpl build(User user)
	{
//		List<GrantedAuthority> authori ties= new ArrayList();
//		authorities.add(user.getPrivelege());
//		List<Group> grp=user.getGroups().stream().collect(Collector.toList());
		List<Group> userGroups = user.getGroups().stream().collect(Collectors.toList());
		List<GrantedAuthority> authorities = userGroups.stream().map(r -> {
			return new SimpleGrantedAuthority(r.getName());
		}).collect(Collectors.toList());
		
//		List<GrantedAuthority>authorities=(List<GrantedAuthority>) user.getGroups().stream().map(g ->new SimpleGrantedAuthority(g.getName()) );
		
		return new UserDetailsImpl(user.getId(), user.getUsername(), user.getName(), user.getPassword(), user.getRole(), user.isIs_firstLogin() ,authorities);
	}
	
	
	public String getId()
	{
		return id;
		
	}
	
	

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
	
	public String getRole() {
		// TODO Auto-generated method stub
		return role;
	}
	
	public boolean isFirstLogin() {
		return isFirstLogin;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	

}
