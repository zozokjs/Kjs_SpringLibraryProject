<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_Saseo.jsp"%>
<style>
	.imgTagContainer img{
	  width: 15%;
	  top: 23%;
	  left: 60%;
}

.booking-form-i{
width:100%;
}
</style>
	        


				<div class="two-colls-right"><!-- 우측 메인 시작 -->
					<div class="two-colls-right-b">
						<div class="padding">
							<div class="right-Submenu-2depth">
								도서 정보 수정
							</div>
							<!-- 우측 본문 시작 -->
							
							
							<!-- 책 이미지-->
							<div class="offer-slider-i catalog-i fly-in ">
								<div class="imgTagContainer">
									<div class="portfolio-full-img" style="text-align: center;">
										<img id="imageUploadPreview"  src="/upload/${book.titleImageUrl}" alt="" >
									</div>
								</div>
							</div>
							
							
							<form id = "bookUpdate"  onsubmit="bookUpdate(${book.id},event)" enctype="multipart/form-data" class="contact-form bg-white rounded p-5" >
								<sec:csrfInput/><!-- CSRF 토큰 적용 -->
								<input  type="hidden" name="titleImageUrl"  value="${book.titleImageUrl}"/>
								
								<div class="tables" style="margin-top: 70px;">
									<table class="table-a">
										<tr>
											<th>이미지</th>
											<td colspan="3">
												<input  type="file" name="file"  onchange="imageChoose(this)"/>	
											</td>
										</tr>
										<tr>
											<th>제목 </th>
											<td>
												<div class="booking-form-i  inputBox-custom"  >
													<div class="input">
														<input type="text"  id="title"  name="title" value = "${book.title}"   class="form-control"   placeholder="제목"    readonly="readonly">
													</div>
												</div>
											</td>
											<th>저자</th>
											<td>
												<div class="booking-form-i  inputBox-custom"  >
													<div class="input">
														<input type="text"  id="writer"  name="writer"  value = "${book.writer}"  class="form-control"   placeholder="저자"   readonly="readonly">
													</div>
												</div>
											</td>
										</tr>
										
										<tr>
											<th>ISBN </th>
											<td>
												<div class="booking-form-i  inputBox-custom"  >
													<div class="input">
															<input type="text"  id="isbn"  name="isbn"  value = "${book.isbn}"  class=""   placeholder="isbn" >
													</div>
												</div>
											</td>
											<th>ISBN SET</th>
											<td>
												<div class="booking-form-i  inputBox-custom"  >
													<div class="input">
														<input type="text"  id="isbnSet"  name="isbnSet"  value = "${book.isbnSet}"  class=""   placeholder="isbnSet"  >
													</div>
												</div>
											</td>
										</tr>
										
										<tr>
											<th>발행처 </th>
											<td>
												<div class="booking-form-i  inputBox-custom"  >
													<div class="input">
														<input type="text"  id="publish"  name="publish"  value = "${book.publish}"  class="form-control"   placeholder="publish"  >
													</div>
												</div>
											</td>
											<th>페이지</th>
											<td>
												<div class="booking-form-i  inputBox-custom"  >
													<div class="input">
														<input type="text"   id="page" name="page"  value = "${book.page}"   class="form-control"  placeholder="페이지"    >
													</div>
												</div>
											</td>
										</tr>
										
										
										<tr>
											<th>크기 </th>
											<td>
												<div class="booking-form-i  inputBox-custom"  >
													<div class="input">
														<input type="text"  id="size"  name="size"  value = "${book.size}"  class="form-control"   placeholder="size"   >
													</div>
												</div>
											</td>
											<th>언어</th>
											<td>
												<div class="booking-form-i  inputBox-custom"  >
													<div class="input">
														<input type="text"  id="language"  name="language"  value = "${book.language}"  class="form-control"   placeholder="language"   >
													</div>
												</div>
											</td>
										</tr>
										
										<tr>
											<th>가격 </th>
											<td>
												<div class="booking-form-i  inputBox-custom"  >
													<div class="input">
														<input type="text"  id="price"  name="price"  value = "${book.price}"  class="form-control"   placeholder="price"   >
													</div>
												</div>
											</td>
											<th>발행일</th>
											<td>
												<div class="booking-form-i  inputBox-custom"  >
													<div class="input">
														<input type="text"  id="publishDate"  name="publishDate"  value = "${book.publishDate}"  class="form-control"   placeholder="publishDate"   >
													</div>
												</div>
											</td>
										</tr>
										
										<tr>
											<th>납본여부 </th>
											<td>
												<div class="card-expiration"  style="width:100%;">
													<select name="deliveryState" class="selectBox-custom"  >
														<option value="1"   ${book.deliveryState == '1' ? 'selected = "selected"' : ''} >했음</option>
														<option value="2"  ${book.deliveryState == '2' ? 'selected = "selected"' : ''} >안했음</option>		
													</select>
												</div>
											</td>
											<th>제본형식</th>
											<td>
												<div class="card-expiration"  style="width:100%;">
													<select name="bindType" class="selectBox-custom"  >
														<option value="1"   ${book.bindType == '1' ? 'selected = "selected"' : ''} >종이</option>
														<option value="2"  ${book.bindType == '2' ? 'selected = "selected"' : ''} >마력</option>
														<option value="3"  ${book.bindType == '3' ? 'selected = "selected"' : ''} >전자잉크</option>
														<option value="4"  ${book.bindType == '4' ? 'selected = "selected"' : ''} >음성</option>
														<option value="5"  ${book.bindType == '5' ? 'selected = "selected"' : ''} >영상</option>
														<option value="6"  ${book.bindType == '6' ? 'selected = "selected"' : ''} >기타</option>	
													</select>
												</div>
											</td>
										</tr>
										
										<tr>
											<th>권 수 </th>
											<td colspan="3">
												<div class="booking-form-i  inputBox-custom"  >
													<div class="input">
														<input type="text"   id="totalAmount" name="totalAmount"  value = "${book.totalAmount}" class="form-control" placeholder="권수"    readonly="readonly">
													</div>
												</div>
											</td>
										</tr>
										
										<tr>
											<th>KDC 대분류 </th>
											<td>
												<div class="card-expiration"  style="width:100%;">
													<select name="kdcTable" class="selectBox-custom" >
														<option value="000"   ${book.kdcTable == '000' ? 'selected = "selected"' : ''} >총류</option>
														<option value="100"    ${book.kdcTable == '100' ? 'selected = "selected"' : ''} >철학</option>
														<option value="200"    ${book.kdcTable == '200' ? 'selected = "selected"' : ''} >종교</option>
														<option value="300"    ${book.kdcTable == '300' ? 'selected = "selected"' : ''} >사회과학</option>
														<option value="400"    ${book.kdcTable == '400' ? 'selected = "selected"' : ''} >자연과학</option>
														<option value="500"    ${book.kdcTable == '500' ? 'selected = "selected"' : ''} >기술과학</option>
														<option value="600"    ${book.kdcTable == '600' ? 'selected = "selected"' : ''} >예술</option>
														<option value="700"    ${book.kdcTable == '700' ? 'selected = "selected"' : ''} >언어</option>
														<option value="800"    ${book.kdcTable == '800' ? 'selected = "selected"' : ''} >문학</option>
														<option value="900"    ${book.kdcTable == '900' ? 'selected = "selected"' : ''} >역사</option>
													</select>
												</div>
											</td>
											<th>대표청구기호</th>
											<td>
												<div class="booking-form-i  inputBox-custom"  >
													<div class="input">
														<input type="text"  id="kdcCallSign"  name="kdcCallSign"  value = "${book.kdcCallSignFamily}" class="form-control"  placeholder="청구기호"   >
													</div>
												</div>
											</td>
										</tr>
										
										<tr>
											<th>줄거리 </th>
											<td colspan="3">
												<div class="booking-form-i  inputBox-custom"  >
													<div class="input">
														<div>
															<textarea  id="contents"  name="contents" cols="30" rows="5"  style="resize: none; width:99.3%; margin-bottom:-5px;" placeholder="줄거리"     >${book.contents}</textarea>
														</div>
													</div>
												</div>
											</td>
										</tr>
									</table>
								</div><!-- 도서 정보 Table 끝 -->
								
								
								<div style="text-align: -webkit-center;">
									<button class=" booking-complete-btn white-btn-custom" >수정 완료</button>
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
	        

<script src="/js_custom/saseo/bookUpdate.js"></script>
<%@ include file="../layout/footer.jsp"%>
