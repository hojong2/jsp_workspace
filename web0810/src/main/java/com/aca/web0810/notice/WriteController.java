package com.aca.web0810.notice;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aca.web0810.model.BoardManager;

public class WriteController extends HttpServlet{
	BoardManager boardManager;
	
	//이 서블릿 클래스가 최최의 접속자에 의해 인스튼서화 될 때, 딱 한번 호출되는 생명주기 메서드
	@Override
	public void init() throws ServletException {
		boardManager = new BoardManager();
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//클라이언트가 전송해 온 파라미터 받기
		request.setCharacterEncoding("utf-8");
		String title 		=request.getParameter("title");
		String writer 	=request.getParameter("writer");
		String content =request.getParameter("content");
		
		//넘어온 파라미터를 이용하여 DB에 insert에 하되, 직접이 아닌 중립적 객체를 이용할 것임
		//insert() 메서드에서 사용되고 있는 각각의 매개변수는 사실 하나의 게시물을 구성하는 데이터이다
		//따라서 객체지향 관점으로 본다면 하나의 게시물은 하나의 레코드에 해당하므로, 게시물을 담을수 있는
		//클래스를 정의하여, 인스턴스 생성후 변수들을 보관해놓는 기법을 이용한다. 이때 정의되는 클래스는
		//로직을 작성하기 위함이 아니라, 오직 데이터만을 담고 가지고 다니는 용도라 하며
		//Data Transfer Object (DTO), Value Object (VO)
		//asdf
		int result=boardManager.insert(title, writer, content);
		out.print("<script>");
		if(result==0) {
			out.print("alert('등록실패');");
			out.print("history.back();");
		}else {
			out.print("alert('등록성공');");
			out.print("location.href='/notice/list.jsp';");
		}
		out.print("</script>");
	}
}