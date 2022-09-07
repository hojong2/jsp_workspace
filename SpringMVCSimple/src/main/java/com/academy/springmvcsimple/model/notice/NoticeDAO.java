package com.academy.springmvcsimple.model.notice;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.academy.springmvcsimple.domain.Notice;
import com.academy.springmvcsimple.mybatis.MybatisConfigManager;

@Repository
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
	//레코드 하나 가져오기
	public Notice select(int notice_id) {
		Notice notice = null;
		SqlSession sqlSession= configManager.getSqlSession();
		notice=sqlSession.selectOne("Notice.selectOne", notice_id);
		configManager.closeSqlSession(sqlSession);
		return notice;
	}
	
	//글 등록
	public int insert(Notice notice) {
		int result=0;
		SqlSession sqlSession = configManager.getSqlSession();
		result=sqlSession.insert("Notice.insert", notice);
		sqlSession.commit();
		configManager.closeSqlSession(sqlSession);
		return result;
	}
	//한 건 수정
	public int update(Notice notice) {
		int result=0;
		SqlSession sqlSession = configManager.getSqlSession();
		result=sqlSession.update("Notice.update", notice);
		sqlSession.commit();
		configManager.closeSqlSession(sqlSession);
		return result;
	}
	
	
	public int delete(int notice_id) {
		int result=0;
		SqlSession sqlSession = configManager.getSqlSession();
		result=sqlSession.delete("Notice.delete", notice_id);
		sqlSession.commit();
		configManager.closeSqlSession(sqlSession);
		return result;
	}
}
