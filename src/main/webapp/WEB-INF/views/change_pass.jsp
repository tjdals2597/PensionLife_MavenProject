<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>호텔 & 펜션 예약시스템</title>
    <link rel="stylesheet" type="text/css" href="./css/m_index.css?v=1">
    <link rel="stylesheet" type="text/css" href="./css/m_sub.css?v=2">
    <link rel="stylesheet" type="text/css" href="./css/m_qaboard.css?v=2">
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
        <p>비밀번호 변경</p>    
        <span class="sub_titles">패스워드 변경 후 로그인 시 해당 비밀번호가 적용 됩니다.</span>  
<form id="change_password">
        <input type="hidden" name="midx" value="${ search_midx }">
        <ul class="write_ul">
            <li><input type="password" class="w_input1" placeholder="변경할 비밀번호을 입력하세요" id="change_pass" name="mpass"></li>
            <li><input type="password" class="w_input1" placeholder="변경할 비밀번호를 다시 입력해주세요" id="change_pass_ck"></li>
        </ul>
</form>
        <div class="member_agreebtn" onclick="change_pass()">패스워드 변경</div>
    </div>
</section>
<!-- 하단 시작 -->
<%@ include file="./footer.jsp" %>
</body>
<script src="./js/change_pass.js?v=2"></script>
<script src="./js/top.js?v=6"></script>
</html>