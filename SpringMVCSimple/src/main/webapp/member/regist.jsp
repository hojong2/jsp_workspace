<%@ page contentType="text/html;charset=UTF-8"%>
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
				<div class="form-group row">
					<label for="inputEmail3" class="col-sm-2 col-form-label">사원명</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="inputEmail3" placeholder="사원명" name="ename">
					</div>
				</div>
				<div class="form-group row">
					<label for="inputPassword3" class="col-sm-2 col-form-label">희망급여</label>
					<div class="col-sm-10">
						<input type="number" class="form-control" id="inputPassword3" placeholder="희망급여" name="sal">
					</div>
				</div>
				<div class="form-group row">
					<label for="inputPassword3" class="col-sm-2 col-form-label">희망부서</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="inputPassword3" placeholder="희망부서" name="dept.dname">
					</div>
				</div>
				<div class="form-group row">
					<label for="inputPassword3" class="col-sm-2 col-form-label">부서위치</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="inputPassword3" placeholder="부서위치" name="dept.loc">
					</div>
				</div>
			</div>
			<!-- /.card-body -->
			<div class="card-footer">
				<button type="button" class="btn btn-danger">사원등록</button>
				<button type="submit" class="btn btn-default float-right">Cancel</button>
			</div>
			<!-- /.card-footer -->
		</form>
	</div>
</body>
</html>