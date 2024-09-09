package com.codebuffer.ProductService.Exception;

import lombok.Data;

@Data
public class ProductServiceCustomException  extends RuntimeException {

	
	 private String errorCode;
	 
	 public ProductServiceCustomException(String message,String errorCode) {
     super(message);  //this method will get executed when data is not available when we are calling findbyid in service layer
     this.errorCode = errorCode;
	 
	 }
}