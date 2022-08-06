<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_Community.jsp"%>
<style>
	textarea {
		resize:none;
	}
</style>
	        
	            <!-- 우측 메인 -->
	            <div class="col-lg-9">
	               <div class="row">
						<div class="col-lg-12 mb-5">
						
							<!-- 2depth 타이틀 영역 -->
							<div class="col-lg-7">
								<div class="">
									<h2 class="mt-3 content-title ">
									1대1문의 게시판  상세 읽기
									</h2>
								</div>
							</div>		
							<hr>	
							
							<input type ="hidden" id="loginUserId" value="${principal.user.id}">
							<input type ="hidden" id="sqResponseDtoId" value="${sqResponseDto.id}">
							
							
							<div class="row">
								<!-- 첫째줄 -->
								<div class="col-md-12">
									<div class="form-group">
										제목
										<input type="text"  	id="title"  name="title" value = "${sqResponseDto.title}"   class="form-control"  onfocus= "this.blur();" readonly="readonly">
									</div>
								</div>
								
								<!-- 둘째줄 -->
								<div class="col-md-6">
									<div class="form-group">
										작성자
										<input type="text"  	id="title"  name="title" value = "${sqResponseDto.user.username}"   class="form-control"  onfocus= "this.blur();" readonly="readonly">
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										등록일
										<input type="text"  	id="title"  name="title" value = "${sqResponseDto.createDateFormatted}"   class="form-control"  onfocus= "this.blur();" readonly="readonly">
									</div>
								</div>
							
								<!-- 셋째줄 -->
								<div class="col-md-12">
									<div class="form-group">
										내용
										<div>
											<textarea  id="contents"  name="contents" class="form-control mb-3"  cols="30" rows="5" onfocus= "this.blur();"  readonly="readonly">
												${sqResponseDto.content}
											</textarea>
										</div>										
									</div>
								</div>
								
								<!-- 작성자 id와 로그인한 사람의 id가 같을 때 -->
								<!-- 작성자만 삭제 가능 -->
								<div class="col-md-12">
									<c:choose>
										<c:when test="${principal.user.id eq sqResponseDto.user.id}">
											<button onclick="singleQuestionDeleteConfirm(${sqResponseDto.id})">삭제</button> 
										</c:when>
									</c:choose> 
								</div>
								<br>
								<br>
								
								<div class="col-md-12">
								답변내용
								</div>
								
								<!-- 답변 작성 공간 시작-->
								<div class="col-md-12">
									<div class="form-group">
										<c:choose>
											<c:when test="${empty sqResponseDto.commentSQ.content}">
												<textarea class="form-control mb-3" cols="30" rows="5"  placeholder = ""  onfocus= "this.blur();" readonly="true">답변을 준비하고 있습니다.</textarea>
												
											
												<c:if test="${principal.user.roleType eq 'SASEO' || principal.user.roleType eq 'ADMIN'}">
													<textarea id="commentContent"  class="form-control mb-3" cols="30" rows="5"  placeholder = "답변 작성 시 수정 불가" required=""></textarea>
													<div class="col-md-2">
														<button onclick="singleQuestionCommentRegistrationConfirm(${sqResponseDto.id}, ${principal.user.id})" >등록</button>
													</div>
												</c:if>	
											</c:when>
											
											<c:otherwise>
												<textarea id="commentContentEdit"   class="form-control mb-3" cols="30" rows="5"   placeholder = ""  readonly="true">${sqResponseDto.commentSQ.content}</textarea>
											</c:otherwise>
											
										</c:choose>
									</div>
								</div>
								 <!--답변 작성 공간 끝 -->
								
							</div>
							
						</div>
					</div>
				</div>
				<!-- 우측 메인 End-->
				
	    	</div>
	    </div>
	</section>
</div>

<script src="/js_custom/community/singleQuestionInfor.js"></script>
<%@ include file="../layout/footer.jsp"%>
