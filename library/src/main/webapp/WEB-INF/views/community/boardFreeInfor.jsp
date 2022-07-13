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
									게시판 상세 읽기
									</h2>
								</div>
							</div>		
							<hr>	
							
							<div class="row">
								<!-- 첫째줄 -->
								<div class="col-md-12">
									<div class="form-group">
										제목
										<input type="text"  	id="title"  name="title" value = "${community.title}"   class="form-control"  onfocus= "this.blur();" readonly="readonly">
									</div>
								</div>
								
								<!-- 둘째줄 -->
								<c:choose>
									<c:when test="${empty community.editDate}">
										<div class="col-md-6">
											<div class="form-group">
												작성자
												<input type="text"  	id="title"  name="title" value = "${community.user.username}"   class="form-control"  onfocus= "this.blur();" readonly="readonly">
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												등록일
												<input type="text"  	id="title"  name="title"  value = "${createDate}"   class="form-control"  onfocus= "this.blur();" readonly="readonly">
											</div>
										</div>
									</c:when>
									<c:otherwise>
										<div class="col-md-4">
											<div class="form-group">
												작성자
												<input type="text"  	id="title"  name="title" value = "${community.user.username}"   class="form-control"  onfocus= "this.blur();" readonly="readonly">
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												등록일
												<input type="text"  	id="title"  name="title" value = "${createDate}"   class="form-control"  onfocus= "this.blur();" readonly="readonly">
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												수정일
												<input type="text"  	id="title"  name="title" value = "${community.editDate}"   class="form-control"  onfocus= "this.blur();" readonly="readonly">
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
												${community.content}
											</textarea>
										</div>										
									</div>
								</div>
								<!-- 작성자 id와 로그인한 사람의 id가 같을 때 -->
								<div class="col-md-12">
									<c:choose>
										<c:when test="${principal.user.id eq community.user.id}">
											<button onclick="location.href='/community/${community.id}/boardFreeUpdate'">수정</button>
											<button onclick="boardFreeDeleteConfirm(${community.id})">삭제</button> 
										</c:when>
									</c:choose>
								
								<br>
								<br>
								<!-- 댓글 시작 -->
								<!-- 이름 / 내용 / 작성 시간 / 삭제버튼 -->
								<div class="col-md-12">
									<table class="table">
										<tbody>
										<!-- 
											<c:forEach var="comment" items=" ${community.comment}"  varStatus="index">
												<tr>
													 <td class="tg-0pky">${comment.id}</td>
													 <td class="tg-0pky">${comment.content}</td>
													 <td class="tg-0pky">${comment.createDate}</td>
													 <td class="tg-0pky">버튼</td>
												</tr>
											</c:forEach>
											 -->
										</tbody>
									</table>
								</div>
								<!--  -->
								<hr>
								<!-- 댓글 작성 공간 -->
								
								<!-- 로그인 안 되어 있으면 표시 안 됨 -->
								<div class="col-md-12">
									<c:choose>
										<c:when test="${empty principal.user.id}">
										</c:when>
										<c:otherwise>
											<div class="row">
												<div class="col-md-10">
													<div class="form-group">
														<textarea id="commentContent"  class="form-control mb-3" cols="30" rows="5"  placeholder = "댓글 작성시 타인에 대한 배려를 담아주세요" required=""></textarea>
													</div>
												</div>
												<div class="col-md-2">
													<button onclick="boardFreeCommentRegistration(${community.id}, ${principal.user.id})" >등록</button>
												</div>
											</div>
										</c:otherwise>
									</c:choose>
								</div>
								
								<!-- 댓글 끝 -->
								
							</div>
							
						</div>
					</div>
				</div>
				<!-- 우측 메인 End-->
				
	    	</div>
	    </div>
	</section>
</div>

<script src="/js_custom/community/boardFreeInfor.js"></script>
<%@ include file="../layout/footer.jsp"%>
