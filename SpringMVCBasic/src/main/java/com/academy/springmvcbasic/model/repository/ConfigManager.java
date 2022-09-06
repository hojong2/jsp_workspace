package com.academy.springmvcbasic.model.repository;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/*
 * mybatis의 설정 정보는 요청이 있을때마다, xml을 읽어들일 필요가 없고
 * 프로그램이 가동되면 한번만 불러와 사용한다.
 */
public class ConfigManager {
	private static ConfigManager instance;
	SqlSessionFactory sqlSessionFactory;
	
	private ConfigManager() {
		try {
			String resource = "com/academy/springmvcbasic/mybatis/config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			
			//mybatis를 이용하면, 기존에 jdbc에서 sql문 수행시 사용했던 PreparedStatement를 사용하는게 아니라
			//대신 SqlSession이라는 객체를 이용한다.. 아래의 SqlSessionFactory는 SqlSession을 관리 및 반환해주는 객체
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//SqlSessionFactory로부터 SqlSession을 얻어갈 수 있도록 메서드를 정의해두자
	public SqlSession getSqlSession() {
		return sqlSessionFactory.openSession();
	}
	//닫기
	public void closeSqlSession(SqlSession sqlSession) {
		sqlSession.close();
	}
	
	public static ConfigManager getInstance() {
		if(instance==null) {
			instance = new ConfigManager();
		}
		return instance;
	}
}
