<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function getResult(){
	var form=document.querySelector("form");
	form.action="/movie.do";
	form.method="post";
	form.submit();
}
</script>
</head>
<body>
	<form>
		<select name="movie">
			<option>최근에 보신 영화를 선택하세요</option>
			<option value="이상한 변호사 우영우">이상한 변호사 우영우</option>
			<option value="오징어게임">오징어게임</option>
			<option value="탑건 메버릭">탑건 메버릭</option>
			<option value="헐크">헐크</option>
		</select>
	</form>
	<button onClick="getResult()">결과보기</button>
</body>
</html>