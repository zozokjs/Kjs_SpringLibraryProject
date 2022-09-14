<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<style>
 .mm-3{
/*  	border : 1px solid black; */
 	margin-right: 18px;
 	margin-left : 20px;
 }
 
  .title_small{
	font-size: 19px;
 }
 
 .columns-block-lbl span{
	padding-bottom: 0px;
  	display: block;
  	background: none;
 }
</style>

<div class="main-cont">

	<div class="inner-page">
		

		<div class="content-wrapper" style="margin-top: 50px;">

<!-- 			<div class="portfolio-full">
				<div class="portfolio-full-img" style="text-align: center;">
					1100 x 634px;
					<img alt="" style="width: 40%;" src="/img_custom/로고_완성2.png" />
				</div>
			</div> -->

			<div class="columns">
				<div class="content-wrapper columns">
					<div class="columns-block">
						<div class="columns-block-lbl"><span><h4>사이트 제작 정보</h4></span></div>
						<div class="columns-row">
							<hr>
						
							<div class="column mm-3">
								<h4>개발 언어</h4>
								<p>프론트엔드 : Javascript, Jquery</p>
								<p>백엔드 : <img alt="" style="width: 100%;" src="/img_custom/springboot_logo.png" /></p>
							</div>
							
							<div class="column mm-3">
								<h4>저작권 정보</h4>
								<p class="title_small">홈페이지 템플릿(구매됨) 출처</p>
								<p>https://themeforest.net/item/sparrow-responsive-travel-online-booking-template/12308398</p>
								<hr>
								<p class="title_small">사진 저작권자 및 출처</p>
								<p>사진 저작권자는 각 이미지 파일의 속성에 표시</p>
								<p>https://pixabay.com/ko/</p>
								<hr>
								<p class="title_small">글자체 저작권자 및 출처</p>
								<p>네이버 나눔명조(일부)</p>
								<p>https://hangeul.naver.com/2021/fonts/nanum</p>
							</div>
							
							<div class="column mm-3">
								<h4>제작자 정보</h4>
								<p>Email : zozokjs@naver.com</p>
								<p>Blog : blog.naver.com/zozokjs</p>
							</div>
						</div>
						<div class="clear"></div>
					</div>
				</div>
			</div>
			
			
			<div style="text-align: center;">
				<button onclick="location.href='/'" class="white-btn-custom" style=" display: inline-block; margin-bottom:50px;">첫 화면으로</button>
			</div>

			<div class="clear"></div>



		</div>
	</div>
</div>

<%@ include file="../layout/footer.jsp"%>