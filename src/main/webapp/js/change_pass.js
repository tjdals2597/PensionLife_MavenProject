function change_pass(){
	var chpw = document.getElementById("change_pass")
	var chpwck = document.getElementById("change_pass_ck")
	
	if(chpw.value == ""){
		alert("변경할 비밀번호를 입력해주세요.");
	}
	else if(chpwck.value == ""){
		alert("변경할 비밀번호를 다시 입력해주세요.");
	}
	else if(chpw.value != chpwck.value){
		alert("변경할 비밀번호가 일치하지 않습니다.");	
	}
	else {
		change_password.method="POST";
		change_password.action="./change_passok.do";
		change_password.submit();
	}
}