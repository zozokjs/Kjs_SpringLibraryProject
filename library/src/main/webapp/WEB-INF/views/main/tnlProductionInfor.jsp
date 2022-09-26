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
								<p class="title_small">FrontPage GIF 저작권자 및 출처</p>
								<p>archillect</p>
								<p>https://thedailytask.tumblr.com/post/153389792445/161031</p>
								<p>https://mobile.twitter.com/archillect/status/884698803718078464</p>
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