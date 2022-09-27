package com.academy.shopping.model.domain;

import java.util.List;

import lombok.Data;

@Data
public class OrderSummary {
	private int ordersummary_id;
	private int totalbuy;
	private int totalpay;
	private String buydate;
	private Member member;
	private PayMethod paymethod;
	private List<OrderDetail> orderDetailList;
}
