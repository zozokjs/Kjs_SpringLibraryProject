<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<style>
.background_img_div_test_border {
	/* border: 10px solid yellow; */
	
}

.background_img_div {
	/* border: 10px solid; */
	margin: 0 auto;
	padding: 130px 0px 0px 0px;
	display: flex;
	flex-direction: column;
	align-items: center;
	width: 95%;
	height: 600px;
	background: url(/img_custom/bg/books-1.jpg);
}

.background_text_div {
	/* border: 10px solid blue; */
	max-width: 1100px;
	width: 100%;
	padding: 170px 0px 0px 0px;
	text-align: center;
	font-size: 57px;
	text-transform: uppercase;
	font-family: 'Nanum Myeongjo', serif;
	width: 100%;
	height: 500px;
	color: antiquewhite;
	font-weight: bold
}

.background_search_div { 
	/* border: 10px solid green; */
	max-width: 1100px;
	width: 100%;
	height: 500px;
	display: flex;
	flex-direction: column;
	justify-content: center;
}

.main-Notice-Table-A-Tag {
	font-size: 20px;
	text-decoration: auto;
}

.main-Notice-Table-Div {
	font-size: 17px;
}

.referenceSite {
	width: 145px;
	height: 65px;
	display: flex;
}

.searchBox{
	height: 150px;
}


/*width가 800이상일 때 아래로 변경함*/
@media (min-width:800px) {
	.mp-slider-lbl-custom{
		 font-size: 50px;
		 font-family: 'Nanum Myeongjo', serif;
	    color: antiquewhite !important;
	      font-weight: bold;
	    text-shadow: -1px 0 gray, 0 1px gray, 1px 0 gray, 0 -1px gray;
	}
}

/*width가 800이하일 때 아래로 변경함*/
@media (max-width:800px) { 
	.mp-slider-lbl-custom{
		font-family: 'Nanum Myeongjo', serif;
	    color: antiquewhite !important;
	    font-weight: bold;
	    text-shadow: -1px 0 gray, 0 1px gray, 1px 0 gray, 0 -1px gray;
	}
	
	.searchBox{
		padding-top: 125px;
	}
}

</style>

<!-- main-cont 시작 -->
<div class="main-cont">

	<!-- 메인화면 슬라이딩 영역 시작  -->
	<div class="mp-slider">
		<!-- // slider // -->
		<div class="mp-slider-row">
			<div class="swiper-container" style="height: 600px;">
			    <div class="swiper-preloader-bg"></div>
			    <div id="preloader">
			    	<div id="spinner"></div>
			    </div>
				<a href="#" class="arrow-left"></a>
				<a href="#" class="arrow-right"></a>
				<div class="swiper-pagination"></div><!-- 이미지 슬라이드 좌우 화살표 -->
				
  				<div class="swiper-wrapper"><!-- 이미지  목록 시작-->	  				
      				<div class="swiper-slide">
						<div class="slide-section" style="background:url(/img_custom/bg/books-1.jpg) center top no-repeat;">
							<div class="mp-slider-lbl  mp-slider-lbl-custom">위대한 상상은 한 권의 작은 책으로부터 시작됩니다</div>
							<div class="mp-slider-lbl-a">A great imagination begins with a small book</div><!-- 영문 번역 -->
						</div>
      				</div>
      				
      				<div class="swiper-slide"> 
						<div class="slide-section slide-b" style="background:url(/img_custom/bg/books-2.jpg) center top no-repeat;">
							<div class="mp-slider-lbl  mp-slider-lbl-custom">세상의 모든 책이 이곳에 있습니다</div>
							<div class="mp-slider-lbl-a">All the books in the world are here</div>
						</div>
      				</div>

      				<div class="swiper-slide"> 
						<div class="slide-section slide-b" style="background:url(/img_custom/bg/books-3.jpg) center top no-repeat;">
							<div class="mp-slider-lbl  mp-slider-lbl-custom"">책은 가장 넒고 가까운 우주입니다</div>
							<div class="mp-slider-lbl-a">Books are the widest and closest universe</div>
						</div>
      				</div> 
       				<!-- 책 속에 길이 있습니다  / 책은 가장 영리한 벗입니다 -->
  				</div><!-- 이미지  목록 끝-->		
					
			</div>
		</div>
	</div>	
	<!-- 메인화면 슬라이딩 영역 끝  -->
	
	<!-- 검색 영역 시작   -->
	<div class="wrapper-a-holder full-width-search">
		<div class="wrapper-a">
	
			<div class="searchBox" >
			  <div class="search-type-padding">	
				<div class="page-search-content" style="max-width: 1100px; background-color: rgba(255, 255, 255, 0.5);">
						<div class="page-search-p"  style="height:73px; display:flex;">
						
							<!-- 검색 상자 -->
							<div class="search-large-i" style="flex-grow: 9;">
								<div class="srch-tab-line no-margin-bottom">
									<div class="input-a">
										<input type="text" id="searchKeyword" style="font-size: 20px;" value="" placeholder="자료검색">
									</div>
								</div>
							</div>
							
							<!-- 검색 버튼 -->
							<div style="flex-grow: 0.3;">
								<a href="javascript:void(0);" onclick="bookSearch()" class="srch-btn" style="height: 47px; font-size: 21px;">검색</a>
							</div>
							<!-- // -->							
						</div>
						<div class="clear"></div>
	
					</div>
					<!-- // tab content hotels // -->
							
				</div>
			</div>
		</div>
		<!-- \\ search \\ -->

		<div class="clear"></div>	
	 </div>
 	<!-- 검색 영역 끝   -->



	<!-- 신착도서 목록 -->
	<div class="pop-destinations">
		<header class="fly-in page-lbl">
			<b>신착 도서 </b>
		</header>

		<div class="mp-popular-row popular-flat">
			<c:choose>
				<c:when test="${empty book}">
					<h3>등록된 책이 없습니다</h3>
				</c:when>
				<c:otherwise>
					<!--사진 반복 시작 -->
					<c:forEach var="booked" items="${book}">
						<div class="offer-slider-i catalog-i fly-in ">
							<div class="imgTagContainer">

								<c:choose>
									<c:when test="${empty booked.titleImageUrl}">
										<a href="/resource/${booked.id}/bookInfor" class="offer-slider-img"> 
											<img src="/img_custom/noTitleImage_150px.jpg" alt="">
										</a>
									</c:when>
									<c:otherwise>
										<a href="/resource/${booked.id}/bookInfor" class="offer-slider-img"> 
											<img alt="" src="/upload/${booked.titleImageUrl}">
										</a>
									</c:otherwise>
								</c:choose>


							</div>
							<div class="offer-slider-txt">
								<div class="offer-slider-link">
									<a href="#">${booked.title}</a>
									<!-- Title -->
								</div>
								<div class="offer-slider-l">
									<div class="offer-slider-location">${booked.writer}</div>
									<!-- 저자 -->
								</div>
								<div class="clear"></div>
							</div>
						</div>

					</c:forEach>
					<!--사진 반복 끝 -->
				</c:otherwise>
			</c:choose>

			<!-- 반복 시작 -->
			<!-- // -->

			<!-- 반복 끝 -->




		</div>
		<!-- \\ -->
	</div>
	<div class="clear"></div>

	<!-- 신착도서 리스트 시작 -->
	<div class="row justify-content-center"></div>
	<!-- 신착도서 리스트 끝 -->


	<!-- 공지사항 -->
	<div class="">


		<div class="content-wrapper" style="margin-top: 50px;">

			<div class="typography" style="padding-bottom: 30px;">
				<div class="content-wrapper">
					<div class="block-qoutes">
						<div class="typography-heading" style="font-size: 20px;">공지사항</div>

							<table class="table-a light main-Notice-Table">
							<tbody>
								<tr>
									<td>
										<div>
											<a href="/community/${boardNotice[0].id}/noticeInfor" class="main-Notice-Table-A-Tag">${boardNotice[0].title}</a>
										</div>
										<div class="main-Notice-Table-Div">
											<fmt:parseDate value="${boardNotice[0].createDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
											<fmt:formatDate value="${parsedDateTime}" pattern="yyyy-MM-dd " />
										</div>
									</td>
									<td>
										<div>
											<a href="/community/${boardNotice[1].id}/noticeInfor" class="main-Notice-Table-A-Tag">${boardNotice[1].title}</a>
										</div>
										<div class="main-Notice-Table-Div">
											<fmt:parseDate value="${boardNotice[1].createDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
											<fmt:formatDate value="${parsedDateTime}" pattern="yyyy-MM-dd " />
										</div>
									</td>
								</tr>
								<tr>
									<td>
										<div>
											<a href="/community/${boardNotice[2].id}/noticeInfor" class="main-Notice-Table-A-Tag">${boardNotice[2].title}</a>
										</div>
										<div class="main-Notice-Table-Div">
											<fmt:parseDate value="${boardNotice[2].createDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
											<fmt:formatDate value="${parsedDateTime}" pattern="yyyy-MM-dd " />
										</div>
									</td>
									<td>
										<div>
											<a href="/community/${boardNotice[3].id}/noticeInfor" class="main-Notice-Table-A-Tag">${boardNotice[3].title}</a>
										</div>
										<div class="main-Notice-Table-Div">
											<fmt:parseDate value="${boardNotice[3].createDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
											<fmt:formatDate value="${parsedDateTime}" pattern="yyyy-MM-dd " />
										</div>
									</td>
								</tr>
								<tr>
									<td>
										<div>
											<a href="/community/${boardNotice[4].id}/noticeInfor" class="main-Notice-Table-A-Tag">${boardNotice[4].title}</a>
										</div>
										<div class="main-Notice-Table-Div">
											<fmt:parseDate value="${boardNotice[4].createDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
											<fmt:formatDate value="${parsedDateTime}" pattern="yyyy-MM-dd " />
										</div>
									</td>
									<td>
										<div>
											<a href="/community/${boardNotice[5].id}/noticeInfor" class="main-Notice-Table-A-Tag">${boardNotice[5].title}</a>
										</div>
										<div class="main-Notice-Table-Div">
											<fmt:parseDate value="${boardNotice[5].createDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
											<fmt:formatDate value="${parsedDateTime}" pattern="yyyy-MM-dd " />
										</div>
									</td>
								</tr>
								<tr>
									<td>
										<div>
											<a href="/community/${boardNotice[6].id}/noticeInfor" class="main-Notice-Table-A-Tag">${boardNotice[6].title}</a>
										</div>
										<div class="main-Notice-Table-Div">
											<fmt:parseDate value="${boardNotice[6].createDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
											<fmt:formatDate value="${parsedDateTime}" pattern="yyyy-MM-dd " />
										</div>
									</td>
									<td>
										<div>
											<a href="/community/${boardNotice[7].id}/noticeInfor" class="main-Notice-Table-A-Tag">${boardNotice[7].title}</a>
										</div>
										<div class="main-Notice-Table-Div">
											<fmt:parseDate value="${boardNotice[7].createDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
											<fmt:formatDate value="${parsedDateTime}" pattern="yyyy-MM-dd " />
										</div>
									</td>
								</tr>
								<tr>
									<td>
										<div>
											<a href="/community/${boardNotice[8].id}/noticeInfor" class="main-Notice-Table-A-Tag">${boardNotice[8].title}</a>
										</div>
										<div class="main-Notice-Table-Div">
											<fmt:parseDate value="${boardNotice[8].createDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
											<fmt:formatDate value="${parsedDateTime}" pattern="yyyy-MM-dd " />
										</div>
									</td>
									<td>
										<div>
											<a href="/community/${boardNotice[9].id}/noticeInfor" class="main-Notice-Table-A-Tag">${boardNotice[9].title}</a>
										</div>
										<div class="main-Notice-Table-Div">
											<fmt:parseDate value="${boardNotice[9].createDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
											<fmt:formatDate value="${parsedDateTime}" pattern="yyyy-MM-dd " />
										</div>
									</td>
								</tr>
							</tbody>
							</table>
					</div>
				</div>
			</div>

			<div style="text-align: center;">
				<button onclick="location.href='/community/boardNotice'" class="booking-complete-btn" style="display: inline-block; margin-bottom: 50px;">전체보기</button>
			</div>

			<div class="clear"></div>



		</div>
	</div>

	<!-- 관련 사이트 -->
	<div class="partners-wrapper">
		<header class="fly-in page-lbl appeared">
			<b>관련사이트</b>
			<div class="partners fly-in">
				<a href="https://www.nl.go.kr/seoji/"><img alt="" src="/img_custom/제국외도서관로고/국립중앙도서관ISBN납본시스템.jpg"></a> <a href="https://www.listopia.net/"><img alt="" src="/img_custom/제국외도서관로고/리스토피아.png"></a> <a href="https://library.busan.go.kr/portal/index.do"><img alt="" src="/img_custom/제국외도서관로고/부산광역시도서관포털.png"></a> <a href="https://home.pen.go.kr/siminlib/main.do"><img alt="" src="/img_custom/제국외도서관로고/부산시립시민도서관.png"></a> <a href="https://library.busan.go.kr/elib/index.do"><img alt="" src="/img_custom/제국외도서관로고/부산전자도서관.png"></a> <a href="https://library.cheongju.go.kr/lib/front/"><img alt="" src="/img_custom/제국외도서관로고/청주시립도서관.jpg"></a>
			</div>
		</header>
	</div>


</div>
<!-- main-cont 끝 -->




<script src="/js_custom/main/index.js"></script>
<%@ include file="../layout/footer.jsp"%>