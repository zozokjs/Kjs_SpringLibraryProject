<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var ="principal"/>
	<!-- 
	isAuthenticated()는 세선에 접근하는 방식
	property = " principal "에서 principal은 정해진 키워드. UserDetails의 인스턴스를 출력한다고 공식문서에 적혀 있다.
	이것을 header에 명시했으므로 body에서는 principal.** 식으로 사용
	principal 내부에 user 객체가 있고 그 내부에 username 등이 있으므로
	principal.user.username 식으로 찾으면 된다.
	 -->
</sec:authorize>

<!DOCTYPE html>
<html lang="en">
<head>
<!-- <title>Sparrow | Travel Agency</title> -->
	<title>토르두스 국립도서관</title>
	<meta name="description" content="" />
	<meta name="keywords" content="" />
	<meta charset="utf-8" /><link rel="icon" href="favicon.png" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"> 
	
	<link rel="stylesheet" href="/css/jquery-ui.css">
	<link rel="stylesheet" href="/css/idangerous.swiper.css">
	<link rel="stylesheet" href="/css/owl.carousel.css">
	<link rel="stylesheet" href="/css/style.css" />
	
	<link href='http://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
	<link href='http://fonts.googleapis.com/css?family=Lora:400,400italic' rel='stylesheet' type='text/css'>
	<link href='http://fonts.googleapis.com/css?family=Raleway:300,400,500,700' rel='stylesheet' type='text/css'>  
	<link href='http://fonts.googleapis.com/css?family=PT+Sans:400,700&amp;subset=latin,cyrillic' rel='stylesheet' type='text/css'>	
	<link href='http://fonts.googleapis.com/css?family=Lato:400,700&amp;subset=latin,latin-ext' rel='stylesheet' type='text/css'>
	<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,600,700&amp;subset=latin,cyrillic' rel='stylesheet' type='text/css'>
	
</head>

<style>
	.subMenuText ul li ul li a {
		font-size:16px;
	}
</style>

<body>
<!-- // authorize // -->

	<!-- 팝업 메뉴 시작------------------------------------------------- -->
	<div class="overlay"></div>
	<div class="autorize-popup">
		<div class="autorize-tabs">
			<a href="#" class="autorize-tab-a current">로그인</a>
			<a href="#" class="autorize-tab-b">회원가입</a>
			<a href="#" class="autorize-close"></a>
			<div class="clear"></div>
		</div>
		<section class="autorize-tab-content">
			<div class="autorize-padding">
				<h6 class="autorize-lbl">Welocome! Login in to Your Accont</h6>
				<input type="text" value="" placeholder="Name">
				<input type="text" value="" placeholder="Password">
				<footer class="autorize-bottom">
					<button class="authorize-btn">Login</button>
					<a href="#" class="authorize-forget-pass">Forgot your password?</a>
					<div class="clear"></div>
				</footer>
			</div>
		</section>
		<section class="autorize-tab-content">
			<div class="autorize-padding">
				<h6 class="autorize-lbl">Register for Your Account</h6>
				<input type="text" value="" placeholder="Name">
				<input type="text" value="" placeholder="Password">
				<footer class="autorize-bottom">
					<button class="authorize-btn">Registration</button>
					<div class="clear"></div>
				</footer>
			</div>
		</section>
	</div>
	<!-- 팝업 메뉴 끝------------------------------------------------- -->
<!-- \\ authorize \\-->


<!-- Header Start --> 
<header id="top">
	<!-- 최상단 메뉴 시작--------------------------------------------------->
	<div class="header-a">
		<div class="wrapper-padding">			
<!-- 			<div class="header-phone">
			<span>토르두스 국립도서관</span>
			</div> -->
			<div class="header-account">
				
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
				<a href="/user/myLibrary" ><span>내서재</span></a>	
			</div>
			
			<div class="clear"></div>
		</div>
	</div>
	<!-- 최상단 메뉴 끝--------------------------------------------------->
	
	<!-- 상단 메뉴 시작--------------------------------------------------->
	<div class="header-b">
	
		<!-- // mobile menu // -->
		<div class="mobile-menu">
				<nav>
					<ul>
						<li><a class="has-child"  href="/guide/wayToHome">도서관이용안내</a>
							<ul>
								<li><a href="/guide/wayToHome">찾아오시는 길</a></li>
								<li><a href="/guide/guideSignup">회원가입 안내</a></li>
								<li><a href="/guide/closeTime">이용시간과 휴관일</a></li>
								<li><a href="/guide/infraUse">시설이용안내</a></li>
								<li><a href="/guide/loanReturnReserveExtension">대출/반납/예약/연장</a></li>
								<li><a href="/guide/donation">자료 기증</a></li>
								<li><a href="/guide/organizationChart">조직도 및 담당 업무</a></li>
							</ul>
						</li>		
						<li><a class="has-child"  href="#">공사중</a>
						</li>
						<li><a class="has-child"  href="/resource/bookSearch">자료검색</a>
							<ul>	
								<li><a href="/resource/bookSearch">통합검색</a></li>
								<li><a href="/resource/newBook">신착도서</a></li>
								<li><a href="/공사중">인기도서</a></li>
							</ul>
						</li>
						<li><a class="has-child"  href="/saseo/bookManage">사서공간</a>
							<ul>
								<li><a href="/saseo/bookManage">도서관리(등록/수정/삭제/권수수정/십진분류)</a></li>
								<li><a href="/saseo/bookRequestManage">희망도서관리(신청처리)</a></li>
								<li><a href="/saseo/signinRequest">회원관리(가입허가/정지/경고)</a></li>
								<li><a href="/saseo/infraManage">시설이용관리(신청처리)</a></li>
							</ul>
						</li>
						<li><a class="has-child"  href="/community/boardFree">열린공간</a>
							<ul>
								<li><a href="/community/boardFree">자유게시판</a></li>
								<li><a href="/community/boardNotice">공지사항</a></li>
								<li><a href="/community/manyQuestion">자주묻는질문</a></li>
								<li><a href="/community/singleQuestion">1대1질문하기</a></li>
							</ul>
						</li>
						<li><a class="has-child"  href="/user/myLibrary"">내서재</a>
							<ul>
								<li><a href="/user/userInfor/${principal.user.id}">회원정보</a></li>
								<li><a href="/user/myLibrary">대출관리(연장/반납)</a></li>
								<li><a href="/user/myLendHistory">반납완료내역</a></li>
								<li><a href="/공사중">희망도서신청관리</a></li>
								<li><a href="/공사중">작성글목록</a></li>
							</ul>
						</li>
						
					</ul>
				</nav>	
			</div>
		<!-- \\ mobile menu \\ -->
			
		<div class="wrapper-padding">
			<div class="header-logo">
				<a href="/">
					<img alt=""  style="height:47px; width:250px;" src="img_custom/로고_완성2(509x74).png" />
					<!-- 로고 관련 static > js > script.js에서 $headerUp과 $headerDown 찾기 -->
				</a>
			</div>
			
			<div class="header-right">

				<a href="#" class="menu-btn"></a>
				<nav class="header-nav subMenuText">
					<ul>
						<li><a href="/guide/wayToHome">도서관이용안내</a>
							<ul>
								<li><a href="/guide/wayToHome">찾아오시는 길</a></li>
								<li><a href="/guide/guideSignup">회원가입 안내</a></li>
								<li><a href="/guide/closeTime">이용시간과 휴관일</a></li>
								<li><a href="/guide/infraUse">시설이용안내</a></li>
								<li><a href="/guide/loanReturnReserveExtension">대출/반납/예약/연장</a></li>
								<li><a href="/guide/donation">자료 기증</a></li>
								<li><a href="/guide/organizationChart">조직도 및 담당 업무</a></li>
							</ul>
						</li>		
						<li><a href="#">공사중</a>
						</li>
						<li><a href="/resource/bookSearch">자료검색</a>
							<ul>	
								<li><a href="/resource/bookSearch">통합검색</a></li>
								<li><a href="/resource/newBook">신착도서</a></li>
								<li><a href="/공사중">인기도서</a></li>
							</ul>
						</li>
						<li><a href="/saseo/bookManage">사서공간</a>
							<ul>
								<li><a href="/saseo/bookManage">도서관리(등록/수정/삭제/권수수정/십진분류)</a></li>
								<li><a href="/saseo/bookRequestManage">희망도서관리(신청처리)</a></li>
								<li><a href="/saseo/signinRequest">회원관리(가입허가/정지/경고)</a></li>
								<li><a href="/saseo/infraManage">시설이용관리(신청처리)</a></li>
							</ul>
						</li>
						<li><a href="/community/boardFree">열린공간</a>
							<ul>
								<li><a href="/community/boardFree">자유게시판</a></li>
								<li><a href="/community/boardNotice">공지사항</a></li>
								<li><a href="/community/manyQuestion">자주묻는질문</a></li>
								<li><a href="/community/singleQuestion">1대1질문하기</a></li>
							</ul>
						</li>
						<li><a href="/user/myLibrary"">내서재</a>
							<ul>
								<li><a href="/user/userInfor/${principal.user.id}">회원정보</a></li>
								<li><a href="/user/myLibrary">대출관리(연장/반납)</a></li>
								<li><a href="/user/myLendHistory">반납완료내역</a></li>
								<li><a href="/공사중">희망도서신청관리</a></li>
								<li><a href="/공사중">작성글목록</a></li>
							</ul>
						</li>
					</ul>
				</nav>
			</div>
			<div class="clear"></div>
		</div>
	</div>	
	<!-- 상단 메뉴 끝------------------------------------------------- -->
</header>

<!-- Header Close --> 
