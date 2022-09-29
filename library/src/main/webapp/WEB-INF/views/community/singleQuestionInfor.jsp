<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_Community.jsp"%>
<link rel="stylesheet" href="/css_custom/community/singleQuestionInfor.css" />
				
				<div class="two-colls-right"><!-- 우측 메인 시작 -->
					<div class="two-colls-right-b">
						<div class="padding">
							<div class="right-Submenu-2depth">
								1대일 질문하기 
							</div>
							
							<input type ="hidden" id="loginUserId" value="${principal.user.id}">
							<input type ="hidden" id="sqResponseDtoId" value="${sqResponseDto.id}">
							
							
							<!-- 우측 본문 시작 -->
							<div class="tables div-tables"  >
								<table class="table-a">
									<tr>
										<td colspan="6"  class="table-td-title">${sqResponseDto.title}</td>
									</tr>

									<tr>
										<td class="table-td-info-left">작성자</td>
										<td  colspan="2"  >${sqResponseDto.user.username}</td>
										<td>등록일</td>
										<td  colspan="2"  class="table-td-info-right">${sqResponseDto.createDateFormatted}</td>
									</tr>

									<tr>
										<td colspan="6"  class="table-td-content" >
											<p>
												${sqResponseDto.content}
											</p>
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

										<div class="tables div-tables"  >
											<table class="table-a">
												<tr>
													<td colspan="6"  class="table-td-title">[Re] ${sqResponseDto.title}</td>
												</tr>
												<tr>
													<td class="table-td-info-left">작성자</td>
													<td  colspan="2"  >사서</td>
													<td>답변일</td>
													<td  colspan="2"  class="table-td-info-right">
														<fmt:parseDate value="${sqResponseDto.commentSQ.createDate}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
														<fmt:formatDate value="${parsedDateTime}" pattern="yyyy년 MM월 dd일 HH시 mm분 ss초" />
													</td>
												</tr>
			
												<tr>
													<td colspan="6"  class="table-td-content" >
														<p>
															${sqResponseDto.commentSQ.content}
														</p>
													</td>
												</tr>
											</table>
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
