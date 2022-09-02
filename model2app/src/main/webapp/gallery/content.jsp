<%@page import="com.academy.model2app.notice.domain.Notice"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	Notice notice=(Notice)request.getAttribute("notice");
	
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
		//버튼이 두개이므로, 배열로 존재한다
		$($("button")[0]).click(function(){
			if(confirm("수정하시겠어요?")){
				$("form").attr({
					action:"/notice/edit.do",
					method:"post"
				});
				$("form").submit();
			}
		});
		
		$($("button")[1]).click(function(){
			if(confirm("삭제하시겠어요?")){
				$(location).attr({
					href:"/notice/delete.do?notice_id=<%=notice.getNotice_id()%>"
				})
			}
		});
	}); 
  </script>
</head>
<body>

<div class="container">
  <h2>상세보기</h2>
  <p>In this example, we use <code>.was-validated</code> to indicate what's missing before submitting the form:</p>
  <form class="was-validated">
  	<input type="hidden" name="notice_id" value=<%=notice.getNotice_id() %>>
    <div class="form-group">
      <input type="text" class="form-control" name="title" value=<%=notice.getTitle()%>>
    </div>
    <div class="form-group">
      <input type="text" class="form-control" name="writer" value=<%=notice.getWriter()%>>
    </div>
    <div class="form-group">
      <textarea class="form-control" name="content"><%=notice.getContent()%></textarea>
    </div>
    
    <button type="button" class="btn btn-primary">수정</button>
    <button type="button" class="btn btn-primary">삭제</button>
  </form>
</div>

</body>
</html>