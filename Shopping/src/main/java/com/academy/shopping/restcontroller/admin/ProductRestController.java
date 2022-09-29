package com.academy.shopping.restcontroller.admin;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.academy.shopping.model.admin.ProductService;
import com.academy.shopping.model.domain.Product;
import com.academy.shopping.model.util.FileManager;
import com.academy.shopping.model.util.Message;

@Controller
public class ProductRestController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/admin/product/delete")                                   //@RequestBody를 통해 json과 DTO를 매핑한다
	public ResponseEntity<Message> delete(HttpServletRequest request, @RequestBody Product product){
		System.out.println("넘겨받은 상품 번호는 "+product.getProduct_id());
		System.out.println("넘겨받은 상품 이름은 "+product.getProduct_name());
		System.out.println(product.getProduct_img());
		
		String dest=request.getServletContext().getRealPath("/resources/data/"+product.getProduct_img());
		productService.remove(product, dest);
		Message message = new Message(1, "상품삭제 성공");
		ResponseEntity<Message> entity= new ResponseEntity(message, HttpStatus.OK);
		return entity;
	}
	
	@PostMapping("/admin/product/update")
	public ModelAndView update(Product product) {
		MultipartFile multi = product.getPhoto();
		
		String filename= multi.getOriginalFilename();
		System.out.println("업로드된 파일명은 "+filename);
		return null;
	}
}
