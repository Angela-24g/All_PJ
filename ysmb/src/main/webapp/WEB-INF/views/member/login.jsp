<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <script src="//code.jquery.com/jquery-3.6.0.min.js"></script>
 <style>
 	.id .pw{align:right;}
 </style>
 <script>
  	function check(event){
  		event.preventDefault();
  		if($('#id').val()=='')
			{ alert("아이디를 입력하시오."); $('#id').focus(); return false; }
		if($('#pw').val()=='')
		{ alert("비밀번호를 입력하시오."); $('#pw').focus(); return false; }
  		
		var id = $('#id').val();
 		var pw = $('#pw').val();
 		var object = {'id':id,'pw':pw};
 		var urlp = "<%=(String)session.getAttribute("str")%>";
  		 $.ajax({
 			type: "POST",
 			url: "loginPro.do",
 			data: object,
 			//contentType: "application/json; charset=utf-8",
 			
 			success: function(check){
 			console.log(check);
 			
 				if(check=="1"){
 					window.location.href="list.do?str="+urlp;	
 				}
 				else { 
 					alert("로그인에 실패했습니다.");
 					$('#id').val('');
 					$('#pw').val('');
 				}
 			}
 		}); 
     }  	
 </script>
</head>
<body>
<center>

<h2>로그인</h2>
<form onsubmit="check(event)"> <!-- action/submit은 동작이 같다면 action지우기 -->
ID <input type="text" name="id" id="id"><br>
비밀번호 <input type="password" name="pw" id="pw"><br>
<input type="submit" value="로그인" style="width:25%;"><br><br>
<a href="findID.do">ID찾기</a>
&nbsp;|&nbsp;
<a href="findPW.do">비밀번호찾기</a>


</center>
</form>
</body>
</html>