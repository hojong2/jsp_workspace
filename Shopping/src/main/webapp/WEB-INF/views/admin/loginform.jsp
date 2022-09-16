<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
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
	width:30%;
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
  margin:auto;
}
</style>
</head>
<body>



<div class="container">
<h3>로그인</h3>
  <form action="/action_page.php">
    <input type="text" name="firstname" placeholder="Your ID..">
    <input type="password" name="firstname" placeholder="Your PassWord..">
    <input type="button" value="관리자 로그인">
    <input type="button" value="관리자 등록" onClick="location.href='/admin/registform'">

  </form>
</div>

</body>
</html>