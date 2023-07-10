<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<div class="row">
	<div class="col-md-8 col-md-offset-2 fh5co-form animate-box"
		data-animate-effect="fadeIn">

			<!-- START 상세보기 -->
			<div class="myTitle">■ 이달의 기부 상세보기 ■</div>
			<div class="myDiv">
				<input type="hidden" id="d_no" name="d_no" value="${donation.d_no }">
				<input type="hidden" id="m_name" name="m_name" value="${sessionScope.loginMember.m_name }">
				<input type="hidden" id="m_email" name="m_email" value="${sessionScope.loginMember.m_email }">
				<table class="myTbl">
					<tr>
						<th class="vTitle" colspan="2">[ <c:if
								test="${donation.d_status == 0}">
							종료
						</c:if> <c:if test="${donation.d_status == 1}">
							모금 진행 중
						</c:if> <c:if test="${donation.d_status == 2}">
							모금완료
						</c:if> ] ${donation.d_title }
						</th>
					</tr>
					<tr>
						<td>작성자 : 관리자</td>
						<td align="right">작성일 : <fmt:formatDate
								value="${donation.d_regdate }" pattern="yyyy-MM-dd" /></td>
					</tr>
					<tr>
						<td colspan="2">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="2"><img
							src="resources/images/${donation.d_photo }" class="myImg"></td>
					</tr>
					<tr>
						<td colspan="2" class="dContent"><br>${donation.d_content }</td>
					</tr>
					<tr>
						<td colspan="2">&nbsp;</td>
					</tr>
					<tr>
						<c:if test="${sessionScope.loginMember != null && donation.d_amount < donation.d_goal}">
							<td colspan="2" align="right">
								<div>기부는 1000원 이상부터 가능합니다.</div>
								<input type="number" id="amount" name="amount" placeholder="기부할 금액">
							
								<button class="dBtn" onclick="requestPay();">기부하기</button>
							</td>
						</c:if>
						<c:if test="${donation.d_status == 0}">
							<td colspan="2" align="center">
								<div class="textB">이미 종료된 기부입니다.</div>
							</td>
						</c:if>
						<c:if test="${donation.d_status == 2}">
							<td colspan="2" align="center">
								<div class="textB">이미 모금이 완료된 기부입니다.</div>
							</td>
						</c:if>
					</tr>
					<tr>
						<td colspan="2">&nbsp;</td>
					</tr>
					<tr>
						<td colspan="2" align="right" class="dContent2">모금현황</td>
					</tr>
					<tr>
						<td colspan="2">
							<div class="progress">
								<div
									class="progress-bar progress-bar-striped bg-info progress-bar-animated"
									role="progressbar" style="width: ${per}%;"
									aria-valuenow="${per}" aria-valuemin="0" aria-valuemax="100">${per}%</div>
							</div>
						</td>
					</tr>
					<tr>
						<td class="dContent">현재 모금액 : <fmt:formatNumber
								value="${donation.d_amount }" type="currency" currencySymbol="￦" />
						<td class="dContent" align="right">목표 모금액 : <fmt:formatNumber
								value="${donation.d_goal }" type="currency" currencySymbol="￦" />
					</tr>
					<tr>
						<td colspan="2" class="dContent2">모금 일정 : <fmt:formatDate
								value="${donation.d_startDate }" pattern="yyyy년 MM월 dd일 E요일" />
							부터 <fmt:formatDate value="${donation.d_endDate }"
								pattern="yyyy년 MM월 dd일 E요일" /> 까지
						</td>
					</tr>
					<tr>
						<td colspan="2">&nbsp;</td>
					</tr>
				</table>
			</div>
			<!-- END 상세보기 -->






	</div>
</div>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
<script src="https://cdn.iamport.kr/v1/iamport.js"></script>
<script type="text/javascript">
	// 가맹점 식별코드 imp72315021
	// rest api key 6573066076405072
	// rest api secret ecZ8YzGxP1vBjjECvOXTATPM3Bx4nNc82TC36V7JqfcOz4ngkjjayXhGqpQgWGDH5ZXY6gwS3zS9vLCI
	function requestPay() {
		var amount = $("#amount").val();
		var d_no = $("#d_no").val();
		var m_name = $("#m_name").val();
		var m_email = $("#m_email").val();
		
		var IMP = window.IMP; // 생략 가능
		IMP.init("imp72315021"); // 예: imp00000000a
		
		var today = new Date();   
        var hours = today.getHours(); // 시
        var minutes = today.getMinutes();  // 분
        var seconds = today.getSeconds();  // 초
        var milliseconds = today.getMilliseconds();
        var makeMerchantUid = hours +  minutes + seconds + milliseconds;
        
		//alert(d_no + ", " + amount);
		IMP.request_pay({
            pg: "kcp.INIpayTest",
            pay_method: "card",
            merchant_uid: "IMP"+makeMerchantUid, 
            name: "후원금액",
            amount: amount,
            buyer_email: m_email,
            buyer_name: m_name
          },
          function (rsp) {
    		    if (rsp.success) {
	    			var msg = '결제가 완료되었습니다.';
	    			msg += '\n고유ID : ' + rsp.imp_uid;
	    			msg += '\n상점 거래ID : ' + rsp.merchant_uid;
	    			msg += '\기부 금액 : ' + rsp.paid_amount;
	    			msg += '카드 승인번호 : ' + rsp.apply_num;
	    			
	    			alert(msg);
	    			let f = document.createElement('form');
	    		    
	    		    let obj;
	    		    obj = document.createElement('input');
	    		    obj.setAttribute('type', 'hidden');
	    		    obj.setAttribute('name', 'amount');
	    		    obj.setAttribute('value', amount);
	    		    
	    		    let obj2;
	    		    obj2 = document.createElement('input');
	    		    obj2.setAttribute('type', 'hidden');
	    		    obj2.setAttribute('name', 'd_no');
	    		    obj2.setAttribute('value', d_no);
	    		    
	    		    let obj3;
	    		    obj3 = document.createElement('input');
	    		    obj3.setAttribute('type', 'hidden');
	    		    obj3.setAttribute('name', 'd_regno');
	    		    obj3.setAttribute('value', rsp.imp_uid);
	    		    
	    		    f.appendChild(obj);
	    		    f.appendChild(obj2);
	    		    f.appendChild(obj3);
	    		    f.setAttribute('method', 'post');
	    		    f.setAttribute('action', 'donation');
	    		    document.body.appendChild(f);
	    		    f.submit();
	    		    
        	    } else {
        	      alert("결제에 실패하였습니다. 에러 내용: " + rsp.error_msg);
        	    }
        	    
       });

	}
</script>

<%@ include file="../footer.jsp"%>