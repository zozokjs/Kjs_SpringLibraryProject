<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- Header Close --> 

<div class="main-wrapper ">
	<div class="">
		<div class="container" style="padding: 10px 0;">
			<div style="display:flex; flex-direction:row; flex-wrap:nowrap; justify-content:flex-end; color:black; font-weight:bold; background-color:white;">
							<div>Home > 1depth > 2depth</div>
			</div>

	
		</div>
	</div>
	
	<section class="section blog-wrap bg-gray" style="padding: 30px 0;">
	    <div class="container">
	        <div class="row">
	        
	            <!-- 사이드 메뉴 -->
	            <!-- css 경로 : static > plugins > bootstrap > css > bootstrap.min.css -->
	            <div class="col-lg-3-c col-lg-3">
	                <div class="sidebar-wrap">
						<div class="sidebar-widget latest-post card border-0 p-4 mb-3">
							<h5>내 서재</h5>
							
					        <div class="media border-bottom py-3">
					            <div class="media-body">
					                <h6 class="my-2"><a href="/user/${principal.user.id}/userInfor"> 회원정보 </a></h6>
					            </div>
					        </div>
	   				        <div class="media border-bottom py-3">
					            <div class="media-body">
					                <h6 class="my-2"><a href="/user/myLibrary">대출관리(연장/반납) </a></h6>
					            </div>
					        </div>
					        <div class="media border-bottom py-3">
					            <div class="media-body">
					                <h6 class="my-2"><a href="/user/myLendHistory">대출내역 </a></h6>
					            </div>
					        </div>
					        <div class="media border-bottom py-3">
					            <div class="media-body">
					                <h6 class="my-2"><a href="#">희망도서신청관리</a></h6>
					            </div>
					        </div>
					        <div class="media border-bottom py-3">
					            <div class="media-body">
					                <h6 class="my-2"><a href="#">작성한 글</a></h6>
					            </div>
					        </div>
							<!-- 스크랩 보기 / 쪽지함 보기 / 로그인 기록 / 내 알람 목록 / 내 알람 설정 -->
					        
						</div>
					</div>
	           	</div>   