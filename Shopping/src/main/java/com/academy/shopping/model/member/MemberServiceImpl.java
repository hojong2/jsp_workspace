package com.academy.shopping.model.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.academy.shopping.exception.MemberException;
import com.academy.shopping.model.domain.Member;
import com.academy.shopping.model.domain.Product;
import com.academy.shopping.model.util.HashManager;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private HashManager hashManager;
	
	@Override
	public List selectAll() {
		List memberList=memberDAO.selectAll();
		return memberList;
	}

	@Override
	public Member select(int member) {
		
		return null;
	}

	@Override
	public void insert(Member member) throws MemberException{
		String result=hashManager.getConvertedPassword(member.getCustomer_pass());
		member.setCustomer_pass(result);
		memberDAO.insert(member);
	}

	@Override
	public void update(Member member) {
	}

	@Override
	public void delete(Member member) {
	}

	@Override
	public Member selectCustomerId(String customer_id) throws MemberException {
		Member member = memberDAO.selectCustomerId(customer_id);
		return member;
	}

	@Override
	public Member selectByIdAndPass(Member member) throws MemberException{
		String resultpass=hashManager.getConvertedPassword(member.getCustomer_pass());
		member.setCustomer_pass(resultpass);
		Member result=memberDAO.selectByIdAndPass(member);
		return result;
	}

}
