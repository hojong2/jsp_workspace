<%@page import="com.academy.model2app.util.Pager"%>
<%@page import="com.academy.model2app.notice.domain.Notice"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%! Pager pager = new Pager(); %>
<%
	List<Notice> noticeList = (List)request.getAttribute("noticeList");
	out.print("게시물 수는 "+noticeList.size());
	pager.init(noticeList, request);  //페이징 공식이 실행
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
  <script type="text/javascript">
  $(function(){
	  $("button").click(function(){
		  $(location).attr({"href":"/notice/write.jsp"});
	  });
  });
  </script>
</head>
<body>

<div class="container">
  <h2>Dark Striped Table</h2>
  <p>Combine .table-dark and .table-striped to create a dark, striped table:</p>            
  <table class="table table-dark table-striped">
    <thead>
      <tr>
        <th>No</th>
        <th>제목</th>
        <th>작성자</th>
        <th>등록일</th>
        <th>조회수</th>
      </tr>
    </thead>
    <tbody>
      <%
      int curPos=pager.getCurPos();
      int num=pager.getNum();
       for(int i = 0; i < pager.getPageSize(); i++) {
    	   if(num<1)break;
          Notice notice = noticeList.get(curPos++);
    %>
      <tr>
        <td><%=num--%></td>
        <td><a href="/notice/content.do?notice_id=<%=notice.getNotice_id()%>"><%=notice.getTitle() %></a></td>
        <td><%=notice.getWriter() %></td>
        <td><%=notice.getRegdate().substring(0,16) %></td>
        <td><%=notice.getHit() %></td>
      </tr>
      
      <%} %>
      <tr>
      	<td colspan="5"><button>글작성</button></td>
      </tr>
    </tbody>
  </table>
</div>

</body>
</html>