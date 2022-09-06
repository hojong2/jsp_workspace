<%@page import="com.academy.springmvcsimple.domain.Notice"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	Notice notice = (Notice)request.getAttribute("notice");
%>

<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}
input[type=text], select, textarea {
  width: 100%;
  padding: 12px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  margin-top: 6px;
  margin-bottom: 16px;
  resize: vertical;
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
.container {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
#comments-list{
	border:1px solid red;
	overflow:hidden;
}
#comments-list *{
	float: left;
}
.title-style{width:80%}
.writer-style{width:10%}
.regdate-style{width:10%}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>

	function del(){
		if(confirm("삭제하시겠습니까?")){
			location.href="/board/delete?notice_id=<%=notice.getNotice_id()%>";
		}
	}
	function edit(){
		if(confirm("수정하시겠어요?")){
			form1.action = "/board/edit";
			form1.method = "post";
			form1.submit();
		}
	}
</script>
</head>
<body >


<div class="container">
  <form name="form1">
  	<input type="hidden" name ="notice_id" value="<%=notice.getNotice_id() %>">    
  	<input type="text" name="title" value="<%=notice.getTitle()%>">
    <input type="text"name="writer" value="<%=notice.getWriter()%>">
    <textarea name="content" style="height:200px"><%=notice.getContent() %></textarea>
    
    <input type="button" value="등록" onClick="regist()">
    <input type="button" value="목록" onClick="location.href='/board/list'">
    <input type="button" value="수정" onClick="edit();">
    <input type="button" value="삭제" onClick="del();">
    
  </form>

</div>

</body>
</html>
