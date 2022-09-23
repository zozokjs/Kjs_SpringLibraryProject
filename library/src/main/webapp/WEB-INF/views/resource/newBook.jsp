<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_Resource.jsp"%>

				
				<div class="two-colls-right"><!-- 우측 메인 시작 -->
					<div class="two-colls-right-b">
						<div class="padding">
							<div class="right-Submenu-2depth">
								신착 도서
							</div>
							
							<div class="catalog-row list-rows"><!-- 우측 본문 시작 -->
							
								<c:forEach var ="books" items="${book.content}">
								
									<div class="cat-list-item fly-in"><!-- 반복부 시작 -->
										<div class="cat-list-item-l">
											<a href="/resource/${books.id}/bookInfor" >
												<img src="/upload/${books.titleImageUrl}"  ><!-- 타이틀 이미지 -->
											</a>
										</div>
										<div class="cat-list-item-r"><!-- 여기부터 콘텐츠 영역 -->
											<div class="cat-list-item-rb">
												<div class="cat-list-item-p">
													<div class="cat-list-content">
														<div class="cat-list-content-a">
															<div class="cat-list-content-l"  style="width:100%; ">
																<div class="cat-list-content-lb">
																
																	<div class="cat-list-content-lpadding"><!--  내용 시작 -->
																		<div class="tables" >
																		
																			<table class="table-a light">
																				<tr>
																					<td>제목</td>
																					<td colspan="3">
																						<a href="/resource/${books.id}/bookInfor" >${books.title}</a>
																					</td>
																				</tr>
																				<tr>
																					<td>저자</td>
																					<td colspan="3">${books.writer}</td>
																				</tr>
																				<tr>
																					<td>발행처</td>
																					<td>${books.publish}</td>
																					<td>발행일</td>
																					<td>${books.publishDate}</td>
																				</tr>
																				<tr>
																					<td>매체구분</td>
																					<td>${books.bindType}</td>
																					<td>페이지</td>
																					<td>${books.page}</td>
																				</tr>
																				<tr>
																					<td>ISBN</td>
																					<td>${books.isbn}</td>
																					<td>ISBN SET</td>
																					<td>${books.isbnSet}</td>
																				</tr>
																				<tr>
																					<td>대여가능수</td>
																					<td colspan="3">${books.remainAmount} / ${books.totalAmount}</td>
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
										<div class="clear"></div>
									</div><!-- 반복부 끝 -->
								</c:forEach>

							</div><!-- 우측 본문 끝 -->
							
							
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
						</div><!-- end of class padding -->
						<div class="clear" ></div>
					
					
					
					</div>
				</div><!-- 우측 메인 끝 -->
				<div class="clear"></div>
				
				
			</div><!-- end of class two-colls(submenu_guide) -->
			<div class="clear"></div><!-- 필수 -->
		</div>
	</div>
</div><!-- /main-cont -->

<%@ include file="../layout/footer.jsp"%>