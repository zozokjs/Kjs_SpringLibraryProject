<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_Saseo.jsp"%>
<style>
	.table-a th {
			font-size: 15px;
			text-align: center;
			width: 200px;
	}
	
	
	
	.selectBox-custom{
		font-size:14px;
		height:34px;
		width:100%;
	}
	.popup_content{
		z-index: 9999;
	    width: 444px;
	    height: 255px;
	    background: #fff;
	    position: fixed;
	    left: 50%;
	    top: -300px;
	    margin-top: -128px;
	    margin-left: -222px;
	}
	
	
	.overlay2{
	    display: none;
    width: 100%;
    height: 100%;
    background: rgba(20,29,30,0.19);
    position: fixed;
    left: 0px;
    top: 0px;
    z-index: 9999;
	
	}
	
</style>

				<div class="overlay2"></div>
				<div class="popup_content"  style="height:150px;">
					<div class="autorize-tabs">
						<a href="#" class="autorize-tab-a current" style="width:140px;">제국력 환산식</a>
						<a href="#" class="autorize-close"></a>
						<div class="clear"></div>
					</div>
					<section class="autorize-tab-content" style="display:block;">
						<div class="autorize-padding">
							<div>
								<p class="autorize-lbl" style="font-family:'' ">
								제국력 = 서력 + 17341년 42일<br>
								예) 서력 기준 오늘 날짜가 2022년 7월 4일인 경우<br>
								제국력 19341년 8월 14일임
								</p>
							</div>
						</div>
					</section>
					
					
					
				</div>


				<div class="two-colls-right"><!-- 우측 메인 시작 -->
					<div class="two-colls-right-b">
						<div class="padding">
							<div class="right-Submenu-2depth">
								새책 등록하기
							</div>
							
							<!-- 우측 본문 시작 -->
							

							
							<form action="/saseo/bookRegistration"  method = "post" enctype="multipart/form-data" class="contact-form bg-white rounded p-5"  id="comment-form">
								<div class="tables" style="margin-top: 70px;">
									<div>
										<label class="inputBox-custom-important-label">*은 필수입력항목입니다</label>
									</div>
									
									<table class="table-a">
										<tr>
											<th><label class="inputBox-custom-require-label">*</label>제목</th>
											<td colspan="3">
												<div class="booking-form-i  inputBox-custom"  >
													<div class="input">
														<input type="text"  	id="title"  name="title" class="form-control"   placeholder="제목" required="">
													</div>
												</div>
											</td>
										</tr>
										
										<tr>
											<th><label class="inputBox-custom-require-label">*</label>저자</th>
											<td colspan="3">
												<div class="booking-form-i  inputBox-custom"  >
													<div class="input">
														<input type="text"  id="writer"  name="writer" class="form-control"   placeholder="저자" required="">
													</div>
												</div>
											</td>
										</tr>
										
										<tr>
											<th>이미지</th>
											<td colspan="3">
												<input type="file" name="file"/>
											</td>
										</tr>
										
										<tr>
											<th>발행처</th>
											<td>
												<div class="booking-form-i  inputBox-custom"  >
													<div class="input">
														<input type="text"  id="publish" name="publish" class="form-control"   placeholder="발행처">
													</div>
												</div>
											</td>
											
											<!-- 작은 팝업 창 띄워서 제국력 기준으로 타 국가의 연력 변환 공식을 공지해야 함  -->
											<th>
												발행일(제국력 기준) 
												<a class="popup_custom" >
													<i class="bi bi-question-circle   "></i>
												</a>
											</th>
											<td>
												<div class="booking-form-i  inputBox-custom"  >
													<div class="input">
														<input type="text"  id="publishDate" name="publishDate"  class="form-control"  placeholder="발행일">
													</div>
												</div>
											</td>
											
										</tr>
										
										<tr>
											<th><label class="inputBox-custom-require-label">*</label>페이지 </th>
											<td>
												<div class="booking-form-i  inputBox-custom"  >
													<div class="input">
														<input type="number"   id="page" name="page" class="form-control"  style="width:96%;"placeholder="페이지"  >
													</div>
												</div>
											</td>
											<th>크기</th>
											<td>
												<div class="booking-form-i  inputBox-custom"  >
													<div class="input">
														<input type="text"   id="size"  name="size" class="form-control" 	 placeholder="크기">
													</div>
												</div>
											</td>
											
										</tr>
										
										<tr>
											<th>가격<br>(원화 기준. 환산 불가시 0으로 기입)</th>
											<td>
												<div class="booking-form-i  inputBox-custom"  >
													<div class="input">
														<input type="text"   id="size"  name="size" class="form-control" 	 placeholder="크기">
													</div>
												</div>
											</td>
											<th>언어(제국어, 니으텡어, 리가(elf), 아아시(fairy),<br> 퀘탙(dwarf), 림어, 그 외 언어는 자유롭게 기입) </th>
											<td>
												<div class="booking-form-i  inputBox-custom"  >
													<div class="input">
														<input type="text"  id="language" name="language" class="form-control"   placeholder="언어">
													</div>
												</div>
											</td>
											
										</tr>
										
										<tr>
											<th>제본형식 </th>
											<td>
												<div class="card-expiration"  style="width:100%;">
													<select name="bindType" class="selectBox-custom" >
														<option value="1">종이</option>
														<option value="2">마력</option>
														<option value="3">전자잉크</option>
														<option value="4">음성</option>
														<option value="5">영상</option>
														<option value="6">기타</option>
													</select>
												</div>
											</td>
											
											<th>납본여부(중앙도서관 제출 여부)</th>
											<td>
												<div class="card-expiration"  style="width:100%;">
													<select name="deliveryState" class="selectBox-custom" >
														<option value="1">했음</option>
														<option value="2">안했음</option>
													</select>
												</div>
											</td>
										</tr>
										
										<tr>
											<th>KDC 대분류</th>
											<td>
												<div class="card-expiration"  style="width:100%;">
													<select name="kdcTable" class="selectBox-custom" >
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
											</td>
											
											<th>대표 청구기호 </th>
											<td>
												<div class="booking-form-i  inputBox-custom"  >
													<div class="input">
														<input type="text"  id="kdcCallSign"  name="kdcCallSign" class="form-control"   readonly="readonly" placeholder="대표 청구기호">
													</div>
												</div>
											</td>
										</tr>
										
										<tr>
											<th>ISBN<br>(예)978-89-605-3241-0 )</th>
											<td>
												<div class="booking-form-i  inputBox-custom"  >
													<div class="input">
														<input type="text"   id="isbn"  name="isbn"  class="form-control"  placeholder="ISBN">
													</div>
												</div>
											</td>
											<th>ISBN SET<br>(예)978-89-605-5173-3 ) </th>
											<td>
												<div class="booking-form-i  inputBox-custom"  >
													<div class="input">
														<input type="text"  id="isbnSet"  name="isbnSet" class="form-control"   placeholder="ISBN SET">
													</div>
												</div>
											</td>
										</tr>
										
										<tr>
											<th>줄거리</th>
											<td colspan="3">
												<div class="booking-form-i">
														<div class="textarea"  style="margin: 10px; padding: 1px;">
															<textarea  id="contents"  name="contents" class="form-control mb-3"  cols="30" rows="5" placeholder="줄거리"></textarea>
														</div>
												</div>
											</td>
										</tr>
										
									</table>
								<div style="text-align: -webkit-center;">
									<button class=" booking-complete-btn white-btn-custom" >등록</button>
								</div>
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

<%@ include file="../layout/footer.jsp"%>
