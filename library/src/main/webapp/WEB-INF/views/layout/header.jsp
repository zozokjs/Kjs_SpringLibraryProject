<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var ="principal"/>
	<!-- isAuthenticated()로 세션 접근 가능
	property="principal"는 정해진 키워드.
	principal.user.~로 찾을 수 있음	
	 -->
</sec:authorize>

<!doctype html>
<html lang="en">
  <head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="megakit,business,company,agency,multipurpose,modern,bootstrap4">
  
  <meta name="author" content="themefisher.com">

  <title>토르두스 국립도서관</title>

  <!-- bootstrap.min css -->
  <link rel="stylesheet" href="/plugins/bootstrap/css/bootstrap.min.css">
  <!-- Icon Font Css -->
  <link rel="stylesheet" href="/plugins/themify/css/themify-icons.css">
  <link rel="stylesheet" href="/plugins/fontawesome/css/all.css">
  <link rel="stylesheet" href="/plugins/magnific-popup/dist/magnific-popup.css">
  <!-- Owl Carousel CSS -->
  <link rel="stylesheet" href="/plugins/slick-carousel/slick/slick.css">
  <link rel="stylesheet" href="/plugins/slick-carousel/slick/slick-theme.css">

  <!-- Main Stylesheet -->
  <link rel="stylesheet" href="/css/style.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>


	<!-- other TEMPLATE -->


</head>

<body>


<!-- Header Start --> 

<header class="navigation">
	<div class="header-top ">
		<div class="container">
			<div class="row justify-content-between align-items-center">
				<div class="col-lg-2 col-md-4">
					<div class="header-top-socials text-center text-lg-left text-md-left">
						<a href="https://www.facebook.com/themefisher" target="_blank"><i class="ti-facebook"></i></a>
						<a href="https://twitter.com/themefisher" target="_blank"><i class="ti-twitter"></i></a>
						<a href="https://github.com/themefisher/" target="_blank"><i class="ti-github"></i></a>
					</div>
				</div>
				<div style="font-family: sans-serif;" class="col-lg-10 col-md-8 text-center text-lg-right text-md-right">
					<div class="header-top-info">
						<a href="/" ><span>홈으로</span></a>		
						<!-- 로그인 한 상태라면 사용자 이름 표시되어야 함 -->		
						<c:choose>
							<c:when test ="${principal.user eq null}">
								<a href="/auth/signin" ><span>로그인</span></a>	
							</c:when>
							<c:otherwise>
								<a href="" ><span>	${principal.user.username}님</span></a>	
								<a href="/logout" ><span>로그아웃</span></a>		
							</c:otherwise>
						</c:choose>
						<a href="" ><span>내서재</span></a>						
					</div>
				</div>
			</div>
		</div>
	</div>
	<nav class="navbar navbar-expand-lg  py-4" id="navbar">
		<div class="container">
		  <a class="navbar-brand" href="/">
		  	토르두스 국립<span>도서관</span>
		  </a>

		  <button class="navbar-toggler collapsed" type="button" data-toggle="collapse" data-target="#navbarsExample09" aria-controls="navbarsExample09" aria-expanded="false" aria-label="Toggle navigation">
			<span class="fa fa-bars"></span>
		  </button>
	  
		  <div class="collapse navbar-collapse text-center" id="navbarsExample09">
			<ul class="navbar-nav ml-auto">
<!-- 			  <li class="nav-item active">
				<a class="nav-link dropdown"  href="index.html">도서관 이용안내 <span class="sr-only">(current)</span></a>
			  </li> -->
  			  <li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="dropdown03" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">도서관 이용안내</a>
					<ul class="dropdown-menu" aria-labelledby="dropdown03">
						<li><a class="dropdown-item" href="pricing.html">게시판</a></li>
						<li><a class="dropdown-item" href="pricing.html">찾아오시는 길</a></li>
						<li><a class="dropdown-item" href="about.html">가입 안내</a></li>
						<li><a class="dropdown-item" href="pricing.html">이용시간과 휴관일</a></li>
						<li><a class="dropdown-item" href="pricing.html">시설이용안내</a></li>
						<li><a class="dropdown-item" href="pricing.html">대출/반납/예약/연장</a></li>
						<li><a class="dropdown-item" href="pricing.html">자료 기증</a></li>
						<li><a class="dropdown-item" href="pricing.html">자료실 좌석 예약</a></li>
						<li><a class="dropdown-item" href="pricing.html">찾아오시는 길</a></li>
						<li><a class="dropdown-item" href="pricing.html">도서관 현황</a></li>
						<li><a class="dropdown-item" href="pricing.html">조직도 및 담당 업무</a></li>
					</ul>
			  </li>
			  
			  <!-- 샘플 -->
		   		<li class="nav-item"><a class="nav-link" href="/board/board_bookRegistration">Sample</a></li>
			  <!-- 자료 검색 -->
   			  <li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="/board/boardSample2" id="dropdown03" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">자료검색</a>
					<ul class="dropdown-menu" aria-labelledby="dropdown03">
						<li><a class="dropdown-item" href="/board/boardSample2">Our company</a></li>
						<li><a class="dropdown-item" href="pricing.html">Pricing</a></li>
					</ul>
			  </li>
  			  <li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="dropdown03" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">전자도서관</a>
					<ul class="dropdown-menu" aria-labelledby="dropdown03">
						<li><a class="dropdown-item" href="about.html">Our company</a></li>
						<li><a class="dropdown-item" href="pricing.html">Pricing</a></li>
					</ul>
			  </li>
  			  <li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="dropdown03" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">열린공간</a>
					<ul class="dropdown-menu" aria-labelledby="dropdown03">
						<li><a class="dropdown-item" href="about.html">Our company</a></li>
						<li><a class="dropdown-item" href="pricing.html">Pricing</a></li>
					</ul>
			  </li>
			   <li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="dropdown05" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">내서재</a>
					<ul class="dropdown-menu" aria-labelledby="dropdown05">
						<li><a class="dropdown-item" href="blog-grid.html">Blog Grid</a></li>
						<li><a class="dropdown-item" href="blog-sidebar.html">Blog with Sidebar</a></li>

						<li><a class="dropdown-item" href="blog-single.html">Blog Single</a></li>
					</ul>
			  </li>
			   <li class="nav-item"><a class="nav-link" href="contact.html">Contact</a></li>
			</ul>

			<form class="form-lg-inline my-2 my-md-0 ml-lg-4 text-center">
			  <a href="/auth/signin" class="btn btn-solid-border btn-round-full">로그인</a>
			</form>
		  </div>
		</div>
	</nav>
</header>

<!-- Header Close --> 
