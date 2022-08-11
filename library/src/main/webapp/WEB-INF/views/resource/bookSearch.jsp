<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_Resource.jsp"%>
	        
	            <!-- 우측 메인 -->
	            <div class="col-lg-8">
	               <div class="row">
						<div class="col-lg-12 mb-5">
							<div class="col-lg-7">
								<div class="">
									<h2 class="mt-3 content-title ">
									통합 검색
									</h2>
								</div>
							</div>		
							<hr>	
							<!-- 내용 Start-->
							<!-- 신착 도서 리스트 표시 -->
							<section class="blog-wrap bg-gray">
							    <div class="container">
							        <div class="row">
							            <div class="col-lg-12">

							               	<!--  본문 시작  -->
						                	<div class="sidebar-widget search card p-4 mb-3 border-0">
												<input type="text" id ="bookSearchKeyword" class="form-control"  placeholder="검색어를 입력하세요">
												<button onclick="bookSearch()" class="btn btn-mian btn-small d-block mt-2">검색</button> 
											</div>
											
											
											<!--  검색 결과 시작  -->
											<div id ="bookDataSearchResult">
											</div>
											<!--  검색 결과 끝  -->
						             		<!--  본문 끝 -->
										</div>
									</div>
									
									
									<div id ="bookDataSearchResult_Paging">
									</div>
									
									<form name="testForm">
									</form>
					                		

								</div><!-- container -->
							</section>
							
							
							
						</div>
					</div>
				</div>
				<!-- 우측 메인 End-->
				
	    	</div>
	    </div>
	</section>
</div>
<script src="/js_custom/resource/bookSearch.js"></script>
<%@ include file="../layout/footer.jsp"%>
