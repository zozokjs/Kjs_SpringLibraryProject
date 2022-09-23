<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_Auth.jsp"%>
<link rel="stylesheet" href="/css_custom/auth/signinOtherPage.css" />	

<!-- LoginFailureHandler.java 참조. 여기서 msg를 던짐. -->
<!-- errorMsg가 비어 있지 않을 때 -> 값이 있을 때 -->
<c:choose>
	<c:when test ="${not empty errorMsg}">
		<script>
			const a = `${errorMsg}`;
			alert( a );
		</script>
	</c:when>
</c:choose>

				<div class="two-colls-right"><!-- 우측 메인 시작 -->
					<div class="two-colls-right-b">
						<div class="padding">
							<div class="right-Submenu-2depth">
							로그인
							</div>
								<div class="tables" >
									<form action ="/auth/signin" method="POST" >
										<sec:csrfInput/><!-- CSRF 토큰 적용 -->
										<table class="table-a light table-a-marginBottom">
											<tbody>
												<!--  0  -->
												<!-- 이 항목이 반드시 있어야 모바일 화면에서 테이블 형태가 유지됨-->
												<tr>
													<th colspan="3"  class="table-border-none" >
													</th>
												</tr>
												
												<!--  1  -->
												<tr>
													<th>아이디</th>
													<td>
														<input type="text"  name="username"   class="form-control"  placeholder="아이디" required="">
													</td>
													<td rowspan="2">
														<button class="authorize-btn  button-login-height"  >로그인</button>
													</td>
												</tr>
												
												<!--  2 -->
												<tr>
													<th>비밀번호</th>
													<td>
														<input type="password"  name="password"   class="form-control" placeholder="비밀번호" required="">
													</td>
												</tr>
												
												<!--  3 -->
												<tr>
																	<td></td>
													<td>
														<a href="/" class="authorize-forget-pass">비밀번호찾기</a> 
													</td>
								
													<td>
														<a href="/auth/signup" class="authorize-forget-pass">회원가입</a>
													</td>
												</tr>
												<tr>
													<td colspan="3" class="table-none">
														<a href="/oauth2/authorization/kakao"> 
															<img alt="" class="oauth_button" src="/img_custom/oauth/kakao_login_small.png" />
														</a>
														<!-- <a href="/oauth2/authorization/NAV"> 
															<img alt="" class="oauth_button" src="/img_custom/oauth/naver_login_small.png" />
														</a> -->
													</td>
												</tr>
											</tbody>
										</table>
									</form>
																		
									<div class="clear"></div>
								</div>	
							
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