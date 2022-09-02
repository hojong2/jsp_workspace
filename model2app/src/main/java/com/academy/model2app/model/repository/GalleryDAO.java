package com.academy.model2app.model.repository;

import org.apache.ibatis.session.SqlSession;

import com.academy.model2app.mybatis.MybatisConfigManager;
import com.academy.model2app.notice.domain.Gallery;

public class GalleryDAO {
	MybatisConfigManager manager = MybatisConfigManager.getInstance();
	public int insert(Gallery gallery) {
		int result=0;
		SqlSession sqlSession = manager.getSqlSession();
		result=sqlSession.insert("Gallery.insert", gallery);
		sqlSession.commit();
		manager.closeSqlSession(sqlSession);
		return result;
	}
}
