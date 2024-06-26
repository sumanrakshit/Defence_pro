package com.Innspark.spring.boot.angularlogin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Innspark.spring.boot.angularlogin.entity.Payloadclicked;
import com.Innspark.spring.boot.angularlogin.entity.PayloadclickedDTO;
import com.Innspark.spring.boot.angularlogin.service.PayloadClickedService;


//This class is responsible for all endpoints of payload that must be required communicate with client
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/payload")
//url- http://localhost:8080/inspark/payloadclick
public class PayLoadClickedController {
	@Autowired
	PayloadClickedService clickedService;
	
	/* ----------------------Payloadclickcontroller--------------------*/
	
	//upload the file by payload id
	//url- http://localhost:8080/inspark/payloadclick/createpayloadclicked
	@PostMapping("/createpayloadclick")
	public ResponseEntity<String> createPayloadclicked(@RequestParam MultipartFile pdffile,
			@RequestParam("Payloadid") Integer Payloadid,@RequestParam String hostname) throws IOException {
		//Intialiaze the string variable to store the result
		String  str=null;
		try {
			str=clickedService.createPayloadClicked(pdffile, Payloadid,hostname);
			return ResponseEntity.ok(str);
		}
		catch(Exception ex) {
			return ResponseEntity.badRequest().build();
		}	
	}
	
	//User will fetch all the clicked payload
	//url- http://localhost:8080/innspark/payloadclick/getall
	@GetMapping("/getallpayloadclick")
	public ResponseEntity<List<PayloadclickedDTO>> getallpayLoadclicked(){
		List<Payloadclicked> getallPayloadClicked=clickedService.getallPayLoadClicked();
		if(getallPayloadClicked!=null) {
			
			//call method of payloadclickdto(getallpayloadclicked)
			List<PayloadclickedDTO> payloadClickedDtolist=payloadclickdto(getallPayloadClicked);
			
			return ResponseEntity.ok(payloadClickedDtolist);
		}
		return ResponseEntity.notFound().build();

	}
	
	//convert PayloadClicked Model to PayLoadClickedDTO Model 
	public List<PayloadclickedDTO> payloadclickdto(List<Payloadclicked> getallpayloadclicked){
		List<PayloadclickedDTO> payloadClickedDtolist=new ArrayList<PayloadclickedDTO>();
		for(Payloadclicked payLoadClicked:getallpayloadclicked) {
				
				PayloadclickedDTO clickedDTO=new PayloadclickedDTO();
				clickedDTO.setDate(payLoadClicked.getDate());
				clickedDTO.setHostname(payLoadClicked.getHostname());
				clickedDTO.setPayload_id(payLoadClicked.getPayLoad().getId());
				clickedDTO.setUUID(payLoadClicked.getUUID());
				payloadClickedDtolist.add(clickedDTO);
			
		}
		return payloadClickedDtolist;
	}
	
}
