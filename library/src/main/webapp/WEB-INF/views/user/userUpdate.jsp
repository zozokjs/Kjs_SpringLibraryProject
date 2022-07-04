<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_User.jsp"%>
	        
	            <!-- 우측 메인 -->
	            <div class="col-lg-8">
	               <div class="row">
						<div class="col-lg-12 mb-5">
						
							<div class="col-lg-7">
								<div class="">
									<h2 class="mt-3 content-title ">
									<!-- 추후에 도서 대출 내역으로 바꾸기 -->
									회원 정보 수정
									</h2>
								</div>
							</div>		
							<hr>	
							
							<!-- 내용 Start-->
							<form id="userUpdate"  onsubmit="userUpdate(${principal.user.id},event)" class="contact-form bg-white rounded p-5"  >
								<h4 class="mb-4">회원 정보를 수정합니다.</h4>
							
								
								<!-- 아이디 -->
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											아이디<input type="text"   id="username"  name="username"  value = "${principal.user.username}"  class="form-control"  placeholder="아이디" readonly="readonly">
										</div>
									</div>
								</div>
								
								<!-- 비밀번호 -->
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											비밀번호<label style="color:red";>(필수)</label><input type="text"   id="password"  name="password"  class="form-control"  placeholder="비밀번호"  required="">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											비밀번호확인<label style="color:red";>(필수)</label><input type="text"   id="confirm-password"  name="confirm-password"  class="form-control"  placeholder="비밀번호확인"  required="">
										</div>
									</div>
								</div>
								
								<!-- 이메일 -->
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											이메일<label style="color:red";>(필수)</label><input type="text"  id="email"  name="email"  value = "${principal.user.email}"   class="form-control"   placeholder="이메일"  required="">
										</div>
									</div>
								</div>
								
								<!-- 이름 -->
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											이름<input type="text"  id="name"  name="name"  value = "${principal.user.name}"   class="form-control"   placeholder="이름" readonly="readonly">
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											생년월일<input type="text"  id="birth"  name="birth"  value = "${principal.user.birth}"   class="form-control"   placeholder="생년월일" readonly="readonly">
										</div>
									</div>
								</div>
								
								<!-- 종족  -->
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											종족
											<div class="col-sm-12">
												<select name="species" class="form-control" disabled>
													<option value="1"   ${principal.user.species == '1' ? 'selected = "selected"' : ''} >Human</option>
													<option value="2"  ${principal.user.species == '2' ? 'selected = "selected"' : ''} >Elf</option>				
													<option value="3"  ${principal.user.species == '3' ? 'selected = "selected"' : ''} >Dwarf</option>				
													<option value="4"  ${principal.user.species == '4' ? 'selected = "selected"' : ''} >Electron</option>				
													<option value="5"  ${principal.user.species == '5' ? 'selected = "selected"' : ''} >Fairy</option>				
													<option value="6"  ${principal.user.species == '6' ? 'selected = "selected"' : ''} >Furry</option>				
													<option value="0"  ${principal.user.species == '0' ? 'selected = "selected"' : ''} >Other</option>				
												</select>
											</div>
										</div>
									</div>
								</div>
	
								<!-- 국가  -->
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											국가
											<div class="col-sm-12">
												<select name="country" class="form-control" disabled>
													<option value="1"   ${principal.user.country == '1' ? 'selected = "selected"' : ''} >토르두스</option>
													<option value="2"  ${principal.user.country == '2' ? 'selected = "selected"' : ''} >일레니아</option>		
													<option value="3"  ${principal.user.country == '3' ? 'selected = "selected"' : ''} >카리우스</option>		
													<option value="4"  ${principal.user.country == '4' ? 'selected = "selected"' : ''} >플러터누스</option>		
													<option value="5"  ${principal.user.country == '5' ? 'selected = "selected"' : ''} >플러터누스</option>		
													<option value="0"  ${principal.user.country == '0' ? 'selected = "selected"' : ''} >그 외</option>		
												</select>
											</div>
										</div>
									</div>
								</div>
								
								
								<!-- 직업  -->
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											직업
											<div class="col-sm-12">
												<select name="job" class="form-control" >
													<option value="1"   ${principal.user.job == '1' ? 'selected = "selected"' : ''} >학생</option>
													<option value="2"  ${principal.user.job == '2' ? 'selected = "selected"' : ''} >공무원</option>		
													<option value="3"  ${principal.user.job == '3' ? 'selected = "selected"' : ''} >회사원</option>		
													<option value="4"  ${principal.user.job == '4' ? 'selected = "selected"' : ''} >전문직</option>		
													<option value="5"  ${principal.user.job == '5' ? 'selected = "selected"' : ''} >자영업</option>		
													<option value="0"  ${principal.user.job == '0' ? 'selected = "selected"' : ''} >그 외</option>		
												</select>
											</div>
										</div>
									</div>
								</div>

								<!-- 연락처 -->
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											연락처<label style="color:red";>(필수)</label><input type="text"   id="phoneNumber"  name="phoneNumber"  value = "${principal.user.phoneNumber}"  class="form-control"  placeholder="연락처"  required="">
										</div> 
									</div>
								</div>
								
								<!-- 주소 -->
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											주소<input type="text"  id="address"  name="address"  value = "${principal.user.address}"   class="form-control"   placeholder="주소"  >
										</div>
									</div>
								</div>
								
 							<button>저장</button>
						</form>
	
						</div>
					</div>
				</div>
				<!-- 우측 메인 End-->
				
	    	</div>
	    </div>
	</section>
</div>
<script src="/js_custom/user/userUpdate.js"></script>
<%@ include file="../layout/footer.jsp"%>