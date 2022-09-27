package com.academy.shopping.model.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.shopping.model.domain.PayMethod;

@Service
public class PayMethodServiceImpl implements PayMethodService{

	@Autowired
	private PayMethodDAO payMethodDAO;
	
	@Override
	public void insert(PayMethod payMethod) {
	}

	@Override
	public List selectAll() {
		List payMethodList = payMethodDAO.selectAll();
		return payMethodList;
	}

	@Override
	public PayMethod select(int paymethod_id) {
		
		return null;
	}

	@Override
	public void update(PayMethod payMethod) {
	}

	@Override
	public void delete(PayMethod payMethod) {
	}

}
