package com.academy.springmvcbasic.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.academy.springmvcbasic.domain.Notice;
import com.academy.springmvcbasic.model.repository.NoticeDAO;

public class NoticeEditController implements Controller{
	private NoticeDAO noticeDAO;
	
	public void setNoticeDAO(NoticeDAO noticeDAO) {
		this.noticeDAO = noticeDAO;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Notice notice = new Notice();
		String notice_id=request.getParameter("notice_id");
		String title=request.getParameter("title");
		String writer=request.getParameter("writer");
		String content=request.getParameter("content");
		notice.setNotice_id(Integer.parseInt(notice_id));
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		
		noticeDAO.update(notice);
		
		ModelAndView mav= new ModelAndView("redirect:/board/content?notice_id="+notice_id);
		return mav;
	}
	
}
