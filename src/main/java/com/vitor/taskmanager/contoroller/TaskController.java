package com.vitor.taskmanager.contoroller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitor.taskmanager.dto.TaskDTO;
import com.vitor.taskmanager.model.Task;
import com.vitor.taskmanager.model.User;
import com.vitor.taskmanager.service.TaskService;
import com.vitor.taskmanager.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public Task createTask(@RequestBody @Valid TaskDTO dto, Principal principal) {
		
		User user = getUserFromPrincipal(principal);
		
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setPriority(dto.getPriority());
        task.setStatus(dto.getStatus());
        task.setDueDate(dto.getDueDate());
        return taskService.createTask(task, user);

	}
	
	@GetMapping
	public List<Task> getUserTasks(Principal principal){
		User user = getUserFromPrincipal(principal);
		return taskService.getUserTasks(user);
		
	}
	
	
	public Optional<Task> updateTask(@PathVariable Long id, @RequestBody @Valid TaskDTO dto,Principal principal){
        User user = getUserFromPrincipal(principal);
        Task updated = new Task();
        updated.setTitle(dto.getTitle());
        updated.setDescription(dto.getDescription());
        updated.setPriority(dto.getPriority());
        updated.setStatus(dto.getStatus());
        updated.setDueDate(dto.getDueDate());
        return taskService.updateTask(id, updated, user);
        
	}
	
	
	public User getUserFromPrincipal(Principal principal) {
		return userService.findByEmail(principal.getName())
				.orElseThrow(() -> new RuntimeException("User not found"));
	}
	
	
	
	

}
