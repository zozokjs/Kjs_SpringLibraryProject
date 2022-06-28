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
									청구기호 최초 등록
									</h2>
								</div>
							</div>		
							<hr>	
							
							<form action="/saseo/${book.id}/bookRegistration_kdc"  method = "post" enctype="multipart/form-data" class="contact-form bg-white rounded p-5"  id="comment-form2">
								<h4 class="mb-4">도서 정보를 입력하세요-청구기호 최초 등록</h4>
								
								<!-- hidden Tag -->
								<input type="hidden"  id="book_id"  name = "bookId" value="${book.id}" />
								<input type="hidden"  id="book_totalAmount"  value="${book.totalAmount}" />
								<input type="hidden"  id="book_kdcCallSignFamily" value="${book.kdcCallSignFamily}" />
								<!-- hidden Tag -->
								
								<!-- 표 시작 -->
								<div class="card">
									<div class="card-block table-border-style">
										<div class="table-responsive">
											<table class="table" >
												<thead style="border:1px solid black;">
													<tr class="table-active"  >
														<th class="tg-0pky">책 제목</th>
														<th class="tg-0pky">${book.title}</th>
													</tr>
													<tr class="table-active" >
														<td class="tg-0pky">ISBN</td>
														<td class="tg-0pky">${book.isbn}</td>
													</tr>
													<tr>
														<td colspan="2" style="text-align:center;">총 ${book.totalAmount}권</td>
													</tr>
													<tr>
														<td class="tg-0pky">번호</td>
														<td class="tg-0pky">청구기호</td>
													</tr>
												</thead>
												<tbody id="kdcCallSignList">
													
												</tbody>
											</table>
										</div>
									</div>
								</div>
								<!-- 표 끝 -->
 							<button>청구 기호 등록</button>
						</form>
							
						</div>
					</div>
				</div>
				<!-- 우측 메인 End-->

	    	</div>
	    </div>
	</section>
</div>
<script src="/js_custom/saseo_bookRegistration_kdc.js"></script>
<%@ include file="../layout/footer.jsp"%>
