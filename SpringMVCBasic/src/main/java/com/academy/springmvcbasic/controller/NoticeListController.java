package com.academy.springmvcbasic.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.academy.springmvcbasic.model.repository.NoticeDAO;

//공지 게시판의 목록 요청을 처리하는 하위 컨트롤러 (3,4 단계 수행)
public class NoticeListController implements Controller{
	private NoticeDAO noticeDAO;
	
	//setter injection(주입)
	public void setNoticeDAO(NoticeDAO noticeDAO) {
		this.noticeDAO=noticeDAO;
	}
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		//3단계 일시킨다
		List noticeList = noticeDAO.selectAll();
		
		//4단계: view로 전달할 것이 있다면 결과 저장
		ModelAndView mav= new ModelAndView();
		mav.addObject("noticeList", noticeList);
		mav.setViewName("board/list");
		return mav;
	}
	
}
