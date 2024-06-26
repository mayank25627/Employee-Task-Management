package com.EmployeeManagement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.EmployeeManagement.model.Admin;
import com.EmployeeManagement.model.SuperAdmin;
import com.EmployeeManagement.repository.SuperAdminRepo;

import jakarta.servlet.http.HttpSession;

@Service
public class SuperAdminService 
{
	@Autowired
	private SuperAdminRepo repo;

	

	public String login(HttpSession session, SuperAdmin loginObj)
	{
		Optional<SuperAdmin> optional = repo.findByEmail(loginObj.getEmail());
		SuperAdmin user ;
		
		if(optional.isPresent())
			user = optional.get();
		else
			return "user not found" ;
		
		if(!user.getPassword().equals(loginObj.getPassword()))
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
