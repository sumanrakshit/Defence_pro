package com.Innspark.spring.boot.angularlogin.security.jwt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;
import org.hibernate.bytecode.internal.bytebuddy.PrivateAccessorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.Innspark.spring.boot.angularlogin.security.services.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;


//Filter the token for  per request 
@Component
@Configuration
public class AuthTokenFilter  extends OncePerRequestFilter{

	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		Map<String ,Object> errors=new HashMap<>();
		response.setHeader("Access-Control-Allow-Origin", "*");
		 response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		 response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
		 response.setHeader("Access-Control-Allow-Credentials", "true");
	     response.setHeader("Access-Control-Expose-Headers", "Authorization");
	      response.addHeader("Access-Control-Expose-Headers", "responseType");
	      response.addHeader("Access-Control-Expose-Headers", "observe");
	      System.out.println("Request Method: "+request.getMethod());
	      
//	      if(request.getMethod().equals("OPTIONS"))
//	      {
//	    	  
//	    	  
//	    	  response.setHeader("Access-Control-Allow-Origin", "*");
//	          response.setHeader("Access-Control-Allow-Methods", "POST,GET,DELETE,PUT");
//	          response.setHeader("Access-Control-Max-Age", "3600");
//	      }
//	      if (!(request.getMethod().equalsIgnoreCase("OPTIONS"))) {
//	          try {
//	              filterChain.doFilter(request, response);
//	          } catch(Exception e) {
//	              e.printStackTrace();
//	          }
//	      } 
//	      else {
//          System.out.println("Pre-flight");
//          response.setHeader("Access-Control-Allow-Origin", "*");
//          response.setHeader("Access-Control-Allow-Methods", "POST,GET,DELETE,PUT");
//          response.setHeader("Access-Control-Max-Age", "3600");
//          response.setHeader("Access-Control-Allow-Headers", "Access-Control-Expose-Headers,Authorization, content-type,access-control-request-headers,access-control-request-method,accept,origin,authorization,x-requested-with,responseType,observe");
//          response.setStatus(HttpServletResponse.SC_OK);
//      }
//	   
		
		// TODO Auto-generated method stub
	      
	    	  
	    
		 try {
			 HttpSession session = request.getSession();
			 System.out.println("session id " +session.getId());
	            String accessToken = jwtUtils.resolveToken(request);
	            if (accessToken == null ) {
	            	System.out.println("check");
	                filterChain.doFilter(request, response);
	                return  ;
	                
	            }
//	            System.out.println("token : "+accessToken);
	            Claims claims = jwtUtils.resolveClaims(request);

	            if(claims != null & jwtUtils.validateClaims(claims)){
	                String username = claims.getSubject();
//	                System.out.println("Username : " + username);
	                Authentication authentication =
	                        new UsernamePasswordAuthenticationToken(username,"",new ArrayList<>());
	                SecurityContextHolder.getContext().setAuthentication(authentication);
	                // System.out.println("cdbncxn...........");
	            }
				filterChain.doFilter(request, response);
		 }
	            catch(Exception e)
	            {
	            	errors.put("message", "Authentication Error");
	                errors.put("details",e.getMessage());
	                response.setStatus(HttpStatus.FORBIDDEN.value());
	                response.setContentType(MediaType.APPLICATION_JSON_VALUE);

	                objectMapper.writeValue(response.getWriter(), errors);
	            	
	            }
		
		 
	      
//	      else {
//	          System.out.println("Pre-flight");
//	          response.setHeader("Access-Control-Allow-Origin", "*");
//	          response.setHeader("Access-Control-Allow-Methods", "POST,GET,DELETE,PUT");
//	          response.setHeader("Access-Control-Max-Age", "3600");
//	          response.setHeader("Access-Control-Allow-Headers", "X-Requested-With,content-type");
//	          response.setHeader("Access-Control-Allow-Credentials", "true");
//	          response.setStatus(HttpServletResponse.SC_OK);
//	      }
		
	}
	
//	@Autowired
//	private JwtUtils jwtUtils;
//	
//	@Autowired
//	private UserDetailsServiceImpl userDetailsServiceImpl;
//	
//	private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		
//		
////		try
////		{
////			
////		System.out.println("Check 2");
////			
////			  String jwt = parseJwt(request);
////			 
////		      if ( jwt != null && jwtUtils.validateJwtToken(jwt)) {
////		    	  System.out.println("Check3");
////		        String username = jwtUtils.getUserNameFromJwtToken(jwt);
////
////		        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);
////		        
////		        UsernamePasswordAuthenticationToken authentication = 
////		                new UsernamePasswordAuthenticationToken(userDetails,
////		                                                        null,
////		                                                        userDetails.getAuthorities());
////		            
////		            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
////
////		            SecurityContextHolder.getContext().setAuthentication(authentication);
////		     
////		     
////			
////			
////		      }
////		}catch(Exception e)
////		{
////			logger.error("Cannot set user authentication: {} ", e);
////			
////		}
////		
////		
////		filterChain.doFilter(request, response);
//		
////		String header = request.getHeader("Authorization");
////
////		if (header == null || !header.startsWith("HTTP_TOKEN")) {
//////			throw new JwtTokenMissingException("No JWT token found in the request headers");
////			System.out.println("No JWT token found in the request headers");
////		}
////
////		String token = header.substring("HTTP_TOKEN".length() + 1);
//		String jwt = parseJwt(request);
//
//		// Optional - verification
//		jwtUtils.validateJwtToken(jwt);
//
////		String userName = jwtUtils.getUserName(token);
////
////		UserDetails userDetails = userAuthService.loadUserByUsername(userName);
//        String username = jwtUtils.getUserNameFromJwtToken(jwt);
//
//        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);
//
//		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//				userDetails, null, userDetails.getAuthorities());
//
//		usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//		if (SecurityContextHolder.getContext().getAuthentication() == null) {
//			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//		}
//
//		filterChain.doFilter(request, response);
//
//		
//	}
//		private String parseJwt(HttpServletRequest request) {
//		    String jwt = jwtUtils.getJwtFromCookies(request);
//		    return jwt;
//		  }

}