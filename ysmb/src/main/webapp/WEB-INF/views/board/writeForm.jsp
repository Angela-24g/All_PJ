<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>writeForm.jsp</title>
<style type="text/css">
	h2{ text-align: center;}
	table{ margin: auto; background-color: ivory;}
</style>

<script src="//code.jquery.com/jquery-3.6.0.min.js"></script>  

<!-- 데이터 유효성 처리  jquery는 .val()이고 javascript는 .value이다.-->
<script>
 function check(e){
//	int pwl = $('#password').val().length;
		if($('#writer').val()=='')
			{
			alert("이름을 입력하시오");
			$('#writer').focus();
			return false;
			}
		if($('#subject').val()=='')
			{
			alert("글 제목을 입력하시오");
			$('#subject').focus();
			return false;
			}
		if($('#content').val()=='')
			{
			alert("글 내용을 입력하시오");
			$('#content').focus();
			return false;
			}
		
			
			console.log(event.target.checked);
		if($('input:checkbox[id="chk"]').is(":checked") == true)
		{	if($('#password').val()=='')
		
			{
			alert("암호를 입력하시오");
			$('#password').focus();
			return false;
			}

 		else if($("#password").val().length<4 || $("#password").val().length>4)
			{
 			
				alert("비밀번호는 4글자의 숫자를 입력하시오");				
				$('#password').val('');
				$('#password').focus();
				return false; 
			} 
 		else if($('#password').val().search(/[0-9]/g)<0)
		{
			alert("숫자를 입력하시오");
			$('#password').val('');
			$('#password').focus();
			return false; 
		}}
		return true;
	} //check-end 
	
	
	function submitHandler(event) {
		//e.preventDefault();
		check();	
		/* const data = {
				pageNum : e.target[0].value,
				num: e.target[1].value,
				ref: e.target[2].value,
				re_step: e.target[3].value,
				re_level: e.target[4].value,
				writer: e.target[5].value,
				subject: e.target[6].value,
				content: e.target[7].value,
				chk: e.target[8].value,
				password: e.target[9].value
		}*/
		
		
	}
	function chkhandler(e){
		if(e.target.checked)
			{
			$('#password').attr("readonly",false);
				
			}
		else{$('#password').attr("readonly",true); $('#password').val('');} 
	}
</script>
</head>
<body>
	<c:if test="${num==0}">
		<h2>게시판 글쓰기</h2>
	</c:if>
	
	<c:if test="${num!=0}">
		<h2>답글 쓰기</h2>
	</c:if>
	
	<form method="POST" action="writePro.do" onsubmit="return check(event)">  <!-- onsubmit은 함수 호출 action은 거기로 가라 -->
		<input type="hidden" name="pageNum" value="${pageNum}"> <!-- 데이터를 감추며 보내고싶다! -->
		<input type="hidden" name="num" value="${num}">
		<input type="hidden" name="ref" value="${ref}">
		<input type="hidden" name="ref_step" value="${re_step}">
		<input type="hidden" name="ref_level" value="${re_level}">
		
		<table border="1">
			<tr>
				<td>이름</td>
				<td><input type="text" value="${name}" name="writer" id="writer" size="30"></td>
			</tr>
			
			<tr>
				<td>글제목</td>
				<td>
				<!-- 원글 -->
				<c:if test="${num==0}">
					<input type="text" name="subject" id="subject" size="40">
				</c:if>
				
				<!-- 답글 -->
				<c:if test="${num!=0}">
					<input type="text" value="${subject}" readonly>
				</c:if>
				</td>
				<!-- 답글 --><c:if test="${num!=0}">
				<tr>
				<td>답변 제목</td>
				<td>
					<input type="text" name="subject" id="subject" size="40" value="[답변]">
				</td>
				</tr>
				</c:if>
			</tr>
			
			<tr>
				<td>글 내용</td>
				<td><textarea name="content" id="content" rows="10" cols="60"></textarea></td>
			</tr>
			
			<tr>
	 		<td>비밀번호 &nbsp;&nbsp; 
	 			<input type="checkbox" id="chk" name="chk" onchange="chkhandler(event)">
	 		</td>
			<td>
				<input type="text" name="password" id="password" readonly>
			</td>
					
			</tr>
			<tr>
				<td colspan="2" align="center">
					<!-- 글쓰기 -->
					<c:if test="${num==0}">
						<input type="submit" value="글쓰기">
					</c:if>
					<!-- 답변쓰기 -->
					<c:if test="${num!=0}">
						<input type="submit" value="답글쓰기">
					</c:if>
					
					<input type="reset" value="다시쓰기">
					<input type="button" value="글 목록" onClick="location.href='list.do?str=${str}&pageNum=1'">
										
				</td>
			</tr>
		</table>
	</form>
	
</body>
</html>