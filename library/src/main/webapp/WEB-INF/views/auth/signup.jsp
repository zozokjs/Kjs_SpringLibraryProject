<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Mega Able bootstrap admin template by codedthemes </title>
    <!-- HTML5 Shim and Respond.js IE10 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 10]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
      <![endif]-->
      <!-- Meta -->
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimal-ui">
      <meta http-equiv="X-UA-Compatible" content="IE=edge" />
      <meta name="description" content="Mega Able Bootstrap admin template made using Bootstrap 4 and it has huge amount of ready made feature, UI components, pages which completely fulfills any dashboard needs." />
      <meta name="keywords" content="bootstrap, bootstrap admin template, admin theme, admin dashboard, dashboard template, admin template, responsive" />
      <meta name="author" content="codedthemes" />
      <!-- Favicon icon -->

      <link rel="icon" href="/assets/images/favicon.ico" type="image/x-icon">
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
      
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
  </head>
  <link rel="stylesheet" href="/css_custom/signup.css">
  
  <body themebg-pattern="theme1">
  <!-- Pre-loader start -->
  <div class="theme-loader">
      <div class="loader-track">
          <div class="preloader-wrapper">
              <div class="spinner-layer spinner-blue">
                  <div class="circle-clipper left">
                      <div class="circle"></div>
                  </div>
                  <div class="gap-patch">
                      <div class="circle"></div>
                  </div>
                  <div class="circle-clipper right">
                      <div class="circle"></div>
                  </div>
              </div>
              <div class="spinner-layer spinner-red">
                  <div class="circle-clipper left">
                      <div class="circle"></div>
                  </div>
                  <div class="gap-patch">
                      <div class="circle"></div>
                  </div>
                  <div class="circle-clipper right">
                      <div class="circle"></div>
                  </div>
              </div>
            
              <div class="spinner-layer spinner-yellow">
                  <div class="circle-clipper left">
                      <div class="circle"></div>
                  </div>
                  <div class="gap-patch">
                      <div class="circle"></div>
                  </div>
                  <div class="circle-clipper right">
                      <div class="circle"></div>
                  </div>
              </div>
            
              <div class="spinner-layer spinner-green">
                  <div class="circle-clipper left">
                      <div class="circle"></div>
                  </div>
                  <div class="gap-patch">
                      <div class="circle"></div>
                  </div>
                  <div class="circle-clipper right">
                      <div class="circle"></div>
                  </div>
              </div>
          </div>
      </div>
  </div>
  <!-- Pre-loader end -->
  <section class="login-block">
        <!-- Container-fluid starts -->
        <div class="container-fluid">
            <div class="row">
                <div class="col-sm-12">
                
                	<!-- 회원가입 폼 Start -->
                    <form action="/auth/signup" method="post" class="md-float-material form-material">
                        <div class="auth-box card">
                            <div class="card-block">
                                <div class="row m-b-20">
                                    <div class="col-md-12">
                                        <h3 class="text-center txt-primary">회원가입</h3>
                                    </div>
                                </div>
                                
                                
                                <!-- 아이디     --------------- -->
                                <div id = "div_username" class="form-group form-primary">
                                    <input type="text"  id ="username" name="username" onkeyup ='findByUsernameR()' class="form-control"  required="">
                                    <span class="form-bar"></span>
                                    <label class="float-label">아이디</label>
                                </div>
                                <!-- 비밀번호     --------------- -->
                                <div class="row">
                                    <div class="col-sm-6">
                                        <div class="form-group form-primary">
                                            <input type="password" name="password" class="form-control" >
                                            <span class="form-bar"></span>
                                            <label class="float-label">비밀번호</label>
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group form-primary">
                                            <input type="password" name="confirm-password" class="form-control" >
                                            <span class="form-bar"></span>
                                            <label class="float-label">비밀번호 확인</label>
                                        </div>
                                    </div>
                                </div>
                                
                                <!-- 이메일     --------------- -->
                                <div class="form-group form-primary">
                                    <input type="text" name="email" class="form-control" required="">
                                    <span class="form-bar"></span>
                                    <label class="float-label">이메일</label>
                                </div>
                                
                                <!-- 이름     --------------- -->
                                <div class="form-group form-primary">
                                    <input type="text" name="name" class="form-control" required="">
                                    <span class="form-bar"></span>
                                    <label class="float-label">이름</label>
                                </div>
                                
                                <!-- 생년월일     --------------- -->
                                <div class="form-group form-primary">
                                    <input type="text" name="birth" class="form-control" required="">
                                    <span class="form-bar"></span>
                                    <label class="float-label">생년월일</label>
                                </div>
                                
                                <!-- 종족     --------------- -->
                               <div class="form-group row">
									<label class="col-sm-2 col-form-label">종족</label>
									<div class="col-sm-10">
										<select name="species" class="form-control">
											<option value="1">Human</option>
											<option value="2">Elf</option>
											<option value="3">Dwarf</option>
											<option value="4">Electron</option>
											<option value="5">Fairy</option>
											<option value="6">Furry</option>
											<option value="0">Other</option>
										</select>
									</div>
								</div>
								
								<!-- 국가     --------------- -->
								<div class="form-group row">
									<label class="col-sm-2 col-form-label">국가</label>
									<div class="col-sm-10">
										<select name="country" class="form-control">
											<option value="1">토르두스</option>
											<option value="2">일레니아</option>
											<option value="3">카리우스</option>
											<option value="4">플러터누스</option>
											<option value="5">펄</option>
											<option value="0">그 외</option>
										</select>
									</div>
								</div>
								
								<!-- 직업     --------------- -->
								<div class="form-group row">
									<label class="col-sm-2 col-form-label">직업</label>
									<div class="col-sm-10">
										<select name="job" class="form-control">
											<option value="1">학생</option>
											<option value="2">공무원</option>
											<option value="3">회사원</option>
											<option value="4">전문직</option>
											<option value="5">자영업</option>
											<option value="0">그 외</option>
										</select>
									</div>
								</div>
								
								<!-- 연락처     --------------- -->
								<div class="form-group form-primary">
                                    <input type="text" name="phoneNumber" class="form-control" required="">
                                    <span class="form-bar"></span>
                                    <label class="float-label">연락처</label>
                                </div>
								
								<!-- 주소     --------------- -->
								<div class="form-group form-primary">
                                    <input type="text" name="address" class="form-control" required="">
                                    <span class="form-bar"></span>
                                    <label class="float-label">주소</label>
                                </div>

<!-- 								<div class="row m-t-25 text-left">
                                    <div class="col-md-12">
                                        <div class="checkbox-fade fade-in-primary">
                                                <input type="checkbox" value="">
                                                <span class="cr"><i class="cr-icon icofont icofont-ui-check txt-primary"></i></span>
                                                <span class="text-inverse">I read and accept <a href="#">Terms &amp; Conditions.</a></span>
                                            </label>
                                        </div>
                                    </div>
                                </div>
 -->                                
                                <div class="row m-t-30">
                                    <div class="col-md-12">
                                        <button class="btn btn-primary btn-md btn-block waves-effect text-center m-b-20">가입</button>
                                    </div>
                                </div>
                        
                              
                                <hr/>
                                <div class="row">
                                    <div class="col-md-4">
                                        <p class="text-inverse text-left m-b-0">Thank you.</p>
                                        <p class="text-inverse text-left"><a href="index.html"><b>Back to website</b></a></p>
                                    </div>
                                    <div class="col-md-6">
                                        <img src="/images/logo-text-small-long.png" alt="small-logo.png">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                    <!-- 회원가입 폼 End -->
                </div>
                <!-- end of col-sm-12 -->
            </div>
            <!-- end of row -->
        </div>
        <!-- end of container-fluid -->
    </section>


<script src="/js_custom/signup.js"></script>


<!-- Required Jquery -->
<script type="text/javascript" src="/assets/js/jquery/jquery.min.js">
</script>  
   
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
<!--  
<script type="text/javascript" src="bower_components/i18next/js/i18next.min.js"></script>
<script type="text/javascript" src="bower_components/i18next-xhr-backend/js/i18nextXHRBackend.min.js"></script>
<script type="text/javascript" src="bower_components/i18next-browser-languagedetector/js/i18nextBrowserLanguageDetector.min.js"></script>
<script type="text/javascript" src="bower_components/jquery-i18next/js/jquery-i18next.min.js"></script>
-->
<script type="text/javascript" src="/assets/js/common-pages.js"></script>
</body>

</html>
