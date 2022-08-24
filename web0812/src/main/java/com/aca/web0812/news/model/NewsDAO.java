package com.aca.web0812.news.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.aca.web0812.domain.News;
import com.aca.web0812.pool.ConnectionManager;
import com.aca.web0812.pool.DBManager;
import com.aca.web0812.pool.PoolManager;


/*
 * DAO는 오직 CRUD만 집중
 */
public class NewsDAO {
	ConnectionManager manager;  //웹이건 응용이건 둘 다 포함할 수 있는 객체
	
	public NewsDAO(){
		manager=PoolManager.getInstance();
	}
	//Create
	public int insert(News news) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0;
		try {
			con=manager.getConnection();  //다형성 polymorphism, 비록 ConnectionManager이지만 호출되는 메서드 동작은 각각 다르게 동작할 수 있다.
			String sql="insert into news(news_id, title, writer, content) values(seq_news.nextval,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, news.getTitle());
			pstmt.setString(2, news.getWriter());
			pstmt.setString(3, news.getContent());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			manager.freeConnection(con, pstmt);
		}
		return result;

	}
	//Read
	public List selectAll() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<News> list = new ArrayList<News>();
		try {
			con=manager.getConnection();
			String sql="select * from news order by news_id desc";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				News news = new News();
				news.setNews_id(rs.getInt("news_id"));
				news.setTitle(rs.getString("title"));
				news.setWriter(rs.getString("writer"));
				news.setContent(rs.getString("content"));
				news.setRegdate(rs.getString("regdate"));
				news.setHit(rs.getInt("hit"));
				
				list.add(news);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			manager.freeConnection(con, pstmt);
		}
		return list;
		
	}
	public News select(int news_id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		News news=null;
		
		con=manager.getConnection();
		String sql="select * from news where news_id=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, news_id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				news = new News();
				news.setNews_id(rs.getInt("news_id"));
				news.setTitle(rs.getString("title"));
				news.setWriter(rs.getString("writer"));
				news.setContent(rs.getString("content"));
				news.setRegdate(rs.getString("regdate"));
				news.setHit(rs.getInt("hit"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			manager.freeConnection(con, pstmt, rs);
		}
		return news;
	}
	//update
	public void update() {
		String sql="update news set title=?, writer?, content=? where news_id=?";
	}
	//delete
	public void delete() {
		String sql="delete news where news_id=?";
	}
}
