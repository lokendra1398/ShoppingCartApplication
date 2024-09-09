package com.codebuffer.OrderService.model;

import java.time.Instant;
//
//import com.codebuffer.PaymentService.model.PaymentMode ;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {

	
	private Long orderId;
	private Instant orderDate;
	private  String orderStatus;
	private long  amount;
	private ProductDetails productDetails;
	private PaymentDetails paymentDetails ;
	
	//we have added the Product response also 
	
	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class ProductDetails {

		private String productName;
		private long productId;
		private long quantity;
		private long price;
		
		
	}
	
	@Data
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class PaymentDetails {

		private long paymentId;
		private PaymentMode paymentMode;

		private String paymentStatus;
		private Instant paymentDate;
		
		
	}
	
	

}
