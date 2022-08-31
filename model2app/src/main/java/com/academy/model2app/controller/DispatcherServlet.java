package com.academy.model2app.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.academy.model2app.blood.controller.BloodController;
import com.academy.model2app.blood.model.BloodManager;
import com.academy.model2app.movie.controller.MovieController;
import com.academy.model2app.movie.controller.MovieController미사용;
import com.academy.model2app.movie.model.MovieManager;

//모든 요청마다 일대일 대응하는 컨트롤러를 전면에 내세우면 오히려 유지 보수성이 떨어진다.
public class DispatcherServlet extends HttpServlet{
	//모든 요청을 이 서블릿이 일단 받아야 한다.
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	//클라이언트의 요청이 어던 방식이던 상관없이, 요청을 받기위해 공통 메서드에서 컨트롤러의 요청처리를 진행
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("요청을 받았습니다.");
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		System.out.println("클라이언트 요청 uri는 "+uri);
		
		Controller controller=null;
		//2단계 요청을 분석한다
		if(uri.equals("/movie.do")) {
			//영화전담자인 MovieController에게 업무를 맡긴다
			controller = new MovieController();
		}else if(uri.equals("/blood.do")) {
			controller = new BloodController();
		}
		controller.execute(request, response);
		RequestDispatcher dis = request.getRequestDispatcher(controller.getViewPage());
		dis.forward(request, response);

	}
}
