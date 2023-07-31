<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<div class="row">
	<div class="col-md-8 col-md-offset-2 fh5co-form animate-box"
		data-animate-effect="fadeIn">

		<!-- Start Form -->
		<form action="applyVolunteer" onsubmit="return apply();">
			<!-- START 상세보기 -->
			<div class="myTitle">■ 자원 봉사 상세보기 ■</div>
			<div class="myDiv">
				<input type="hidden" name="v_no" value="${detail.v_no }">
				<table class="myTbl">
					<tr>
						<th class="vTitle" colspan="2">[${detail.v_area} /
							${detail.v_type}] ${detail.v_title }</th>
					</tr>
					<tr>
						<td>작성자 : ${detail.v_writer }</td>
						<td align="right">작성일 : <fmt:formatDate
								value="${detail.v_regdate }" pattern="yyyy-MM-dd" /></td>
					</tr>
					<tr>
						<td colspan="2">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="2">${detail.v_content }</td>
					</tr>
					<tr>
						<td colspan="2">봉사 날짜 : <fmt:formatDate
								value="${detail.v_date }" pattern="yyyy년 MM월 dd일 E요일" />
							${detail.v_time }시
						</td>
					</tr>
					<tr>
						<td colspan="2">현재 인원 : ${detail.v_staff }명 / 모집 인원 :
							${detail.v_recruit }명</td>
					</tr>
					<tr>
						<td colspan="2">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="2">채팅방 링크 : <a href="${detail.v_chat }">${detail.v_chat }</a></td>
					</tr>
				</table>
			</div>
			<!-- END 상세보기 -->

			<div class="form-group">
				<c:if test="${error != null}">
					<div class="textB">${error }</div>
				</c:if>
				<c:if test="${detail.v_status == 0}">
					<div class="textB">이미 마감된 봉사입니다.</div>
				</c:if>
				<c:if test="${detail.v_status == 2}">
					<div class="textB">이미 종료된 봉사입니다.</div>
				</c:if>
				<c:if test="${sessionScope.loginMember.m_id != null && sessionScope.loginMember.m_id != detail.v_writer}">
					<c:if test="${list == null && detail.v_status == 1}">
						<button class="btn myBtnRight">신청하기</button>
					</c:if>
					<c:if test="${list != null}">
						<button type="button" class="btn myBtn-DelRight"
							onclick="cancelV(${list.v_no}, '${list.v_id }');">취소하기</button>
					</c:if>
				</c:if>
				<c:if test="${sessionScope.loginMember.m_id == detail.v_writer}">
					<button type="button" class="btn myBtnRight"
						onclick="updateV(${detail.v_no});">수정</button>
					<button type="button" class="btn myBtn-DelRight"
						onclick="deleteV(${detail.v_no});">삭제</button>
				</c:if>
			</div>
		</form>
		<!-- END Sign In Form -->

		<div class="myDiv cmt">
			<h2 class="vTitle">댓글</h2>
			<c:if test="${empty cmts}">
				<div class="textB">작성된 댓글이 없습니다.</div>
			</c:if>
			<c:if test="${sessionScope.loginMember != null }">
				<form action="cmtWrite" method="post">
					<input type="hidden" name="v_no" value="${detail.v_no }">
					<input type="hidden" name="token" value="${generatedToken }">
					<table class="table table-borderless">
						<tr>
							<td><textarea placeholder="댓글을 입력해주세요."
									autofocus="autofocus" name="c_content" autocomplete="off"
									maxlength="200" class="writeArea"></textarea></td>
							<td align="right"><button class="writeBtn">작성</button></td>
						</tr>
					</table>
				</form>
			</c:if>
			<c:if test="${!empty cmts}">
				<table class="table">
					<c:forEach var="cmt" items="${cmts }">
						<tr class="table-secondary">
							<th><c:forEach begin="1" end="${cmt.c_indent }">
									<img src="resources/images/reply.gif">
								</c:forEach> <c:if test="${cmt.c_id == null}">알 수 없음</c:if>${cmt.c_id }
							</th>
							<td align="right">작성일 : <fmt:formatDate
									value="${cmt.c_regdate }" pattern="yyyy-MM-dd hh:mm:ss" /> <c:if
									test="${sessionScope.loginMember.m_id == cmt.c_id}">
									<input type="hidden" name="c_no" value="${cmt.c_no }">
									<button type="button" class="myCmtBtnRight" id="update">수정</button>
									<button type="button" class="myCmtBtn-DelRight"
										onclick="deleteC(${detail.v_no}, ${cmt.c_no});">삭제</button>
								</c:if>
							</td>
						</tr>
						<tr>
							<td id="content">${cmt.c_content }</td>
							<td align="right"><c:if
									test="${sessionScope.loginMember != null }">
									<button type="button" id="regC" class="cmtBtn">답글 작성</button>
								</c:if></td>
						</tr>

						<!-- 클릭 시 보여줄 부분 -->
						<tr id="cmtCmt">
							<form action="cmtCmtWrite" method="post">
								<td>			
									<input type="hidden" name="v_no" value="${detail.v_no }">
									<input type="hidden" name="c_no" value="${cmt.c_no }">
									<input type="hidden" name="token" value="${generatedToken }">
									<img src="resources/images/reply.gif">
									<textarea placeholder="답글을 입력해주세요." autofocus="autofocus"
										name="c_content" autocomplete="off" maxlength="200"
										class="writeArea2"></textarea>
								</td>
								<td align="right">
									<button class="writeBtn2">작성</button>
								</td>
							</form>
						</tr>
						<!-- 
								<td>			
									<input type="hidden" name="v_no" value="${detail.v_no }">
									<input type="hidden" name="c_no" value="${cmt.c_no }">
									<img src="resources/images/reply.gif">
									<textarea placeholder="답글을 입력해주세요." autofocus="autofocus"
										name="c_content" autocomplete="off" maxlength="200"
										class="writeArea2"></textarea>
								</td>
								<td align="right">
									<button type="button" class="writeBtn2">작성</button>
								</td>
						-->
					</c:forEach>
				</table>
				<!-- paging -->
				<div class="pageCount">
					<input type="hidden" name="v_no" value="${detail.v_no }">
					<c:forEach var="p" begin="1" end="${pageCount }">
						<a href="cmt_paging?v_no=${detail.v_no }&p=${p }">${p }</a>
					</c:forEach>
				</div>
			</c:if>
		</div>

	</div>
</div>

<script type="text/javascript">
$(function() {
	 $('tr[id^=cmtCmt]').each(function(){
	        $(this).hide();
	    });
	
	
	$("[id='regC']").click(function() {
		$(this).closest("tr").next().slideToggle();
		let text = $(this).text();
		let text2 = "답글 작성";
		if (text == text2) {
			$(this).text("작성 취소");
		} else {
			$(this).text("답글 작성");
		}
		
	});
	
	$("[id='update']").click(function() {
		let con = $(this).closest("tr").next().children("#content");
		let text = con.text();
		//alert(text);
		
//		let c_no = $(this).closest("td").prev().children("input[name='c_no']").val();
		let c_no = $(this).prev().val();
		
		let cc_no = $("input[name='c_no']").val();
		let v_no = $("input[name='v_no']").val();
		// alert(v_no + ", 댓글 번호 : " + c_no +", test : " + cc_no);
		
		let parent = $(this).closest("tr").next();
		parent.empty();
		parent.append('<td><textarea autofocus="autofocus" name="c_content" autocomplete="off" maxlength="200" class="updateArea">'
						+ text + '</textarea></td>');
		let textarea = $(".updateArea").text();
		parent.append('<td align="right" valign="middle"><c:if test="${sessionScope.loginMember != null }"><button type="button" class="updateBtn">수정</button></c:if></td>');
		
		$("[class='updateBtn']").click(function() {
			let c_content = $(this).closest("td").prev().children(".updateArea").val();
			// alert(c_content);
			updateCmt(v_no, c_no, c_content);
		});
	}); 
});
	
</script>
<%@ include file="../footer.jsp"%>