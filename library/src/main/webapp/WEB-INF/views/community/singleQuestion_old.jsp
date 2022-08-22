<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_Community.jsp"%>
           <style>
           
	        	table{
	        /* 		border : 2px solid;
	         */	}
	        	
	        	thead{
	        		border : 2.5px solid;
	        	}
	        	th, td{
	        		text-align :center;
	        	}
	        	/**
	        	
	        	th, td{
	        		border : 2px solid; 
	        		border-collapse : collpase; 
	        		padding : 10px 5px;
	        		text-align :center;
	        	}*/
	        
	        </style>
	        
	            <!-- 우측 메인 -->
	            <div class="col-lg-9">
	               <div class="row">
						<div class="col-lg-12 mb-5">
						
							<!-- 2depth 타이틀 영역 -->
							<div class="col-lg-7">
								<div class="">
									<h2 class="mt-3 content-title ">
									1ㄷ1 질문하기
									</h2>
								</div>
							</div>		
							<hr>	
							
							<!-- 본문 Start -->
							<div class="col-lg-12 col-md-8 mb-1" >
								<div class="blog-item-content bg-white"  style="padding: 0.1rem !important;">
								
									<table class="table">
										<thead>
											<tr>
												 <th class="tg-0pky">번호</th>
												 <th class="tg-0pky">제목</th>
												 <th class="tg-0pky">작성자</th>
												 <th class="tg-0pky">등록날짜</th>
												 <th class="tg-0pky">처리상황</th>
												 
											</tr>
										</thead>
										<tbody>
											<c:forEach var = "SingleQ" items ="${singleQuestionList.content}"   varStatus="index">
												<tr>
													 <td class="tg-0pky">${SingleQ.id}</td>
													 <td class="tg-0pky">
													 	<a href="/community/${SingleQ.id}/singleQuestionInfor">${SingleQ.title}</a> 
													 </td>
													 <td class="tg-0pky">${SingleQ.user.username}</td>
													 <td class="tg-0pky">${SingleQ.createDate}</td>
													 
													 <c:choose>
													 	<c:when test="${SingleQ.answerOk eq true}">
													 		<td class="tg-0pky">답변 완료</td>
													 	</c:when>
													 	<c:otherwise>
														 	<td class="tg-0pky"> 답변 대기</td>
													 	</c:otherwise>
													 </c:choose>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<button type="button" 
	                                   onclick="location.href='/community/singleQuestionRegistrationForm'"
	                                   class="btn btn-primary btn-md btn-block waves-effect waves-light text-center m-b-20">질문하기</button>									
								</div>
	 						</div>
	 				
							<!-- 본분 End -->
							
							
			                <!-- 페이지 버튼 Start -->
			                <div class="row justify-content-center mt-5">
				            	<div class="col-lg-12 text-center">
					            	<nav class="navigation pagination d-inline-block">
						                <div class="nav-links">
					                	
					                		<!-- 이전 버튼 -->
							                <c:choose>
												<c:when test="${singleQuestionList.first }">
												</c:when>
												<c:otherwise>
													<a class="prev page-numbers" href="?page=${singleQuestionList.number-1}">이전</a>
												</c:otherwise>
											</c:choose>
												
							                <c:forEach var="index" begin="${startPage}" end="${endPage}">
						                      	<c:choose>
													<c:when test="${singleQuestionList.number+1  eq index}" >
													   <span aria-current="page" class="page-numbers current">${index}</span>
													</c:when>
													<c:otherwise>
														<a class="page-numbers current" href="?page=${index-1}">${index}</a>
													</c:otherwise>
												</c:choose>
							                </c:forEach>
								          
								                
											
											<!-- 다음 버튼 -->
 											<c:choose>
												<c:when test="${singleQuestionList.last }">
												</c:when>
												<c:otherwise>
													<a class="next page-numbers disabled"  href="?page=${singleQuestionList.number+1}">다음</a>
												</c:otherwise>
											</c:choose>
										
						                </div>
					                </nav>
	                			</div>
			                </div>
   							<!-- 페이지 버튼 End -->
							
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
