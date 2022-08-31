<%@ page contentType="text/html;charset=UTF-8"%>
<%
	String msg=(String)request.getAttribute("data");
	String blood=(String)request.getAttribute("data1");
	out.print(blood+"형  ");
	out.print(msg);
%>