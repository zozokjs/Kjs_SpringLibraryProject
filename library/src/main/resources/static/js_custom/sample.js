/**


자바스크립트 및 제이쿼리 양식 작성하는 곳

 */
 
 //속성 추가
 //ex) <input type="text" data-type="danger">
 $('#username').attr('data-type','danger');
 
 //속성 앞에 추가
 $('#div_username').prepend('<span class="error-message">Please enter numbers only!</span>')
 $('#username').after('<span class="error-message">Please enter numbers only!</span>')
 
 
 
 //id가 div_username에서 input 태그의 이벤트 발생 시
$('#div_username').on('input', function (evt) {
  var value = evt.target.value
  
  if (value.length === 0) {
    evt.target.className = ''
    return
  }
 
  // 숫자일 때
  if ($.isNumeric(value)) {
	
	//evt의 부모 노드의 클래스 변경
    evt.target.parentNode.className = 'form-group form-primary'
    
   //evt가 발생된 것 이후에 추가 
    evt.target.after('<label class="float-label">form-danger</label>')
    
    
  } else {
    evt.target.parentNode.className = 'form-group form-danger form-static-label',
  }
 
 
 //