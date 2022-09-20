<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body style="background:yellow">
	<h1>응~ 오류났어~</h1>
	<%
		RuntimeException e = (RuntimeException)request.getAttribute("e");
		out.print("다음에 다시와~<p>");
		out.print(e);
	%>
</body>
</html>