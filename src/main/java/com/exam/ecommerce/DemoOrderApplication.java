package com.exam.ecommerce;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.exam.ecommerce.feignclient.FeignClientUtil;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;



@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(clients = { FeignClientUtil.class })
@OpenAPIDefinition(info =@Info(title="Order API", description = "Order MicroService") )
public class DemoOrderApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	/*
	 * @Bean
	 * 
	 * @LoadBalanced public RestTemplate getRestTemplate() { return new
	 * RestTemplate(); }
	 */
	public static void main(String[] args) {
		SpringApplication.run(DemoOrderApplication.class, args);

	}

	
	 

}
