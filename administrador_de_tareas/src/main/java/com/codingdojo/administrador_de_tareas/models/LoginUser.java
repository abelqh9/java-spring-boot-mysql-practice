package com.codingdojo.administrador_de_tareas.models;

import javax.validation.constraints.NotEmpty;

public class LoginUser {

    @NotEmpty(message="Email is required!")
    private String email;
    
    @NotEmpty(message="Password is required!")
    private String password;
    
    public LoginUser() {}
    
    public LoginUser(String email,String password) {
    	this.email = email;
    	this.password = password;
    }

    //GETTERS AND SETTERS
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
}
