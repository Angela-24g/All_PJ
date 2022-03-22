<%@page import="java.util.Date"%>
<%@page import="co.kr.ysmb.dto.NBoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	table{margin:auto; text-align:center;}
	div{text-align:center;}
	.title{font-weight:bold;}
</style>
</head>
<body>
<% 
		NBoardDTO nbdto = (NBoardDTO)request.getAttribute("ndto");
	 	String writer = nbdto.getNwriter();
		Date date = nbdto.getNwdate();
		String content = nbdto.getNcontent();
		String title = nbdto.getTitle();
		String name = (String)session.getAttribute("name");
		
	%>	
<c:choose>
	<c:when test="${id eq 'writer'}">
		<button id="updnb" onclick="location.href='udpnb.do?no=${list.no}'">수정</button>
		<button id="delnb" onclick="location.href='delnb.do?no=${list.no}'">삭제</button><br>
	</c:when>
</c:choose>
 <table>
  <thead>
	

	<tr><%= title %></tr>
	<tr><td>작성자</td><td><%= writer %></td></tr>
	<tr><td>작성일</td><td><fmt:formatDate value="<%= date %>" pattern="YYYY-MM-dd HH:mm:ss"/></td></tr>
	<tr><td>내용</td>	<td><%= content %></td></tr>
			
	</thead>
	
	<tbody> 
		 	<tr>
		 		<td cols="2"></td>
				<td>
				<div>
					<c:choose>
						<c:when test="${imglist.thumb eq null}">
							<c:forEach var="imglist" items="${imgList}">
								<img src="/ysmb/<c:out value="${imglist.url}"/>"/><br>							
							</c:forEach>
						</c:when>
					 </c:choose>	
					 </div>
				</td>
			</tr> 
	</tbody>		
 </table>

</body>
</html>