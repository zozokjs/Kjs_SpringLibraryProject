<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_Saseo.jsp"%>

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
	        
	            <!-- 우측 메인 Start -->
	            <div class="col-lg-8">
	               <div class="row">
						<div class="col-lg-12 mb-5">
						
							<!-- 2depth 타이틀 영역 -->
							<div class="col-lg-7">
								<div class="">
									<h2 class="mt-3 content-title ">
									사용 정지된 회원 목록(비번 5회 틀림)
									</h2>
								</div>
							</div>		
							<hr>	
							
							
							<!-- 본문 Start -->
							<c:choose>
								<c:when test="${empty userList}">
									<h3>사용 정지된 회원이 없습니다.</h3>
								</c:when>
								<c:otherwise>
									<div class="col-lg-12 col-md-8 mb-1">
										<div class="blog-item" style="display:flex; flex-direction:row; flex-wrap:nowrap; justify-content:space-around;">
											<div class="blog-item-content bg-white p-4" >
											
												<table class="tg">
													<thead>
														<tr>
															 <th class="tg-0pky">순서</th>
															 <th class="tg-0pky">아이디</th>
															 <th class="tg-0pky">이메일</th>
															 <th class="tg-0pky">처리버튼</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach var = "users" items ="${userList.content}"   varStatus="index">
															<tr>
																 <td class="tg-0pky">
																 	<c:out value="${index.count}"></c:out>
																 </td>
																 <td class="tg-0pky">${users.username}</td>
																 <td class="tg-0pky">${users.email}</td>
																 <td class="tg-0pky">
																 	<button onclick="userPermit(${users.id},event)">계정활성화</button> 
																 </td>
															</tr>
														</c:forEach>
													</tbody>
												</table>
												
											</div>
										</div>
									</div>
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
												<c:when test="${userList.first }">
													<%-- <a class="prev page-numbers disabled" href="?page=${book.number-1}">이전</a> --%>
												</c:when>
												<c:otherwise>
													<a class="prev page-numbers" href="?page=${userList.number-1}">이전</a>
												</c:otherwise>
											</c:choose>
											
							                <%-- <c:forEach var="index" begin="0" end="${book.totalPages / 10}"> --%> <!--  58 -->
												
												
							                <c:forEach var="index" begin="${startPage}" end="${endPage}">
						                      	<c:choose>
													<c:when test="${userList.number+1  eq index}" >
													   <span aria-current="page" class="page-numbers current">${index}</span>
													</c:when>
													<c:otherwise>
														<a class="page-numbers current" href="?page=${index-1}">${index}</a>
													</c:otherwise>
												</c:choose>
							                </c:forEach>
								          
								                
											
											<!-- 다음 버튼 -->
 											<c:choose>
												<c:when test="${userList.last }">
													<%-- <a class="next page-numbers disabled"  href="?page=${book.number+1}">다음</a> --%>
												</c:when>
												<c:otherwise>
													<a class="next page-numbers disabled"  href="?page=${userList.number+1}">다음</a>
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
<script src="/js_custom/saseo/userLockManage.js"></script>
<%@ include file="../layout/footer.jsp"%>
