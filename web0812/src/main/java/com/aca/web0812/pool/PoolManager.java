package com.aca.web0812.pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

//웹에서의 커넥션 풀로부터 커넥션을 얻기위한 전담 객체
public class PoolManager extends ConnectionManager{
	private static PoolManager instance;
	InitialContext context; // JNDI 검색객체
	DataSource ds;
	
	private PoolManager() {
		try {
			context=new InitialContext();
			ds=(DataSource)context.lookup("java:comp/env/jdbc/myoracle"); //검색시작
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static PoolManager getInstance() {
		if(instance==null) {
			instance=new PoolManager();
		}
		return instance;
		
	}
	
	@Override
	public Connection getConnection() {
		Connection con=null;
		try {
			con = ds.getConnection(); //새로운 접속이 아니라, 기존 풀에 모여있는 Connection 빌리기
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	@Override
	public void freeConnection(Connection con) {
		if(con!=null) {
			try {
				con.close(); //다시 풀로 돌려보냄
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void freeConnection(Connection con, PreparedStatement pstmt) {
		if(con!=null) {
			try {
				con.close(); //다시 풀로 돌려보냄
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(pstmt!=null) {
			try {
				pstmt.close(); //다시 풀로 돌려보냄
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void freeConnection(Connection con, PreparedStatement pstmt, ResultSet rs) {
		// TODO Auto-generated method stub
		if(con!=null) {
			try {
				con.close(); //다시 풀로 돌려보냄
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(pstmt!=null) {
			try {
				pstmt.close(); //다시 풀로 돌려보냄
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(rs!=null) {
			try {
				rs.close(); //다시 풀로 돌려보냄
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
