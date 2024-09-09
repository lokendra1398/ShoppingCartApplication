package com.codebuffer.PaymentService.service;

import com.codebuffer.PaymentService.model.PaymentRequest;
import com.codebuffer.PaymentService.model.PaymentResponse;

public interface PaymentService {

	Long doPayment(PaymentRequest paymentRequest);

	PaymentResponse getPaymentDetailsByOrderId(String orderId);



}
