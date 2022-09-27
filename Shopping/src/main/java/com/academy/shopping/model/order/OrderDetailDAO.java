package com.academy.shopping.model.order;

import com.academy.shopping.model.domain.OrderDetail;
import com.academy.shopping.model.domain.PayMethod;

public interface OrderDetailDAO {
	public void insert(OrderDetail orderDetail);
	public OrderDetail selectByOrderSummaryId(int ordersummary_id);
	public void update(OrderDetail orderDetail);
	public void delete(int orderdetail_id);
}
