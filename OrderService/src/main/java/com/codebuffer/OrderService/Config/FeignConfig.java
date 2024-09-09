package com.codebuffer.OrderService.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.codebuffer.OrderService.external.Decoder.CustomErrorDecoder;

import feign.codec.ErrorDecoder;

@Configuration
public class FeignConfig {

	
	
	@Bean
	ErrorDecoder errorDecoder() {
		return new CustomErrorDecoder();
	}
	
}
