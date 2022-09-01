package com.academy.model2app.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
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
	//FileInputStream fis;
	Properties props;
	
	/*
	 * JSP 내장 객체는 몇개 되지 않음
	 * request --> HttpServletRequest
	 * response --> HttpServletResponse
	 * session --> HttpSession
	 * application --> ServletContext : 어플리케이션의 전역적 정보를 가진 객체
	 * 												ex) getRealPath() 이 어플리케이션내의 자원의 풀 경로
	 */
	
	//생성자 또는 init()
	@Override
	public void init(ServletConfig config) throws ServletException {
		props=(Properties)config.getServletContext().getAttribute("props");
		/*
		try {
			ServletContext context=config.getServletContext();
			//자바의 웹어플리케이션은 플랫폼에 독립적이어야 하므로, 자원의 주소는 class내에 하드코딩해서는 안된다.
			String param = config.getInitParameter("contextConfigLocation");
			String path=context.getRealPath(param);
			fis = new FileInputStream(path);
			props = new Properties();
			props.load(fis);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
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
		
		//주의! 검색 결과는 컨트롤러 클래스 자체가 아닌, 단지 그 클래스의 경로일 뿐이다(String)
		String controllerClassName = props.getProperty(uri);
		//결국 String 경로를 이용하여 실제 클래스의 인스턴스를 생성하는 방법
		try {
			Class controllerClass=Class.forName(controllerClassName);  //아직 인스턴스가 올라온건 아니다. 즉 new 하지 않았다.
			controller=(Controller)controllerClass.newInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		if(uri.equals("/movie.do")) {
			//영화전담자인 MovieController에게 업무를 맡긴다
			controller = new MovieController();
		}else if(uri.equals("/blood.do")) {
			controller = new BloodController();
		}
		*/
		controller.execute(request, response);
		//5단계 알맞은 뷰페이지 보여주기
		//포워딩 하기 전에, 매핑 파일에서 검색을 해야 한다(실제 jsp 파일 경로를 얻기 위해)
		String viewName = controller.getViewName();  //=/notice/result/write in mapping.properties
		String viewPage = props.getProperty(viewName); //=/notice/list.jsp in mapping.properties
		
		//무조건 포워딩하면 문제가 발생할 수 있다.
		
		if(controller.isForward()) {
			RequestDispatcher dis = request.getRequestDispatcher(viewPage);
			dis.forward(request, response);
		}else{
			//리다이렉트(요청을 일단 끊고, 브라우저로 하여금 지정한 url로 다시 들어오게 한다)
			response.sendRedirect(viewPage);
		}
	}
	
	//서블릿이 일을 다하고 소멸될때, (서버가 죽을때 등등) 호출되는 생명주기 메서드
	/*
	@Override
	public void destroy() {
		if(fis!=null) {
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}*/
}
