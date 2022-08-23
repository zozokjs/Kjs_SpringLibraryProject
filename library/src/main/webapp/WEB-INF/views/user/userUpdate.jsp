<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_User.jsp"%>
	        
		
				<div class="two-colls-right"><!-- 우측 메인 시작 -->
					<div class="two-colls-right-b">
						<div class="padding">
							<div class="right-Submenu-2depth">
								<!-- 1depth Menu -->
								회원 정보 수정
							</div>
							
							<form id="userUpdate"  onsubmit="userUpdate(${principal.user.id},event)" class="contact-form bg-white rounded p-5"  >
								
								<div class="tables" style="margin-top: 70px;">
								
									<div>
										<label class="inputBox-custom-important-label">*은 필수입력항목입니다</label>
									</div>
									<table class="table-a">
										<tr>
											<th>아이디</th>
											<td colspan="">${principal.user.username}
												<%-- <div class="booking-form-i inputBox-custom"  >
													<div class="input">
														<input type="text"   id="username"  name="username"  value = "${principal.user.username}"  class="form-control"  placeholder="아이디" readonly="readonly">
													</div>
												</div> --%>
											</td>
											<th><label class="inputBox-custom-require-label">*</label>이메일</th>
											<td colspan="3">
												<div class="booking-form-i inputBox-custom"  >
													<div class="input">
														<input type="text"  id="email"  name="email"  value = "${principal.user.email}"   class="form-control"   placeholder="이메일"  required="">
													</div>
												</div>
											</td>
										</tr>
										
										<tr>
											<th><label class="inputBox-custom-require-label">*</label>새비밀번호</th>
											<td colspan="">
												<div class="booking-form-i-short inputBox-custom"  >
													<div class="input">
														<input type="password"  id ="password"  name="password"  onkeyup ='checkPasswordLength()'  required=""  >
													</div>
												</div>
												<div  id="div_passwordLengthResult" ></div>
											</td>
											<th><label class="inputBox-custom-require-label">*</label>새 비밀번호 확인</th>
											<td colspan="">
												<div class="booking-form-i-short inputBox-custom"  >
													<div class="input">
														<input type="password"  id ="confirm-password"  name="confirm-password"   onkeyup ='checkPasswordMatch()' required=""  >
													</div>
												</div>
												<div  id="div_passwordMatchResult" ></div>
											</td>
										</tr>
										
										<tr>
											<th>이름</th>
											<td colspan="">${principal.user.name}
												<%-- <div class="booking-form-i inputBox-custom"  >
													<div class="input">
														<input type="text"  id="name"  name="name"  value = "${principal.user.name}"   class="form-control"   placeholder="이름" readonly="readonly">
													</div>
												</div> --%>
											</td>
											<th>생년월일</th>
											<td colspan="">${principal.user.birth}
												<%-- <div class="booking-form-i inputBox-custom"  >
													<div class="input">
														<input type="text"  id="birth"  name="birth"  value = "${principal.user.birth}"   class="form-control"   placeholder="생년월일" readonly="readonly">
													</div>
												</div> --%>
											</td>
										</tr>
										
										<tr>
											<th>국가</th>
											<td colspan="">
												<div class="card-expiration"  style="width:100%;">
													<select name="country" class="selectBox-custom" disabled>
														<option value="1"   ${principal.user.country == '1' ? 'selected = "selected"' : ''} >토르두스</option>
														<option value="2"  ${principal.user.country == '2' ? 'selected = "selected"' : ''} >일레니아</option>		
														<option value="3"  ${principal.user.country == '3' ? 'selected = "selected"' : ''} >카리우스</option>		
														<option value="4"  ${principal.user.country == '4' ? 'selected = "selected"' : ''} >플러터누스</option>		
														<option value="5"  ${principal.user.country == '5' ? 'selected = "selected"' : ''} >플러터누스</option>		
														<option value="0"  ${principal.user.country == '0' ? 'selected = "selected"' : ''} >그 외</option>		
													</select>
												</div>
											</td>
											<th>종족</th>
											<td colspan="">
												<div class="card-expiration"  style="width:100%;">
													<select name="species" class="selectBox-custom" disabled>
														<option value="1"   ${principal.user.species == '1' ? 'selected = "selected"' : ''} >Human</option>
														<option value="2"  ${principal.user.species == '2' ? 'selected = "selected"' : ''} >Elf</option>				
														<option value="3"  ${principal.user.species == '3' ? 'selected = "selected"' : ''} >Dwarf</option>				
														<option value="4"  ${principal.user.species == '4' ? 'selected = "selected"' : ''} >Electron</option>				
														<option value="5"  ${principal.user.species == '5' ? 'selected = "selected"' : ''} >Fairy</option>				
														<option value="6"  ${principal.user.species == '6' ? 'selected = "selected"' : ''} >Furry</option>				
														<option value="0"  ${principal.user.species == '0' ? 'selected = "selected"' : ''} >Other</option>				
													</select>
												</div>
											</td>
										</tr>
										
										<tr>
											<th>직업</th>
											<td colspan="3">
												<div class="card-expiration"  style="width:100%;">
													<select name="job" class="selectBox-custom" >
														<option value="1"   ${principal.user.job == '1' ? 'selected = "selected"' : ''} >학생</option>
														<option value="2"  ${principal.user.job == '2' ? 'selected = "selected"' : ''} >공무원</option>		
														<option value="3"  ${principal.user.job == '3' ? 'selected = "selected"' : ''} >회사원</option>		
														<option value="4"  ${principal.user.job == '4' ? 'selected = "selected"' : ''} >전문직</option>		
														<option value="5"  ${principal.user.job == '5' ? 'selected = "selected"' : ''} >자영업</option>		
														<option value="0"  ${principal.user.job == '0' ? 'selected = "selected"' : ''} >그 외</option>		
													</select>
												</div>
											</td>
										</tr>
										
										<tr>
											<th><label class="inputBox-custom-require-label">*</label>연락처</th>
											<td colspan="3">
												<div class="booking-form-i inputBox-custom"  >
													<div class="input">
														<input type="text"   id="phoneNumber"  name="phoneNumber"  value = "${principal.user.phoneNumber}"  class="form-control"  placeholder="연락처"  required="">
													</div>
												</div>
											</td>
										</tr>
										
									
										
										<tr>
											<th>주소</th>
											<td colspan="3">
												<div class="booking-form-i inputBox-custom"  >
													<div class="input">
														<input type="text"  id="address"  name="address"  value = "${principal.user.address}"   class="form-control"   placeholder="주소"  >
													</div>
												</div>
											</td>
										</tr>
									</table>
								</div>
								<div style="text-align: -webkit-center;">
									<button class=" booking-complete-btn white-btn-custom" >수정 완료</button>
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

<script src="/js_custom/user/userUpdate.js"></script>
<%@ include file="../layout/footer.jsp"%>