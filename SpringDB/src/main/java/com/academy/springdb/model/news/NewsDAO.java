package com.academy.springdb.model.news;

import java.util.List;

import com.academy.springdb.model.domain.News;

public interface NewsDAO {
	public List selectAll();
	public News select(int news_id);
	public void insert(News news);
	public void update(News news);
	public void delete(int news_id);
}
