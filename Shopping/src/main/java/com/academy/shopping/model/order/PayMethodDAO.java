package com.academy.shopping.model.order;

import java.util.List;

import com.academy.shopping.model.domain.Member;
import com.academy.shopping.model.domain.OrderSummary;
import com.academy.shopping.model.domain.PayMethod;

public interface PayMethodDAO {
	public void insert(PayMethod payMethod);
	public List selectAll();
	public PayMethod select(int paymethod_id);
	public void update(PayMethod payMethod);
	public void delete(PayMethod payMethod);
}
