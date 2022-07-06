<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_Resource.jsp"%>
	        
	            <!-- 우측 메인 -->
	            <div class="col-lg-8">
	               <div class="row">
						<div class="col-lg-12 mb-5">
							<div class="col-lg-7">
								<div class="">
									<h2 class="mt-3 content-title ">
									신착 도서
									</h2>
								</div>
							</div>		
							<hr>	
							<!-- 내용 Start-->
							<!-- 신착 도서 리스트 표시 -->
							<section class="blog-wrap bg-gray">
							    <div class="container">
							        <div class="row">
							            <div class="col-lg-12">
<!-- 							                <div class="row"> -->
								               	<!--  본문 시작  -->
								                <c:forEach var ="books" items="${book.content}">
												    <!-- 반복부 Start -->
												    <div class="col-lg-12 col-md-8 mb-1"  style="display:flex;">
												        <!-- <div class="blog-item" style="width:700px; height:600px; display:flex; flex-direction:row; flex-wrap:nowrap; justify-content:space-around;">
-->												            <div style="display:flex; flex-shrink:30;">
												                <img src="/upload/${books.titleImageUrl}" alt="" class="img-fluid w-100">
												            </div>
												            <div class="blog-item-content bg-white p-4" style="display:flex; flex-shrink:1; flex-direction: column;">
												                <h3 class="mt-3 mb-3"><a href="/resource/${books.id}/bookInfor">${books.title}</a></h3>							
												                <!-- 표 Start --->
												                <table class="tg">
												                    <thead>
												                        <tr>
												                            <th class="tg-0pky">저자</th>
												                            <th class="tg-0pky">${books.writer}</th>
												                            <th class="tg-0lax">발행처</th>
												                            <th class="tg-0lax">${books.publish}</th>
												                        </tr>
												                    </thead>
												                    <tbody>
												                        <tr>
												                            <td class="tg-0pky">ISBN</td>
												                            <td class="tg-0pky">${books.isbn}</td>
												                            <td class="tg-0lax">ISBNSET</td>
												                            <td class="tg-0lax">${books.isbnSet}</td>
												                        </tr>
												                        <tr>
												                            <td class="tg-0pky">매체구분</td>
												                            <td class="tg-0pky">${books.bindType}</td>
												                            <td class="tg-0lax">페이지</td>
												                            <td class="tg-0lax">${books.page}</td>
												                        </tr>
												                        <tr>
												                            <td class="tg-0pky" >발행일</td>
												                            <td class="tg-0pky">${books. publishDate}</td>
												                        </tr>
												                        <tr>
												                            <td class="tg-0pky" >대여가능수</td>
												                            <td class="tg-0pky">${books.remainAmount} / ${books.totalAmount}</td>
												                        </tr>
												                    </tbody>
												                </table>
												                <!-- 표 End -->
												            </div>
<!-- 												        </div>
 -->												    </div>
												  <!-- 반복부 End -->
												
												</c:forEach>
												
							             		<!--  본문 끝 -->
							                
		<!-- 									</div> -->
										</div>
									</div>
									
									
									
					                <!-- 페이지 버튼 시작 -->
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
													
									                <!-- 현재 페이지일 때는  -->
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
	      							<!-- 페이지 버튼 끝 -->		

								</div><!-- container -->
							</section>
							
							
							
						</div>
					</div>
				</div>
				<!-- 우측 메인 End-->
				
	    	</div>
	    </div>
	</section>
</div>
<%@ include file="../layout/footer.jsp"%>
