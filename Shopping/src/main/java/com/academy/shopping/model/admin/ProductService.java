package com.academy.shopping.model.admin;

import java.io.File;
import java.util.List;

import com.academy.shopping.model.domain.Product;

public interface ProductService {
	public List selectAll();
	public List selectBySubId(int subcategory_id);
	public List selectByTopId(int topcategory_id);
	public Product select(int product_id);
	public void regist(Product product, String savePath);
	public void registByExcel(File file, String read, String des);  //엑셀 파일을 매개변수로 넘김
	public void update(Product product);
	public void remove(Product product, String dest);  //파일삭제+db삭제
	
}
