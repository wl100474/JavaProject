<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<div class="row">
	<div class="col-md-8 col-md-offset-2 my8Form">

		<!-- Start Form -->
		<form action="regVolunteer" method="post" class="fh5co-form animate-box"
			data-animate-effect="fadeIn" onsubmit="return VCheck();">
			<!-- START 설문 등록 -->
			<div class="myTitle">■ 자원 봉사 등록 ■</div>
			<div class="myDiv">
				<div class="form-group">
					<label for="v_title"><h3>제목</h3></label> 
					<input type="text"
						class="form-control" id="v_title" name="v_title" placeholder="제목"
						autocomplete="off">
				</div>
				<div class="form-group">
					<label for="v_content" class="sr-only">봉사 내용</label>
					<textarea class="form-control" id="v_content" name="v_content"
						placeholder="봉사에 대한 설명(200자 이내)" maxlength="200" autocomplete="off"></textarea>
				</div>
				<div class="form-group">
					<label for="v_type"><h3>봉사 타입</h3></label><br> 
					<select id="v_type" name="v_type">
						<option>동물복지 봉사 활동</option>
						<option>아동복지 봉사 활동</option>
						<option>사회복지관 활동</option>
						<option>노인복지 봉사 활동</option>
						<option>무료급식</option>
					</select>
				</div>
				<div class="form-group">
					<label><h3>봉사 날짜</h3></label><br> 
					<input type="date" id="v_date" name="v_date">
					<select name="v_time">
						<c:forEach var="t" begin="1" end="24">
							<option>${t }</option>
						</c:forEach>
					</select> 시
				</div>
				<div class="form-group">
					<label for="v_area"><h3>봉사 지역</h3></label> 
					<input type="text"
						class="form-control" id="v_area" name="v_area" placeholder="시/군/구"
						autocomplete="off">
				</div>
				<div class="form-group">
					<label for="v_recruit"><h3>모집 인원</h3></label> 
					<input type="number"
						class="form-control" id="v_recruit" name="v_recruit" placeholder="최대 10명"
						autocomplete="off">
				</div>
				<div class="form-group">
					<label for="v_chat"><h3>채팅방 링크</h3></label> 
					<input type="text"
						class="form-control" id="v_chat" name="v_chat" placeholder="오픈채팅방 링크 첨부"
						autocomplete="off">
				</div>
			</div>
			<!-- END 봉사 등록  -->

			<div class="form-group">
				<button class="btn myBtnCenter">등록</button>
			</div>
		</form>
		<!-- END Sign In Form -->

	</div>
</div>

<%@ include file="../footer.jsp"%>