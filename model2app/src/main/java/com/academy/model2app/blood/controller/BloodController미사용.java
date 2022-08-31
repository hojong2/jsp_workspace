package com.academy.model2app.blood.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.academy.model2app.blood.model.BloodManager;

/*
 * 아래의 클래스는 MVC 패턴 중, 컨트롤러를 정의하는 것임
 * 컨트롤러란?
 * 일반적으로 컨트롤러란 MV를 분리시켜 개발하기 위한 중재자를 말한다
 * 전산분야에서 컨트롤러의 5대 업무
 * 1) 요청을 받을 수 있어야한다.
 * 2) 요청을 분석할 수 있어야한다.
 * 3) 알맞는 비즈니스 로직 객체에게 일 시킨다.
 * 4) 결과인 View에 전달할 것이 있을경우, 결과를 저장해놓아야 한다.
 * 5) 알맞는 뷰를 이용하여 결과 보여주기
 */
public class BloodController미사용 extends HttpServlet{
	BloodManager manager = new BloodManager();
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//클라이언트가 선택한 파라미터를 분석하여 적절한 결과 보여주기
		
		//1) 요청을 받을 수 있어야한다.
		request.setCharacterEncoding("utf-8");
		String blood=request.getParameter("blood");
		
		//3) 알맞는 비즈니스 로직 객체에게 일 시킨다.
		String msg=manager.getAdvice(blood);
		
		//4) 결과 페이지로 가져갈 것이 있을 경우, 결과를 저장해 놓아야 한다.
		HttpSession session = request.getSession();
		session.setAttribute("data", msg);
		session.setAttribute("data1", blood);
		
		//5) 알맞는 뷰 페이지 보여주기
		response.sendRedirect("/blood/advice.jsp");  //클라이언트 브라우저로 하여금 지정한 url로 재접속
	}
	
}
