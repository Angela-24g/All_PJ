<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list.jsp</title>
<style>
	.writebtn{text-align: right; margin-right: 400px;}
	h2{text-align: center;}
	table{margin:auto; background: ivory;}
</style>
</head>
<body>

<c:choose>
<c:when test="${str eq 'comm'}">
<h2>Community</h2>
<table>
<div class="writebtn"><a href="writeForm.do">글쓰기</a></div><br>
	<tr>
		<td>번호</td>
		<td colspan="2" style="text-align:center;">제목</td>
		<td>작성자</td>
		<td>작성일</td>
		<td>조회</td>
		<td>IP</td>
	</tr>
	<c:forEach var="data" items="${list}" varStatus="status">
		<tr>
			<td>${data.num}</td>
			<td>
			<input type="hidden" name="password" value="${data.pstate}" id="password">
			
			<c:choose>
				<c:when test="${data.pstate eq 't'}">
					<a href="chkform.do?num=${data.num}">${data.subject}</a>
							
			</td>
					<input type="hidden" name="num" id="num" value="${data.num}">
				</c:when>
				<c:otherwise>
					<a href="viewForm.do?&num=${data.num}">${data.subject}</a></td>
				</c:otherwise>
			</c:choose>
			<td>
				<c:if test="${data.pstate eq 't'}">
					[비밀글]
				</c:if>
			</td>
			<td>${data.writer}</td>
			<td><fmt:formatDate value="${data.regdate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
			<td>${data.readcount}</td>
			<td>${data.ip}</td>
		</tr>
	</c:forEach>
</table>
</c:when>
<c:otherwise>
<h2>공지사항</h2>
<table>
<c:choose>
<c:when test="${id eq 'admin'}">
<div class="writebtn">
	<a href="flgift.do" style="font-weight:bold;">글쓰기</a>
</div>
<br>
</c:when>
</c:choose>
	<tr>
		<td colspan="2" style="font-weight:bold;">번호</td>
		<td colspan="2" style="text-align:center;font-weight:bold;" >제목</td>
		<td colspan="2" style="text-align:center;font-weight:bold;">작성자</td>
		<td colspan="2" style="text-align:center;font-weight:bold;">작성일</td>
		<td colspan="2" style="text-align:center;font-weight:bold;">조회</td>

	</tr>
	<c:forEach var="data" items="${nblist}" varStatus="status">
		<tr>
			<td>${data.no}</td>
			<td colspan="2" style="text-align:center;"><a href="viewNbForm.do?no=${data.no}">${data.title}</td>
			<td colspan="2" style="text-align:center;">관리자</td>
			<td colspan="2" style="text-align:center;"><fmt:formatDate value="${data.nwdate}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
			<td colspan="2" style="text-align:center;">${data.rcount}</td>

		</tr>
	</c:forEach>
</table>
	</c:otherwise>
</c:choose>

<table width="80%"> 
	<tr>
	<!-- 0. 나중에 쓸 마지막 페이지 설정(예, endPage:5.5일 경우, pageCount로(5로 설정 된[최소]) 할거다!(에러 방지!)
	-->	<td style="text-align:center;">
			<c:if test="${endPage>pageCount}">
				<c:set var="endPage" value="${pageCount}" />
			</c:if>
<!--			1. 페이지 블록 세우기(<)-(현재 페이지가 어디에 있든 10개씩의 페이지를 보여줄거라
			시작페이지*현재 있는 페이지 - 에서 10을 빼준다. 예) 현재 13이면 시작은 3부터) - 그 시작 페이지로 가라!	
-->			<c:if test="${startPage>10}">
				<a href="list.do?str=${str}&pageNum=${pageNum}">◀</a>
			</c:if>
<!-- 	2. 페이지 개수나타내기[1],[2],[3],[4] 처럼 - pageNum, startpage, endpage
-->		<c:forEach var="i" begin="${startPage}" end="${endPage}">
			<c:choose>
					<c:when test="${i eq pageNum}">
						<a href="list.do?str=${str}&pageNum=${i}" style="font-weight:bold;">[${i}]</a>
					</c:when>
					<c:otherwise>
					<a href="list.do?str=${str}&pageNum=${i}">[${i}]</a> <!-- 페이지 번호를 나열하고 페ㅇ지 블럭을 따로 보냄(가져가신 듯) -->
					</c:otherwise>
				</c:choose>
		</c:forEach>
<!--	3. 페이지 블록 세우기(>) - pageNum, endrow
-->			<c:if test="${endPage<pageCount}">
				<a href="list.do?str=${str}&pageNum=${startPage+10}">▶</a>
			</c:if>
		</td>
	</tr>

</table>
</body>
</html>