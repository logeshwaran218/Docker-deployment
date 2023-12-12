package com.example.Cardetail.extension;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;

@RestControllerAdvice
public class CustomExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ProblemDetail handleSecurityException(Exception ex) {
		ProblemDetail errordetail=null;
		if(ex instanceof BadCredentialsException) {
			 errordetail=ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401),ex.getMessage());
			errordetail.setProperty("access_denied","Authentication failure");
		}
		if(ex instanceof AccessDeniedException) {
			 errordetail=ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403),ex.getMessage());
			errordetail.setProperty("access_denied","not Authorised!");
		}
		if(ex instanceof SignatureException) {
			 errordetail=ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403),ex.getMessage());
			errordetail.setProperty("access_denied","Jwt signatureException");
		}
		if(ex instanceof ExpiredJwtException) {
			 errordetail=ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403),ex.getMessage());
			errordetail.setProperty("access_denied","Jwt token Expired");
		}
		return errordetail;
		
	}
	

}
