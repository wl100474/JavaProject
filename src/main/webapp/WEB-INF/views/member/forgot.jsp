<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<div class="container">
	<div class="row">
		<div class="col-md-6 col-md-offset-3">

			<!-- Start Form -->
			<div class="fh5co-form animate-box" data-animate-effect="fadeIn">
				<h2>Forgot Password</h2>
				<div class="form-group" id="alert"></div>
				<div class="form-group">
					<label for="m_id" class="sr-only">ID</label> <input type="text"
						class="form-control" id="m_id" name="m_id" placeholder="ID"
						autocomplete="off"> <label for="m_email" class="sr-only">Email</label>
					<input type="email" class="form-control" id="m_email"
						name="m_email" placeholder="Email" autocomplete="off">
				</div>
				<div class="form-group">
					<p>
						<a href="/project">Sign In</a> or <a href="sign-up">Sign Up</a>
					</p>
				</div>
				<div class="form-group">
					<button class="myBtnColor" id="send">Send Email</button>
				</div>
			</div>
			<!-- END Form -->

		</div>
	</div>

</div>

<script type="text/javascript">
$("#send").click(function() {
	let m_id = $("#m_id").val();
	let m_email = $("#m_email").val();
	
	$.ajax({
		url : "finduserpwd",
		type : 'POST',
		dataType : 'json',
		data : {
			m_id : m_id,
			m_email : m_email
		},
		success : function(result) {
			if(result==true){
				$("#alert").empty();
				let content = $("<div>").attr("class",
				"alert alert-success");
				content.attr("role", "alert");
				let p = content.text("임시 비밀번호가 발급되었습니다.\n메일함을 확인해 주세요.");
				p.html(p.html().replace(/\n/g, '<br/>'));
		
				let div = $("<div>").attr("class", "form-group");
				div.append(p);
				$("#alert").append(div);
	
				setTimeout(function() {
					location.href="/project";
				}, 3000);
				
				console.log(result);
			} else {
				$("#alert").empty();
				let content = $("<div>").attr("class",
				"alert alert-danger");
				content.attr("role", "alert");
				content.text("아이디 또는 이메일을 정확하게 입력해 주세요.");
		
				let div = $("<div>").attr("class", "form-group");
				div.append(content);
				$("#alert").append(div);
				console.log(result);
			}
			

		}
	});
});
</script>

<%@ include file="../footer.jsp"%>