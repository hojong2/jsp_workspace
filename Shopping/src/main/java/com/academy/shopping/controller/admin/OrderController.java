package com.academy.shopping.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.academy.shopping.model.domain.OrderSummary;
import com.academy.shopping.model.order.OrderSummaryService;

@Controller
@RequestMapping("/admin")      // 공통 uri
public class OrderController {
   @Autowired
   private OrderSummaryService orderSummaryService;
   
   
   @GetMapping("/order/list")
   public ModelAndView getList(HttpServletRequest request) {
      List orderSummaryList = orderSummaryService.selectAll();
      
      
       ModelAndView mav =  new ModelAndView("admin/order/orderlist");
       mav.addObject("orderSummaryList", orderSummaryList);
       return mav;
   }
   
   //주문 상세 정보 요청 처리
   @GetMapping("/order/detail")
   public ModelAndView getDetail(HttpServletRequest request, int ordersummary_id) {
	   OrderSummary orderSummary = orderSummaryService.select(ordersummary_id);
	   ModelAndView mav = new ModelAndView("admin/order/orderdetail");
	   mav.addObject("orderSummary", orderSummary);
	   return mav;
   }
}
