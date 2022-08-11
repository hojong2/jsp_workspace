<%@page import="com.aca.web0810.model.BoardDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%request.setCharacterEncoding("utf-8"); %>
<!-- jsp에서는 태그이지만, 오직 서버에서만 실행될수 있는 빈즈 태그를 지원해준다. -->
<jsp:useBean id="board" class="com.aca.web0810.domain.Board"/>
<jsp:setProperty property="*" name="board"/>
<%!
	BoardDAO boardDAO = new BoardDAO();
%>
<%
	int result=boardDAO.edit(board);
	out.print("<script>");
	if(result==0){
		out.print("alert('수정실패');");
		out.print("hitory.back();");
	}else{
		out.print("alert('수정성공');");
		out.print("location.href='/notice/content.jsp?board_id="+board.getBoard_id()+"';");
	}
	out.print("</script>");
%>