package com.aca.web0810.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.aca.web0810.domain.Board;

/*이 클래스는 웹기반뿐만 아니라 스탠다드 기반에서도 공용으로 쓸 수 있는 수준으로 정의해놓자
 * 왜?? 재사용을 위해
 * 아래의 클래스는 오직 C(create=insert)Read(=select)UD만을 처리하기 위한 객체이므로
 * 객체지향의 설계분야에서 이러한 역할을 수행하는 객체를 가리켜 DAO(=Data Access Object)
 */

public class BoardDAO {
	String url="jdbc:oracle:thin:@localhost:1521:XE";
	String user="java";
	String password="1234";
	
	//레코드 넣기
	public int insert(Board board) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		int result=0;  //멤버변수가 아닌 지역변수는 컴파일러가 초기화해주지 않는다. 따라서 반드시 초기화하자
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection(url, user, password);
			String sql="insert into board(board_id, title, writer, content) values(board_seq.nextval,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			
			result=pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(pstmt!=null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return result;
	}
}
