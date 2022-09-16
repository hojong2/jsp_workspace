package com.academy.springdb.model.news;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.academy.springdb.exception.CommentsException;
import com.academy.springdb.exception.NewsException;
import com.academy.springdb.model.domain.News;

@Service
public class NewsServiceImpl implements NewsService{

	@Autowired
	@Qualifier("mybatisNewsDAO")
	private NewsDAO newsDAO;
	
	@Autowired
	@Qualifier("mybatisCommentsDAO")
	private CommentsDAO commentsDAO;
	
	@Override
	public List selectAll() {
		return newsDAO.selectAll();
	}

	@Override
	public News select(int news_id) {
		
		return newsDAO.select(news_id);
	}

	@Override
	public void regist(News news) throws NewsException{
		newsDAO.insert(news);
	}

	@Override
	public void update(News news) throws NewsException{
		newsDAO.update(news);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(int news_id) throws NewsException, CommentsException{
		
		//Transaction : 세부업무가 모두 성공해야 전체를 성공으로 간주하는 업무(DML) 수행단위
		
		//세부업무1 댓글 삭제
		commentsDAO.deleteByNewsId(news_id);  //CommentsException 예외 걸려있음
		
		//세부업무2 뉴스 삭제
		newsDAO.delete(news_id);  //NewsException 예외 걸려있음
		
		
	}
	
}
