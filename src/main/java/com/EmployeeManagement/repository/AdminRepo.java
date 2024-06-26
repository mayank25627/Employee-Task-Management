package com.EmployeeManagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EmployeeManagement.model.Admin;

public interface AdminRepo extends JpaRepository<Admin, Integer>

{

	Optional<Admin> findByEmail(String email);
	List<Admin> findByIsEnable(String isEnable);

}
