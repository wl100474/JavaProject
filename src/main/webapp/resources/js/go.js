function logout() {
// let really = confirm("로그아웃하시겠습니까?");
// if (really) {
// location.href = "login";
// }
	
	Swal.fire({
        title: '로그아웃하시겠습니까?',
        icon: 'question',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '로그아웃',
        cancelButtonText: '돌아가기'
    }).then((result) => {
        if (result.isConfirmed) {
        	Swal.fire({
                icon: 'success',                         
                title: '로그아웃 되었습니다.',
                showConfirmButton: false
            });
            setTimeout(function() {
            	location.href = "login";
			}, 2000);
        }
    });
}

function goDel() {
//	if (confirm("회원을 탈퇴하시겠습니까?\r\n회원 정보와 관련된 모든 데이터가 지워집니다.")) {
//		alert("탈퇴가 완료되었습니다.")
//		location.href = "index";
//	} else {
//		return false;
//	}

	Swal.fire({
        title: '회원을 탈퇴하시겠습니까?',
        text: "회원 정보와 관련된 모든 데이터가 지워집니다.",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '탈퇴',
        cancelButtonText: '취소'
    }).then((result) => {
    	if (result.isConfirmed) {
        	Swal.fire({
                icon: 'success',                         
                title: '탈퇴가 완료되었습니다.',
                showConfirmButton: false
            });
            setTimeout(function() {
            	location.href = "index";
			}, 2000);
        }
    });
}

function regVolunteer() {
	location.href = "regVolunteer";
}

function detail(v_no) {
	location.href = "volunteerDetail?v_no=" + v_no;
}

function updateV(v_no) {
	location.href = "updateVolunteer?v_no=" + v_no;
}

function deleteV(v_no) {
	if (confirm("작성글을 삭제하시겠습니까?")) {
		alert("삭제가 완료되었습니다.");
		location.href = "deleteVolunteer?v_no=" + v_no;
	} else {
		return false;
	}
}

function apply() {
	if (confirm("봉사를 신청하시겠습니까?")) {
		alert("신청이 완료되었습니다.");
		return true;
	} else {
		return false;
	}
}

function cancelV(v_no, v_id) {
	if (confirm("봉사를 취소하시겠습니까?")) {
		alert("취소가 완료되었습니다.");
		location.href = "cancelApplication?v_no=" + v_no + "&v_id=" + v_id;
	} else {
		return false;
	}
}

// function updateC(v_no, c_no) {
// let con = $("[id='update']").closest("tr").next().children("#content");
// let text = con.text();
// alert(text);
// let parent = $(this).closest("tr").next();
// parent.empty();
// parent.append('<td><textarea autofocus="autofocus" name="c_content"
// autocomplete="off" maxlength="200" class="updateArea">'
// + text + '</textarea></td>');
// let textarea = $(".updateArea").text();
// parent.append('<td align="right" valign="middle"><c:if
// test="${sessionScope.loginMember != null }"><button type="button"
// class="updateBtn">수정</button></c:if></td>');
// $(".updateBtn").click(function() {
// updateCmt(v_no, c_no);
// });
// }

function updateCmt(v_no, c_no, c_content) {

	if (confirm("댓글을 수정하시겠습니까?")) {
		$.ajax({
			url : "updateCmt",
			type : 'POST',
			data : {
				v_no : v_no,
				c_no : c_no,
				c_content : c_content
			},
			success : function(cnt) {
				if (cnt == 0) {
					alert('댓글 수정에 실패하였습니다.');
					return false;
				} else {
					alert('댓글이 수정되었습니다.');
					location.href = "volunteerDetail?v_no=" + v_no;
				}
			}
		});
	} else {
		return false;
	}
	// location.href = "updateCmt?v_no="+ v_no + "&c_no=" + c_no;
}

function deleteC(v_no, c_no) {
	if (confirm("댓글을 삭제하시겠습니까?")) {
		alert("삭제가 완료되었습니다.");
		location.href = "deleteCmt?v_no=" + v_no + "&c_no=" + c_no;
	} else {
		return false;
	}
}