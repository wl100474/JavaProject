<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Start Sign In Form -->
<form action="login" method="post" class="fh5co-form animate-box"
	data-animate-effect="fadeInRight">
	<h2>Sign In</h2>
	<div class="form-group">
		<label for="m_id" class="sr-only">Username</label> 
		<input type="text" class="form-control" id="m_id" name="m_id" placeholder="Username"
			autocomplete="off" value="${cookie.lastLoginId.value }">
	</div>
	<div class="form-group">
		<label for="m_pw" class="sr-only">Password</label> <input
			type="password" class="form-control" id="m_pw" name="m_pw"
			placeholder="Password" autocomplete="off"  value="${cookie.lastLoginPw.value }">
	</div>
	<div class="form-group">
		<label for="remember"><input type="checkbox" id="remember" name="remember" ${empty cookie.lastLoginId.value ? "":"checked" }>
			Remember Me</label>
	</div>
	<div class="form-group">
		<p>
			Not registered? <a href="sign-up">Sign Up</a> | <a
				href="forgot">Forgot Password?</a>
		</p>
	</div>
	<div class="form-group">
		<input type="submit" value="Sign In" class="btn myBtnColor">
	</div>
</form>
<!-- END Sign In Form -->