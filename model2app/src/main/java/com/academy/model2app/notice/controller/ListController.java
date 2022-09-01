package com.academy.model2app.notice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.academy.model2app.controller.Controller;
import com.academy.model2app.model.repository.NoticeDAO;


public class ListController implements Controller{
	
	//3단계 일시킨다(DAO)
	NoticeDAO noticeDAO = new NoticeDAO();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		List noticeList = noticeDAO.selectAll();
		
		//4단계 결과 페이지로 가져갈 것이 있으면 결과를 저장해 놓는다(DispatchServlet에게 전달하기 위해).
		request.setAttribute("noticeList", noticeList);
	}

	@Override
	public String getViewName() {
		// TODO Auto-generated method stub
		return "/notice/result/list";
	}
	
	//jsp인 뷰로 가져갈 것이 있으므로, request는 죽으면 안된다 따라서 응답을 하지말고, 서버의 jsp자원으로 포워딩을 시도하여 request를 살려두자
	@Override
	public boolean isForward() {
		return true;
	}
}
