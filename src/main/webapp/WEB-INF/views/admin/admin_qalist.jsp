<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>호텔 & 펜션 예약시스템</title>
    <link rel="stylesheet" type="text/css" href="./css/m_index.css?v=2">
    <link rel="stylesheet" type="text/css" href="./admin_css/index.css?v=3">
    <script src="./js/jquery.js"></script>
</head>
<body>
<%@ include file="admin_top.jsp" %>
<article class="admin_lists">
    <p>QA 문의 게시판 미답변 리스트</p>
    <ul class="lists_uls color1">
        <li>제목</li>
        <li>글쓴이</li>
        <li>등록일</li>
    </ul>
    <!--qa 데이터 출력 리스트 부분 -->
	<cr:if test="${ qalist.size() == 0 }"><ul class="qa_list"><li colspan="5" style="text-align: center;">문의내역이 없습니다.</li></ul></cr:if>
    <cr:forEach var="qadata" items="${ qalist }">
	    <ul class="lists_uls" onclick="js_select_qa(${ qadata.getQidx() })">
	        <li style="text-align: left;">
	        <cr:if test="${ qadata.getQtitle().length() > 15 }">${ qadata.getQtitle().substring(0, 15) }...</cr:if>
	        <cr:if test="${ qadata.getQtitle().length() <= 15 }">${ qadata.getQtitle() }</cr:if>
			</li>
	        <li>${ qadata.getQname() }</li>
	        <li>${ qadata.getQindate().split(' ')[0] }</li>
	    </ul>
    </cr:forEach>
    <!--qa 데이터 출력 리스트 부분 -->
</article>
<form id="frm_qa_view">
	<input type="hidden" name="qa_idx_value" id="qa_idx_value" value="">
</form>
<input type="button" class="adbtn1" value="답변 등록된 문의" onclick="go_qalist('1')">
<br><br>
<%@ include file="./admin_footer.jsp" %>
</body>
<script src="./js/admin_page.js?v=4"></script>
</html>