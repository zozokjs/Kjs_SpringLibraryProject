


$(document).ready(function() {  
	
	
	//id가 userUpdate일 때 form 내부 값 가져오기
	let data =$(`#userUpdate`).serialize();

	console.log(data);
	
	$.ajax({
		
		type : "put",
		url : `/api/main/footer/getVisitorCount`,
		contentType : "application/x-www-form-urlencoded; charset=utf-8"
		
	}).done(res =>{
		/***
		console.log("성공", res);
		console.log(res.data.countToday);
		console.log(res.data.countYesterday);
		console.log(res.data.countTotal);
		 */
		
		$("#visitorCount").html("<p>[방문자집계]  오늘 : "+res.data.countToday+"  | 어제 : "+res.data.countYesterday+"  |  전체 : "+res.data.countTotal+"")

	}).fail(error =>{
		console.log(error);
		if(error.data == null){
			console.log(error.responseText);
		}else{
			console.log(" update 실패", error);	 		
			alert(JSON.stringify(error.responseJSON.data));
		}
		 
	})
	
});
