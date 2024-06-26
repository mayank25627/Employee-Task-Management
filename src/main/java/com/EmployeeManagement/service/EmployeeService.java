package com.EmployeeManagement.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.representer.Represent;

import com.EmployeeManagement.model.Admin;
import com.EmployeeManagement.model.Employee;
import com.EmployeeManagement.model.Task;
import com.EmployeeManagement.repository.AdminRepo;
import com.EmployeeManagement.repository.EmployeeRepo;
import com.EmployeeManagement.repository.TaskRepo;

import jakarta.servlet.http.HttpSession;

@Service
public class EmployeeService 
{
	@Autowired
	private EmployeeRepo empRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired 
	private TaskRepo taskRepo;
	
	@Autowired
    private AdminRepo adminRepo;
	

	public ArrayList<Employee> getEmp(HttpSession session) 
	{
		Admin admin = (Admin) session.getAttribute("user");
		return empRepo.findByAdminId(admin.getAdminId()) ;
		
	}



	public String login(HttpSession session, Employee loginObj) {
        Optional<Employee> optional = empRepo.findByEmail(loginObj.getEmail());
        Employee user;

        if (optional.isPresent()) {
            user = optional.get();
        } else {
            return "User not found";
        }

        boolean passwordMatches = passwordEncoder.matches(loginObj.getPassword(), user.getPassword());

        if (!passwordMatches) {
            return "Password mismatched";
        }

        session.setAttribute("user", user);
        return "login successfull";
    }



	public String addTask(HttpSession session, Task task) 
	{
		Employee emp = (Employee) session.getAttribute("user");
		task.setEmpId(emp.getEmpId());
		taskRepo.save(task);
				
		return "task added sucessfully";
	}



	public ArrayList<Task> viewTask(HttpSession session) 
	{
		Employee user = (Employee) session.getAttribute("user");
		
		return taskRepo.findByEmpId(user.getEmpId());
	
	}
	
	public void deleteEmployeeById(Integer empId) {
        empRepo.deleteById(empId);
    }
	
	public Optional<Admin> getAdminById(Integer adminId) {
        return adminRepo.findById(adminId);
    }
}
