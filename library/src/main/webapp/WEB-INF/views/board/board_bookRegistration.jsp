<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<!-- Header Close --> 

<div class="main-wrapper ">
	<section class="page-title bg-1">
	  <div class="container">
	    <div class="row">
	      <div class="col-md-12">
	        <div class="block text-center">
	          <span class="text-white">Board</span>
	          <h1 class="text-capitalize mb-4 text-lg">도서 등록</h1>
	          <ul class="list-inline">
	            <li class="list-inline-item"><a href="index.html" class="text-white">Home</a></li>
	            <li class="list-inline-item"><span class="text-white">/</span></li>
	            <li class="list-inline-item"><a href="#" class="text-white-50">News details</a></li>
	          </ul>
	        </div>
	      </div>
	    </div>
	  </div>
	</section>
	
	<section class="section blog-wrap bg-gray">
    <div class="container">
    	<div class="row">
        
            <!-- 사이드 메뉴 -->
            <!-- css 경로 : static > plugins > bootstrap > css > bootstrap.min.css -->
            <div class="col-lg-4-c col-lg-4">
                <div class="sidebar-wrap">
					<div class="sidebar-widget latest-post card border-0 p-4 mb-3">
						<h5>Latest Posts</h5>
				        <div class="media border-bottom py-3">
				            <div class="media-body">
				                <h6 class="my-2"><a href="#"> 2depth 메뉴 1 </a></h6>
				            </div>
				        </div>
   				        <div class="media border-bottom py-3">
				            <div class="media-body">
				                <h6 class="my-2"><a href="#">2depth 메뉴 2 </a></h6>
				            </div>
				        </div>
				        <div class="media border-bottom py-3">
				            <div class="media-body">
				                <h6 class="my-2"><a href="#">2depth 메뉴 3 </a></h6>
				            </div>
				        </div>
					</div>
					</div>
            </div>   
        
            <!-- 우측 메인 -->
            <div class="col-lg-8">
                <div class="row">
					<div class="col-lg-12 mb-5">

						<form action="/book/bookRegistration"  method = "post" enctype="multipart/form-data" class="contact-form bg-white rounded p-5"  id="comment-form">
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
								</div>
<!-- 							<input class="btn btn-main btn-round-full" type="submit" name="submit-contact" id="submit_contact" value="등록">
 -->							
 							<button>등록</button>
						</form>
					</div>

				</div>
			</div>
            <!-- 우측 메인 끝-->
        </div>
    </div>
	</section>
</div>
<%@ include file="../layout/footer.jsp"%>
