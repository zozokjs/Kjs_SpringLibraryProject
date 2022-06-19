<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_Resource.jsp"%>

<!-- -----------------------------------------------------------

					자료 검색의 책 상세 정보 화면

 -->
	        
	            <!-- 우측 메인 -->
	            <div class="col-lg-8">
	               <div class="row">
						<div class="col-lg-12 mb-5">
						
							<div class="col-lg-7">
								<div class="">
									<h2 class="mt-3 content-title ">책 상세 내용</h2>
								</div>
							</div>		
							<hr>	
							
							<!-- 내용 Start-->
							<section class="blog-wrap bg-gray">
								<div class="container">
									<div class="row">
										<div class="col-lg-12 col-md-8 mb-1">

											<!-- 상세정보 시작 -->
					       					<div class="media border-bottom py-3">
												<img class="mr-4" src="/upload/noTitleImage_150px.jpg" alt="">
												<div class="media-body">
													<h4 class="my-2">${book.title}</h4>
													<h6 class="my-2">저자 : ${book.writer} |  발행처: ${book.publish}</h6>
													<h6 class="my-2">언어 : ${book.language} |  출판일 : ${book.publishDate}</h6>
													<h6 class="my-2">대출가능여부 : 몰라영 |  대출 : 5/5  </h6>
													<h6 class="my-2">페이지 : ${book.page} | 크기 : ${book.size}</h6>
													<h6 class="my-2">제본형식 : ${book.bindType} | 도서 분류 : ${book.kdcTable}</h6>
													<h6 class="my-2">좋아요 : ?개</h6>
													<button onclick="bookLending(${book.id},event)">대출</button> 
													<button onclick="location.href='/'     ">좋아용</button> 
													<button onclick="location.href='/'     ">관심도서 추가</button>
												</div>
											</div>
											<!-- 줄거리 -->
											<div>
											<p>${book.contents}</p>

											</div>
											<!-- 상세정보 끝 -->
													
										</div>
									</div>
								</div>
							</section>
						</div>
					</div>
				</div>
				<!-- 우측 메인 End-->
				
	    	</div>
	    </div>
	</section>
</div>
<script src="/js_custom/resource_bookInfor.js"></script>
<%@ include file="../layout/footer.jsp"%>
