<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="header.jsp"%>

<div class="container">
	<div class="row">
		<div class="col-md-8">
			<div class="fh5co-form animate-box" data-animate-effect="fadeInLeft">
				<h2>Would you like to volunteer with us?</h2>
				<a class="more" href="list">more →</a>
				<div class="main">
					<!-- 시작 -->
					<div id="carouselExampleControls" class="carousel slide"
						data-bs-ride="carousel">
						<div class="carousel-inner">
							<div class="carousel-item active">
								<img class="d-block w-100"
									src="resources/images/WWGN-001.jpg" />
								
							</div>
							<div class="carousel-item">
								<a href="donation">
									<img class="d-block w-100"
										src="resources/images/WWGN-002.png" />
									
								</a>
							</div>
						</div>
						<button class="carousel-control-prev" type="button"
							data-bs-target="#carouselExampleControls" data-bs-slide="prev">
							<span class="carousel-control-prev-icon" aria-hidden="true"></span>
							<span class="visually-hidden">Previous</span>
						</button>
						<button class="carousel-control-next" type="button"
							data-bs-target="#carouselExampleControls" data-bs-slide="next">
							<span class="carousel-control-next-icon" aria-hidden="true"></span>
							<span class="visually-hidden">Next</span>
						</button>
					</div>
					<!-- 끝 -->
				</div>
			</div>


		</div>
		<div class="col-md-4">
			<jsp:include page="${lp }"></jsp:include>
		</div>
	</div>


	<%@ include file="footer.jsp"%>