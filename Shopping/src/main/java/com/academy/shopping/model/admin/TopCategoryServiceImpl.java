package com.academy.shopping.model.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.shopping.exception.TopCategoryException;
import com.academy.shopping.model.domain.TopCategory;

@Service
public class TopCategoryServiceImpl implements TopCategoryService{

	@Autowired
	private TopCategoryDAO topCategoryDAO;
	
	@Override
	public List selectAll() {
		
		return topCategoryDAO.selectAll();
	}

	@Override
	public TopCategory select(int topcategory_id) {
		
		return null;
	}

	@Override
	public void insert(TopCategory topCategory) throws TopCategoryException{
		topCategoryDAO.insert(topCategory);
	}

	@Override
	public void update(TopCategory topCategory) {
	}

	@Override
	public void delete(TopCategory topCategory) {
	}

}
