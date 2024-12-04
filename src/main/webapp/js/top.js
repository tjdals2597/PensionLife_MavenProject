$(function(){
	//햄버거 버튼 부분
	$("#menu_slide").click(function() {
		$("#menus_bar").fadeToggle();
	});
	
	$("#header_logo").click(function() {
		location.href = "/main";
	});
	
	$("#idbutton").click(function() {
		location.href = "./m_idsearch.do";
	});
	
	$("#signbutton").click(function() {
		location.href = "./join.do";
	});
	
	$("#reserbutton").click(function() {
		location.href = "./reservation_list.do";
	});
	
	$("#qabutton").click(function() {
		location.href = "./qa_list.do";
	});
});

function js_login(){
	if (frm_top_login.login_mid.value == ""){
		alert("아이디를 입력해주세요.");
		frm_top_login.login_mid.focus();
	}
	else if (frm_top_login.login_mpass.value == ""){
		alert("비밀번호를 입력해주세요.");
		frm_top_login.login_mpass.focus();
	}
	else {
		frm_top_login.method = "POST";
		frm_top_login.action = "./login.do";
		frm_top_login.submit();
	}
}


//로그인 팝업
function login_pop(){
	autologin();
	var pop = document.getElementById("popup");
		if(pop.style.display=="none"){
		pop.style.display = "flex";
	}
	else{
		pop.style.display = "none";
	}
}
//로그인 팝업 닫기
function pop_close(){
	var pop = document.getElementById("popup");
	pop.style.display = "none";
}

function logout(){
	if (confirm("정말로 로그아웃 하시겠습니까?")) {
		let id=localStorage.getItem("mid");
		let pass=localStorage.getItem("mpass");
		let ck=localStorage.getItem("autologin");
		if(id!=null&&pass!=null&&ck!=null){
			localStorage.removeItem("mid");
			localStorage.removeItem("mpass");
			localStorage.removeItem("autologin");
		}
		location.href="./logout.do";
	}
}

function autologin(){
	var ck=localStorage.getItem("autologin");
	var id=localStorage.getItem("mid");
	var pass=localStorage.getItem("mpass");
	
	if(id!=null&&pass!=null&&ck!=null){
		boolean="";
		frm_top_login.mid.value=id;
		var sp=pass.split("/");
		if(sp[1]=="auto"){
			frm_top_login.mpass.value=sp[0];
			frm_top_login.auto_login.checked=true;
			frm_top_login.method="post";
			frm_top_login.action="./login.do";
			frm_top_login.submit();
		}
	}
}