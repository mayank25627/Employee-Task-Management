package com.EmployeeManagement.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EmployeeManagement.model.Admin;
import com.EmployeeManagement.model.Request;
import com.EmployeeManagement.model.SuperAdmin;
import com.EmployeeManagement.repository.AdminRepo;
import com.EmployeeManagement.repository.ReqRepository;
import com.EmployeeManagement.service.AdminService;
import com.EmployeeManagement.service.SuperAdminService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/superAdmin")
public class SuperAdminController 
{
	@Autowired
	private SuperAdminService serv;
	
	@Autowired         
	private ReqRepository reqRepo;
	
	@Autowired
	private AdminService adminServ ;
	
	@PostMapping("/login")
	public String login(HttpSession session , @RequestBody SuperAdmin loginObj)
	{
		return serv.login(session,loginObj);
	}
	
	@GetMapping("/requests")
	public ArrayList<Request> requests()
	{
		return reqRepo.findByStatus("Pending"); 
	}
	
	
	@PutMapping("/update/{adminId}/{status}")
	public String  updateStatus(@PathVariable Integer adminId, @PathVariable String status)
	{
		Optional<Request> optional = reqRepo.findByadminId(adminId);
		
		Request obj = optional.get();
		obj.setStatus(status);
		reqRepo.save(obj);
		
		return  adminServ.updateStatus(adminId, status);

	}
	
	@GetMapping("/acceptedAdmins") 
    public List<Admin> getAcceptedAdmins() {
        return adminServ.getAcceptedAdmins();
    }
	
	@GetMapping("/logout")
	public ResponseEntity<Map<String, String>> logout(HttpSession session) {
	    session.invalidate();
	    Map<String, String> response = new HashMap<>();
	    response.put("message", "Logout successful");
	    response.put("redirect", "/index.html");
	    return ResponseEntity.ok(response);
	}


}
