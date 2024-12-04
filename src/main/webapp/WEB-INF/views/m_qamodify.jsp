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
    <link rel="stylesheet" type="text/css" href="./css/m_qaboard.css?v=4">
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
        <p>1:1 문의게시판(문의수정)</p>
        <span class="sub_titles">빠르게 궁금한 사항을 답변 드리도록 하겠습니다.</span>
        <form id="frm" enctype="multipart/form-data">
		<input type="hidden" name="qidx" value="${ userQaData.getQidx() }">
		<input type="hidden" name="qfile" value="${ userQaData.getQfile() }">
		<input type="hidden" name="exfile1_del_ck" id="exfile1_del_ck" value="false">
		<input type="hidden" name="exfile2_del_ck" id="exfile2_del_ck" value="false">
        <ul class="write_ul">
            <li class="cate_txt">질문항목 :${ userQaData.getQcategory() }</li>
            <li><input type="text" class="w_input1 w_bg" name="qname" value="${ userQaData.getQname() }" readonly ></li>
            <li><input type="text" class="w_input1 w_bg" name="qmhp" value="${ userQaData.getQphone() }" readonly></li>
            <li><input type="text" class="w_input1 w_bg" name="qemail" value="${ userQaData.getQemail() }" readonly></li>
            <li><input type="text" class="w_input1" name="qtitle" value="${ userQaData.getQtitle() }"></li>
            <li><textarea class="w_input2" name="qtext">${ userQaData.getQtext() }</textarea></li>
            <cr:set var="qfile" value="${ fn:split(userQaData.getQfile(), '|') }"/>
            <cr:if test="${ qfile[1] != null }">
            <li class="fileview" id="file_li1">첨부파일 :${ qfile[0] }
            <button type="button" class="btn_del" onclick="file_delete('1')">삭제</button>
            </li>
            </cr:if>
            <cr:if test="${ qfile[3] != null }">
            <li class="fileview" id="file_li2">첨부파일 : ${ qfile[2] }
            <button type="button" class="btn_del" onclick="file_delete('2')">삭제</button>
            </li>
            </cr:if>
            <cr:if test="${ qfile[1] == null }">
            <li class="fileview"><input type="file" name="qfiles" class="w_li"> * 최대 2MB까지 가능</li>
            </cr:if>
            <cr:if test="${ qfile[3] == null }">
            <li class="fileview"><input type="file" name="qfiles" class="w_li"> * 최대 2MB까지 가능</li>
            <li>※ 첨부파일은 이미지만 등록 가능합니다.</li>
            </cr:if>
        </ul>
        </form>
        <div class="member_agreebtn" onclick="go_qamodify_db()">문의수정</div>
        <div class="member_agreebtn" onclick="go_qadelete_db()" style="background-color: darkblue; color:white;">문의삭제</div>
    </div>  
</section>
<!-- 하단 시작 -->
<%@ include file="./footer.jsp" %>
</body>
<script>
function go_qamodify_db(){
	if(frm.qtitle.value==""){
		alert("'질문 제목'을 입력해주세요.")
	}
	else if(frm.qtext.value==""){
		alert("'질문 내용'을 입력해주세요.");
	}
	else{
	frm.method = "post";
	frm.action = "./qa_update.do";
    frm.submit();
	}
}

function go_qadelete_db(){
	if (confirm("정말로 문의를 삭제하시겠습니까?")) {
		frm.method = "post";
		frm.action = "qa_delete.do";
	    frm.submit();
    }
}
let liFile1 = document.getElementById("file_li1");
let liFile2 = document.getElementById("file_li2");

let exfile1_del_ck = document.getElementById("exfile1_del_ck");
let exfile2_del_ck = document.getElementById("exfile2_del_ck");

function file_delete(index) {
	if (index == "1") {
		exfile1_del_ck.value = "true";
		liFile1.innerHTML = `<li class="fileview"><input type="file" name="qfiles" class="w_li"> * 최대 2MB까지 가능</li>`;
	} else if (index == "2") {
		exfile2_del_ck.value = "true";
		liFile2.innerHTML = `<li class="fileview"><input type="file" name="qfiles" class="w_li"> * 최대 2MB까지 가능</li>`;
	} else {
		alert("잘못된 접근입니다.");
	}
}
</script>
<script src="./js/top.js?v=6"></script>
</html>