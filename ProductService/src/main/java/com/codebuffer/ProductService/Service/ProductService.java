package com.codebuffer.ProductService.Service;

import com.codebuffer.ProductService.Model.ProductRequest;
import com.codebuffer.ProductService.Model.ProductResponse;

public interface ProductService {

	long addProduct(ProductRequest productrequest);

	ProductResponse getProductById(long productId);

	void reduceQuantity(long productId, long quantity);

}
