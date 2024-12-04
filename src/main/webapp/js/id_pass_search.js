var return_id="error";


function search_myid(){
		let name=document.getElementById("id_search_name").value;
		let hp=document.getElementById("id_search_hp").value;
		let email=document.getElementById("id_search_email").value;
		const a=/^[ㄱ-ㅎ|ㅏ-ㅢ|가-힁|a-z|A-Z]/;
		const b=/^[0-9]/;
	if(name==""){
		alert("고객명을 입력해주세요.");
	}
	else if(hp==""){
		alert("연락처를 입력해주세요.");
	}
	else if(email==""){
		alert("이메일을 입력해주세요.");
	}
	else if(!a.test(name)){
		alert("고객명은 한글과 영어만 입력해주세요.");
	}
	else if(!b.test(hp)){
		alert("연락처는 숫자만 입력해주세요.");	
	}
	else if(!/@/.test(email)){
		alert("이메일을 정확히 입력해주세요.");		
	}
	else{
		var http,result;
		http=new XMLHttpRequest();
		http.onreadystatechange=function(){
			if(http.readyState==4&&http.status==200){
				//eval로 backend에 대한 script를 로드하여 활성화 시킴
				console.log(this.response);
				return_id=this.response;		
				setTimeout(change_display,1000);
			}
		}
		http.open("post","./id_search.do",true);
		http.setRequestHeader("content-type","application/x-www-form-urlencoded");
		http.send("mname="+name+"&&mphone="+hp+"&&memail="+email);
	}
}

function change_display(){
    const id = document.querySelector(".id_searchview");
    if (id) {
        if (return_id != "error") {
            id.style.display = 'block';
            let change_id = return_id.substring(0, 4) + "*".repeat(return_id.length-4);
            
            id.innerHTML ="고객의 아이디 :"+ change_id + "입니다"; 
        }   	
		else{
			id.style.display = 'block';
			id.innerHTML="가입되지 않은 회원정보입니다.";
		}
    }
}

function change_mypass(){
	const a=/^[A-Za-z0-9]/;
	const b=/^[ㄱ-ㅎ|ㅏ-ㅢ|가-힁|a-z|A-Z]/;
	const c=/^[0-9]/;
	if(id_pass_search.mid.value==""){
		alert("아이디를 입력해주세요.");
	}
	else if(id_pass_search.mname.value==""){
		alert("고객명을 입력해주세요.");
	}
	else if(id_pass_search.mphone.value==""){
		alert("연락처를 입력해주세요.");
	}
	else if(!a.test(id_pass_search.mid.value)){
		alert("아이디는 영문/숫자만 입력가능합니다.");
	}
	else if(!b.test(id_pass_search.mname.value)){
		alert("고객명은 한글만 입력해주세요.");
	}
	else if(!c.test(id_pass_search.mphone.value)){
		alert("연락처는 숫자만 입력해주세요.");
	}
	else {
		id_pass_search.method="post";
		id_pass_search.action="./change_pass.do";
		id_pass_search.submit();
	}
}