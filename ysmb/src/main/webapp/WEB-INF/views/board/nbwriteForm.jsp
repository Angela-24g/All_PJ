<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	h2{text-align: center;}
	table{margin:auto;}
</style>
<script>
	function check(){
		if($('#title').val()=='')
			{ alert("제목을 입력하시오");}
		if($('#ncontent').val()=='')
		{ alert("내용을 입력하시오");}
		form.submit();
	}
</script>
</head>
<body>
<h2>공지사항</h2>
<br>
<form action="flPro.do" onsubmit="check()" method="post" enctype="multipart/form-data">
  <table>
  	<tr>
  		<td>글제목 <input type="text" name="title" id="title" maxlength="20"style="width:80%;"></td>
 
  	</tr>
  	<tr>
  		<td>글내용<textarea rows="15" cols="100" name="ncontent" id="ncontent"></textarea><br></td>
  	</tr>
  	<tr>
  		<td>profile<img id="myimg"/><br>
	       <input type="file" id="profile" name="profileFile"/></td>
  	</tr> 
  	<tr>
  		<td><input type="submit" value="글쓰기">&nbsp;</td>
  		<td><input type="reset" value="전체취소">&nbsp;</td>
  		<td><input type=button onClick="location.href='list.do?str=${str}&pageNum=1'"value="글목록"></td>
  	</tr>
  </table> 
   
</form>

</body>
</html>