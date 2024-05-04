package com.Innspark.spring.boot.angularlogin.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

//@Entity means this class is converted to table automatically 
//without define any configuration
@Entity
public class Payload {
	//define the payload properties
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	private String name;
	private String imagename;
	
	//@Lob means this fields store large binary object
    @Lob
    //@Column to change the column behaviour
    @Column(columnDefinition = "LONGBLOB")
    private byte[] fileData;

	private String description;
	
	//Define Getter and Setter Methods
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
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
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public byte[] getFileData() {
		return fileData;
	}
	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}
	
	//Define Override toString method 
	@Override
	public String toString() {
		return "PayLoad [id=" + id + ", name=" + name + ", imagename=" + imagename + ", description=" + description
				+ "]";
	}
	
	
}
