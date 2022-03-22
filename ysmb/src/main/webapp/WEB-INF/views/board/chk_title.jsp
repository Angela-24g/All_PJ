<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
function chk(){
	e.preventDefault(); //event막기
	var password = $('#password').val();
	var num = $('#num').val();
	
	if($('#password').val()=='')
		{
			alert("암호를 입력하시오.");
			$('#password').focus();
			
		}
	
	
}
</script>
<body>
비밀 글 입니다. 암호를 입력해주세요.
<br>
<br>
비밀번호<br>
<form action="chktitle.do" method="POST" onsubmit="chk()">
<input type="text" name="password" id="password"> 
<input type="hidden" name="num" id="num" value="${num}">
<input type="submit" value="확인" >
</form>
</body>
</html>