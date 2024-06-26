package com.EmployeeManagement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.representer.Represent;

import com.EmployeeManagement.model.Admin;
import com.EmployeeManagement.model.Request;
import com.EmployeeManagement.repository.AdminRepo;
import com.EmployeeManagement.repository.ReqRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class AdminRegService 
{
	
	@Autowired
	private AdminRepo adminrepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ReqRepository reqRepo;
	
	public String registration(Admin admin) 
	{
		Optional<Admin> optional = adminrepo.findByEmail(admin.getEmail()) ;	
		if(optional.isPresent())
		{
			Admin user = optional.get();
			
			if(user.getIsEnable().equals("Pending"))
				return "request pending";
			else if(user.getIsEnable().equals("Rejected"))
				return "Request Rejected";
			
			
		}
		
		String password = passwordEncoder.encode(admin.getPassword());
		
		
		admin.setPassword(password);
		admin.setIsEnable("Pending");
		
		Request req = new Request();
		req.setAdminName(admin.getName());
		req.setEmail(admin.getEmail());
		
		
		adminrepo.save(admin);
		
		Optional<Admin> obj =  adminrepo.findByEmail(admin.getEmail());
		Admin us = obj.get();
		
		System.out.println();
		
		req.setAdminId(us.getAdminId());
		req.setStatus("Pending");
		
		reqRepo.save(req);
		
		return "Registration done Wait for Super admin to accept or reject request";
		
		

	}

	public String login(HttpSession session, Admin loginObj)
	{
		Optional<Admin> optional = adminrepo.findByEmail(loginObj.getEmail());
		Admin user;
		
		
		if(optional.isPresent())
			user = optional.get();
		else
			return "Please Register First" ;
		
		if(!user.getIsEnable().equals("Accepted"))return user.getIsEnable();
		
        boolean passwordMatches = passwordEncoder.matches(loginObj.getPassword(), user.getPassword());
		if(!passwordMatches)
		{
			return "password mismatched";
		}
		else 
		{
			session.setAttribute("user", user);
			return "successfull";
		}
	}
	
}
