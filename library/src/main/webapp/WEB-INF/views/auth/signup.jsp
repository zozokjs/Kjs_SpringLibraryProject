<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_Auth.jsp"%>

<style>

	

</style>
				
				<div class="two-colls-right"><!-- 우측 메인 시작 -->
					<div class="two-colls-right-b">
						<div class="padding">
							<div class="right-Submenu-2depth">
								회원가입
							</div>
							
							<!-- 우측 본문 시작 -->
							<form action="/auth/signup" method="post" onsubmit = "return validationAll()" class="md-float-material form-material">
                   			
                   			<sec:csrfInput/><!-- CSRF 토큰 적용 -->
							<div class="tables" style="margin-top: 70px;">
								<div>
									
								</div>
									<table class="table-a">
									<tbody>
									
										<!--  0  -->
										<!-- 이 항목이 반드시 있어야 모바일 화면에서 테이블 형태가 유지됨-->
										<tr>
											<th colspan="3"  style="">
												<label class="inputBox-custom-important-label">*은 필수입력항목입니다</label>
											</th>
										</tr>
										
										<!--  1  -->
										<tr>
											<th><label class="inputBox-custom-require-label">*</label>아이디</th>
											<td colspan="2">
												<div class="booking-form-i  inputBox-custom"  >
													<div class="input">
														<input type="text"  id ="username" name="username" onkeyup ='findByUsernameRealtime()' required=""  >
													</div>
												</div>
												<div  id="div_usernameResult" ></div>
											</td>
										</tr>
										
										<!--  2  -->
										<tr>
											<th><label class="inputBox-custom-require-label">*</label>비밀번호</th>
											<td colspan="2">
												<div class="booking-form-i-short   inputBox-custom"  >
													<div class="input">
														<input type="password"  id ="password"  name="password"  onkeyup ='checkPasswordFormat()'  required=""  >
													</div>
												</div>
												<div  id="div_passwordFormatResult" ></div>
											</td>
										</tr>
										
										<!--  3  -->
										<tr>
											<th><label class="inputBox-custom-require-label">*</label>비밀번호 확인</th>
											<td colspan="2">
												<div class="booking-form-i-short    inputBox-custom"  >
													<div class="input">
														<input type="password"  id ="confirm-password"  name="confirm-password"   onkeyup ='checkPasswordMatch()'  required="" >
													</div>
												</div>
												<div  id="div_passwordMatchResult" ></div>
											</td>
										</tr>
										
										<!--  4  -->
										<tr>
											<th><label class="inputBox-custom-require-label">*</label>이메일</th>
											<td colspan=""  style="width:50%;">
												<div class="booking-form-i inputBox-custom  div-width-full"  >
													<div class="input">
														<input type="text"  id = "email"  name="email"   onkeyup ='checkEmailFormat()' required=""  >
													</div>
												</div>
												<div  id="div_emailFormatResult" ></div>
												</td>
												<td>
													<div id="div_emailAuthSendButton"  style="text-align: -webkit-center;">
												    	<!--추가 버튼-->
														<button id="emailAuthSendButton"  onclick ="emailAuthenticationSending_Before(event)" class=" booking-complete-btn white-btn-short-custom" >인증번호전송</button>
													</div>
												</td>
										</tr>
										
										<!--  4.5  -->
										<tr id="div_emailAuthentication"></tr>
										
										<!--  5 -->
										<tr>
											<th>이름</th>
											<td colspan="2">
												<div class="booking-form-i inputBox-custom"  >
													<div class="input">
														<input type="text" name="name" >
													</div>
												</div>
											</td>
										</tr>
										
										<!--  6  -->
										<tr>
											<th>생년월일</th>
											<td colspan="2">
												<div class="booking-form-i inputBox-custom"  >
													<div class="input">
														<input type="text" name="birth" >
													</div>
												</div>
											</td>
										</tr>
										
										<!--  7  -->
										<tr>
											<th>국가</th>
											<td colspan="2">
												<div class="card-expiration  div-width-full"  >
													<select name="country" class="selectBox-custom" >
														<option value="1">토르두스</option>
														<option value="2">파이시아</option>
														<option value="3">가넴</option>
														<option value="4">스위프</option>
														<option value="5">펄</option>
														<option value="0">그 외</option>
													</select>
												</div>
											</td>
										</tr>
										
										<!--  8  -->
										<tr>
											<th>종족</th>
											<td colspan="2">
												<div class="card-expiration  div-width-full" >
													<select name="species" class="selectBox-custom">
														<option value="1">Human</option>
														<option value="2">Elf</option>
														<option value="3">Dwarf</option>
														<option value="4">Electron</option>
														<option value="5">Fairy</option>
														<option value="6">Furry</option>
														<option value="0">Other</option>
													</select>
												</div>
											</td>
										</tr>
										
										<!--  9  -->
										<tr>
											<th>직업</th>
											<td colspan="2">
												<div class="card-expiration  div-width-full" >
													<select name="job" class="selectBox-custom">
														<option value="1">학생</option>
														<option value="2">공무원</option>
														<option value="3">회사원</option>
														<option value="4">전문직</option>
														<option value="5">자영업</option>
														<option value="0">그 외</option>
													</select>
												</div>
											</td>
										</tr>
										
										<!--  10  -->
										<tr>
											<th><label class="inputBox-custom-require-label">*</label>연락처</th>
											<td colspan="2">
												<div class="booking-form-i inputBox-custom"  >
													<div class="input">
														<input type="text"  id= "phoneNumber"  name="phoneNumber"  required=""  onkeyup ='checkPhoneNumberFormat()'>
													</div>
												</div>
												<div  id="div_phoneNumberFormatResult" ></div>
											</td>
										</tr>
										
										<!--  11  -->
										<tr>
											<th>주소</th>
											<td colspan="2">
												<div class="booking-form-i inputBox-custom"  >
													<div class="input">
														<input type="text"  name="address" >
													</div>
												</div>
											</td>
										</tr>
										</tbody>
									</table>
									<button class="booking-complete-btn">가입</button>
								<div class="clear"></div>
							</div>
							</form>
							<!-- 우측 본문 끝 -->
			
							
						</div><!-- end of class padding -->
						<div class="clear" ></div>
					</div>
				</div><!-- 우측 메인 끝 -->
				<div class="clear"></div>
				
			</div><!-- end of class two-colls(submenu_guide) -->
			<div class="clear"></div><!-- 필수 -->
		</div>
	</div>
</div><!-- /main-cont -->


<script src="/js_custom/auth/signup.js"></script>
<script src="/js_custom/auth/signupFormatChecker.js"></script>
<%@ include file="../layout/footer.jsp"%>