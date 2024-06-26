package com.EmployeeManagement.controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.EmployeeManagement.repository.EmployeeRepo;
import com.EmployeeManagement.service.AdminService;
import com.EmployeeManagement.service.EmployeeService;
import com.EmployeeManagement.service.TaskService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/adminController")
public class AdminController 
{
	@Autowired 
	private EmployeeRepo empRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired EmployeeService empServ;
	
	@Autowired
	private AdminService adminServ;
	
	@Autowired
	private TaskService taskServe;
	
	@PostMapping("/addEmployee")
	public String addEmployee(HttpSession session,@RequestBody Employee emp)
	{
		Optional<Employee> optional = empRepo.findByEmail(emp.getEmail());
		if(optional.isPresent())return "employee with this email is already present";
		
		Admin admin = (Admin) session.getAttribute("user");
		emp.setAdminId(admin.getAdminId());
		
		String pass = passwordEncoder.encode(admin.getPassword());
		emp.setPassword(pass);
		empRepo.save(emp);
		return "Employee added successfully";
	}
	
	@GetMapping("/getEmployees")
	public ArrayList<Employee> getEmp(HttpSession session)
	{
		return empServ.getEmp(session);
	}
	
	@GetMapping("/getEmployee/{empId}")
	public ResponseEntity<Employee> getEmployee(@PathVariable Integer empId) {
	    Optional<Employee> employeeOptional = empRepo.findById(empId);
	    if (employeeOptional.isPresent()) {
	        return ResponseEntity.ok(employeeOptional.get());
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    }
	}
	
	 @PutMapping("/updateEmployee")
	    public String updateEmployee(HttpSession session, @RequestBody Employee emp) {
	        Optional<Employee> optional = empRepo.findById(emp.getEmpId());
	        if(optional.isPresent()) {
	            Employee existingEmployee = optional.get();
	            existingEmployee.setName(emp.getName());
	            existingEmployee.setEmail(emp.getEmail());
	            existingEmployee.setPhone(emp.getPhone());
	            existingEmployee.setAddress(emp.getAddress());
	            // Only update password if it is provided
	            if (emp.getPassword() != null && !emp.getPassword().isEmpty()) {
	                String pass = passwordEncoder.encode(emp.getPassword());
	                existingEmployee.setPassword(pass);
	            }
	            empRepo.save(existingEmployee);
	            return "Employee updated successfully";
	        } else {
	            return "Employee not found";
	        }
	 }
	
	 @GetMapping("/viewTasks/{id}")
	 public ResponseEntity<ArrayList<Task>> viewTasks(@PathVariable Integer id) {
	     ArrayList<Task> tasks = taskServe.viewTaskOfEmp(id);
	     return new ResponseEntity<>(tasks, HttpStatus.OK);
	 }
	
	@DeleteMapping("/deleteEmployee/{empId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer empId) {
        Optional<Employee> employeeOptional = empRepo.findById(empId);
        if (employeeOptional.isPresent()) {
            empServ.deleteEmployeeById(empId);
            return ResponseEntity.ok("Employee deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
        }
    }
	
	
}
