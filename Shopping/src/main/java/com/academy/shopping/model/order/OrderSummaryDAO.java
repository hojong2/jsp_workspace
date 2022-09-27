package com.academy.shopping.model.order;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.academy.shopping.model.domain.Member;
import com.academy.shopping.model.domain.OrderSummary;

public interface OrderSummaryDAO {
	public void insert(OrderSummary orderSummary);
	public List selectAll();
	public OrderSummary selectByCustomerId(Member member);
	public OrderSummary select(int ordersummary_id);
	public void update(OrderSummary orderSummary);
	public void delete(OrderSummary orderSummary);
}
