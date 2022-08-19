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
								도서 상세 정보
							</div>
							
							<!-- 우측 본문 시작 -->
							
							<!-- hidden Tag -->
							<input type="hidden"  id="book_totalAmount"  value="${book.totalAmount}" />
							<!-- hidden Tag -->
							
							<!-- 책 이미지-->
							<div class="offer-slider-i catalog-i fly-in ">
								<div class="imgTagContainer">
									<div class="portfolio-full-img" style="text-align: center;">
										<img src="/upload/${book.titleImageUrl}" alt="" >
									</div>
								</div>
							</div>
							
							<div class="tables" style="margin-top: 70px;">
								<table class="table-a">
								
									<tr>
										<th>제목 </th>
										<td>${book.title}</td>
										<th>저자</th>
										<td>${book.writer}</td>
									</tr>
									
									<tr>
										<th>ISBN </th>
										<c:choose>
											<c:when test="${empty book.isbn}">
												<td>아직 등록되지 않았습니다</td>
											</c:when>
											<c:otherwise>
												<td>${book.isbn}</td>
											</c:otherwise>
										</c:choose>
										<th>ISBN SET</th>
										<c:choose>
											<c:when test="${empty book.isbnSet}">
												<td>아직 등록되지 않았습니다</td>
											</c:when>
											<c:otherwise>
												<td>${book.isbnSet}</td>
											</c:otherwise>
										</c:choose>
									</tr>
									
									<tr>
										<th>발행처 </th>
										<c:choose>
											<c:when test="${empty book.publish}">
												<td>아직 등록되지 않았습니다</td>
											</c:when>
											<c:otherwise>
												<td>${book.publish}</td>
											</c:otherwise>
										</c:choose>
										<th>페이지</th>
										<td>${book.page}</td>
									</tr>
									
									
									<tr>
										<th>크기 </th>
										<c:choose>
											<c:when test="${empty book.size}">
												<td>아직 등록되지 않았습니다</td>
											</c:when>
											<c:otherwise>
												<td>${book.size}</td>
											</c:otherwise>
										</c:choose>
										<th>언어</th>
										<c:choose>
											<c:when test="${empty book.language}">
												<td>아직 등록되지 않았습니다</td>
											</c:when>
											<c:otherwise>
												<td>${book.language}</td>
											</c:otherwise>
										</c:choose>
									</tr>
									
									<tr>
										<th>가격 </th>
										<c:choose>
											<c:when test="${empty book.price}">
												<td>아직 등록되지 않았습니다</td>
											</c:when>
											<c:otherwise>
												<td>${book.price}</td>
											</c:otherwise>
										</c:choose>
										<th>발행일</th>
										<c:choose>
											<c:when test="${empty book.publishDate}">
												<td>아직 등록되지 않았습니다</td>
											</c:when>
											<c:otherwise>
												<td>${book.publishDate}</td>
											</c:otherwise>
										</c:choose>
									</tr>
									
									<tr>
										<th>납본여부 </th>
										<td>
											<div class="card-expiration"  style="width:100%;">
												<select name="deliveryState" class="selectBox-custom"  disabled>
													<option value="1"   ${book.deliveryState == '1' ? 'selected = "selected"' : ''} >했음</option>
													<option value="2"  ${book.deliveryState == '2' ? 'selected = "selected"' : ''} >안했음</option>		
												</select>
											</div>
										</td>
										<th>제본형식</th>
										<td>
											<div class="card-expiration"  style="width:100%;">
												<select name="bindType" class="selectBox-custom"  disabled>
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
										<td colspan="3">${book.totalAmount}</td>
									</tr>
									
									<tr>
										<th>KDC 대분류 </th>
										<td>
											<div class="card-expiration"  style="width:100%;">
												<select name="kdcTable" class="selectBox-custom"  disabled>
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
										<td>${book.kdcCallSignFamily}</td>
									</tr>
									
									<tr>
										<th>줄거리 </th>
										<td colspan="3">
											<div class="booking-form-i  inputBox-custom"  >
												<div class="input">
													<c:choose>
														<c:when test="${empty book.contents}">
															아직 등록되지 않았습니다.
														</c:when>
														<c:otherwise>
															<div>
																<textarea  id="contents"  name="contents" cols="30" rows="5"  style="resize: none; width:99.3%; margin-bottom:-5px;" placeholder="줄거리"  onfocus= "this.blur();"  readonly="readonly">${book.contents}</textarea>
															</div>
														</c:otherwise>
													</c:choose>
												</div>
											</div>
										</td>
									</tr>
								</table>
								<div style="text-align: -webkit-center;">
									<button class=" booking-complete-btn white-btn-custom"  onclick="location.href='/saseo/${book.id}/bookUpdate'" >수정하기</button>
								</div>
							</div><!-- 도서 정보 Table 끝 -->
							
							
							<!-- 청구기호 정보 Table 시작 -->
							<div class="tables" style="margin-top: 70px;">
								<table class="table-a">
									<thead style="border: 1px solid black;">
										<tr class="table-active">
											<th class="tg-0pky">책 제목</th>
											<th class="tg-0pky">${book.title}</th>
										</tr>
										<tr class="table-active">
											<td class="tg-0pky">ISBN</td>
											<td class="tg-0pky">${book.isbn}</td>
										</tr>
										<tr>
											<td colspan="2" style="text-align: center;">총 ${book.totalAmount}권</td>
										</tr>
										<tr>
											<td class="tg-0pky">번호</td>
											<td class="tg-0pky">청구기호</td>
										</tr>
									</thead>
									<tbody id="kdcCallSignList">
									<!-- 청구 기호 없을 때 뭐라해야 함 -->
										<c:choose>
											<c:when test="${empty sameBook}">
												<!-- 청구 기호 없을 때 -->
												<tr>
													<td colspan="2"  style="text-align:center;">등록된 청구기호가 없습니다</td>
												</tr>
											</c:when>
											<c:otherwise>
												<!-- 청구 기호 있을 때 -->
												<c:forEach var="sameBooks"  items="${sameBook}">
												<tr>
													<td colspan="2"  style="text-align:center;">${sameBooks.kdcCallSign}</td>
												</tr>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
							</div>
							<!-- 청구기호 정보 Table 끝 -->
							
							<div>
								<div style="text-align: -webkit-center;">
									<c:choose>
										<c:when test="${empty sameBook}">
											<!-- 청구 기호 없을 때    등록 화면으로 이동-->
											<button class=" booking-complete-btn white-btn-custom"  onclick="location.href='/saseo/${book.id}/bookRegistration_kdc' "  >청구기호 등록하기</button>
										</c:when>
										<c:otherwise>
											<!-- 청구 기호 있을 때    수정 화면으로 이동-->
											<button class=" booking-complete-btn white-btn-custom"  onclick="location.href='/saseo/${book.id}/bookUpdate_kdc' "  >청구기호 수정하기</button>
										</c:otherwise>
									</c:choose>
								</div>
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
<%@ include file="../layout/footer.jsp"%>
