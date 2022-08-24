package com.aca.web0812.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//독립실행형에서 커넥션을 관리하기 위한 객체
public class DBManager extends ConnectionManager{
	private static DBManager instance = new DBManager();
	
	String url="jdbc:oracle:this:@localhost:1521:XE";
	String user="java";
	String password="1234";
	Connection con =null;
	
	private DBManager() {
	}
	
	public static DBManager getInstance() {
		return instance;
	}
	
	@Override
	public Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection(url,user,password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
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
