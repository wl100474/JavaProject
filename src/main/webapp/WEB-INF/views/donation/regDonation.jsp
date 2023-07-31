<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<div class="row">
	<div class="col-md-8 col-md-offset-2 my8Form">

		<!-- Start Form -->
		<form action="regDonation" method="post" class="fh5co-form animate-box" enctype="multipart/form-data"
			data-animate-effect="fadeIn">
			<!-- START 설문 등록 -->
			<div class="myTitle">■ 크라우드 펀딩 등록 ■</div>
			<div class="myDiv">
				<div class="form-group">
					<label for="d_title"><h3>제목</h3></label> 
					<input type="text"
						class="form-control" id="d_title" name="d_title" placeholder="제목"
						autocomplete="off">
				</div>
				<div class="form-group">
					<label for="d_content" class="sr-only">펀딩 내용</label>
					<textarea class="form-control" id="d_content" name="d_content"
						placeholder="펀딩에 대한 설명(200자 이내)" maxlength="200" autocomplete="off"></textarea>
				</div>
				<div class="form-group">
					<label for="d_photo"><h3>사진</h3></label><br> 
					<input type="file" class="form-control-file" id="d_photo" name="d_photo">
				</div>
				<div class="form-group">
					<label><h3>펀딩 일정</h3></label><br> 
					<input type="date" id="d_startDate" name="d_startDate"> ㅡ
					<input type="date" id="d_endDate" name="d_endDate">
				</div>
				<div class="form-group">
					<label for="d_goal"><h3>목표 모금액</h3></label> 
					<input type="number"
						class="form-control" id="d_goal" name="d_goal" placeholder="숫자만 입력"
						autocomplete="off">
				</div>
			</div>
			<!-- END 펀딩 등록  -->

			<div class="form-group">
				<button type="submit" class="myBtnCenter">등록</button>
			</div>
		</form>
		<!-- END Sign In Form -->

	</div>
</div>

<%@ include file="../footer.jsp"%>