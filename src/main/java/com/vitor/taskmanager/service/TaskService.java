package com.vitor.taskmanager.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitor.taskmanager.model.Task;
import com.vitor.taskmanager.model.User;
import com.vitor.taskmanager.repository.TaskRepository;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	public Task createTask(Task task, User user) {
		task.setUser(user);
		task.setCreatedAt(LocalDateTime.now());
		return taskRepository.save(task);
	}
	
	public List<Task> getUserTasks(User user){
		return taskRepository.findByUser(user);
	}
	
	public Optional<Task> getTaskById(Long id, User user){
		return taskRepository.findById(id)
				.filter(task -> task.getUser().equals(user));
		
	}
	
	public Optional<Task>updateTask(Long id, Task updatedTask, User user){
		return getTaskById(id, user).map(task ->{
            task.setTitle(updatedTask.getTitle());
            task.setDescription(updatedTask.getDescription());
            task.setPriority(updatedTask.getPriority());
            task.setStatus(updatedTask.getStatus());
            task.setDueDate(updatedTask.getDueDate());
            return taskRepository.save(task);
		});
	}
	
	public boolean deleteTask(Long id, User user) {
		return getTaskById(id, user).map(task -> {
			taskRepository.delete(task);
			return true;
		}).orElse(false);
	}

}
