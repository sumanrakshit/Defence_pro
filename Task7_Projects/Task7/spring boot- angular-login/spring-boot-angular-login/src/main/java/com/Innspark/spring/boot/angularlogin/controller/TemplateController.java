package com.Innspark.spring.boot.angularlogin.controller;

import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
//import java.util.zip.DeflaterOutputStream;
//import java.util.zip.InflaterOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.Innspark.spring.boot.angularlogin.entity.Payload;
import com.Innspark.spring.boot.angularlogin.entity.Template;
import com.Innspark.spring.boot.angularlogin.entity.TemplateClicked;
import com.Innspark.spring.boot.angularlogin.entity.TemplateFields;
import com.Innspark.spring.boot.angularlogin.payload.ErrorRes;
import com.Innspark.spring.boot.angularlogin.repository.TemplateRepository;
import com.Innspark.spring.boot.angularlogin.service.TemplateService;



@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RestController
@RequestMapping("/v1")
public class TemplateController {
	
	@Autowired
	private TemplateService templateService;
	@Autowired
	 private TemplateRepository templateRepository;
	
	
	/*---------------------------------------------------------------------- Template ------------------------------------------------------------*/
	
	
	
	//url- http://localhost:8080/v1/templates
		@PostMapping("/templates")
//		@ExceptionHandler(MaxUploadSizeExceededException.class)
		public ResponseEntity<?> createTemplate(@RequestParam("imgicon") MultipartFile imgicon,
	            @RequestParam String name,
												@RequestParam String description)  {
//			String str=null;
//			str=templateService.createTemplate(imgicon,template);
//			if(str.equals(null)) {
//				throw new Error("You can't added empty name");
//				ErrorRes errorresponse=new ErrorRes(HttpStatus.NO_CONTENT, "You can't added empty name");
//				return new ResponseEntity(errorresponse,HttpStatus.NO_CONTENT);
			
			try {
				//			String str=null;
				Template tmp=templateService.createTemplate(imgicon,name,description);
//				if(str.equals(null)) {
//					throw new Error("You can't added empty name");
////					ErrorRes errorresponse=new ErrorRes(HttpStatus.NO_CONTENT, "You can't added empty name");
////					return new ResponseEntity(errorresponse,HttpStatus.NO_CONTENT);
//				}
				return new  ResponseEntity<>(tmp , HttpStatus.OK);
			}
			catch (Exception e) {
				// TODO: handle exception
				ErrorRes errorResponse=new ErrorRes(HttpStatus.UNPROCESSABLE_ENTITY, e.getMessage());
				return new ResponseEntity<>(errorResponse,HttpStatus.UNPROCESSABLE_ENTITY);
			}
			 		
		}
	
	
	//User will get the template by templateid
	//url- http://localhost:8080/v1/templates/{id}
	@GetMapping("/templates/{id}")
	public ResponseEntity<?> getTemplateByid(@PathVariable("id") String id){
		try {
			//Integer idint=Integer.parseInt(id);
			Optional<Template> templateOptional = templateRepository.findById(id);
			System.out.println(templateOptional.get().getFileData().length);
			return ResponseEntity.ok(templateOptional.get());
			}
		catch(Exception ex) {
			ErrorRes errorResponse=new ErrorRes(HttpStatus.NOT_FOUND, "Cannot fetch data");
			return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
		}
	}

	//User will get all the template 
	//url- http://localhost:8080/v1/templates
	@CrossOrigin(origins = "**")
	@GetMapping("/templates")
	public ResponseEntity<?> getAllTemplate() {
		//System.out.println(InetAddress.getLocalHost().getHostName());
		try {
			List<Template> templatelist=templateService.getallTemplate();
			//		respoHeaders.set("Type", "getall");
			return ResponseEntity.ok(templatelist);

		}
		catch (Exception ex) {
			ErrorRes errorResponse=new ErrorRes(HttpStatus.NOT_FOUND, "Cannot fetch data");
			return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
		}
	}
	
	//User will update the template by id
	//url- http://localhost:8080/v1/templates/{templateid}
	@PutMapping("/templates/{templateid}")
	public ResponseEntity<?> updatetemplate(@PathVariable String templateid,@RequestParam MultipartFile imgicon,@RequestParam("template") String template){
		try {
			//Integer payloadid=Integer.parseInt(templateid);
			Template updatetemplate=templateService.updatetemplate(templateid,imgicon,template);
			return ResponseEntity.ok(updatetemplate);
		}
		catch(NoSuchElementException ex) {
//			System.out.println(ex.getMessage());
			ErrorRes errorResponse=new ErrorRes(HttpStatus.CONFLICT, ex.getMessage());
			return new ResponseEntity<>(errorResponse,HttpStatus.CONFLICT);
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ErrorRes errorResponse=new ErrorRes(HttpStatus.CONFLICT, e.getMessage());
			return new ResponseEntity<>(errorResponse,HttpStatus.CONFLICT);
		}


	}
	
	//User will delete the template by templateid
	//url- http://localhost:8080/v1/templates/{templateid}
	@DeleteMapping("/templates/{templateid}")
	public ResponseEntity<?> deletetemplate(@PathVariable("templateid") String templateid){

		try {
//			Integer payloadid=Integer.parseInt(id);
			String str=templateService.deletetemplate(templateid);
			return new ResponseEntity<>("Delete successfully", HttpStatus.OK);
		}
		catch(Exception ex) {
			ErrorRes errorRes=new ErrorRes(HttpStatus.NOT_FOUND, ex.getMessage());
			return new ResponseEntity<>(errorRes,HttpStatus.NOT_FOUND);
		}
		
	}
	
	/* ----------------------------- Template field ------------------------------------*/
	
	//url- http://localhost:8080/v1/template_fields/{id}
	@PostMapping("/template_fields/{id}")
	public ResponseEntity<?> createTemplateField(@RequestBody TemplateFields templateFields, @PathVariable String id)
	{
		try
		{
			TemplateFields tmpf=templateService.createTemplateField(templateFields, id);
			if(tmpf==null) {
				throw new NullPointerException();
//				throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY ,"Template creation failed");
			}
			return new ResponseEntity<>(tmpf,HttpStatus.OK);
		}
		catch(Exception e)
		{
			//e.printStackTrace();
			//return new ResponseEntity("Exception Occurs",HttpStatus.NO_CONTENT);
//			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY ,"Template creation failed");
			ErrorRes errorRes=new ErrorRes(HttpStatus.UNPROCESSABLE_ENTITY, "Template fields added creation field ");
			return new ResponseEntity<>(errorRes, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	//url- http://localhost:8080/v1/template_fields
	@GetMapping("/template_fields/{id}")
	public ResponseEntity<?> getTemplateList(@PathVariable String id)
	{
		try
		{
//			List<TemplateFields> tmp=templateService.getTemplatefieldslist();
			List<TemplateFields> tmp=templateService.getTemplatefieldslist(id);
			return new ResponseEntity<>(tmp,HttpStatus.OK);
			
			
		}
		catch(Exception e)
		{
//			e.printStackTrace();
			return new ResponseEntity<>("Template List fetch fail", HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	
	
	/*------------------------------------------------------------------- Template Response ------------------------------------------------*/
	//url- http://localhost:8080/v1/template_responses/{templateName}
	@PostMapping("/template_responses/{templateName}")
	public ResponseEntity<?> templateresponse(@RequestBody TemplateClicked templateClicked, @PathVariable String templateName)
	{
		try
		{
			System.out.println(templateName);
			TemplateClicked tmp=templateService.createTemplateRes(templateClicked, templateName);
			if(tmp==null) {
				throw new NullPointerException();
			}
			return new ResponseEntity<>(tmp,HttpStatus.OK);
			
		}
		catch(NullPointerException e)
		{
//			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY ,"Error "+e.getMessage());
			ErrorRes errorRes=new ErrorRes(HttpStatus.INTERNAL_SERVER_ERROR, "Template response not added in database ");
			return new ResponseEntity<>(errorRes, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch(Exception e)
		{
//			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY ,"Error "+e.getMessage());
			ErrorRes errorRes=new ErrorRes(HttpStatus.UNPROCESSABLE_ENTITY, "Template response not added in database ");
			return new ResponseEntity<>(errorRes, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		
	}

}
