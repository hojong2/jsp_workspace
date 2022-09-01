<%@page import="com.academy.model2app.notice.domain.Notice"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	List<Notice> noticeList = (List)request.getAttribute("noticeList");
	out.print("게시물 수는 "+noticeList.size());
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
       for(int i = 0; i < noticeList.size(); i++) {
          Notice notice = noticeList.get(i);
    %>
      <tr>
        <td><%=notice.getNotice_id() %></td>
        <td><%=notice.getTitle() %></td>
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