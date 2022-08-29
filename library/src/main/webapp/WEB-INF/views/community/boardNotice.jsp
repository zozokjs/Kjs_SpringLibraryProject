<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_Community.jsp"%>
           <style>
           
	        	table{
	        /* 		border : 2px solid;
	         */	}
	        	
	        	thead{
	        		border : 2.5px solid;
	        	}
	        	th, td{
	        		text-align :center;
	        	}
	        	/**
	        	
	        	th, td{
	        		border : 2px solid; 
	        		border-collapse : collpase; 
	        		padding : 10px 5px;
	        		text-align :center;
	        	}*/
	        </style>
	      
				
				<div class="two-colls-right"><!-- 우측 메인 시작 -->
					<div class="two-colls-right-b">
						<div class="padding">
							<div class="right-Submenu-2depth">
								공지사항
							</div>
							
							<!-- 우측 본문 시작 -->
							<div class="tables" style="margin-top: 70px;">
								<table class="table-a">
									<thead>
										<tr>
											<th class="tg-0pky">순서</th>
											 <th class="tg-0pky">제목</th>
											 <th class="tg-0pky">작성자</th>
											 <th class="tg-0pky">조회수</th>
											 <th class="tg-0pky">등록날짜</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var = "notice" items ="${boardNotice.content}"   varStatus="index">
										
											
										
											<tr>
												 <td class="">${notice.id}</td>
												 <td class="">
												 	<a href="/community/${notice.id}/noticeInfor">${notice.title}</a>
												 </td>
												 <td class="">${notice.user.username}</td>
												 <td class="">${notice.readCount}</td>
												 <td class="">
													 <fmt:parseDate value="${notice.createDate}"   pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both"  />
													 <fmt:formatDate value="${parsedDateTime}"   pattern="yyyy-MM-dd  | HH:mm"/>
												 </td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<c:if test="${principal.user.roleType eq 'SASEO' || principal.user.roleType eq 'ADMIN'}">
									<!-- 흰색 버튼 -->
									<div style="text-align: -webkit-center;">
										<button class=" booking-complete-btn white-btn-custom"   onclick="location.href='/community/boardNoticeRegistrationForm'" >글쓰기</button>
									</div>
								</c:if>
								
							<!-- 우측 본문 끝 -->
							
							</div>
							
							
							
							<!-- Page -->
							<div class="pagination pagination-custom"  style="display:flex; justify-content:center;">	<!-- 페이지 버튼 시작 -->
								<!-- 이전 버튼 -->
				                <c:choose>
									<c:when test="${community.first }">
									</c:when>
									<c:otherwise>
										<a class="" href="?page=${community.number-1}">이전</a>
									</c:otherwise>
								</c:choose>
								
								<!-- 현재 페이지일 때 Active  -->
				                <c:forEach var="index" begin="${startPage}" end="${endPage}">
			                      	<c:choose>
										<c:when test="${community.number+1  eq index}" >
										   <a class="active" href="#">${index}</a>
										</c:when>
										<c:otherwise>
											<a class="" href="?page=${index-1}">${index}</a>
										</c:otherwise>
									</c:choose>
				                </c:forEach>
								
								<!-- 다음 버튼 -->
								<c:choose>
									<c:when test="${community.last }">
									</c:when>
									<c:otherwise>
										<a class=""  href="?page=${community.number+1}">다음</a>
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
