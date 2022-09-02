package com.academy.model2app.notice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.academy.model2app.controller.Controller;
import com.academy.model2app.model.repository.NoticeDAO;
import com.academy.model2app.notice.domain.Notice;

public class EditController implements Controller{
	NoticeDAO noticeDAO = new NoticeDAO();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String title=request.getParameter("title");
		String writer=request.getParameter("writer");
		String content=request.getParameter("content");
		int notice_id=Integer.parseInt(request.getParameter("notice_id"));
		Notice notice = new Notice();
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		notice.setNotice_id(notice_id);
		noticeDAO.update(notice);
		request.setAttribute("notice", notice);
	}

	@Override
	public String getViewName() {
		// TODO Auto-generated method stub
		return "/notice/result/edit";
	}

	@Override
	public boolean isForward() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
