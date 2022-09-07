package com.academy.springmvcsimple.model.member;

import java.util.List;

import com.academy.springmvcsimple.domain.Emp;

//서비스는 Model(데이터 또는 로직)이다
//사원과 관련된 업무를 처리하는 서비스 객체
//만일 서비스의 존재가 없다면, 서비스가 부담해야 할 업무를 컨트롤러가 해버리게된다.
public interface MemberService {
	
	public int regist(Emp emp);  //메서드는 하나지만, 내부적으로는 사원 등록이란 부서+사원 둘다 성공해야 전체를 성공으로 간주하는 트랜잭션 상황
	
	public List selectAll();
	public Emp select(int empno);
	public int update(Emp emp);
	public int delete(int empno);
}
