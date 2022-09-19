package com.academy.shopping.model.admin;

import java.util.List;

import com.academy.shopping.model.domain.TopCategory;

public interface TopCategoryDAO {
	public List selectAll();
	public TopCategory select(int topcategory_id);
	public void insert(TopCategory topCategory);
	public void update(TopCategory topCategory);
	public void delete(TopCategory topCategory);
	
}
