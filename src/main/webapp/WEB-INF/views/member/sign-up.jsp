<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
		<div class="row">
			<div class="col-md-4 col-md-offset-4">

				<!-- Start Sign In Form -->
				<form action="index" method="post" class="fh5co-form animate-box"
					data-animate-effect="fadeIn">
					<h2>Sign Up</h2>
					<div class="form-group">
						<div class="alert alert-success" role="alert" id="formAlert">양식을 모두 입력해 주세요.</div>
					</div>
					<div class="form-group">
						<label for="m_id" class="sr-only">ID</label> 
						<input type="text" class="form-control" id="m_id" name="m_id" placeholder="ID" autocomplete="off" oninput="checkID();">
					</div>
					<div class="form-group">
						<label for="m_pw" class="sr-only">Password</label> 
						<input type="password" class="form-control" id="m_pw" name="m_pw" placeholder="Password" autocomplete="off">
					</div>
					<div class="form-group">
						<label for="re-pw" class="sr-only">Re-type Password</label>
						<input type="password" class="form-control" id="re_pw" name="re_pw" placeholder="Re-type Password" autocomplete="off" oninput="checkPW();">
					</div>
					<div class="form-group">
						<label for="m_name" class="sr-only">Name</label> 
						<input type="text" class="form-control" id="m_name" name="m_name" placeholder="Name" autocomplete="off">
					</div>
					<div class="form-group">
						<label for="m_email" class="sr-only">Email</label> 
						<input type="email" class="form-control" id="m_email" name="m_email" placeholder="Email" autocomplete="off" oninput="checkEmail();">
					</div>
					<div class="form-group">
						<p>
							Already registered? <a href="/project">Sign In</a>
						</p>
					</div>
					<div class="form-group">
						<button class="btn myBtnColor" onclick="return registerCheck();">Sign Up</button>
					</div>
				</form>
				<!-- END Sign In Form -->

			</div>
		</div>
		

<%@ include file="../footer.jsp" %>