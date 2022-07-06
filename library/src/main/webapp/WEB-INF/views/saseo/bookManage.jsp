<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_Saseo.jsp"%>

	        
	            <!-- 우측 메인 -->
	            <div class="col-lg-8">
	               <div class="row">
						<div class="col-lg-12 mb-5">
							<div class="col-lg-7">
								<div class="">
									<h2 class="mt-3 content-title ">
									도서관리
									</h2>
								</div>
							</div>		
							<hr>	
							
							<button type="button" 
	                                    onclick="location.href='/saseo/bookRegistration'"
	                                    class="btn btn-primary btn-md btn-block waves-effect waves-light text-center m-b-20">새책등록</button>
							               	
							<!-- 본문 Start -->
							<div class="row justify-content-center">
				               	
								<c:forEach var = "booked" items ="${book.content}">
									<div class="col-lg-4 col-md-6 col-sm-6">
										<div class="team-item-wrap mb-5 mb-lg-0">
											<div class="portflio-item position-relative">
											
												<!-- 상세 페이지로 이동 -->
												<a href ="/saseo/${booked.id}/bookInfor" class="popup-gallery">
												
													<!--  책 이미지 -->
													<img src="/upload/${booked.titleImageUrl}" alt="" class="img-fluid w-100">
													<i class="ti-plus overlay-item"></i>
													<div class="portfolio-item-content">
														<h3 class="mb-0 text-white">상세정보</h3>
													</div>
												</a>
											</div>
											<div class="team-item-content">
												<h4 class="mt-3 mb-5 lh-36" style="text-align: center;">
													<!-- 책 제목 -->
													<a href="#" class="text-black">${booked.title}</a>
												</h4>
											</div>
										</div>
									</div>
								</c:forEach>
								
							</div>
							<!-- 본분 End -->
							
							
			                <!-- 페이지 버튼 Start -->
			                <div class="row justify-content-center mt-5">
				            	<div class="col-lg-12 text-center">
					            	<nav class="navigation pagination d-inline-block">
						                <div class="nav-links">
					                	
					                		<!-- 이전 버튼 -->
							                <c:choose>
												<c:when test="${book.first }">
													<%-- <a class="prev page-numbers disabled" href="?page=${book.number-1}">이전</a> --%>
												</c:when>
												<c:otherwise>
													<a class="prev page-numbers" href="?page=${book.number-1}">이전</a>
												</c:otherwise>
											</c:choose>
											
							                <%-- <c:forEach var="index" begin="0" end="${book.totalPages / 10}"> --%> <!--  58 -->
												
												
							                <c:forEach var="index" begin="${startPage}" end="${endPage}">
						                      	<c:choose>
													<c:when test="${book.number+1  eq index}" >
													   <span aria-current="page" class="page-numbers current">${index}</span>
													</c:when>
													<c:otherwise>
														<a class="page-numbers current" href="?page=${index-1}">${index}</a>
													</c:otherwise>
												</c:choose>
							                </c:forEach>
								          
								                
											
											<!-- 다음 버튼 -->
 											<c:choose>
												<c:when test="${book.last }">
													<%-- <a class="next page-numbers disabled"  href="?page=${book.number+1}">다음</a> --%>
												</c:when>
												<c:otherwise>
													<a class="next page-numbers disabled"  href="?page=${book.number+1}">다음</a>
												</c:otherwise>
											</c:choose>
										
						                </div>
					                </nav>
	                			</div>
			                </div>
   							<!-- 페이지 버튼 End -->									
						</div>
					</div>
				</div>
				<!-- 우측 메인 End-->
				
						
				
				
				
				
	    	</div>
	    </div>
	</section>
</div>
<%@ include file="../layout/footer.jsp"%>
