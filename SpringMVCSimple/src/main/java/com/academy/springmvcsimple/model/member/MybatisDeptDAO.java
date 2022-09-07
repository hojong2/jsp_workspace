package com.academy.springmvcsimple.model.member;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.academy.springmvcsimple.domain.Dept;
import com.academy.springmvcsimple.mybatis.MybatisConfigManager;

@Repository
public class MybatisDeptDAO implements DeptDAO{

	@Autowired
	MybatisConfigManager manager;
	
	@Override
	public int insert(Dept dept) {
		int result=0;
		SqlSession sqlSession = manager.getSqlSession();
		result=sqlSession.insert("Dept.insert", dept);
		sqlSession.commit();
		manager.closeSqlSession(sqlSession);
		
		return result;
	}

	@Override
	public List selectAll() {
		
		return null;
	}

	@Override
	public Dept select(int deptno) {
		
		return null;
	}

	@Override
	public int update(Dept dept) {
		
		return 0;
	}

	@Override
	public int delete(int deptno) {
		
		return 0;
	}

}
