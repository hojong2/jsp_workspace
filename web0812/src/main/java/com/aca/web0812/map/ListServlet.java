package com.aca.web0812.map;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aca.web0812.domain.HotSpot;
import com.aca.web0812.model.HotSpotDAO;

//데이터 목록을 구하는 서블릿
public class ListServlet extends HttpServlet {
	
	HotSpotDAO hotspotDAO;
	
	public void init() throws ServletException {
		hotspotDAO = new HotSpotDAO();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//DB와의 CRUD는 별도의 전담객체가 따로 있다(DAO)
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		List<HotSpot> list = hotspotDAO.selectAll();
		
		//아래처럼 불편하게 로직을 짜지 않는다. 주로 라이브러리를 이용 ex.GSON
		//추후 스프링을 사용시 아래의 코드가 아닌 그냥 자동으로 제이슨 배열을 생성해준다.
		out.print("[");
		for(int i=0; i<list.size(); i++) {
			HotSpot dto=list.get(i);
			out.print("{");
			out.print("\"hotspot\":"+dto.getHotspot_id()+",");		
			out.print("\"lati\":"+dto.getLati()+",");		
			out.print("\"longi\":"+dto.getLongi()+",");		
			out.print("\"icon\":\""+dto.getIcon()+"\",");		
			out.print("\"content\":\""+dto.getContent()+"\"");
			if(i<list.size()-1) {
				out.print("},");
			}else {
				out.print("}");
			}
			}
			out.print("]");
	}
}
