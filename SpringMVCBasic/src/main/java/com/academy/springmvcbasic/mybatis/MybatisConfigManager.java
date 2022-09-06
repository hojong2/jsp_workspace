package com.academy.springmvcbasic.mybatis;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/*
 * config.xml을 파싱하여, 쿼리문 수행객체인 SqlSession을 모아놓고 관리해주는
 * SqlSessionFactory를 생성하야 한다
 * 공식홈페이지에 의하면, SqlSessionFactory는 메모리에 싱클톤으로 관리하자
 */
public class MybatisConfigManager {
	
	private static MybatisConfigManager instance;
	SqlSessionFactory sqlSessionFactory;
	InputStream inputStream;
	
	private MybatisConfigManager() {
		String resource = "com/academy/springmvcbasic/mybatis/config.xml";
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void closeStream(InputStream is) {
		if(is!=null) {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public SqlSession getSqlSession() {
		return sqlSessionFactory.openSession();
	}
	
	public void closeSqlSession(SqlSession sqlSession) {
		sqlSession.close();
	}
	
	public static MybatisConfigManager getInstance() {
		if(instance==null) {
			instance=new MybatisConfigManager();
		}
		return instance;
	}
}
