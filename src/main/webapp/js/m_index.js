// 펜션 클릭으로 예약 페이지 이동
function js_goreser(name) {
	document.getElementById("resername").value = name;
	frm_reserv.method = "POST";
	frm_reserv.action = "./reserpage_go.do";
	frm_reserv.submit();
}

// 예약된 펜션 클릭으로 상세정보 페이지 이동
function viewReserDetail(ridx) {
	location.href = "./reservation_check.do?number=" + ridx;
}

// 펜션 예약 취소
function js_reservation_cancel() {
	if(confirm("정말로 예약을 취소하시겠습니까?")) {
		frm_del_reser.method = "POST";
		frm_del_reser.action = "reser_delete.do";
		frm_del_reser.submit();
	}
}