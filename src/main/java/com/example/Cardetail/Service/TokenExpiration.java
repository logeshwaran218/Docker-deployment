//package com.example.Cardetail.Service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import jakarta.servlet.http.HttpServletRequest;
//
//@Service
//public class TokenExpiration {
//
//	private final JwtService jwtService;
//	@Autowired
//	public TokenExpiration(JwtService jwtService) {
//		this.jwtService=jwtService;
//	}
//	
//	public boolean checkTokenExpiry(String token) {
//		
//		return jwtService.validateToken(token);
//	}
//	public static String  getToken(HttpServletRequest request)
//	{
//		String authHeader=request.getHeader("Authorization");
//		String token=authHeader.substring(7);
//		return token;
//	}
//	
//}
