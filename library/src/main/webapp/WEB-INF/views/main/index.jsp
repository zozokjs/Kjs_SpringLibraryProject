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
	background: url(images/bg/books-2596809.jpg);
}

.background_text_div {
	/* border: 10px solid blue; */
	max-width: 1100px;
	width: 100%;
	padding: 170px 0px 0px 0px;
	text-align: center;
	font-size: 57px;
	text-transform: uppercase;
	font-family: 'Montserrat';
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


.main-Notice-Table-A-Tag{
    font-size: 20px;
    text-decoration: auto;
}

.main-Notice-Table-Div{
    font-size: 17px;
}

.referenceSite{
width:145px;
height:65px;
display:flex;
}

</style>

<!-- main-cont 시작 -->
<div class="main-cont">


	<!-- 홈페이지 소개 및 검색창 표시 영역 -->
	<!-- 배경그림 설정 2535px-->
	<div class="background_img_div">
		<!-- 글자 영역 -->
		<div class="background_text_div">
			<div>
				<!-- <div style="background-color: rgba(255, 255, 255, 0.5);  "> -->
				<div>책과 함께하는 즐거움!</div>
				<div>토르두스 국립도서관</div>
			</div>
		</div>
		<!-- 검색 영역 -->
		<div class="background_search_div"><!-- container2 -->
			<div class="background_img_div_test_border" style="flex-grow: 3;"><!-- items2 -->
			</div>
			<div class="background_img_div_test_border" style="max-width: 1100px; flex-grow: 3;"><!-- items2 -->
				<div class="page-search-content" style="max-width: 1100px; background-color: rgba(255, 255, 255, 0.5);">
					
					<div class="page-search-p" style="display: flex; flex-direction: row; justify-content: center;"><!-- container -->
						<div class="search-large-i" style="flex-grow: 9;"><!-- items -->
							<div class="srch-tab-line no-margin-bottom">
								<div class="input-a">
									<input type="text"  id="searchKeyword" style="font-size: 30px;" value="" placeholder="자료검색">
								</div>
							</div>
						</div>
						<div style="flex-grow: 0.3;"><!-- items -->
							<a href="javascript:void(0);"  onclick="bookSearch()" class="srch-btn" style="height: 45px; font-size: 30px;">검색</a>
							
						</div>
					</div>
					
				</div>
			</div>
			<!-- items2 -->
			<div class="background_img_div_test_border" style="flex-grow: 3;">
			</div>
		</div>
	</div>
	

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
						<%--
						<div class="fly-in offer-slider-i">
							<div class="cat-list-item fly-in"   style="padding-top:15px;">
								<div class="cat-list-item-l">
										<!-- 이미지 크기  626 x 424 --> 
										<a href="/resource/${booked.id}/bookInfor"  class="offer-slider-img" >
											<c:choose>
												<c:when test="${empty booked.titleImageUrl}">
													<img class="mr-4" src="/img_custom//noTitleImage_150px.jpg" alt="">
												</c:when>
												<c:otherwise>
													<img class="mr-4" src="/upload/${booked.titleImageUrl}" alt="">  
												</c:otherwise>
											</c:choose>
										</a>
								</div>
							</div>
							<div class="offer-slider-txt">
								<div class="offer-slider-link">
									<!-- 책 제목 -->
									<a href="/">${booked.title}</a>
								</div>
								<div class="offer-slider-l">
									<!-- 작가 이름 -->
									<div class="offer-slider-location">${booked.writer}</div>
								</div>
								<div class="clear"></div>
							</div>
						</div> --%>

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
	<div class="row justify-content-center">
	</div>
	<!-- 신착도서 리스트 끝 -->
	
	
	<!-- 공지사항 -->
	<div class="">


		<div class="content-wrapper" style="margin-top: 50px;">

			<div class="typography" style="padding-bottom:30px;">
				<div class="content-wrapper">
					<div class="block-qoutes">
						<div class="typography-heading" style="font-size:20px;">공지사항</div>
						
						<table class="table-a light main-Notice-Table">
							<tbody>
									<tr>
										<td>
											<div>
												<a href="/community/${boardNotice[0].id}/noticeInfor" class="main-Notice-Table-A-Tag"     >${boardNotice[0].title}</a>
											</div>
											<div class="main-Notice-Table-Div">
												<fmt:parseDate value="${boardNotice[0].createDate}"   pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"  />
												 <fmt:formatDate value="${parsedDateTime}"   pattern="yyyy-MM-dd "/>
											</div>
										</td>
										<td>
											<div>
												<a href="/community/${boardNotice[1].id}/noticeInfor"   class="main-Notice-Table-A-Tag" >${boardNotice[1].title}</a>
											</div>
											<div class="main-Notice-Table-Div">
												<fmt:parseDate value="${boardNotice[1].createDate}"   pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"  />
												 <fmt:formatDate value="${parsedDateTime}"   pattern="yyyy-MM-dd "/>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<a href="/community/${boardNotice[2].id}/noticeInfor">${boardNotice[2].title}</a>
										</td>
										<td>
											<a href="/community/${boardNotice[3].id}/noticeInfor">${boardNotice[3].title}</a>
										</td>
									</tr>
									<tr>
										<td>
											<a href="/community/${boardNotice[4].id}/noticeInfor">${boardNotice[4].title}</a>
										</td>
										<td>
											<a href="/community/${boardNotice[5].id}/noticeInfor">${boardNotice[5].title}</a>
										</td>
									</tr>
									<tr>
										<td>
											<a href="/community/${boardNotice[6].id}/noticeInfor">${boardNotice[6].title}</a>
										</td>
										<td>
											<a href="/community/${boardNotice[7].id}/noticeInfor">${boardNotice[7].title}</a>
										</td>
									</tr>
									<tr>
										<td>
											<a href="/community/${boardNotice[8].id}/noticeInfor">${boardNotice[8].title}</a>
										</td>
										<td>
											<a href="/community/${boardNotice[9].id}/noticeInfor">${boardNotice[9].title}</a>
										</td>
									</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			
			<div style="text-align: center;">
				<button onclick="location.href='/community/boardNotice'" class="booking-complete-btn" style=" display: inline-block; margin-bottom:50px;">전체보기</button>
			</div>

			<div class="clear"></div>



		</div>
	</div>


	<div class="partners-wrapper">
		<header class="fly-in page-lbl appeared">
			<b>관련사이트</b>
			<div class="partners fly-in">
		    	<a href="https://www.nl.go.kr/seoji/"><img alt="" src="/img_custom/제국외도서관로고/국립중앙도서관ISBN납본시스템.jpg"></a>
		    	<a href="https://www.listopia.net/"><img alt="" src="/img_custom/제국외도서관로고/리스토피아.png"></a>
		    	<a href="https://library.busan.go.kr/portal/index.do"><img alt="" src="/img_custom/제국외도서관로고/부산광역시도서관포털.png"></a>
		    	<a href="https://home.pen.go.kr/siminlib/main.do"><img alt="" src="/img_custom/제국외도서관로고/부산시립시민도서관.png"></a>
		    	<a href="https://library.busan.go.kr/elib/index.do"><img alt="" src="/img_custom/제국외도서관로고/부산전자도서관.png"></a>
		    	<a href="https://library.cheongju.go.kr/lib/front/"><img alt="" src="/img_custom/제국외도서관로고/청주시립도서관.jpg"></a>
		  	  </div>
		</header>  
	   </div>
	

</div>
<!-- main-cont 끝 -->





<!-- <div class="block">
	<span class="d-block mb-3 text-white text-capitalize">Prepare for new future</span>
	<h1 class="animated fadeInUp mb-5">책과 함께하는 즐거움<br>토르두스 국립도서관 </h1>
	<input type="text"  id="searchKeyword" class="form-control mb-3" placeholder="검색어를 입력하세요">
	<a class="btn btn-main btn-small"  href="javascript:void(0);"  onclick="bookSearch()">검색</a>
</div> -->












<script src="/js_custom/main/index.js"></script>
<%@ include file="../layout/footer.jsp"%>