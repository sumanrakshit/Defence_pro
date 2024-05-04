package com.Innspark.spring.boot.angularlogin.entity;

//In this class have multiple properties like name,imagename,
//filedata and description.
public class PayloadDto {
	
	//define properties that are using in PayloadDto
	private String name;
	private String imagename;
    private String fileData;
	private String description;
	
	//Implement Getter and  Setter Method
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getImagename() {
		return imagename;
	}
	public void setImagename(String imagename) {
		this.imagename = imagename;
	}

	public String getFileData() {
		return fileData;
	}
	public void setFileData(String fileData) {
		this.fileData = fileData;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

}
