package com.Innspark.spring.boot.angularlogin.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
@Entity
//this Entity convert table in database
public class Payloadclicked {
	
	//Declare class properties
	//Make primary key With annotate with @ID
	@Id
	private String UUID;
	private String date;
	private String hostname;
	
	//Represent this field are LargeBinaryObject
	@Lob
	//Change the column behaviour with marked @Column
	@Column(columnDefinition = "LONGBLOB")
	
	//Transient means this field will not be store in table
	@Transient
	private byte[] filePayloadclick;

	@Transient
	private String filename;
	//Linked with many to one means single payload have many payloadclicked  
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name="payload_id", nullable=false)
    private Payload payLoad;

	//Implements Getter and Setter Methods
	public String getUUID() {
		return UUID;
	}
	public void setUUID(String uUID) {
		UUID = uUID;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public Payload getPayLoad() {
		return payLoad;
	}
	public void setPayLoad(Payload payLoad) {
		this.payLoad = payLoad;
	}
	
	public byte[] getFilePayloadclick() {
		return filePayloadclick;
	}
	public void setFilePayloadclick(byte[] filePayloadclick) {
		this.filePayloadclick = filePayloadclick;
	}
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}

	//override toString method and define itself
	@Override
	public String toString() {
		return "PayLoadClicked [UUID=" + UUID + ", date=" + date + ", hostname=" + hostname + ", payLoad=" + payLoad
				+ "]";
	}
}
