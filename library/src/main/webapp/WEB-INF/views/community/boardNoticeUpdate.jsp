<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_Community.jsp"%>
	        
	       	
				<div class="two-colls-right"><!-- 우측 메인 시작 -->
					<div class="two-colls-right-b">
						<div class="padding">
							<div class="right-Submenu-2depth">
								공지사항
							</div>
							
							<form action="/community/${boardNotice.id}/boardNoticeUpdate"   method = "post" enctype="multipart/form-data" >
								<sec:csrfInput/><!-- CSRF 토큰 적용 -->
								<!-- 우측 본문 시작 -->
								<div class="tables   div-tables" >
									<table class="table-a">
										<tr>
											<td>제목</td>
											<td colspan="3">
												<div class="input">
														<input type="text"  	id="title"  name="title"  value = "${boardNotice.title}"    class="form-control"   placeholder="제목" required="" readonly="readonly">
													</div>
											</td>
										</tr>
										
										<tr>
											<td>작성자</td>
											<td>${boardNotice.user.username}</td>
											<td>등록일</td>
											<td>${boardNotice.createDate}</td>
										</tr>
									
										<tr>
											<td>내용</td>
											<td colspan="3">
												<div class="booking-form-i  inputBox-custom"  >
													<div class="input">
														<textarea id="summernote" name="content">${boardNotice.content}</textarea>
													</div>
												</div>
											</td>
										</tr>
									</table>
								</div>
								
								<!-- 흰색 버튼 -->
								<div style="text-align: -webkit-center;">
									<button class=" booking-complete-btn white-btn-custom"  '>수정 완료</button>
								</div>
							</form>
							
				
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

<script src="/js_custom/community/summerNoteCustom.js"></script>
<%@ include file="../layout/footer.jsp"%>