package com.codebuffer.OrderService.external.response;

import java.time.Instant;

import com.codebuffer.OrderService.model.PaymentMode;

//import com.codebuffer.PaymentService.model.PaymentMode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponse {

	private long paymentId;
	private String status;
	private PaymentMode paymentMode;
	private long amount;
	private Instant  paymentDate;
	private long  orderId;
	
	
}
