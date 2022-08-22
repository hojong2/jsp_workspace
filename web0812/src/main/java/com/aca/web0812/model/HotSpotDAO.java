package com.aca.web0812.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aca.web0812.domain.HotSpot;

//오직 HotSpot 테이블에 대한 CRUD를 담당하는 DAO객체
public class HotSpotDAO {
	String url="jdbc:mysql://localhost:3306/javastudy?useUnicode=true&characterEncoding=utf8";
	String user="root";
	String pass="1234";
	public int insert(HotSpot hotspot) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result=0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pass);
			String sql = "insert into hotspot(lati, longi, icon, content) values(?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setFloat(1, hotspot.getLati());
			pstmt.setFloat(2, hotspot.getLongi());
			pstmt.setString(3, hotspot.getIcon());
			pstmt.setString(4, hotspot.getContent());
			
			result = pstmt.executeUpdate();  //insert 쿼리수행 후 이 Connection에 대한 세션이 닫히기 전에 insert에 의해 증가된 pk 얻어오기
			
			sql = "select last_insert_id() as hotspot";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				result=rs.getInt("hotspot");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(con!=null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(pstmt!=null)
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(rs!=null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
		}
		return result;

		
	}
	
	//목록 가져오기
	public List selectAll() {
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List list = new ArrayList();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url, user, pass);
			String sql="select * from hotspot order by hotspot asc";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				//레코드 한건을 대체하기 위한 DTO 하나의 인스턴스 생성
				HotSpot dto = new HotSpot();
				dto.setHotspot_id(rs.getInt("hotspot")); //pk
				dto.setLati(rs.getFloat("lati"));
				dto.setLongi(rs.getFloat("longi"));
				dto.setIcon(rs.getString("icon"));
				dto.setContent(rs.getString("content"));
				
				list.add(dto);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return list;

	}
	
	//하나만 가져오기 (가장 최근에 insert된)
	//last_insert_id() : 현재 나의 세션내에서 나에 의해 증가된 pk값을 가져오기
	public HotSpot select(int hotspot) {
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		HotSpot dto = null;
		
		try {
			String sql="select * from hotspot where hotspot=?";
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url, user, pass);
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, hotspot);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
			dto = new HotSpot();
			dto.setHotspot_id(rs.getInt("hotspot")); //pk
			dto.setLati(rs.getFloat("lati"));
			dto.setLongi(rs.getFloat("longi"));
			dto.setIcon(rs.getString("icon"));
			dto.setContent(rs.getString("content"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dto;

	}
}
