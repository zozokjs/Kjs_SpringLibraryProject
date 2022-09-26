/**

자바스크립트 및 제이쿼리 양식 작성하는 곳

 */
 /*
 window.onload = function() { 
    //alert('페이지 전체가 로드되었습니다.');
  };
	``노크메프 패턴에 의한 요청을 수신하고 있습니다. Λαμβάνουμε αιτήματα από το μοτίβο Knockmap...
	(지구와 최초 연결을 성공한 노크메프 나루가 사용한 마력 발산 패턴에 의한 요청만을 수락함)
	``수신 되었습니다. Hello World! έχει παραληφθε Hello World!
	``상호간 마력 연결 중입니다. Συνδεόμαστε μεταξ μας
	``시공간 연결 유효성을 확인하고 있습니다. Επικυρώνετε τη χωροχρονική σύνδεση.
	(마력 발신자와 수신자와의 공간적 격차가 매우 큰 경우, 상호 시간 흐름 차이가 클 수 있음. 
	이는 원할한 통신을 방해하므로 발신, 수신자와 시간 흐름이 큰 차이가 없는 정도인지를 확인함)
	``연결을 확립하고 있습니다. Η σύνδεση δημιουργείται.
	``연결이 완성되었습니다. Η σύνδεση ολοκληρώθηκε.
	``안정화 작업 완료되었습니다.  Οι εργασίες σταθεροποίησης έχουν ολοκληρωθεί.
	``화면이 표시됩니다	 θα εμφανιστεί η οθόνη
*/

/*let playLog = setInterval(function(){
	console.log("hi");
	}, 3000);*/
//$("#p-log").change(alert("aaa"));
//<p>메롱 <span class='numscroller' data-slno='1' data-min='0' data-max='100' data-delay='3' data-increment='2'>0 </span>%</p>


/*function  logAppender(text){
	$("#p-log").append(text);
}
*/
setTimeout(log_1, 300);
setTimeout(log_2, 1200);
setTimeout(log_3, 1500);
setTimeout(log_4, 1900);
setTimeout(log_5, 2200);
setTimeout(log_6, 2500);
setTimeout(log_7, 2800);
setTimeout(moving, 3200);

function  log_1(){ $("#p-log").append("<p>Λαμβ νουμε αιτ ματα απ το μοτ βο Knockmap..</p>"); }
function  log_2(){ $("#p-log").append("<p>έχει παραληφθε Hello World!</p>"); }
function  log_3(){ $("#p-log").append("<p>Συνδε μαστε μεταξ μα</p>"); }
function  log_4(){ $("#p-log").append("<p> Επικυρ νετε τη χωροχρονικ σύνδεση.</p>"); }
function  log_5(){ $("#p-log").append("<p> Η σύνδεση δημιουργε ται.</p>"); }
function  log_6(){ $("#p-log").append("<p>Η σύνδεση ολοκληρ θηκε.</p>"); }
function  log_7(){ $("#p-log").append("<p>Οι εργασ ες σταθεροποηση έχουν ολοκληρωθε.</p>"); }
function  log_8(){ $("#p-log").append("<p>θα εμφανιστε η οθ νη.</p>"); }


function moving(){
	location.href = "/";
}







