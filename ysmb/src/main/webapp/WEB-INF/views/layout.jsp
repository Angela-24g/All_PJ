<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta http-equiv="imagetoolbar" content="no">
<meta http-equiv="X-UA-Compatible" content="IE=10,chrome=1">
<meta property='og:locale' content='ko_KR' />
<meta property='og:type' content='website' />

<link rel="stylesheet" type="text/css" href="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
<link rel="stylesheet" type="text/css" href="resources/css/common.css">

<!-- <script type="text/javascript" src="//code.jquery.com/jquery-3.6.0.min.js"></script> -->
<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="//cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js" integrity="sha512-qTXRIMyZIFb8iQcfjXWCO8+M5Tbc38Qi5WzdPOYZHIlZpzBHG3L3by84BBBOiRGiEb7KKtAOAs5qYdUiZiQNNQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<!-- 날짜변환용이다.(src 이거!) -->
<script type="text/javascript" src="resources/js/slick.js"></script>
<script type="text/javascript" src="resources/js/pages.js"></script>

<link rel="shortcut icon" type="image/x-icon" href="resources/imgs/favicon.ico?1"/>
<title>my community home</title>
</head>
<body>
<header class="header">
    <tiles:insertAttribute name="header"></tiles:insertAttribute>
</header>
<div class="wrapper clear">
    <aside>
        <tiles:insertAttribute name="side"></tiles:insertAttribute>
    </aside>
    <section>
        <tiles:insertAttribute name="content"></tiles:insertAttribute>
    </section>
</div>
<footer class="footer">
    <tiles:insertAttribute name="footer"></tiles:insertAttribute>
</footer>
</body>
</html>