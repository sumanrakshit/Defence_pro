package com.Innspark.spring.boot.angularlogin.security.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Innspark.spring.boot.angularlogin.entity.Group;
import com.Innspark.spring.boot.angularlogin.entity.User;
import com.Innspark.spring.boot.angularlogin.repository.UserRepository;

import jakarta.transaction.Transactional;



//Create UserDetailsServiceImpl class from UserDetailsService that implement LoadUserByUsername method
@Service
public class UserDetailsServiceImpl  implements UserDetailsService{
	
	
	@Autowired
	 private UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("usbbbb........."+ username);
		User user=userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User name not found " + username));
		
		
		List<Group> userGroups = user.getGroups().stream().collect(Collectors.toList());
		List<GrantedAuthority> authorities = userGroups.stream().map(r -> {
			return new SimpleGrantedAuthority(r.getName());
		}).collect(Collectors.toList());
		
//		 UserDetails userDetails =
//	                org.springframework.security.core.userdetails.User.builder()
//	                       
//	                        .username(user.getUsername())
//	                        .group(user.getGroups())
//	                        
//	                        .build();
//	
		return UserDetailsImpl.build(user);
	}

}
