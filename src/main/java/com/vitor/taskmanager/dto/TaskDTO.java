package com.vitor.taskmanager.dto;

import java.time.LocalDateTime;

import com.vitor.taskmanager.model.Priority;
import com.vitor.taskmanager.model.Status;

import jakarta.validation.constraints.NotBlank;

public class TaskDTO {
	
	
	@NotBlank(message = "Title is required")
	private String title;
	
	private String description;
	private Priority priority;
	private Status status;
	private LocalDateTime dueDate;

	
	public String getTitle() {return title;}
	public void setTitle(String title) {this.title = title; }
	
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }
	
	public Priority getPriority() { return priority; }
	public void setPriority(Priority priority) { this.priority = priority; }
	
	public Status getStatus() { return status; }
	public void setStatus(Status status) { this.status = status; }
	
	public LocalDateTime getDueDate() { return dueDate; }
	public void setDueDate(LocalDateTime dueDate) { this.dueDate = dueDate; }
}
