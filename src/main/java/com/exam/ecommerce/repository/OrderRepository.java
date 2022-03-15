package com.exam.ecommerce.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.exam.ecommerce.entities.OrderDetails;

@Repository
public interface OrderRepository extends JpaRepository<OrderDetails,Integer>{
	
	
     
	  public List<OrderDetails> findByStatusNot(String name);
	
}
