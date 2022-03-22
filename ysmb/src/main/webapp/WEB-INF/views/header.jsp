<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="inner_wrap clear">
        <ul class="gnb_top_ul clear">
            <li class="gnb_top_li">
                <a href="joinForm.do" class="gnb_top_a">join</a>
            </li> 
            <li class="gnb_top_li">
            <c:choose>
				  <c:when test="${id==null}">
				   <a href="loginForm.do">login</a>
				  </c:when>
				  <c:otherwise>
				  	<% String name = (String)session.getAttribute("name");%>${name} 님 &nbsp;
				    <a href="logOut.do">logout</a>
		   </c:otherwise>
				</c:choose>
				</li>
            <li class="gnb_top_li">
                  <c:choose>
				  <c:when test="${id==null}">
				  
				  </c:when>
				  <c:otherwise> 
				  <a href="mypage.do" class="gnb_top_a">mypage</a>
		  		  </c:otherwise>
				</c:choose>    
            </li>
        </ul>
        <nav class="gnb clear">
            <h1 class="logo">
                <a href="list.do" class="logo_link">
                    <img src="resources/imgs/yslogo.png" alt="logo">
                </a>
            </h1>
            <ul class="gnb_ul clear">
                <li class="gnb_li">   
                    <a href="list.do?str=nb&pageNum=1" class="gnb_a" id="href">   
                     공지사항
                    </a>
                </li>
                <li class="gnb_li">
                	<a href="list.do?str=gall&pageNum=1" class="gnb_a" id="gall"> 
                       갤러리
                    </a>
                </li>
               
                 <li class="gnb_li">
                    <a href="list.do?str=comm&pageNum=1" class="gnb_a" id="comm"> 
                    Community   
                    </a>
                </li>
                <li class="gnb_li">
                    <a href="faq.do" class="gnb_a">
                        FAQ
                    </a>
                </li>
            </ul>
        </nav>
    </div>