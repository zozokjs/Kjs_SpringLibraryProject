<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_User.jsp"%>
	        
	       	
				<div class="two-colls-right"><!-- 우측 메인 시작 -->
					<div class="two-colls-right-b">
						<div class="padding">
							<div class="right-Submenu-2depth">
								회원 정보
							</div>
							
							
							
							<!-- 우측 본문 시작 -->
							<form id="comment-form" action="/user/userUpdateBefore/${principal.user.username}"  method = "Post" enctype="multipart/form-data" class="contact-form bg-white rounded p-5"  >
							<!-- 수정 화면으로 진입시, POST 요청을 위해 form 방식 사용 -->
								<sec:csrfInput/><!-- CSRF 토큰 적용 -->
								<div class="tables">
									<table class="table-a">
										<tr>
											<td>아이디</td>
											<td colspan="3">${principal.user.username}</td>
										</tr>
	
										<tr>
											<td>이메일</td>
											<td colspan="3">${principal.user.email}</td>
										</tr>
	
										<tr>
											<td>이름</td>
											<td colspan="">${principal.user.name}</td>
											<td>생년월일</td>
											<td colspan="">${principal.user.birth}</td>
										</tr>
											
										<tr>	
											<td>국가</td>
											<td colspan="">
												<div class="card-expiration"  style="width:100%;">
													<select name="country" class="selectBox-custom"  disabled>
														<option value="1"   ${principal.user.country == '1' ? 'selected = "selected"' : ''} >토르두스</option>
														<option value="2"  ${principal.user.country == '2' ? 'selected = "selected"' : ''} >일레니아</option>		
														<option value="3"  ${principal.user.country == '3' ? 'selected = "selected"' : ''} >카리우스</option>		
														<option value="4"  ${principal.user.country == '4' ? 'selected = "selected"' : ''} >플러터누스</option>		
														<option value="5"  ${principal.user.country == '5' ? 'selected = "selected"' : ''} >플러터누스</option>		
														<option value="0"  ${principal.user.country == '0' ? 'selected = "selected"' : ''} >그 외</option>		
													</select>
												</div>
											</td>
										
											<td>종족</td>
											<td colspan="">
												<div class="card-expiration"  style="width:100%;">
													<select name="bindType" class="selectBox-custom"  disabled>
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
											<td>직업</td>
											<td colspan="3">
												<div class="card-expiration"  style="width:100%;">
													<select name="job" class="selectBox-custom"  disabled>
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
											<td>연락처</td>
											<td colspan="3">${principal.user.phoneNumber}</td>
										</tr>
										
										<tr>
											<td>주소</td>
											<td colspan="3">${principal.user.address}</td>
										</tr>
									</table>
								</div>
									<!-- 흰색 버튼 -->
								<div style="display:flex; justify-content:space-around;">
									<button class=" booking-complete-btn white-btn-custom" >수정</button>
									<button onclick = "exit()" class=" booking-complete-btn white-btn-custom" >탈퇴</button> 
								</div>	
							</form>
					
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

<%@ include file="../layout/footer.jsp"%>