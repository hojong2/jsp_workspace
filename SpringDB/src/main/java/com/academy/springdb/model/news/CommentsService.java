package com.academy.springdb.model.news;

import java.util.List;

import com.academy.springdb.model.domain.Comments;

public interface CommentsService {
	public List selectAll();
	public List selectByNewsId(int news_id);
	public Comments select(int comments_id);
	public void insert(Comments comments);
	public void update(Comments comments);
	public void delete(int comments_id);
}
