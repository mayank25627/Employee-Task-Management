package com.EmployeeManagement.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EmployeeManagement.model.Employee;
import com.EmployeeManagement.model.Task;

import jakarta.servlet.http.HttpSession;

public interface EmployeeRepo extends JpaRepository<Employee, Integer>
{

	Optional<Employee> findByEmail(String email);

	ArrayList<Employee> findByAdminId(int adminId);

	
	
}
