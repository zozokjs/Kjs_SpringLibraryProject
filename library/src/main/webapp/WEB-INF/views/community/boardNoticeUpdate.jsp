<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_Community.jsp"%>
	        
	            <!-- 우측 메인 -->
	            <div class="col-lg-9">
	               <div class="row">
						<div class="col-lg-12 mb-5">
						
							<!-- 2depth 타이틀 영역 -->
							<div class="col-lg-7">
								<div class="">
									<h2 class="mt-3 content-title ">
									게시글 수정하기
									</h2>
								</div>
							</div>		
							<hr>	
							
							<form action="/community/${boardNotice.id}/boardNoticeUpdate"  method = "post" enctype="multipart/form-data" >

								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											제목
											<input type="text"  	id="title"  name="title" value = "${boardNotice.title}"   class="form-control"  onfocus= "this.blur();" readonly="readonly">
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											작성자
											<input type="text"  	id="title"  name="title" value = "${boardNotice.user.username}"   class="form-control"  onfocus= "this.blur();" readonly="readonly">
										</div>
									</div>
									<div class="col-md-6">
										<div class="form-group">
											등록일
											<input type="text"  	id="title"  name="title" value = "${boardNotice.createDate}"   class="form-control"  onfocus= "this.blur();" readonly="readonly">
										</div>
									</div>
									
									<div class="col-md-12">
										<div class="form-group">
											내용
											<div>
												<textarea  id="content"  name="content" class="form-control mb-3"  cols="30" rows="5"  required="">
													${boardNotice.content}
												</textarea>
											</div>										
										</div>
									</div>
								</div>
								<button>수정 완료</button>
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
