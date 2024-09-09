package com.codebuffer.OrderService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.codebuffer.OrderService.external.response.ErrorResponse;





//by   giving controleradvice annotation we can handle excpetions in our controller 
@ControllerAdvice
public class RestResponseEntityExceptionHandler  extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ErrorResponse> handleProductServiceException(CustomException Exception){
		return new ResponseEntity<>(new ErrorResponse().builder()
				.errorMessage(Exception.getMessage())
				.errorCode(Exception.getErrorCode())
				.build(),HttpStatus.valueOf(Exception.getStatus()));
	
	
	}
	
}
