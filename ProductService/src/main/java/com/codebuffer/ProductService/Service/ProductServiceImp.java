package com.codebuffer.ProductService.Service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codebuffer.ProductService.Entity.Product;
import com.codebuffer.ProductService.Exception.ProductServiceCustomException;
import com.codebuffer.ProductService.Model.ProductRequest;
import com.codebuffer.ProductService.Model.ProductResponse;
import com.codebuffer.ProductService.Repository.ProductRepository;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;



@Service
@Log4j2
public class ProductServiceImp   implements ProductService {

	@Autowired
	private ProductRepository productrepository;
	
	@Override
	public long addProduct(ProductRequest productRequest) {
		// TODO Auto-generated method stub
		
		
		
		log.info("Adding product....");
		

		Product product = 
				           Product.builder()
				           .productName(productRequest.getName())
                           .quantity(productRequest.getQuantity())
                           .price(productRequest.getPrice())
                           .build();
		
		
		productrepository.save(product);

		log.info(" Product created....");
		return product.getProductId();
		
		
	}

	public ProductResponse getProductById(long productId) {
		// TODO Auto-generated method stub

		log.info(" Get the product  for productid");
		Product product =  productrepository.findById(productId).orElseThrow(()-> new ProductServiceCustomException("product with id is not found","PRODUCT_NOT_FOUND"));
		
		ProductResponse productResponse = new ProductResponse(); 
		BeanUtils.copyProperties(product, productResponse);
		return productResponse;
	}

	@Override
	public void reduceQuantity(long productId, long quantity) {
		// TODO Auto-generated method stub
		log.info( "Reduce  Quantity {}  for the id: {}",quantity,productId);
		
		
		Product product  = productrepository.findById(productId).orElseThrow(()-> new  ProductServiceCustomException (
				
				"Product with given Id not found",
				"product_not_found"
				
				));
		
		if(product.getQuantity() < quantity) {
			
			throw new ProductServiceCustomException(
					
					"Product doesnot have suffecient quantity",
					"Insuffecient_Quantity"
					);
			
			
			
		}
				product.setQuantity(product.getQuantity() - quantity);
				productrepository.save(product);
				log.info("Product quantity updated successfully");
		
		
	}

}
