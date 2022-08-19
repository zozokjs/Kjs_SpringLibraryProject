<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_Resource.jsp"%>


	        	<div class="two-colls-right"><!-- 우측 메인 시작 -->
					<div class="two-colls-right-b">
						<div class="padding">
							<div class="right-Submenu-2depth">
								통합 검색
							</div>
							<!-- 우측 본문 시작 -->
							
							<div class="page-search-p" style="display: flex; flex-direction: row; justify-content: center;"><!-- 검색창 시작 -->
								<div class="search-large-i" style="flex-grow: 9;">
									<!-- items -->
									<div class="srch-tab-line no-margin-bottom">
										<div class="input-a">
											<input type="text"  id ="bookSearchKeyword"  style="font-size: 30px;" value="" placeholder="자료검색">
										</div>
									</div>
								</div>
								<div style="flex-grow: 0.3;">
									<!-- items -->
									<a href="javascript:void(0);"  onclick="bookSearch()"  class="srch-btn" style="height: 45px; font-size: 30px;">검색</a>
								</div>
							</div><!-- 검색창 끝 -->
							<!-- ------------------------------------------------------------------------------------------------------------ -->
											
							<!--현재 화면에서 검색한 검색 결과 Start  -->
							<div id ="bookDataSearchResult">
							</div>
							<!-- 현재 화면에서 검색한 검색 결과 End  -->
							
							
							<!-- ------------------------------------------------------------------------------------------------------------ -->
							<!--  인덱스 페이지에서 검색한 검색 결과 Start -->
							<div id ="bookDataSearchResult_fromIndex">
								<c:choose>
									<c:when test="${byIndexPageSearch eq true}">
										<input  type="hidden"  id="searchKeyword"  value="${searchKey}"/>
										<c:choose>
											
											<c:when test="${searchResultZero eq true}">
												<!--  인덱스 페이지에서 검색한 검색 결과가 표시됩니다. -->
												<h1> "${searchKey}"에 대한 검색 결과가 없습니다.</h1>
											</c:when>
											<c:otherwise>
												<!--  인덱스 페이지에서 검색한 검색 결과가 표시됩니다. -->
												<h1>"${searchKey}"에 대한 검색 결과</h1>
												<hr>
												
												<c:forEach var = "books" items ="${bookSearchData.content}"   varStatus="index">
													<div class="cat-list-item fly-in"><!-- 반복부 시작 -->
														<div class="cat-list-item-l">
															<a href="#">
																<img alt="" src="/upload/${books.titleImageUrl}"  ><!-- 타이틀 이미지 -->
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
									    	</c:otherwise>
								    	</c:choose>
									</c:when>
									<c:otherwise>
									</c:otherwise>
								</c:choose>
							</div>
							<!--  인덱스 페이지에서 검색 시 검색 결과 End -->
							
							
							
							
							<!-- ------------------------------------------------------------------------------------------------------------ -->
							<!-- 현재 화면에서 검색 시 페이징 Start -->
							<div id ="bookDataSearchResult_Paging">
							</div>
							<!-- 현재 화면에서 검색 시 페이징 End -->
									
									
									
							
							<!-- ------------------------------------------------------------------------------------------------------------ -->
							<div id ="bookDataSearchResultPaging_fromIndex"><!--  인덱스 페이지에서 검색한 페이징 Start -->
								<c:choose>
									<c:when test="${byIndexPageSearch eq true}">
										<c:choose>
											<c:when test="${searchResultZero eq true}">
											</c:when>
											<c:otherwise>
												<!--  인덱스 페이지에서 검색한 검색 결과가 표시됩니다. -->
												<div class="pagination pagination-custom"  style="display:flex; justify-content:center;">	<!-- 페이지 버튼 시작 -->
										                	
							                		<!-- 이전 버튼 -->
									                <c:choose>
														<c:when test="${bookSearchData.first}">
														</c:when>
														<c:otherwise>
															<a  href="javascript:void(0);"  onclick="bookSearchPagingProcess(${bookDataBySearch.number-1})" >이전</a>
														</c:otherwise>
													</c:choose>
													
									             	<!-- 현재 페이지일 때 Active  -->
									                <c:forEach var="index" begin="${startPage}" end="${endPage}">
								                      	<c:choose>
															<c:when test="${bookSearchData.number+1  eq index}" >
															   <a class="active"  >${index}</a>
															</c:when>
															<c:otherwise>
																<a href="javascript:void(0);" onclick="bookSearchPagingProcess(${index-1})"   >${index}</a>
															</c:otherwise>
														</c:choose>
									                </c:forEach>
													
													<!-- 다음 버튼 -->
		 											<c:choose>
														<c:when test="${bookSearchData.last }">
														</c:when>
														<c:otherwise>
															<a href="javascript:void(0);"  onclick="bookSearchPagingProcess(${bookSearchData.number+1})" >다음</a>
														</c:otherwise>
													</c:choose>
											                
								                </div>
											</c:otherwise>
										</c:choose>
									</c:when>
								</c:choose>
							</div><!--  인덱스 페이지에서 검색한 페이징 End -->
							
							
						</div><!-- end of Class  padding -->
					</div><!-- end of  Class two-colls-right-b-->
					<div class="clear" ></div>
				</div><!-- 우측 메인 끝 --><!-- end of class two-colls-right) -->
				<div class="clear"></div>
				
			</div><!-- end of class two-colls(submenu_) -->
			<div class="clear"></div><!-- 필수 -->
		</div>
	</div>
</div><!-- /main-cont -->

	        	
<script src="/js_custom/resource/bookSearch.js"></script>
<%@ include file="../layout/footer.jsp"%>
