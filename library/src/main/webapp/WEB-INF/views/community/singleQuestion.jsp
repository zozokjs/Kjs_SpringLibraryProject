<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_Community.jsp"%>
         
				
				<div class="two-colls-right"><!-- 우측 메인 시작 -->
					<div class="two-colls-right-b">
						<div class="padding">
							<div class="right-Submenu-2depth">
								1ㄷ1 질문하기
							</div>
							
							<!-- 우측 본문 시작 -->
							<div class="tables div-tables" >
								<table class="table-a">
									<thead>
										<tr>
											<th class="">번호</th>
											<th class="">제목</th>
											<th class="">작성자</th>
											<th class="">등록날짜</th>
											<th class="">처리상황</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var = "SingleQ" items ="${singleQuestionList.content}"   varStatus="index">
											<tr>
												 <td class="">${SingleQ.id}</td>
												 <td class="">
												 	<a href="/community/${SingleQ.id}/singleQuestionInfor">${SingleQ.title}</a> 
												 </td>
												 <td class="">${SingleQ.user.username}</td>
												 <td class="">${SingleQ.createDate}</td>
												 <c:choose>
												 	<c:when test="${SingleQ.answerOk eq true}">
												 		<td class="">답변 완료</td>
												 	</c:when>
												 	<c:otherwise>
													 	<td class=""> 답변 대기</td>
												 	</c:otherwise>
												 </c:choose>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							
							<!-- 흰색 버튼 -->
							<div style="text-align: -webkit-center;">
								<button class=" booking-complete-btn white-btn-custom"  onclick="location.href='/community/singleQuestionRegistrationForm'" >질문하기</button>
							</div>
							
							<!-- Page -->
							<div class="pagination pagination-custom" >	<!-- 페이지 버튼 시작 -->
								<!-- 이전 버튼 -->
				                <c:choose>
									<c:when test="${singleQuestionList.first }">
									</c:when>
									<c:otherwise>
										<a class="" href="?page=${singleQuestionList.number-1}">이전</a>
									</c:otherwise>
								</c:choose>
								
								<!-- 현재 페이지일 때 Active  -->
				                <c:forEach var="index" begin="${startPage}" end="${endPage}">
			                      	<c:choose>
										<c:when test="${singleQuestionList.number+1  eq index}" >
										   <a class="active" href="#">${index}</a>
										</c:when>
										<c:otherwise>
											<a class="" href="?page=${index-1}">${index}</a>
										</c:otherwise>
									</c:choose>
				                </c:forEach>
								
								<!-- 다음 버튼 -->
								<c:choose>
									<c:when test="${singleQuestionList.last }">
									</c:when>
									<c:otherwise>
										<a class=""  href="?page=${singleQuestionList.number+1}">다음</a>
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