<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_Saseo.jsp"%>

<!-- 팝업 시작 -->
		<div class="overlay2"></div>
			<div class="popup_content"  style="height:210px;">
				<div class="autorize-tabs">
					<a href="#" class="autorize-tab-a current" style="width:140px;">청구기호 부여식</a>
					<a href="#" class="autorize-close"></a>
					<div class="clear"></div>
				</div>
				<section class="autorize-tab-content" style="display:block;">
					<div class="autorize-padding">
						<div>
							<p class="autorize-lbl" style="font-family:'' ">
							[분류번호] [저자도서기호] [권차기호] [복본기호] <br>
							책제목 : 한국사 /저자 : 김땡땡 /권수 : 2 / 같은책 : 4권 있으며 3번째 책인 경우<br>
							921.9  / 김492두  / v.2  /  c.3<br>
							*분류번호 시작은 KDC대분류기호를 따르되 나머지는 임의 부여<br>
							*저자도서기호 시작은 저자성1자를 따르되 나머지는 임의 부여<br>
							*권차기호 시작은 권수를 명확히 따라야 함<br>
							*복본기호 수정 시 순차적으로 입력할 것<br>
							</p>
						</div>
					</div>
				</section>
				
				</div>
<!-- 팝업 끝 -->
			
				
	           <div class="two-colls-right"><!-- 우측 메인 시작 -->
					<div class="two-colls-right-b">
						<div class="padding">
							<div class="right-Submenu-2depth">
								청구기호 최초 등록
							</div>
							
							<!-- 우측 본문 시작 -->
							<form action="/saseo/${book.id}/bookRegistration_kdc"  method = "post" enctype="multipart/form-data" class="contact-form bg-white rounded p-5"  id="comment-form2">
								<!-- hidden Tag -->
								<sec:csrfInput/><!-- CSRF 토큰 적용 -->
								<input type="hidden"  id="book_id"  name = "bookId" value="${book.id}" />
								<input type="hidden"  id="book_totalAmount"  value="${book.totalAmount}" />
								<input type="hidden"  id="book_kdcCallSignFamily" value="${book.kdcCallSignFamily}" />
								<!-- hidden Tag -->
								
								<div class="tables" style="margin-top: 70px;">
									<table class="table-a">
										<thead style="border:1px solid black;">
											<tr>
												<th>책 제목 </th>
												<td>${book.title}</td>
												<th>저자 </th>
												<td>${book.writer}</td>
											</tr>
											<tr>
												<td>ISBN</td>
												<td>${book.isbn}</td>
												<td>KDC 대분류</td>
												<td>${book.kdcTable}</td>
											</tr>
											
											<tr>
												<th colspan="2">번호</th>
												<td colspan="2">청구기호</td>
											</tr>
											<tr>
												<td colspan="4" style="text-align:center;">
												추가 버튼을 눌러 청구기호를 등록해주세요
												<a class="popup_custom" >
													<i class="bi bi-question-circle   "></i>
												</a>
												</td>
											</tr>
										</thead>
										<tbody  id="kdcCallSignList">
											<tr>
												<td colspan="2"  style="text-align:center;">
													<div class="booking-form-i  inputBox-custom"  >
														<div class="input">
															<input type="text" name="kdcCallSign" placeholder="청구기호">
														</div>
													</div>
												</td>
												<td colspan="2" >
													<button onclick="kdcAdd(event)" class=" booking-complete-btn white-btn-custom" >추가</button>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
								<!-- 흰색 버튼 -->
								<div style="text-align: -webkit-center;">
									<button class=" booking-complete-btn white-btn-custom" >청구기호 등록 완료</button>
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

<script src="/js_custom/saseo/bookRegistration_kdc.js"></script>
<%@ include file="../layout/footer.jsp"%>