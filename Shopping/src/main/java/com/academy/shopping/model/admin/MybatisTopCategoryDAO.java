package com.academy.shopping.model.admin;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.academy.shopping.exception.TopCategoryException;
import com.academy.shopping.model.domain.TopCategory;

@Repository
public class MybatisTopCategoryDAO implements TopCategoryDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List selectAll() {
		
		return sqlSessionTemplate.selectList("TopCategory.selectAll");
	}

	@Override
	public TopCategory select(int topcategory_id) {
		
		return null;
	}

	@Override
	public void insert(TopCategory topCategory) throws TopCategoryException{
		int result=sqlSessionTemplate.insert("TopCategory.insert", topCategory);
		if(result==0) {
			throw new TopCategoryException("상위 카테고리 등록 실패");
		}
	}

	@Override
	public void update(TopCategory topCategory) {
	}

	@Override
	public void delete(TopCategory topCategory) {
	}
	
}
