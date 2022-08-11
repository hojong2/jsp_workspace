package web0809.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditServlet extends HttpServlet{
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="java";
	String password="1234";
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");  //파라미더에 대한 인코딩
		String board_id=request.getParameter("board_id"); //사용자가 선택한 게시물 id
		String title=request.getParameter("title");
		String writer=request.getParameter("writer");
		String content=request.getParameter("content");
		String sql="update board set title=?, writer=?, content=? where board_id="+board_id;
		
		PrintWriter out = response.getWriter();
		
		//클라이언트가 받게될 응답정보 문자열은, response 객체가 가진 스트림에 적재시켜야 한다
		response.setContentType("text/html;charset=UTF-8");  //응답 정보에 대한 인코딩
		
		//1.드라이버 로드 2.접속 3.쿼리수행 4.db예제
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			out.print("드라이버 로드 성공<br>");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Connection con=null;
		
		try {
			con=DriverManager.getConnection(url, user, password);
			if(con==null) {
				out.print("접속실패<br>");
			}else {
				out.print("접속성공<br>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		PreparedStatement pstmt=null;
		
		try {
			 pstmt = con.prepareStatement(sql);
			 pstmt.setString(1, title);
			 pstmt.setString(2, writer);
			 pstmt.setString(3, content);
			 
			 int result=pstmt.executeUpdate();
			 if(result<1) {
				 out.print("수정실패<br>");
			 }else {
				 //톰켓이 응답정보를 클라이언트에게 보낼때, 해당 클라이언트의 브라우저로 하여금
				 //재접속할 주소를 기입 즉 여기서 페이지 이동이 일어나지 않는다.
				 response.sendRedirect("/board/list.jsp");
			 }
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
