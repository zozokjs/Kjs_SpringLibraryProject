<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
 <%@ include file="../layout/submenu_Guide.jsp"%>
	            <!-- 우측 메인 -->
	            <div class="col-lg-8">
	               <div class="row">
						<div class="col-lg-12 mb-5">
							
							<div class="col-lg-7">
								<div class="">
									<h2 class="mt-3 content-title ">
									오시는 길
									</h2>
								</div>
							</div>		
							<hr>			
							<!-- 지도 시작 -->
							<div id="map2" style="width: 1140px; height: 480px;">
								<div id="map2" style="width: 700px; height: 500px;">
									<script type="text/javascript"
										src="//dapi.kakao.com/v2/maps/sdk.js?appkey=01d44202f2f9d241ef89ae26c19707c1"></script>
									<script type="text/javascript"
										src="//dapi.kakao.com/v2/maps/sdk.js?appkey=01d44202f2f9d241ef89ae26c19707c1&libraries=services,clusterer,drawing"></script>
									<script>
										
										//진우도
										var mapContainer = document
												.getElementById('map2'), // 지도를 표시할 div 
										mapOption = {
											center : new kakao.maps.LatLng(
													35.066584859135595,
													128.87436413407838), // 지도의 중심좌표
											level : 5
										// 지도의 확대 레벨
										};
										var map = new kakao.maps.Map(
												mapContainer, mapOption); // 지도를 생성합니다
										var iwContent = '<div style="width:100%; height:90px; padding:5px; background-color: white; font-family:함초롬바탕;"><strong style="color:black;">토르두스</strong><strong style="color:blue">국립</strong><strong style="color:black;">도서관</strong><br><a href="https://map.kakao.com/link/map/토르두스국립도서관,35.066584859135595,128.87436413407838" style="color:blue; font-family:함초롬바탕;" target="_blank">큰지도보기</a> <a href="https://map.kakao.com/link/map/토르두스국립도서관,35.066584859135595,128.87436413407838" style="color:blue; font-family:함초롬바탕;" target="_blank">길찾기</a></div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
										iwPosition = new kakao.maps.LatLng(
												35.06686622784739,
												128.87248523526407), //인포윈도우 표시 위치입니다
										iwRemoveable = false; // removeable 속성을 true 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다
										// 인포윈도우를 생성하고 지도에 표시합니다
										var infowindow = new kakao.maps.InfoWindow(
												{
													map : map, // 인포윈도우가 표시될 지도
													position : iwPosition,
													content : iwContent,
													removable : iwRemoveable
												});
										map.setDraggable(false); //지도 드래그 이동 끄기
										$(window).resize(function() {
											console.log("호출");
											map.relayout();
										});
										map.relayout();
									</script>
								</div>
							</div>
							<!-- 지도 끝 -->
							
							
							<div class="blog-item-content bg-white p-5">
							
								<h4 class="mt-3 mb-4">지하철이용시</h4>
								<p class = "lead mb-4 font-weight-normal text-black">
									1호선 동래역 하차후, 버스이용(마을버스 17번) 2번출구앞 좌측편 
									동래지하철역 버스정류장에서 마을버스 17번 이용
									3호선 종합운동장역 하차후,버스이용(54번,83-1번,마을버스 17번) 
									9번출구앞에서 약50m직진 후,삼정그린아파트
									정류장에서 54번,83-1번,마을버스 17번 이용 1,2호선 서면역 
									하차후,버스이용(133번, 54번, 81번,
									83-1번, 103번) 13번출구로 나와서 부전시장입구 정류장에서 
									133번,54번,81번,83-1번,103번 이용
								</p>
								
								<h4 class="mt-3 mb-4">버스 이용시 </h4>
								<p class = "lead mb-4 font-weight-normal text-black">
									동래방면:44번,마을버스 17번 구포,가야방면:33번 만덕방면:133번 해운대방면:63번
									서면방면:54번,81번,103번 
								</p>
								
								<h4 class="mt-3 mb-4">도로교통이용안내 </h4>
								<p class = "lead mb-4 font-weight-normal text-black">
								 	미남,동래,연산교차로->거성사거리에서 초읍방향으로 900m 직진
									서면교차로->부암교차로(초읍방향)->어린이대공원 앞에서 우회전 하마정사거리->부산시민공원 정문(국립국악원)에서
									좌회전->초읍삼거리 우회전
								</p>
								
								<h4 class="mt-3 mb-4">보다 자세한 사항은  </h4>
								<p class = "lead mb-4 font-weight-normal text-black">
								 	지하철 : 부산교통공사 사이버 스테이션(
									http://www.humetro.busan.kr/homepage/cyberstation/map.do )참조 버 스
									: 부산시 버스노선 안내센터 홈페이지( http://bus.busan.go.kr )참조
								</p>
								
							</div>
						</div>
					</div>
				</div>
				<!-- 우측 메인 End-->
				
	    	</div>
	    </div>
	</section>
</div>
<%@ include file="../layout/footer.jsp"%>