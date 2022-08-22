<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<%@ include file="../layout/submenu_Community.jsp"%>

	<!-- w3cschool -->
    <!-- https://www.w3schools.com/howto/howto_js_accordion.asp -->
    
           <style>
           /* Style the buttons that are used to open and close the accordion panel */
			.accordion {
				  background-color: #eee;
				  color: #444;
				  cursor: pointer;
				  padding: 18px;
				  width: 100%;
				  border: none;
				  text-align: left;
				  outline: none;
				  font-size: 15px;
				  transition: 0.4s;
				}
				
				.active, .accordion:hover {
				  background-color: #ccc;
				}
				
				.accordion:after {
				  content: '\002B';
				  color: #777;
				  font-weight: bold;
				  float: right;
				  margin-left: 5px;
				}
				
				.active:after {
				  content: "\2212";
				}
				
				.panel {
				  padding: 0 18px;
				  background-color: white;
				  max-height: 0;
				  overflow: hidden;
				  transition: max-height 0.2s ease-out;
				}
	        
	        </style>
	        
	            <!-- 우측 메인 -->
	            <div class="col-lg-9">
	               <div class="row">
						<div class="col-lg-12 mb-5">
						
							<!-- 2depth 타이틀 영역 -->
							<div class="col-lg-7">
								<div class="">
									<h2 class="mt-3 content-title ">
									자주하는질문
									</h2>
								</div>
							</div>		
							<hr>	
							
							<!-- 본문 Start -->
							<div class="col-lg-12 col-md-8 mb-1" >
							
								<br>
								
								<button class="accordion">Q.도서관은 언제 이용한가요?</button>
								<div class="panel">
								  <p>
								  A. 우리도서관은 정기휴관일(매월 마지막주 월요일)과 국가공휴일, 그 외 공사 등의 특별한 사정으로 도서관장이 지정한 날을 제외하고는 언제나 무료로 이용 가능합니다.
								  </p>
								</div>
								
								<button class="accordion">Q.도서를 기증하려면 어떻게 해야하나요?</button>
								<div class="panel">
								  <p>
								  A. 대상자료 : 발행연도 5년 이내의 단행본, 희귀자료 및 학술적 가치가 있는 자료 등
										기증방법 : 우편/택배 또는 도서관 직접 방문
										기증장소 : (우)47103 부산광역시 부산진구 월드컵대로 462 1층 사서과
								  </p>
								</div>
								
								<button class="accordion">Q.타인의 대출카드로도 빌릴 수 있나요?</button>
								<div class="panel">
								  <p>
								  A. 본인 독서회원카드로만 대출 가능합니다.
								  </p>
								</div>
								
								<button class="accordion">Q.필요한 자료를 인쇄할 수 있나요?</button>
								<div class="panel">
							    	<p>
								  	A. 디지털자료실(3층) 모든 PC 및 복사실(2층)에 비치된 PC에서 인쇄 가능합니다.
									결제방법은 신용카드, 체크카드, 티머니 교통카드, 삼성페이로 가능하고 금액은 아래와 같습니다.
									</p>
								</div>
								
								<button class="accordion">Q.무선인터넷을 사용할 수 있나요?</button>
								<div class="panel">
								 	<p>
								  	A. 무선 단말기(스마트폰, 노트북 등)의 네트워크 연결설정에서 “Busan_Lib"을 선택하면 누구나 이용가능합니다.<br>
										무선인터넷 이용 가능 장소 : 지하 식당, 1층 휴게실 근처, 2층 복도, 3층 디지털실, 3층 여자열람실 노트북코너(멀티미디어학습공간), 3층 복도, 3층 휴게실, 4층 남자열람실 노트북코너(멀티미디어학습공간), 4층 옥외휴게실 근처
									</p>
								</div>	
								
								
                            </div>
                 
	 				
							<!-- 본분 End -->
							
						</div>
					</div>
				</div>
				<!-- 우측 메인 End-->
				
	    	</div>
	    </div>
	</section>
</div>

<script>
	var acc = document.getElementsByClassName("accordion");
	var i;
	
	for (i = 0; i < acc.length; i++) {
	  acc[i].addEventListener("click", function() {
	    this.classList.toggle("active");
	    var panel = this.nextElementSibling;
	    if (panel.style.maxHeight) {
	      panel.style.maxHeight = null;
	    } else {
	      panel.style.maxHeight = panel.scrollHeight + "px";
	    } 
	  });
	}
</script>
<%@ include file="../layout/footer.jsp"%>
