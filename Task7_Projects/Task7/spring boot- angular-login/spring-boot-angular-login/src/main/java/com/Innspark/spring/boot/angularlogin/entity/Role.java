
package com.Innspark.spring.boot.angularlogin.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


//
//@AllArgsConstructor
//@NoArgsConstructor

/*@AllArgsConstructor
@NoArgsConstructor*/
@Entity
@Table(name="roles")
//Role has properties id, name, privelege(List of Previlege), dte(create date of this role )
public class Role {
	
	

	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int id;
	
	private String id;

	
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String org;
	
	@Column(nullable = false)
	private List<String> privilege;
	
	@Column(nullable = false)
	private long created_date;

//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
	

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	

	
	public List<String> getPrivilege() {
		return privilege;
	}

	public void setPrivilege(List<String> privilege) {
		this.privilege = privilege;
	}

	public long getCreated_date() {
		return created_date;
	}

	public void setCreated_date(long created_date) {
		this.created_date = created_date;
	}
	
	

//	public Role(int id, String name, String org, List<String> privilege, long created_date) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.org = org;
//		this.privilege = privilege;
//		this.created_date = created_date;
//	}
	

	public Role() {
		super();
			}

	public Role(String id, String name, String org, List<String> privilege, long created_date) {
	super();
	this.id = id;
	this.name = name;
	this.org = org;
	this.privilege = privilege;
	this.created_date = created_date;
}
	
	

	public Role(String id, String name, String org) {
		super();
		this.id = id;
		this.name = name;
		this.org = org;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", org=" + org + ", privilege=" + privilege + ", created_date="
				+ created_date + "]";
	}
	

	
	

}
