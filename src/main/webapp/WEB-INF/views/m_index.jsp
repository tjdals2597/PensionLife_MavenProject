<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>호텔 & 펜션 예약시스템</title>
    <link rel="stylesheet" type="text/css" href="./css/m_index.css?v=1">
    <script src="./js/jquery.js"></script>
</head>
<body>
<!-- 상단 시작 -->
<%@ include file="./top.jsp" %>
<!-- 상단 끝 -->
<main>
<!-- 배너 -->
<%@ include file="./banner.jsp" %>
<!-- 배너 끝-->
<!-- 중단 -->
<section>
    <ol class="product">
	    <cr:forEach var="pdata" items="${ pensionlist }">
	        <li>
	            <div onclick="js_goreser('${pdata.getPname()}')">
	                <span><img src="${ pdata.getRimg() }"></span>
	                <span>${ pdata.getPname() }</span>
	                <span>${ pdata.getRprice() }원</span>
	            </div>
	        </li>
	    </cr:forEach>
    </ol>
</section>
<form id="frm_reserv">
	<input type="hidden" name="resername" id="resername" value="">
</form>
<!-- 하단 시작 -->
<%@ include file="./footer.jsp" %>
</body>
<script src="./js/m_index.js?v=1"></script>
<script src="./js/top.js?v=6"></script>
</html>