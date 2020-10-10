package com.app.models;

public class User {
	private int user_id; 
	private String username; 
	private String pass_word;
	private String first_name; 
	private String last_name; 
	private String email; 
	private String updatePassword;

	public String getUpdatePassword() {
		return updatePassword;
	}


	public void setUpdatePassword(String updatePassword) {
		this.updatePassword = updatePassword;
	}
	private Role role;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public User(int userId, String username, String firstName, String lastName, String email,
			Role role) {
		super();
		this.user_id = userId;
		this.username = username;		
		this.first_name = firstName;
		this.last_name = lastName;
		this.email = email;
		this.role = role;
	}
	
	public User(String username, String password, String firstName, String lastName, String email, Role role) {
		super();		
		this.username = username;	
		this.pass_word = password;
		this.first_name = firstName;
		this.last_name = lastName;
		this.email = email;
		this.role = role;
	}
	
	public User(int userId, String username, String password, String firstName, String lastName, String email, Role role) {
		super();
		this.user_id = userId;
		this.username = username;
		this.pass_word = password;
		this.role = role;
		this.first_name = firstName;
		this.last_name = lastName;
		this.email = email;
	}

	public int getUserId() {
		return user_id;
	}
	public void setUserId(int userId) {
		this.user_id = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return pass_word;
	}
	public void setPassword(String password) {
		this.pass_word = password;
	}
	public String getFirstName() {
		return first_name;
	}
	public void setFirstName(String firstName) {
		this.first_name = firstName;
	}
	public String getLastName() {
		return last_name;
	}
	public void setLastName(String lastName) {
		this.last_name = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "User [userId=" + user_id + ", username=" + username + ", password=" + pass_word + ", firstName="
				+ first_name + ", lastName=" + last_name + ", email=" + email + ", role=" + role + "]";
	}

}
