package com.exam.ecommerce;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.exam.ecommerce.feignclient.FeignClientUtil;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(clients= {FeignClientUtil.class})
public class DemoOrderApplication {

	@Bean
	public ModelMapper modelMapper()
	{
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
