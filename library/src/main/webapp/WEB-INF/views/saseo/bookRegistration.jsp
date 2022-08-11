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
								
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											제목<label style="color:red";>(필수)</label><input type="text"  	id="title"  name="title" class="form-control"   placeholder="제목" required="">
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											저자<label style="color:red";>(필수)</label><input type="text"  id="writer"  name="writer" class="form-control"   placeholder="저자" required="">
										</div>
									</div>
								</div>
								
								<!-- 십진 분류 1차, 2차 -->
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											ISBN( 예)978-89-605-3241-0 ) 
											<input type="text"   id="isbn"  name="isbn"  class="form-control"  placeholder="ISBN">
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											ISBN SET( 예)978-89-605-5173-3 ) 
											<input type="text"  id="isbnSet"  name="isbnSet" class="form-control"   placeholder="ISBN SET">
										</div>
									</div>
								</div>
								
								
								이미지 <input type="file" name="file"/>
								
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											발행처<input type="text"  id="publish" name="publish" class="form-control"   placeholder="발행처">
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											페이지<label style="color:red";>(필수)</label><input type="number"   id="page" name="page" class="form-control"  placeholder="페이지"  > 
										</div>
									</div>
								</div>
								
								
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											크기<input type="text"   id="size"  name="size" class="form-control" 	 placeholder="크기">
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											언어(제국어, 니으텡어, 리가(elf), 아아시(fairy), 퀘탙(dwarf), 심어, 그 외 언어는 자유롭게 기입)
											<input type="text"  id="language" name="language" class="form-control"   placeholder="언어">
										</div>
									</div>
								</div>
								
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											가격(원화 기준. 환산 불가시 0으로 기입)
											<input type="text"  	id="price"  name="price" class="form-control"   placeholder="가격">
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											<!-- 작은 팝업 창 띄워서 제국력 기준으로 타 국가의 연력 변환 공식을 공지해야 함  -->
											발행일(제국력 기준)
											<input type="text"  id="publishDate" name="publishDate"  class="form-control"  placeholder="발행일">
										</div>
									</div>
								</div>
								

								
								<!-- 납본여부  -->
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											납본여부(중앙도서관 제출 여부)
												<select name="deliveryState" class="form-control">
													<option value="1">했음</option>
													<option value="2">안했음</option>
												</select>
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											제본형식
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
							<!-- 
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												권 수<input type="text"  id="totalAmount" name="totalAmount"  class="form-control" placeholder="권수">
											</div>
										</div>
									</div>
						 	-->	
									<div class="row">
										<div class="col-md-6">
											<div class="form-group">
												KDC 대분류
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
										<div class="col-md-6">
											<div class="form-group">
												대표 청구기호<input type="text"  id="kdcCallSign"  name="kdcCallSign" class="form-control"   readonly="readonly" placeholder="대표 청구기호">
											</div>
										</div>
									</div>
									
									<div class="row">
										<div class="col-md-12">
											줄거리
											<textarea  id="contents"  name="contents" class="form-control mb-3"  cols="30" rows="5" placeholder="줄거리"></textarea>
										</div>
									</div>
								
									
									
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
<%@ include file="../layout/footer.jsp"%>
