package com.dailycodespring.ProductService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dailycodespring.ProductService.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	
}
