package com.EmployeeManagement.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EmployeeManagement.model.Task;
import com.EmployeeManagement.repository.TaskRepo;

@Service
public class TaskService 
{

	@Autowired
	private TaskRepo taskRepo;
	
	public String update(Integer taskId, String status) 
	{
		Optional<Task> optional = taskRepo.findById(taskId);
		
		Task task = optional.get();
		task.setTaskStatus(status);
		
		taskRepo.save(task);
		
		return "task updated sucessfully";
	}

	public ArrayList<Task> viewTaskOfEmp(Integer id) 
	{
		return taskRepo.findByEmpId(id);
	}
	

}
