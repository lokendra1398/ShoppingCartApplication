package com.codebuffer.ProductService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codebuffer.ProductService.Entity.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{

	
	
	
}
