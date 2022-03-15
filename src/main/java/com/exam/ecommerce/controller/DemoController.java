package com.exam.ecommerce.controller;


import org.springframework.http.HttpHeaders;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.exam.ecommerce.dto.OrderDetailsDto;
import com.exam.ecommerce.dto.ProductDto;
import com.exam.ecommerce.entities.OrderDetails;
import com.exam.ecommerce.entities.Product;
import com.exam.ecommerce.services.DemoServices;

@RestController
@RequestMapping("/demo")
public class DemoController {

	@Autowired
	DemoServices demoServices;

	
	@PostMapping("/order")
	public ResponseEntity<String> addOrder(@RequestBody OrderDetails order) {
		demoServices.addOrder(order);
		return ResponseEntity.status(HttpStatus.CREATED).body("Order created");
	}

	@GetMapping("/orders")
	public ResponseEntity<List<OrderDetails>> getOrders() {
		return ResponseEntity.ok(demoServices.getOrders());

	}

	@PutMapping("/order")
	public ResponseEntity<?> updateOrder(@RequestBody OrderDetails order) {
		return demoServices.updateOrder(order);
	}
	

	@DeleteMapping("/order/{orderid}")
	public ResponseEntity<?> deleteOrderStatus(@PathVariable int orderid) {

		 return demoServices.deleteOrderStatus(orderid);
		
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<ProductDto>> getProducts() {
		return ResponseEntity.ok(demoServices.getProducts());

	}

	@PostMapping("/product")
	public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto) {
		
		return new ResponseEntity<>(demoServices.addProduct(productDto),HttpStatus.CREATED);
	}

	

	@PutMapping("/product")
	public ResponseEntity<?>  updateProduct(@RequestBody ProductDto productDto) {
		return demoServices.updateProduct(productDto);
	}

	@DeleteMapping("/product")
	public ResponseEntity<?> deleteProduct(@RequestParam String productName) {
		return demoServices.deleteProduct(productName);
	}

	@GetMapping("/products/{price}")
	public ResponseEntity<List<Product>> getProducts(@PathVariable int price) {
		return ResponseEntity.ok(demoServices.getProducts(price));

	}

	@GetMapping("/product/category/{category}")
	public ResponseEntity<List<Product>> getProductsby(
			@PathVariable(value = "category", name = "category") String category) {
		return ResponseEntity.ok(demoServices.getProductsby(category));

	}

	@GetMapping("/product/id/{productId}")
	public ResponseEntity<?> getProductbyId(@PathVariable(value = "productId") int productId) {
		return demoServices.getProductbyId(productId);

	}

	
	
	
	@Autowired
	RestTemplate restTemplate;
	
	
	
	@GetMapping("/orderstatus/{orderid}")
	public String trackStatus(@PathVariable int orderid)
	{
		HttpHeaders headers=new HttpHeaders();
		headers.add("Accept",MediaType.APPLICATION_JSON_VALUE.toString());
		headers.add("Content-Type",MediaType.APPLICATION_JSON_VALUE.toString());
		
	
      HttpEntity<String> entity=new HttpEntity<String>(headers);
	
		
		  return restTemplate.getForObject("http://localhost:8080/api/shipmentstatus/{orderid}", String.class,orderid);
	
		
	}
	
	
	
	@PostMapping("/savestatus")
	public String saveStatus(@RequestBody OrderDetailsDto orderDetailsDto) {
		

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		orderDetailsDto.setShipmentstatus("Done");
		orderDetailsDto.setShipmentDate(orderDetailsDto.getDeliverydate().plusDays(2));
		HttpEntity<OrderDetailsDto> entity = new HttpEntity<>(orderDetailsDto, headers);

		//ResponseEntity<String> res = restTemplate.exchange("http://localhost:8080/api/status", HttpMethod.POST, entity,String.class);
		return restTemplate.postForObject("http://localhost:8080/api/status",orderDetailsDto,String.class);
		//return res.getBody();
	}
	@PutMapping("/updatestatus/{orderid}")
	public String saveStatus(@PathVariable int orderid)
	{
		try
		{
		OrderDetailsDto orderDetailsDto =demoServices.getShipmentStatus(orderid);
        orderDetailsDto.setShipmentstatus("placed");
        HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<OrderDetailsDto> entity = new HttpEntity<>(orderDetailsDto, headers);

		ResponseEntity<String> res = restTemplate.exchange("http://localhost:8080/api/status", HttpMethod.PUT, entity,String.class);
		return res.getBody();
		}
		catch(NullPointerException e)
		{
			return "order id not there";
		}
		
		
	}
	
	@DeleteMapping("/orderstatus/{orderid}")
	public String deleteStatus(@PathVariable int orderid)
	{
		
		 ResponseEntity<String> res = restTemplate.exchange("http://localhost:8080/api/status/{orderid}", HttpMethod.DELETE,HttpEntity.EMPTY,String.class,orderid);
		return res.getBody();
	}
	
	@GetMapping("/getproduct")
	public String getproductfromshipment()
	{
		 HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity=new HttpEntity<>(headers);
		
		String url="http://localhost:8080/api/getproduct?productName={productName}";
	//	UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url).queryParam("productName", "Life");//builder.buildAndExpand().toUri()
		ResponseEntity<String> res = restTemplate.exchange(url, HttpMethod.GET, entity,String.class,"Life");
		return res.getBody();
	}
	
}

