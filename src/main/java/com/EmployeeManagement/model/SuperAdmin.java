package com.EmployeeManagement.model;

import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="supAdmin")
public class SuperAdmin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int supid;

	String name;
	String email;
	String password;
	ArrayList<Request> req;
	
	
	public ArrayList<Request> getReq() {
		return req;
	}
	public void setReq(ArrayList<Request> req) {
		this.req = req;
	}
	public int getSupid() {
		return supid;
	}
	public void setSupid(int supid) {
		this.supid = supid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public SuperAdmin(int supid, String name, String email, String password, ArrayList<Request> req) {
		super();
		this.supid = supid;
		this.name = name;
		this.email = email;
		this.password = password;
		this.req = req;
	}
	public SuperAdmin() {
		
	}
	

}
