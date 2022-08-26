<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_Community.jsp"%>
	        
				
				<div class="two-colls-right"><!-- 우측 메인 시작 -->
					<div class="two-colls-right-b">
						<div class="padding">
							<div class="right-Submenu-2depth">
								1대1 문의 작성
							</div>
 							
							<!-- 우측 본문 시작 -->
							<div class="typography" style="padding-bottom:30px;">
								<div class="content-wrapper">
									<div class="block-qoutes">
										<label class="inputBox-custom-important-label">*등록된 질문글은 수정할 수 없습니다.</label>
									</div>
								</div>
							</div>	
							<form action="/community/singleQuestionRegistration"  method = "get"  enctype="multipart/form-data" >
								<div class="tables" style="">
									<table class="table-a">
										<tr>	
											<td>제목</td>
											<td>
												<div class="input">
													<input type="text"  id="title"  name="title" class="form-control"   placeholder="제목" required="">
												</div>
											</td>
										</tr>
										<tr>	
											<td>내용</td>
											<td colspan="3">
												<div class="booking-form-i  inputBox-custom"  >
													<div class="input">
														<div>
															<textarea  id="content"  name="content" cols="30" rows="5"  style="resize: none; width:99.3%; margin-bottom:-5px;"  placeholder="등록된 질문글은 수정할 수 없습니다" ></textarea>
														</div>
													</div>
												</div>
											</td>
										</tr>
									</table>
								</div>
								<div style="text-align: -webkit-center;">
									<button class=" booking-complete-btn white-btn-custom" >질문 등록</button>
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

<%@ include file="../layout/footer.jsp"%>
