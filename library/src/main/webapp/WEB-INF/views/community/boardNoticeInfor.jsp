<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_Community.jsp"%>
<link rel="stylesheet" href="/css_custom/community/boardNoticeInfor.css" />

				<div class="two-colls-right"><!-- 우측 메인 시작 -->
					<div class="two-colls-right-b">
						<div class="padding">
							<div class="right-Submenu-2depth">
								<!-- 1depth Menu -->
								공지사항
							</div>
							
							<input type ="hidden" id="loginUserId" value="${principal.user.id}">
							<input type ="hidden" id="boardNoticeId" value="${boardNotice.id}">
							
							<!-- 우측 본문 시작 -->
							<div class="tables  div-tables" >
								<table class="table-a">
									<tr>
										<td colspan="6" class="table-td-title">${boardNotice.title}</td>
									</tr>
									<tr class="table-td-info-left">
										<c:choose>
											<c:when test="${empty boardNotice.editDate}">
												<td class="table-td-info-left">작성자</td>
												<td>${boardNotice.user.username}</td>
												<td >등록일</td>
												<td colspan="3"  class="table-td-info-right">${boardNotice.createDateFormatted}</td>
											</c:when>
											<c:otherwise>
												<td class="table-td-info-left">작성자</td>
												<td>${boardNotice.user.username}</td>
												<td>등록일</td>
												<td>${boardNotice.createDateFormatted}</td>
												<td>수정일</td>
												<td colspan="3"  class="table-td-info-right">${boardNotice.createDateFormatted}</td>
											</c:otherwise>
										</c:choose>
										
									</tr>
									<tr>
										<td colspan="6"  class="table-td-content">
											<p>
												${boardNotice.content}
											</p>
										</td>
									</tr>
								</table>
							</div>
							<!-- 작성자 id와 로그인한 사람의 id가 같을 때 -->
							<c:choose>
								<c:when test="${principal.user.id eq boardNotice.user.id}">
									<div style="display:flex; justify-content:space-around;">
										<button onclick="location.href='/community/${boardNotice.id}/boardNoticeUpdate'"  class=" booking-complete-btn white-btn-custom" >수정</button>
										<button onclick="boardNoticeDeleteConfirm(${boardNotice.id})"  class=" booking-complete-btn white-btn-custom" >삭제</button> 
									</div>
								</c:when>
							</c:choose> 
							
							<!-- 우측 본문 끝 -->
							
							
							
							
				
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

<script src="/js_custom/community/boardNoticeInfor.js"></script>
<%@ include file="../layout/footer.jsp"%>
