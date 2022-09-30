//   https : //tyrannocoding.tistory.com/15  

$(document).ready(function() {
	$('#summernote').summernote({
		  height: 400,                 // 에디터 높이
		  minHeight: 300,             // 최소 높이
		  maxHeight: null,             // 최대 높이
		  focus: true,                  // 에디터 로딩후 포커스를 맞출지 여부
		  lang: "ko-KR",					// 한글 설정
		  placeholder: '.',	//placeholder 설정
          toolbar: [
							['style', ['style']],
							['fontsize', ['fontsize']],
							['font', ['bold', 'italic', 'underline', 'clear']],
							['fontname', ['fontname']],
							['color', ['color']],
							['para', ['ul', 'ol', 'paragraph']],
							['height', ['height']],
							['table', ['table']],
							['insert', ['link', 'picture', 'hr']],
							['view', ['codeview']]
						],
						fontNames:['함초롬바탕', '나눔고딕',  '맑은 고딕', '궁서체','sans-serif', 'Arial', 'Comic Sans MS', 'Courier New','Verdana' ],
						callbacks: {	
							//이미지 업로드 했을 때 동작함(글 작성 완료와는 관계 없이)
							onImageUpload: function(files) {

								for(var ind = files.length - 1; ind >= 0; ind--){
									//console.log("이미지 길이 "+files.length)

									uploadSummernoteImageFile(files[ind], this);
								}
							}
						}
	});
	$('#summernote').summernote('fontSize',15); //기본 폰트 크기 설정
	$('#summernote').summernote('fontName', '나눔고딕'); //기본 폰트 설정
	$('#summernote').summernote('justifyLeft'); //기본 문단 정렬 설정(왼쪽)
	
}); 

function uploadSummernoteImageFile(file, el){

	//alert("이미지 업로드 중");

	data = new FormData();
	data.append("file", file);
	$.ajax({
		data : data,
		type : "post",
		url : `/user/api/community/summernoteImage`,
		contentType : false,
		enctype : 'multipart/form-data',
		processData : false,
		beforeSend : function(xhr) 
        {   /*데이터를 전송하기 전에 헤더에 csrf값 설정*/
			xhr.setRequestHeader(csrfHeaderValue, csrfTokenValue);
        },
		success : function(data) {
				//console.log(data)
					$(el).summernote('editor.insertImage', '/community/'+data);
		}
	})
	/**
	.done(res =>{
		console.log("성공", res.message);
		data.url = res.message;
		$(el).summernote('editor.insertImage', data.url);
		
	}).fail(error =>{
		
	}); */

} 