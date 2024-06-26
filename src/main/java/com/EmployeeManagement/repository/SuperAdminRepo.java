package com.EmployeeManagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EmployeeManagement.model.Admin;
import com.EmployeeManagement.model.Request;
import com.EmployeeManagement.model.SuperAdmin;

public interface SuperAdminRepo extends JpaRepository<SuperAdmin, Integer>
{

	Optional<SuperAdmin> findByEmail(String email);


}
