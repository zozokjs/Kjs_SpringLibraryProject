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
		font-weight:bold
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
</style>

<!-- main-cont 시작 -->
<div class="main-cont">


	<div>
	<!-- <!-- 	<div class="mp-slider search-only">
		// slider //
		<div class="mp-slider-row slim-slider">
			<div class="swiper-container">
				화살표
					<a href="#" class="arrow-left"></a> <a href="#" class="arrow-right"></a>
		 		<div class="swiper-pagination"></div>
				<div class="swiper-wrapper">
					<div class="swiper-slide">
						<div class="slide-section" style="background: url(img/sider-01.jpg) center top no-repeat;">
						<div style="display:flex; justify-content:center; align-items:center; height:807px; background: url(images/bg/books-2596809.jpg) center top no-repeat;">
						2400 x 807px;
						<div class="slide-section" style="background: url(images/bg/books-2596809.jpg) center top no-repeat;">
							<div class="mp-slider-lbl">봉라</div>
							<div class="mp-slider-lbl-a">Make Your Life Better and Bright! You must trip with Us!</div>
							<div style="display:flex; flex-direction:column;">
								<div class="" style="position:absolute; top:20px; left:50%; font-size:20px; z-index: 2;  text-align:center;">글자 글자 글자자자</div>
								<div class="mp-slider-btn">
									<a href="#" class="btn-a">Learn more</a>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
		\\ slider \\
	</div> -->
	</div>
	
	<!-- 배경그림 설정 2535px-->
	<div class="background_img_div">
		<!-- 글자 영역 -->
		<div class="background_text_div"  >
			<div>
			<!-- <div style="background-color: rgba(255, 255, 255, 0.5);  "> -->
				<div>책과 함께하는 즐거움!</div>
				<div>토르두스 국립도서관</div>
			</div>
		</div>
		
		<!-- 검색 영역 -->
		<!-- container2 -->
		<div class="background_search_div" >
			<!-- items2 -->					
			<div class="background_img_div_test_border"  style=" flex-grow:3; " >  </div>
			<!-- items2 -->
			<div class="background_img_div_test_border"  style="max-width:1100px;  flex-grow:3;  ">
				<div class="page-search-content" style="max-width:1100px; background-color: rgba(255, 255, 255, 0.5);  ">
					
						<!-- container -->
						<div class="page-search-p" style="display:flex; flex-direction:row; justify-content:center;"> 
							<!-- items -->
							<div class="search-large-i" style="  flex-grow:9;  ">
								<div class="srch-tab-line no-margin-bottom">
									<div class="input-a">
										<input type="text" style="font-size: 30px;" value="" placeholder="자료검색">
									</div>
								</div>
							</div>
							<!-- items -->
							<div  style="  flex-grow:0.3;   " >
								<a href="#" class="srch-btn" style="height: 45px; font-size: 30px;">검색</a>
							</div>
						</div>
						
				
				</div>
			</div>
			<!-- items2 -->
			<div class="background_img_div_test_border"  style=" flex-grow:3;  ">  </div>
			
		</div>
		
	</div>
	 
 
	
	
	
	<div class="mp-b">
		<div class="wrapper-padding">
			<div class="fly-in mp-b-left">
				<div class="mp-b-lbl">choose hotel by region</div>
				<!-- // regions // -->
				<div class="regions">
					<div class="regions-holder">
						<map id="imgmap201410281607" name="imgmap201410281607">
							<!--img alt="" usemap="#imgmap201410281607" width="347" height="177" src="img/world.png" id="imgmap201410281607">
									<area id="africa" shape="poly" alt="africa" title="" coords="183,153,173,129,176,115,170,107,163,97,145,98,138,85,141,75,149,63,161,58,169,57,173,56,172,61,182,65,185,62,199,65,204,77,211,89,212,92,222,92,221,96,210,110,207,117,221,125,217,141,203,138,192,152" href="" />
									<area id="asia" shape="poly" alt="asia" title="" coords="256,96,259,93,260,83,269,76,277,86,281,96,278,102,289,116,304,111,309,99,295,87,306,70,312,58,311,47,316,39,308,33,306,27,319,29,329,40,331,28,340,20,336,15,311,14,289,11,282,10,280,12,258,10,250,4,236,8,227,12,218,11,223,16,225,23,220,37,222,43,217,45,221,49,221,56,201,58,199,63,202,70,208,79,214,89,225,86,233,77,236,72,247,79" href="" />
									<area id="europe" shape="poly" alt="europe" title="" coords="191,56,177,55,170,46,157,56,149,54,157,38,171,31,168,20,183,11,197,14,220,16,220,32,218,42,213,47,219,55" href="" />
									<area id="austalia" shape="poly" alt="australia" title="" coords="302,155,315,150,322,153,327,162,335,161,342,154,342,108,328,103,321,110,326,119,313,128,297,138,296,151" href="" />
									<area id="north-america" shape="poly" alt="north_america" title="" coords="58,94,55,84,52,79,52,75,42,68,56,67,61,75,66,72,65,61,82,49,90,46,100,42,102,36,102,29,99,21,111,15,115,28,131,18,140,17,156,2,154,0,96,1,90,3,88,9,74,11,66,8,53,8,50,12,35,13,28,10,5,15,0,18,1,32,13,28,22,31,21,42,14,53,18,68,25,76,31,84,40,89" href="" />
									<area id="south-america" shape="poly" alt="south_america" title="" coords="62,102,68,89,81,92,99,101,99,106,105,109,118,113,117,122,113,126,110,140,103,143,97,156,88,165,75,169,71,137,70,131,56,121,54,113,56,106" href="" /-->
						</map>
						<div class="asia"></div>
						<div class="africa"></div>
						<div class="austalia"></div>
						<div class="europe"></div>
						<div class="north-america"></div>
						<div class="south-america"></div>
					</div>
				</div>
				<!-- // regions // -->
				<nav class="regions-nav">
					<ul>
						<li><a class="europe" href="#">Europe</a></li>
						<li><a class="asia" href="#">Asia</a></li>
						<li><a class="north-america" href="#">North america</a></li>
						<li><a class="south-america" href="#">south america</a></li>
						<li><a class="africa" href="#">africa</a></li>
						<li><a class="austalia" href="#">australia</a></li>
					</ul>
				</nav>
			</div>
			<div class="fly-in mp-b-right">
				<div class="mp-b-lbl">reasons to book with us</div>
				<div class="reasons-item-a">
					<div class="reasons-lbl">Awesome design</div>
					<div class="reasons-txt">Voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui.</div>
				</div>
				<div class="reasons-item-b">
					<div class="reasons-lbl">carefully handcrafted</div>
					<div class="reasons-txt">Voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui.</div>
				</div>
				<div class="clear"></div>
				<div class="reasons-item-c">
					<div class="reasons-lbl">fully responsive</div>
					<div class="reasons-txt">Voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui.</div>
				</div>
				<div class="reasons-item-d">
					<div class="reasons-lbl">fully responsive</div>
					<div class="reasons-txt">Voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui.</div>
				</div>
				<div class="clear"></div>
			</div>
			<div class="clear"></div>
		</div>
	</div>

	<!-- 2400 x 500 -->
	<!-- <div style="background: url(img/in2_popular.jpg) center top no-repeat;" class="last-order fly-in appeared"> -->
	 	<div style="background: url( images/bg/books_2400x500.jpg ) center top no-repeat;" class="last-order fly-in appeared">
			<div class="last-order-content">
				<div class="last-order-a fly-in appeared">Ropular Deal</div>
				<div class="last-order-b fly-in appeared">Grand hotel - london, england</div>
				<div class="last-order-c fly-in appeared">Fri 14 Now - Sun 16 Now</div>
				<div class="last-order-d fly-in appeared">300$</div>
				<a href="#" class="last-order-btn fly-in appeared">Book now</a>
			</div>
		</div>
		
	</div>
<!-- main-cont 끝 -->





<!-- <div class="block">
	<span class="d-block mb-3 text-white text-capitalize">Prepare for new future</span>
	<h1 class="animated fadeInUp mb-5">책과 함께하는 즐거움<br>토르두스 국립도서관 </h1>
	<input type="text"  id="searchKeyword" class="form-control mb-3" placeholder="검색어를 입력하세요">
	<a class="btn btn-main btn-small"  href="javascript:void(0);"  onclick="bookSearch()">검색</a>
</div> -->






<!-- 신착도서 리스트 시작 -->
<%-- <div class="row justify-content-center">
	<c:choose>
		<c:when test="${empty book}">
			<h3>등록된 책이 없습니다</h3>
		</c:when>
		<c:otherwise>

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
	
		</c:otherwise>
	</c:choose>
</div> --%>





<script src="/js_custom/main/index.js"></script>
<%@ include file="../layout/footer.jsp"%>