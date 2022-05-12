package com.shoesstation.cuoikididong.exception;


import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	Logger logger=LoggerFactory.getLogger(GlobalExceptionHandler.class);
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleResourceNotFoundException(
			ResourceNotFoundException ex,WebRequest request){
		RestErrorResponse errorResponse=new RestErrorResponse(
				ex.getMessage(),HttpStatus.NOT_FOUND,LocalDateTime.now());
		return new ResponseEntity<Object>(errorResponse,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnwantedException(Exception ex,WebRequest request) {
		RestErrorResponse errorResponse=new RestErrorResponse(
				"Lỗi không xác định!",HttpStatus.INTERNAL_SERVER_ERROR,LocalDateTime.now());
		
		logger.warn(ex.toString());
		return new ResponseEntity<Object>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
}
