package com.academy.shopping.model.member;

import java.util.List;

import com.academy.shopping.model.domain.Member;
import com.academy.shopping.model.domain.Product;

public interface MemberService {
	public List selectAll();
	public Member select(int member);
	public Member selectCustomerId(String customer_id);
	public Member selectByIdAndPass(Member member);
	public void insert(Member member);
	public void update(Member member);
	public void delete(Member member);
}
