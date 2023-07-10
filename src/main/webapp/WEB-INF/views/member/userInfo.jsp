<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
		<div class="row">
			<div class="col-md-4 col-md-offset-4 updateForm">

				<!-- Start User Info Form -->
				<form action="userInfo" method="post" class="fh5co-form animate-box"
					data-animate-effect="fadeIn">
					<h2>회원 정보 수정</h2>
					<div class="form-group">
						<div class="alert alert-success" role="alert" id="formAlert">양식을 모두 입력해 주세요.</div>
					</div>
					<div class="form-group">
						<label for="m_id">ID</label> 
						<input type="text" class="form-control" id="m_id" name="m_id" placeholder="ID" autocomplete="off" value="${sessionScope.loginMember.m_id }" readonly="readonly">
					</div>
					<div class="form-group">
						<label for="m_pw">Password</label> 
						<input type="password" class="form-control" id="m_pw" name="m_pw" placeholder="Password" autocomplete="off" value="${sessionScope.loginMember.m_pw }">
					</div>
					<div class="form-group">
						<label for="re-pw">Re-type Password</label>
						<input type="password" class="form-control" id="re_pw" name="re_pw" placeholder="Re-type Password" autocomplete="off" value="${sessionScope.loginMember.m_pw }" oninput="checkPW();">
					</div>
					<div class="form-group">
						<label for="m_name">Name</label> 
						<input type="text" class="form-control" id="m_name" name="m_name" placeholder="Name" autocomplete="off" value="${sessionScope.loginMember.m_name }">
					</div>
					<div class="form-group">
						<label for="m_email">Email</label> 
						<input type="email" class="form-control" id="m_email" name="m_email" placeholder="Email" autocomplete="off" value="${sessionScope.loginMember.m_email }" oninput="checkEmail();">
					</div>
					<div class="form-group">
						<button class="btn myBtnColor" onclick="return updateCheck();">Update</button>
					</div>
				</form>
				<div class="fh5co-form animate-box"
					data-animate-effect="fadeIn">
					<span>회원을 탈퇴하시겠습니까?</span><button class="btn btn-del" onclick="goDel();">Delete</button>
				</div>
				
				<!-- END User Info Form -->

			</div>
		</div>
		

<%@ include file="../footer.jsp" %>