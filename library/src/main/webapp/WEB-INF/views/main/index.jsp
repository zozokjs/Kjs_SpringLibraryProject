<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="main-wrapper ">
<!-- Slider Start -->
<section class="slider">
	<div class="container">
		<div class="row-custom">
			<div class="col-lg-9 col-md-10">
				<div class="block">
					<span class="d-block mb-3 text-white text-capitalize">Prepare for new future</span>
					<h1 class="animated fadeInUp mb-5">책과 함께하는 즐거움<br>토르두스 국립도서관 </h1>
					<input type="text"  id="searchKeyword" class="form-control mb-3" placeholder="검색어를 입력하세요">
					<a class="btn btn-main btn-small"  href="javascript:void(0);"  onclick="bookSearch()">검색</a>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- Section Intro Start -->

<!-- 도서대출예약 등 -->
<section class="section-custom service border-top">
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-lg-4 col-md-6 col-sm-6">
				<div class="service-item mb-5">
					<i class="ti-desktop"></i>
					<h3 class="mb-3">도서 등록</h3>
					<p>                          -                                 </p>
				</div>
			</div>

			<div class="col-lg-4 col-md-6 col-sm-6">
				<div class="service-item mb-5">
					<i class="ti-layers"></i>
					<h3 class="mb-3">도서반납연기</h3>
					<p>                          -                                 </p>
				</div>
			</div>

			<div class="col-lg-4 col-md-6 col-sm-6">
				<div class="service-item mb-5">
					<i class="ti-bar-chart"></i>
					<h3 class="mb-3">희망도서신청</h3>
					<p>                          -                                 </p>
				</div>
			</div>
<hr>
			<div class="col-lg-4 col-md-6 col-sm-6">
				<div class="service-item mb-5 mb-lg-0">
					<i class="ti-vector"></i>
					<h3 class="mb-3">자료실 좌석예약</h3>
					<p>                          -                                 </p>
				</div>
			</div>

			<div class="col-lg-4 col-md-6 col-sm-6">
				<div class="service-item mb-5 mb-lg-0">
					<i class="ti-android"></i>
					<h3 class="mb-3">전자도서관</h3>
					<p>                          -                                 </p>
				</div>
			</div>

			<div class="col-lg-4 col-md-6 col-sm-6">
				<div class="service-item mb-5 mb-lg-0">
					<i class="ti-pencil-alt"></i>
					<h3 class="mb-3">책값반환제</h3>
					<p>                          -                                 </p>
				</div>
			</div>
			
		</div>
	</div>
</section>


<!-- 신착도서 -->
<section class="section latest-blog bg-2">
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-lg-7 text-center">
				<div class="section-title">
					<span class="h6 text-color">신착 도서</span>
				</div>
			</div>
		</div>


		<!-- 신착도서 리스트 시작 -->
		<div class="row justify-content-center">
		<c:choose>
			<c:when test="${empty book}">
				<h3>등록된 책이 없습니다</h3>
			</c:when>
			<c:otherwise>
				<!-- 등록된 책이 있을 때 -->
<!-- 				<div class="team-item position-relative">
					등록된 책이 없습니다.
					<img src="/upload/noTitleImage.jpg">
				</div> -->
				<!--사진 반복 시작 -->
				<c:forEach var = "booked" items ="${book}">
				
					<div class="col-lg-4 col-md-6 col-sm-6">
						<div class="team-item-wrap mb-5 mb-lg-0">
							<div class="team-item position-relative">
								<img src="/upload/${booked.titleImageUrl}" alt="" class="img-fluid w-100">
							</div>
							<div class="team-item-content">
								<h3 class="mt-3 mb-5 lh-36"><a href="/" class="text-white">${booked.title}</a></h3>
							</div>
						</div>
					</div>
				</c:forEach>
			<!--사진 반복 끝 -->
			</c:otherwise>
			
		</c:choose>

			
			
			
		</div>
	</div>
</section>


<section class="section intro">
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-lg-4 col-md-6 col-12">
				<div class="intro-item mb-5 mb-lg-0"> 
					<i class="ti-desktop color-one"></i>
					<h4 class="mt-4 mb-3">지식의 교류</h4>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Odit, ducimus.</p>
				</div>
			</div>
			<div class="col-lg-4 col-md-6">
				<div class="intro-item mb-5 mb-lg-0">
					<i class="ti-medall color-one"></i> 
					<h4 class="mt-4 mb-3">문화의 발전</h4>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Odit, ducimus.</p>
				</div>
			</div>
			<div class="col-lg-4 col-md-6">
				<div class="intro-item">
					<i class="ti-layers-alt color-one"></i>
					<h4 class="mt-4 mb-3">모든 지성체를 위한 화합</h4>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Odit, ducimus.</p>
				</div>
			</div> 
		</div>
	</div>
</section>

</div>
<script src="/js_custom/main/index.js"></script>
<%@ include file="../layout/footer.jsp"%>