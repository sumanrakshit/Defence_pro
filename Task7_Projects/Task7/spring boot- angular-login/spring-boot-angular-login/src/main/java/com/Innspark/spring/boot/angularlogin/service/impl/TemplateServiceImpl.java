package com.Innspark.spring.boot.angularlogin.service.impl;

import java.io.IOException;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.Innspark.spring.boot.angularlogin.dbUtil.DBConnection;
import com.Innspark.spring.boot.angularlogin.entity.Payload;
import com.Innspark.spring.boot.angularlogin.entity.Template;
import com.Innspark.spring.boot.angularlogin.entity.TemplateClicked;
import com.Innspark.spring.boot.angularlogin.entity.TemplateFields;
import com.Innspark.spring.boot.angularlogin.repository.TemplateRepository;
import com.Innspark.spring.boot.angularlogin.repository.TemplateClickedRepository;
import com.Innspark.spring.boot.angularlogin.repository.TemplateFieldsRepository;
import com.Innspark.spring.boot.angularlogin.service.TemplateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;




@Service
public class TemplateServiceImpl implements TemplateService {
	
	@Autowired
	private TemplateFieldsRepository templteFieldsRepository;
	
	@Autowired
	private TemplateRepository templateRepository;
	@Autowired
	private TemplateClickedRepository templateClickedRepository;
	
	
//	Add Template List Field 
	@Override
	public TemplateFields createTemplateField(TemplateFields templateFields, String id) {
		//String tmpId=templateRepository.findByName(templateName).getId();
//		Template templ=templateRepository.findByName(templateName);
		Optional<Template> templ=templateRepository.findById(id);
		
		if(templ==null) {
			return null;
		}
		TemplateFields tmpfields=new TemplateFields();
		tmpfields.setId(uuid());
		tmpfields.setHas_host(templateFields.isHas_host());
		tmpfields.setHas_user(templateFields.isHas_user());
		tmpfields.setAddfields(templateFields.getAddfields());
		tmpfields.setDecoyId(id);
		
		TemplateFields res=templteFieldsRepository.save(tmpfields);
		
		
		return res;
	}
	
	
	
	
	
	//business logic of create the template with the help of file and template string type
	public Template createTemplate( MultipartFile imgicon,String name,String description)  throws Exception{
	    //Convert String to Object by ObjectMapper class
		Template objectTemplate=null;
		

			Template templatenew = new Template();
			templatenew.setId(uuid());
			templatenew.setName(name);
			templatenew.setImagename(imgicon.getOriginalFilename());
			templatenew.setDescription(description);
			templatenew.setFileData(imgicon.getBytes());
            
            templateRepository.save(templatenew);
            
//            return "File uploaded successfully";
            return  templateRepository.save(templatenew);
            
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			System.out.println("Eroor "+e.getMessage());
//			return "Error"+e.getMessage();
////			throw new Error(e.getMessage()); 
//		} 
		
		

		
	}
	
	//get the template from database and sent back it to controller method
	public Template getTemplateByid(String templateid) {
		Optional<Template> tmp=templateRepository.findById(templateid);
		//check template is present or not
		if(tmp.isPresent()) {
			return tmp.get();
		}
		//template is not present with template id
		else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"PayLoad Not Found");
		}
	
	}
	
	//get all the template from table and sent back it to controller method
	public List<Template> getallTemplate() {
		List<Template> templateAll=templateRepository.findAll();
		return templateAll;
	}
	
	//logic of updatetemplate by id and sent back response to controller method
	public Template updatetemplate(String templateid, MultipartFile imgicon, String template) throws IOException {
		Template oldtemplate=templateRepository.findById(templateid).get();

		if(oldtemplate==null) {
			
			return null;
		}
		
		
		Template newtemplate=new ObjectMapper().readValue(template, Template.class);
		oldtemplate.setDescription(newtemplate.getDescription());
		if(imgicon.getBytes()==null) {
			oldtemplate.setFileData(oldtemplate.getFileData());
			oldtemplate.setImagename(oldtemplate.getImagename());
		}
		oldtemplate.setFileData(imgicon.getBytes());
		oldtemplate.setImagename(imgicon.getOriginalFilename());
		oldtemplate.setName(newtemplate.getName());
		oldtemplate.setId(templateid);
		templateRepository.save(oldtemplate);
		
		return oldtemplate;
	}
	
	//logic of deletetemplate in table  by templateid and sent back response to controller method
	public String deletetemplate(String templateid)  {
		
		//check template data is present or not
		Template template=templateRepository.findById(templateid).get();
		templateRepository.delete(template);
		return null;
		
//		catch (Exception e) {
//			// TODO: handle exception
//			System.out.println(e.getMessage());
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"PayLoad Not Found"); 
//		}
		
	}



	/*fetch all template field list */
//	@Override
//	public List<TemplateFields> getTemplatefieldslist() {
//		List<TemplateFields> tmpfileds=templteFieldsRepository.findAll();
//		
//		return tmpfileds;
//	}
	@Override
	public List<TemplateFields> getTemplatefieldslist(String id) {
		List<TemplateFields> tmpfileds=templteFieldsRepository.findByDecoyId(id);
		
		return tmpfileds;
	}



//Add decoy response after generate decoy from another server 
	@Override
	public TemplateClicked createTemplateRes(TemplateClicked templateClicked, String templateName) {
		
		Template template=templateRepository.findByName(templateName);
		if(template==null) {
			System.out.println("Null object");
			return null;
		}
		String tmpId=templateRepository.findByName(templateName).getId();
		//String tmpid=templateRepository.findByName(templateName).getId();
//		System.out.println(tmpId);
		
		TemplateClicked tmp=new TemplateClicked();
		tmp.setId(uuid());
		tmp.setHostname(templateClicked.getHostname());
		tmp.setCreated_date(dateTime());
		tmp.setIpAddress(ipAddress());
		tmp.setTemplate_id(tmpId);
		TemplateClicked tmpclicked=templateClickedRepository.save(tmp);
		
		return tmpclicked;
	}
	
	
	
	
	
	
	
	/* -------------------------------------------------Other Methods ---------------------------------------------*/
	
//	Generate  Date and time 
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

//	Genearte IPAddress 
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
	public String uuid() {
		UUID uuid=UUID.randomUUID();
		return uuid.toString();
	}
	

	

}
