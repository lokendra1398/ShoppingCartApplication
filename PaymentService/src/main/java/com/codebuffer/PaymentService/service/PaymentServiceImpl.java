package com.codebuffer.PaymentService.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codebuffer.PaymentService.entity.TransactionDetails;
import com.codebuffer.PaymentService.model.PaymentMode;
import com.codebuffer.PaymentService.model.PaymentRequest;
import com.codebuffer.PaymentService.model.PaymentResponse;
import com.codebuffer.PaymentService.repository.TransactionDetailsRepository;

import lombok.extern.log4j.Log4j2;



@Service
@Log4j2
public class PaymentServiceImpl implements  PaymentService {

	
	@Autowired
	private TransactionDetailsRepository  transactionDetailsRepository;
	
	@Override
	public Long doPayment(PaymentRequest paymentRequest) {
		// TODO Auto-generated method stub
		
		TransactionDetails transactiondetails = TransactionDetails.builder().
				                                paymentDate(Instant.now())
				                                .paymentMode(paymentRequest.getPaymentMode().name())
				                                .paymentStatus("SUCCESS")
				                                .orderId(paymentRequest.getOrderId())
				                                .referenceNumber(paymentRequest.getReferenceNumber())
				                                .amount(paymentRequest.getAmount())
				                                .build();
		
		
		
		transactionDetailsRepository.save(transactiondetails);
		
		log.info("Transaction is completed snd saved with id :{}",transactiondetails.getId());
		return transactiondetails.getId();

	
	
	
	}

	@Override
	public PaymentResponse getPaymentDetailsByOrderId(String orderId) {
		// TODO Auto-generated method stub
		log.info("Getting payment details for the OrderId: {}",orderId);
		
		
		//for this we will create custom method in repository
		TransactionDetails transactionDetails = transactionDetailsRepository.findByOrderId(Long.valueOf(orderId));
		
		PaymentResponse paymentResponse = PaymentResponse.builder()
				                           .paymentId(transactionDetails.getId())
				                           .paymentMode(PaymentMode.valueOf(transactionDetails.getPaymentMode()))
				                           .paymentDate(transactionDetails.getPaymentDate())
				                           .orderId(transactionDetails.getOrderId())
				                           .status(transactionDetails.getPaymentStatus())
				                           .amount(transactionDetails.getAmount())
				                           .build();
		
		return paymentResponse;
	}

	
	
	
	
	
	
}
