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
    <link rel="stylesheet" type="text/css" href="./admin_css/index.css?v=6">
    <script src="./js/jquery.js"></script>
</head>
<body>
<%@ include file="admin_top.jsp" %>
<form id="frm_adm_qawr">
<input type="hidden"  name="awriter" value="${ userdata.get(0) }">
<input type="hidden"  name="qidx" value="${ qadata.getQidx() }">
<article class="admin_lists">
    <p>QA 문의 내용</p>
    <ul class="qa_write">
        <li>고객명</li>
        <li>${ qadata.getQname() }</li>
        <li>제목</li>
        <li>${ qadata.getQtitle() }</li>
        <li>내용</li>
        <li class="view1">${ qadata.getQtext() }</li>
        <li>등록일</li>
        <li>${ qadata.getQindate().split("")[0] }</li>
        <li>답변</li>
        <li>     
        <textarea placeholder="답변내용을 입력하세요" class="answer" name="answer" id="ans_content">${ qadata.getAnswer() }</textarea>
        </li>
    </ul>
	<cr:if test="${ qadata.getAnswer() == null }">
    <input type="button" class="adbtn1" value="답변등록" onclick="js_admin_answer()">
	</cr:if>
	<cr:if test="${ qadata.getAnswer() != null }">
	<input type="button" class="adbtn1" value="이전 화면" onclick="history.go(-1);">
	</cr:if>
</article>
</form>
<%@ include file="./admin_footer.jsp" %>
</body>
<script src="./js/admin_page.js?v=1"></script>
</html>