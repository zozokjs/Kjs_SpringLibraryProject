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
		dataType : "json"
		
	}).done(res =>{
		
		console.log("성공", res);
		
		//검색 성공(결과 1건 이상)
		if(res.code == 0){
			makeSearchResultTrue(res.data.bookDataBySearch);
			makeSearchResultTrue_Paging(res.data.bookDataBySearch);
		}
		//검색 성공(결과 0건)
		else{
			makeSearchResultFalse(bookSearchKeyword);
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
function makeSearchResultFalse(searchKeyword){
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
			`<h3>"${searchKey}"에 대한 검색 결과</h3>
			<hr>
			<!--  검색 결과 start  -->`;
	
	
	
	/** 
	검색 결과 반복 표시*/
	bookDataBySearch.content.forEach(function(book){
	
		searchResultHTML += 
		`	
				    <div class="col-lg-12 col-md-8 mb-1"  style="display:flex;">
			            <div style="display:flex; flex-shrink:30;">
			                <img src="/upload/${book.titleImageUrl}" alt="" class="img-fluid w-100">
			            </div>
			            <div class="blog-item-content bg-white p-4" style="display:flex; flex-shrink:1; flex-direction: column;">
			                <h3 class="mt-3 mb-3"><a href="/resource/${book.id}/bookInfor">${book.title}</a></h3>							
			                <!-- 표 Start --->
			                <table class="tg">
			                    <thead>
			                        <tr>
			                            <th class="tg-0pky">저자</th>
			                            <th class="tg-0pky">${book.writer}</th>
			                            <th class="tg-0lax">발행처</th>
			                            <th class="tg-0lax">${book.publish}</th>
			                        </tr>
			                    </thead>
			                    <tbody>
			                        <tr>
			                            <td class="tg-0pky">ISBN</td>
			                            <td class="tg-0pky">${book.isbn}</td>
			                            <td class="tg-0lax">ISBNSET</td>
			                            <td class="tg-0lax">${book.isbnSet}</td>
			                        </tr>
			                        <tr>
			                            <td class="tg-0pky">매체구분</td>
			                            <td class="tg-0pky">${book.bindType}</td>
			                            <td class="tg-0lax">페이지</td>
			                            <td class="tg-0lax">${book.page}</td>
			                        </tr>
			                        <tr>
			                            <td class="tg-0pky" >발행일</td>
			                            <td class="tg-0pky">${book. publishDate}</td>
			                        </tr>
			                        <tr>
			                            <td class="tg-0pky" >대여가능수</td>
			                            <td class="tg-0pky">${book.remainAmount} / ${book.totalAmount}</td>
			                        </tr>
			                    </tbody>
			                </table>
			                <!-- 표 End -->
			            </div>
			    	</div>
				  			`
	});
	
	
	/** 
	검색 결과 표시 종료*/
	searchResultHTML += 
	`<!--검색 결과 End -->`;
	
	/** 
	HTML 붙여 넣기*/
	$("#bookDataSearchResult").html(searchResultHTML);
	
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
								<!-- 페이지 버튼 시작 -->
					                <div class="row justify-content-center mt-5">
						            	<div class="col-lg-12 text-center">
							            	<nav class="navigation pagination d-inline-block">
								                <div class="nav-links">`;
								                
	/** 
	이전 버튼 표시*/		                
	if(bookDataBySearch.first != true){
		//searchResultHTML_Paging += `	<a class="prev page-numbers" href="?page=${bookDataBySearch.number-1}">이전</a> `;
		searchResultHTML_Paging += `	<a class="prev page-numbers"  href="javascript:void(0);"  onclick="bookSearchPagingProcess(${bookDataBySearch.number-1})" >이전</a> `;
	}

													
	/** 
	페이지 버튼 반복 표시*/
	for(let index=1; index<=bookDataBySearch.totalPages; index++){
		
			//현재 페이지일 때
			if(bookDataBySearch.number+1  ==  index){
				searchResultHTML_Paging += `<span aria-current="page" class="page-numbers current">${index}</span>`;
			}else{
				//searchResultHTML_Paging += `	<a class="page-numbers current" href="?page=${index-1}">${index}</a> `;
				searchResultHTML_Paging += `	<a class="page-numbers current" href="javascript:void(0);" onclick="bookSearchPagingProcess(${index-1})"   >${index}</a> `;
				
			}
	}


	/** 
	다음 버튼 표시*/		
	if(bookDataBySearch.last != true){
		//searchResultHTML_Paging += `	<a class="next page-numbers disabled"  href="?page=${bookDataBySearch.number+1}">다음</a> `;
		//searchResultHTML_Paging += `	<a id = "nextP" class="next page-numbers disabled"   href="javascript:void(0);"  onclick = "bookSearchPagingProcess(${bookDataBySearch.number+1})">다음</a> `;
		searchResultHTML_Paging += `	<a id = "nextP" class="next page-numbers disabled"   href="javascript:void(0);"  onclick="bookSearchPagingProcess(${bookDataBySearch.number+1})" >다음</a> `;

	}
	
	
	/** 
	페이지 버튼 끝*/			
	searchResultHTML_Paging +=
	`																			
								                </div>
							                </nav>
			                			</div>
					                </div>
	      							<!-- 페이지 버튼 끝 -->`;
	      							
	      							
	/** 
	HTML 붙여 넣기*/
	$("#bookDataSearchResult_Paging").html(searchResultHTML_Paging);
	
}// end of makeSearchResultTrue_Paging()




 /*
 도서 검색 처리 페이지하는 경우 */
function bookSearchPagingProcess(pageIndex) {
	
	//alert(pageIndex);
	/*
	const bookSearchKeyword = $("#bookSearchKeyword").val(); //검색어
	console.log("검색어 > "+bookSearchKeyword);
	//console.log(typeof(bookIdNumber));
	*/
	
	$.ajax({
		type : "get",
		//url : `?page=${pageIndex}`,
		//url : `/api/resource/${searchKey}/bookSearch?page=1`,
		url : `/api/resource/${searchKey}/bookSearch?page=${pageIndex}`,
		dataType : "json"
		
	}).done(res =>{
		console.log("성공", res);
		
		//검색 성공(결과 1건 이상)
		if(res.code == 0){
			makeSearchResultTrue(res.data.bookDataBySearch);
			makeSearchResultTrue_Paging(res.data.bookDataBySearch);
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
}//end of bookSearch()



