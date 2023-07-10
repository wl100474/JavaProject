<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<div class="row">
	<div class="col-md-8 col-md-offset-2">

		<!-- Start Form -->
		<form action="updateVolunteer" method="post" class="fh5co-form animate-box"
			data-animate-effect="fadeIn" onsubmit="return updateVCheck();">
			<!-- START 설문 등록 -->
			<div class="myTitle">■ 자원 봉사 수정 ■</div>
			<div class="myDiv">
			<input type="hidden" name="v_no" value="${detail.v_no }">
				<div class="form-group">
					<label for="v_title"><h3>제목</h3></label> 
					<input type="text"
						class="form-control" id="v_title" name="v_title" value="${detail.v_title }"
						autocomplete="off">
				</div>
				<div class="form-group">
					<label for="v_content" class="sr-only">봉사 내용</label>
					<textarea class="form-control" id="v_content" name="v_content"
						maxlength="200" autocomplete="off">${detail.v_content }</textarea>
				</div>
				<div class="form-group">
					<label for="v_type"><h3>봉사 타입</h3></label><br> 
					<select id="v_type" name="v_type">
						<option <c:if test="${detail.v_type eq '동물복지 봉사 활동' }">selected</c:if>>동물복지 봉사 활동</option>
						<option <c:if test="${detail.v_type eq '아동복지 봉사 활동' }">selected</c:if>>아동복지 봉사 활동</option>
						<option <c:if test="${detail.v_type eq '사회복지관 활동' }">selected</c:if>>사회복지관 활동</option>
						<option <c:if test="${detail.v_type eq '노인복지 봉사 활동' }">selected</c:if>>노인복지 봉사 활동</option>
						<option <c:if test="${detail.v_type eq '무료급식' }">selected</c:if>>무료급식</option>
					</select>
				</div>
				<div class="form-group">
					<label><h3>봉사 날짜</h3></label><br> 
					<input type="date" id="v_date" name="v_date" value="<fmt:formatDate value="${detail.v_date }" pattern = "yyyy-MM-dd"/>">
					<select name="v_time">
						<c:forEach var="t" begin="1" end="24">
							<option <c:if test="${detail.v_time eq t }">selected</c:if>>${t }</option>
						</c:forEach>
					</select> 시
				</div>
				<div class="form-group">
					<label for="v_area"><h3>봉사 지역</h3></label> 
					<input type="text"
						class="form-control" id="v_area" name="v_area" placeholder="시/군/구" value="${detail.v_area }"
						autocomplete="off">
				</div>
				<div class="form-group">
					<label for="v_recruit"><h3>모집 인원</h3></label> 
					<input type="number"
						class="form-control" id="v_recruit" name="v_recruit" placeholder="최대 10명" value="${detail.v_recruit }"
						autocomplete="off">
				</div>
				<div class="form-group">
					<label for="v_chat"><h3>채팅방 링크</h3></label> 
					<input type="text"
						class="form-control" id="v_chat" name="v_chat" placeholder="오픈채팅방 링크 첨부" value="${detail.v_chat }"
						autocomplete="off">
				</div>
			</div>
			<!-- END 봉사 등록  -->

			<div class="form-group">
				<button class="myBtnCenter">수정</button>
			</div>
		</form>
		<!-- END Sign In Form -->

	</div>
</div>

<%@ include file="../footer.jsp"%>