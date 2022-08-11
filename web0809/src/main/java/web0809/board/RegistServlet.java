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

/*클라이언트의 브라우저에서 전송된 파라미터를 넘겨받아 오라클에 insert 하자
 * jsp에서 해도된다 하지만, jsp 주 용도는 디자인이 포함된 경우에 많이 쓰이므로, 이번 실습에서는 서블릿으로 한다
 */
public class RegistServlet extends HttpServlet {
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="java";
	String password="1234";
	//게시판 폼 양식중 글 내용이 textarea이르모, 상당한 양의 데이터가 전송되어 지므로
	//post방식으로 전송되어진다. 따라서 우리는 doXXX형 메서드 중 doPost로 처리한다
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//이 메서드는 Servlet의 service() 메서드에 의해 호출되어지는 메서드이다
		request.setCharacterEncoding("utf-8");  //파라미더에 대한 인코딩
		String title=request.getParameter("title");
		String writer=request.getParameter("writer");
		String content=request.getParameter("content");
		
		//클라이언트가 받게될 응답정보 문자열은, response 객체가 가진 스트림에 적재시켜야 한다
		response.setContentType("text/html;charset=UTF-8");  //응답 정보에 대한 인코딩
		PrintWriter out=response.getWriter();
		out.print("title is: "+title+"<br>");
		out.print("writer is: "+writer+"<br>");
		out.print("내용 is: "+content+"<br>");
		
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
		String sql="insert into board(board_id, title, writer, content) values(board_seq.nextval,?,?,?)";
		
		PreparedStatement pstmt=null;
		
		try {
			 pstmt = con.prepareStatement(sql);
			 pstmt.setString(1, title);
			 pstmt.setString(2, writer);
			 pstmt.setString(3, content);
			 
			 int result=pstmt.executeUpdate();
			 if(result<1) {
				 out.print("등록실패<br>");
			 }else {
				 out.print("등록성공<br>");
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
