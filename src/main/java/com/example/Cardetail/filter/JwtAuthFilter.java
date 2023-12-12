package com.example.Cardetail.filter;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.example.Cardetail.Entity.User;
import com.example.Cardetail.Repository.UserRepository;
import com.example.Cardetail.Service.JwtService;
import com.example.Cardetail.Service.UserUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class JwtAuthFilter extends OncePerRequestFilter {
	
	private HandlerExceptionResolver exceptionResolver;
	
	
	@Autowired
	private JwtService jwtservice;
	
	@Autowired
	private UserUserDetailsService userDetailsService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	public JwtAuthFilter(HandlerExceptionResolver exceptionResolver) {
		this.exceptionResolver=exceptionResolver;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		String authHeader=request.getHeader("Authorization");
		
		String token=null;
		String username=null;
		try {
		if(authHeader!=null && authHeader.startsWith("Bearer ")) {
			token=authHeader.substring(7);
			username=jwtservice.extractUsername(token);
		}
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			UserDetails userDetails=userDetailsService.loadUserByUsername(username);
			
			if(jwtservice.validateToken(token,userDetails)) {
				UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
			
		}
	
		filterChain.doFilter(request, response);
		}catch(Exception ex)
		{
			exceptionResolver.resolveException(request, response,null, ex);
		}
	}
	
		public Optional<User> getUserId(HttpServletRequest request) {
		final String authHeader = request.getHeader("Authorization");
	    String username = null;
	    String jwt = null;
	    if (authHeader != null && authHeader.startsWith("Bearer")) {
	        jwt = authHeader.substring(7);
	        username = jwtservice.extractUsername(jwt);
	    }
	    
	    return userRepository.findByName(username);
		
	}

}
