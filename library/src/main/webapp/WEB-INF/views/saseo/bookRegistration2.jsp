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
							<h4 class="mb-4">도서 정보를 입력하세요-청구기호 등록</h4>
								
								<!-- 십진 분류 1차, 2차 -->
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											ISBN<input type="text"   id="isbn"  name="isbn"  class="form-control"  placeholder="ISBN">
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											ISBN SET<input type="text"  id="isbnSet"  name="isbnSet" class="form-control"   placeholder="ISBN SET">
										</div>
									</div>
								</div>
								
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											제목<input type="text"  	id="title"  name="title" class="form-control"   placeholder="제목">
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											저자<input type="text"  id="writer"  name="writer" class="form-control"   placeholder="저자">
										</div>
									</div>
								</div>
								
									
								</div>
 							<button>상세 청구기호 같이 등록</button>
 							<button>청구기호 빼고 등록</button>
 							
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
