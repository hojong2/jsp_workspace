package com.academy.shopping.model.admin;

import java.util.List;

import com.academy.shopping.model.domain.Product;
import com.academy.shopping.model.domain.SubCategory;

public interface ProductDAO {
	public List selectAll();
	public List selectBySubId(int subcategory_id);
	public Product select(int product_id);
	public void insert(Product product);
	public void update(Product product);
	public void delete(Product product);
}
