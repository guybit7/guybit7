package com.in28minutes.restfulwebservices.exceptions;

import java.util.Date;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@RestController
public class CustomizedResponseEntityHandler extends ResponseEntityExceptionHandler {
	
	  private static final Logger logger = LogManager.getLogger(CustomizedResponseEntityHandler.class);  
	  
	public CustomizedResponseEntityHandler() {
		System.out.println("---> CustomizedResponseEntityHandler");
	}
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest req) {
		ExceptionResponse theExceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), req.getDescription(false)); 
		return new ResponseEntity(theExceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundExceptions(UserNotFoundException ex, WebRequest req) {
		ExceptionResponse theExceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), req.getDescription(false));
		logger.error("BALAGAN");  
		return new ResponseEntity(theExceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	
	@Override
	public final ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,HttpHeaders headers,HttpStatus status,WebRequest request) {
		ExceptionResponse theExceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), "validation failed");
		return new ResponseEntity(theExceptionResponse, HttpStatus.BAD_REQUEST);
	}

}
