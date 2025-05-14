package com.vitor.taskmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vitor.taskmanager.model.Task;
import com.vitor.taskmanager.model.User;

public  interface TaskRepository extends JpaRepository<Task, Long> {
	
	List<Task>findByUser(User user);

}
