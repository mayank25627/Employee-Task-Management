package com.EmployeeManagement.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.EmployeeManagement.model.Task;

public interface TaskRepo extends JpaRepository<Task, Integer>
{

	ArrayList<Task> findByEmpId(int empId);

}
