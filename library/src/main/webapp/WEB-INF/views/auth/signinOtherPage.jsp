<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_Auth.jsp"%>

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
							
							<!-- 우측 본문 시작 -->
							<div class="cat-list-item-r div-first"  ><!-- 여기부터 콘텐츠 영역 -->
								<div class="cat-list-item-rb">
									<div class="cat-list-content-l  div-second"  >
										<div class="cat-list-content-lpadding  div-third"  ><!--  내용 시작 -->
											<div class="tables" >
												<form action ="/auth/signin" method="POST" >
													<sec:csrfInput/><!-- CSRF 토큰 적용 -->
													<table class="table-a light">
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
														</tbody>
													</table>
												</form>
												<div style="text-align:center;">
													<!-- 카카오 로그인 -->
													<a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=01d44202f2f9d241ef89ae26c19707c1&redirect_uri=http://localhost:8080/auth/oauth/kakao/callback">
														<img alt=""  class="oauth_button"   src="/img_custom/oauth/kakao_login_button_wide.png" />
													</a>
													<!-- 네이버 로그인 -->
													<a href="/oauth2/authorization/naver"><!-- yml 파일의 authorization-uri에 설정된 주소가 요청된다. -->
														<img alt=""  class="oauth_button"  src="/img_custom/oauth/naver_login_button.png" />
													</a>
												</div>
																					
												<div class="clear"></div>
											</div>	
										</div><!-- 내용 끝 -->
									<br class="clear" />
								</div>
							</div>
							<br class="clear" />
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