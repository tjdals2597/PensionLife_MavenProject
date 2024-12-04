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
        <p>1:1 문의게시판</p>
        <span class="sub_titles">질문하신 리스트가 출력 됩니다.</span>
        <ul class="qa_lists">
            <li>질문제목</li>
            <li>글쓴이</li>
            <li>등록일</li>
            <li>처리</li>
        </ul>
        <cr:if test="${ qalist.size() == 0 }"><ul class="qa_lists2"><li colspan="5" style="text-align: center;">문의내역이 없습니다.</li></ul></cr:if>
        <cr:forEach var="qadata" items="${ qalist }">
	        <ul class="qa_lists2" onclick="viewQaDetail(${ qadata.getQidx() })">
	            <li>
	            <div class="qtitle-link">
	            <cr:if test="${ qadata.getQtitle().length() > 15 }">${ qadata.getQtitle().substring(0, 15) }...</cr:if>
	            <cr:if test="${ qadata.getQtitle().length() <= 15 }">${ qadata.getQtitle() }</cr:if>
	            </div>
	            </li> 
	            <li>${ qadata.getQname() }</li>
	            <li>${ qadata.getQindate().substring(0, 10) }</li>
	            <cr:if test="${ qadata.getAnswer() == null }">
	            <li>미답변</li>
	            </cr:if>
	            <cr:if test="${ qadata.getAnswer() != null }">
	            <li style="color: red;">답변완료</li>
	            </cr:if>
	        </ul>
        </cr:forEach>
        <div class="member_agreebtn" onclick="go_qawrite()">문의하기</div>
    </div>  
</section>
<!-- 하단 시작 -->
<%@ include file="./footer.jsp" %>
</body>
<script>
function go_qawrite(){
	window.location.href = "./qa_write.do";
}

function viewQaDetail(qidx) {
    var form = document.createElement("form");
    form.setAttribute("method", "post");
    form.setAttribute("action", "qa_view.do");

    var inputField = document.createElement("input");
    inputField.setAttribute("type", "hidden");
    inputField.setAttribute("name", "qidx");
    inputField.setAttribute("value", qidx);

    form.appendChild(inputField);
    document.body.appendChild(form);

    form.submit();
}

</script>
<script src="./js/top.js?v=6"></script>
</html>