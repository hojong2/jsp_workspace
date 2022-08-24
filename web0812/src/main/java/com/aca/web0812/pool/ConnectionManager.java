package com.aca.web0812.pool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


//데이터베이스 접속을 얻거나, 해제해주는 전담객체
abstract public class ConnectionManager {
	
	//커넥션 얻어오기
	public abstract Connection getConnection();
	
	//DB관련 자원해제
	public abstract void freeConnection(Connection con); //Only Connection
	public abstract void freeConnection(Connection con, PreparedStatement pstmt); //DML
	public abstract void freeConnection(Connection con, PreparedStatement pstmt, ResultSet rs); //SELECT 
}
