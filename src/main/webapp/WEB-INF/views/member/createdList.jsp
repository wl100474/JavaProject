<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<div class="row">
	<div class="col-md-8 col-md-offset-2 fh5co-form animate-box my8Form"
		data-animate-effect="fadeIn">
		<div class="myTitle">■ 내가 등록한 봉사 목록 ■</div>
		<table class="table table-hover myListTbl">
			<tr>
				<th>제목</th>
				<th>봉사 타입</th>
				<th>봉사일</th>
				<th>상태</th>
				<th>등록일</th>
			</tr>
			<c:if test="${empty myList}">
				<tr>
					<td colspan="5" align="center"><b>작성하신 봉사 활동 글이 없습니다.</b></td>
				</tr>
			</c:if>
			<c:forEach var="l" items="${myList }">
				<tr onclick="detail(${l.v_no});" class="mouse">
					<td>${l.v_title }</td>
					<td>${l.v_type }</td>
					<td><fmt:formatDate value="${l.v_date}" pattern="yyyy-MM-dd E" />
						${l.v_time }시</td>
					<td>${l.v_status == 1 ? "모집중" : "종료" }</td>
					<td><fmt:formatDate value="${l.v_regdate}" pattern="yyyy-MM-dd" /></td>
				</tr>
			</c:forEach>
		</table>
		<!-- paging -->
		<div class="pageCount">
			<c:forEach var="p" begin="1" end="${pageCount }">
				<a href="createdList_paging?p=${p }">${p }</a>
			</c:forEach>
		</div>
	</div>
</div>




<%@ include file="../footer.jsp"%>