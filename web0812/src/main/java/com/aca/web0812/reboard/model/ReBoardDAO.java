package com.aca.web0812.reboard.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.aca.web0812.domain.ReBoard;
import com.aca.web0812.pool.ConnectionManager;
import com.aca.web0812.pool.PoolManager;

public class ReBoardDAO{
	ConnectionManager manager = PoolManager.getInstance();
	public int insert(ReBoard reboard) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0;
		con=manager.getConnection();
		String sql="insert into reboard(reboard_id, title, writer, content, team) values(seq_reboard.nextval, ?, ?, ?, seq_reboard.nextval)";
		try {
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1, reboard.getTitle());
		pstmt.setString(2, reboard.getWriter());
		pstmt.setString(3, reboard.getContent());
		result=pstmt.executeUpdate();
		}catch (Exception e) {
			e.getStackTrace();
		}finally {
			manager.freeConnection(con, pstmt);
		}
		return result;
	}
	
	public List selectAll() {
		Connection con= null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		List list = new ArrayList();
		con=manager.getConnection();
		String sql="select * from reboard order by team desc, step asc";
		
		try {
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				ReBoard reBoard = new ReBoard();
				reBoard.setReboard_id(rs.getInt("reboard_id"));
				reBoard.setTitle(rs.getString("title"));
				reBoard.setWriter(rs.getString("writer"));
				reBoard.setContent(rs.getString("content"));
				reBoard.setRegdate(rs.getString("regdate"));
				reBoard.setHit(rs.getInt("hit"));
				reBoard.setTeam(rs.getInt("team"));
				reBoard.setStep(rs.getInt("step"));
				reBoard.setDepth(rs.getInt("depth"));
				list.add(reBoard);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			manager.freeConnection(con, pstmt, rs);
		}		
		return list;
	}
	
	public ReBoard select(int reboard_id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ReBoard reBoard=null;
		con=manager.getConnection();
		String sql="select * from reboard where reboard_id=?";
		try {

			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, reboard_id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				reBoard = new ReBoard();
				reBoard.setReboard_id(rs.getInt("reboard_id"));
				reBoard.setTitle(rs.getString("title"));
				reBoard.setWriter(rs.getString("writer"));
				reBoard.setContent(rs.getString("content"));
				reBoard.setRegdate(rs.getString("regdate"));
				reBoard.setHit(rs.getInt("hit"));
				reBoard.setTeam(rs.getInt("team"));
				reBoard.setStep(rs.getInt("step"));
				reBoard.setDepth(rs.getInt("depth"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			manager.freeConnection(con, pstmt, rs);
		}

		return reBoard;
	}
	
	//?????? ??????
	public int reply(ReBoard reBoard) {
		int result=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		
		con=manager.getConnection();
		
		try {
			//????????? ????????? ?????? ??????(?????? ??? ????????? step??? ??? ????????? step??? 1??? ???????????????, ?????? team ????????????)
			//String sql="update reboard set step=step+1 where team=?????????team, step>?????????step";
			String sql="update reboard set step=step+1 where team=? and step>?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, reBoard.getTeam());
			pstmt.setInt(2, reBoard.getStep());
			result= pstmt.executeUpdate();
			//?????? insert
			/*
			sql="insert into reboard(reboard_id, title, writer, content, team, step, depth)";
			sql+="values(seq_reboard.nextval,?,?,?, ?????????team, ?????????step+1, ?????????depth+1)";
			*/
			sql="insert into reboard(reboard_id, title, writer, content, team, step, depth)";
			sql+=" values(seq_reboard.nextval,?,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, reBoard.getTitle());
			pstmt.setString(2, reBoard.getWriter());
			pstmt.setString(3, reBoard.getContent());
			pstmt.setInt(4, reBoard.getTeam()); //?????????team
			pstmt.setInt(5, reBoard.getStep()+1);  //?????????step+1 : ????????? ?????? ?????? ?????????
			pstmt.setInt(6, reBoard.getDepth()+1);  //?????????dept+1 : ?????? ????????? ???????????????
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			manager.freeConnection(con,pstmt);
		}

		return result;
	}
}
