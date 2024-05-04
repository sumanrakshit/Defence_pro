package com.Innspark.spring.boot.angularlogin.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//
//@AllArgsConstructor
//@NoArgsConstructor

@Data
@Getter
@Setter
@Entity

//previlege has properties id, name, createdate(create of this previlege date and time)
public class Previlege {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private long created_date;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
public long getCreated_date() {
		return created_date;
	}
	public void setCreated_date(long created_date) {
		this.created_date = created_date;
	}

	
	public Previlege() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Previlege(int id, String name, long created_date) {
		super();
		this.id = id;
		this.name = name;
		this.created_date = created_date;
	}
	@Override
	public String toString() {
		return "Previlege [id=" + id + ", name=" + name + ", created_date=" + created_date + "]";
	}
	
	
	
	

}
