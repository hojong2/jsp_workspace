package com.academy.springdb.model.news;

import java.util.List;

import com.academy.springdb.model.domain.Comments;

public class HibernateCommentsDAO implements CommentsDAO{

	@Override
	public List selectAll() {
		
		return null;
	}

	@Override
	public List selectByNewsId(int news_id) {
		
		return null;
	}

	@Override
	public Comments select(int comments_id) {
		
		return null;
	}

	@Override
	public void insert(Comments comments) {
	}

	@Override
	public void update(Comments comments) {
	}

	@Override
	public void delete(int comments_id) {
	}

}
