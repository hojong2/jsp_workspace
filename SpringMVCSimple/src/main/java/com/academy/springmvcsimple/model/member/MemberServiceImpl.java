package com.academy.springmvcsimple.model.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.academy.springmvcsimple.domain.Emp;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private EmpDAO empDAO;
	
	@Autowired
	private DeptDAO deptDAO;
	
	
	//한명의 사원 등록이란 사실 부서와 사원 테이블 모두 성공해야 전체를 성공으로 간주하는 트랜잭션 상황이다.
	@Override
	public int regist(Emp emp) {
		int result=0;
		
		result=deptDAO.insert(emp.getDept());
		
		//mybatis에 의해 Emp DTO가 참조하고 있었던 Dept DTO pk를 포함한 모든 값들이 채워져 있게 된다.

		if(result != 0) {
			result=empDAO.insert(emp);
		}
		return result;
	}

	@Override
	public List selectAll() {
		
		return empDAO.selectAll();
	}

	@Override
	public Emp select(int empno) {
		Emp emp= empDAO.select(empno);
		return emp;
	}

	@Override
	public int update(Emp emp) {
		
		return 0;
	}

	@Override
	public int delete(int empno) {
		
		return 0;
	}

}
