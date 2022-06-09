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
							<!-- 일단 
							1.	1권만 표시 
							2. 사진도 표시
							3. 다른 권도 표시
							4. 페이징
							5. 수정 버튼 클릭하면 수정창으로 이동됨.
							-->
							<!-- 신착도서 리스트 시작 -->
							
							
							<div class="row justify-content-center">
							
								<c:forEach var = "booked" items ="${book}">
								
									<!-- 샘플 첫번쨰 -->
									<div class="col-lg-4 col-md-6 col-sm-6">
										<div class="team-item-wrap mb-5 mb-lg-0">
											<div class="portflio-item position-relative">
												<a href ="#" class="popup-gallery">
												
													<!--  책 이미지 -->
													<img src="/upload/${booked.titleImageUrl}" alt="" class="img-fluid w-100">
													<i class="ti-plus overlay-item"></i>
													<div class="portfolio-item-content">
														<h3 class="mb-0 text-white">수정</h3>
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
							
						</div>
					</div>
				</div>
				<!-- 우측 메인 End-->
				
						
				
				
				
				
	    	</div>
	    </div>
	</section>
</div>
<script src="/js/board_bookRegistration.js"></script>
<%@ include file="../layout/footer.jsp"%>
