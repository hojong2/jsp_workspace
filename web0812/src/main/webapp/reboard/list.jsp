<%@page import="com.aca.web0812.domain.ReBoard"%>
<%@page import="java.util.List"%>
<%@page import="com.aca.web0812.reboard.model.ReBoardDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%! ReBoardDAO reBoardDAO = new ReBoardDAO(); %>
<%
	
	List<ReBoard> boardList = reBoardDAO.selectAll();

	int totalRecord=boardList.size(); //총 레코드 수
	int pageSize=10; //한 페이지당 보여질 레코드 수
	int totalPage=(int)Math.ceil((float)totalRecord/pageSize);
	int blockSize=10;
	int currentPage=1;
	if(request.getParameter("currentPage")!=null){
		currentPage=Integer.parseInt(request.getParameter("currentPage"));
	}
	int firstPage = currentPage-(currentPage-1)%blockSize;
	int lastPage = firstPage + (blockSize-1);
	int curPos=(currentPage-1)*pageSize;  //페이지당 List의 시작 index
	int num=totalRecord-curPos;//페이지당 시작 번호
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
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
#reply{
	transform: rotate(180deg);
	width:13px;
}
</style>
<%@ include file="/inc/header.jsp" %>
<script>
$(document).ready(function(){  //addEventListener("load", function(){})와 동일
	$("button").click(function(){
		//location.href="/reboard/regist.jsp"; //javascript
		$(location).attr("href", "/reboard/regist.jsp");  //jquery
	});
});
</script>
</head>
<body>
	<table>
		<tr>
			<th>No</th>
			<th>제목</th>
			<th>작성자</th>
			<th>등록일</th>
			<th>조회수</th>
		</tr>
		
		<%for(int i=1; i<pageSize; i++){ //언제나 10건씩 노출시키자 %>
		<%if(num<1) break; %>
		<%ReBoard reBoard = boardList.get(curPos++); %>
		<tr>
			<td><%=num--%></td>
			<td>
				<%if(reBoard.getDepth()>0){ %>
					<img src="/res/images/reply.webp" id="reply" style="margin-left: <%=reBoard.getDepth()*10%>px;">	
				<%} %>
				<a href="/reboard/content.jsp?reboard_id=<%=reBoard.getReboard_id()%>"><%=reBoard.getTitle()%></a>
			</td>
			<td><%=reBoard.getWriter()%></td>
			<td><%=reBoard.getRegdate()%></td>
			<td><%=reBoard.getHit()%></td>
		</tr>
		<%} %>
		<tr>
			<td colspan="5"><button>글등록</button>
		</tr>
	</table>
	<%@ include file="/inc/footer.jsp" %>
</body>
</html>