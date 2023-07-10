<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>

<div class="container">
	<div class="row">
		<div class="col-md-12">
			<div class="fh5co-form animate-box" data-animate-effect="fadeInLeft">
				<div class="wGroup">
					<button class="btn btn-outline-primary admin" onclick="">회원관리</button>
					<button class="btn btn-outline-primary admin" onclick="">봉사관리</button>
					<button class="btn btn-outline-primary admin" onclick="">기부관리</button>
				</div>
				<div class="wGroup myDiv">
					<div>
						<canvas id="myChart"></canvas>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
	<script type="text/javascript">
		$(function() {
			$.getJSON("count.getJSON", function(data) {
				//alert(JSON.stringify(data));
				let ar = [];
				let l = [];
				let d = [];

				for ( var key in data) {
					//alert("count : " + data[key].COUNT + ", date : " + data[key].V_REGDATE)

					ar[key] = {
						label : data[key].V_REGDATE,
						y : data[key].COUNT
					}
					
					l[key] = [data[key].V_REGDATE]
					d[key] = [data[key].COUNT]
				}
				
				const ctx = document.getElementById('myChart');

				  new Chart(ctx, {
				    type: 'bar',
				    data: {
				      labels: l,
				      datasets: [{
				        label:'일별 신규 등록 봉사 게시글 수',
				        data: d,
				        backgroundColor: 'rgba(75, 192, 192, 0.2)',
				        borderColor: 'rgb(75, 192, 192)',
				        borderWidth: 1
				      }]
				    },
				    options: {
				      scales: {
				        y: {
				          beginAtZero: true,
				          ticks: {
				              // forces step size to be 1 units
				              stepSize: 1
				          }
				        }
				      }
				    }
				  });
			});

		});
	</script>
	<%@ include file="footer.jsp"%>