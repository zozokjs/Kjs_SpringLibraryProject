<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- main-cont -->
<div class="main-cont">
	<div class="body-wrapper">
		<div class="wrapper-padding">

			<div class="page-head">
				<div class="page-title" style="font-size: 30px; font-weight: bold;"></div>
				<div class="breadcrumbs" style="font-size: 20px;"></div>
				<div class="clear"></div>
			</div>
			<div class="two-colls">
				<div class="two-colls-left">
					<div class="left-SubMenu-1depth"><!-- 1depth title --></div>
					<!-- \\ side \\ -->
					<div class="blog-widget recent-widget left-SubMenu-2depth">
						<nav>
							<ul>
								<li><a href="/user/userInfor/${principal.user.id}">회원정보</a></li>
								<li><a href="/user/myLibrary">대출관리(연장/반납)</a></li>
								<li><a href="/user/myLendHistory">반납완료내역</a></li>
								<li><a href="/user/myBoardHistory">작성글목록</a></li>
							</ul>
						</nav>
					</div>
				</div>
