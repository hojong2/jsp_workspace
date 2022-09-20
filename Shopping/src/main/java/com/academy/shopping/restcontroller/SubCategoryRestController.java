package com.academy.shopping.restcontroller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.academy.shopping.model.admin.SubCategoryService;
import com.academy.shopping.model.domain.SubCategory;

@Controller
public class SubCategoryRestController {
	
	@Autowired
	private SubCategoryService subCategoryService;
	
	//관리자 - 해당 상위 카테고리에 소속된 하위 카테고리 목록 가져오기 요청 처리
	@GetMapping("/admin/subcategory/{topcategory_id}")
	@ResponseBody
	public List getSubList(@PathVariable("topcategory_id") int topcategory_id) {
		System.out.println("넘어온 topcategory_id "+topcategory_id);
		return subCategoryService.selectByTopCategoryId(topcategory_id);
	}
	
	@PostMapping("/admin/subcategory")
	public ResponseEntity registSubList(SubCategory subCategory) {
		subCategoryService.insert(subCategory);
		
		//응답정보의 컨텐츠의 헤더에 인코딩 처리
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "text/html;charset=UTF-8");
		
		ResponseEntity responseEntity= new ResponseEntity("등록성공", headers,HttpStatus.OK);
		return responseEntity;
	}
}
