<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_User.jsp"%>
	        
	    
				<div class="two-colls-right"><!-- 우측 메인 시작 -->
					<div class="two-colls-right-b">
						<div class="padding">
							<div class="right-Submenu-2depth">
								대출 관리(연장/반납 신청)
							</div>
							
							<c:choose>
								<c:when test="${empty userLendList}">
									<h3>대출 중인 책이 존재하지 않습니다.</h3>
								</c:when>
								<c:otherwise>
								
									<!-- 반복부 Start-->
									<c:forEach var = "userLendInfor"  items ="${userLendList}">
										<div class="cat-list-item fly-in"><!-- 반복부 시작 -->
										<div class="cat-list-item-l">
											<a href="#">
												<img alt="" src="/upload/${userLendInfor.titleImageUrl}"  ><!-- 타이틀 이미지 -->
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
																						<a href="/resource/${userLendInfor.bookId}/bookInfor">${userLendInfor.title}</a>
																					</td>
																				</tr>
																				<tr>
																					<td>저자</td>
																					<td colspan="3">${userLendInfor.writer}</td>
																				</tr>
																				<tr>
																					<td>발행처</td>
																					<td>${userLendInfor.publish}</td>
																				</tr>
																				<tr>
																					<td>매체구분</td>
																					<td>${userLendInfor.bindTypeToString}</td>
																				</tr>
																				<tr>
																					<td>대출일</td>
																					<td>${userLendInfor.formattedCreateDate}</td>
																				</tr>
																				
																				<c:choose>
																					<c:when test="${userLendInfor.extensionDate eq null}">
																						<tr>
																							<td>반납예정일</td>
																							<td colspan="3">${userLendInfor.formattedReturnPlanDate}</td>
																						</tr>
																					</c:when>
																					<c:otherwise>
																							<td>반납예정일(연장됨)</td>
																							<td colspan="3">${userLendInfor.formattedExtensionDate}</td>
																					</c:otherwise>
																				</c:choose>
																				
																			</table>
																			
																		</div>	
																		
																		<div style="display:flex; justify-content:space-around;">
																			<button type ="button" onclick="bookReturnConfirm(${userLendInfor.id})"  class=" booking-complete-btn white-btn-custom" >반납</button>
																			<button type ="button" onclick="bookExtensionConfirm(${userLendInfor.id})"  class=" booking-complete-btn white-btn-custom" >연장</button>
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

<script src="/js_custom/user/myLibrary.js"></script>
<%@ include file="../layout/footer.jsp"%>