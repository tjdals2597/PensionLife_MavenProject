<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>호텔 & 펜션 예약시스템</title>
    <link rel="stylesheet" type="text/css" href="./css/m_index.css?v=1">
    <link rel="stylesheet" type="text/css" href="./css/m_sub.css?v=2">
    <link rel="stylesheet" type="text/css" href="./css/m_reservation.css?v=3">
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
<section class="subpage">
    <div class="member_agree">
    	<p>펜션 예약 목록</p>
    	<span class="sub_titles">예약하신 리스트가 출력 됩니다.</span>
        <ul class="reser_lists">
            <li>펜션명</li>
            <li>객실명</li>
            <li>예약일자</li>
            <li>인원수</li>
        </ul>
        <cr:if test="${ reserlist.size() == 0 }"><ul class="reser_lists2"><li colspan="5" style="text-align: center;">예약내역이 없습니다.</li></ul></cr:if>
        <cr:forEach var="reserdata" items="${ reserlist }">
	        <ul class="reser_lists2" onclick="viewReserDetail(${ reserdata.getReser_idx() })">
	            <li>${ reserdata.getPname() }</li>
	            <li>${ reserdata.getRname() }</li>
	            <li>${ fn:split(reserdata.getReser_date(), " ")[0] }</li>
	            <li>${ reserdata.getReser_gnum() }명</li>
	        </ul>
        </cr:forEach>
    </div>
</section>
<!-- 하단 시작 -->
<%@ include file="./footer.jsp" %>
</body>
<script src="./js/top.js?v=6"></script>
<script src="./js/m_index.js?v=2"></script>
</html>