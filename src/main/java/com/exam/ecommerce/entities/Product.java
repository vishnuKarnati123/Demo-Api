package com.exam.ecommerce.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Product implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRO_SEQ")
	@SequenceGenerator(name = "PRO_SEQ", allocationSize = 1, sequenceName = "PRO_SEQ")
	private int productid;
	private String productname;
	private String category;
	private int price;

	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name = "orderid")
	@JsonBackReference
	private OrderDetails orderDetails;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(int productid, String productname, String category, int price, OrderDetails orderDetails) {
		super();
		this.productid = productid;
		this.productname = productname;
		this.category = category;
		this.price = price;
		this.orderDetails = orderDetails;
	}

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public OrderDetails getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(OrderDetails orderDetails) {
		this.orderDetails = orderDetails;
	}

	@Override
	public String toString() {
		return "Product [productid=" + productid + ", productname=" + productname + ", category=" + category
				+ ", price=" + price +"]";
	}

}
