package com.academy.springdb.model.news;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.academy.springdb.model.domain.News;

@Repository
public class HibernateNewsDAO implements NewsDAO{

	@Override
	public List selectAll() {
		
		return null;
	}

	@Override
	public News select(int news_id) {
		
		return null;
	}

	@Override
	public void insert(News news) {
		System.out.println("hibernate 템플릿으로 insert 시도");
	}

	@Override
	public void update(News news) {
	}

	@Override
	public void delete(int news_id) {
	}

}
