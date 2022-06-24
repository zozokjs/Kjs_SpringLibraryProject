<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_User.jsp"%>
	        
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
						
							<div class="col-lg-7">
								<div class="">
									<h2 class="mt-3 content-title ">
									대출 내역
									</h2>
								</div>
							</div>		
							<hr>	
							<!-- 테이블 시작 -->
							<table class="tg">
								<thead class="table-active">
									<tr>
										<th class="tg-0lax">번호</th>
										<th class="tg-0lax">책제목</th>
										<th class="tg-0lax">저자</th>
										<th class="tg-0lax">대출일</th>
										<th class="tg-0lax">반납예정일</th>
										<th class="tg-0lax">버튼 모음</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td class="tg-0pky"></td>
										<td class="tg-0pky"></td>
										<td class="tg-0pky"></td>
										<td class="tg-0pky"></td>
										<td class="tg-0lax"></td>
										<td class="tg-0lax"></td>
									</tr>
									<tr>
										<td class="tg-0lax"></td>
										<td class="tg-0lax"></td>
										<td class="tg-0lax"></td>
										<td class="tg-0lax"></td>
										<td class="tg-0lax"></td>
										<td class="tg-0lax"></td>
									</tr>
								</tbody>
							</table>
							<!-- 테이블 끝 -->
							
							
							
						</div>
					</div>
				</div>
				<!-- 우측 메인 End-->
				
	    	</div>
	    </div>
	</section>
</div>
<%@ include file="../layout/footer.jsp"%>