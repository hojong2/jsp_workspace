package com.academy.shopping.model.admin;

import java.util.List;

import com.academy.shopping.model.domain.Product;

public interface ProductService {
	public List selectAll();
	public List selectBySubCategoryId(int subcategory_id);
	public Product select(int product_id);
	public void regist(Product product, String savePath);
	public void update(Product product);
	public void delete(Product product);
}
