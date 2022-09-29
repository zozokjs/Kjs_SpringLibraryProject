<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_Community.jsp"%>
				
				
				<div class="two-colls-right"><!-- 우측 메인 시작 -->
					<div class="two-colls-right-b">
						<div class="padding">
							<div class="right-Submenu-2depth">
								1대일 질문하기 - 상세
							</div>
							
							<input type ="hidden" id="loginUserId" value="${principal.user.id}">
							<input type ="hidden" id="sqResponseDtoId" value="${sqResponseDto.id}">
							
							
							<!-- 우측 본문 시작 -->
							<div class="tables div-tables"  >
								<table class="table-a">
									<tr>
										<td>제목</td>
										<td colspan="3">${sqResponseDto.title}</td>
									</tr>

									<tr>
										<td>작성자</td>
										<td>${sqResponseDto.user.username}</td>
										<td>등록일</td>
										<td>${sqResponseDto.createDateFormatted}</td>
									</tr>

									<tr>
										<td>내용</td>
										<td colspan="5" class="tables-td">
											<div class="typography  div-typography" >
												<div class="content-wrapper">
													<div class="block-qoutes">
														<p>
															${sqResponseDto.content}
														</p>
													</div>
												</div>
											</div>
									
										</td>
									</tr>
								</table>
							</div>
							
							<!-- 흰색 버튼 -->
							<c:choose>
								<c:when test="${principal.user.id eq sqResponseDto.user.id}">
									<div style="text-align: -webkit-center;">
										<button class=" booking-complete-btn white-btn-custom"  onclick="singleQuestionDeleteConfirm(${sqResponseDto.id})" >삭제</button>
									</div>
								</c:when>
							</c:choose> 
							<hr>
		
							<div>
							답변내용
							</div>
							
							<!-- 답변 작성 공간 시작-->
							<div >
								<c:choose>
									<c:when test="${empty sqResponseDto.commentSQ.content}">
										<div class="typography" style="padding-bottom:30px;">
											<div class="content-wrapper">
												<div class="block-qoutes">
													<p>
														답변을 준비하고 있습니다.
													</p>
												</div>
											</div>
										</div>
										
										
										<c:if test="${principal.user.roleType eq 'SASEO' || principal.user.roleType eq 'ADMIN'}">
											<div class="booking-form-i">
												<div class="textarea"  style="margin: 10px; padding: 1px;">
													<textarea  id="commentContent"  class=""  cols="30" rows="5" placeholder = "답변 작성 시 수정 불가" required=""></textarea>
												</div>
											</div>
											<div style="text-align: -webkit-center;">
												<button class=" booking-complete-btn white-btn-custom"  onclick="singleQuestionCommentRegistrationConfirm(${sqResponseDto.id}, ${principal.user.id})"  >등록</button>
											</div>
										</c:if>	
									</c:when>
									
									<c:otherwise>
										<div class="typography" style="padding-bottom:30px;">
											<div class="content-wrapper">
												<div class="block-qoutes">
													<p>
														${sqResponseDto.commentSQ.content}
													</p>
												</div>
											</div>
										</div>
									</c:otherwise>
									
								</c:choose>
							</div>
						
							
				
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

<script src="/js_custom/community/singleQuestionInfor.js"></script>
<%@ include file="../layout/footer.jsp"%>
