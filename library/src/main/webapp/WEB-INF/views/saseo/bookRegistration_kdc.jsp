<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_Saseo.jsp"%>
				
			
				
	           <div class="two-colls-right"><!-- 우측 메인 시작 -->
					<div class="two-colls-right-b">
						<div class="padding">
							<div class="right-Submenu-2depth">
								청구기호 최초 등록
							</div>
							
							<!-- 우측 본문 시작 -->
							<form action="/saseo/${book.id}/bookRegistration_kdc"  method = "post" enctype="multipart/form-data" class="contact-form bg-white rounded p-5"  id="comment-form2">
								<!-- hidden Tag -->
								<input type="hidden"  id="book_id"  name = "bookId" value="${book.id}" />
								<input type="hidden"  id="book_totalAmount"  value="${book.totalAmount}" />
								<input type="hidden"  id="book_kdcCallSignFamily" value="${book.kdcCallSignFamily}" />
								<!-- hidden Tag -->
								
								<div class="tables" style="margin-top: 70px;">
									<table class="table-a">
										<thead style="border:1px solid black;">
											<tr>
												<th>책 제목 </th>
												<th>${book.title}</th>
											</tr>
											<tr>
												<td>ISBN</td>
												<td>${book.isbn}</td>
											</tr>
											<tr>
												<th>번호</th>
												<td>청구기호</td>
											</tr>
											<tr>
												<td colspan="2" style="text-align:center;">추가 버튼을 눌러 청구기호를 등록해주세요</td>
											</tr>
										</thead>
										<tbody  id="kdcCallSignList">
											<tr>
												<td style="text-align:center;">
													<div class="booking-form-i  inputBox-custom"  >
														<div class="input">
															<input type="text" name="kdcCallSign" placeholder="청구기호">
														</div>
													</div>
												</td>
												<td>
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