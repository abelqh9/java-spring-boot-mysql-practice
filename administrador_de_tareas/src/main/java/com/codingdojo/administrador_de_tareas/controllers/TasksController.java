package com.codingdojo.administrador_de_tareas.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.codingdojo.administrador_de_tareas.models.Task;
import com.codingdojo.administrador_de_tareas.models.User;
import com.codingdojo.administrador_de_tareas.services.TaskService;
import com.codingdojo.administrador_de_tareas.services.UserService;

@Controller
public class TasksController {
	
	final TaskService taskService;
	final UserService userService;
	
	public TasksController(TaskService taskService, UserService userService) {
		this.taskService = taskService;
		this.userService = userService;
	}
	
	@GetMapping("/tasks")
	public String showTasks(
			Model model, 
			HttpSession session) {
		
		Long userId = (Long)session.getAttribute("userId");
		
		if( userId != null ) {
			
			List<Task> tasks;
			
			if( session.getAttribute("order") !=null ) {
				int order =  (int)session.getAttribute("order");
				if(order == 1) {
					tasks = this.taskService.getTasksDesc();				
				}else {
					tasks = this.taskService.getTasksAsc();				
				}
			}else {
				tasks = this.taskService.getTasks();				
			}
			
			User user = this.userService.getUserById(userId);
			
			model.addAttribute("user", user);
			model.addAttribute("tasks", tasks);
			
			return "tasks.jsp";
			
		}else {
			return "redirect:/";
		}
	}
	
	
	
	@GetMapping("/tasks/{task_id}")
	public String showTask(
			@PathVariable("task_id") Long task_id,
			Model model, 
			HttpSession session) {
		
		Long userId = (Long)session.getAttribute("userId");
				
		if( userId != null ) {
			
			User user = this.userService.getUserById(userId);
			Task task = this.taskService.getTaskById(task_id);
			
			model.addAttribute("user", user);
			model.addAttribute("task", task);
			
			return "showTask.jsp";
			
		}else {
			return "redirect:/";
		}
	}
	
	
	
	@GetMapping("/tasks/new")
	public String showNewTaskForm(
			Model model, 
			HttpSession session) {
		
		Long userId = (Long)session.getAttribute("userId");
		
		if( userId != null ) {
			
			User user = this.userService.getUserById(userId);
			List<User> users = this.userService.getUsers();
			Task task = new Task();
			
			model.addAttribute("user", user);
			model.addAttribute("users", users);
			model.addAttribute("model_task", task);
			
			return "newTask.jsp";
		}else {
			return "redirect:/";
		}
	}
	@PostMapping("/tasks/new")
	public String newTask(
			Model model,
			@Valid @ModelAttribute("model_task") Task task,
			BindingResult result) {
		
		List<User> users = this.userService.getUsers();
		
		if ( this.taskService.createTask(task, result) != null ) {
			return "redirect:/tasks";
		}else {
			model.addAttribute("users", users);
			return "newTask.jsp";
		}
	}
	
	
	
	@GetMapping("/tasks/{task_id}/edit")
	public String show_editTaskForm(
			Model model, 
			HttpSession session,
			@PathVariable("task_id") Long task_id) {
		
		Long userId = (Long)session.getAttribute("userId");
		
		if( userId != null ) {
			
			User user = this.userService.getUserById(userId);
			List<User> users = this.userService.getUsers();
			Task task = this.taskService.getTaskById(task_id);
			Boolean isTheUser = user.getTasks().contains(task);
			
			if ( isTheUser ) {
				model.addAttribute("user", user);
				model.addAttribute("users", users);
				model.addAttribute("model_task", task);
				model.addAttribute("task_name", task.getTask());
				
				return "editTask.jsp";
			}else {
				return "redirect:/";
			}
			
		}else {
			return "redirect:/";
		}
	}
	@PutMapping("/tasks/{task_id}/edit")
	public String editTask(
			Model model,
			HttpSession session, 
			@Valid @ModelAttribute("model_task") Task task,
			BindingResult result,
			@PathVariable("task_id") Long task_id) {
		
		Long userId = (Long)session.getAttribute("userId");
		
		if( userId != null ) {
			
			User user = this.userService.getUserById(userId);
			List<User> users = this.userService.getUsers();
			Task oldTask = this.taskService.getTaskById(task_id);
			Boolean isTheUser = user.getTasks().contains(oldTask);

			if ( isTheUser ) {
				if( this.taskService.editTask(task, result) != null ) {				
					return "redirect:/tasks";
				}else {
					model.addAttribute("user", user);
					model.addAttribute("users", users);
					model.addAttribute("task_name", oldTask.getTask());
					
					return "editTask.jsp";
				}
				
			}else {
				return "redirect:/";
			}
			
		}else {
			return "redirect:/";
		}
	}
	
	
	
	@DeleteMapping("/tasks/{task_id}/delete")
	public String deleteTask(
			HttpSession session, 
			@PathVariable("task_id") Long task_id) {
		
		Long userId = (Long)session.getAttribute("userId");
		
		if( userId != null ) {
			
			User user = this.userService.getUserById(userId);
			Task task = this.taskService.getTaskById(task_id);
			Boolean isTheUser = user.getTasks().contains(task);
			
			if( isTheUser ) {
				this.taskService.deleteTaskById(task_id);
				return "redirect:/tasks";
			}else {
				return "redirect:/";
			}
			
		}else {	
			return "redirect:/";
		}		
	}
	
	@DeleteMapping("/tasks/{task_id}/complete")
	public String completeTask(
			HttpSession session,
			@PathVariable("task_id") Long task_id) {
		
		Long userId = (Long)session.getAttribute("userId");
		
		if( userId != null ) {
			
			User user = this.userService.getUserById(userId);
			Task task = this.taskService.getTaskById(task_id);
			Boolean isTheAssignedUser = task.getAssigned_user().equals(user);
			
			if( isTheAssignedUser ) {
				this.taskService.deleteTaskById(task_id);
				return "redirect:/tasks";
			}else {
				return "redirect:/";
			}
			
		}else {	
			return "redirect:/";
		}		
	}
	
	@PostMapping("/order/1")
	public String changeorder1(HttpSession session) {
		
		session.setAttribute("order", 1);

		return "redirect:/tasks";
	}
	
	@PostMapping("/order/2")
	public String changeorder2(HttpSession session) {
		
		session.setAttribute("order", 2);

		return "redirect:/tasks";
	}

}
