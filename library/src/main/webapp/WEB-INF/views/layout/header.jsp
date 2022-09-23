<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
	<!-- Google Analytics를 위한 tag (gtag.js) 시작 -->
	<script async src="https://www.googletagmanager.com/gtag/js?id=G-WT7DNH7MEZ"></script>
	<script>
	  window.dataLayer = window.dataLayer || [];
	  function gtag(){dataLayer.push(arguments);}
	  gtag('js', new Date());
	
	  gtag('config', 'G-WT7DNH7MEZ');
	</script>
	<!-- Google Analytics를 위한 tag (gtag.js) 끝 -->
		
	<!-- 날짜 라이브러리 -->
	<script src="https://cdn.jsdelivr.net/npm/dayjs@1/dayjs.min.js"></script>
	
	<title>토르두스 국립도서관</title>
	<meta name="description" content="" />
	<meta name="keywords" content="" />
	<meta charset="utf-8" /><link rel="icon" href="/img_custom/favicon-16x16.png" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"> 
	
	<!-- 	<meta name="csrf-token" content="{{#_csrf}}token{{/_csrf}}"> -->
	<meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
	
	
	<!-- Style Custom Start-->
	
	<link rel="stylesheet" href="/css/jquery-ui.css">
	<link rel="stylesheet" href="/css/idangerous.swiper.css">
	<link rel="stylesheet" href="/css/owl.carousel.css">
	<link rel="stylesheet" href="/css/style.css" />
	<link rel="stylesheet" href="/css_custom/style_custom.css" />
	
	<link rel="stylesheet" href="/css_custom/auth/findToIdPassword.css" />
	<link rel="stylesheet" href="/css_custom/auth/resetPassword.css" />	


	<link rel="stylesheet" href="/css_custom/auth/signupSuccess.css" />
	
	<link rel="stylesheet" href="/css_custom/layout/header.css" />
	
	<link rel="stylesheet" href="/css_custom/community/boardFree.css" />

	<link rel="stylesheet" href="/css_custom/community/boardNotice.css" />
	
	<!-- Style Custom End-->
	
	
	<!-- 구글 한글 폰트 추가 -->
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
	<link href="https://fonts.googleapis.com/css2?family=Nanum+Myeongjo&display=swap" rel="stylesheet">
	
	<link href='https://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
	<link href='https://fonts.googleapis.com/css?family=Lora:400,400italic' rel='stylesheet' type='text/css'>
	<link href='https://fonts.googleapis.com/css?family=Raleway:300,400,500,700' rel='stylesheet' type='text/css'>  
	<link href='https://fonts.googleapis.com/css?family=PT+Sans:400,700&amp;subset=latin,cyrillic' rel='stylesheet' type='text/css'>	
	<link href='https://fonts.googleapis.com/css?family=Lato:400,700&amp;subset=latin,latin-ext' rel='stylesheet' type='text/css'>
	<link href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700&amp;subset=latin,cyrillic' rel='stylesheet' type='text/css'>
	
	<!-- 부트스크랩 아이콘을 가져오기 위한 CDN -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
</head>

<style>
 	.oauth_kakao{
 		height: 54px;
 		width: 40%; 
 		margin:7px;
 	}
	
	.oauth_naver{
		height: 55px;
		width: 40%; 
		margin:7px;
	}
</style>

<body>

<!-- // authorize // -->

	<!-- 팝업 시작------------------------------------------------- -->
	<div class="overlay"></div>
	<div class="autorize-popup"  style="height:300px;"><!--popup-content  -->
		<div class="autorize-tabs">
			<a href="#" class="autorize-tab-a current">로그인</a>
			<a href="#" class="autorize-close"></a>
			<div class="clear"></div>
		</div>
		
		
		<section class="autorize-tab-content">
			<form action ="/auth/signin" method="POST" >
				<input type="hidden"  name="${_csrf.parameterName}"  value="${_csrf.token}"  /><!-- CSRF 토큰 적용 -->
				<div class="autorize-padding">
					<input type="text"  name="username"     class="form-control"  placeholder="아이디" required="">
					<input type="password"  name="password"    class="form-control" placeholder="비밀번호" required="">
					<footer class="autorize-bottom">
						<button class="authorize-btn" >로그인</button>
						<a href="/auth/findToIdPassword" class="authorize-forget-pass">아이디 | 비밀번호찾기</a> 
						<a href="/auth/signup" class="authorize-forget-pass">회원가입</a>
						<div class="clear"></div>
					</footer>
				</div>
			</form>
			<div style="text-align:center;">
				<!-- 카카오 로그인 -->
				<a href="/oauth2/authorization/kakao"><!-- yml 파일의 authorization-uri에 설정된 주소가 요청됨 -->
					<img alt=""  class="oauth_kakao"   src="/img_custom/oauth/kakao_login.png" />
				</a>
				<!-- 네이버 로그인 ... 구현 완료... 승인 대기 중
				<a href="/oauth2/authorization/naver">
					<img alt=""  class="oauth_naver"  src="/img_custom/oauth/naver_login.png" />
				</a>-->
			</div>
		</section>
		


	</div>
	<!-- 팝업 끝------------------------------------------------- -->
<!-- \\ authorize \\-->


<!-- Header Start --> 
<header id="top">
	<!-- 최상단 메뉴 시작--------------------------------------------------->
	<div class="header-a">
		<div class="wrapper-padding">	
		
			<div class="header-account header_information_left" style="float:left;">
				<span >제국력  <span id="ID"></span> | </span>
				<span>서력 </span>	<span id="AD"></span>
			</div>		
			
			<div class="header-account  header_information_right">
				<a href="/"  class=""><span>홈으로</span></a>		
				<!-- 로그인 한 상태라면 사용자 이름 표시되어야 함 -->
				<c:choose>
				 	<c:when test ="${principal.user eq null}">
						<!-- 로그인 버튼에 팝업 이벤트 달려 있음(header-account-Login-button로 검색)-->
						<a href="/auth/signin"  class="header-account-Login-button "   ><span>로그인</span></a>
						<a href="/auth/signup"  class=""><span>회원가입</span></a>		
					 </c:when>
					<c:otherwise> 
						<a href="/"  class=""> <span>${principal.user.username}님</span></a>	
						<a href="#" onclick="javascript:btnClick(logoutAskOne);"><span>로그아웃</span></a>
						<form name="logoutAskOne" action="/logout" method="post">
							<sec:csrfInput/><!-- CSRF 토큰 적용 -->
						</form>
						<a href="/user/myLibrary"  class=""> <span>내서재</span></a>		
					</c:otherwise>
				</c:choose> 
			</div> 
			
			
			
			<div class="clear"></div>
		</div>
	</div>
	<!-- 최상단 메뉴 끝--------------------------------------------------->

	<!-- 상단 메뉴 시작--------------------------------------------------->
	<div class="header-b">

		<!-- 화면이 모바일 형태일 때의 메뉴를 표시함-->
		<div class="mobile-menu">
			<nav>
				<ul>
					<!-- 1 -->
					<li><a class="has-child"  href="/guide/wayToHome">도서관이용안내</a>
						<ul>
							<li><a href="/guide/wayToHome">찾아오시는 길</a></li>
							<li><a href="/guide/informationUse">도서관 이용안내</a></li>
							<li><a href="/guide/infraUse">시설이용안내</a></li>
							<li><a href="/guide/organizationChart">조직도 및 담당 업무</a></li>
						</ul>
					</li>
					
					<!-- 2 -->
					<li><a class="has-child"  href="/resource/bookSearch">자료검색</a>
						<ul>	
							<li><a href="/resource/bookSearch">통합검색</a></li>
							<li><a href="/resource/newBook">신착도서</a></li>
						</ul>
					</li>
					
					<!-- 3 -->
					<c:choose>
						<c:when test ="${principal.user.roleType eq 'ADMIN' || principal.user.roleType eq 'SASEO' }">	
						<li><a class="has-child"  href="/saseo/bookManage">사서공간</a>
							<ul>
								<li><a href="/saseo/bookManage">도서관리(등록/수정/삭제/권수수정/십진분류)</a></li>
								<li><a href="/saseo/bookRequestManage">희망도서관리(신청처리)</a></li>
								<li><a href="/saseo/signinRequest">회원관리(가입허가/정지/경고)</a></li>
							</ul>
						</li>
						</c:when>
					</c:choose>
					
					<!-- 4 -->
					<li><a class="has-child"  href="/community/boardFree">열린공간</a>
						<ul>
							<li><a href="/community/boardFree">자유게시판</a></li>
							<li><a href="/community/boardNotice">공지사항</a></li>
							<li><a href="/community/manyQuestion">자주묻는질문</a></li>
							<li><a href="/community/singleQuestion">1대1질문하기</a></li>
						</ul>
					</li>
					
					<!-- 5 -->
					<li><a class="has-child"  href="/user/myLibrary"">내서재</a>
						<ul>
							<li><a href="/user/userInfor/${principal.user.id}">회원정보</a></li>
							<li><a href="/user/myLibrary">대출관리(연장/반납)</a></li>
							<li><a href="/user/myLendHistory">반납완료내역</a></li>
							<li><a href="/user/myBoardHistory">작성글목록</a></li>
						</ul>
					</li>
				</ul>
					
			</nav>	
		</div>
		<!-- \\ mobile menu \\ -->
		<div class="wrapper-padding">
			<div class="header-logo">
				<a href="/">
					<img alt=""  style="height:47px; width:250px;" src="/img_custom/로고_완성3.png" />
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
								<li><a href="/guide/informationUse">도서관 이용안내</a></li>
								<li><a href="/guide/infraUse">시설이용안내</a></li>
								<li><a href="/guide/organizationChart">조직도 및 담당 업무</a></li>
							</ul>
						</li>		
<!-- 						<li><a href="/auth/signupSuccess">공사중</a> -->
						</li>
						<li><a href="/resource/bookSearch">자료검색</a>
							<ul>	
								<li><a href="/resource/bookSearch">통합검색</a></li>
								<li><a href="/resource/newBook">신착도서</a></li>
<!-- 								<li><a href="/공사중">인기도서</a></li> -->
							</ul>
						</li>

						<!-- 사서 권한만 볼 수 있음  원본-->
						<c:choose>
							<c:when test ="${principal.user.roleType eq 'ADMIN' || principal.user.roleType eq 'SASEO' }">	
								<li><a href="/saseo/bookManage">사서공간</a>
									<ul>
										<li><a href="/saseo/bookManage">도서관리(등록/수정/삭제/권수수정/십진분류)</a></li>
										<li><a href="/saseo/bookRequestManage">희망도서관리(신청처리)</a></li>
										<li><a href="/saseo/signinRequest">회원관리(가입허가/정지/경고)</a></li>
										<li><a href="/saseo/infraManage">시설이용관리(신청처리)</a></li>
									</ul>
								</li>
							</c:when>
						</c:choose>
						
						<li><a href="/community/boardFree">열린공간</a>
							<ul>
								<li><a href="/community/boardFree">자유게시판</a></li>
								<li><a href="/community/boardNotice">공지사항</a></li>
								<li><a href="/community/manyQuestion">자주묻는질문</a></li>
								<li><a href="/community/singleQuestion">1대1질문하기</a></li>
							</ul>
						</li>
						<li>
							<a href="/user/myLibrary"">내서재</a>
							<ul>
								<li><a href="/user/userInfor/${principal.user.id}">회원정보</a></li>
								<li><a href="/user/myLibrary">대출관리(연장/반납)</a></li>
								<li><a href="/user/myLendHistory">반납완료내역</a></li>
								<li><a href="/user/myBoardHistory">작성글목록</a></li>
 								<!-- <li><a href="/공사중">희망도서신청관리</a></li> -->
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
<script src="/js_custom/layout/header.js"></script>
<!-- Header Close --> 
