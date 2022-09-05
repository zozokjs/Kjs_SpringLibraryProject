<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<style>
	
	.footerAlarmDiv{
		display : none;
		position : fixed;
		bottom : 100px;
		right : 40px; 
	}
	
	.footerAlarm {
		font-size:60px;
		color : white;
		padding : 20px;
		border-radius: 50%;
		background: blue;
	}

   
	.footerLogo_outSide{
		display:flex; 
		flex-direction: column; 
		align-items: center;
	}
	
	.footerLogo {
		padding-bottom:1px; 
		display:flex; 
		justify-content:center;
	}
	
	.footerLogo_underDiv{
		margin: auto;
	}
	
	/*최대 width가 1024이하일 때 아래로 변경함*/
	@media (max-width:1024px) {
			.footerLogo{
				padding-bottom:1px; 
				display:flex; 
				flex-direction : column
			}
			
			.footerLogo_underDiv{
			margin-top:20px;
		}
	}
	
</style>
<div class = "footerAlarmDiv"  >
	<i class="bi bi-bell-fill footerAlarm"></i>
</div>
<footer class="footer-a">
	<div class="wrapper-padding">
	<hr>
		<div class="footerLogo_outSide"  >
			
		
			<div class="typography footerLogo" >
			
				<div class="footerLogo_underDiv" >
					<a href="/">
						<img alt=""  style="height:47px; width:250px;" src="/img_custom/로고_완성3_dark2.png" />
						<!-- 로고 관련 static > js > script.js에서 $headerUp과 $headerDown 찾기 -->
					</a>
				</div>
				<div class="content-wrapper" style="margin-left: 15px;">
					<div class="block-qoutes">
						<p>
							대한민국 부산광역시 강서구 신호동 진우도 진우로1번길1 <br>
							토르두스 이듀스골타 마티마 민넬르헤이민 4번 
						</p>
					</div>
				</div>
			</div>
			<div>
				<p>고객센터 | 사이트 관리자 연락처 : zozokjs@naver.com</p>
			</div>
			
		</div>
	<hr>
	</div>
	<div class="clear"></div>
</footer>

<!-- // scripts // -->
<!-- 모달 구현 시작(순서 바뀌면 안 됨.. 이게 항상 위에 있어야 함. js파일이 늦게 위치해야 한다.)-->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/css/bootstrap.min.css" integrity="sha384-Smlep5jCw/wG7hdkwQ/Z5nLIefveQRIY9nfy6xoR1uRYBtpZgI6339F5dgvm/e9B" crossorigin="anonymous">

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.2/js/bootstrap.min.js" integrity="sha384-o+RDsa0aLu++PJvFqy8fFScvbHFLtbvScb8AjopnFD+iEQ7wo/CG0xlczd+2O/em" crossorigin="anonymous"></script>
<script src="/js/bootstrap-4-Modal-Wrapper/dist/bootstrap-modal-wrapper-factory.min.js"></script>
<!-- 모달 구현 끝-->




<script src="/js/jquery.min.js"></script>
<script src="/js/jquery-ui.min.js"></script>
<!-- <script src="https://code.jquery.com/jquery-3.6.0.js"></script> -->
<!-- 		<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script> -->
<script src="/js/jqeury.appear.js"></script>
<script src="/js/script.js"></script>
<script src="/js/idangerous.swiper.js"></script>

<script src="/js/owl.carousel.min.js"></script>
<script src="/js/bxSlider.js"></script>
<script src="/js/custom.select.js"></script>
<script src="/js/slideInit.js"></script>
<!-- 	<script type="text/javascript" src="/js/twitterfeed.js"></script> -->

<script src="/js_custom/script_custom.js"></script>



<!-- \\ scripts \\ -->

</body>
</html>
