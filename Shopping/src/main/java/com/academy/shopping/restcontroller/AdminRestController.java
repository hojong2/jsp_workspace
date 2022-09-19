package com.academy.shopping.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.academy.shopping.exception.AdminException;
import com.academy.shopping.model.admin.AdminService;
import com.academy.shopping.model.domain.Admin;
import com.academy.shopping.model.util.HashManager;

//Controller vs RestController(자동으로 @ResponseBody가 붙여짐)
@RestController
public class AdminRestController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private HashManager hashManager;
	
	@PostMapping("/admin")
	@ResponseBody  //RestController에서는 이 속성이 자동 부여된다. 그리고 이 속성의 의미는 결과 뷰 페이지를 매핑하는 것이 아니라 순수 데이터를 전송하는 효과를 낸다
	public ResponseEntity regist(Admin admin) {
		adminService.insert(admin);
		//ResponseEntity 응답정보를 전담하는 객체(필수는 아니지만 http 응답정보에 최적화되어있어서 편하다)
		return new ResponseEntity(HttpStatus.OK);
	}
	
	//로그인 요청을 처리한다
	@PostMapping("/admin/login")
	public ResponseEntity<String> login(Admin admin) {
		//db에 패스워드를 비교하기 전에, 먼저 클라이언트가 전송한 password를 hash값으로 변경한 후 비교해야 함
		String hashedValue = hashManager.getConvertedPassword(admin.getPass());  //평문 -> hash
		admin.setPass(hashedValue);  //dto에 평문 패스워드를 암호화된 패스워드값으로 교체
		Admin obj=adminService.selectByIdAndPass(admin);  //해당 아이디와 패스워드가 일치하는 회원이 있을때, DTO가 null이 아니다
		System.out.println("로그인 결과: " +obj);
		ResponseEntity<String> entity= new ResponseEntity<String>("1", HttpStatus.OK);
		return entity;
	}
	
	@ExceptionHandler(AdminException.class)
	public ResponseEntity<String> handleException(AdminException e) {
		ResponseEntity<String> entity= new ResponseEntity<String>("0", HttpStatus.OK);
		return entity;
	}
}
