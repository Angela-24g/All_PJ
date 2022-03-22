<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<link href="https://fonts.googleapis.com/css?family=Lato"
	rel="stylesheet" type="text/css">

<link href="https://fonts.googleapis.com/css?family=Montserrat"
	rel="stylesheet" type="text/css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


<style>
body {
	font: 400 15px/1.8 Lato, sans-serif;
	color: #777;
}

h3, h4 {
	margin: 10px, 0, 30px, 0;
	letter-spacing: 10px;
	font-size: 20px;
	color: #111;
}

/* .container {

			padding: 200px 120px;

		}  */
.person {
	border: 10px solid transparent;
	margin-bottom: 10px;
	width: 50%;
	height: 80%;
	opacity: 0.9;
}

.carousel.slide {
	/* nav과 캐러셀을 분리하기 위해서! 캐러셀에 마진 값을 줌!!!! 캐러셀 안의 클래스를 불러오는 것이니 (.캐러셀.슬라이드)!!! */
	margin-top: 50px;
}

.carousel-inner img {
	/* -webkit-filter: grayscale(90%); */
	/* filter: grayscale(90%);  */
	width: 100%;
	margin: auto;
}

@media ( max-width :600px) {
	.carousel-caption {
		display: none;
	}
}

.bg-1 {
	background: #2d2d30;
	color: #bdbdbd;
}

.bg-1 h3 {
	color: #fff;
}

.bg-1 p {
	font-style: italic;
}

.list-group-item:first-child {
	border-top-right-radius: 0;
	border-top-left-radius: 0;
}

.thumbnail {
	padding: 0 0 15px 0;
	border: none;
	border-radius: 0;
}

.thumbnail p {
	margin-top: 15px;
	color: #555;
}

.btn {
	padding: 10px 20px;
	background-color: #333;
	color: #f1f1f1;
	border-radius: 0;
	transition: .2s;
}

.btn:hover, .btn:focus {
	border: 1px solid #333;
	background-color: #fff;
	color: #000;
}

#googleMap {
	width: 100%;
	height: 400px;
	-webkit-filter: grayscale(100%);
	filter: grayscale(100%);
}




.open .dropdown-toggle {
	color: #fff;
	background-color: #555 !important;
}

.dropdown-menu li a {
	color: #000 !important;
}

.dropdown-menu li a:hover {
	background-color: red !important;
}

.footer {
	
	background-color: black;
	color: #f5f5f5;
	padding: 32px;
}

.footer a {
	color: #f5f5f5;
}

.footer a:hover {
	color: #777;
	text-decoration: none;;
}

.form-control {
	border-radius: 0;
}

textarea {
	resize: none;
}

#fl-intro {
	width: 100%;
	height: 300px;
	background-color: #FFEBFE; /* #FFEBFE */
}

#fl-intro p {
	text-align: left;
}

#fl-intro img {
	padding: 0;
}

#about {
	width: 100%;
	height: 300px;
	padding: 40px;
	background-color: pink;
}

.container-h {
	/* 	margin-bottom: 50px; */
	height: 240px;
	background-color: white;
}
/* 	#about .container1{
			
			width:33%;
 			
		} */
#about #container2 {
	/* 	padding-left: 300px;
			padding-right: 300px;
			width:60%; */
	
}

.container4 {
	width: 100%;
	height: 80px;
	background-color: #B2CCFF;
}
.footer1{
	width: "100%"; 
	height:80px;
	background-color:black;
}
</style>
</head>
<body>

<body id="myPage" data-spy="scroll" data-target=".navbar">
	<!-- carousel  -->

	<!-- 캐러셀 로직

	1. 홍보하고자 하는 내용을 넣을것.

	1-1. 인디케이터(무엇을 몇개를 출력할건지 넣기)

	1-2. 슬라이드(캐러셀에 적용될 사진 올리기)

	1-3. 왼쪽/오른쪽으로 갈 방향키 올리기 -->



	<div id="myCarousel" class="carousel slide" data-role="carousel">



		<ol class="carousel-indicators">

			<li data-target="myCarousel" data-slide-to=0 class="active"></li>
			<!-- data타겟으로 id myCarousel을 가리키고 슬라이드로 뭐부터 몇까지 할건지를 설정! -->

			<li data-target="myCarousel" data-slide-to=1></li>
			<!--  active는 점 3개에 어디에 점을 채울 것인지를 나타내는 것이다. -->

			<li data-target="myCarousel" data-slide-to=2></li>

		</ol>



		<div class="carousel-inner" role="listbox">
			<!-- listbox로 캐러셀 전체 박스 안에 캐러셀 이너 박스를 넣고 사진들을(items) 넣을 것! -->



			<!-- <div class="item active">

				<img src="C:\javaproject\Myhomepage\public\img\fl1.jpg"
					alt="first-flower" width="120" height="20">

				<div class="carousel-caption">
					그냥 그림에 이름 붙이는 거다!

					<h4>All Flowers</h4>

					<p>Lorem Ipsum</p>

				</div>

			</div>

			<div class="item">

				<img src="C:\javaproject\Myhomepage\public\img\fl2.jpg"
					alt="second-flower" width="120" height="20">

				<div class="carousel-caption">
					그냥 그림에 이름 붙이는 거다!

					<h4>One flower</h4>

					<p>Lorem Ipsum</p>

				</div>

			</div>

			<div class="item">

				<img src="C:\javaproject\Myhomepage\public\img\fl3.jpg"
					alt="third-flower" width="120" height="20">

				<div class="carousel-caption">
					그냥 그림에 이름 붙이는 거다!

					<h4>Other Flowers</h4>

					<p>Lorem Ipsum</p>

				</div>

			</div> -->

		</div>

		<!-- 밑에 코드 자체가 전체적으로 캐러셀을 움직일 수 있게 만드는 구조이다!!! -->

		<a href="#myCarousel" role="button" data-slide="prev"
			class="left carousel-control"> <span
			class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span> <span
			class="sr-only">Previous</span>

		</a> <a href="#myCarousel" role="button" data-slide="next"
			class="right carousel-control"> <span
			class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>

			<span class="sr-only">Next</span>

		</a>

	</div>

	<!-- flower introduction bar -->

	<!-- 소개 로직

	1. 이달의 꽃 소개 이름 붙이기

	2. 먼저, 칸을 가로로 2칸으로 나누기

	2-1. 칸을 두개로 나누되, 비율을 3:7로 나눌 것!

	3. 해당 네모칸의 전체 배경(단색 이미지 넣기)

	3-1. 아님 꽃 배경(대각선으로 양쪽에 하나씩 있는 것만 구해서 넣기)

	4. 가로로 첫번째꺼를 이미지 넣고 두번째인 곳은 꽃 소개 및 소개 영상(및 꽃 관리법 영상 링크)

	 -->

	<div id="about">
		<div class="container-h">
			<!-- 	<div class="row"> -->
			<!-- <div class="container1">
					<img src="C:\javaproject\Myhomepage\public\img\fl5.jpg" alt="new flower" width="300" height="20" class="img-circle person">
				</div> -->
			<div id="container2" class="container text-center">

				<h3>Flower</h3>

				<p>We love Spring!!</p>

				<p>Where does it come from? Contrary to popular belief, Lorem
					Ipsum is not simply random text. It has roots in a piece of

					classical Latin literature from 45 BC, making it over 2000 years
					old. Richard McClintock, a Latin professor at Hampden-Sydney
					College in Virginia, looked up one of the more obscure Latin words,

					consectetur, from a Lorem Ipsum passage, and going through the
					cites of the word in classical literature, discovered the
					undoubtable source. Lorem Ipsum comes from sections 1.10.32 and
					1.10.33 of "de Finibus Bonorum et Malorum" (The Extremes of Good
					and Evil) by Cicero, written in 45 BC. This book is a treatise on
					the theory of ethics, very popular during the Renaissance. The
					first line of Lorem Ipsum, "Lorem ipsum dolor sit amet..", comes
					from a line in section 1.10.32.</p>

			</div>
			<!-- </div>	 -->
		</div>

		<!-- <div id="fl-intro" class="container text-left">

		<div class="row">

			<div class="col-sm-4">

			<p class="text-left"><strong>이 달의 꽃소개</strong></p><br>

			<p>

The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for those interested. Sections 1.10.32 and 1.10.33 from "de Finibus Bonorum et Malorum" by Cicero are also reproduced in their exact original form, accompanied by English versions from the 1914 translation by H. Rackham.</p>
-->

		<div class="bg-1" id="tour">

			<div class="container">
				<p class="text-center">
					<br>
				</p>
				<div id="myCarousel" class="carousel slide" data-role="carousel">


					<ol class="carousel-indicators">
						<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
						<li data-target="#myCarousel" data-slide-to="1"></li>
						<li data-target="#myCarousel" data-slide-to="2"></li>
					</ol>
					<div class="row">
					<div class="carousel-inner" role="listbox">

						<div class="item active">
							<div class="col-sm-4">
								<div class="row text-center">
									<div class="item">
										<div class="thumbnail">
											<img src="C:\javaproject\Myhomepage\public\img\fl1.jpg"
												alt="Paris" width="400" height="300">
											<p>
												<strong>Paris</strong>
											</p>
											<p>Friday 17 November 2021</p>
											<button class="btn" data-toggle="modal"
												data-target="#myModal">Buy Tickets</button>
										</div>
									</div>
									<div class="item">
										<div class="thumbnail">
											<img src="C:\javaproject\Myhomepage\public\img\fl2.jpg"
												alt="New York" width="400" height="300">
											<p>
												<strong>New York</strong>
											</p>
											<p>Friday 17 November 2021</p>
											<button class="btn" data-toggle="modal"
												data-target="#myModal">Buy Tickets</button>
										</div>
									</div>
									<div class="item">
										<div class="thumbnail">
											<img src="C:\javaproject\Myhomepage\public\img\fl3.jpg"
												alt="San Francisco" width="400" height="300">
											<p>
												<strong>San Francisco</strong>
											</p>
											<p>Friday 17 November 2021</p>
											<button class="btn" data-toggle="modal"
												data-target="#myModal">Buy Tickets</button>
										</div>
									</div>
								</div>
							</div>
						</div>


						<a href="#myCarousel" role="button" data-slide="prev"
							class="left carousel-control"> <span
							class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
							<span class="sr-only">Previous</span>
						</a> <a href="#myCarousel" role="button" data-slide="next"
							class="right carousel-control"> <span
							class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
							<span class="sr-only">Next</span>
						</a>
					</div>
				</div>
			</div>
		</div></div>
		<!-- bottom bar -->

		<div id="contact" class="container">



			<h3 class="text-center">Contact</h3>

			<p class="text-center">
				<em>Contact on flower campaign</em>
			</p>

			<div class="row">

				<div class="col-sm-4">

					<p>Come with us</p>

					<p>
						<span class="glyphicon glyphicon-map-marker"></span> Chicago, US
					</p>

					<p>
						<span class="glyphicon glyphicon-phone"></span> Phone:
						010-9999-8888
					</p>

					<p>
						<span class="glyphicon glyphicon-envelope"></span> eMail:
						master@myhome.com
					</p>

				</div>

				<div class="col-sm-8">

					<div class="row">

						<div class="col-sm-6 form-group">

							<input type="text" class="form-control" name="name" id="name"
								placeholder="name..." required>

						</div>

						<div class="col-sm-6 form-group">

							<input type="email" id="email" class="form-control" name="email"
								placeholder="email..." required="" />

						</div>

						<textarea name="comments" id="comments" rows="5"
							class="form-control"></textarea>

						<br>

						<div class="row">

							<div class="col-sm-12 form-group">

								<button type="button" class="btn pull-right">Send</button>

							</div>

						</div>

					</div>

				</div>

			</div>

			<br>

			<h3 class="text-left">Other Contact</h3>
			<div class="row">
				<p>
					<a class="fa fa-facebook-square" href="http://www.facebook.com"
						style="font-size: 30px"></a>
				</p>
				<p>
					<a class="fa fa-instagram" href="http://www.facebook.com"
						style="font-size: 30px"></a>
				</p>
				<p>
					<a class="fa fa-youtube-play" href="http://www.facebook.com"
						style="font-size: 30px"></a>
				</p>
				<p>
					<a class="fa fa-amazon" href="http://www.facebook.com"
						style="font-size: 30px"></a>
				</p>

			</div>






		</div>
		<!-- footer -->
		<div class="footer1">
						<p>Copyright @ LYS<br>
						Fax:29-123-4576 Question calling is bad. So try to e-mail. <br>Email:jswp@americanflower.us</p>
						
		</div>
		<div class="modal fade" id="myModal" role="dialog">

			<div class="modal-dialog">

				<div class="modal-content">

					<!-- header -->

					<div class="modal-header">

						<button class="close" type="button" data-dimiss="modal"></button>

						<h4>
							<span class="glyphicon glyphicon-lock"> Tickets</span>
						</h4>

					</div>



					<!-- body -->

					<div class="modal-body">

						<form action="" role="form">

							<div class="form-group">

								<label for="psw"><span
									class="glyphicon glyphicon-shopping-cart"></span> Tickets, $20
									per person </label> <input type="number" placeholder="How many?"
									class="form-control" id="psw" />

							</div>

							<div class="form-group">

								<label for="username"><span
									class="glyphicon glyphicon-user"></span> Send To </label> <input
									type="text" class="form-control" id="username"
									placeholder="Enter email!!" />

							</div>

							<button type="submit" class="btn btn-block">
								Pay <span class="glyphicon glyphicon-ok"></span>

							</button>

						</form>

					</div>

					<!-- footer -->
					

					<div class="modal-footer">

						<button class="btn btn-danger btn-default pull-left"
							data-dismiss="modal">

							<span class="glyphicon glyphicon-remove"></span> Cancel
						</button>

						<p>
							Need <a href="#">help?</a>
						</p>

					</div>
					
				</div>



			</div>



		</div>
</body>

</html>