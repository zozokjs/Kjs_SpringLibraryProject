<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
									공지사항 게시판 상세 읽기
									</h2>
								</div>
							</div>		
							<hr>	
							
							<input type ="hidden" id="loginUserId" value="${principal.user.id}">
							<input type ="hidden" id="boardNoticeId" value="${boardNotice.id}">
							
							
							<div class="row">
								<!-- 첫째줄 -->
								<div class="col-md-12">
									<div class="form-group">
										제목
										<input type="text"  	id="title"  name="title" value = "${boardNotice.title}"   class="form-control"  onfocus= "this.blur();" readonly="readonly">
									</div>
								</div>
								
								<!-- 둘째줄 -->
								<c:choose>
									<c:when test="${empty boardNotice.editDate}">
										<div class="col-md-6">
											<div class="form-group">
												작성자
												 <input type="text"  	id="title"  name="title" value = "${boardNotice.user.username}"   class="form-control"  onfocus= "this.blur();" readonly="readonly">
										</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												등록일
												<input type="text"  	id="title"  name="title"  value = "${boardNotice.createDateFormatted}"   class="form-control"  onfocus= "this.blur();" readonly="readonly">
											</div>
										</div>
									</c:when>
									<c:otherwise>
										<div class="col-md-4">
											<div class="form-group">
												작성자
												<input type="text"  	id="title"  name="title" value = "${boardNotice.user.username}"   class="form-control"  onfocus= "this.blur();" readonly="readonly">
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												등록일
												<input type="text"  	id="title"  name="title" value = "${boardNotice.createDateFormatted}"   class="form-control"  onfocus= "this.blur();" readonly="readonly">
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												수정일
												<input type="text"  	id="title"  name="title" value = "${boardNotice.editDate}"   class="form-control"  onfocus= "this.blur();" readonly="readonly">
											</div>
										</div>
									</c:otherwise>
								</c:choose>
							
								<!-- 셋째줄 -->
								<div class="col-md-12">
									<div class="form-group">
										내용
										<div>
											<textarea  id="contents"  name="contents" class="form-control mb-3"  cols="30" rows="5" onfocus= "this.blur();"  readonly="readonly">
												${boardNotice.content}
											</textarea>
										</div>										
									</div>
								</div>
								<!-- 작성자 id와 로그인한 사람의 id가 같을 때 -->
								<div class="col-md-12">
									<c:choose>
										<c:when test="${principal.user.id eq boardNotice.user.id}">
											<button onclick="location.href='/community/${boardNotice.id}/boardNoticeUpdate'">수정</button>
											<button onclick="boardNoticeDeleteConfirm(${boardNotice.id})">삭제</button> 
										</c:when>
									</c:choose> 
								</div>
								<br>
								<br>
								
							</div>
							
						</div>
					</div>
				</div>
				<!-- 우측 메인 End-->
				
	    	</div>
	    </div>
	</section>
</div>

<script src="/js_custom/community/boardNoticeInfor.js"></script>
<%@ include file="../layout/footer.jsp"%>
