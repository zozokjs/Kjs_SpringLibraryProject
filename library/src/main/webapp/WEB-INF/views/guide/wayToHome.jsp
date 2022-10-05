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
		height:90px; 
		padding:5px; 
		background-color: white; 
		font-family:양재다운명조M;
	}
	
	.kakaoMap_iwContentBox div a{
	font-family:양재다운명조M;
	}
	html, body {width:100%;height:100%;margin:0;padding:0;} 
	.map_wrap {position:relative;overflow:hidden;width:100%;height:350px;}
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
				<!-- 지도 끝 -->
				<div class="map_wrap">
				    <div id="map" style="width:100%;height:100%;position:relative;overflow:hidden;"></div> 
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
			
			 	
				<div class="typography" style="margin-top:40px;">
					<div class="content-wrapper">
						<div class="typography-block">
							<h1>방문객에게 알립니다</h1>
							<div class="blockqoute-tp-a">
								<span>
									진우도에 접근하셔도 육체 형태로는 도서관 포털 진입이 불가합니다.<br>
									진우도 지반이 매우 연약하오니 소담공원 내에 위치한 포털 사용 시 주의바랍니다.<br>
									소담공원 근방에는 주차공간이 없사오니 대중교통 이용 바랍니다.
								</span> <b></b>
							</div>
						</div>
						
						<hr>
							
						<div class="typography-block">
							<h1>대중교통 이용시</h1>
							<p class="guide_pTag">
								(1) 1호선 하단역 하차 후 '하단역 버스정류장'에서 3번 버스 탑승 <br>
								(2) '신호중학교 버스정류장'에서 하차 후 소담공원으로 도보 이동(약 2분)<br>
								(3) 소담공원에 위치한 안내문에 따라 바닷가 쪽으로 이동 후 포털 사용
							</p>
						</div>
						
						<hr>
						
						<div class="typography-block">
							<h1>자가용 이용 시</h1>
							<p class="guide_pTag">
								(1) 네비게이션에 '부산 소담공원' 입력 후 이동<br>
								(2) 소담공원에 위치한 안내문에 따라 바닷가 쪽으로 이동 후 포털 사용
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
<script src="/js_custom/guide/wayToHome.js"></script>
<%@ include file="../layout/footer.jsp"%>
