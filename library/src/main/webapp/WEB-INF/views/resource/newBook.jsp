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
									<!-- 추후에 도서 대출 내역으로 바꾸기 -->
									신착 도서
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
							                <div class="row">
							                

												<c:forEach var = "booked"  items ="${book}">
												
													<!-- 반복부 Start-->
													<div class="col-lg-12 col-md-8 mb-1">
														<div class="blog-item" style="display:flex; flex-direction:row; flex-wrap:nowrap; justify-content:space-around;">
															<div style="display:flex; flex-grow:2;">
																<img src="/upload/noTitleImage_150px.jpg" alt="" class="img-fluid rounded">
															</div>
															<div class="blog-item-content bg-white p-4" style="display:flex; flex-grow:8; flex-direction: column;">
																<h3 class="mt-3 mb-3"><a href="/resource/${booked.id}/bookInfor">${booked.title}</a></h3>
																<!-- 표 Start -->
																<table class="tg">
																	<thead>
																		<tr>
																			<th class="tg-0pky">저자</th>
																			<th class="tg-0pky">${booked.writer}</th>
																			<th class="tg-0lax">발행처</th>
																			<th class="tg-0lax">${booked.publish}</th>
																		</tr>
																	</thead>
																	<tbody>
																		<tr>
																			<td class="tg-0pky">ISBN</td>
																			<td class="tg-0pky">${booked.isbn}</td>
																			<td class="tg-0lax">ISBNSET</td>
																			<td class="tg-0lax">${booked.isbnSet}</td>
																		</tr>
																		<tr>
																			<td class="tg-0pky">매체구분</td>
																			<td class="tg-0pky">${booked.bindType}</td>
																			<td class="tg-0lax">페이지</td>
																			<td class="tg-0lax">${booked.page}</td>
																		</tr>
																		<tr>
																			<td class="tg-0pky" >발행일</td>
																			<td class="tg-0pky">${booked. publishDate}</td>
																		</tr>
																		<tr>
																			<td class="tg-0pky" >대여가능수</td>
																			<td class="tg-0pky">${booked.totalAmount} / ${booked.totalAmount}</td>
																		</tr>
																	</tbody>
																</table>
																<!-- 표 End -->
															</div>
														</div>
													</div>
													<!-- 반복부 End-->
												</c:forEach>
						
											</div>
										</div>
									</div>
								</div>
							</section>
							
							
							
						</div>
					</div>
				</div>
				<!-- 우측 메인 End-->
				
	    	</div>
	    </div>
	</section>
</div>
<%@ include file="../layout/footer.jsp"%>
