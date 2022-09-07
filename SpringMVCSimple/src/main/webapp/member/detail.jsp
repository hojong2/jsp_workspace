<%@page import="com.academy.springmvcsimple.domain.Emp"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	Emp emp=(Emp)request.getAttribute("emp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원등록</title>
<%@ include file="/inc/header.jsp" %>
</head>
<%@include file="/inc/putter.jsp" %>

<!-- Page specific script -->
<script>
$(function () {
  //bsCustomFileInput.init();
  
  $("button[type='button']").click(function(){
	  $(".form-horizontal").attr({
		  action:"/member/regist",
		  method:"post" 
	  });
	  $(".form-horizontal").submit();
  });
});

function del(){
	if(confirm("삭제하시겠습니까?")){
		location.href="/member/delete?notice_id=<%=emp.getEmpno()%>";
	}
}
function edit(){
	if(confirm("수정하시겠어요?")){
		form1.action = "/member/edit";
		form1.method = "post";
		form1.submit();
	}
}
</script>
<body>
	<div class="card card-info">
		<div class="card-header">
			<h3 class="card-title">사원등록 Form</h3>
		</div>
		<!-- /.card-header -->
		<!-- form start -->
		<form class="form-horizontal">
			<div class="card-body">
				<input type="hidden" value="<%=emp.getEmpno() %>" name="empno"/>
				<div class="form-group row">
					<label for="inputEmail3" class="col-sm-2 col-form-label">사원명</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="inputEmail3" name="ename" value=<%=emp.getEname() %>>
					</div>
				</div>
				<div class="form-group row">
					<label for="inputPassword3" class="col-sm-2 col-form-label">희망급여</label>
					<div class="col-sm-10">
						<input type="number" class="form-control" id="inputPassword3" name="sal" value=<%=emp.getSal() %>>
					</div>
				</div>
				<div class="form-group row">
					<label for="inputPassword3" class="col-sm-2 col-form-label">희망부서</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="inputPassword3" name="dept.dname" value=<%=emp.getDept().getDname() %>>
					</div>
				</div>
				<div class="form-group row">
					<label for="inputPassword3" class="col-sm-2 col-form-label">부서위치</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="inputPassword3" name="dept.loc" value=<%=emp.getDept().getLoc() %>>
					</div>
				</div>
			</div>
			<!-- /.card-body -->
			<div class="card-footer">
				<button type="button" class="btn btn-danger" onclick="edit()">수정</button>
				<button type="button" class="btn btn-danger" onclick="del()">삭제</button>
				<button type="button" class="btn btn-danger" onclick="location.href='/member/list'">목록</button>
				<button type="submit" class="btn btn-default float-right">Cancel</button>
			</div>
			<!-- /.card-footer -->
		</form>
	</div>
</body>
</html>