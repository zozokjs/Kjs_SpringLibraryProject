<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_Saseo.jsp"%>

<style>
	
	.offer-slider-link a{
		font-size:20px;
	}
	
	.offer-slider-location {
		font-size:14px;
	}

</style>
	        	<div class="two-colls-right"><!-- 우측 메인 시작 -->
					<div class="two-colls-right-b">
						<div class="padding">
							<div class="right-Submenu-2depth">
								도서관리
							</div>
							
							<div class="catalog-row">          
							 
								<c:forEach var = "booked" items ="${book.content}"><!-- 반복부 시작 --> 
						            <div class="offer-slider-i catalog-i fly-in ">
						            	<div class="imgTagContainer">
											<a href="/saseo/${booked.id}/bookInfor" class="offer-slider-img">
												<img alt=""  src="/upload/${booked.titleImageUrl}"><!-- 626 x 464 -->
												<span class="offer-slider-overlay">
													<span class="offer-slider-btn">view details</span>
													<span></span>
												</span>
											</a>
										</div>
										<div class="offer-slider-txt">
											<div class="offer-slider-link">
												<a href="#">${booked.title}</a><!-- Title -->
											</div>
											<div class="offer-slider-l">
												<div class="offer-slider-location">${booked.writer} </div><!-- 저자 -->
											</div>
											<div class="clear"></div>
										</div>
									</div>
								</c:forEach><!-- 반복부 끝 -->
								
							</div>
							
							<div class="clear"></div>
							<div style="text-align: -webkit-center;  margin-bottom:30px;">
								<button onclick="location.href='/saseo/bookRegistration'" class="white-btn-custom"  style="height:77px;">새책 등록</button>
							</div>
							
							
							<div class="pagination pagination-custom"  style="display:flex; justify-content:center;">	<!-- 페이지 버튼 시작 -->
								<!-- 이전 버튼 -->
				                <c:choose>
									<c:when test="${book.first }">
									</c:when>
									<c:otherwise>
										<a class="" href="?page=${book.number-1}">이전</a>
									</c:otherwise>
								</c:choose>
								
								<!-- 현재 페이지일 때 Active  -->
				                <c:forEach var="index" begin="${startPage}" end="${endPage}">
			                      	<c:choose>
										<c:when test="${book.number+1  eq index}" >
										   <a class="active" href="#">${index}</a>
										</c:when>
										<c:otherwise>
											<a class="" href="?page=${index-1}">${index}</a>
										</c:otherwise>
									</c:choose>
				                </c:forEach>
								
								<!-- 다음 버튼 -->
								<c:choose>
									<c:when test="${book.last }">
									</c:when>
									<c:otherwise>
										<a class=""  href="?page=${book.number+1}">다음</a>
									</c:otherwise>
								</c:choose>
									
								<div class="clear"></div>
							</div><!-- 페이지 버튼 끝 -->	
							<!-- 우측 본문 끝 -->
					
				
							</div><!-- end of class [ padding] -->
						<div class="clear" ></div>
					</div><!-- end of class [ two-colls-right-b ] -->
				</div><!-- 우측 메인 끝 --><!-- end of class [ two-colls-right ] -->
				<div class="clear"></div>
				
			</div><!-- end of class [ two-colls(submenu_guide) ] -->
			<div class="clear"></div><!-- 필수 -->
		</div><!-- end of class [ wrapper-padding ] -->
	</div><!-- end of class [ body-wrapper ] -->
</div><!-- /main-cont -->

<%@ include file="../layout/footer.jsp"%>
