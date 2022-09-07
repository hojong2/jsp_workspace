package com.academy.springmvcsimple.model.member;

import java.util.List;

import com.academy.springmvcsimple.domain.Dept;

/* 
 * 이 DAO는 모든 Dept관련된 DAO의 최상위 객체이다
 * 즉 JDBC, Hibernate, Mybatis, JPA... 상관없는 최상위 객체
 * 
 */
public interface DeptDAO {
	public int insert(Dept dept);
	public List selectAll();
	public Dept select(int deptno);
	public int update(Dept dept);
	public int delete(int deptno);
}
