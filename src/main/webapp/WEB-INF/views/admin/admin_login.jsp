<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>호텔 & 펜션 예약시스템</title>
    <link rel="stylesheet" type="text/css" href="../css/m_index.css?v=1">
    <link rel="stylesheet" type="text/css" href="../admin_css/index.css?v=4">
    <script src="../js/jquery.js"></script>
</head>
<body>
<header class="admin_header"><img src="../img/header_logo.png"></header>
<aside class="admin_login">
    <p><img src="../admin_img/logo.png"><span>ADMINISTRATOR</span></p>
    <form id="frm_admin_login">
    <ol class="admin_part">
        <li><input type="text" class="adin1" placeholder="아이디를 입력하세요" name="aid" id="admin_id" value="admin1"></li>
        <li><input type="password" class="adin1" placeholder="패스워드를 입력하세요" name="apass" id="admin_pw" value="pensionmaster1234"></li>
        <li><input type="button" class="adbtn1" id="admin_login_btn" value="LOGIN"></li>
    </ol>
    </form>
</aside>
<%@ include file="./admin_footer.jsp" %>
</body>
<script src="../js/admin_page.js?v=2"></script>
</html>