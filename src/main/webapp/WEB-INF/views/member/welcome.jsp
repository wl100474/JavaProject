<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Start Welcome Form -->
<div class="fh5co-form animate-box"
	data-animate-effect="fadeInRight">
	<h2>welcome, ${sessionScope.loginMember.m_name } !</h2>
	<div class="form-group">
		<h4 class="welcomeP"><a href="myVolunteerList">■ &nbsp;&nbsp;나의 봉사리스트</a></h4>
	</div>
	<div class="form-group">
		<h4 class="welcomeP"><a href="userInfo">■ &nbsp;&nbsp;개인정보수정</a></h4>
	</div>
	<div class="form-group">
		
	</div>
	<div class="form-group wGroup">
		<button class="btn myBtnColor wbtn" onclick="regVolunteer();">봉사 등록</button>
		<button class="btn myBtnColor wbtn" onclick="logout();">Sign Out</button>
	</div>
	
</div>
<!-- END Welcome Form -->