package com.exam.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exam.ecommerce.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>{
	
	public List<Product> findByPriceGreaterThan(int price);

	public List<Product> findByCategory(String category);
	
	public List<Product> findByPriceLessThanEqual(int price);
	
	public Product findByProductname(String productname);


}
