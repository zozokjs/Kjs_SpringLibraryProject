//진우도
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapOption = {
			center : new kakao.maps.LatLng(35.066584859135595,
					128.87436413407838), // 지도의 중심좌표
			level : 6
		// 지도의 확대 레벨
		};

// 지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption); 


// 지도타입 컨트롤의 지도 또는 스카이뷰 버튼을 클릭하면 호출되어 지도타입을 바꾸는 함수입니다
function setMapType(maptype) { 
    var roadmapControl = document.getElementById('btnRoadmap');
    var skyviewControl = document.getElementById('btnSkyview'); 
    if (maptype === 'roadmap') {
        map.setMapTypeId(kakao.maps.MapTypeId.ROADMAP);    
        roadmapControl.className = 'selected_btn';
        skyviewControl.className = 'btn';
    } else {
        map.setMapTypeId(kakao.maps.MapTypeId.HYBRID);    
        skyviewControl.className = 'selected_btn';
        roadmapControl.className = 'btn';
    }
}

// 지도 확대, 축소 컨트롤에서 확대 버튼을 누르면 호출되어 지도를 확대하는 함수입니다
function zoomIn() {
    map.setLevel(map.getLevel() - 1);
}

// 지도 확대, 축소 컨트롤에서 축소 버튼을 누르면 호출되어 지도를 확대하는 함수입니다
function zoomOut() {
    map.setLevel(map.getLevel() + 1);
}


var iwContent = 
	'<div class="kakaoMap_iwContentBox">'
	+'<img src="/img_custom/favicon-16x16.png" />'
	+'   토르두스국립도서관<br>'
	+'<div><a href="https://map.kakao.com/link/map/토르두스국립도서관,35.067946, 128.873730" class="kakaoMap_small_Button">큰지도</a></div>'
	+'<div><a href="https://map.kakao.com/link/map/토르두스국립도서관,35.067946, 128.873730" class="kakaoMap_small_Button">길찾기</a></div>'
	+'<br>' 
	+'</div>', 
	
iwPosition = new kakao.maps.LatLng(35.067946, 128.873730), 
		
iwRemoveable = false;

var infowindow = new kakao.maps.InfoWindow({
	map : map, // 인포윈도우가 표시될 지도
	position : iwPosition,
	content : iwContent,
	removable : iwRemoveable
});

map.setDraggable(false); 

$(window).resize(function() {
	//console.log("호출");
	map.relayout();
});
map.relayout();



