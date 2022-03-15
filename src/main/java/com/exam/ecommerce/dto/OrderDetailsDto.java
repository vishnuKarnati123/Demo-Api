package com.exam.ecommerce.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;




public class OrderDetailsDto {
	
	private int orderid;	
	private LocalDate orderdate;
	private LocalDate deliverydate;
	private String status;
	private String shipmentstatus;
	private LocalDate shipmentDate;

	private List<ProductDto> products;
	
	
	
	
	public OrderDetailsDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getOrderid() {
		return orderid;
	}


	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}



	public LocalDate getOrderdate() {
		return orderdate;
	}


	public void setOrderdate(LocalDate orderdate) {
		this.orderdate = orderdate;
	}


	public LocalDate getDeliverydate() {
		return deliverydate;
	}


	public void setDeliverydate(LocalDate deliverydate) {
		this.deliverydate = deliverydate;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public List<ProductDto> getProducts() {
		return products;
	}


	public void setProducts(List<ProductDto> products) {
		this.products = products;
	}


	public String getShipmentstatus() {
		return shipmentstatus;
	}


	public void setShipmentstatus(String shipmentstatus) {
		this.shipmentstatus = shipmentstatus;
	}


	public LocalDate getShipmentDate() {
		return shipmentDate;
	}


	public void setShipmentDate(LocalDate shipmentDate) {
		this.shipmentDate = shipmentDate;
	}


	

}
