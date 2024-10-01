package com.employee_management.Exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends RuntimeException {
private static HttpStatus status = null;

public UserNotFoundException(String message) {
	super(message);
	UserNotFoundException.status = null;
	
}

public UserNotFoundException(String message, HttpStatus status) {
	super(message);
	UserNotFoundException.status=status;
}
public static HttpStatus getStatus() {
	return UserNotFoundException.status;
}
	
}
