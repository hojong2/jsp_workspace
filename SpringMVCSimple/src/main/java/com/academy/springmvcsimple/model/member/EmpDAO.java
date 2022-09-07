package com.academy.springmvcsimple.model.member;

import java.util.List;

import com.academy.springmvcsimple.domain.Emp;

public interface EmpDAO {
	public int insert(Emp emp);
	public List selectAll();
	public Emp select(int empno);
	public int update(Emp emp);
	public int delete(int empno);
}
