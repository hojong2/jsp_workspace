package com.academy.shopping.model.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.academy.shopping.exception.EmailException;
import com.academy.shopping.exception.OrderDetailException;
import com.academy.shopping.exception.OrderSummaryException;
import com.academy.shopping.model.domain.Member;
import com.academy.shopping.model.domain.OrderDetail;
import com.academy.shopping.model.domain.OrderSummary;
import com.academy.shopping.model.util.MailFormReader;
import com.academy.shopping.model.util.MailSender;

@Service
public class OrderSummaryServiceImpl implements OrderSummaryService{

	@Autowired
	private OrderSummaryDAO orderSummaryDAO;
	
	@Autowired
	private OrderDetailDAO orderDetailDAO;
	
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private MailFormReader mailFormReader;
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void order(OrderSummary orderSummary) throws OrderSummaryException, OrderDetailException, EmailException{
		
		orderSummaryDAO.insert(orderSummary);
		//구매한 물건 수만큼
		for(int i=0;i<orderSummary.getOrderDetailList().size();i++) {
			OrderDetail orderDetail = orderSummary.getOrderDetailList().get(i);
			//OrderDetail이 필요로 하는 부모의 foreign key 값을 대입해주자
			orderDetail.setOrdersummary_id(orderSummary.getOrdersummary_id());
			orderDetailDAO.insert(orderDetail);
		}
		
		String content=mailFormReader.getStringFromMailForm("ddddd");
		//이메일 발송
		mailSender.send(content);

	}

	@Override
	public List selectAll() {
		List orderList = orderSummaryDAO.selectAll();
		return orderList;
	}

	@Override
	public OrderSummary selectByCustomerId(Member member) {
		
		return null;
	}

	@Override
	public OrderSummary select(int ordersummary_id) {
		OrderSummary orderSummary = orderSummaryDAO.select(ordersummary_id);
		return orderSummary;
	}

	@Override
	public void update(OrderSummary orderSummary) {
	}

	@Override
	public void delete(OrderSummary orderSummary) {
	}

}
