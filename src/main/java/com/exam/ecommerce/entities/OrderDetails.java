package com.exam.ecommerce.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class OrderDetails implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int orderid;	
	private LocalDate orderdate;
	private LocalDate deliverydate;
	private String status;
	private String shipmentstatus;
	private LocalDate shipmentDate;
	@OneToMany(cascade=CascadeType.ALL,mappedBy="orderDetails",orphanRemoval = true, fetch = FetchType.LAZY)	
	@JsonManagedReference
	private List<Product> products;

	public OrderDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderDetails(int orderid, LocalDate orderdate, LocalDate deliverydate, String status, List<Product> products) {
		super();
		this.orderid = orderid;
		this.orderdate = orderdate;
		this.deliverydate = deliverydate;
		this.status = status;
		this.products = products;
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

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
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

	@Override
	public String toString() {
		return "OrderDetails [orderid=" + orderid + ", orderdate=" + orderdate + ", deliverydate=" + deliverydate
				+ ", status=" + status + ", products=" + products + "]";
	}

	

	
	
	

}
