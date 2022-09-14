<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_Auth.jsp"%>


				<div class="two-colls-right"><!-- 우측 메인 시작 -->
					<div class="two-colls-right-b">
						<div class="padding">
							<div class="right-Submenu-2depth">
								아이디 / 비밀번호 찾기
							</div>
							
							
	                            <div class="accordeons-toggles">
									
									<!-- 좌측    -->
									
									<div class="shortcodes-left">
										<div calss="typography-block">
											<h2>아이디 찾기</h2>
										</div>
										<div>
											<label class="inputBox-custom-important-label  style-label" >가입할 때 입력한 이메일을 입력해주세요</label>
										</div>
										<div class="tables" >
											<table class="table-a" >
												<tr>
													 <td style="font-size:15px; padding-top:20px;">이메일 주소</td>
													 <td>
														<div class="input">
															<input type="text"  id="idEmail"  name="idEmail"   class="input-custom"   placeholder="이메일" required="">
														</div>
													</td>
												</tr>
												<tr>
													<td colspan="2">
														<div style="text-align: -webkit-center;">
															<button class=" booking-complete-btn white-btn-custom"  onclick="findToId()"  >아이디찾기</button>
														</div>
													</td>
												</tr>
											</table>
										</div>
									</div>
									
									
									<!-- 우측    -->
									<div class="shortcodes-right">
										<div calss="typography-block">
											<h2>비밀번호 찾기</h2>
										</div>
										<div>
											<label class="inputBox-custom-important-label  style-label" >가입할 때 입력한 이메일을 입력해주세요</label>
										</div>
										<div class="tables"  >
											<table class="table-a">
												<tr>
													 <td style="font-size:15px; padding-top:20px;">아이디</td>
													 <td>
														<div class="input">
															<input type="text"  id="passwordUsername"  name="passwordUsername"  class="input-custom"   placeholder="아이디" required="">
														</div>
													</td>
												</tr>
												
												<tr>
													 <td style="font-size:15px; padding-top:20px;">이메일 주소</td>
													 <td>
														<div class="input">
															<input type="text"   id="passwordEmail"  name="passwordEmail" class="input-custom"   placeholder="이메일" required="">
														</div>
													</td>
												</tr>
												<tr>
													<td colspan="2">
														<div style="text-align: -webkit-center;">
															<button class=" booking-complete-btn white-btn-custom"  onclick="findToPassword()" >비밀번호찾기</button>
														</div>
													</td>
												</tr>
											</table>
										</div>
									</div>
									
									<div class="clear"></div>
								</div>
	                            
	      
                            
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


<script src="/js_custom/auth/findToIdPassword.js"></script>
<%@ include file="../layout/footer.jsp"%>
