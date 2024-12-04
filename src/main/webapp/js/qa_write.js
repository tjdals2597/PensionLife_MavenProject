$(function(){
	//1:1문의 select 부분
	$(".qa_part > li").click(function(){
		$n = $(this).index();
		$(".qa_part > li").attr("class","");
		$(".qa_part > li").eq($n).attr("class","onselect");
	});
});

function sendQcategory(qc) {
    // Hidden input 요소에 선택된 값 설정
    document.getElementById('qcategory').value = qc;
    //console.log(qc);
    //console.log(frm.qname.value);
}

function go_qawrite_insert(){
	
	if(document.getElementById('qcategory').value==""){
		alert("'문의 유형 선택'을 하셔야 문의등록이 가능합니다.");		
	}
	else if(frm.qtitle.value==""){
		alert("'질문 제목'을 입력해주세요.");
	}
	else if(frm.qtext.value==""){
		alert("'질문 내용'을 입력해주세요.");
	}	
	else{
		frm.method="post";
		//frm.action="./m_qalist.jsp";
		frm.action="./qawrite_insert.do";		
		//document.getElementById('frm').submit();
		frm.submit();
	}

}