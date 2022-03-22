<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    h2{text-align: center;}
	table{margin:auto;}
	#col{margin: auto;}
</style>
</head>
<body>
<h2>Community 수정</h2><br>
<form action="updatePro.do" method="post" >
<table>
	<tr>
		<td><h2>이름</h2></td>
		<td><input type="text" name="writer" id="writer" value="${writer}">
			<input type="hidden" name="num" id="num" value="${num}">
		</td>
	
	</tr>
	<tr>
		<td><h2>글제목</h2></td>
		<td><input type="text" name="subject" value="${subject}"></td>
	</tr>
	<tr>
		<td><h2>내용</h2></td>
		<td><textarea rows="20" cols="100" name="content" >${content}</textarea></td>
	</tr>

	<tr>
		<td>
			<input type="submit" value="글수정" >
			<input type="reset" value="다시쓰기">
			<input type="button" value="글삭제" onClick="location.href='delete.do'">
			<input type="button" value="글목록" onClick="location.href='list.do'">
		</td>
	</tr>
</table>
</form>
</body>
</html>