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
									청구기호 수정
									</h2>
								</div>
							</div>		
							<hr>	
							
							<form  id = "bookUpdate_kdc" onsubmit="bookUpdate_kdc(${book.id},event)" class="contact-form bg-white rounded p-5" >
								<h4 class="mb-4">도서 정보를 입력하세요-청구기호 수정</h4>
								
								<!-- hidden Tag -->
								<input type="hidden"  id="book_id"  name = "bookId" value="${book.id}" />
								<input type="hidden"  id="book_volume"  value="${book.volume}" />
								<input type="hidden"  id="book_kdcCallSignFamily" value="${book.kdcCallSignFamily}" />
								<input type="hidden"  id="sameBook" value="${sameBook}" />
								<!-- hidden Tag -->
								
								<!-- Controller에서 넘어온 List<SameBook>을 자바스크립트에서 반복시키기 위함 -->
								<script type="text/javascript">
									
									var samebookArray = new Array();
									
									<c:forEach items="${sameBook}" var="item">
										samebookArray.push({										
											kdcCallSign : "${item.kdcCallSign}"
										});
									</c:forEach>
								
								</script>
								
								<!-- 표 시작 -->
								<div class="card">
									<div class="card-block table-border-style">
										<div class="table-responsive">
											<table class="table" >
												<thead style="border:1px solid black;">
													<tr>
													    <th class="tg-0pky">제목</th>
													    <th class="tg-0pky"></th>
													</tr>
												
													<tr>
													    <td class="tg-0pky">ISBN</td>
													    <td class="tg-0pky"></td>
												  	</tr>
												 	 <tr>
													    <td class="tg-0pky" colspan="2">총 ${book.volume}권</td>
												  	</tr>
											  	</thead>
												<tbody id="kdcCallSignList">
												
											  	</tbody>
											</table>
										</div>
									</div>
								</div>
								<!-- 표 끝 -->
 							<button>청구 기호 수정 완료</button>
						</form>
							
						</div>
					</div>
				</div>
				<!-- 우측 메인 End-->

	    	</div>
	    </div>
	</section>
</div>
<script src="/js_custom/saseo_bookUpdate_kdc.js"></script>
<%@ include file="../layout/footer.jsp"%>
