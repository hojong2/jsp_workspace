package com.academy.springdb.model.news;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.academy.springdb.exception.CommentsException;
import com.academy.springdb.model.domain.Comments;

@Service
public class CommentsServiceImpl implements CommentsService{

	@Autowired
	@Qualifier("jdbcCommentsDAO")
	private CommentsDAO commentsDAO;
	
	@Override
	public List selectAll() {
		
		return null;
	}

	@Override
	public List selectByNewsId(int news_id) {
		return commentsDAO.selectByNewsId(news_id);
	}

	@Override
	public Comments select(int comments_id) {
		
		return null;
	}

	@Override
	public void insert(Comments comments) throws CommentsException {
		commentsDAO.insert(comments);
	}

	@Override
	public void update(Comments comments) {
	}

	@Override
	public void delete(int comments_id) {
	}

}
