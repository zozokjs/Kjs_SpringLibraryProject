<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_Community.jsp"%>
	        
	            <div class="two-colls-right"><!-- 우측 메인 시작 -->
					<div class="two-colls-right-b">
						<div class="padding">
							<div class="right-Submenu-2depth">
								자유게시판
							</div>
							
							<!-- 우측 본문 시작 -->
							<form action="/community/boardFreeRegistration"  method = "get"  enctype="multipart/form-data" >
								<sec:csrfInput/><!-- CSRF 토큰 적용 -->
								<div class="tables div-tables" >
									<div>
										<label class="inputBox-custom-important-label">*은 필수입력항목입니다</label>
									</div>
								</div>
								<table class="table-a">
									<tr>
										<td><label class="inputBox-custom-require-label">*</label>제목</td>
										<td>
											<div class="booking-form-i  inputBox-custom"  >
												<div class="input">
													<input type="text"  	id="title"  name="title" class="form-control"   placeholder="제목" required="">
												</div>
											</div>
										</td>
									</tr>
									<tr>
										<td><label class="inputBox-custom-require-label">*</label>내용</td>
										<td>
											<div class="booking-form-i">
												<textarea id="summernote" name="content"></textarea>													
											</div>
										</td>
									</tr>
								</table>
								
								<div style="text-align: -webkit-center;">
									<button class=" booking-complete-btn white-btn-custom" >등록</button>
								</div>
							</form>
							<!-- 흰색 버튼 -->
							
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

<script src="/js_custom/community/summerNoteCustom.js"></script>
<%@ include file="../layout/footer.jsp"%>
