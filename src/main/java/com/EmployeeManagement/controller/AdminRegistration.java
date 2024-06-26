package com.EmployeeManagement.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.EmployeeManagement.model.Admin;
import com.EmployeeManagement.service.AdminRegService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/adminlogReg")
public class AdminRegistration
{
	@Autowired
	private AdminRegService serv;
	
	@PostMapping("/registration")
	public String registration(@RequestBody Admin admin)
	{		
		if(admin == null) return "Please enter correct input";
		return serv.registration(admin);
	}
	
	@PostMapping("/login")
	public String login(HttpSession session , @RequestBody Admin loginObj)
	{
		return serv.login(session,loginObj);
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
