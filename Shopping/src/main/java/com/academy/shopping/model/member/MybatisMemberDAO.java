package com.academy.shopping.model.member;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.academy.shopping.exception.MemberException;
import com.academy.shopping.model.domain.Member;
import com.academy.shopping.model.domain.Product;

@Repository
public class MybatisMemberDAO implements MemberDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List selectAll() {
		List memberList= sqlSessionTemplate.selectList("Member.selectAll");
		return memberList;
	}

	@Override
	public Member select(int member) {
		
		return null;
	}

	@Override
	public void insert(Member member) throws MemberException {
		int result=sqlSessionTemplate.insert("Member.insert", member);
		if(result==0) {
			throw new MemberException("회원가입 실패");
		}
	}

	@Override
	public void update(Member member) {
	}

	@Override
	public void delete(Member member) {
	}

	@Override
	public Member selectCustomerId(String customer_id) throws MemberException{
		Member member = sqlSessionTemplate.selectOne("Member.selectCustomerId", customer_id);
		if(member!=null) {
			throw new MemberException("이미 존재하는 ID가 있습니다.");
		}
		return member;
	}

}
