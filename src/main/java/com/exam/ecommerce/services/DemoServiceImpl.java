package com.exam.ecommerce.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.exam.ecommerce.dto.OrderDetailsDto;
import com.exam.ecommerce.dto.ProductDto;
import com.exam.ecommerce.entities.OrderDetails;
import com.exam.ecommerce.entities.Product;
import com.exam.ecommerce.repository.OrderRepository;
import com.exam.ecommerce.repository.ProductRepository;

@Service
public class DemoServiceImpl implements DemoServices {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	

	@Override
	public void addOrder(OrderDetails o) { // TODO Auto-generated method stub
		System.out.println(o);
		
		OrderDetails order = new OrderDetails();
		order.setStatus(o.getStatus());
		order.setOrderdate(o.getOrderdate());
		order.setDeliverydate(o.getDeliverydate());
		order=orderRepository.save(order);
		
		List<Product> products = o.getProducts();
		for(Product product:products)
		{
			Product p=new Product();
			p.setProductid(product.getProductid());
			p.setProductname(product.getProductname());
			p.setPrice(product.getPrice());
			p.setCategory(product.getCategory());
			p.setOrderDetails(order); 
			System.out.println(productRepository.save(p));		
			
			
		}
		
		
	}

	@Override
	public List<OrderDetails> getOrders() {
		// TODO Auto-generated method stub
		System.out.println(orderRepository.findByStatusNot("Deleted"));
		return orderRepository.findByStatusNot("Deleted");

	}
	
	@Override
	public ResponseEntity<?> updateOrder(OrderDetails order) {
		
		Optional<OrderDetails> ordDetailOptional = orderRepository.findById(order.getOrderid()); 
		OrderDetails orderDetails =ordDetailOptional.get();
		if (ordDetailOptional.isPresent())
		{
			orderDetails.setDeliverydate(order.getDeliverydate());
			orderDetails.setOrderdate(order.getOrderdate());
			orderDetails.setStatus(order.getStatus());
			orderDetails=orderRepository.save(orderDetails);
		 for (Product prod : order.getProducts())
		 {
			 Optional<Product> prd = Optional.ofNullable(productRepository.findByProductname(prod.getProductname())); 
			 if (prd.isPresent()) 
			 { 
				 System.out.println(prd.get());
			     prd.get().setProductname(prod.getProductname());
			    prd.get().setCategory(prod.getCategory());
			    prd.get().setPrice(prod.getPrice()); 	
			    prd.get().setOrderDetails(orderDetails);
			    productRepository.save(prd.get());
			    
			  }
			 else
			 {
				 prod.setOrderDetails(orderDetails);
				 productRepository.save(prod);  
			 }
		 }
		 return new ResponseEntity<>("Order Updated",HttpStatus.OK);
		 
		}
		
		else
		{
			return new ResponseEntity<>("Order is not present",HttpStatus.BAD_REQUEST);
		}
		
	}

	@Override
	public ResponseEntity<?> deleteOrderStatus(int orderid) {
		Optional<OrderDetails> optional = orderRepository.findById(orderid);
		if (optional.isPresent()) {
			OrderDetails orderDetails = optional.get();
			List<Product> product = orderDetails.getProducts();
			for (Product p : product) {
				if (p.getPrice() == 1000) {
					orderDetails.setStatus("Deleted");
					orderRepository.save(orderDetails);
					break;
				}
			}

			return new ResponseEntity<>("order status deleted",HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<>("orderid is not present",HttpStatus.BAD_REQUEST);
		}

	}

	@Override
	public List<ProductDto> getProducts() {
		// TODO Auto-generated method stub
		
		List<Product> product=productRepository.findAll();
		
		List<ProductDto> listProductDto=Arrays.asList(mapper.map(product,ProductDto[].class));
		
		return listProductDto;
	
	}

	@Override
	public ResponseEntity<?> deleteProduct(String productName) {
		
		
		Optional<Product> product = Optional.ofNullable(productRepository.findByProductname(productName));
		if(product.isPresent())
		{
			productRepository.deleteById(product.get().getProductid());
		    return new ResponseEntity<>("Product is deleted",HttpStatus.OK);
		    
		}
		else
		{
			 return new ResponseEntity<>("Product not found",HttpStatus.BAD_REQUEST);	
		}

	}

	@Override
	public List<Product> getProducts(int price) {
		// TODO Auto-generated method stub
		return productRepository.findByPriceGreaterThan(price);

	}

	@Override
	public List<Product> getProductsby(String category) {
		// TODO Auto-generated method stub
		return productRepository.findByCategory(category);
	}

	@Override
	public ResponseEntity<?> getProductbyId(int productId) {
		// TODO Auto-generated method stub
		Optional<Product> product = productRepository.findById(productId);
		return product.isPresent() ? ResponseEntity.ok().body(productRepository.findById(productId))
				: new ResponseEntity<>("Product id is not present", HttpStatus.BAD_REQUEST);
	}
	
	 ProductDto convertModelToDto(Product product){
		 ProductDto productDto = mapper.map(product, ProductDto.class);
			
		return productDto;
		
	}
	
	Product convertDtoToEntity(ProductDto productDto){
		Product product =mapper.map(productDto,Product.class);
		
		return product;
	}

	@Override
	public ProductDto addProduct(ProductDto productDto) {
		Product product= convertDtoToEntity(productDto);
		product=productRepository.save(product);
		return convertModelToDto(product);
	}

	@Override
	public ResponseEntity<?> updateProduct(ProductDto productDto) {
		
		Product product=mapper.map(productDto, Product.class);
		
		Optional<Product> p = Optional.ofNullable(productRepository.findByProductname(product.getProductname()));
		if (p.isPresent()) {
			System.out.println(product.getProductid());
			p.get().setCategory(product.getCategory());
			p.get().setPrice(product.getPrice());
			p.get().setProductname(product.getProductname());
			product=productRepository.save(p.get()); // updated
			ProductDto productDto1=mapper.map(product, ProductDto.class);
			return new ResponseEntity<>(productDto1,HttpStatus.OK);
		}
		else
		{
		return new ResponseEntity<>("Product is not present",HttpStatus.BAD_REQUEST);
		}

	}



	@Override
	public OrderDetailsDto getShipmentStatus(int orderid) {
		// TODO Auto-generated method stub

		Optional<OrderDetails> orderDetails=orderRepository.findById(orderid);
		if(orderDetails.isPresent())
		{
		return mapper.map(orderDetails.get(),OrderDetailsDto.class);
		
		}
		else
		{
			return null;
		}
		
	}

	

}
