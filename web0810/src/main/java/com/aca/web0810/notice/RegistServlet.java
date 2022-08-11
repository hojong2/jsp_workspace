package com.aca.web0810.notice;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class RegistServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//클라이언트가 전송해 온 파라미터 받기
		request.setCharacterEncoding("utf-8");
		String title 		=request.getParameter("title");
		String writer 	=request.getParameter("writer");
		String content =request.getParameter("content");
		
		//접속이라는 행위는 내부적으로 상당히 많은 절차와 인증을 거쳐야 하는 어려운 작업
		//Connection Pooling 기법이란? 접속자가 없더라도, Tomcat 서버의 메모리에 미리 다수의 Connection 객체를
		//확보해 놓고 메모리 pool에 모아놓는 커넥션관리 기법
		//사용모습 : 클라이언트 접속 --> pool에 모여있는 Connection 중에 하나를 추출하여 요청을 처리할 Connection
		//으로 사용함, 이때 쿼리문 수행이 모두 종료되었을때도 Connection을 close()하지 않고 다시 pool로 돌려보낸다.
		//다수의 클라이언트를 처리할때 부하가 걸리지 않음
		//코드구현방법: 1)개발자가 직접 알고리즘을 적용하여 개발 2) 나보다 실력이 뛰어난 다른 개발자꺼 씀
		//3) 공식적인 라이브러리를 이용하는 방법(apache.org의 커넥션풀을 제공해준다. DBCP)
		//좋은 소식) Tomcat 서버는 이미 DBCP를 이용한 커넥션풀을 지원한다
		//선행학습으로써, JNDI(Java Naming Directory Interface)
		
		//이 시점부터는 절대로 자바클래스안에, 추후 변경 가능성이 큰 자원의 정보는 기재하지 않는다(하드코딩금지)
		
		//검색을 담당하는 검색객체 생성
		Connection con=null;
		PreparedStatement pstmt = null;
		
		try {
			InitialContext ctx = new InitialContext();
			
			//DataSource가 바로 Connection Pooling을 구현한 객체이다.
			DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/myoracle"); //매개변수로 
			
			//커넥션풀로부터 Connection 하나를 빌려써보자
			con = ds.getConnection();
			String sql="insert into board(board_id, title, writer, content) values(board_seq.nextval, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, writer);
			pstmt.setString(3, content);
			int result = pstmt.executeUpdate(); //실행
			out.print("<script>");
			if(result==0) {
				out.print("alert('등록실패');");
				out.print("history.back();");
			}else {
				out.print("alert('등록성공');");
				out.print("location.href='/notice/list.jsp';");
			}
			out.print("</script>");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null){
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
