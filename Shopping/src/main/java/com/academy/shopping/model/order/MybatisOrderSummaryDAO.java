package com.academy.shopping.model.order;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.academy.shopping.exception.OrderSummaryException;
import com.academy.shopping.model.domain.Member;
import com.academy.shopping.model.domain.OrderSummary;

@Repository
public class MybatisOrderSummaryDAO implements OrderSummaryDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public void insert(OrderSummary orderSummary) throws OrderSummaryException{
		int result=sqlSessionTemplate.insert("OrderSummary.insert", orderSummary);
		if(result==0) {
			throw new OrderSummaryException("주문정보가 입력되지 않았습니다.");
		}
	}

	@Override
	public List selectAll() {
		List list=sqlSessionTemplate.selectList("OrderSummary.selectAll");
		return list;
	}

	@Override
	public OrderSummary selectByCustomerId(Member member) {
		return null;
	}

	@Override
	public OrderSummary select(int ordersummary_id) {
		OrderSummary orderSummary = sqlSessionTemplate.selectOne("OrderSummary.select", ordersummary_id);
		return orderSummary;
	}

	@Override
	public void update(OrderSummary orderSummary) {
	}

	@Override
	public void delete(OrderSummary orderSummary) {
	}

}
