package com.Innspark.spring.boot.angularlogin.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.Innspark.spring.boot.angularlogin.dbUtil.DBConnection;
import com.Innspark.spring.boot.angularlogin.entity.Payload;
import com.Innspark.spring.boot.angularlogin.repository.PayloadClickedRepository;
import com.Innspark.spring.boot.angularlogin.repository.PayloadRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/* Service Layer of Payload */
//Mark with annotation service because this class is responsible for write the business logic
@Service
public class PayloadService {
	
	//Inject the payloadRepository and payloadClickedRepository by dependency injection
	@Autowired
	PayloadRepository payloadRepository;
	@Autowired
	PayloadClickedRepository payloadclickedRepository;
	
	//business logic of create the payload with the help of file and payload string type
	public String create( MultipartFile imgicon,String payLoad) {
	    //Convert String to Object by ObjectMapper class
		Payload objectPayload=null;
		try {
			objectPayload = new ObjectMapper().readValue(payLoad, Payload.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			return "Error"+e.getMessage();
		} 
		
		try {
            
			Payload payLoad1 = new Payload();
            payLoad1.setName(objectPayload.getName());
            payLoad1.setImagename(imgicon.getOriginalFilename());
            payLoad1.setDescription(objectPayload.getDescription());
            payLoad1.setFileData(imgicon.getBytes());
            
            payloadRepository.save(payLoad1);
            
            return "File uploaded successfully";
            
        } 
		catch (IOException e) {
            return "Error uploading file";
        }

		
	}
	
	//get the payload from database and sent back it to controller method
	public Payload getByid(Integer id) {
		Optional<Payload> load=payloadRepository.findById(id);
		//check payload is present or not
		if(load.isPresent()) {
			return load.get();
		}
		//payload is not present with payload id
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"PayLoad Not Found");
		}
	
	}
	
	//get all the payload from table and sent back it to controller method
	public List<Payload> getallPayLoad() {
		List<Payload> payLoadAll=payloadRepository.findAll();
		return payLoadAll;
	}
	
	//logic of updatepayload by payloadid and sent back response to controller method
	public Payload updatepayload(Integer payloadid, MultipartFile imgicon, String payload) throws IOException {
		// TODO Auto-generated method stub
		Payload oldpayload=payloadRepository.findById(payloadid).get();
		Payload newpayload=new ObjectMapper().readValue(payload, Payload.class);
		oldpayload.setDescription(newpayload.getDescription());
		oldpayload.setFileData(imgicon.getBytes());
		oldpayload.setImagename(imgicon.getOriginalFilename());
		oldpayload.setName(newpayload.getName());
		oldpayload.setId(payloadid);
		payloadRepository.save(oldpayload);
		return oldpayload;
	}
	
	//logic of deletepayload in table  by payloadid and sent back response to controller method
	public String deletepayload(Integer payloadid) throws SQLException {
		// TODO Auto-generated method stub
		Payload payLoad=payloadRepository.findById(payloadid).get();
		//check payload data is present or not
		if(payLoad==null) {
			return "Not Deleted";
		}
		Connection driverConnection=DBConnection.getConnection();
		PreparedStatement stmt=driverConnection.prepareStatement("delete from payloadclicked where payload_id=?");  
		stmt.setInt(1,payloadid);//1 specifies the first parameter in the query  
		if(stmt.executeUpdate()==1) {
			payloadRepository.delete(payLoad);
		}
		
		return "Deleted Successfully in database";
	}
	
	
	
}
