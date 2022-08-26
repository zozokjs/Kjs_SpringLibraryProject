<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_User.jsp"%>
	        
	 
				<div class="two-colls-right"><!-- 우측 메인 시작 -->
					<div class="two-colls-right-b">
						<div class="padding">
							<div class="right-Submenu-2depth">
								<!-- 1depth Menu -->
								반납 완료 내역
							</div>

							<c:choose>
								<c:when test="${empty userLendHistoryList.content}">
									<h3>반납 내역이 존재하지 않습니다.</h3>
								</c:when>
								<c:otherwise>
								
									<!-- 반복부 Start-->
									<c:forEach var = "userLendHistoryInfor"  items ="${userLendHistoryList.content}">
									
										<!-- 해당 아이디는 Lend 테이블의 id임. -->
										<input  type="hidden" id="lendId"  value="${userLendHistoryInfor.id}"/>
									
										<div class="cat-list-item fly-in"><!-- 반복부 시작 -->
										<div class="cat-list-item-l">
											<a href="#">
												<img alt="" src="/upload/${userLendHistoryInfor.titleImageUrl}"  ><!-- 타이틀 이미지 -->
											</a>
										</div>
										<div class="cat-list-item-r"><!-- 여기부터 콘텐츠 영역 -->
											<div class="cat-list-item-rb">
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
																					<td colspan="3">
																						<a href="/resource/${userLendHistoryInfor.bookId}/bookInfor">${userLendHistoryInfor.title}</a>
																					</td>
																				</tr>
																				<tr>
																					<td>저자</td>
																					<td colspan="3">${userLendHistoryInfor.writer}</td>
																				</tr>
																				<tr>
																					<td>발행처</td>
																					<td>${userLendHistoryInfor.publish}</td>
																				</tr>
																				<tr>
																					<td>매체구분</td>
																					<td>${userLendHistoryInfor.bindTypeToString}</td>
																				</tr>
																				<tr>
																					<td>대출일</td>
																					<td>${userLendHistoryInfor.formattedCreateDate}</td>
																				</tr>
																				<tr>
																					<td>반납 날짜</td>
																					<td colspan="3">${userLendHistoryInfor.returnDate}</td>
																				</tr>
																				
																				<c:choose>
																					<c:when test="${userLendHistoryInfor.useState eq false}">
																						<tr>
																							<td colspan="4" style="color:red;">해당 도서는 삭제되었습니다.</td>
																						</tr>
																					</c:when>
																				</c:choose>
																				
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
								</c:otherwise>
							</c:choose>	
							
							
							<!-- Page -->
							<div class="pagination pagination-custom"  style="display:flex; justify-content:center;">	<!-- 페이지 버튼 시작 -->
								<!-- 이전 버튼 -->
				                <c:choose>
									<c:when test="${userLendHistoryList.first }">
									</c:when>
									<c:otherwise>
										<a class="" href="?page=${userLendHistoryList.number-1}">이전</a>
									</c:otherwise>
								</c:choose>
								
								<!-- 현재 페이지일 때 Active  -->
				                <c:forEach var="index" begin="${startPage}" end="${endPage}">
			                      	<c:choose>
										<c:when test="${userLendHistoryList.number+1  eq index}" >
										   <a class="active" href="#">${index}</a>
										</c:when>
										<c:otherwise>
											<a class="" href="?page=${index-1}">${index}</a>
										</c:otherwise>
									</c:choose>
				                </c:forEach>
								
								<!-- 다음 버튼 -->
								<c:choose>
									<c:when test="${userLendHistoryList.last }">
									</c:when>
									<c:otherwise>
										<a class=""  href="?page=${userLendHistoryList.number+1}">다음</a>
									</c:otherwise>
								</c:choose>
									
								<div class="clear"></div>
							</div><!-- 페이지 버튼 끝 -->	
							
							
							
				
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