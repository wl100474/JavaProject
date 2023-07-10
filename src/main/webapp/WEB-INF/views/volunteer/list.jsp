<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<div class="row">
	<div class="col-md-8 col-md-offset-2 fh5co-form animate-box my8Form"
		data-animate-effect="fadeIn">
		<div class="myTitle"><a href="list">■ VOLUNTEERING LIST ■</a></div>
		<c:if test="${sessionScope.loginMember.m_id != null}">
			<button class="myBtnLeft" onclick="regVolunteer();">봉사 등록</button>
		</c:if>
		<form action="list_type">
			<button class="searchBtn">찾기</button>
			<select name="search" class="search">
				<option>전체</option>
				<option>동물복지 봉사 활동</option>
				<option>아동복지 봉사 활동</option>
				<option>사회복지관 활동</option>
				<option>노인복지 봉사 활동</option>
				<option>무료급식</option>
			</select>
		</form>
		<table class="table table-hover myListTbl">
			<tr class="table-secondary">
				<th>지역</th>
				<th>제목</th>
				<th>봉사 타입</th>
				<th>봉사일</th>
				<th>상태</th>
				<th>등록일</th>
			</tr>
			<c:if test="${empty List}">
				<tr>
					<td colspan="5" align="center"><b>작성된 글이 없습니다.</b></td>
				</tr>
			</c:if>
			<c:forEach var="l" items="${List }">
				<tr onclick="detail(${l.v_no});" class="mouse">
					<td>${l.v_area }</td>
					<td>${l.v_title }</td>
					<td>${l.v_type }</td>
					<td><fmt:formatDate value="${l.v_date}" pattern="yyyy-MM-dd E" />
						${l.v_time }시</td>
					<td>
						<c:if test="${l.v_status == 0}">
							마감
						</c:if>
						<c:if test="${l.v_status == 1}">
							모집중
						</c:if>
						<c:if test="${l.v_status == 2}">
							종료
						</c:if>
					</td>
					<td><fmt:formatDate value="${l.v_regdate}"
							pattern="yyyy-MM-dd" /></td>
				</tr>
			</c:forEach>
		</table>
		<!-- paging -->
		<div class="pageCount">
			<c:forEach var="p" begin="1" end="${pageCount }">
				<a href="list_paging?p=${p }">${p }</a>
			</c:forEach>
		</div>
	</div>
</div>




<%@ include file="../footer.jsp"%>