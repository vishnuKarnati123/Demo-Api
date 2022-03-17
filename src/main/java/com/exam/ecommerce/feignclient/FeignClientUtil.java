package com.exam.ecommerce.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.exam.ecommerce.dto.OrderDetailsDto;
//value="feignDemo", url="http://localhost:8080/api"
@FeignClient(value="feignDemo", url="http://localhost:8080/api")
public interface FeignClientUtil {
	
	@GetMapping("/shipmentstatus/{orderid}")
	public String  getShipmentstatus(@PathVariable int orderid);
	
		
	@PostMapping("/status")
	public String addShipmentStatus(@RequestBody OrderDetailsDto order);

	@PutMapping("/status")
	public String updateShipmentStatus(@RequestBody OrderDetailsDto order);

	
	
	@DeleteMapping("/status/{orderid}")
	public String deleteStatus(@PathVariable int orderid);

}
