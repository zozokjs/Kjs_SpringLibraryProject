<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_User.jsp"%>
	        
	        <style>
	        	table{
	        		border : 2px solid;
	        	}
	        	th, td{
	        		border : 2px solid; 
	        		border-collapse : collpase; 
	        		padding : 10px 5px;
	        		text-align :center;
	        	}
	        
	        </style>
	            <!-- 우측 메인 -->
	            <div class="col-lg-8">
	               <div class="row">
						<div class="col-lg-12 mb-5">
						
							<div class="col-lg-7">
								<div class="">
									<h2 class="mt-3 content-title ">
									반납 완료 내역
									</h2>
								</div>
							</div>		
							<hr>		
							
							
							<!-- 본문 Start -->
							<c:choose>
								<c:when test="${empty userLendHistoryList }">
									<h3>반납 내역이 존재하지 않습니다.</h3>
								</c:when>
								<c:otherwise>
							
									<!-- 반복부 Start-->
									<c:forEach var = "userLendHistoryInfor"  items ="${userLendHistoryList.content}">
					
										<!-- 해당 아이디는 Lend 테이블의 id임. -->
										<input  type="hidden" id="lendId"  value="${userLendInfor.id}"/>
													
										<div class="col-lg-12 col-md-8 mb-1">
											<div class="blog-item" style="display:flex; flex-direction:row; flex-wrap:nowrap; justify-content:space-around;">
												<div style="display:flex; flex-grow:2;">
													<img src="/upload/${userLendHistoryInfor.titleImageUrl}" alt="" class="img-fluid rounded">
												</div>
												<div class="blog-item-content bg-white p-4" style="display:flex; flex-grow:8; flex-direction: column;">
													<h3 class="mt-3 mb-3"><a href="/resource/${userLendHistoryInfor.bookId}/bookInfor">${userLendHistoryInfor.title}</a></h3>
													<!-- 표 Start -->
													<table class="tg">
														<thead>
															<tr>
																<th class="tg-0pky">저자</th>
																<th class="tg-0pky" colspan=3>  ${userLendHistoryInfor.writer}</th>
															</tr>
														</thead>
														<tbody>
															<tr>
																<td class="tg-0pky">발행처</td>
																<td class="tg-0pky" colspan=3> ${userLendHistoryInfor.publish}</td>
															</tr>
															<tr>
																<td class="tg-0pky">매체구분</td>
																<td class="tg-0pky" colspan=3> ${userLendHistoryInfor.bindTypeToString}</td>
															</tr>
															<tr>
																<td class="tg-0pky">대출 날짜</td>
																<td class="tg-0pky" colspan=3> ${userLendHistoryInfor.formattedCreateDate}</td>
															</tr>
															<tr>
																<td class="tg-0pky" >반납 날짜</td>
																<td class="tg-0pky" colspan=3> ${userLendHistoryInfor.returnDate}</td>
															</tr>
															<tr>
																
																<td class="tg-0pky" colspan=4>
																</td>
															</tr>
														</tbody>
													</table>
													<!-- 표 End -->
												</div>
											</div>
										</div>
									
		 							</c:forEach> 
									<!-- 반복부 End-->
								
								</c:otherwise>
							</c:choose>
							<!-- 본분 End -->
							
							
							<!-- 페이지 버튼 Start -->
			                <div class="row justify-content-center mt-5">
				            	<div class="col-lg-12 text-center">
					            	<nav class="navigation pagination d-inline-block">
						                <div class="nav-links">
					                	
					                		<!-- 이전 버튼 -->
							                <c:choose>
												<c:when test="${userLendHistoryList.first }">
													<%-- <a class="prev page-numbers disabled" href="?page=${book.number-1}">이전</a> --%>
												</c:when>
												<c:otherwise>
													<a class="prev page-numbers" href="?page=${userLendHistoryList.number-1}">이전</a>
												</c:otherwise>
											</c:choose>
											
							                <%-- <c:forEach var="index" begin="0" end="${book.totalPages / 10}"> --%> <!--  58 -->
												
												
							                <c:forEach var="index" begin="${startPage}" end="${endPage}">
						                      	<c:choose>
													<c:when test="${userLendHistoryList.number+1  eq index}" >
													   <span aria-current="page" class="page-numbers current">${index}</span>
													</c:when>
													<c:otherwise>
														<a class="page-numbers current" href="?page=${index-1}">${index}</a>
													</c:otherwise>
												</c:choose>
							                </c:forEach>
								          
								                
											
											<!-- 다음 버튼 -->
 											<c:choose>
												<c:when test="${userLendHistoryList.last}">
													<%-- <a class="next page-numbers disabled"  href="?page=${book.number+1}">다음</a> --%>
												</c:when>
												<c:otherwise>
													<a class="next page-numbers disabled"  href="?page=${userLendHistoryList.number+1}">다음</a>
												</c:otherwise>
											</c:choose>
										
						                </div>
					                </nav>
	                			</div>
			                </div>
   							<!-- 페이지 버튼 End -->	
							
							
							
							
							
						</div>
					</div>
				</div>
				<!-- 우측 메인 End-->
				
	    	</div>
	    </div>
	</section>
</div>
<script src="/js_custom/user_myLibrary.js"></script>
<%@ include file="../layout/footer.jsp"%>