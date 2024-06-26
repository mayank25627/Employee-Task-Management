package com.EmployeeManagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EmployeeManagement.model.Admin;
import com.EmployeeManagement.model.Employee;
import com.EmployeeManagement.repository.AdminRepo;
import com.EmployeeManagement.repository.EmployeeRepo;

import jakarta.servlet.http.HttpSession;

@Service
public class AdminService 
{
	@Autowired
	private AdminRepo adminrepo;
	
	@Autowired 
	private EmployeeRepo empRepo;
	
	public String updateStatus(Integer adminId, String status)
	{
		Optional<Admin> optional = adminrepo.findById(adminId);
		Admin obj = optional.get();
		obj.setIsEnable(status);
		adminrepo.save(obj);
		
		return "Status updated successfully";
	}
	
	 public List<Admin> getAcceptedAdmins() {
	        return adminrepo.findByIsEnable("Accepted"); 
	  }

}
