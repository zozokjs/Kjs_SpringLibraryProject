<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_Saseo.jsp"%>

	        
	            <!-- 우측 메인 -->
	            <div class="col-lg-8">
	               <div class="row">
						<div class="col-lg-12 mb-5">
						
							<div class="col-lg-7">
								<div class="">
									<h2 class="mt-3 content-title ">
									책 상세정보
									</h2>
								</div>
							</div>		
							<hr>	
							
							<form id="comment-form" action="/saseo/${book.id}/bookUpdate"  method = "get" enctype="multipart/form-data" class="contact-form bg-white rounded p-5"  >
								<h4 class="mb-4">상세 정보</h4>

								<!-- hidden Tag -->
								<input type="hidden"  id="book_totalAmount"  value="${book.totalAmount}" />
								<!-- hidden Tag -->
								
								
								<!-- 이미지 표시 -->
								<div class="row justify-content-center">
									<div class="col-lg-4 col-md-6 col-sm-6">
										<div class="team-item-wrap mb-5 mb-lg-0">
											<div class="position-relative">
													<!--  책 이미지 -->
													<img src="/upload/${book.titleImageUrl}" alt="" class="img-fluid w-100">
											</div>
										</div>
									</div>
								</div>
								
								<br>
								<br>
								
								<!-- 제목 / 저자 -->			
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											제목
											<input type="text"  	id="title"  name="title" value = "${book.title}"   class="form-control"   placeholder="제목"  onfocus= "this.blur();" readonly="readonly">
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											저자
											<input type="text"  id="writer"  name="writer"  value = "${book.writer}"  class="form-control"   placeholder="저자" onfocus= "this.blur();" readonly="readonly">
										</div>
									</div>
								</div>
								
								<!-- ISBN / ISBN SET -->
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
										ISBN
										<c:choose>
											<c:when test="${empty book.isbn}">
												<input type="text" id=""  name="" value = "아직 등록되지 않았습니다."   class="form-control"   placeholder=""  onfocus= "this.blur();" readonly="readonly">
											</c:when>
											<c:otherwise>
												<input type="text"  id="isbn"  name="isbn"  value = "${book.isbn}"  class="form-control"   placeholder="isbn" onfocus= "this.blur();" readonly="readonly">
											</c:otherwise>
										</c:choose>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											ISBN SET
											<c:choose>
												<c:when test="${empty book.isbnSet}">
													<input type="text" id=""  name="" value = "아직 등록되지 않았습니다."   class="form-control"   placeholder=""  onfocus= "this.blur();" readonly="readonly">
												</c:when>
												<c:otherwise>
													<input type="text"  id="isbnSet"  name="isbnSet"  value = "${book.isbnSet}"  class="form-control"   placeholder="isbnSet" onfocus= "this.blur();" readonly="readonly">
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>

								<!-- 발행처 / 페이지 -->
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											발행처
											<c:choose>
												<c:when test="${empty book.publish}">
													<input type="text" id=""  name="" value = "아직 등록되지 않았습니다."   class="form-control"   placeholder=""  onfocus= "this.blur();" readonly="readonly">
												</c:when>
												<c:otherwise>
													<input type="text"  id="publish"  name="publish"  value = "${book.publish}"  class="form-control"   placeholder="publish" onfocus= "this.blur();" readonly="readonly">
												</c:otherwise>
											</c:choose>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											페이지<input type="text"   id="page" name="page"  value = "${book.page}"   class="form-control"  placeholder="페이지"  onfocus= "this.blur();"  readonly="readonly">
										</div>
									</div>
								</div>
								
								<!-- 크기 / 언어 -->
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											크기
											<c:choose>
												<c:when test="${empty book.size}">
													<input type="text" id=""  name="" value = "아직 등록되지 않았습니다."   class="form-control"   placeholder=""  onfocus= "this.blur();" readonly="readonly">
												</c:when>
												<c:otherwise>
													<input type="text"  id="size"  name="size"  value = "${book.size}"  class="form-control"   placeholder="size" onfocus= "this.blur();" readonly="readonly">
												</c:otherwise>
											</c:choose>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											언어
											<c:choose>
												<c:when test="${empty book.language}">
													<input type="text" id=""  name="" value = "아직 등록되지 않았습니다."   class="form-control"   placeholder=""  onfocus= "this.blur();" readonly="readonly">
												</c:when>
												<c:otherwise>
													<input type="text"  id="language"  name="language"  value = "${book.language}"  class="form-control"   placeholder="language" onfocus= "this.blur();" readonly="readonly">
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>
								
								<!-- 가격 / 발행일 -->
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											가격
											<c:choose>
												<c:when test="${empty book.price}">
													<input type="text" id=""  name="" value = "아직 등록되지 않았습니다."   class="form-control"   placeholder=""  onfocus= "this.blur();" readonly="readonly">
												</c:when>
												<c:otherwise>
													<input type="text"  id="price"  name="price"  value = "${book.price}"  class="form-control"   placeholder="price" onfocus= "this.blur();" readonly="readonly">
												</c:otherwise>
											</c:choose>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											발행일
											<c:choose>
												<c:when test="${empty book.publishDate}">
													<input type="text" id=""  name="" value = "아직 등록되지 않았습니다."   class="form-control"   placeholder=""  onfocus= "this.blur();" readonly="readonly">
												</c:when>
												<c:otherwise>
													<input type="text"  id="publishDate"  name="publishDate"  value = "${book.publishDate}"  class="form-control"   placeholder="publishDate" onfocus= "this.blur();" readonly="readonly">
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>
								

								
								<!-- 납본여부   / 제본형식-->
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											납본여부
												<select name="deliveryState" class="form-control" disabled>
													<option value="1"   ${book.deliveryState == '1' ? 'selected = "selected"' : ''} >했음</option>
													<option value="2"  ${book.deliveryState == '2' ? 'selected = "selected"' : ''} >안했음</option>													
												</select>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											제본형식
												<select name="bindType" class="form-control" disabled>
													<option value="1"   ${book.bindType == '1' ? 'selected = "selected"' : ''} >종이</option>
													<option value="2"  ${book.bindType == '2' ? 'selected = "selected"' : ''} >마력</option>
													<option value="3"  ${book.bindType == '3' ? 'selected = "selected"' : ''} >전자잉크</option>
													<option value="4"  ${book.bindType == '4' ? 'selected = "selected"' : ''} >음성</option>
													<option value="5"  ${book.bindType == '5' ? 'selected = "selected"' : ''} >영상</option>
													<option value="6"  ${book.bindType == '6' ? 'selected = "selected"' : ''} >기타</option>
												</select>
										</div>
									</div>
								</div>
								
								<!-- 권 수  -->
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											권 수
											<input type="text"   id="totalAmount" name="totalAmount"  value = "${book.totalAmount}" class="form-control" placeholder="권수"  onfocus= "this.blur();" readonly="readonly">
										</div>
									</div>
								</div>
									
									<!-- KDC 대분류   / 대표청구기호-->
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												KDC 대분류
												<select name="kdcTable" class="form-control" disabled>
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
										</div>
										<div class="col-md-6">
											<div class="form-group">
												대표청구기호<input type="text"  id="kdcCallSign"  name="kdcCallSign"  value = "${book.kdcCallSignFamily}" class="form-control"  placeholder="청구기호"  onfocus= "this.blur();"  readonly="readonly">
											</div>
										</div>
									</div>
									
									<!-- 줄거리 -->
									<div class="row">
										<div class="col-md-12">
											<div class="form-group">
												줄거리
												<c:choose>
													<c:when test="${empty book.contents}">
														<input type="text" id=""  name="" value = "아직 등록되지 않았습니다."   class="form-control"   placeholder=""  onfocus= "this.blur();" readonly="readonly">
													</c:when>
													<c:otherwise>
														<div>
															<textarea  id="contents"  name="contents" class="form-control mb-3"  cols="30" rows="5"  style="resize: none;" placeholder="줄거리"  onfocus= "this.blur();"  readonly="readonly">${book.contents}</textarea>
														</div>
													</c:otherwise>
												</c:choose>
											</div>
										</div>
									</div>
								<!-- 청구 기호 정보 출력해야 함. -->
								
 							<button>수정하기</button>
						
						</form>
							<div>
								<!-- 표 시작 -->
								<div class="card">
									<div class="card-block table-border-style">
										<div class="table-responsive">
											<table class="table" >
												<thead style="border:1px solid black;">
													<tr class="table-active"  >
														<th class="tg-0pky">책 제목</th>
														<th class="tg-0pky">${book.title}</th>
													</tr>
													<tr class="table-active" >
														<td class="tg-0pky">ISBN</td>
														<td class="tg-0pky">${book.isbn}</td>
													</tr>
													<tr>
														<td colspan="2" style="text-align:center;">총 ${book.totalAmount}권</td>
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
																<td class="tg-0pky"  colspan="2"  style="text-align:center;">
																<input type="text" name="kdcCallSign" value="등록된 청구기호가 없습니다."  onfocus= "this.blur();" readonly="readonly">
																</td>
															</tr>
														</c:when>
														
														<c:otherwise>
															<!-- 청구 기호 있을 때 -->
															<c:forEach var="sameBooks"  items="${sameBook}">
															<tr>
																<td class="tg-0pky"  colspan="2"  style="text-align:center;">
																<input type="text" name="kdcCallSign" value="${sameBooks.kdcCallSign}"  onfocus= "this.blur();" readonly="readonly">
																</td>
															</tr>
															</c:forEach>
														</c:otherwise>
													</c:choose>
														
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
							<!-- 표 끝-->
							
							<c:choose>
								<c:when test="${empty sameBook}">
									<!-- 청구 기호 없을 때    등록 화면으로 이동-->
									<button type="button" onclick="location.href='/saseo/${book.id}/bookRegistration_kdc' "  >청구기호 등록하기</button>
								</c:when>
								<c:otherwise>
									<!-- 청구 기호 있을 때    수정 화면으로 이동-->
									<button type="button" onclick="location.href='/saseo/${book.id}/bookUpdate_kdc' "  >청구기호 수정하기</button>
								</c:otherwise>
							</c:choose>
							
						
						</div>
					</div>
				</div>
				<!-- 우측 메인 End-->

	    	</div>
	    </div>
	</section>
</div>
<%@ include file="../layout/footer.jsp"%>
