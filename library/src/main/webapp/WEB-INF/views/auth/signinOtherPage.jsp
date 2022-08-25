<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_Auth.jsp"%>

				<div class="two-colls-right"><!-- 우측 메인 시작 -->
					<div class="two-colls-right-b">
						<div class="padding">
							<div class="right-Submenu-2depth">
							로그인
							</div>
			
							
							<!-- 우측 본문 시작 -->
							<div class="cat-list-item-r" style="margin:auto; width:58%;"><!-- 여기부터 콘텐츠 영역 -->
								<div class="cat-list-item-rb">
									<div class="cat-list-item-p">
										<div class="cat-list-content">
											<div class="cat-list-content-a">
												<div class="cat-list-content-l"  style="margin-right:0px; ">
													<div class="cat-list-content-lb">
														<div class="cat-list-content-lpadding"  style="border:0px;"><!--  내용 시작 -->
															<div class="tables" >
																<form action ="/auth/signin" method="POST" >
																	<table class="table-a light">
																		<tr>
																			<th>아이디</th>
																			<td>
																				<input type="text"  name="username"   value="zozo" class="form-control"  placeholder="아이디" required="">
																			</td>
																			<td rowspan="2">
																				<button class="authorize-btn"  style="height:77px;">로그인</button>
																			</td>
																		</tr>
																		<tr>
																			<th>비밀번호</th>
																			<td>
																				<input type="password"  name="password"   value="1234"  class="form-control" placeholder="비밀번호" required="">
																			</td>
																		</tr>
																		<tr>
																			<td></td>
																			<td>
																				<a href="/" class="authorize-forget-pass">비밀번호찾기</a> 
																			</td>
																			<td>
																				<a href="/auth/signup" class="authorize-forget-pass">회원가입</a>
																			</td>
																		</tr>
																	</table>
																</form>
																<div class="clear"></div>
															</div>	
														</div><!-- 내용 끝 -->
														
													</div>
													<br class="clear" />
												</div>
											</div>
											
											<div class="clear"></div>
										</div>
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