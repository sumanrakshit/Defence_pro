package com.Innspark.spring.boot.angularlogin.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
public class TemplateFields {
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Integer id;
	private String id;
	@Column(nullable = false)
	private boolean has_user;
	@Column(nullable = false)
	private boolean has_host;
	@Column(nullable = false)
	private List<String> addfields;
	@Column(nullable = false)
	private String decoyId;
//	public Integer getId() {
//		return id;
//	}
//	public void setId(Integer id) {
//		this.id = id;
//	}
	
	public boolean isHas_user() {
		return has_user;
	}
	public void setHas_user(boolean has_user) {
		this.has_user = has_user;
	}
	public boolean isHas_host() {
		return has_host;
	}
	public void setHas_host(boolean has_host) {
		this.has_host = has_host;
	}
	public List<String> getAddfields() {
		return addfields;
	}
	public void setAddfields(List<String> addfields) {
		this.addfields = addfields;
	}
//	public int getDecoyId() {
//		return decoyId;
//	}
//	public void setDecoyId(int decoyId) {
//		this.decoyId = decoyId;
//	}
	
	public TemplateFields(String id, boolean has_user, boolean has_host, List<String> addfields, String decoyId) {
		super();
		this.id = id;
		this.has_user = has_user;
		this.has_host = has_host;
		this.addfields = addfields;
		this.decoyId = decoyId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDecoyId() {
		return decoyId;
	}
	public void setDecoyId(String decoyId) {
		this.decoyId = decoyId;
	}
	public TemplateFields() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "TemplateFields [id=" + id + ", has_user=" + has_user + ", has_host=" + has_host + ", addfields="
				+ addfields + ", decoyId=" + decoyId + "]";
	}
	
	

}
