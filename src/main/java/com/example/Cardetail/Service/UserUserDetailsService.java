package com.example.Cardetail.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.Cardetail.Entity.User;
import com.example.Cardetail.Repository.UserRepository;
//@Component
@Service
public class UserUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> userinfo=repository.findByName(username);
		
		 return userinfo.map(UserInfoUserdetails::new)
		        .orElseThrow(()->new UsernameNotFoundException("user not found"+username));
		
	}

}
