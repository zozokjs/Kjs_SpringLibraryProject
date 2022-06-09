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
									새책 등록하기
									</h2>
								</div>
							</div>		
							<hr>	
							
							<form action="/saseo/bookRegistration"  method = "post" enctype="multipart/form-data" class="contact-form bg-white rounded p-5"  id="comment-form">
							<h4 class="mb-4">도서 정보를 입력하세요</h4>
								
								<!-- 십진 분류 1차, 2차 -->
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											ISBN<input class="form-control" type="text" name="isbn" id="isbn" placeholder="ISBN">
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											ISBN SET<input class="form-control" type="text" name="isbnSet" id="isbnSet" placeholder="ISBN SET">
										</div>
									</div>
								</div>
								
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											제목<input class="form-control" type="text" name="title"	id="title" placeholder="제목">
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											저자<input class="form-control" type="text" name="writer"id="writer" placeholder="저자">
										</div>
									</div>
								</div>
								
								이미지 <input type="file" name="file"/>
								
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											발행처<input class="form-control" type="text" name="publish"id="publish" placeholder="발행처">
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											페이지<input class="form-control" type="text" name="page"id="page" placeholder="페이지">
										</div>
									</div>
								</div>
								
								
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											크기<input class="form-control" type="text" name="size"	id="size" placeholder="크기">
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											언어<input class="form-control" type="text" name="language"id="language" placeholder="언어">
										</div>
									</div>
								</div>
								
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											가격<input class="form-control" type="text" name="price"	id="price" placeholder="가격">
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											발행일<input class="form-control" type="text" name="publishDate"id="publishDate" placeholder="발행일">
										</div>
									</div>
								</div>
								

								
								<!-- 납본여부  -->
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											납본여부
											<div class="col-sm-10">
												<select name="deliveryState" class="form-control">
													<option value="1">했음</option>
													<option value="2">안했음</option>
												</select>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											제본형식
											<div class="col-sm-10">
												<select name="bindType" class="form-control">
													<option value="1">종이</option>
													<option value="2">마력</option>
													<option value="3">전자잉크</option>
													<option value="4">음성</option>
													<option value="5">영상</option>
													<option value="6">기타</option>
												</select>
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												권 수<input class="form-control" type="text" name="volume"	id="volume" placeholder="권수">
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												KDC 대분류
												<div class="col-sm-10">
													<select name="kdcTable" class="form-control">
														<option value="000">총류</option>
														<option value="100">철학</option>
														<option value="200">종교</option>
														<option value="300">사회과학</option>
														<option value="400">자연과학</option>
														<option value="500">기술과학</option>
														<option value="600">예술</option>
														<option value="700">언어</option>
														<option value="800">문학</option>
														<option value="900">역사</option>
													</select>
												</div>
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group">
												청구기호<input class="form-control" type="text" name="kdcCallSign" id="kdcCallSign" placeholder="청구기호">
											</div>
										</div>
									</div>
									
									줄거리
									<textarea class="form-control mb-3" name="contents" id="contents" cols="30" rows="5" placeholder="줄거리"></textarea>
									
									
								</div>
 							<button>등록</button>
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
