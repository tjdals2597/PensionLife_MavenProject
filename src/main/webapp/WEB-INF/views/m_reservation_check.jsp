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
    <link rel="stylesheet" type="text/css" href="./css/m_reservation.css?v=2">
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
<form id="frm_del_reser">
	<input type="hidden" name="reser_idx" id="reser_idx" value="${ reserdata.getReser_idx() }">
</form>
<section class="subpage">
    <div class="member_agree">
    <p>펜션 예약 확인</p>
    <ol class="reser_ol">
    <li>
    <span class="reser_part1">펜션명</span>
    <span class="reser_part2">
    	${ reserdata.getPname() }
    </span>
    </li>
    <li>
    <span class="reser_part1">객실명</span>
    <span class="reser_part2">
		${ reserdata.getRname() }
    </span>
    </li>
    <li>
    <span class="reser_part1">예약 일자</span>
    <span class="reser_part2">
    	${ reserdata.getReser_date() }
    </span>
    </li>
    <li>
    <span class="reser_part1">객실구조</span>
    <span class="reser_part2">
    	${ reserdata.getRcomp() }
    </span>
    </li>
    <li>
    <span class="reser_part1">입실인원</span>
    <span class="reser_part2">
    	기준 ${ reserdata.getRgnum() }인 / 최대 ${ reserdata.getRmnum() }인
    </span>
    </li>
    <li>
    <span class="reser_part1">추가인원</span>
    <span class="reser_part2">
    기준인원 초과시 추가요금이 발생합니다.
    </span>
    </li>
    <li>
    <span class="reser_part1">구매금액</span>
    <span class="reser_part2">
    	${ reserdata.getReser_price() }원
    </span>
    </li>
    </ol>
    <p>예약자 정보</p>
    <ol class="reser_ol">
    <li>
    <span class="reser_part1">예약자명</span>
    <span class="reser_part2">
    	${ reserdata.getReser_gname() }
    </span>
    </li>
    <li>
    <span class="reser_part1">휴대폰</span>
    <span class="reser_part2">
    	${ reserdata.getReser_gtel() }
    </span>
    </li>
    <li>
    <span class="reser_part1">입실인원</span>
    <span class="reser_part2">
		${ reserdata.getReser_gnum() }인
    </span>
    </li>
    <li>
    <span class="reser_part1">이메일</span>
    <span class="reser_part2">
    	${ reserdata.getReser_email() }
    </span>
    </li>
    </ol>
    <div class="member_agreebtn" onclick="js_reservation_cancel()">예약취소</div>
    </div>
</section>
<!-- 하단 시작 -->
<%@ include file="./footer.jsp" %>
</body>
<script src="./js/top.js?v=6"></script>
<script src="./js/m_index.js?v=1"></script>
</html>