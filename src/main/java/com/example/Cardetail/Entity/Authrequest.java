package com.example.Cardetail.Entity;

public class Authrequest {
	private String username; 
    private String password;
	@Override
	public String toString() {
		return "Authrequest [username=" + username + ", password=" + password + "]";
	}
	public Authrequest() {
		super();
	}
	public Authrequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	} 
}
