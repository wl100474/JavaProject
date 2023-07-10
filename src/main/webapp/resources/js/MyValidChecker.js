/* 회원가입 유효성 체크 */
function registerCheck() {
   
    if($.trim($('#m_id').val()) == '') {
        alert("아이디를 입력해주세요.");
        $('#m_id').focus();
        return false;
    }
    if($.trim($('#m_pw').val()) == '') {
        alert("비밀번호를 입력해주세요.");
        $('#m_pw').focus();
        return false;
    }
    if($.trim($('#re_pw').val()) == '') {
        alert("비밀번호 확인칸을 입력해주세요.");
        $('#re_pw').focus();
        return false;
    }
    if($.trim($('#m_name').val()) == '') {
        alert("이름을 입력해주세요.");
        $('#m_name').focus();
        return false;
    }
    if($.trim($('#m_email').val()) == '') {
        alert("이메일을 입력해주세요.");
        $('#m_email').focus();
        return false;
    }
 
    if(confirm("회원가입을 하시겠습니까?")){
        alert("회원가입이 완료되었습니다. 감사합니다.");
    } else {
    	return false;
    }
    return true;
}

function checkID() {
	let m_id = $("#m_id").val();
	$.ajax({
		url: "idCheck",
		data: {m_id:m_id},
		success: function(cnt) {
			$("#formAlert").empty();
			if (cnt == 0 && m_id.length >= 4) {
				$("#formAlert").text("사용 가능한 아이디입니다.");
				$("#formAlert").css("color", "green");
			} else if (cnt != 0 && m_id.length >= 4) {
				$("#formAlert").text("이미 존재하는 아이디입니다.");
				$("#formAlert").css("color", "red");
			} else {
				$("#formAlert").text("아이디를 4글자 이상 입력해 주세요.");
				$("#formAlert").css("color", "red");
			}
		}
	});
}

function checkEmail() {
	let regExp = /\w+([-+.]\w+)*@\w+([-.]\w+)*\.[a-zA-Z]{2,4}$/;
	let m_email = $("#m_email").val();
	$.ajax({
		url: "emailCheck",
		data: {m_email:m_email},
		success: function(cnt) {
			if (regExp.test(m_email)) {
				$("#formAlert").empty();
				if (cnt == 0) {
					$("#formAlert").text("사용 가능한 이메일입니다.");
					$("#formAlert").css("color", "green");
				} else {
					$("#formAlert").text("이미 존재하는 이메일입니다.");
					$("#formAlert").css("color", "red");
				} 
			} else {
				$("#formAlert").text("이메일 양식에 맞춰 입력해 주세요.");
				$("#formAlert").css("color", "red");
			}
		}
	});
}


	

function checkPW() {
	let m_pw = $("#m_pw").val();
	let re_pw = $("#re_pw").val();
	$("#formAlert").empty();
	if (m_pw != re_pw) {
		$("#formAlert").text("비밀번호가 일치하지 않습니다.");
		$("#formAlert").css("color", "red");
	} else {
		$("#formAlert").text("비밀번호가 일치합니다.");
		$("#formAlert").css("color", "green");
	} 

}

function updateCheck() {
    if($.trim($('#m_name').val()) == '') {
        alert("이름을 입력해주세요.");
        return false;
    }
    if($.trim($('#m_pw').val()) == '') {
        alert("비밀번호를 입력해주세요.");
        return false;
    }
    if($.trim($('#re_pw').val()) == '') {
        alert("비밀번호 확인칸을 입력해주세요.");
        return false;
    }
    if($.trim($('#m_pw').val()) != $.trim($('#re_pw').val())) {
        alert("비밀번호가 일치하지 않습니다.");
        return false;
    }
    if($.trim($('#m_email').val()) == '') {
        alert("이메일을 입력해주세요.");
        return false;
    }
 
    if(confirm("정보 수정을 완료 하시겠습니까?")){
        alert("수정이 완료되었습니다.");
    } else {
    	return false;
    }
    return true;
}

/* 봉사 유효성 체크 */
function VCheck() {
	let v_date = $('#v_date').val(); // 봉사일
	v_date = new Date(v_date);
	let vYear = v_date.getFullYear();
	let vMonth = v_date.getMonth() + 1;
	let vDay = v_date.getDate();
	v_date = vYear + vMonth  + vDay;
	
	let today = new Date();			 // 오늘 날짜
	let tYear = today.getFullYear();
	let tMonth = today.getMonth() + 1;
	let tDay = today.getDate();
	today = tYear + tMonth + tDay;
	
    if($.trim($('#v_title').val()) == '') {
        alert("제목을 입력해주세요.");
        return false;
    }
    if($.trim($('#v_content').val()) == '') {
        alert("아이디를 입력해주세요.");
        return false;
    }
    if($.trim($('#v_date').val()) == '') {
        alert("날짜를 선택해주세요.");
        return false;
    }
    if (v_date <= today) { // 봉사일이 오늘 날짜보다 작을 때
    	alert("지정하신 봉사일은 유효하지 않습니다.");
        return false;
	}
    if($.trim($('#v_area').val()) == '') {
        alert("봉사 지역을 입력해주세요.");
        return false;
    }
    if($.trim($('#v_recruit').val()) == '') {
        alert("모집 인원을 입력해주세요.");
        return false;
    }
    if($.trim($('#v_chat').val()) == '') {
        alert("채팅방 링크를 입력해주세요.");
        return false;
    }
    if(!$('#v_chat').val().startsWith('https://open.kakao.com')) {
        alert("채팅방 링크가 양식에 맞지 않습니다.");
        return false;
    }
 
    if(confirm("봉사를 등록하시겠습니까?")){
        alert("등록이 완료되었습니다. 감사합니다.");
    } else {
    	return false;
    }
    return true;
}

/* 봉사 수정 유효성 체크 */
function updateVCheck() {
	let v_date = $('#v_date').val(); // 봉사일
	v_date = new Date(v_date);
	let vYear = v_date.getFullYear();
	let vMonth = v_date.getMonth() + 1;
	let vDay = v_date.getDate();
	v_date = vYear + vMonth + vDay;
	
	let today = new Date();			 // 오늘 날짜
	let tYear = today.getFullYear();
	let tMonth = today.getMonth() + 1;
	let tDay = today.getDate();
	today = tYear + tMonth + tDay;
	
    if($.trim($('#v_title').val()) == '') {
        alert("제목을 입력해주세요.");
        return false;
    }
    if($.trim($('#v_content').val()) == '') {
        alert("아이디를 입력해주세요.");
        return false;
    }
    if($.trim($('#v_date').val()) == '') {
        alert("날짜를 선택해주세요.");
        return false;
    }
    if (v_date <= today) { // 봉사일이 오늘 날짜보다 작을 때
    	alert("지정하신 봉사일은 유효하지 않습니다.");
        return false;
	}
    if($.trim($('#v_area').val()) == '') {
        alert("봉사 지역을 입력해주세요.");
        return false;
    }
    if($.trim($('#v_recruit').val()) == '') {
        alert("모집 인원을 입력해주세요.");
        return false;
    }
    if($.trim($('#v_chat').val()) == '') {
        alert("채팅방 링크를 입력해주세요.");
        return false;
    }
 
    if(confirm("봉사를 수정하시겠습니까?")){
        alert("수정이 완료되었습니다. 감사합니다.");
    } else {
    	return false;
    }
    return true;
}