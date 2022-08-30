<%@page import="com.academy.web0829.board.util.Pager"%>
<%@page import="java.util.List"%>
<%@page import="com.academy.web0829.board.repository.BoardDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%! 
	BoardDAO boardDAO = new BoardDAO();
	Pager pager = new Pager();
%>
<%
	List boardList = boardDAO.selectAll();
	out.print("게시물 수는 "+boardList.size());
	pager.init(boardList, request);
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
table {
	border-collapse: collapse;
	border-spacing: 0;
	width: 100%;
	border: 1px solid #ddd;
}

th, td {
	text-align: left;
	padding: 16px;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

input[type=button] {
  background-color: #04AA6D;
  color: white;
  padding: 12px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=button]:hover {
  background-color: #45a049;
}

.page-style{
	font-size:20px;
	font-weight:bold;
	color:red;
}
</style>
</head>
<body>

	<table>
		<tr>
			<th width="5%">No</th>
			<th width="75%">기사제목</th>
			<th width="5%">작성자</th>
			<th width="10%">작성일</th>
			<th width="5%">조회수</th>
		</tr>
		<!-- 하나의 페이지에 너무 많은 데이터가 있을 경우, 원하는 크기로 분리하여 보여주는 기법을 페이징(Paging) 처리라
		페이징 처리는 결국 데이터에 대한 산수계산이므로, 개발자마다 본인 스스로 로직을 개발해야함 -->
		<%for(int i=1; i<=pageSize ; i++){ %>
		<tr>
			<td><%%></td>
			<td><%%></td>
			<td><%%></td>
			<td><%%></td>
			<td><%%></td>
		</tr>
		<%} %>
		<tr>
			<td colspan="5" style="text-align:center"></td>
		</tr>
		<tr>
			<td colspan="5"><input type="button" name="글 작성" value="글 작성" onclick="location.href='/board/regist.jsp';"></td>
		</tr>
	</table>

</body>
</html>