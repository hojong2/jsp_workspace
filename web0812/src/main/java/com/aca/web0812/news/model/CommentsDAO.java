package com.aca.web0812.news.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.aca.web0812.domain.Comments;
import com.aca.web0812.pool.ConnectionManager;
import com.aca.web0812.pool.PoolManager;

/*
 * DAO와 DTO는 테이블마다 1:1대응하게 생성해야 함. 따라서 오라클에 table이 만일 100개라면, DAO 100개, DTO 100개
 * 제작시엔 시간이 좀 걸리지만, 추후 유지보수할때 시간이 단축, 유지보수시간=돈
 */
public class CommentsDAO {
	ConnectionManager manager=PoolManager.getInstance();
	
	public int insert(Comments comments) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0;
		
		con=manager.getConnection();
		String sql="insert into comments(comments_id, detail, author, news_id) values(seq_comments.nextval,?,?,?)";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, comments.getDetail());
			pstmt.setString(2, comments.getAuthor());
			pstmt.setInt(3, comments.getNews().getNews_id());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			manager.freeConnection(con, pstmt);
		}
		
		return result;
	}
	
	//모든 레코드 가져오기
	
}
