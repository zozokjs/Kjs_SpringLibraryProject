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
						
							<div class="col-lg-12">
								<div class="">
									<h2 class="mt-3 content-title ">
									대출 관리(연장/반납 신청)
									</h2>
								</div>
							</div>		
							<hr>

							<c:choose>
								<c:when test="${empty userLendList}">
									<h3>대출 중인 책이 존재하지 않습니다.</h3>
								</c:when>
								<c:otherwise>
								
									<!-- 반복부 Start-->
									<c:forEach var = "userLendInfor"  items ="${userLendList}">
									
										<div class="col-lg-12 col-md-8 mb-1">
											<div class="blog-item" style="display:flex; flex-direction:row; flex-wrap:nowrap; justify-content:space-around;">
												<div style="display:flex; flex-grow:2;">
													<img src="/upload/${userLendInfor.titleImageUrl}" alt="" class="img-fluid rounded">
												</div>
												<div class="blog-item-content bg-white p-4" style="display:flex; flex-grow:8; flex-direction: column;">
													<h3 class="mt-3 mb-3"><a href="/resource/${userLendInfor.bookId}/bookInfor">${userLendInfor.title}</a></h3>
													<!-- 표 Start -->
													<table class="tg">
														<thead>
															<tr>
																<th class="tg-0pky">저자</th>
																<th class="tg-0pky" colspan=3>  ${userLendInfor.writer}</th>
															</tr>
														</thead>
														<tbody>
															<tr>
																<td class="tg-0pky">발행처</td>
																<td class="tg-0pky" colspan=3> ${userLendInfor.publish}</td>
															</tr>
															<tr>
																<td class="tg-0pky">매체구분</td>
																<td class="tg-0pky" colspan=3> ${userLendInfor.bindTypeToString}</td>
															</tr>
															<tr>
																<td class="tg-0pky">대출일</td>
																<td class="tg-0pky" colspan=3> ${userLendInfor.formattedCreateDate}</td>
															</tr>
															<tr>
																<td class="tg-0pky" >반납예정일</td>
																<td class="tg-0pky" colspan=3> ${userLendInfor.formattedReturnPlanDate}</td>
															</tr>
															<tr>
																<td class="tg-0pky" colspan=4>
																	<button type ="button" onclick="bookReturnConfirm(${userLendInfor.id})">반납</button>
																	<button type ="button" onclick="bookExtensionConfirm(${userLendInfor.id})">연장</button>
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
							
						</div>
					</div>
				</div>
				<!-- 우측 메인 End-->
				
	    	</div>
	    </div>
	</section>
</div>
<script src="/js_custom/user/myLibrary.js"></script>
<%@ include file="../layout/footer.jsp"%>