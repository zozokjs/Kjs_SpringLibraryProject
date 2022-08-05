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
									1대1 문의 작성
									</h2>
								</div>
							</div>		
							<hr>	
							
							<h4>*등록된 질문 글은 수정할 수 없습니다.</h4>
							<form action="/community/singleQuestionRegistration"  method = "get"  enctype="multipart/form-data" >
								<div class="row">
									<div class="col-md-12">
										<div class="form-group">
											제목
											<label style="color:red";>(필수)</label>
											<input type="text"  	id="title"  name="title" class="form-control" required="" >
										</div>
									</div>
									<div class="col-md-12">
										<div class="form-group">
											내용<label style="color:red";>(필수)</label>
											<textarea  id="content"  name="content" placeholder="a" class="form-control mb-3"  cols="30" rows="5"  required=""></textarea>
										</div>
									</div>
								</div>
								<button>질문 등록</button>
							</form>
							
						</div>
					</div>
				</div>
				<!-- 우측 메인 End-->
				
	    	</div>
	    </div>
	</section>
</div>

<script>
/* $(document).ready(function() {
  $('#summernote').summernote({
    tabsize: 2,
    height: 300
  });
});
 */</script>
<%@ include file="../layout/footer.jsp"%>
