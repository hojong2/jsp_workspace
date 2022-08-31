package com.academy.model2app.movie.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.academy.model2app.movie.model.MovieManager;

/*
 * 영화에 대한 조언을 처리하는 컨트롤러
 */
public class MovieController미사용 extends HttpServlet{
	MovieManager manager = new MovieManager();
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1) 요청을 받는다
		 * 2) 요청을 분석한다
		 * 3) 알맞은 로직 객체에 일을 시킨다
		 * 4) 결과 페이지로 전달할 것이 있다면, 걸과 저장
		 * 5) 결과 페이지 보여주기
		*/
		request.setCharacterEncoding("utf-8");
		String movie=request.getParameter("movie");
		
		//3단계 로직 객체에 일 시키기
		String msg=manager.getAdvice(movie);
		
		//4단계 결과저장
		/*
		HttpSession session=request.getSession();
		session.setAttribute("data", msg);
		session.setAttribute("data1", movie);
		세션을 이용하면, 브라우저를 닫지 않는 한 세션이 유지되어 데이터를 보관할 수 있지만,
		만일, 세션 방법 이외의 방법이 있다면, 이용안할 이유가 없다.
		현실의 114 서비스, 이메일 포워딩 처럼 javaEE에서도 서버측의 특정 자원으로 현재 요청을 포워딩하는
		기술을 지원하며, 이때 사용되는 객체가 바로 RequestDisPatcher이다.
		*/
		request.setAttribute("data", msg);
		request.setAttribute("data1", movie);
		RequestDispatcher dis = request.getRequestDispatcher("/movie/advice.jsp");
		dis.forward(request, response);// 포워딩 실행
		
		//response.sendRedirect("/movie/advice.jsp");
		
	}
	
}
