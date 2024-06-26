package com.EmployeeManagement.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EmployeeManagement.model.Request;

public interface ReqRepository extends JpaRepository<Request, Integer>
{

	ArrayList<Request> findByStatus(String string);

	Optional<Request> findByadminId(Integer adminId);

}
