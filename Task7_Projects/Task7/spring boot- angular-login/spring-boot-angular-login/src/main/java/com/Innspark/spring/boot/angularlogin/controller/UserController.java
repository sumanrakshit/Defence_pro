package com.Innspark.spring.boot.angularlogin.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpHeaders;

import com.Innspark.spring.boot.angularlogin.entity.Group;
import com.Innspark.spring.boot.angularlogin.entity.Previlege;
import com.Innspark.spring.boot.angularlogin.entity.Role;
import com.Innspark.spring.boot.angularlogin.entity.User;
import com.Innspark.spring.boot.angularlogin.security.jwt.JwtUtils;
import com.Innspark.spring.boot.angularlogin.security.services.UserDetailsImpl;
import com.Innspark.spring.boot.angularlogin.service.UserService;
import com.Innspark.spring.boot.angularlogin.payload.AddRequest;
import com.Innspark.spring.boot.angularlogin.payload.ErrorRes;
import com.Innspark.spring.boot.angularlogin.payload.LoginRequest;
import com.Innspark.spring.boot.angularlogin.payload.MessageResponse;
import com.Innspark.spring.boot.angularlogin.payload.UserResponse;
import com.Innspark.spring.boot.angularlogin.repository.RoleRepository;
import com.Innspark.spring.boot.angularlogin.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
//@CrossOrigin("**")

@RestController
@RequestMapping("/v1")

public class UserController {
	
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	 @Autowired
	 AuthenticationManager authenticationManager;
	 
	 Authentication authentication;
	 
	//------------------------------------Authenticate	User---------------------------------------------
		
//		Login User (http://localhost:8080/v1/login_user)
//	 @CrossOrigin("**")
	@ResponseBody
	@PostMapping("/login_user")
	public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest)
	{
		
		boolean success=false;
		try
		{
			
			 authentication = authenticationManager
			        .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
			if(authentication.isAuthenticated())
			{
				success=true;
			}
			else
			{
				success=false;
			}
			
			System.out.println("Is authenticate " + authentication.isAuthenticated());
			String username=authentication.getName();
//			System.out.println(" username " + username);
			
//			 User user= new User(username,"");
//			  User users=(User) authentication.getPrincipal();
			 UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//			 System.out.println("Userdetails " + userDetails.getAuthorities());
//			 System.out.println("usedet"+ userDetails.getUsername());
//			  System.out.println(" user "+ user.getPrivelege());
			  String token=jwtUtils.createToken(userDetails);
			  List<String> groups = userDetails.getAuthorities().stream()
			            .map(item -> item.getAuthority())
			            .collect(Collectors.toList());
			  
//			  System.out.println("Token " +token );
			  
			 
			  
			//   System.out.println("Group " + groups);

				 String rolename=userDetails.getRole();
				//  System.out.println("Rolename "+rolename);
				 String groupname="";
				 for(String grp:groups) {
					 groupname=grp;
				 }
				//  System.out.println("Grpip ...." + groupname);
				 //System.out.println();
				 Role role=roleRepository.findByNameAndOrg(rolename, groupname);
				//  System.out.println(" checkkkkkrole "+role);
				 
			  ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
			  System.out.println(role.getPrivilege());
			  
			UserResponse userResponse=  new UserResponse(userDetails.getId(), userDetails.getUsername(), userDetails.getRole(), userDetails.isFirstLogin(), groups, role.getPrivilege(),token);
			
			
			System.out.println("userdetails " + userResponse);
			if(success)
			{
				 return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body(userResponse);
			}
			
			else
			{
				return new ResponseEntity<>("Not authenticate ..please check", HttpStatus.NOT_FOUND);
			}
			
//			return ResponseEntity.ok(userResponse);
		}
		catch(BadCredentialsException e)
		{
			ErrorRes errorResponse = new ErrorRes(HttpStatus.UNAUTHORIZED,"Invalid username or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);

		}
		catch (Exception e){
            ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
		
	}
	
	
//	LogOut User (http://localhost:8080/v1/logout_user)	
	
	@PostMapping("/logout_user")
	  public ResponseEntity<?> logoutUser() {
	    
	    try {
	    	MessageResponse messageResponse=new MessageResponse();
			messageResponse.setMessage("Successfully deleted");
			messageResponse.setSuccess(true);
			messageResponse.setStatus("200 OK");
	    	ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
		    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
		        .body(messageResponse);
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    	ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	    	
	    	
	    }
	  }

	@GetMapping("/user_privilege")
	public ResponseEntity<?> getUserPrivileges()
	{
		 try {
			 List<String>privileges=new ArrayList<String>();
			 UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
			 List<String> groups = userDetails.getAuthorities().stream()
			            .map(item -> item.getAuthority())
			            .collect(Collectors.toList());
			 String rolename=userDetails.getRole();
				//  System.out.println("Rolename "+rolename);
				 String groupname="";
				 for(String grp:groups) {
//					 groupname=grp;
					 Role role=userService.getUserPrivilege(rolename, grp);
					 privileges.addAll(role.getPrivilege())  ;
				 }
				 System.out.println(privileges);
				 List<String> uniqueList = privileges.stream()
                         .distinct()
                         .collect(Collectors.toList());

			 
			 return new ResponseEntity<>(uniqueList, HttpStatus.OK);
			    
		    }
		    catch(Exception e)
		    {
		    	e.printStackTrace();
		    	ErrorRes errorResponse = new ErrorRes(HttpStatus.BAD_REQUEST, e.getMessage());
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		    	
		    	
		    }
		
	}
	
	
	
	
	/* -----------------------------------user  ------------------------*/
	
	
	
	
	
//	 Add new User (http://localhost:8080/v1/users)
	
	@CrossOrigin("**")
	@PostMapping("/users")
	public ResponseEntity<?> addUser(@RequestBody() AddRequest user1)
	{
		try {
			User user=userService.addUser(user1);
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
		catch(Exception ex) {
			
			
			ErrorRes errorResponse = new ErrorRes(HttpStatus.NOT_ACCEPTABLE, ex.getMessage() );
			System.out.println("ceck" + errorResponse);
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
            
		}
		
	}
	
	
//	Get all User (http://localhost:8080/v1/users)
	
	@GetMapping("/users")
	public ResponseEntity<?> getAllUser()
	{
		try {
			List<User> lstUsr=new ArrayList<User>();
			lstUsr=userService.getAllUser();
			
			return new ResponseEntity(lstUsr,HttpStatus.OK);
		}
		catch(Exception ex) {
			ErrorRes errorResponse = new ErrorRes(HttpStatus.NOT_FOUND, ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}

	}
	//************Added by Mohit****************
	// Get all User by organization (http://localhost:8080/v1/org_users)
	@GetMapping("/org_users")
	public ResponseEntity<?> getUserByOrg() {
		try {
			
			 UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
			 List<String> groups = userDetails.getAuthorities().stream()
			            .map(item -> item.getAuthority())
			            .collect(Collectors.toList());
		      List<User> userByOrg = userService.getUserByOrg(groups);
		      return ResponseEntity.ok(userByOrg);	
		}
		catch(HttpMessageNotReadableException ex) {
			ErrorRes errorResponse = new ErrorRes(HttpStatus.NOT_FOUND, ex.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}
		catch(Exception ex) {
			ErrorRes errorResponse = new ErrorRes(HttpStatus.NOT_FOUND, ex.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}
		
		
	}
	
//	Get User by id(http://localhost:8080/v1/users/{id})
	
	@GetMapping("/users/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") String id)
	{
		try {
			User usr=userService.getById(id);
			return new ResponseEntity(usr,HttpStatus.OK);
		}
		catch (Exception ex) {
			// TODO: handle exception
			ErrorRes errorResponse = new ErrorRes(HttpStatus.NOT_FOUND, ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}	
	} 
	
	
//	edit User (http://localhost:8080/v1/users/{id})
	
	@PutMapping("/users/{id}")
	public ResponseEntity<?> updateUser( @PathVariable String id, @RequestBody AddRequest user1)
	{
		try {
//			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
			User user=userService.updateUser( id,user1);
			return new ResponseEntity<>(user,HttpStatus.OK); 
		}
		catch (Exception ex) {
			ErrorRes errorResponse = new ErrorRes(HttpStatus.CONFLICT, ex.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
		}
		
		
	}
	
	//Delete the user with group that link to the multiple groups
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteuser(@PathVariable String id){
		
		try {
			System.out.println("Id "+id);
			String str=userService.deleteuser(id);
			if(str==null) {
				System.out.println("Hello");
				MessageResponse messageResponse=new MessageResponse("Not Deleted","409 Conflict",false);

				return new ResponseEntity<>(messageResponse,HttpStatus.CONFLICT);
//				ErrorRes errorResponse = new ErrorRes(HttpStatus.CONFLICT, "Not deleted in database Some errors in sql query");
//				return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
			}
			MessageResponse messageResponse=new MessageResponse();
			messageResponse.setMessage("Successfully deleted");
			messageResponse.setSuccess(true);
			messageResponse.setStatus("200 OK");
			return new ResponseEntity<>(messageResponse,HttpStatus.OK);
		}
		catch(Exception ex) {
			System.out.println("Hello ---"+ex.getMessage());
			MessageResponse messageResponse=new MessageResponse(ex.getMessage(),"409 Conflict",false);

			return new ResponseEntity<>(messageResponse,HttpStatus.CONFLICT);
//			ErrorRes errorResponse = new ErrorRes(HttpStatus.CONFLICT, ex.getMessage());
//			return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
		}
		
		
//		return null;
	}
		
		


	//Add By Vivek
	//fetch information of user who belong according to login privilage
	@GetMapping("/users_org")
	public ResponseEntity<?> getuserorg(@RequestBody List<String> Org){
		try{
			List<User> userslist=userService.fetchListuser(Org);
			return  ResponseEntity.ok(userslist);
		}
		catch (Exception ex){
			return new ResponseEntity<>("No data",HttpStatus.CONFLICT);
		}



	}
	// Change User Password (http://localhost:8080/v1/users_password)
   //	when user first login go to change password page, next time go to user details page
	
	@PostMapping("/users_password")
	public ResponseEntity<?> changePassword( @RequestBody User user1)
	{
		try {
			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

			String id=userDetails.getId();
			userService.changePassword(id, user1);
//			Optional<User> usr=userRepository.findById(id);
//			return new ResponseEntity<>(usr, HttpStatus.OK);
			
			
			MessageResponse messageResponse=new MessageResponse();
			messageResponse.setMessage("Successfully Change Password");
			messageResponse.setSuccess(true);
			messageResponse.setStatus("200 OK");
			return new ResponseEntity<>(messageResponse,HttpStatus.OK);
		}
		catch (Exception ex) {
			ErrorRes errorResponse = new ErrorRes(HttpStatus.CONFLICT, ex.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
		}
		
	}
	
	
	
//	-----------------------ROLE ------------------------------------------------------
	
	
//	Add Role with  field privilege, organization, name of this role(http://localhost:8080/api/addrole)
	//url-http://localhost:8080/v1/roles
	@PostMapping("/roles")
	public ResponseEntity<?> addRoles(@RequestBody Role role)
	{
		try {
			Role role1=userService.addRoles(role);
			System.out.println("Successfully add Roles");
			return new ResponseEntity<>(role1, HttpStatus.OK);
		}
		catch(Exception ex) {
			ErrorRes errorResponse = new ErrorRes(HttpStatus.UNPROCESSABLE_ENTITY, ex.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse);
		}
		
		
	}
	
//   Get all role	(http://localhost:8080/v1/roles)
	
	@GetMapping("/roles")
	public ResponseEntity<?> getAllRoles()
	{
		try {

			List<Role> roles=userService.getAllRole();
			return new ResponseEntity(roles,HttpStatus.OK);
		}
		catch (Exception ex) {
			
			ErrorRes errorResponse = new ErrorRes(HttpStatus.NOT_FOUND, ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}
	}
	
	//Edit Role with role id (http://localhost:8080/v1/roles/{id})
	@PutMapping("roles/{id}")
	public ResponseEntity<?> editrole(@PathVariable String id, @RequestBody Role role) {
		try {
			String str=userService.editrole(id,role);
			if(str==null) {
				//throw new ErrorRes(HttpStatus.NOT_MODIFIED, "Not update Successfully");
				MessageResponse messageResponse=new MessageResponse("Not Updated","409 Conflict",false);
				return new ResponseEntity<>(messageResponse,HttpStatus.CONFLICT);
			}

			MessageResponse messageResponse=new MessageResponse();
			messageResponse.setMessage("Successfully Upated");
			messageResponse.setSuccess(true);
			messageResponse.setStatus("200 OK");
			return new ResponseEntity<>(messageResponse,HttpStatus.OK);
		}
		catch (Exception ex) {
			ErrorRes errorResponse = new ErrorRes(HttpStatus.CONFLICT, ex.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
		}
	}
	
	//Delete Role with role id (http://localhost:8080/v1/roles/{id})
	@DeleteMapping("roles/{id}")
	public ResponseEntity<?> deleterole(@PathVariable String id){
		try {
			String str=userService.deleterole(id);
			return ResponseEntity.status(HttpStatus.OK).body(str);
		}
		catch(Exception ex) {
			ErrorRes errorResponse = new ErrorRes(HttpStatus.NOT_FOUND, ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}
		
	}
	
//  Get all unique role	(http://localhost:8080/v1/uni_roles)
	@GetMapping("/uni_roles")
	public ResponseEntity<?> getUniqueRole()
	{
		try
		{
			Set<String> uniqueRole=userService.getUniqueRole();
			return new ResponseEntity(uniqueRole,HttpStatus.OK);
		}
		catch(Exception ex )
		{
			ErrorRes errorResponse = new ErrorRes(HttpStatus.NOT_FOUND, ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}
	}
	
	
	
//	----------------------------------------------------Privilege------------------------------------------------- 
	

//	Add Previlege (http://localhost:8080/v1/privileges)
	
	@PostMapping("/privileges") 
	public ResponseEntity<?> addPrevilege (@RequestBody Previlege priPrevilege)
	{
		try {
			Previlege prv=userService.addPrivilege(priPrevilege);
			
			return new ResponseEntity<>(prv, HttpStatus.OK);
		}
		catch (Exception ex) {
			ErrorRes errorResponse = new ErrorRes(HttpStatus.NOT_ACCEPTABLE, ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorResponse);
		}
		
	}
	
	
//Get all previlege List (http://localhost:8080/v1/privileges)	
	
	@GetMapping("/privileges")
	public ResponseEntity<?> findAllPrivilege(){
		try {
			List<Previlege> listpvg=userService.getAllPvg();
			return new ResponseEntity<>(listpvg,HttpStatus.OK);
		}
		catch(Exception ex) {
			ErrorRes errorResponse = new ErrorRes(HttpStatus.NO_CONTENT, "can not fetch data");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(errorResponse);
		}
		
	}
	
//	@PutMapping("/editpvg/{id}")
//	public String editpvg(@RequestBody Previlege pvg,@RequestParam String id) {
//		
//		return null;
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	@GetMapping("/login/{username}/{password}")
//	public  Boolean loginUser(@PathVariable("username") String username1, @PathVariable("password") String password1)
//	{
//		boolean res=userService.loginUser(username1, password1);
//		if(res==false)
//		{
//			return false;
//		}
//		return res;
//		
//	}
	
	
	
	
//	@PostMapping("/login")
//	public ResponseEntity<?> loginUser(@RequestBody LoginRequest usr1) throws Exception  
//	{
//		System.out.println("check ");
//		try
//		{
//			 Authentication authentication = authenticationManager
//				        .authenticate(new UsernamePasswordAuthenticationToken(usr1.getUsername(), usr1.getPassword()));
//			 System.out.println("is user Authenticate"+ authentication.isAuthenticated());
//			 SecurityContextHolder.getContext().setAuthentication(authentication);
//
//			    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//			    System.out.println("user " + userDetails);
//			    
////			    User users=(User) authentication.getPrincipal();
////			UserDetailsImpl userDetails=userService.loginUser(usr1);
//			
//
//			    ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);
//			    
//			    List<String> groups = userDetails.getAuthorities().stream()
//			            .map(item -> item.getAuthority())
//			            .collect(Collectors.toList());
//			    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body(new UserResponse(userDetails.getId(), userDetails.getUsername(), userDetails.getPrivelege(), userDetails.isFirstLogin(), groups));
//	            
//		}
//		catch(BadCredentialsException e)
//		{
//			throw new Exception("Incorrect username or password", e);
//		}
//		
//		
//		 
//		 
//		  
//
//		      
//
//		
////		User user=userService.loginUser(usr1);
////		
////			return new ResponseEntity<>(user,HttpStatus.OK);
//		
//	}
//
	
	
	
	
	
//	@RequestMapping
//	@PreAuthorize("hasGroup()")
//	public void Usergroup()
//	{
//		
//	}
	
//check Payload permission
	//url-http://localhost:8080/v1/access_decoy
	@GetMapping("/access_decoy")
	public ResponseEntity<?> isPayloadaccess(@RequestParam String role, @RequestParam String org)
	{
		try {
			boolean res=false;
			res=userService.isPayload(role, org);
			return new ResponseEntity<>(res,HttpStatus.OK);
		}
		catch(Exception ex) {
			ErrorRes errorResponse = new ErrorRes(HttpStatus.NOT_FOUND, ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}
	}
	
	/* check role Permission */
	//url-http://localhost:8080/v1/access_role
	@GetMapping("/access_role")
	public ResponseEntity<?> isRoleaccess(@RequestParam String role, @RequestParam String org)
	{
		try {
			boolean res;
			res=userService.isRoleAccess(role, org);

			return new ResponseEntity<>(res,HttpStatus.OK);
		}
		catch(Exception ex) {
			ErrorRes errorResponse = new ErrorRes(HttpStatus.NOT_FOUND, ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}
		
	}
	
	
//	check add user permision
	//url-http://localhost:8080/v1/access_user
	@GetMapping("/access_user")
	public ResponseEntity<?> isUseraccess(@RequestParam String role, @RequestParam String org)
	{
		try {
			boolean res;
			res=userService.isUserAccess(role, org);

			return new ResponseEntity<>(res,HttpStatus.OK);
		}
		catch(Exception ex) {
			ErrorRes errorResponse = new ErrorRes(HttpStatus.NOT_FOUND, ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		}

	}
	
	
}
