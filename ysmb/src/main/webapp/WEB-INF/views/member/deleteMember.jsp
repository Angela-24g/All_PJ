<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function check(){
	if($('#id').val()=='')
		{ alert("id를 입력하세요");}
	else{ 
		$.ajax({
			type:"POST",
			url: "deleteMember.do",
			data: $('#id').val(),
			dataType:"JSON",
			success: function(data){
				if(data.result==1)
				{alert("탈퇴되었습니다.");}
				else {alert("일치하는 ID가 없습니다.");}
			}
		});
	}
}
</script>
</head>
<body>
<center>
<form action="deleteMember.do" onSubmit="return check()">
<h2>회원 탈퇴</h2> 
<br><br>
ID<input type="text" name="id" id="id"><br><br>
<input type="submit" value="회원 탈퇴">
<input type="button" value="이전으로" onClick="location.href='mypage.do'">
</form>
</center>
</body>
</html>