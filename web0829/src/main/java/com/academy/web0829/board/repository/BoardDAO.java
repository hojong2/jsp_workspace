package com.academy.web0829.board.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.academy.web0829.board.domain.Board;
import com.academy.web0829.mybatis.ConfigManager;

public class BoardDAO {
	ConfigManager configManager=ConfigManager.getInstance();
	
	//한건 넣기
	public int insert(Board board) {
		SqlSession sqlSession=configManager.getSqlSession();  //mybatis의 쿼리수행 객체
		int result=0;
		//여기서 sql문을 작성하는 것이 아닌, XML에 작성된 쿼리문을 호출한다
		result=sqlSession.insert("babo.insert", board);
		sqlSession.commit();
		configManager.closeSqlSession(sqlSession);
		return result;
	}
	
	public List selectAll() {
		SqlSession sqlSession = configManager.getSqlSession();
		List list=null;
		list=sqlSession.selectList("Board.selectAll");
		configManager.closeSqlSession(sqlSession);
		return list;
	}
}
