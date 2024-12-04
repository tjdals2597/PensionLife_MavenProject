function join_location() {
	if(frm2.ok1.checked==false){
		alert("이용약관의 동의는 필수입니다.");
	}
	else if(frm2.ok2.checked==false){
		alert("개인정보수집 동의는 필수입니다.");	
	}
	else if(frm2.ok3.checked==false){
		alert("개인정보 제3자 제공 동의는 필수입니다.");	
	}
	else if(frm2.ok4.checked==false){
		alert("개인정보 위탁제공 동의는 필수입니다.");	
	}
	else{	
		frm2.method= "POST";
		frm2.action= "./m_join.do";
		frm2.submit();
	}
}

function ok_all(){
	let w=1;
	let ok6=document.getElementById("ok6");
	while(w<6){

		document.getElementById("ok"+w).checked=ok6.checked;
		w++;
	}
}

function ok_one(z){
	let count=0;
	const ok6=document.getElementById("ok6");
	let w=1;
	while(w<6){
		if(count!=5){
			ok6.checked=false;
		}
		if(document.getElementById("ok"+w).checked==true){
			count++;
		}
		if(count==5){
			ok6.checked=true;
		}
		w++;
	}	
}