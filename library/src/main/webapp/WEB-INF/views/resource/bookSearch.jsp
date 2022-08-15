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
									통합 검색
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

							               	<!--  본문 Start  -->
						                	<div class="sidebar-widget search card p-4 mb-3 border-0">
												<input type="text" id ="bookSearchKeyword" class="form-control"  placeholder="검색어를 입력하세요">
												<button onclick="bookSearch()" class="btn btn-mian btn-small d-block mt-2">검색</button> 
											</div>
											
											<!-- ------------------------------------------------------------------------------------------------------------ -->
											
											<!--현재 화면에서 검색 시 검색 결과 Start  -->
											<div id ="bookDataSearchResult">
											</div>
											<!-- 현재 화면에서 검색 시 검색 결과 End  -->
											
											<!-- ------------------------------------------------------------------------------------------------------------ -->
						
											<!--  인덱스 페이지에서 검색 시 검색 결과 Start -->
											<!-- 
											byIndexPageSearch = 인덱스 페이지에서 전달된 결과인지 확인함
											searchResultZero = 검색 결과가 0이면 true
											 -->
											<div id ="bookDataSearchResult_fromIndex">
											<c:choose>
												
												<c:when test="${byIndexPageSearch eq true}">
												
													<input  type="hidden"  id="searchKeyword"  value="${searchKey}"/>
													
													<c:choose>
														<c:when test="${searchResultZero eq true}">
															<h3> "${searchKey}"에 대한 검색 결과가 없습니다.</h3>
														</c:when>
														
														<c:otherwise>
															<h3>"${searchKey}"에 대한 검색 결과</h3>
															<hr>
														
															<c:forEach var = "book" items ="${bookSearchData.content}"   varStatus="index">
																<div class="col-lg-12 col-md-8 mb-1"  style="display:flex;">
														            <div style="display:flex; flex-shrink:30;">
														                <img src="/upload/${book.titleImageUrl}" alt="" class="img-fluid w-100">
														            </div>
														            <div class="blog-item-content bg-white p-4" style="display:flex; flex-shrink:1; flex-direction: column;">
														                <h3 class="mt-3 mb-3"><a href="/resource/${book.id}/bookInfor">${book.title}</a></h3>							
														                <!-- 표 Start --->
														                <table class="tg">
														                    <thead>
														                        <tr>
														                            <th class="tg-0pky">저자</th>
														                            <th class="tg-0pky">${book.writer}</th>
														                            <th class="tg-0lax">발행처</th>
														                            <th class="tg-0lax">${book.publish}</th>
														                        </tr>
														                    </thead>
														                    <tbody>
														                        <tr>
														                            <td class="tg-0pky">ISBN</td>
														                            <td class="tg-0pky">${book.isbn}</td>
														                            <td class="tg-0lax">ISBNSET</td>
														                            <td class="tg-0lax">${book.isbnSet}</td>
														                        </tr>
														                        <tr>
														                            <td class="tg-0pky">매체구분</td>
														                            <td class="tg-0pky">${book.bindType}</td>
														                            <td class="tg-0lax">페이지</td>
														                            <td class="tg-0lax">${book.page}</td>
														                        </tr>
														                        <tr>
														                            <td class="tg-0pky" >발행일</td>
														                            <td class="tg-0pky">${book. publishDate}</td>
														                        </tr>
														                        <tr>
														                            <td class="tg-0pky" >대여가능수</td>
														                            <td class="tg-0pky">${book.remainAmount} / ${book.totalAmount}</td>
														                        </tr>
														                    </tbody>
														                </table>
														                <!-- 표 End -->
														            </div>
														    	</div>
													    	</c:forEach>
												    	</c:otherwise>
											    	</c:choose>
											    	
												</c:when>
												
												<c:otherwise>
												</c:otherwise>
											</c:choose>
											</div>
											
											
											<!--  본문 End -->
											
											<!--  인덱스 페이지에서 검색 시 검색 결과 End -->
										</div>
									</div>
									
									
									<!-- ------------------------------------------------------------------------------------------------------------ -->
									
									
									<!-- 현재 화면에서 검색 시 페이징 Start -->
									<div id ="bookDataSearchResult_Paging">
									</div>
									<!-- 현재 화면에서 검색 시 페이징 End -->
									<!-- ------------------------------------------------------------------------------------------------------------ -->
									
									
									
									<!--  메인화면에서 검색 시 페이징 Start -->
									<div id ="bookDataSearchResultPaging_fromIndex">
									<c:choose>
										<c:when test="${byIndexPageSearch eq true}">
											<c:choose>
												<c:when test="${searchResultZero eq true}">
												</c:when>
												<c:otherwise>
													<div class="row justify-content-center mt-5">
										            	<div class="col-lg-12 text-center">
											            	<nav class="navigation pagination d-inline-block">
												                <div class="nav-links">
											                	
											                		<!-- 이전 버튼 -->
													                <c:choose>
																		<c:when test="${bookSearchData.first}">
																		</c:when>
																		<c:otherwise>
																			<a class="prev page-numbers"  href="javascript:void(0);"  onclick="bookSearchPagingProcess(${bookDataBySearch.number-1})" >이전</a>
																		</c:otherwise>
																	</c:choose>
																	
													                <!-- 현재 페이지일 때는  -->
													                <c:forEach var="index" begin="${startPage}" end="${endPage}">
												                      	<c:choose>
																			<c:when test="${bookSearchData.number+1  eq index}" >
																			   <span aria-current="page" class="page-numbers current">${index}</span>
																			</c:when>
																			<c:otherwise>
																				<a class="page-numbers current" href="javascript:void(0);" onclick="bookSearchPagingProcess(${index-1})"   >${index}</a>
																			</c:otherwise>
																		</c:choose>
													                </c:forEach>
																	
																	<!-- 다음 버튼 -->
						 											<c:choose>
																		<c:when test="${bookSearchData.last }">
																		</c:when>
																		<c:otherwise>
																			<a class="next page-numbers disabled"   href="javascript:void(0);"  onclick="bookSearchPagingProcess(${bookSearchData.number+1})" >다음</a>
																		</c:otherwise>
																	</c:choose>
																
												                </div>
											                </nav>
							                			</div>
									                </div>
												</c:otherwise>
											</c:choose>
										</c:when>
									</c:choose>
									</div>
							
								<!--  메인화면에서 검색 시 페이징 End -->
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
<script src="/js_custom/resource/bookSearch.js"></script>
<%@ include file="../layout/footer.jsp"%>
