package com.academy.shopping.model.domain;

import lombok.Data;

@Data
public class OrderDetail {
	private int orderdetail_id;
	private int ea;
	private Product product;
	private int ordersummary_id;
} 
