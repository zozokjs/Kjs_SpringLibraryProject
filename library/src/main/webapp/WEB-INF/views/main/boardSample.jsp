<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_BookDatajsp"%>
           <style>
	        	table{
	        		border : 2px solid;
	        	}
	        	th, td{
	        		border : 2px solid; 
	        		border-collapse : collpase; 
	        		padding : 10px 5px;
	        		text-align :center;
	        	}
	        
	        </style>
	        
	            <!-- 우측 메인 -->
	            <div class="col-lg-8">
	               <div class="row">
						<div class="col-lg-12 mb-5">
						
							<!-- 2depth 타이틀 영역 -->
							<div class="col-lg-7">
								<div class="">
									<h2 class="mt-3 content-title ">
									2 depth 메뉴
									</h2>
								</div>
							</div>		
							<hr>	
							
							<button onclick="bookLending(${book.id},event)">대출</button> 
							<button type="button" 
	                                    onclick="location.href='/auth/signup'"
	                                    class="btn btn-primary btn-md btn-block waves-effect waves-light text-center m-b-20">회원가입</button>
							
							<input  type="hidden" name="titleImageUrl"  value="${book.titleImageUrl}"/>
							
							<!-- 본문 Start -->
							<c:choose>
								<c:when test="${empty userList}">
								</c:when>
								<c:otherwise>
								</c:otherwise>
							</c:choose>
							
							<!-- 내용 Start-->
						</div>
					</div>
				</div>
				<!-- 우측 메인 End-->
				
	    	</div>
	    </div>
	</section>
</div>
<!-- 
				<script src="/js_custom/saseo_bookUpdate.js"></script>
 -->
 <script src="/js_custom/saseo_bookUpdate_kdc.js"></script>
<%@ include file="../layout/footer.jsp"%>
