<%@page import="com.academy.springmvcsimple.domain.Notice"%>
<%@page import="java.util.List"%>
<%@page import="com.academy.springmvcsimple.util.Pager"%>
<%@page import="com.academy.springmvcsimple.model.repository.NoticeDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%! 
	Pager pager = new Pager();
	%>
<%
	List <Notice> noticeList = (List)request.getAttribute("noticeList");
	out.print("게시물 수는 :"+noticeList.size());
	pager.init(noticeList, request);//공식 계산 
	
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
			<th width="75%">제목</th>
			<th width="5%">작성자</th>
			<th width="10%">작성일</th>
			<th width="5%">조회수</th>
		</tr>
		<%
			int curPos = pager.getCurPos();
			int num = pager.getNum();
		%>
		<%=pager.getPageSize() %>
	<%for(int i=1; i<=pager.getPageSize(); i++){ %>
	<% if(num<1)break; %>
	<%Notice notice =noticeList.get(curPos++); %>
		<tr>
			<td><%=num-- %></td>
			<td><a href="/board/content?notice_id=<%=notice.getNotice_id() %>"><%=notice.getTitle() %></a></td>
			<td><%=notice.getWriter() %></td>
			<td><%=notice.getRegdate() %></td>
			<td><%=notice.getHit() %></td>
		</tr>
	<%} %>
		<tr>
			<td colspan="5" style="text-align:center">
			<%for(int i = pager.getFirstPage();i<=pager.getLastPage();i++){ %>
			<%if(i>pager.getTotalPage())break; %>
				[<%=i %>]
				<%} %>
			</td>
		</tr>
		<tr>
			<td colspan="5"><button onClick="location.href='/board/regist.jsp';">글작성</button></td>
		</tr>
	</table>

</body>
</html>