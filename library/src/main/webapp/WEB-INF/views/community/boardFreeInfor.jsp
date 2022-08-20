<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_Community.jsp"%>

<style>

</style>

				<div class="two-colls-right"><!-- 우측 메인 시작 -->
					<div class="two-colls-right-b">
						<div class="padding">
							<div class="right-Submenu-2depth">
								<!-- 1depth Menu -->
								자유게시판-상세 읽기
							</div>
							
							<input type ="hidden" id="loginUserId" value="${principal.user.id}">
							<input type ="hidden" id="boardFreeId" value="${boardFree.id}">
							
							<!-- 우측 본문 시작 -->
							<div class="tables" style="margin-top: 70px;">
								<table class="table-a">
									<tr>
										<td>제목</td>
										<td colspan="5">${boardFree.title}</td>
									</tr>
									<tr>
										<c:choose>
											<c:when test="${empty boardFree.editDate}">
												<td>작성자</td>
												<td>${boardFree.user.username}</td>
												<td >등록일</td>
												<td colspan="3">${boardFree.createDateFormatted}</td>
											</c:when>
											<c:otherwise>
												<td>작성자</td>
												<td>${boardFree.user.username}</td>
												<td>등록일</td>
												<td>${boardFree.createDateFormatted}</td>
												<td>수정일</td>
												<td>${boardFree.editDate}</td>
											</c:otherwise>
										</c:choose>
										
									</tr>
									<tr>
										<td>내용</td>
										<td colspan="5" style="text-align:left;">
											<div class="typography" style="padding-bottom:30px;">
												<div class="content-wrapper">
													<div class="block-qoutes">
														<p>
															${boardFree.content}
														</p>
													</div>
												</div>
											</div>
									
										</td>
									</tr>
								</table>
							</div>
							<!-- 작성자 id와 로그인한 사람의 id가 같을 때 -->
							<c:choose>
								<c:when test="${principal.user.id eq boardFree.user.id}">
									<div style="display:flex; justify-content:space-around;">
										<button onclick="location.href='/community/${boardFree.id}/boardFreeUpdate'"  class=" booking-complete-btn white-btn-custom" >수정</button>
										<button onclick="boardFreeDeleteConfirm(${boardFree.id})" class=" booking-complete-btn white-btn-custom" >삭제</button> 
									</div>
								</c:when>
							</c:choose> 
							
							<hr>
							
							
							<!-- 댓글 시작 -->
							<!-- 이름 / 내용 / 작성 시간 / 삭제버튼 -->
							<div class="tables" style="margin-top: 70px;">
								<table class="table-a">
									<tbody>
									<!-- 댓글이 없을 경우 없다고 표시 -->
									<c:choose>
										<c:when test="${empty boardFree.comments}">
											<tr>
												<td colspan="4">등록된 덧글이 없습니다.</td>
											</tr>
										</c:when>
										<c:otherwise>
											<c:forEach var="comments" items="${boardFree.comments}">
												<tr>
													<td>${comments.user.username}</td>
													<td>${comments.content}</td>
													<td>${comments.createDateFormatted}</td>
													<!-- 로그인 한 사람만 삭제 버튼 보임 -->
													<c:choose>
														<c:when test="${not empty principal.user.id}">
															<td>
																<div style="text-align: -webkit-center;">
																	<button onclick="boardFreeCommentDeleteConfirm(${comments.id})"  class=" booking-complete-btn white-btn-small-custom"  >삭제</button>
																</div>
															</td>
														</c:when>
													</c:choose>
												</tr>
											</c:forEach>
										</c:otherwise>
									</c:choose>
								</tbody>
							</table>
						</div>
							<!-- 댓글 끝 -->
							
							<hr>

							<!-- 댓글 작성 공간 시작-->								
							<!-- 로그인 안 되어 있으면 표시 안 됨 -->
							<div class="tables" style="margin-top: 40px;">
								<table class="table-a">
									<tr>
										<td colspan="5" style="width:100%;">
											<div class="booking-form-i  inputBox-custom textarea-comment"  >
													<textarea id="commentContent"  cols="30" rows="5"  style="resize: none; width:99.3%; margin-bottom:-5px;"  placeholder = "댓글 작성시 타인에 대한 배려를 담아주세요" required=""></textarea>
											</div>
										</td>
										<td>
											<div style="text-align: -webkit-center;">
												<button onclick="boardFreeCommentRegistrationConfirm(${boardFree.id}, ${principal.user.id})"    class=" booking-complete-btn white-btn-custom" >등록</button>
											</div>
										</td>
									</tr>
								</table>
							</div>
							
							<!-- 댓글 작성 공간 끝-->								
							
							
							
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

<script src="/js_custom/community/boardFreeInfor.js"></script>
<%@ include file="../layout/footer.jsp"%>
