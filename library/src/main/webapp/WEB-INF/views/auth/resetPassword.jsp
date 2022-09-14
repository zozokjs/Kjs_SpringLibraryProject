<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<style>

</style>
<!-- main-cont -->
<div class="main-cont main-cont-custom">
	<div class="typography">
				<div class="content-wrapper">
			<div class="typography-heading   typography-heading-custom"   >비밀번호 재설정</div>
			<div class="typography-block">
					<div class="shortcodes-right  shortcodes-right-custom">
					
						<form action="/auth/resetPassword"  method="post" onsubmit = "return validationAll()" class="md-float-material form-material">
						
							<input type="hidden"  id="username"  name="username"  value="${user.username}"   >
					
							<div class="tables"  >
								<table class="table-a">
									<tr>
										 <td style="font-size:15px; padding-top:20px;">새 비밀번호</td>
										 <td class= "td-custom">
											<div class="input">
												<input type="password"  id="password"  name="password"  class="input-custom"   onkeyup ='checkPasswordLength()'   placeholder="새 비밀번호" required="">
											</div>
											<div  id="div_passwordLengthResult" ></div>
										</td>
									</tr>
									
									<tr>
										 <td style="font-size:15px; padding-top:20px;">새 비밀번호 확인</td>
										 <td class= "td-custom">
											<div class="input">
												<input type="password"  id="confirm-password"  name="confirm-password" class="input-custom"   onkeyup ='checkPasswordMatch()'  placeholder="새 비밀번호 확인" required="">
											</div>
											<div  id="div_passwordMatchResult" ></div>
										</td>
									</tr>
									<tr>
										<td colspan="2">
											<div style="text-align: -webkit-center;">
												<button class=" booking-complete-btn white-btn-custom" >완료</button>
											</div>
										</td>
									</tr>
								</table>
							</div>
						</form>
					</div>
					
					<div class="clear"></div>
				
			</div>
				
		</div>

	</div><!-- end of  typography -->
</div><!-- /main-cont -->


<script src="/js_custom/auth/resetPassword.js"></script>
<%@ include file="../layout/footer.jsp"%>
