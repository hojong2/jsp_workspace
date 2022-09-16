package com.academy.shopping.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.academy.shopping.exception.AdminException;
import com.academy.shopping.model.admin.AdminService;
import com.academy.shopping.model.domain.Admin;

//Controller vs RestController(자동으로 @ResponseBody가 붙여짐)
@RestController
public class AdminRestController {
	
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/admin")
	@ResponseBody  //RestController에서는 이 속성이 자동 부여된다. 그리고 이 속성의 의미는 결과 뷰 페이지를 매핑하는 것이 아니라 순수 데이터를 전송하는 효과를 낸다
	public ResponseEntity regist(Admin admin) {
		adminService.insert(admin);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@ExceptionHandler(AdminException.class)
	public String handleException(AdminException e) {
		
		return e.getMessage();
	}
}
