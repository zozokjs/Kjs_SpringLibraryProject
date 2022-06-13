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
								<!-- 십진 분류 1차, 2차 -->
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											ISBN<input type="text"   id="isbn"  name="isbn"  value = "${book.isbn}"  class="form-control"  placeholder="ISBN" readonly="readonly">
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											ISBN SET<input type="text"  id="isbnSet"  name="isbnSet"  value = "${book.isbnSet}"   class="form-control"   placeholder="ISBN SET" readonly="readonly">
										</div>
									</div>
								</div>
								
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											제목<input type="text"  	id="title"  name="title" value = "${book.title}"   class="form-control"   placeholder="제목" readonly="readonly">
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											저자<input type="text"  id="writer"  name="writer"  value = "${book.writer}"  class="form-control"   placeholder="저자" readonly="readonly">
										</div>
									</div>
								</div>
								
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											발행처<input type="text"  id="publish" name="publish" value = "${book.publish}"  class="form-control"   placeholder="발행처" readonly="readonly" >
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											페이지<input type="text"   id="page" name="page"  value = "${book.page}"   class="form-control"  placeholder="페이지" readonly="readonly">
										</div>
									</div>
								</div>
								
								
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											크기<input type="text"   id="size"  name="size"  value = "${book.size}" class="form-control" 	 placeholder="크기"  readonly="readonly">
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											언어<input type="text"  id="language" name="language"  value = "${book.language}" class="form-control"   placeholder="언어"  readonly="readonly">
										</div>
									</div>
								</div>
								
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											가격<input type="text"  	id="price"  name="price"  value = "${book.price}" class="form-control"   placeholder="가격" readonly="readonly">
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											발행일<input type="text"  id="publishDate" name="publishDate" value = "${book.publishDate}" class="form-control"  placeholder="발행일"  readonly="readonly">
										</div>
									</div>
								</div>
								

								
								<!-- 납본여부  -->
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											납본여부
											<div class="col-sm-10">
												<select name="deliveryState" class="form-control" disabled>
													<option value="1"   ${book.deliveryState == '1' ? 'selected = "selected"' : ''} >했음</option>
													<option value="2"  ${book.deliveryState == '2' ? 'selected = "selected"' : ''} >안했음</option>													
												</select>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											제본형식
											<div class="col-sm-10">
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
									
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												권 수<input type="text"   	id="volume" name="volume"  value = "${book.volume}" class="form-control" placeholder="권수" readonly="readonly">
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												KDC 대분류
												<div class="col-sm-10">
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
										</div>
										<div class="col-md-6">
											<div class="form-group">
												청구기호<input type="text"  id="kdcCallSign"  name="kdcCallSign"  value = "${book.kdcCallSign}" class="form-control"  placeholder="청구기호" readonly="readonly">
											</div>
										</div>
									</div>
									
									줄거리
									<textarea  id="contents"  name="contents"  value = "${book.contents}" class="form-control mb-3"  cols="30" rows="5" placeholder="줄거리" readonly="readonly"></textarea>
									
									
								</div>
 							<button>수정하기</button>
						</form>
							
						</div>
					</div>
				</div>
				<!-- 우측 메인 End-->

	    	</div>
	    </div>
	</section>
</div>
<script src="/js/saseo_bookRegistration.js"></script>
<%@ include file="../layout/footer.jsp"%>
