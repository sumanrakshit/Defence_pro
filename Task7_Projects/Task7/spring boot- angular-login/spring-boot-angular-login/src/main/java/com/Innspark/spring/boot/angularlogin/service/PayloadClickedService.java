package com.Innspark.spring.boot.angularlogin.service;

import java.io.IOException;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Innspark.spring.boot.angularlogin.entity.Payload;
import com.Innspark.spring.boot.angularlogin.entity.Payloadclicked;
import com.Innspark.spring.boot.angularlogin.repository.PayloadClickedRepository;
import com.Innspark.spring.boot.angularlogin.repository.PayloadRepository;

/*- Service Layer of PayloadClickedService-*/
@Service
public class PayloadClickedService {
	@Autowired
	PayloadClickedRepository payloadclickedRepository;
	@Autowired
	PayloadRepository load_Repository;
	
	public String createPayloadClicked(MultipartFile pdffile,Integer payloadid,String hostname) throws IOException{
		Payloadclicked loadClicked=new Payloadclicked();
		//import a random uuid (generate unique String)
		UUID uuid = UUID.randomUUID();
        String uuidString = uuid.toString();
        //Set uuid
        loadClicked.setUUID(uuidString);
        //set Hostname
        loadClicked.setHostname(hostname);
        //fetch payload id 
        Payload payLoad=load_Repository.findById(payloadid).get();
        System.out.println("Your PayLoad id is "+payLoad.getId());
        
        //set payLoad in loadclicked
        loadClicked.setPayLoad(payLoad);
        
        String datetime= datetime();     
        loadClicked.setDate(datetime);
        
        loadClicked.setFilePayloadclick(pdffile.getBytes());
        loadClicked.setFilename(pdffile.getOriginalFilename());
        
        /*List<PayLoadClicked> listPayloadclicked=payLoad.getListPayLoadClicked();
        listPayloadclicked.add(loadClicked);
        payLoad.setListPayLoadClicked(listPayloadclicked);
        */
        payloadclickedRepository.save(loadClicked);
        System.out.println("Sucessfully saved  in database");
        return "Successfully save in database";
	}
	
	//getall payloadclicked from the table and sent back response to the controller nethod
	public List<Payloadclicked> getallPayLoadClicked() {
		System.out.println("Payload ENter get all");
		List<Payloadclicked> listClicked=payloadclickedRepository.findAll();
		System.out.println("Payload Exit get all");
		return listClicked;
	}
	
	//logic of format date and time
	public String datetime() {
		String pattern = "MM/dd/yyyy HH:mm:ss";    
        DateFormat df = new SimpleDateFormat(pattern);
        Date today = Calendar.getInstance().getTime();        
        String datetime = df.format(today);
        return datetime;
	 }
	

}
