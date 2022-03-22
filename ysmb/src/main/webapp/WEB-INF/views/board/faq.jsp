<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 헤더  -->

<style>
div.faqWrap ul.categoryIcon li:nth-of-type(1) a:link, a:visited, active, a:focus{
      opacity: 1.0;
       cursor: pointer;
}
.panel{padding:20px;}
</style>	
<!-- 헤더끝  -->

		<div class="faqBox">
			<div class="utilMenu">
				<button class="accordion" id="accordion"><strong class="listTit">모임</strong>&nbsp;&nbsp;<span class="opValue">모임은 어떤 형식으로 이루어지나요?</span></button>
				<div class="panel">
				  <p>저희 동아리는 격 주마다 오후 5시 반에 동아리 실에서 모입니다. 저희 동아리는 사진 동아리로 직접 사진을 찍기 위한 여행이나
				  맛집 탐방 등 여러 활동을 통해서 동아리의 원래 목적을 실현합니다.</p></div>
				
				<button class="accordion"><strong class="listTit">모임</strong>&nbsp;&nbsp;<span class="opValue">공지사항은 언제 올라오나요?</span></button>
				<div class="panel">
				  <p>공지 사항은 당일 아침이나 전날 밤 9시에 올라옵니다. 참고 바랍니다.</p></div>
				
				<button class="accordion"><strong class="listTit">컨셉</strong>&nbsp;&nbsp;<span class="opValue">사진 컨셉은 어떻게 되나요?</span></button>
				<div class="panel">
				  <p>저희 동아리의 사진 컨셉은 투표로 이루어집니다. 커뮤니티 글에 직접 등록하셔서 댓글에 따라 과반수로 합이 이루어진다면 그 곳으로 정해집니다. 또한 저희 카카오톡 방이 있으니
				  거기서 문의하셔도 좋습니다.</p></div>
				  
				<button class="accordion"><strong class="listTit">준비</strong>&nbsp;&nbsp;<span class="opValue">카메라는 어떤 걸 준비해야 하나요?</span></button>
				<div class="panel">
				  <p>저희 동아리는 카메라를 따로 정하고 쓰지는 않습니다. 좋은 풍경이나 사진을 위해서면 휴대폰 카메라도 좋습니다.</p></div>
				
				<button class="accordion"><strong class="listTit">활동</strong>&nbsp;&nbsp;<span class="opValue">사진 동아리 전시회가 따로 있나요?</span></button>
				<div class="panel">
				  <p>동아리 축전이나 행사 때 저희 동아리는 1순위로 자리 배치가 되어서 그때 전시회를 엽니다. 많은 참여 바랍니다.</p></div>
		</div>

<script>
var acc = document.getElementsByClassName("accordion");
var i;

for (i = 0; i < acc.length; i++) {
  acc[i].addEventListener("click", function() {
    this.classList.toggle("active");
    var panel = this.nextElementSibling;
    if (panel.style.display === "block") {
      panel.style.display = "none";
    } else {
      panel.style.display = "block";
    }
  });
}

</script>
