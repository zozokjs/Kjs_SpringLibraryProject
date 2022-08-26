<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_Resource.jsp"%>

<!-- -----------------------------------------------------------

					자료 검색의 책 상세 정보 화면

 -->
 <style>

	
	 .table-a td{
	 	font-size:15px;
	 }

 </style>
	        
				<div class="two-colls-right"><!-- 우측 메인 시작 -->
					<div class="two-colls-right-b">
						<div class="padding">
							<div class="right-Submenu-2depth">
								통합검색(책 상세 화면)
							</div>
							<div class="catalog-row list-rows">
								<div class="cat-list-item fly-in"   style="padding-top:15px;">
									<div class="cat-list-item-l">
										<a href="#">
											<img class="mr-4" src="/upload/noTitleImage_150px.jpg" alt="">  <!-- 타이틀 이미지 -->
										</a>
									</div>
									<div class="cat-list-item-r"><!-- 여기부터 콘텐츠 영역 -->
										<div class="cat-list-item-rb"  style="margin: 20px 0;">
											<div class="cat-list-item-p">
												<div class="cat-list-content">
													<div class="cat-list-content-a">
														<div class="cat-list-content-l"  style="margin-right:0px; ">
															<div class="cat-list-content-lb">
															
																<div class="cat-list-content-lpadding"><!--  내용 시작 -->
																	<div class="tables" >
																	
																		<table class="table-a light">
																			<tr>
																				<td>제목</td>
																				<td colspan="3">${book.title}</td>
																			</tr>
																			<tr>
																				<td>저자</td>
																				<td colspan="3">${book.writer}</td>
																			</tr>
																			<tr>
																				<td>발행처</td>
																				<td>${book.publish}</td>
																				<td>발행일</td>
																				<td>${book.publishDate}</td>
																			</tr>
																			<tr>
																				<td>매체구분</td>
																				<td>${books.bindType}</td>
																				<td>페이지</td>
																				<td>${book.page}</td>
																			</tr>
																			<tr>
																				<td>ISBN</td>
																				<td>${book.isbn}</td>
																				<td>ISBN SET</td>
																				<td>${book.isbnSet}</td>
																			</tr>
																			<tr>
																				<td>대여가능수</td>
																				<td colspan="3">${book.remainAmount} / ${book.totalAmount}</td>
																			</tr>
																			
																			<tr>
																				<td  colspan="4">
																					<div style="text-align: -webkit-center;">
																						<button class=" booking-complete-btn white-btn-custom"  onclick="bookLending(${book.id},event)" >대출하기</button>
																					</div>
																				</td>
																			</tr>
																			
																		</table>
																		
																	</div>	
																</div><!-- 내용 끝 -->
																
															</div>
															<br class="clear" />
														</div>
													</div>
													
													<div class="clear"></div>
												</div>
											</div>
										</div>
										<br class="clear" />
									</div>
									
<!-- 									<div>
										<div style="text-align: center;">
											<button onclick="location.href='/'" class="booking-complete-btn" style="display: inline-block; margin-bottom: 50px;">홈으로</button>
										</div>
									</div> -->
									
									<div class="typography" style="padding-bottom:30px;">
										<div class="content-wrapper">
											<div class="block-qoutes">
												<div class="typography-heading" style="font-size:20px;">줄거리</div>
												
												<div class="blockqoute-tp-a" style="margin-bottom:45px;">
													<span>
														${book.contents}
													</span> 
												</div>
											</div>
										</div>
									</div>
									
									
									
									<div class="clear"></div>
								</div>
							</div>
							
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

<script src="/js_custom/resource/bookInfor.js"></script>
<%@ include file="../layout/footer.jsp"%>
