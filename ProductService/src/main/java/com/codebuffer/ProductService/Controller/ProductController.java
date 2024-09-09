package com.codebuffer.ProductService.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codebuffer.ProductService.Model.ProductRequest;
import com.codebuffer.ProductService.Model.ProductResponse;
import com.codebuffer.ProductService.Service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	
	@Autowired
 private ProductService productService;

   @PostMapping
   public ResponseEntity<Long>  addProduct(@RequestBody ProductRequest productrequest){
	   long productId = productService.addProduct(productrequest);
	   
	 return new ResponseEntity<>(productId,HttpStatus.CREATED);
   }
  @GetMapping("/{id}") 
  public ResponseEntity<ProductResponse>  getProductById(@PathVariable("id") long ProductId){

	  ProductResponse productresponse = productService.getProductById(ProductId);
	  return new ResponseEntity<>(productresponse,HttpStatus.OK);
  }
  @PutMapping("/reduceQuantity/{id}")
  public ResponseEntity<Void>  reduceQuantity(@PathVariable("id") long productId, @RequestParam long quantity){
	
	  productService.reduceQuantity(productId, quantity);
	  return new ResponseEntity<>(HttpStatus.OK);
	  
	  
	 
	  
  }
	  
	  
	  
	  
   }
