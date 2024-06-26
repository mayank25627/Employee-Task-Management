package com.EmployeeManagement.controller;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.EmployeeManagement.model.Employee;
import com.EmployeeManagement.model.Task;
import com.EmployeeManagement.service.EmployeeService;
import com.EmployeeManagement.service.TaskService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/employeeLog")
public class EmployeeController 
{
	@Autowired
	private EmployeeService empServ;
	
	@Autowired TaskService taskServ;
	
	@PostMapping("/login")
	public String login(HttpSession session , @RequestBody Employee loginObj)
	{
		return empServ.login(session,loginObj);
	}
	
	 @GetMapping("/getCurrentEmployee")
	 public Employee getCurrentEmployee(HttpSession session) 
	 {
	     Employee employee = (Employee) session.getAttribute("user");
	     return employee;
	 }
	 
	 @GetMapping("/getAdmin/{adminId}")
	    public ResponseEntity<?> getAdminById(@PathVariable int adminId) {
	        Optional<Admin> admin = empServ.getAdminById(adminId);
	        if (admin.isPresent()) {
	            return ResponseEntity.ok(admin.get());
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	
	@PostMapping("/addTask")
	public String addTask(HttpSession session , @RequestBody Task task )
	{
		return empServ.addTask(session, task);
	}
	
	@GetMapping("/viewTask")
	public ArrayList<Task> viewTask(HttpSession session)
	{
		return empServ.viewTask(session);
	}
	
	@PutMapping("/update/{taskId}/{status}")
	public String updateTask(@PathVariable Integer taskId, @PathVariable String status)
	{
		return taskServ.update(taskId,status);
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
