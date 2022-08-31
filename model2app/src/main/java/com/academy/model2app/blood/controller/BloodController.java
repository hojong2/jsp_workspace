package com.academy.model2app.blood.controller;

import java.net.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.academy.model2app.blood.model.BloodManager;
import com.academy.model2app.controller.Controller;

public class BloodController implements Controller{
	BloodManager manager = new BloodManager();
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String blood=request.getParameter("blood");
		String msg=manager.getAdvice(blood);
		
		request.setAttribute("data", msg);
		request.setAttribute("data1", blood);
		
	}
	
	public String getViewPage() {
		return "/blood/advice.jsp";
	}
}
