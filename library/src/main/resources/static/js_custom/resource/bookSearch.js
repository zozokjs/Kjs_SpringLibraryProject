/** 검색 로직


(1)index 페이지에서 검색 시
ResourceController.java ->  bookSearch.jsp

(2)bookSearch.jsp에서 검색 시 
bookSearch.js의 bookSearch() POST요청 -> ResourceAPIController.java

(3)bookSearch.jsp에서 페이징 할 때
 bookSearch.js의 bookSearchPagingProcess() GET요청-> ResourceAPIController.java
 
 */


//검색어
let searchKey;

 /*
 도서 검색 처리 */
function bookSearch() {

	const bookSearchKeyword = $("#bookSearchKeyword").val(); //검색어
	console.log("검색어 > "+bookSearchKeyword);
	//console.log(typeof(bookIdNumber));
	searchKey = bookSearchKeyword; //검색어를 전역변수에 할당함
	
	$.ajax({
		
		type : "post",
		url : `/api/resource/${bookSearchKeyword}/bookSearch`,
		dataType : "json",
		beforeSend : function(xhr) 
        {   /*데이터를 전송하기 전에 헤더에 csrf값 설정*/
			xhr.setRequestHeader(csrfHeaderValue, csrfTokenValue);
        }
		
	}).done(res =>{
		
		console.log("성공", res);
		
		//검색 성공(결과 1건 이상)
		if(res.code == 0){
			makeSearchResultTrue(res.data.bookDataBySearch);
			makeSearchResultTrue_Paging(res.data.bookDataBySearch);
		}
		//검색 성공(결과 0건)
		else{
			makeSearchResultFalse();
		}
	}).fail(error =>{
		
		if(error.data == null){
			alert(error.responseJSON.message);
			console.log(error.responseJSON.message);
			
		}else{
			console.log(" 검색 실패", error);	 		
			
	
			//그래서 자바스크립트 문자열을 JSON.stringfy()로 JSON 문자열로 변환함 
			alert(JSON.stringify(error.responseJSON.data));
		
		};
	})
}//end of bookSearch()



 /** 
 검색 결과가 0건일 때 HTML 표시
 
  */
function makeSearchResultFalse(){
	const dat = `<h3> "${searchKey}"에 대한 검색 결과가 없습니다.</h3>`;
	$("#bookDataSearchResult").html(dat);
}//end of makeSearchResultFalse()



 /** 
 검색 결과가 1건 이상일 때 HTML 표시
 
  */
function makeSearchResultTrue(bookDataBySearch){
	
	/** 
	변수 선언*/
	let searchResultHTML = 
			`
			<!-- 현재 페이지에서 검색한 검색 결과가 표시됩니다.-->
			<h1>"${searchKey}"에 대한 검색 결과</h1>
			<input  type="hidden"  id="searchKeyword"  value="${searchKey}"/>
			<hr>
			<!--  검색 결과 start  -->`;
	
	
	
	/** 
	검색 결과 반복 표시*/
	bookDataBySearch.content.forEach(function(books){
		
		searchResultHTML += 
		`
					<div class="cat-list-item fly-in"><!-- 반복부 시작 -->
						<div class="cat-list-item-l">
							<a href="#">
								<img alt="" src="/upload/${books.titleImageUrl}"  ><!-- 타이틀 이미지 -->
							</a>
						</div>
						<div class="cat-list-item-r"><!-- 여기부터 콘텐츠 영역 -->
							<div class="cat-list-item-rb">
								<div class="cat-list-item-p">
									<div class="cat-list-content">
										<div class="cat-list-content-a">
											<div class="cat-list-content-l"  style="margin-right:0px; ">
												<div class="cat-list-content-lb">
													<div class="cat-list-content-lpadding"><!--  내용 시작 -->
														<div class="tables" >
														
															<table class="table-a light">
																<tr>
																	<td>제목</td>
																	<td colspan="3">
																		<a href="/resource/${books.id}/bookInfor" >${books.title}</a>
																	</td>
																</tr>
																<tr>
																	<td>저자</td>
																	<td colspan="3">${books.writer}</td>
																</tr>
																<tr>
																	<td>발행처</td>
																	<td>${books.publish}</td>
																	<td>발행일</td>
																	<td>${books.publishDate}</td>
																</tr>
																<tr>
																	<td>매체구분</td>
																	<td>${books.bindType}</td>
																	<td>페이지</td>
																	<td>${books.page}</td>
																</tr>
																<tr>
																	<td>ISBN</td>
																	<td>${books.isbn}</td>
																	<td>ISBN SET</td>
																	<td>${books.isbnSet}</td>
																</tr>
																<tr>
																	<td>대여가능수</td>
																	<td colspan="3">${books.remainAmount} / ${books.totalAmount}</td>
																</tr>
															</table>
															
														</div>	
													</div><!-- 내용 끝 -->
													
												</div>
												<br class="clear" />
											</div>
										</div>
										
										<div class="clear"></div>
									</div>
								</div>
							</div>
							<br class="clear" />
						</div>
						<div class="clear"></div>
					</div><!-- 반복부 끝 -->
		`
	});
	
	
	/** 
	검색 결과 표시 종료*/
	searchResultHTML += 
	`<!--검색 결과 End -->`;
	
	/** 
	HTML 붙여 넣기*/
	$("#bookDataSearchResult").html(searchResultHTML);
	//index 화면에서 검색한 결과를 삭제함
	$("#bookDataSearchResult_fromIndex").html("");
}//end of makeSearchResultTrue()




 /** 
 검색 결과가 1건 이상일 때 페이징 처리
 
  */
function makeSearchResultTrue_Paging(bookDataBySearch){
	
	
	console.log("현재 first "+bookDataBySearch.first);
	console.log("현재 last "+bookDataBySearch.last);
	console.log("현재 Number "+bookDataBySearch.number);
	 
	 
	/** 
	페이지 버튼 시작*/		
	let searchResultHTML_Paging = 
	`
								<!-- 현재 페이지에서 검색한 검색 결과가 표시됩니다.-->
				                <div class="pagination pagination-custom"  style="display:flex; justify-content:center;"><!-- 페이지 버튼 시작 -->`;
						            	
								                
	/** 
	이전 버튼 표시*/		                
	if(bookDataBySearch.first != true){
		searchResultHTML_Paging += `	<a href="javascript:void(0);"  onclick="bookSearchPagingProcess(${bookDataBySearch.number-1})" >이전</a> `;
	}

													
	/** 
	페이지 버튼 반복 표시*/
	for(let index=1; index<=bookDataBySearch.totalPages; index++){
		
			//현재 페이지일 때
			if(bookDataBySearch.number+1  ==  index){
				searchResultHTML_Paging += `<a class="active" >${index}</a>`;
			}else{
				searchResultHTML_Paging += `	<a  href="javascript:void(0);" onclick="bookSearchPagingProcess(${index-1})"   >${index}</a> `;
				
			}
	}


	/** 
	다음 버튼 표시*/		
	if(bookDataBySearch.last != true){
		searchResultHTML_Paging += `	<a  href="javascript:void(0);"  onclick="bookSearchPagingProcess(${bookDataBySearch.number+1})" >다음</a> `;

	}
	
	
	/** 
	페이지 버튼 끝*/			
	searchResultHTML_Paging +=
	`																			
								                
					                </div><!-- 페이지 버튼 끝 -->`;
	      							
	      							
	      							
	/** 
	HTML 붙여 넣기*/
	$("#bookDataSearchResult_Paging").html(searchResultHTML_Paging);
	$("#bookDataSearchResultPaging_fromIndex").html("");//index 화면에서 검색한 결과를 삭제함
}// end of makeSearchResultTrue_Paging()




 /*
 도서 검색  -> 페이징 처리 */
function bookSearchPagingProcess(pageIndex) {
	
	//alert(pageIndex);
	if($("#searchKeyword").val() != undefined){
		searchKey = $("#searchKeyword").val();
		//
	}else{
		searchKey =  $("#bookSearchKeyword").val();
	}
	
	//alert(searchKey);
	console.log("bookSearchPagingProcess  READ");
	console.log("검색어 > "+searchKey);
	//console.log(typeof(bookIdNumber));
	console.log("페이지 > "+pageIndex);
	
	$.ajax({
		type : "get",
		//url : `?page=${pageIndex}`,
		//url : `/api/resource/${searchKey}/bookSearch?page=1`,
		url : `/api/resource/${searchKey}/bookSearch?page=${pageIndex}`,
		dataType : "json",
		beforeSend : function(xhr) 
        {   /*데이터를 전송하기 전에 헤더에 csrf값 설정*/
			xhr.setRequestHeader(csrfHeaderValue, csrfTokenValue);
        }
		
	}).done(res =>{
		console.log("성공", res);
		
		//검색 성공(결과 1건 이상)
		if(res.code == 0){
			makeSearchResultTrue(res.data.bookDataBySearch);
			makeSearchResultTrue_Paging(res.data.bookDataBySearch);
			
			$("#bookDataSearchResult_fromIndex").remove();
			$("#bookDataSearchResultPaging_fromIndex").remove();
		}
	}).fail(error =>{
		
		if(error.data == null){
			//alert(error.responseJSON.message);
			//console.log(error.responseJSON);
			
		}else{
			console.log(" 검색 실패", error);	 		
			
	
			//그래서 자바스크립트 문자열을 JSON.stringfy()로 JSON 문자열로 변환함 
			//alert(JSON.stringify(error.responseJSON.data));
		
		};
	})
}//end of bookSearchPagingProcess()



