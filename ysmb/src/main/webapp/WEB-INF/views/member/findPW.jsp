<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function findid()
	{
		
	}
	function findpw(){
		
	}
</script>
</head>
<body>
<form onsubmit="findid()">
이름<input type="text" id="name" name="name">
전화번호<input type="text" id="tel" name="tel">&nbsp;<input type="submit" value="비밀번호 찾기">
<br><p>전화번호 입력 시, - 문자 제외</p><br>

</form>
<br><br>
<form onsubmit="findpw()">
아이디<input type="text" id="id" name="id">

<input type="submit" value="비밀번호 찾기">
</form>
</body>
</html>