package com.Innspark.spring.boot.angularlogin.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Innspark.spring.boot.angularlogin.entity.Template;
import com.Innspark.spring.boot.angularlogin.entity.TemplateClicked;
import com.Innspark.spring.boot.angularlogin.entity.TemplateFields;


@Repository
public interface TemplateService {
	
	public TemplateFields createTemplateField(TemplateFields templateFields, String id);
	public Template createTemplate( MultipartFile imgicon,String name,String description)  throws Exception;
//	public List<TemplateFields> getTemplatefieldslist();
	public List<TemplateFields> getTemplatefieldslist(String id);
//	public  String createTemplate(MultipartFile imgicon,String template ) throws Exception;

	public Template getTemplateByid(String templateid);
	public List<Template> getallTemplate();
	public Template updatetemplate(String templateid, MultipartFile imgicon, String template) throws IOException;
	public String deletetemplate(String templateid) throws SQLException;
	public TemplateClicked createTemplateRes(TemplateClicked templateClicked, String templateName);
	
	

}
