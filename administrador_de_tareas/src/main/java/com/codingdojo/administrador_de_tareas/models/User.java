package com.codingdojo.administrador_de_tareas.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message="¡Name must be present!")
    private String name;
    
    @NotEmpty(message="¡Email must be present!")
    @Email(message="¡Email must be valid format!")
    private String email;
    
    @NotEmpty(message="¡Password must be present!")
    @Size(min=8, message="Password must be at least 8 characters long")
    private String password;
    
    @Transient
    @NotEmpty(message="Confirm Password must be present!")
    @Size(min=8, max=128, message="Confirm Password must match with the password")
    private String confirm;
    
    @Column(updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-DD")
    private Date created_at;
    
    @DateTimeFormat(pattern = "yyyy-MM-DD")
    private Date updated_at;
    
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private List<Task> tasks;
    
    @OneToMany(mappedBy="assigned_user", fetch = FetchType.LAZY)
    private List<Task> assigned_tasks;
  
    public User() {}
    
    public User(String name, String email, String password, String confirm) {
    	this.name = name;
    	this.email = email;
    	this.password = password;
    	this.confirm = confirm;
    }
    
    @PrePersist
    public void onCreated() {
    	this.created_at = new Date();
    }
    
    @PreUpdate
    public void onUpdated() {
    	this.updated_at = new Date();
    }
    
    //GETTERS AND SETTERS

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public List<Task> getAssigned_tasks() {
		return assigned_tasks;
	}

	public void setAssigned_tasks(List<Task> assigned_tasks) {
		this.assigned_tasks = assigned_tasks;
	}
}