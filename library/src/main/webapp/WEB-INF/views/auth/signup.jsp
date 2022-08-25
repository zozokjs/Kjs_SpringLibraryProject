<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_Auth.jsp"%>

<style>
	.table-a th {
		font-size: 15px;
		text-align: center;
		width: 200px;
	}
	
	

</style>
				
				<div class="two-colls-right"><!-- 우측 메인 시작 -->
					<div class="two-colls-right-b">
						<div class="padding">
							<div class="right-Submenu-2depth">
								회원가입
							</div>
							
							<!-- 우측 본문 시작 -->
							<form action="/auth/signup" method="post" onsubmit = "return validationAll()" class="md-float-material form-material">
                   			
                   			
							<div class="tables" style="margin-top: 70px;">
								<div class="">
								<div>
									<label class="inputBox-custom-important-label">*은 필수입력항목입니다</label>
								</div>
									<table class="table-a">
										<tr>
											<th><label class="inputBox-custom-require-label">*</label>아이디</th>
											<td colspan="3">
												<div class="booking-form-i  inputBox-custom"  >
													<div class="input">
														<input type="text"  id ="username" name="username" onkeyup ='findByUsernameRealtime()' required=""  >
													</div>
												</div>
												<div  id="div_usernameResult" ></div>
											</td>
										</tr>
										<tr>
											<th><label class="inputBox-custom-require-label">*</label>비밀번호</th>
											<td colspan="3">
												<div class="booking-form-i-short   inputBox-custom"  >
													<div class="input">
														<input type="password"  id ="password"  name="password"  onkeyup ='checkPasswordLength()'  required=""  >
													</div>
												</div>
												<div  id="div_passwordLengthResult" ></div>
											</td>
										</tr>
										<tr>
											<th><label class="inputBox-custom-require-label">*</label>비밀번호 확인</th>
											<td colspan="3">
												<div class="booking-form-i-short    inputBox-custom"  >
													<div class="input">
														<input type="password"  id ="confirm-password"  name="confirm-password"   onkeyup ='checkPasswordMatch()' required=""  >
													</div>
												</div>
												<div  id="div_passwordMatchResult" ></div>
											</td>
										</tr>
										<tr>
											<th><label class="inputBox-custom-require-label">*</label>이메일</th>
											<td colspan="3">
												<div class="booking-form-i inputBox-custom"  style="width:60%;">
													<div class="input">
														<input type="text"  id = "email"  name="email"  value="zozokjs@naver.com" required=""  >
													</div>
												</div>
												<div id="div_emailAuthSendButton"  style="text-align: -webkit-center;">
											    	<!--추가 버튼-->
													<button id="emailAuthSendButton"  onclick ="emailAuthenticationSending_Before(event)" class=" booking-complete-btn white-btn-custom" >인증번호전송</button>
												</div>
											</td>
										</tr>
										
										<tr id="div_emailAuthentication"></tr>
										<tr>
											<th>이름</th>
											<td colspan="3">
												<div class="booking-form-i inputBox-custom"  >
													<div class="input">
														<input type="text" name="name" >
													</div>
												</div>
											</td>
										</tr>
										<tr>
											<th>생년월일</th>
											<td colspan="3">
												<div class="booking-form-i inputBox-custom"  >
													<div class="input">
														<input type="text" name="birth" >
													</div>
												</div>
											</td>
										</tr>
										<tr>
											<th>국가</th>
											<td colspan="3">
												<div class="card-expiration"  style="width:100%;">
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
										<tr>
											<th>종족</th>
											<td colspan="3">
												<div class="card-expiration"  style="width:100%;">
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
										<tr>
											<th>직업</th>
											<td colspan="3">
												<div class="card-expiration"  style="width:100%;">
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
										<tr>
											<th><label class="inputBox-custom-require-label">*</label>연락처</th>
											<td colspan="3">
												<div class="booking-form-i inputBox-custom"  >
													<div class="input">
														<input type="text" name="phoneNumber"  required=""  >
													</div>
												</div>
											</td>
										</tr>
										<tr>
											<th>주소</th>
											<td colspan="3">
												<div class="booking-form-i inputBox-custom"  >
													<div class="input">
														<input type="text"  name="address" >
													</div>
												</div>
											</td>
										</tr>
									</table>
									<button class="booking-complete-btn">가입</button>
								</div>
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
<%@ include file="../layout/footer.jsp"%>