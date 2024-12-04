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
    <link rel="stylesheet" type="text/css" href="./css/m_qaboard.css?v=3">
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
        <p>1:1 문의게시판(내용확인)</p>
        <span class="sub_titles">빠르게 궁금한 사항을 답변 드리도록 하겠습니다.</span>
        <ul class="write_ul">
            <li class="cate_txt">질문항목 :${ userQaData.getQcategory() }</li>
            <li><input type="text" class="w_input1 w_bg" value="${ userQaData.getQname() }" readonly></li>
            <li><input type="text" class="w_input1 w_bg" value="${ userQaData.getQphone() }" readonly></li>
            <li><input type="text" class="w_input1 w_bg" value="${ userQaData.getQemail() }" readonly></li>
            <li><input type="text" class="w_input1" value="${ userQaData.getQtitle() }" readonly></li>
            <li><textarea class="w_input2" readonly>${ userQaData.getQtext() }</textarea></li>
            <cr:set var="qfile" value="${ fn:split(userQaData.getQfile(), '|') }"/>
            <li class="fileview">첨부파일 :
            <a href="/qafile/${ qfile[1] }">${ qfile[0] }</a></li>
            <li class="fileview">첨부파일 :
            <a href="/qafile/${ qfile[3] }">${ qfile[2] }</a></li>
            <cr:if test="${ userQaData.getAnswer() != null }">
            <li class="admin_view">관리자 답변내용</li>
            <li><textarea class="w_input2" readonly>${ userQaData.getAnswer() }</textarea></li>
            </cr:if>
        </ul>
        <cr:if test="${ userQaData.getAnswer() == null }">
        <div class="member_agreebtn" onclick="goQaModify(${ userQaData.getQidx() })">수정하기</div>
        </cr:if>
        <div class="member_agreebtn" onclick="go_qalist()">문의 리스트</div>
    </div>  
</section>
<!-- 하단 시작 -->
<%@ include file="./footer.jsp" %>
</body>
<script>
function goQaModify(qidx) {

    var form = document.createElement("form");
    form.setAttribute("method", "post");
    form.setAttribute("action", "qa_modify.do");

    var inputField = document.createElement("input");
    inputField.setAttribute("type", "hidden");
    inputField.setAttribute("name", "qidx");
    inputField.setAttribute("value", qidx);

    form.appendChild(inputField);
    document.body.appendChild(form);

    form.submit();
    
}

function go_qalist(){
	
	location.href = "./qa_list.do";
	
}
</script>
<script src="./js/top.js?v=6"></script>
</html>