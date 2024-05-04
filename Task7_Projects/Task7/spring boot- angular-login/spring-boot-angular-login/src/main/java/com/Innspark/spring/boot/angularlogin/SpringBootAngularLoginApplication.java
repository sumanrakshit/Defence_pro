package com.Innspark.spring.boot.angularlogin;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterOutputStream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableWebMvc
public class SpringBootAngularLoginApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootAngularLoginApplication.class, args);

	}
	
	
	
	@Override
	 public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/v1/**")
	                .allowedOrigins(
	                        "http://localhost:4200"
	                )
	                .allowedMethods(
	                        "GET",
	                        "PUT",
	                        "POST",
	                        "DELETE",
	                        "PATCH",
	                        "OPTIONS"
	                ).allowedHeaders("*").allowCredentials(true).maxAge(3600);
	    }

	
//	 @Bean
//	    CorsConfigurationSource corsConfigurationSource() {
//	        CorsConfiguration configuration = new CorsConfiguration();
//	        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
//	        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
//	        configuration.setAllowedOrigins(Collections.singletonList("*")); // Allow requests from any origin
//	        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Allowed HTTP methods
//	        configuration.setAllowedHeaders(Collections.singletonList("*"));
//	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	        source.registerCorsConfiguration("/v1/**", configuration);
//	        return source;
//	    }
	
	  @Bean
	    CorsConfigurationSource corsConfigurationSource() {
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        CorsConfiguration corsConfiguration = new CorsConfiguration();
	        corsConfiguration.addAllowedOrigin("http://localhost:4200");
	        corsConfiguration.addAllowedHeader("*");
	        corsConfiguration.setAllowedMethods(Arrays.asList(
	                HttpMethod.GET.name(),
	                HttpMethod.HEAD.name(),
	                HttpMethod.POST.name(),
	                HttpMethod.PUT.name(),
	                HttpMethod.DELETE.name()));
	        corsConfiguration.setMaxAge(1800L);
	        source.registerCorsConfiguration("/**", corsConfiguration); // you restrict your path here
	        return source;
	    }
	 
}
