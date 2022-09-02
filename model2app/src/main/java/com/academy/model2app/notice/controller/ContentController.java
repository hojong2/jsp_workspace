package com.academy.model2app.notice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.academy.model2app.controller.Controller;
import com.academy.model2app.model.repository.NoticeDAO;
import com.academy.model2app.notice.domain.Notice;

//3 단계 - 일시키기, 4단계 - 결과 저장
public class ContentController implements Controller{
	NoticeDAO noticeDAO = new NoticeDAO();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int notice_id=Integer.parseInt(request.getParameter("notice_id"));
		Notice notice =noticeDAO.select(notice_id);
		request.setAttribute("notice", notice);  //request에 notice DTO 저장
	}

	@Override
	public String getViewName() {
		// TODO Auto-generated method stub
		return "/notice/result/content";
	}

	@Override
	public boolean isForward() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
