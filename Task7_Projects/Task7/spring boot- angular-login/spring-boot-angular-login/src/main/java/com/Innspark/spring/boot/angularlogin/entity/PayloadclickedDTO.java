package com.Innspark.spring.boot.angularlogin.entity;

//This class is used to modify the properties and copy the data from payloadclicked entity
public class PayloadclickedDTO {
	//Properties declared with private access modifier
	private String UUID;
	private String date;
	private String hostname;
	private Integer payload_id;
	
	//Declare Getter and Setter Method
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
	
	public Integer getPayload_id() {
		return payload_id;
	}
	public void setPayload_id(Integer payload_id) {
		this.payload_id = payload_id;
	}
	
	//Declare override toString method
	@Override
	public String toString() {
		return "PayLoadClickedDTO [UUID=" + UUID + ", date=" + date + ", hostname=" + hostname + ", payload_id="
				+ payload_id + "]";
	}
}
