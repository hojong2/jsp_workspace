<%@ page contentType="text/html;charset=UTF-8"%>
<%
	String msg=(String)request.getAttribute("data");
	String movie=(String)request.getAttribute("data1");
	out.print("선택한 항목은 "+movie+":  ");
	out.print(msg);
%>