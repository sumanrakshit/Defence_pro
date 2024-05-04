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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



/*@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter*/
@Entity
public class TemplateClicked {
	@Id
	private String id;
	@Column(nullable = false)
	private long created_date;
	@Column(nullable = false)
	private String hostname;
	@Column(nullable = false)
	private String ipAddress;
	@Column(nullable = false)
	 private String template_id;
	
	public long getCreated_date() {
		return created_date;
	}
	public void setCreated_date(long created_date) {
		this.created_date = created_date;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
//	public int getTemplate_id() {
//		return template_id;
//	}
//	public void setTemplate_id(int template_id) {
//		this.template_id = template_id;
//	}
	
	
	
	public String getTemplate_id() {
		return template_id;
	}
	public TemplateClicked(String id, long created_date, String hostname, String ipAddress, String template_id) {
	super();
	this.id = id;
	this.created_date = created_date;
	this.hostname = hostname;
	this.ipAddress = ipAddress;
	this.template_id = template_id;
}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}
	public TemplateClicked() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "TemplateClicked [id=" + id + ", created_date=" + created_date + ", hostname=" + hostname
				+ ", ipAddress=" + ipAddress + ", template_id=" + template_id + "]";
	}
	
	
	
	
	
//	//Represent this field are LargeBinaryObject
//	@Lob
//	//Change the column behaviour with marked @Column
//	@Column(columnDefinition = "LONGBLOB")
//	
//	//Transient means this field will not be store in table
//	@Transient
//	private byte[] filePayloadclick;

//	@Transient
//	private String filename;
//	//Linked with many to one means single payload have many payloadclicked  
//	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
//    @JoinColumn(name="payload_id", nullable=false)
   
	 

}
