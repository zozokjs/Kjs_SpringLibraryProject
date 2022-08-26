<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_Guide.jsp"%>


				
				<div class="two-colls-right"><!-- 우측 메인 시작 -->
					<div class="two-colls-right-b">
						<div class="padding">
							<div class="right-Submenu-2depth">
								<!-- 1depth Menu -->
							</div>
							
							<!-- 우측 본문 시작 -->
							<div class="tables" style="margin-top: 70px;">
								<table class="table-a">
								<div class="input">
									<input type="text"  	id="title"  name="title" class="form-control"   placeholder="제목" required="">
								</div>
								</table>
							</div>
							
							<!-- 흰색 버튼 -->
							<div style="text-align: -webkit-center;">
								<button class=" booking-complete-btn white-btn-custom"  onclick="location.href='/saseo/${book.id}/bookUpdate"'>하기</button>
							</div>
							<!-- 우측 본문 끝 -->
							<div class="typography" style="padding-bottom:30px;">
								<div class="content-wrapper">
									<div class="block-qoutes">
										<div class="typography-heading" style="font-size:20px;">도서관 가입을 환영합니다!</div>
										
										<div class="blockqoute-tp-a">
											<span>본 도서관은 개인이 운영하는 사이트로써, 사이트 관리자에 의한 가입 승인 이후에 대출이 가능합니다.</span> <b></b>
										</div>
										<p>
											단, 열람이 가능한 책은 (아직까지는) 없으므로 이점 참고하시기 바랍니다. 가입 감사합니다!
										</p>
									</div>
								</div>
							</div>
							
							<!-- Page -->
							<div class="pagination pagination-custom"  style="display:flex; justify-content:center;">	<!-- 페이지 버튼 시작 -->
								<!-- 이전 버튼 -->
				                <c:choose>
									<c:when test="${book.first }">
									</c:when>
									<c:otherwise>
										<a class="" href="?page=${book.number-1}">이전</a>
									</c:otherwise>
								</c:choose>
								
								<!-- 현재 페이지일 때 Active  -->
				                <c:forEach var="index" begin="${startPage}" end="${endPage}">
			                      	<c:choose>
										<c:when test="${book.number+1  eq index}" >
										   <a class="active" href="#">${index}</a>
										</c:when>
										<c:otherwise>
											<a class="" href="?page=${index-1}">${index}</a>
										</c:otherwise>
									</c:choose>
				                </c:forEach>
								
								<!-- 다음 버튼 -->
								<c:choose>
									<c:when test="${book.last }">
									</c:when>
									<c:otherwise>
										<a class=""  href="?page=${book.number+1}">다음</a>
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