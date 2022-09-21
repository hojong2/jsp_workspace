package com.academy.shopping.model.admin;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.academy.shopping.model.domain.Product;

@Repository
public class MybatisProductDAO implements ProductDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List selectAll() {
		
		return sqlSessionTemplate.selectList("Product.selectAll");
	}

	@Override
	public List selectBySubId(int subcategory_id) {
		
		return sqlSessionTemplate.selectList("Product.selectBySubId", subcategory_id);
	}

	@Override
	public Product select(int product_id) {
		
		return sqlSessionTemplate.selectOne("Product.select", product_id);
	}

	@Override
	public void insert(Product product) {
		sqlSessionTemplate.insert("Product.insert", product);
	}

	@Override
	public void update(Product product) {
	}

	@Override
	public void delete(Product product) {
	}
	
}
