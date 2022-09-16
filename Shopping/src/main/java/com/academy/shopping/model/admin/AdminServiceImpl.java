package com.academy.shopping.model.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.academy.shopping.exception.AdminException;
import com.academy.shopping.model.domain.Admin;
import com.academy.shopping.model.util.HashManager;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	@Qualifier("mybatisAdminDAO")
	private AdminDAO adminDAO;
	
	@Autowired
	private HashManager hashManager;
	@Override
	public List selectAll() {
		
		return null;
	}

	@Override
	public Admin select(int admin_id) {
		
		return null;
	}

	@Override
	public Admin selectByIdAndPass(Admin admin) throws AdminException{
		
		return adminDAO.selectByIdAndPass(admin);
	}

	@Override
	public void insert(Admin admin) throws AdminException{
		//회원등록 전에 평문이였던 비밀번호를 암호화시킨다
		String hash=hashManager.getConvertedPassword(admin.getPass());
		admin.setPass(hash);
		adminDAO.insert(admin);
	}

	@Override
	public void update(Admin admin) {
	}

	@Override
	public void delete(Admin admin) {
	}
	
}
