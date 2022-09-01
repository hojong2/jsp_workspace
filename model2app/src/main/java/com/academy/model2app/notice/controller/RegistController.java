package com.academy.model2app.notice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.academy.model2app.controller.Controller;
import com.academy.model2app.model.repository.NoticeDAO;
import com.academy.model2app.notice.domain.Notice;

//3단계 알맞는 모델(M) 객체에 일시키기
//4단계 저장할 것이 있다면 저장(DML의 경우 클라이언트에게 보여줄 것이 없다. 따라서 4단계 생략)
public class RegistController implements Controller{
	NoticeDAO noticeDAO= new NoticeDAO();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String title=request.getParameter("title");
		String writer=request.getParameter("writer");
		String content=request.getParameter("content");
		
		Notice notice = new Notice();
		
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		
		int result=noticeDAO.insert(notice);
		
	}
	@Override
	public String getViewName() {
		// TODO Auto-generated method stub
		return "/notice/result/write";
	}
	
	//jsp로 가져갈 것이 없으므로, 데이터를 유지할 필요도 없다, 따라서 request는 죽어도 되므로, 응답을 해버리고
	//이때 응답을 하는순간 서버의 request, response, thread는 소멸한다.
	@Override
	public boolean isForward() {
		return false;
	}
}
