package com.academy.model2app.movie.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.academy.model2app.controller.Controller;
import com.academy.model2app.movie.model.MovieManager;

/*
 * 영화에 대한 전문적인 지식을 가진 전담 컨트롤러
 * 요청을 직접 받지 않으므로, 서블릿일 필요가 없다.
 */
public class MovieController implements Controller{
	MovieManager manager = new MovieManager();
	//컨트롤러의 5대 업무중 3단계를 수행(알맞은 비즈니스 로직 객체에 일 시키기)
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String movie=request.getParameter("movie");
		String msg=manager.getAdvice(movie);  //영화에 대한 판단결과 반환받기
		
		//4단계 뷰페이지로 가져갈 것이 있을 경우 결과 저장
		request.setAttribute("data", msg);
		request.setAttribute("data1", movie);
		
	}
	
	//형님 컨트롤러가 어떤 뷰페이지를 보여줘야 할지를 여기서 결장해보자
	public String getViewPage() {
		return "/movie/advice.jsp";
	}
}
