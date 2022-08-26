<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_User.jsp"%>
	        
		
				<div class="two-colls-right"><!-- 우측 메인 시작 -->
					<div class="two-colls-right-b">
						<div class="padding">
							<div class="right-Submenu-2depth">
								<!-- 1depth Menu -->
								회원 정보 수정 전 비밀번호 확인ㅓ
							</div>
							
							<input type="hidden"  id ="userId" value="${principal.user.id}">
							
							<div class="tables" style="margin-top: 70px;">
								<table class="table-a">
									<tr>
										<td colspan="4">개인 정보 보호를 위해 기존 비밀번호를 입력해주세요</td>
									</tr>

									<tr>
										<td>비밀번호</td>
										<td colspan="3">
											<div class="booking-form-i inputBox-custom"  >
												<div class="input">
													<input type="password"  id="password" name="password"   class="form-control" required="">
												</div>
											</div>
											<div  id="div_passwordMatchProcessResult" ></div>
										</td>
									</tr>
								</table>
							</div>
							<div style="text-align: -webkit-center;">
								<button class=" booking-complete-btn white-btn-custom"  onclick="passwordMatchChecker()">확인</button>
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

<script src="/js_custom/user/userUpdateBefore.js"></script>
<%@ include file="../layout/footer.jsp"%>