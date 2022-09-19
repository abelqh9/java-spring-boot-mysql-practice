package com.codingdojo.administrador_de_tareas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.codingdojo.administrador_de_tareas.models.Task;
import com.codingdojo.administrador_de_tareas.repositories.TaskRepository;

@Service
public class TaskService {
	
	final TaskRepository taskRepository;
	
	public TaskService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}
	
	public List<Task> getTasks() {
		return this.taskRepository.findAll();
	}
	
	public List<Task> getTasksDesc() {
		return this.taskRepository.findAll_DESC();
	}
	
	public List<Task> getTasksAsc() {
		return this.taskRepository.findAll_ASC();
	}
	
	public Task getTaskById(Long id) {
		Optional<Task> result = this.taskRepository.findById(id);
		
		if( result.isPresent() ){
			return result.get();
		}else {
			return null;
		}
	}
	
	public Task createTask(Task newTask, BindingResult result) {
        if(!result.hasErrors()) {
        	return this.taskRepository.save(newTask);        	
        }else {
        	return null;
        }
	}
	
	public Task editTask(Task task, BindingResult result) {
        if(!result.hasErrors()) {
        	return this.taskRepository.save(task);        	
        }else {
        	return null;
        }
	}
	
	public void deleteTaskById(Long id) {
		this.taskRepository.deleteById(id);
	}
	
	public void completeTask(Task task) {
		this.taskRepository.delete(task);
	}
}
