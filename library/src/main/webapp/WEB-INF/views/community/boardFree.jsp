<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_Community.jsp"%>
<link rel="stylesheet" href="/css_custom/community/boardFreeInfor.css" />


				
				<div class="two-colls-right"><!-- 우측 메인 시작 -->
					<div class="two-colls-right-b">
						<div class="padding">
							<div class="right-Submenu-2depth">
								자유게시판
							</div>
							
							<!-- 우측 본문 시작 -->
							<div class="tables  div-tables" >
								<table class="table-a">
									<thead>
										<tr>
											 <th class="">번호</th>
											 <th class="">제목[댓글수]</th>
											 <th class="">작성자</th>
											 <th class="">조회수</th>
											 <th class="">등록날짜</th>
											 
										</tr>
									</thead>
									<tbody>
										<c:forEach var = "free" items ="${boardFree.content}"   varStatus="index">
											<tr>
												 <td class="">${free.id}</td>
												 <td class="">
												 	<a href="/community/${free.id}/infor">${free.title}</a> [${free.commentCount}]
												 </td>
												 <td class="">${free.username}</td>
												 <td class="">${free.readCount}</td>
												 <td class="">
												 	<fmt:parseDate value="${free.createDate}"   pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"  />
													 <fmt:formatDate value="${parsedDateTime}"   pattern="yyyy-MM-dd  | HH:mm"/>
												 </td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								
								<c:if test="${principal.user.roleType eq 'USER' || principal.user.roleType eq 'SASEO' || principal.user.roleType eq 'ADMIN'}">
									<!-- 흰색 버튼 -->
									<div style="text-align: -webkit-center;">
										<button class=" booking-complete-btn white-btn-custom"  onclick="location.href='/community/boardFreeRegistrationForm'">글쓰기</button>
									</div>
								</c:if>

							<!-- 우측 본문 끝 -->
							
							</div>
							
							
							
							<!-- Page -->
							<div class="pagination pagination-custom  div-pagination" >	<!-- 페이지 버튼 시작 -->
								<!-- 이전 버튼 -->
				                <c:choose>
									<c:when test="${boardFree.first }">
									</c:when>
									<c:otherwise>
										<a class="" href="?page=${boardFree.number-1}">이전</a>
									</c:otherwise>
								</c:choose>
								
								<!-- 현재 페이지일 때 Active  -->
				                <c:forEach var="index" begin="${startPage}" end="${endPage}">
			                      	<c:choose>
										<c:when test="${boardFree.number+1  eq index}" >
										   <a class="active" href="#">${index}</a>
										</c:when>
										<c:otherwise>
											<a class="" href="?page=${index-1}">${index}</a>
										</c:otherwise>
									</c:choose>
				                </c:forEach>
								
								<!-- 다음 버튼 -->
								<c:choose>
									<c:when test="${boardFree.last }">
									</c:when>
									<c:otherwise>
										<a class=""  href="?page=${boardFree.number+1}">다음</a>
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
