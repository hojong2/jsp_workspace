package com.academy.springmvcbasic.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.academy.springmvcbasic.domain.Notice;
import com.academy.springmvcbasic.model.repository.NoticeDAO;


//상세보기 요청을 처리하는 하위 컨트롤러
public class NoticeContentController implements Controller{
	private NoticeDAO noticeDAO;
	
	public void setNoticeDAO(NoticeDAO noticeDAO) {
		this.noticeDAO = noticeDAO;
	}
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//일시키기
		int notice_id=Integer.parseInt(request.getParameter("notice_id"));
		Notice notice =noticeDAO.select(notice_id);
		
		//4단계 결과저장
		ModelAndView mav = new ModelAndView("board/content"); //setViewName()와 동일
		mav.addObject("notice", notice);
		
		return mav;
	}

}
