package com.academy.springdb.model.news;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.academy.springdb.exception.NewsException;
import com.academy.springdb.model.domain.News;

@Service
public class NewsServiceImpl implements NewsService{

	@Autowired
	@Qualifier("mybatisNewsDAO")
	private NewsDAO newsDAO;
	
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

	@Override
	public void delete(int news_id) throws NewsException{
		newsDAO.delete(news_id);
	}
	
}
