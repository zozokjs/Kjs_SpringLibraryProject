<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_Guide.jsp"%>

<style>
	
	.kakaoMap_small_Button{
	display: block;
	    width: 50%;
	    height: 20px;
	    padding-top: 5px;
	    background: white;
	    border-radius: 2px;
	    -webkit-border-radius: 2px;
	    -moz-border-radius: 2px;
	    text-align: center;
	    font-size: 14px;
	    text-decoration: none;
	    color: gray;
	    border: 0px;
	    margin-top: 8px;
	    float: left;
	    font-family:양재다운명조M;
	    font-weight: normal;
	    text-transform: uppercase;
	}
	
	.kakaoMap_iwContentBox{
		width:100%; 
		height:52px; 
		padding:5px; 
		background-color: white; 
		font-family:양재다운명조M;
		border-right:1px solid black;
	}
	kakaoMap_iwContentBox div a{
	font-family:양재다운명조M;
	}
	
	.map_wrap {position: relative;overflow: hidden;width: 100%;height: 350px;}
	.radius_border{border:1px solid #919191;border-radius:5px;}     
	.custom_typecontrol {position:absolute;top:10px;right:10px;overflow:hidden;width:130px;height:30px;margin:0;padding:0;z-index:1;font-size:12px;font-family:'Malgun Gothic', '맑은 고딕', sans-serif;}
	.custom_typecontrol span {display:block;width:65px;height:30px;float:left;text-align:center;line-height:30px;cursor:pointer;}
	.custom_typecontrol .btn {background:#fff;background:linear-gradient(#fff,  #e6e6e6);}       
	.custom_typecontrol .btn:hover {background:#f5f5f5;background:linear-gradient(#f5f5f5,#e3e3e3);}
	.custom_typecontrol .btn:active {background:#e6e6e6;background:linear-gradient(#e6e6e6, #fff);}    
	.custom_typecontrol .selected_btn {color:#fff;background:#425470;background:linear-gradient(#425470, #5b6d8a);}
	.custom_typecontrol .selected_btn:hover {color:#fff;}   
	.custom_zoomcontrol {position:absolute;top:50px;right:10px;width:36px;height:80px;overflow:hidden;z-index:1;background-color:#f5f5f5;} 
	.custom_zoomcontrol span {display:block;width:36px;height:40px;text-align:center;cursor:pointer;}     
	.custom_zoomcontrol span img {width:15px;height:15px;padding:12px 0;border:none;}             
	.custom_zoomcontrol span:first-child{border-bottom:1px solid #bfbfbf;}


</style>

	<!-- 우측 메인 시작 -->
	<div class="two-colls-right">
		<div class="two-colls-right-b">
			<div class="padding">
				<div class="right-Submenu-2depth">
					찾아오시는 길
				</div>
				
				
				
				<!-- 지도 시작 -->
				<div class="map_wrap">
					<div id="map" style="width: 1140px; height: 480px;"></div>
					<!-- 지도타입 컨트롤 div 입니다 -->
					<div class="custom_typecontrol radius_border">
						<span id="btnRoadmap" class="selected_btn" onclick="setMapType('roadmap')">지도</span> 
						<span id="btnSkyview" class="btn" onclick="setMapType('skyview')">스카이뷰</span>
					</div>
					<!-- 지도 확대, 축소 컨트롤 div 입니다 -->
					<div class="custom_zoomcontrol radius_border">
						<span onclick="zoomIn()"><img src="https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/ico_plus.png" alt="확대"></span> 
						<span onclick="zoomOut()"><img src="https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/ico_minus.png" alt="축소"></span>
					</div>
				</div>
				
				<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=01d44202f2f9d241ef89ae26c19707c1"></script>
				<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=01d44202f2f9d241ef89ae26c19707c1&libraries=services,clusterer,drawing"></script>
				<script>
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
					
					// 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
					var iwContent = 
						'<div class="kakaoMap_iwContentBox">'
						+'<img src="/img_custom/favicon-16x16.png" />'
						//+'<strong style="color:black;">토르두스</strong><strong style="color:blue">국립</strong><strong style="color:black;">도서관</strong><br>'
						+'   토르두스국립도서관<br>'
						
						+'<div><a href="https://map.kakao.com/link/map/토르두스국립도서관,35.067946, 128.873730" class="kakaoMap_small_Button">큰지도</a></div>'
						+'<div><a href="https://map.kakao.com/link/map/토르두스국립도서관,35.067946, 128.873730" class="kakaoMap_small_Button">길찾기</a></div>'
						//+'<a href="https://map.kakao.com/link/map/토르두스국립도서관,35.066584859135595,128.87436413407838" style="color:blue; font-family:함초롬바탕;" target="_blank">큰지도보기</a>'
						+'<br>' 
						//+'<a href="https://map.kakao.com/link/map/토르두스국립도서관,35.066584859135595,128.87436413407838" style="color:blue; font-family:함초롬바탕;" target="_blank">길찾기</a>'
						+'</div>', 
						
					//인포윈도우 표시 위치입니다
					iwPosition = new kakao.maps.LatLng(35.067946, 128.873730), 
							
					 // removeable 속성을 true 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다		
					iwRemoveable = false;
					
					// 인포윈도우를 생성하고 지도에 표시합니다
					var infowindow = new kakao.maps.InfoWindow({
						map : map, // 인포윈도우가 표시될 지도
						position : iwPosition,
						content : iwContent,
						removable : iwRemoveable
					});
					
					//지도 드래그 이동 끄기
					map.setDraggable(false); 
					
					$(window).resize(function() {
						console.log("호출");
						map.relayout();
					});
					map.relayout();
					
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
					
				</script>
				<!-- 지도 끝 -->
	
	
				<div class="typography" style="margin-top:40px;">
					<div class="content-wrapper">
						<div class="typography-block">
							<h1>지하철 이용 시</h1>
							<p>
								1호선 동래역 하차후, 버스이용(마을버스 17번) 2번출구앞 좌측편 <br>
										동래지하철역 버스정류장에서 마을버스 17번 이용 <br>
										3호선 종합운동장역 하차후,버스이용(54번,83-1번,마을버스 17번) <br>
										9번출구앞에서 약50m직진 후,<br>
										삼정그린아파트 정류장에서 54번,83-1번,마을버스 17번 이용 1,2호선 서면역 <br>
										하차후,버스이용(133번, 54번, 81번,<br>
										83-1번, 103번) 13번출구로 나와서 부전시장입구 정류장에서 <br>
										133번,54번,81번,83-1번,103번 이용
							</p>
						</div>
						<div class="typography-block">
							<h1>버스 이용시</h1>
							<p>
								동래방면:44번,마을버스 17번 구포,가야방면:33번 만덕방면:133번 해운대방면:63번<br>
								서면방면:54번,81번,103번 <br>
							</p>
						</div>
						<div class="typography-block">
							<h1>도로교통 이용 시</h1>
							<p>
								미남,동래,연산교차로->거성사거리에서 초읍방향으로 900m 직진<br>
								서면교차로->부암교차로(초읍방향)->어린이대공원 앞에서 우회전 하마정사거리->부산시민공원 정문(국립국악원)에서<br>
								좌회전->초읍삼거리 우회전<br>
							</p>
						</div>
						
						<div class="typography-block">
							<h1>텔레포트 이용 시</h1>
							<p>
								한국이력통신연합회의 인장이 찍힌 텔레포트운용면허증을 지닌 사람에 한해 <br>
								지정된 장소에서 1일당 1회 이용
							</p>
						</div>
						
					</div>
				</div>
	
				<div class="clear"></div>
			</div><!-- end of class padding -->
		</div>
	</div><!-- end of class two-colls-right -->
	<div class="clear" ></div>
	<!-- 우측 메인 끝 -->

</div>
<div class="clear"></div>

</div>
</div>
</div>
<!-- /main-cont -->

<%@ include file="../layout/footer.jsp"%>
