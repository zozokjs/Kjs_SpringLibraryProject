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
	        
	          	<div class="two-colls-right"><!-- 우측 메인 시작 -->
					<div class="two-colls-right-b">
						<div class="padding">
							<div class="right-Submenu-2depth">
								사용 정지된 회원 목록(비번 5회 틀림)
							</div>
							
							<!-- 우측 본문 시작 -->
							<c:choose>
								<c:when test="${empty userList}">
									<h3>사용 정지된 회원이 없습니다.</h3>
								</c:when>
								<c:otherwise>
									<div class="tables" style="margin-top: 70px;">
										<table class="table-a">
											<thead>
												<tr>
													 <th class="">순서</th>
													 <th class="">아이디</th>
													 <th class="">이메일</th>
													 <th class="">처리버튼</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach var = "users" items ="${userList.content}"   varStatus="index">
													<tr>
														 <td class="">
														 	<c:out value="${index.count}"></c:out>
														 </td>
														 <td class="">${users.username}</td>
														 <td class="">${users.email}</td>
														 <td class="">
														 	<div style="text-align: -webkit-center;">
																<button class=" booking-complete-btn white-btn-custom"  onclick="userPermit(${users.id},event)"'>계정활성화</button>
															</div>
														 </td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</c:otherwise>
							</c:choose>
							
							
							
							
							<!-- Page -->
							<div class="pagination pagination-custom"  style="display:flex; justify-content:center;">	<!-- 페이지 버튼 시작 -->
								<!-- 이전 버튼 -->
				                <c:choose>
									<c:when test="${userList.first }">
									</c:when>
									<c:otherwise>
										<a class="" href="?page=${userList.number-1}">이전</a>
									</c:otherwise>
								</c:choose>
								
								<!-- 현재 페이지일 때 Active  -->
				                <c:forEach var="index" begin="${startPage}" end="${endPage}">
			                      	<c:choose>
										<c:when test="${userList.number+1  eq index}" >
										   <a class="active" href="#">${index}</a>
										</c:when>
										<c:otherwise>
											<a class="" href="?page=${index-1}">${index}</a>
										</c:otherwise>
									</c:choose>
				                </c:forEach>
								
								<!-- 다음 버튼 -->
								<c:choose>
									<c:when test="${userList.last }">
									</c:when>
									<c:otherwise>
										<a class=""  href="?page=${userList.number+1}">다음</a>
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
<script src="/js_custom/saseo/userLockManage.js"></script>
<%@ include file="../layout/footer.jsp"%>
