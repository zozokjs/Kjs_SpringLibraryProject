<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>ㄴㅇㄴ</title>
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css"
        integrity="sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p" crossorigin="anonymous" />
      <!-- Favicon icon -->

      <link rel="icon" href="assets/images/favicon.ico" type="image/x-icon">
      <!-- Google font-->     
      <link href="https://fonts.googleapis.com/css?family=Roboto:400,500" rel="stylesheet">
      <!-- Required Fremwork -->
      <link rel="stylesheet" type="text/css" href="/assets/css/bootstrap/css/bootstrap.min.css">
      <!-- waves.css -->
      <link rel="stylesheet" href="/assets/pages/waves/css/waves.min.css" type="text/css" media="all">
      <!-- themify-icons line icon -->
      <link rel="stylesheet" type="text/css" href="/assets/icon/themify-icons/themify-icons.css">
      <!-- ico font -->
      <link rel="stylesheet" type="text/css" href="/assets/icon/icofont/css/icofont.css">
      <!-- Font Awesome -->
      <link rel="stylesheet" type="text/css" href="/assets/icon/font-awesome/css/font-awesome.min.css">
      <!-- Style.css -->
      <link rel="stylesheet" type="text/css" href="/assets/css/style.css">
</head>
<body>

<section class="login-block">
        <!-- Container-fluid starts -->
        <div class="container">
            <div class="row">
                <div class="col-sm-12">
                    <!-- Authentication card start -->
	                    <div class="auth-box card">
	                        <div class="card-block">
	                            <div class="row m-b-20">
	                                <div class="col-md-12">
	                                    <h3 class="text-center">토르두스 제국 도서관</h3>
	                                </div>
	                            </div>
	                            
	                            
	                            <!-- start of form -->	
	                            <form action ="/auth/signin" method="POST" class="md-float-material form-material">
	                            
		                            <!-- 아이디 -->
		                            <div class="form-group form-primary">
		                                <input type="text" name="username"  value="zozo" class="form-control" required="required">
		                                <span class="form-bar"></span>
		                                <label class="float-label">ID</label>
		                            </div>
		                            
		                            <!-- 비번 -->
		                            <div class="form-group form-primary">
		                                <input type="password" name="password"  value="1234" class="form-control" required="required">
		                                <span class="form-bar"></span>
		                                <label class="float-label">Password</label>
		                            </div>
		                            
		                            <div class="row m-t-25 text-left">
		                                <div class="col-12">
		                                    <div class="checkbox-fade fade-in-primary d-">
		                                        <label>
		                                            <input type="checkbox" value="">
		                                            <span class="cr"><i class="cr-icon icofont icofont-ui-check txt-primary"></i></span>
		                                            <span class="text-inverse">Remember me</span>
		                                        </label>
		                                    </div>
		                                    <div class="forgot-phone text-right f-right">
		                                        <a href="#" class="text-right f-w-600"> Forgot Password?</a>
		                                    </div>
		                                </div>
		                            </div>
		                            <!-- 로그인 버튼 -->
		                            <div class="row m-t-30">
		                                <div class="col-md-12">
		                                    <button class="btn btn-primary btn-md btn-block waves-effect waves-light text-center m-b-20">로그인</button>
		                                </div>
		                            </div>
	                            </form>
	                			<!-- end of form -->	
	                            
	                            
	                            <div class="row m-t-30">
	                                <div class="col-md-12">
	                                    <button type="button" 
	                                    onclick="location.href='/auth/signup'"
	                                    class="btn btn-primary btn-md btn-block waves-effect waves-light text-center m-b-20">회원가입</button>
	                                </div>
	                            </div>
	                            <hr/>
	                            <div class="row">
	                                <div class="col-md-10">
	                                    <p class="text-inverse text-left"><a href="index.html"><b>Back to website</b></a></p>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
                            
                        
                </div>
                <!-- end of col-sm-12 -->
            </div>
            <!-- end of row -->
        </div>
        <!-- end of container-fluid -->
    </section>
<!-- Required Jquery -->
<script type="text/javascript" src="/assets/js/jquery/jquery.min.js"></script>     
<script type="text/javascript" src="/assets/js/jquery-ui/jquery-ui.min.js "></script>     
<script type="text/javascript" src="/assets/js/popper.js/popper.min.js"></script>     
<script type="text/javascript" src="/assets/js/bootstrap/js/bootstrap.min.js "></script>
<!-- waves js -->
<script src="/assets/pages/waves/js/waves.min.js"></script>
<!-- jquery slimscroll js -->
<script type="text/javascript" src="/assets/js/jquery-slimscroll/jquery.slimscroll.js "></script>
<!-- modernizr js -->
    <script type="text/javascript" src="/assets/js/SmoothScroll.js"></script>    
     <script src="/assets/js/jquery.mCustomScrollbar.concat.min.js "></script>
<!-- i18next.min.js -->
<script type="text/javascript" src="/assets/js/common-pages.js"></script>
</body>

</html>
