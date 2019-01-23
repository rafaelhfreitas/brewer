package com.bigworks.brewer.controller.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bigworks.brewer.service.exception.NameStyleAlreadyExistsException;

@ControllerAdvice
public class ControllerAdviceExceptionHandler {
	
	@ExceptionHandler(NameStyleAlreadyExistsException.class)
	public ResponseEntity<String> handleNameStyleAlreadyExistsException(NameStyleAlreadyExistsException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}

}
