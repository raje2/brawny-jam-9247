package com.project.beanclass;

public class Engineer {
	
	private String name;
	private String email;
	private String password;
	private String category;
	
	
	public Engineer() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Engineer(String name, String email, String password, String category) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.category = category;
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}


	@Override
	public String toString() {
		return "Engineer [name=" + name + ", email=" + email + ", password=" + password + ", category=" + category
				+ "]";
	}
	
	
	

}
