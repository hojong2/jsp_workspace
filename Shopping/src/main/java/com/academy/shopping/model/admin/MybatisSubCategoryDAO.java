package com.academy.shopping.model.admin;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.academy.shopping.exception.SubCategoryException;
import com.academy.shopping.model.domain.SubCategory;

@Repository
public class MybatisSubCategoryDAO implements SubCategoryDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List selectAll() {
		
		return sqlSessionTemplate.selectList("SubCategory.selectAll");
	}

	@Override
	public SubCategory select(int subcategory_id) {
		
		return null;
	}

	@Override
	public void insert(SubCategory subCategory) throws SubCategoryException {
		int result=sqlSessionTemplate.insert("SubCategory.insert", subCategory);
		if(result==0) {
			throw new SubCategoryException("하위 카테고리 등록실패");
		}
	}

	@Override
	public void update(SubCategory subCategory) {
	}

	@Override
	public void delete(SubCategory subCategory) {
	}

	@Override
	public List selectByTopCategoryId(int topcategory_id) {
		
		return sqlSessionTemplate.selectList("SubCategory.selectByTopCategoryId", topcategory_id);
	}

}
