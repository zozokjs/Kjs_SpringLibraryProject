<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<!-- Header Close --> 

<div class="main-wrapper ">
	<section class="page-title bg-1">
	  <div class="container">
	    <div class="row">
	      <div class="col-md-12">
	        <div class="block text-center">
	          <span class="text-white">Board</span>
	          <h1 class="text-capitalize mb-4 text-lg">Sample</h1>
	          <ul class="list-inline">
	            <li class="list-inline-item"><a href="index.html" class="text-white">Home</a></li>
	            <li class="list-inline-item"><span class="text-white">/</span></li>
	            <li class="list-inline-item"><a href="#" class="text-white-50">News details</a></li>
	          </ul>
	        </div>
	      </div>
	    </div>
	  </div>
	</section>
	
	<section class="section blog-wrap bg-gray">
    <div class="container">
        <div class="row">
        
            <!-- 사이드 메뉴 -->
            <!-- css 경로 : static > plugins > bootstrap > css > bootstrap.min.css -->
            <div class="col-lg-4-c col-lg-4">
                <div class="sidebar-wrap">
					<div class="sidebar-widget latest-post card border-0 p-4 mb-3">
						<h5>Board Sample</h5>
				        <div class="media border-bottom py-3">
				            <div class="media-body">
				                <h6 class="my-2"><a href="#"> 2depth 메뉴 1 </a></h6>
				            </div>
				        </div>
   				        <div class="media border-bottom py-3">
				            <div class="media-body">
				                <h6 class="my-2"><a href="#">2depth 메뉴 2 </a></h6>
				            </div>
				        </div>
				        <div class="media border-bottom py-3">
				            <div class="media-body">
				                <h6 class="my-2"><a href="#">2depth 메뉴 3 </a></h6>
				            </div>
				        </div>
					</div>
					</div>
            </div>   
        
            <!-- 우측 메인 -->
            <div class="col-lg-8">
                <div class="row">
					<div class="col-lg-12 mb-5">
						<div class="single-blog-item">
							<img src="/images/blog/2.jpg" alt="" class="img-fluid rounded">
				
							<div class="blog-item-content bg-white p-5">
								<div class="blog-item-meta bg-gray py-1 px-2">
									<span class="text-muted text-capitalize mr-3"><i class="ti-pencil-alt mr-2"></i>Creativity</span>
									<span class="text-muted text-capitalize mr-3"><i class="ti-comment mr-2"></i>5 Comments</span>
									<span class="text-black text-capitalize mr-3"><i class="ti-time mr-1"></i> 28th January</span>
								</div> 
				
								<h2 class="mt-3 mb-4"><a href="blog-single.html">Improve design with typography?</a></h2>
								<p class="lead mb-4">Non illo quas blanditiis repellendus laboriosam minima animi. Consectetur accusantium pariatur repudiandae!</p>
				
								<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Possimus natus, consectetur? Illum libero vel nihil nisi quae, voluptatem, sapiente necessitatibus distinctio voluptates, iusto qui. Laboriosam autem, nam voluptate in beatae.</p>
				
								<h3 class="quote">A brand for a company is like a reputation for a person. You earn reputation by trying to do hard things well.</h3>
								
								<p class="lead mb-4 font-weight-normal text-black">The same is true as we experience the emotional sensation of stress from our first instances of social rejection ridicule. We quickly learn to fear and thus automatically.</p>
				
								<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Iste, rerum beatae repellat tenetur incidunt quisquam libero dolores laudantium. Nesciunt quis itaque quidem, voluptatem autem eos animi laborum iusto expedita sapiente.</p>
				
								<div class="tag-option mt-5 clearfix">
								    <ul class="float-left list-inline"> 
								    	<li>Tags:</li> 
								    	<li class="list-inline-item"><a href="#" rel="tag">Advancher</a></li>
								    	<li class="list-inline-item"><a href="#" rel="tag">Landscape</a></li>
								    	<li class="list-inline-item"><a href="#" rel="tag">Travel</a></li>
								   	</ul>        
				
								    <ul class="float-right list-inline">
								        <li class="list-inline-item"> Share: </li>
								        <li class="list-inline-item"><a href="#" target="_blank"><i class="fab fa-facebook-f" aria-hidden="true"></i></a></li>
								        <li class="list-inline-item"><a href="#" target="_blank"><i class="fab fa-twitter" aria-hidden="true"></i></a></li>
								        <li class="list-inline-item"><a href="#" target="_blank"><i class="fab fa-pinterest-p" aria-hidden="true"></i></a></li>
								        <li class="list-inline-item"><a href="#" target="_blank"><i class="fab fa-google-plus" aria-hidden="true"></i></a></li>
								    </ul>
							    </div>
							</div>
						</div>
					</div>

					</div>
				</div>
            </div>
        </div>
		</section>
</div>
<%@ include file="../layout/footer.jsp"%>
