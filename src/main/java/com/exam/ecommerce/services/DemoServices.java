package com.exam.ecommerce.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.exam.ecommerce.dto.OrderDetailsDto;
import com.exam.ecommerce.dto.ProductDto;
import com.exam.ecommerce.entities.OrderDetails;
import com.exam.ecommerce.entities.Product;

public interface DemoServices {

	public void addOrder(OrderDetails order);

	public List<OrderDetails> getOrders();

	public List<ProductDto> getProducts();

	public ProductDto addProduct(ProductDto p);

	public ResponseEntity<?> deleteProduct(String productName);

	public List<Product> getProducts(int price);

	public List<Product> getProductsby(String category);

	public ResponseEntity<?> getProductbyId(int productId); 


	public ResponseEntity<?> updateProduct(ProductDto productDto);



	public ResponseEntity<?> updateOrder(OrderDetails order);

	public ResponseEntity<?>deleteOrderStatus(int orderid);

	public OrderDetailsDto getShipmentStatus(int orderid);


}
