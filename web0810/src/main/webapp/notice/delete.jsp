<%@page import="com.aca.web0810.model.BoardDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%!
	BoardDAO boardDAO = new BoardDAO();
%>
<%
	String board_id=request.getParameter("board_id");
	out.print(board_id);
	
	int result = boardDAO.delete(Integer.parseInt(board_id));
	out.print("<script>");
	if(result==0){
		out.print("alert('삭제실패');");
		out.print("hitory.back();");
	}else{
		out.print("alert('삭제성공');");
		out.print("location.href='/notice/list.jsp';");
	}
	out.print("</script>");
%>