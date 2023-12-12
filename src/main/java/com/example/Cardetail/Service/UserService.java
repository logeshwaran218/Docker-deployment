package com.example.Cardetail.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Cardetail.Entity.User;
import com.example.Cardetail.Repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userrepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public String adduser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userrepository.save(user);
		return "details added succesfully";
		
	}
}
