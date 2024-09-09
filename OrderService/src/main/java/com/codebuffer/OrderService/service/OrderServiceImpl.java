package com.codebuffer.OrderService.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.codebuffer.OrderService.entity.Order;
import com.codebuffer.OrderService.exception.CustomException;
import com.codebuffer.OrderService.external.client.PaymentService;
import com.codebuffer.OrderService.external.client.ProductService;
import com.codebuffer.OrderService.external.request.PaymentRequest;
import com.codebuffer.OrderService.external.response.PaymentResponse;
import com.codebuffer.OrderService.model.OrderRequest;
import com.codebuffer.OrderService.model.OrderResponse;
import com.codebuffer.OrderService.repository.OrderRepository;
import com.codebuffer.ProductService.Model.ProductResponse;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
	private RestTemplate  restTemplate; 
	
	@Override
	public long placeOrder(OrderRequest orderRequest) {
	
	  //Order ENtity -> save the data with status order created
      //ProductService ->Block Products(Reduced to Quantity)
      //PaymentService -> Payments ->Success ->Complete,Else
		//Cancelled
		
	
	
	log.info("Adding product....");
	
	productService.reduceQuantity(orderRequest.getProductId(),orderRequest.getQuantity());
	

	log.info("Creating order with status created");
Order order = Order.builder().amount(orderRequest.getTotalAmount())
                             .orderStatus("CREATED")
                             .productId(orderRequest.getProductId())
                             .orderDate(Instant.now())
                             .quantity(orderRequest.getQuantity())
                   .build(); 
       
order =  orderRepository.save(order);

// .paymentMode(paymentRequest.getPaymentmode().name())

log.info("Calling payment Service to complete the payment");
  PaymentRequest paymentRequest = PaymentRequest.builder()
	                        	  .orderId(order.getId())
		                          .paymentMode(orderRequest.getPaymentMode()) 
		                          .amount(orderRequest.getTotalAmount())
		                           .build();

                   String orderstatus = null;
                   try {
                	   paymentService.doPayment(paymentRequest);
                	   log.info("Payment done successfully. Changing order status to placed");
                	   orderstatus = "PLACED";
                   }
                    catch(Exception e){
                    	 log.info("Error Occured. Changing order status to FaiLed");
                    	 orderstatus = "FAILED";
                   
                    	
                    }
  
                      order.setOrderStatus(orderstatus);
                      orderRepository.save(order);
  
  
  
	log.info(" Order places Successfully with the orderid: {}", order.getId());

	

		return order.getId();
	}

	@Override
	public OrderResponse getOrderDetails(long orderId) {
		log.info("Get order details for Order Id: {}", orderId);
		
		Order order = orderRepository.findById(orderId).
				orElseThrow(()->new CustomException("Order not found for the order id" + orderId,"NOT_FOUND",404));
		
		log.info("Invoking Product service to fetch the product for id{}:",order.getProductId());
		//using rest template we are calling the 
		ProductResponse productResponse = restTemplate.getForObject
				("http://PRODUCT-SERVICE/product/" + order.getProductId(), ProductResponse.class);
		
		
		log.info("Getting Payment information from the payment service");
		PaymentResponse  	paymentResponse = restTemplate.getForObject
				        ("http://PAYMENT-SERVICE/payment/order/"+ order.getId(), PaymentResponse.class);
				
		
		
		//static class jo bnayi thi uski details save krnge jo result hume milnge use
		OrderResponse.ProductDetails productDetails = OrderResponse.ProductDetails.builder()
				                                      .productName(productResponse.getProductName())
				                                      .productId(productResponse.getProductId())
				                                       .build();
		
		OrderResponse.PaymentDetails paymentDetails = OrderResponse.PaymentDetails.builder()
				                                       .paymentId(paymentResponse.getPaymentId())
				                                       .paymentStatus(paymentResponse.getStatus())
				                                       .paymentDate(paymentResponse.getPaymentDate())
				                                       .paymentMode(paymentResponse.getPaymentMode())
				                                       .build();
				                                       
				                              
				
				
		
		OrderResponse	orderResponse  = OrderResponse.builder()
				                       .orderId(order.getId())
				                       .orderStatus(order.getOrderStatus())
				                       .amount(order.getAmount())
				                       .orderDate(order.getOrderDate())
				                       .productDetails(productDetails)  //yha pe hum productdetails set krdenge
				                       .paymentDetails(paymentDetails)
				                       .build();
				                       
		return orderResponse ;           
	}     

}
