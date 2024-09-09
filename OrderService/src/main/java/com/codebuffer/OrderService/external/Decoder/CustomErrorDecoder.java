package com.codebuffer.OrderService.external.Decoder;

import java.io.IOException;

import com.codebuffer.OrderService.exception.CustomException;
import com.codebuffer.OrderService.external.response.ErrorResponse;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class CustomErrorDecoder  implements ErrorDecoder {

	//used for converting json to java objects
	@Override
	public Exception decode(String s, Response response) {
		// TODO Auto-generated method stub
		ObjectMapper obj = new ObjectMapper();
		
		log.info(response.request().url());
		log.info(response.request().headers());
		
		try {
			ErrorResponse errorResponse =  obj.readValue(response.body().asInputStream(),ErrorResponse.class);
			
			return new CustomException(errorResponse.getErrorMessage(),errorResponse.getErrorCode(),response.status());}
//		} catch (StreamReadException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (DatabindException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		 catch (IOException e) {
			// TODO Auto-generated catch block
			 throw  new   CustomException("Internal Server Error","INTERNAL_SERVER_ERROR",500);
			
		}
		
		
	}

}
