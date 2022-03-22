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
<style>
	h2{text-align:center;}
	.eventWrap {
    width: 1280px;
    margin: 0 auto;
    padding: 0 80px 160px;
	}
html, body, div, span, applet, object, iframe, h1, h2, h3, h4, h5, h6, p, blockquote, pre, a, abbr, acronym, address, big, cite, code, del, dfn, em, font, img, ins, kbd, q, s, samp, small, strike, strong, sub, sup, tt, var, b, u, i, center, dl, dt, dd, ol, ul, li, fieldset, form, label, legend, table, caption, article, aside, hgroup, header, footer, figure, figcaption, nav, section {
    margin: 0;
    padding: 0;
    border: 0;
    font-size: 100%;
    vertical-align: baseline;
    background: transparent;
    line-height: 1.4em;
    box-sizing: border-box;
    font-weight: 200;
}
user agent stylesheet
div {
    display: block;
   
}
html[lang="ko"] body, html[lang="en"] body {
    word-break: keep-all;
}
html body {
    word-break: break-all;
}
body {
    padding: 0;
    margin: 0;
    box-sizing: border-box;
    font-family: 'Noto Sans', sans-serif;
    font-size: 14px;
    color: #666;
    -webkit-font-smoothing: antialiased;
}
ul.eventTab li.on a:before, ul.eventTab li.on a:after {
    display: none;
}
.tapBox li.on a:before {
    content: '';
    display: block;
    position: absolute;
    top: 0;
    left: 0;
    bottom: -1px;
    width: 1px;
    background: #9c836a;
}
.tapBox li.on a:before {
    content: '';
    display: block;
    position: absolute;
    top: 0;
    left: 0;
    bottom: -1px;
    width: 1px;
    background: #9c836a;
}
ul.eventTab li a:before, ul.eventTab li a:after {
    display: none;
}
ul.eventTab li.on a:before, ul.eventTab li.on a:after {
    display: none;
}
.tapBox li.on a:after {
    content: '';
    display: block;
    position: absolute;
    top: 0;
    right: 0;
    bottom: -1px;
    width: 1px;
    background: #9c836a;
}
.tapBox li.on a:after {
    content: '';
    display: block;
    position: absolute;
    top: 0;
    right: 0;
    bottom: -1px;
    width: 1px;
    background: #9c836a;
}
ul.eventTab li a:before, ul.eventTab li a:after {
    opacity: 1.0;
}
.tapBox li a:after {
    content: '';
    display: block;
    position: absolute;
    top: 0;
    right: 0;
    bottom: 0;
    width: 1px;
    background: #e6e3df;
}
.tapBox li a:after {
    content: '';
    display: block;
    position: absolute;
    top: 0;
    right: 0;
    bottom: 0;
    width: 1px;
    background: #e6e3df;
}


.tapBox li a {
    display: block;
    position: relative;
    height: 48px;
    padding: 12px 0;
    border-top: 1px solid #e6e3df;
    border-bottom: 1px solid #9c836a;
    line-height: 22px;
    text-align: center;
    color: #666;
    vertical-align: top;
}
a:link, a:visited, a:hover, a, active, a:focus {
    text-decoration: none;
    cursor: pointer;

}
/*img*/
ul li .img:not(.noScale) {
    overflow: hidden;
    position: relative;
}
/*ul.eventList li .img {
    width: 352px;
    height: 232px;
    background: url(/resource/images/pc/common/empty_logo.png) #f9f9f9 no-repeat 50% 50%;
}*/
p {
    display: block;
   
}
.event {
	display:flex;
	align-items:right;
	margin: 50px;
}
.event .imgs{
	display: flex;
	margin: 0;

}
.event .txt .info{

    position: relative;
    padding: 40px 0;
    border-top: 1px solid #e6e3df;

}
.event .txt .title{
	font-size: 25px;
}
.event .txt .sub{
	font-size: 20px;
}
.event .txt .date {	
	font-size: 20px;
}

/*event box constructure*/

.contTitle + .eventBox {
    margin-top: -14px;
}
.eventBox {
    position: relative;
    margin-top: 55px;
}

/* a tag*/
ul.eventTab li a {
    height: 40px;
    font-size: 16px;
    font-weight: 300;
    color: #999;
    line-height: 40px;
    border: 0;
}

	ul.eventList{margin: 0 -16px 0; font-size: 0;}
	ul.eventList li{ margin: 45px 16px 0; vertical-align: top; font-size: 14px; float: left; width: 25%; padding:3px;}
	ul.eventList li:nth-child(1),	ul.eventList li:nth-child(2),	ul.eventList li:nth-child(3){margin-top: 0;}
	ul.eventList li a{display: block;}
	ul.eventList li .img { width:352px; height:232px; background:url(/resource/images/pc/common/empty_logo.png) #f9f9f9 no-repeat 50% 50%;;}
	ul.eventList li .img img { display:block; width:100%; height:100%;}
	ul.eventList li div.txt{padding: 20px 0 30px;}
	ul.eventList li strong{display: block; color: #333; font-size: 14px; font-weight: 450; text-transform: uppercase; overflow:hidden; text-overflow:ellipsis; white-space:nowrap;}
	ul.eventList li span.sub{ overflow:hidden; display: block; height:40px; margin: 12px 0 16px; color: #666; font-size: 14px; word-break: keep-all; line-height: 20px;}
	ul.eventList li span.date{display: block; font-size: 12px; line-height: 1; margin-top: 2px; color: #999;}
	ul.eventList li span.date .time {display:inline-block;position: relative;padding-left:12px;margin-left:9px;}
	ul.eventList li span.date .time:before {content:'';position: absolute;top:50%;left:0;width:1px;height:9px;margin-top:-4px;background:#c2c2c2;}
	
	.paginate{margin-top: 50px; text-align: center; display:block; width:100%; clear:both;}
	.paginate a{display: inline-block; width: 24px; height: 24px; padding: 0 3px; font-size: 14px; color: #999; font-weight: 300; line-height: 23px; vertical-align: top; transition: 0.2s; box-sizing: unset;}
	.paginate a.direction{background: url(/images/event/btn_paging.png) center no-repeat; text-indent: -9999px;}
	.paginate a.direction{background-position: 0 0;}
	.paginate a.direction:nth-of-type(2){background-position: 0 -24px; margin-right: 10px;}
	.paginate a.next{background-position: 0 -48px; margin-left: 10px;}
	.paginate a.direction:last-of-type{background-position: 0 -72px; margin-left: 0;}
	
.paginate a.active, .paginate a:hover {
    text-decoration: underline;
    color: black ; /* #9c836a */
}
</style>
<body>
<div class="eventWrap">
	<div class="eventBox">
	<div>	
		<c:choose>
			<c:when test="${str eq 'gall'}">
				<button onclick="location.href='flgift.do'">글쓰기</button>
			</c:when>
			<c:otherwise>
				<button onclick="location.href='udpnb.do'">글쓰기</button>
			</c:otherwise>
		</c:choose>
	</div>
		<c:forEach var="flst" items="${fllist}">
		
	  <c:forEach var="imglist" items="${flst.imgs}">
	  		<c:choose>
	  		
	  		<c:when test="${imglist.thumb ne null}">
		 <ul class="eventList clearFixed">
			<li data-animation="fadeInUp"><a href="viewgallery.do?no=${imglist.bno}">
					<p class="img">
					
					<img src="/ysmb/<c:out value="${imglist.url}"/>"/></p>
					<div class="txt">
						
						<strong>${flst.title}</strong>
						<span class="sub">${flst.nwriter}</span>
						<span class="sub">${flst.ncontent}</span>					
						<span class="date"><fmt:formatDate value="${flst.nwdate}" pattern="yyyy-MM-dd HH:mm:ss"/></span>		
					</div>
					</a>	
				</li>
			</ul>
			</c:when>
			</c:choose>
			
			</c:forEach> 
		</c:forEach>
	</div>
</div>

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
	



<%-- <c:forEach var="flst" items="${fllist}">
	<c:out value="${flst.no}"/>
	<c:forEach var="imglist" items="${flst.imgs}">
		<img src="/ysmb/<c:out value="${imglist.url}"/>"/>
	</c:forEach>
</c:forEach> --%>
</body>
</html>