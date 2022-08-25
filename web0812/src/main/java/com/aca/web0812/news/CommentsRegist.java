package com.aca.web0812.news;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aca.web0812.domain.Comments;
import com.aca.web0812.domain.News;
import com.aca.web0812.news.model.CommentsDAO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//댓글 등록 요청을 처리하는 서블릿
public class CommentsRegist extends HttpServlet {
	CommentsDAO commentsDAO= new CommentsDAO();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String detail=request.getParameter("detail");
		String author=request.getParameter("author");
		String news_id=request.getParameter("news_id");
		
		//DTO에 담기
		Comments comments = new Comments();
		comments.setDetail(detail);
		comments.setAuthor(author);
		
		News news = new News();
		news.setNews_id(Integer.parseInt(news_id));
		comments.setNews(news);//Comments DTO안에 News DTO 넣기. 자식이 부모를 has a 로 보유했기 때문에
		
		commentsDAO.insert(comments);
		
		//클라이언트가 비동기 방식으로 요청을 한다는 것은, 전체 HTML디자인을 바꾸겠다는것이 아니라 현재 디자인 페이지는 유지하되
		//오직 데이터만 주고받기 위함이다 따라서 
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//뉴스기사에 딸려있는 댓글 가져오기
		List<Comments> commentsList= commentsDAO.selectAll(news.getNews_id());
		
		//클라이언트에게 등록과 동시에, 지금까지 누적된 댓글 목록을 보내주자
		//json표기를 일일히 손으로 표기하기에는 한계가 있다. 따라서 GSON을 사용한다.
		Gson gson = new Gson();
		
		String json=gson.toJson(commentsList);
		out.print(json);
	}
}
