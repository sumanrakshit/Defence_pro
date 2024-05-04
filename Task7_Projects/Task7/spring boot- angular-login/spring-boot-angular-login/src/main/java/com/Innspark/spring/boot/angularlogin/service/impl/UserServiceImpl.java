package com.Innspark.spring.boot.angularlogin.service.impl;

import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.Date;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import java.util.stream.Collectors;

import com.Innspark.spring.boot.angularlogin.entity.*;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.Innspark.spring.boot.angularlogin.dbUtil.DBConnection;
import com.Innspark.spring.boot.angularlogin.payload.AddRequest;
import com.Innspark.spring.boot.angularlogin.repository.PrevilegeRepository;
import com.Innspark.spring.boot.angularlogin.repository.RoleRepository;
import com.Innspark.spring.boot.angularlogin.repository.UserRepository;
import com.Innspark.spring.boot.angularlogin.security.jwt.JwtUtils;
import com.Innspark.spring.boot.angularlogin.security.services.UserDetailsImpl;
import com.Innspark.spring.boot.angularlogin.service.UserService;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;	
	@Autowired
	private PrevilegeRepository previlegeRepository;
	@Autowired
	private JwtUtils jwtUtils;
	 @Autowired
	private AuthenticationManager authenticationManager;
	 @Autowired
	private PasswordEncoder encoder;
	private Connection connection;
	
	
	public UserServiceImpl() throws SQLException
	{
		connection=DBConnection.getConnection();
	}
	
	
//	-------------------------------------Authentication login -------------------------
	
	@Override
	public UserDetailsImpl loginUser(User user)   {
		// TODO Auto-generated method stub
		
		
//		  Optional<User> userOptional = userRepository.findByUsername(user.getUsername());
//
//	        if (userOptional.isPresent()) {
//	            User usr = userOptional.get();
//
//	            if (usr.getPassword().equals(user.getPassword())) {
//	            	System.out.println("User LOGIN Successfully ");
//	                return usr;
//	            } else {
//	            	System.out.println("Invalid Password");
//	            	 throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Password");
//	                
//	            }
//	        } else {
//	        	System.out.println("User Name not found");
//	        	 throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Username not found");
//	        }
		
		 Authentication authentication = authenticationManager
			        .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		 
		 
		  SecurityContextHolder.getContext().setAuthentication(authentication);

		    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		    
		    return userDetails ;

		
		
		
	}
	
//	@Override
//	public Boolean loginUser(String username, String password) {
//		// TODO Auto-generated method stub
//		boolean res=false;
//		
//		
//		try {
//			PreparedStatement statement=connection.prepareStatement("SELECT * FROM innuser WHERE username= '"+ username+"'");
//			ResultSet rs=statement.executeQuery();
//				while(rs.next())
//				{
//					if(rs.getString("username").equals(username))
//						
//					{
//						if(rs.getString("username").equals(username) && rs.getString("password").equals(password))
//						{
//							res=true;
//							System.out.println("Successfully loggedin");
//						}
//						else
//						{
//							System.out.println("Invalid Password");
//						}
//					}
//					else
//					{
//						System.out.println("UserName not matched");
//						
//					}
//					
//				}
//				
//				
//			
//			
//			
//			
//		}
//		catch(SQLException e)
//		{
//			e.printStackTrace();
//		}
//		
//		return res;
//		
//	}
	
	
	
	
	/* ---------------------------------------User ----------------------------------------------*/

	@Override
	public User addUser(AddRequest request) {
		// TODO Auto-generated method stub
		
//		User usrs=new User(
//				  user.getUsername(),
//				generatePWD(6),
//				user.getPrivelege()
//				
//				);
		UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();
		
		User user=new User();
		user.setId(uuidString);
		user.setUsername(request.getUsername());
		user.setName(request.getName());
		user.setPassword(generatePWD(6));
		
		user.setRole(request.getRole());
		user.setIs_firstLogin(true);;
		user.setGroups(request.getGroup().stream().map(r -> {
			Group ur = new Group();
			ur.setName(r);
			return ur;
		}).collect(Collectors.toSet()));
		user.setIpaddress(ipAddress());
		user.setCreated_date(dateTime());
		user.setCreator_id(request.getCreator_id());
		
		return userRepository.save(user);
				
		
//		String sql = " insert into innuser (username, password,privelege,is_first_login)"
//			    + " values (?, ?,?,?)";
//		try {
//			PreparedStatement statement=connection.prepareStatement(sql);
//			statement.setString(1, usrs.getUsername());
//			statement.setString(2, usrs.getPassword());
//			statement.setString(3, usrs.getPrivelege());
//			statement.setBoolean(4, true);
//			
//			statement.executeUpdate();
//			System.out.println("Successfully added ");
//			
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		User createUser=userRepository.save(usrs);
//		return createUser;
	}	
	
	
	
	@Override
	public void changePassword(String id, User user) {
		// TODO Auto-generated method stub
		
//		
//String sql = "UPDATE `innuser` SET `password` = ?,`is_first_login` = ? WHERE `id` = ?";
//		
//		
//		try {
//			PreparedStatement statement=connection.prepareStatement(sql);
//			
//			statement.setString(1, user.getPassword());
//			statement.setBoolean(2, false);
//			statement.setInt(3, id);
//			statement.executeUpdate();
//			System.out.println("Change Password successfully");
//			
//		} catch (SQLException e) {
//			
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		Optional<User> userOptional=userRepository.findById(id);
		
		if(userOptional.isPresent())
		{
			User usr=userOptional.get();
			usr.setPassword(user.getPassword());
			usr.setIs_firstLogin(false);
			userRepository.save(usr);
		}
		else
		{
			 throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
			
		}
		
	}
	
	
	public List<User> getAllUser(){
		
		List<User> lstUsr=new ArrayList<User>();
		List<User> usr=new ArrayList<User>();
		
//		lstUsr=userRepository.findAll();
//		String sql = " select * from innuser ";
		try {
			 usr=userRepository.findAll();
			 
			  System.out.println("user "  + usr);
			
//			PreparedStatement statement=connection.prepareStatement(sql);
//			ResultSet rs = statement.executeQuery();
//		     while (rs.next()) {
//
//		            User user = new User();
//		            
//		          
//		            user.setId(rs.getInt("id"));
//		            user.setUsername(rs.getString("Username"));
//		            user.setPassword(rs.getString("password"));
//		            user.setPrivelege(rs.getString("privelege"));
//		            
//
//		            lstUsr.add(user);
//		        }
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return usr;
		
		
	}
	
	
	@Override
	public User updateUser(String id, AddRequest user) {
		// TODO Auto-generated method stub
		
		
		User existUser=userRepository.findById(id).get();
//		System.out.println(existUser);
		existUser.setUsername(existUser.getUsername());
		existUser.setPassword(existUser.getPassword());
		existUser.setCreator_id(existUser.getCreator_id());
		existUser.setCreated_date(existUser.getCreated_date());
		existUser.setIs_firstLogin(existUser.isIs_firstLogin());
		
		existUser.setRole(user.getRole());
		
//		System.out.println(user.getRole());
//		System.out.println("check "+ user.getGroup());
		existUser.setGroups(user.getGroup().stream().map(r -> {
			Group ur = new Group();
			ur.setName(r);
			return ur;
		}).collect(Collectors.toSet()));
		existUser.setIpaddress(ipAddress());
		existUser.setId(id);
		existUser.setIs_firstLogin(existUser.isIs_firstLogin());
		existUser.setName(existUser.getName());
		System.out.println(" existUser "+  existUser);
		
		User updateUser=userRepository.save(existUser);
		
		
//		String sql = "UPDATE `innuser` SET `username` = ?,`privelege` = ? WHERE `id` = ?";
		
		
//		try {
//			PreparedStatement statement=connection.prepareStatement(sql);
//			
//			statement.setString(1, user.getUsername());
//			statement.setString(2, user.getPrivelege());
//			statement.setInt(3, id);
//			statement.executeUpdate();
//			System.out.println("User update successfully");
//			
//		} catch (SQLException e) {
//			
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return updateUser;
		
		
	}
	@Override
	public String deleteuser(String id) {
		User user=userRepository.findById(id).get();
//		userRepository.delete(user);
//		User usr1=(User) userRepository.findAll();
//		return "Success";
		Set<Group> groups=user.getGroups();
		if(groups.size()==0) {
			userRepository.delete(user);
			return "Delete Successfully in database";
		}
		int groupssize=groups.size();
		boolean flag=false;
		try {
		for(Group grp:groups) {
			flag=false;
			int grpid=grp.getId();
			String sql="delete from user_groups where group_id=?";
			
			PreparedStatement statement=connection.prepareStatement(sql);
			statement.setInt(1, grpid);
			int executeusergrp=statement.executeUpdate();
			if(executeusergrp==1) {
				String sql_grp="delete from grp where id=?";
				PreparedStatement statement_sql_grp=connection.prepareStatement(sql_grp);
				statement_sql_grp.setInt(1,grpid);
				int executequery=statement_sql_grp.executeUpdate();
				if(executequery==1) {
					flag=true;
					System.out.println("Flag true");
				}
			}
			groupssize--;
			
			}
		
		if(groupssize==0 && flag==true) {
			String sql_userinfo="delete from userinfo where id=?";
			PreparedStatement statement_user=connection.prepareStatement(sql_userinfo);
			statement_user.setString(1, id);
			int executeuserquery=statement_user.executeUpdate();
			if(executeuserquery==1) {
				return "Delete Successfully in database";
			}
			
		}
		}
		catch(SQLException ex) {
				System.out.println(ex.getMessage());
				return null;
			}
		return null;
			
		}
		
		
	
	@Override
	public User getById(String id) {
		// TODO Auto-generated method stub
		Optional<User> optionalUser=userRepository.findById(id);
		return optionalUser.get();
		
		
	}
	
	/* -----------------------------------------------------Role --------------------------------------------*/
	
	@Override
	public List<Role> getAllRole() {
		// TODO Auto-generated method stub
		
		List<Role> roles=new ArrayList();
		try
		{
			roles=roleRepository.findAll();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		System.out.println("Roles "  + roles);
		return roles;
	}


	@Override
	public Role addRoles(Role role) {
		// TODO Auto-generated method stub
		
		UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();
		
		
		Role roles=new Role();
		
		roles.setId(uuidString);
		roles.setName(role.getName());
		roles.setOrg(role.getOrg());
		roles.setPrivilege(role.getPrivilege());
//		System.out.println("checkkkkkkkk "+role.getPrivilege());
		roles.setCreated_date(dateTime());
		
		System.out.println("Roles "+ roles);
		return roleRepository.save(roles);
		
		
	}
	
	@Override
	public String editrole(String id, Role role) {
		// TODO Auto-generated method stub
		Optional<Role> oldrole=roleRepository.findById(id);
		if(oldrole.isPresent()){
			Role oldroles=oldrole.get();
			oldroles.setName(role.getName());
			oldroles.setPrivilege(role.getPrivilege());
			oldroles.setOrg(role.getOrg());
			roleRepository.save(oldroles);
			return "Update role Successfully in database";
		}
		System.out.println("Null value");
		return null;
	}
	
	@Override
	public String deleterole(String  id) {
		Optional<Role> role=roleRepository.findById(id);
		if(role.isPresent()){
			Role newrole=role.get();
			roleRepository.delete(newrole);
			return "Deleted role successfully in database";
		}
		else{
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Not found any role"); 
		}
		
	}
	

	@Override
	public Set<String> getUniqueRole() {
		// TODO Auto-generated method stub
		Set<String> uniqueRoleNames = new HashSet<>();
		try
		{
			
			 String sql = "SELECT DISTINCT name FROM roles";
			 PreparedStatement statement=connection.prepareStatement(sql);
	            ResultSet rs = statement.executeQuery();
	            while (rs.next()) {
	                String roleName = rs.getString("name");
	                uniqueRoleNames.add(roleName);
	            }

		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
			
		}
		return uniqueRoleNames;
	}

	
	/* ---------------------------------------------Privilege ------------------------------------*/

	@Override
	public Previlege addPrivilege(Previlege previlege) {
		// TODO Auto-generated method stub
		
		
		
		Previlege prv=new Previlege();
		prv.setName(previlege.getName());
		prv.setCreated_date(dateTime());
		 return previlegeRepository.save(prv);
		
	}


	@Override
	public List<Previlege> getAllPvg() {
		// TODO Auto-generated method stub
		
		List<Previlege> prvg=previlegeRepository.findAll();
		
		System.out.println("List of Previlege "+ prvg);
		
		return prvg;
	}

	
	/* -------------------------------------------------------Others method ----------------------------------------*/
	
	static String generatePWD(int len) 
	{ 
	   
	    String str= "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"; 
	    int n = str.length(); 
	  
	    // String to hold my Password
	    String psw=""; 
	  
	    for (int i = 1; i <= len; i++) 
	        psw += (str.charAt((int) ((Math.random()*10) % n))); 
	  
	    return(psw); 
	}

	public Long dateTime() 
	{
		
//		String pattern = "MM/dd/yyyy HH:mm:ss";
//		DateFormat df = new SimpleDateFormat(pattern);
//		Date today = Calendar.getInstance().getTime(); 
//		String todayAsString = df.format(today);
////		return todayAsString;
//		
//		
//		
////		SimpleDateFormat df = new SimpleDateFormat("MMM dd yyyy HH:mm:ss.SSS zzz");
//		Date date;
//		try {
//			date = df.parse(todayAsString);
//			long epoch = date.getTime();
//			System.out.println(epoch);
//			return epoch;
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
//		ZoneId zoneId = ZoneId.of("Asia/Kolkata"); 
//		LocalDate date = LocalDate.now(); 
//		long EpochMilliSecondsAtDate = date.atStartOfDay(zoneId).toInstant().toEpochMilli();
//		
//		return EpochMilliSecondsAtDate;
		
		
		Date date = Calendar.getInstance().getTime();
		System.out.println(date);
 		long EpochMilliSecondsAtDate = date.toInstant().toEpochMilli();
 		System.out.println(EpochMilliSecondsAtDate);
 		return EpochMilliSecondsAtDate;
		
	}

	
	public String ipAddress()
	{
		String res="";
		try {
            Enumeration<NetworkInterface> networkInterfaceEnumeration = NetworkInterface.getNetworkInterfaces();
            while( networkInterfaceEnumeration.hasMoreElements()){
                for ( InterfaceAddress interfaceAddress : networkInterfaceEnumeration.nextElement().getInterfaceAddresses())
                    if ( interfaceAddress.getAddress().isSiteLocalAddress())
                    {
                    	System.out.println(interfaceAddress.getAddress().getHostAddress());
                    	res= interfaceAddress.getAddress().getHostAddress();
                    	
                    }
                        
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
		return res;
	}
	
	
	/* ------------------------------------------------------------Check Privilege --------------------------------------*/
	
	
	

	@Override
	public boolean isPayload(String role, String org) {
		// TODO Auto-generated method stub
//		  boolean res=false;
//		try
//		{
//			PreparedStatement statement=connection.prepareStatement("SELECT privelege FROM roles WHERE name= '"+role+"' and org= '"+group+"'");
//			ResultSet rs=statement.executeQuery();
//			List<String> listpvg=new ArrayList<String>();
//			String str=null;
//			while(rs.next()) {
//				Role prrvgs=new Role();
//				
//				prrvgs.setPrivelege(rs.getArray("privelege"));
//				
//				 res=true;
//			
//			}
//			System.out.println(str);
//		}
//		catch(SQLException e)
//		{
//			e.printStackTrace();
//		}
//		
		
//		List<Role> roles=roleRepository.findAllActiveUsersNative();
//		roles.forEach((item)->System.out.println(item));
		
//		List<String> privileges = new ArrayList<>();
//		
//		 String sql = "SELECT privelege FROM roles WHERE name = ? AND org = ?";
//         try (PreparedStatement statement = connection.prepareStatement(sql)) {
//             statement.setString(1, role);
//             statement.setString(2, group);
//
//             try (ResultSet resultSet = statement.executeQuery()) {
//                 if (resultSet.next()) {
//                     String privilegeString = resultSet.getString("privelege");
//                     
//                     if (privilegeString != null && !privilegeString.isEmpty())
//                     {
//                    	 String[] privilegeArray = privilegeString.split(",");
//                    	 for (String privilege : privilegeArray)
//                    	 {
//                    		 System.out.println("check " + privilege);
//                    		 if(privilege.equals("pvg6"))
//                    		 {
//                    			 res=true;
//                    		 }
//                    	 }
//                    	 
//                     }
//                     System.out.println("string " + privilegeString);
//                     privileges = parsePrivilegeString(privilegeString);
//                     
//                     
//                     System.out.println("privilege List " + privileges);
//                 }
//             }
//         }
//         catch(SQLException e)
//         {
//        	 
//         }
//		List<String> privilege=new ArrayList<String>();
		
//		List<Role> roles=roleRepository.findByNameAndOrg(role, org);
//		boolean res=false;
//		
//		for(Role rl: roles)
//		{
//			List<String> list=rl.getPrivelege();
//			for(String pvgs : list)
//			{
//				if(pvgs.equals("pvg6"))
//				{
//					res=true;
//					System.out.println(" access payload");
//					break;
//				}
//				System.out.println("Need permission to access payload");
//			}
//			
//		}
		
		
//		roles.forEach((r)->
//		{
////			r.getPrivelege();
//			
//			List<String> list=r.getPrivelege();
////			res=list.stream().filter(rol->rol.equals("pvg6"))list;
//
//			for(String pvgs:list) {
//				
//				if(pvgs.equals("pvg6"))
//					res=true;
//				
//				
//			}
//			System.out.println("Privilege List " + r.getPrivelege());
//			
//		});
		Role roles=roleRepository.findByNameAndOrg(role, org);
		return roles.getPrivilege().stream().anyMatch((prvg)->prvg.equals("ACCESS_PAYLOAD"));
		
		
	}
	
	
	
	
	private List<String> parsePrivilegeString(String privilegeString) {
	    List<String> privileges = new ArrayList<>();
	    
	    if (privilegeString != null && !privilegeString.isEmpty()) {
	        // Split the comma-separated string into individual privileges
	        String[] privilegeArray = privilegeString.split(",");
	        
	        for (String privilege : privilegeArray) {
	            privileges.add(privilege.trim());  // Add each privilege to the list (remove leading/trailing spaces)
	            privileges.add(",");
	        }
	    }
	    
	    return privileges;
	}


	@Override
	public boolean isRoleAccess(String role, String org) {
		// TODO Auto-generated method stub
		Role roles=roleRepository.findByNameAndOrg(role, org);
		return roles.getPrivilege().stream().anyMatch((prvg)->prvg.equals("ACCESS_ADD_ROLE"));
	}


	@Override
	public boolean isUserAccess(String role, String org) {
		// TODO Auto-generated method stub
		Role roles=roleRepository.findByNameAndOrg(role, org);
		return roles.getPrivilege().stream().anyMatch((prvg)->prvg.equals("ACCESS_ADD_USER"));
		
	}


	@Override
	public Template addTemplate(Template template) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Template getTemplate() {
		// TODO Auto-generated method stub
		return null;
	}
	//Add by vivek
	@Override
	public List<User> fetchListuser(List<String> Org){

		Set<String> userid=new HashSet<>();
		List<User> users=new ArrayList<>();

		try{
			for(int i=0;i<Org.size();i++) {

				String query = "select * from userinfo where id in (select user_id from user_groups where group_id in (select id from grp where name =?))";
				PreparedStatement st = connection.prepareStatement(query);
				st.setString(1, Org.get(i));
				ResultSet set = st.executeQuery();
				while (set.next()) {

					String id = set.getString("id");
					User userres=userRepository.findById(id).get();
					if (userid.contains(id)) {
						continue;
					} else {
						userid.add(id);
						users.add(userres);
					}
				}
			}
		}
		catch(Exception e){
			System.out.println("Exception e "+e.getMessage());
			throw new Error("No data found");
		}
		System.out.println(users.size());
		return users;
	}


	//************Added by Mohit****************
	@Override
	public List<User> getUserByOrg(List<String> orgName) {
		return userRepository.findByGroups(orgName);
	}


	@Override
	public Role getUserPrivilege(String rolename , String groupname) {
		// TODO Auto-generated method stub
		
		
		 Role role=roleRepository.findByNameAndOrg(rolename, groupname);

		return role;
	}

	

	



	


}
