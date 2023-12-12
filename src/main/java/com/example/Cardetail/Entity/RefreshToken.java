//package com.example.Cardetail.Entity;
//
//import java.time.Instant;
//
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.OneToOne;
//
//public class RefreshToken {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY) 
//	private int id;
//	private String token;
//	private Instant expiryDate;
//	 @OneToOne
//	 @JoinColumn(name="user_id",referencedColumnName="id")
//	private User user;
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
//	public String getToken() {
//		return token;
//	}
//	public void setToken(String token) {
//		this.token = token;
//	}
//	public Instant getExpiryDate() {
//		return expiryDate;
//	}
//	public void setExpiryDate(Instant expiryDate) {
//		this.expiryDate = expiryDate;
//	}
//	public User getUser() {
//		return user;
//	}
//	public void setUser(User user) {
//		this.user = user;
//	}
//	public RefreshToken(int id, String token, Instant expiryDate, User user) {
//		super();
//		this.id = id;
//		this.token = token;
//		this.expiryDate = expiryDate;
//		this.user = user;
//	}
//	public RefreshToken() {
//		super();
//	}
//	@Override
//	public String toString() {
//		return "RefreshToken [id=" + id + ", token=" + token + ", expiryDate=" + expiryDate + ", user=" + user + "]";
//	}
//
//}
