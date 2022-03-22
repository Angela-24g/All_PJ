<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
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

.panel {
  padding: 0 18px;
  display: none;
  background-color: white;
  overflow: hidden;
}
</style>
</head>
<body>

<button class="accordion">전화번호로 ID찾기</button>
<div class="panel">
이름<input type="text" id="name" name="name">
전화번호<input type="text" id="tel" name="tel">
<input type="hidden" id="data" value="1">
</div>
<button class="accordion">이메일로 ID찾기</button>
<div class="panel">
이름<input type="text" id="name" name="name">
이메일<input type="text" id="email" name="email">
<input type="hidden" id="data" value="2">
</div>

<input type="button" onclick="findID()" value="아이디 찾기">

<script>
	
		var acc = document.getElementsByClassName("accordion");
		var i;

		for (i = 0; i < acc.length; i++) {
		  acc[i].addEventListener("click", function() {
		   // this.classList.toggle("active");
		   this.classList.remove("active");
		    var panel = this.nextElementSibling;
		    if (panel.style.display === "block") {
		      panel.style.display = "none";
		    } else {
		      panel.style.display = "block";
		    }
		  });
		}
</script>
<script>
function findID(){
	var name=$("#name").val();
	var tel=$("#tel").val();
	var email=$("#email").val();
	var no = $("#data").val();
	console.log(no);
}
</script>

</body>
</html>