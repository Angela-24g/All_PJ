<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<center>
<h2>제목</h2><br>
<c:forEach var="list" items="${list}">
<p id="title" name="title">${list.title}</p>
<p>작성자&nbsp;&nbsp;|&nbsp;&nbsp;<c:out value="${list.nwriter}"></c:out>
<p>작성일&nbsp;&nbsp;|&nbsp;&nbsp;<fmt:formatDate value="${list.nwdate}" pattern="YYYY-MM-dd hh:mm:ss"/> </p>
<c:choose>
	<c:when test="${id eq 'admin'}">
		<button id="updnb" onclick="location.href='udpnb.do?no=${list.no}'">수정</button>
		<button id="delnb" onclick="location.href='delnb.do?no=${list.no}'">삭제</button><br>
	</c:when>

</c:choose>
<span id="content" name="content">${list.ncontent}</span><br><br>	
<c:forEach var="imglist" items="${imglist}">
	
	<img src="/ysmb/<c:out value="${imglist.url}"/>"/><br>	

	
  </c:forEach>
	
</c:forEach>

</center>

</body>
</html>