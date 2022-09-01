package com.academy.model2app.model.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.academy.model2app.mybatis.MybatisConfigManager;
import com.academy.model2app.notice.domain.Notice;

public class NoticeDAO {
	MybatisConfigManager configManager = MybatisConfigManager.getInstance();
	//모두 가져오기
	public List selectAll() {
		List list =null;
		SqlSession sqlSession=configManager.getSqlSession();
		list=sqlSession.selectList("Notice.selectAll");
		configManager.closeSqlSession(sqlSession);
		return list;
	}
	/*
	public Notice select() {

	}*/
	
	//글 등록
	public int insert(Notice notice) {
		int result=0;
		SqlSession sqlSession = configManager.getSqlSession();
		result=sqlSession.insert("Notice.insert", notice);
		sqlSession.commit();
		configManager.closeSqlSession(sqlSession);
		return result;
	}
	/*
	public int update() {
		
	}*/
	
	/*
	public int delete() {
		
	}*/
}
