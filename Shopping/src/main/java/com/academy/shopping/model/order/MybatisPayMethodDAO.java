package com.academy.shopping.model.order;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.academy.shopping.model.domain.PayMethod;

@Repository
public class MybatisPayMethodDAO implements PayMethodDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public void insert(PayMethod payMethod) {
		
	}

	@Override
	public List selectAll() {
		List payMethodList= sqlSessionTemplate.selectList("PayMethod.selectAll");
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
