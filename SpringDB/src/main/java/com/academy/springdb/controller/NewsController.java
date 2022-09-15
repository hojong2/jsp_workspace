package com.academy.springdb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import com.academy.springdb.exception.NewsException;
import com.academy.springdb.model.domain.News;
import com.academy.springdb.model.news.NewsService;

@Controller //스프링 컨테이너가 메모리에 올릴 대상이 될 수 있도록
public class NewsController {
	
	@Autowired
	private NewsService newsService;
	
	public NewsController() {
		// TODO Auto-generated constructor stub
		System.out.println("응애 나 그냥 컨트롤러");
	}
	/*
	@GetMapping("/rest")
	@ResponseBody
	public String test() {
		return "하이";
	}*/
	
	@GetMapping("/news/list")
	public ModelAndView selectAll() {
		List newsList = newsService.selectAll();
		ModelAndView mav = new ModelAndView("news/list");
		mav.addObject("newsList", newsList);
		return mav;
	}
	//글쓰기 폼 요청
	@GetMapping("/news/registform")
	public ModelAndView registForm() {
		ModelAndView mav = new ModelAndView("news/regist");
		return mav;
	}
	
	//글쓰기 요청처리
	@PostMapping("/news/regist")
	public String regist(News news) {
		newsService.regist(news);
		return "redirect:/news/list";
	}
	
	@GetMapping("/news/content")
	public ModelAndView select(int news_id) {
		News news = newsService.select(news_id);
		ModelAndView mav = new ModelAndView("news/content");
		mav.addObject("news", news);
		return mav;
	}
	
	@PostMapping("/news/update")
	public ModelAndView update(News news) {
		newsService.update(news);
		ModelAndView mav = new ModelAndView("redirect:/news/content?news_id="+news.getNews_id());
		return mav;
	}
	
	@GetMapping("/news/delete")
	public String delete(int news_id) {
		newsService.delete(news_id);
		
		return "redirect:/news/list";
	}
	
	//스프링 MVC의 컨트롤러의 메서드들 중에서 예외가 발생할때, 이 예외를 처리할 메서드를 지원해준다.
	@ExceptionHandler(NewsException.class)
	public ModelAndView handleException(NewsException e) {
		
		//클라이언트가 에러 메시지를 볼 수 있도록 뷰로 저장한다
		ModelAndView mav= new ModelAndView("error/result");
		mav.addObject("msg", e.getMessage());
		return mav;
	}
}
