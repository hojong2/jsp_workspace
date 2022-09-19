<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}

input[type=text], input[type=password] {
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
  background-color: #0080FF;
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
	width:30%;
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
  margin:auto;
}
</style>
<script>
function registAsync(){
	$.ajax({
		url:"/rest/admin",
		type:"post",
		data:{
			user_id:$("input[name='user_id']").val(),
			pass:$("input[name='pass']").val(),
			name:$("input[name='name']").val()
		},
		success:function(result, status, xhr){
			alert(result);
		}
	});
}
$(function(){
	$($("input[type='button']")[1]).click(function(){
		//비동기로 가입을 처리할 예정
		registAsync();
	})
	$($("input[type='button']")[0]).click(function(){
		//비동기로 가입을 처리할 예정
		$(location).attr("href", "/admin/loginform");
	})
})
</script>
</head>
<body>



<div class="container">
<h3>회원가입</h3>
  <form>
    <input type="text" name="user_id" placeholder="관리자 ID..">
    <input type="password" name="pass" placeholder="관리자 PassWord..">
    <input type="text" name="name" placeholder="관리자 Name..">
    <input type="button" value="관리자 로그인">
    <input type="button" value="관리자 등록">

  </form>
</div>

</body>
</html>