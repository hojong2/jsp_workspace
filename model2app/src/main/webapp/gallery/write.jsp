<%@ page contentType="text/html;charset=UTF-8"%>
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
			//서버에 요청
			$("form").attr({
				method:"post",
				action:"/gallery/regist.do",
				enctype:"multipart/form-data"  //바이너리 파일이 포함될 경우 반드시 이 속성이 지정되어야 한다
			});
			
			$("form").submit();
		});
	}); 
  </script>
</head>
<body>

<div class="container">
  <h2>Form Validation</h2>
  <p>In this example, we use <code>.was-validated</code> to indicate what's missing before submitting the form:</p>
  <form class="was-validated">
  
    <div class="form-group">
      <input type="text" class="form-control" placeholder="제목 입력" name="title" required>
    </div>
    <div class="form-group">
      <input type="text" class="form-control" placeholder="작성자 입력" name="writer" required>
    </div>
    <div class="form-group">
      <textarea class="form-control" name="content"></textarea>
    </div>
    <div>
    	<input type="file" name="photo">
    </div>
    
    <button type="button" class="btn btn-primary">Submit</button>
  </form>
</div>

</body>
</html>