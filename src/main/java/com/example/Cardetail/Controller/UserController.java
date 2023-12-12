package com.example.Cardetail.Controller;

import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Cardetail.Entity.Authrequest;
import com.example.Cardetail.Entity.User;
import com.example.Cardetail.Service.JwtService;
import com.example.Cardetail.Service.UserService;

@RestController
@RequestMapping("/userdetails")
public class UserController {
	
	
	@Autowired
	private UserService userservice;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private AuthenticationManager authenticationmanager;
	
	@PostMapping("/new")
	public String addnewuser(@RequestBody User user) {
		return userservice.adduser(user);
	}
 
	@PostMapping("/authenticate")
	public String authenticateAndGetToken(@RequestBody Authrequest authrequest) {
		Authentication authentication=authenticationmanager.authenticate(new UsernamePasswordAuthenticationToken(authrequest.getUsername(),authrequest.getPassword()));
		if(authentication.isAuthenticated()) {
		return jwtService.generateToken(authrequest.getUsername());
		}
		else {
			throw new UsernameNotFoundException("invalid user request");
		}
		
	}
	
}
