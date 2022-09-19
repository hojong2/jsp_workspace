package com.academy.shopping.model.admin;

import java.util.List;

import org.springframework.stereotype.Service;

import com.academy.shopping.model.domain.SubCategory;

@Service
public class SubCategoryServiceImpl implements SubCategoryService{

	@Override
	public List selectAll() {
		
		return null;
	}

	@Override
	public List selectByTopCategoryId(int topcategory_id) {
		
		return null;
	}

	@Override
	public SubCategory select(int subcategory_id) {
		
		return null;
	}

	@Override
	public void insert(SubCategory subCategory) {
	}

	@Override
	public void update(SubCategory subCategory) {
	}

	@Override
	public void delete(SubCategory subCategory) {
	}

}
