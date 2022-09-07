<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_User.jsp"%>
	        
	 
				<div class="two-colls-right"><!-- 우측 메인 시작 -->
					<div class="two-colls-right-b">
						<div class="padding">
							<div class="right-Submenu-2depth">
								<!-- 1depth Menu -->
								작성글목록
							</div>

							<c:choose>
								<c:when test="${empty userBoardHistory.content}">
									<h3>작성한 글이 존재하지 않습니다.</h3>
								</c:when>
								<c:otherwise>
									<div class="tables" >
								
										<table class="table-a light">
											<tbody>
												<!--  0  -->
												<!-- 이 항목이 반드시 있어야 모바일 화면에서 테이블 형태가 유지됨-->
												<tr>
													<th colspan="4"  style="border-right:none; border-left:none;; border-top:none">
													</th>
												</tr>
												<tr>
													<td>번호</td>
													<td>게시판 구분</td>
													<td>제목</td>
													<td>작성일</td>
												</tr>
												
												<!-- 반복부 Start-->
												<c:forEach var = "userBoardHistoryInfor"  items ="${userBoardHistory.content}"  >
													<tr>
														<td>${userBoardHistoryInfor.sequence}</td>
														<td>
															<c:choose>
																<c:when test="${userBoardHistoryInfor.flag eq 1}">
																	자유게시판
																</c:when>
																<c:otherwise>
																	1대1문의게시판
																</c:otherwise>
															</c:choose>
														</td>
														<td>
															<c:choose>
																<c:when test="${userBoardHistoryInfor.flag eq 1}">
																	<a href="/community/${userBoardHistoryInfor.id}/infor">${userBoardHistoryInfor.title}</a>
																</c:when>
																<c:otherwise>
																	<a href="/community/${userBoardHistoryInfor.id}/singleQuestionInfor">${userBoardHistoryInfor.title}</a>
																</c:otherwise>
															</c:choose>
														</td>
														<td>
															<fmt:parseDate value="${userBoardHistoryInfor.createDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
															<fmt:formatDate value="${parsedDateTime}" pattern="yyyy-MM-dd' 'HH:mm" />
														</td>
													</tr>
												</c:forEach>
												<!-- 반복부 끝 -->
												
											</tbody>
										</table>
										
										<br class="clear" />
									<div class="clear"></div>
								</div>
							</c:otherwise>
						</c:choose>	
								
							
						<!-- Page -->
						<div class="pagination pagination-custom" >	<!-- 페이지 버튼 시작 -->
							<!-- 이전 버튼 -->
			                <c:choose>
								<c:when test="${userBoardHistory.first }">
								</c:when>
								<c:otherwise>
									<a class="" href="?page=${userBoardHistory.number-1}">이전</a>
								</c:otherwise>
							</c:choose>
							
							<!-- 현재 페이지일 때 Active  -->
			                <c:forEach var="index" begin="${startPage}" end="${endPage}">
		                      	<c:choose>
									<c:when test="${userBoardHistory.number+1  eq index}" >
									   <a class="active" href="#">${index}</a>
									</c:when>
									<c:otherwise>
										<a class="" href="?page=${index-1}">${index}</a>
									</c:otherwise>
								</c:choose>
			                </c:forEach>
							
							<!-- 다음 버튼 -->
							<c:choose>
								<c:when test="${userBoardHistory.last }">
								</c:when>
								<c:otherwise>
									<a class=""  href="?page=${userBoardHistory.number+1}">다음</a>
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