package com.academy.shopping.model.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.academy.shopping.exception.ProductException;
import com.academy.shopping.exception.UploadException;
import com.academy.shopping.model.domain.Product;
import com.academy.shopping.model.util.FileManager;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private FileManager fileManager;
	
	@Override
	public List selectAll() {
		
		return productDAO.selectAll();
	}

	@Override
	public List selectBySubCategoryId(int subcategory_id) {
		
		return null;
	}

	@Override
	public Product select(int product_id) {
		
		return productDAO.select(product_id);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void regist(Product product, String savePath) throws UploadException, ProductException{
		String filename=fileManager.save(product, savePath);
		product.setProduct_img(filename);
		productDAO.insert(product);
	}

	@Override
	public void update(Product product) {
	}

	@Override
	public void delete(Product product) {
	}
	
}
