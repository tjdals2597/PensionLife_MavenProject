<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="menus_bar" id="menus_bar">
    <div class="load_menus" id="load_menus">
        <ul>
            <li><span id="reserbutton">팬션 예약확인</span></li>
            <li>팬션 예약취소</li>
            <li><span id="qabutton">1:1문의 게시판</span></li>
        </ul>
    </div>
</div>
<header>
<ul class="top_menu">
    <li id="menu_slide"><img src="./img/menu.svg"></li>
    <li id="header_logo"><img src="./img/header_logo.png"></li>
    <cr:if test="${ userdata == null }">
    	<li onclick="login_pop()"><img src="./img/login.svg"></li>
    </cr:if>
    <cr:if test="${ userdata != null }">
    	<li>${ userdata.get(3) }님</li>
    	<li onclick="logout()"><img src="./img/top_exit.png"></li>
    </cr:if>
</ul>
</header>
<form id="frm_top_login">
<aside class="popup" id="popup" style="display:none;">
	<div class="login">
		<span class="close" onclick="pop_close()">X</span>
		<p>MEMBER-LOGIN</p>
		<ol>
		<li><input type="text" class="login_input" placeholder="아이디를 입력하세요" id="login_mid" name="mid"></li>
		<li><input type="password" class="login_input" placeholder="패스워드를 입력하세요" id="login_mpass" name="mpass"></li>
		<li><label><input type="checkbox" class="login_check" value="Y" name="auto_login"> 자동로그인</label></li>
		<li><input type="button" value="로그인" class="login_btn" onclick="js_login()"></li>
		<li class="login_info">
		<span id="idbutton">아이디 찾기</span>
		<span id="signbutton">회원가입</span>
		</li>
		</ol>
	</div>
</aside>
</form>