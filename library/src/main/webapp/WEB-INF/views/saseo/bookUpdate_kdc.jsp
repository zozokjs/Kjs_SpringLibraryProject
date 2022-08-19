<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_Saseo.jsp"%>

<style>
	.booking-form-i{
		width:50%;
	}
	
	.table-a th {
				font-size: 15px;
				text-align: center;
				width: 200px;
	}
	
	.table-a td {
				font-size: 15px;
				text-align: center;
				width: 200px;
	}

</style>

   		<div class="two-colls-right"><!-- 우측 메인 시작 -->
					<div class="two-colls-right-b">
						<div class="padding">
							<div class="right-Submenu-2depth">
								청구기호 수정
							</div>
							
							<!-- 우측 본문 시작 -->
							
							<form  id = "bookUpdate_kdc" onsubmit="bookUpdate_kdc(${book.id},event)" >
								<!-- hidden Tag -->
								<input type="hidden"  id="book_id"  name = "bookId" value="${book.id}" />
								<input type="hidden"  id="book_totalAmount"  value="${book.totalAmount}" />
								<input type="hidden"  id="book_kdcCallSignFamily" value="${book.kdcCallSignFamily}" />
								<input type="hidden"  id="sameBook" value="${sameBook}" />
								<!-- hidden Tag -->
								
								<!-- Controller에서 넘어온 List<SameBook>을 자바스크립트에서 반복시키기 위함 -->
								<script type="text/javascript">
									
									var samebookArray = new Array();
									
									<c:forEach items="${sameBook}" var="item">
										console.log("반복 중");
										samebookArray.push({										
											kdcCallSign : "${item.kdcCallSign}",
											samebookId : "${item.id}"
										});
									</c:forEach>
								
								</script>
								<div class="tables" style="margin-top: 70px; text-align:center;">
									<table class="table-a">
										<thead style="border:1px solid black;">
											<tr>
											    <td class="" >제목</td>
											    <td class=""></td>
											</tr>
											<tr>
											    <td class="">ISBN</td>
											    <td class=""></td>
										  	</tr>
										 	 <tr>
											    <td class="" colspan="2">기존 총 ${book.totalAmount}권</td>
										  	</tr>
										  	 <tr>
											    <td class="" colspan="2">현재 대출 중인 청구기호는 삭제 할 수 없습니다.</td>
										  	</tr>
									  	</thead>
										<tbody id="kdcCallSignList">
										
									  	</tbody>
									</table>
								</div>
								<div style="text-align: -webkit-center;">
									<button class=" booking-complete-btn white-btn-custom" >청구 기호 수정 완료</button>
								</div>
							</form>
							
							
							
							
							<!-- 우측 본문 끝 -->
					
				
							</div><!-- end of class [ padding] -->
						<div class="clear" ></div>
					</div><!-- end of class [ two-colls-right-b ] -->
				</div><!-- 우측 메인 끝 --><!-- end of class [ two-colls-right ] -->
				<div class="clear"></div>
				
			</div><!-- end of class [ two-colls(submenu_guide) ] -->
			<div class="clear"></div><!-- 필수 -->
		</div><!-- end of class [ wrapper-padding ] -->
	</div><!-- end of class [ body-wrapper ] -->
</div><!-- /main-cont -->

<%@ include file="../layout/footer.jsp"%>
<script src="/js_custom/saseo/bookUpdate_kdc.js"></script>
<%@ include file="../layout/footer.jsp"%>
