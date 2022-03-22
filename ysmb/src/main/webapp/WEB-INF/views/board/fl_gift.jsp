<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항글쓰기</title>
</head>
<style>
	table{margin:auto;}
	h2{text-align:center;}
	span{font-weight:bold;}
</style>
<body>
<h2>공지사항 글쓰기</h2><br><br>
<c:choose>
<c:when test="${selection eq 'updnew' && btype eq 'nboard'}">
<h2>글쓰기</h2>
<form action="updPronb.do" method="post" encType="multipart/form-data">
<table>
	<tr>
		<td>글 제목&nbsp;<input type="text" name="title" id="title" value="${title}" ><input type="hidden" id="no" name="no" value="${no}" style="width:100%;"><br></td>
		
	</tr>
	<tr>
		<td>글 내용&nbsp;<textarea cols="100" rows="5" name="ncontent" id="ncontent">${ncontent}</textarea></td>
	</tr>
	<tr>
		<td>
			<c:forEach var="img" items="${imglist}">
				<span>File: </span>${img.url}&nbsp;<input type="hidden" id="url" name="url" value="${img.url}"><input type="button" onclick="deletesrc()" value="삭제" style="width:50px;">
				<br>
			</c:forEach>
			<ul id="attachlist"></ul>
			<button onclick="addfile(event)" type="button">첨부추가</button>
		</td>
	</tr>
	<tr>
		<td><input type="submit" value="등록하기"/></td>	
	</tr>
	</table>
</form>
<button id="list" onClick="location.href='list.do?str=nb&pageNum=1'">목록으로</button>

</c:when>
<c:when test="${btype eq 'nboard'}">
<form action="flPro.do" method="post" encType="multipart/form-data">
<table>
	<tr>
		<td>글 제목&nbsp;<input type="text" name="title" id="title" maxlength="100" style="width:60%;"><br></td>
	</tr>
	<tr>
		<td>글 내용&nbsp;<textarea cols="100" rows="5" name="content" id="content"></textarea></td>
	</tr>
	<tr>
		<td>
			<ul id="attachlist"></ul>
			<button onclick="addfile(event)" type="button">첨부추가</button>
		</td>
	</tr>
	<tr>
		<td><input type="submit" value="등록하기"/></td>	
	</tr>
	</table>
</form>
<button id="list" onClick="location.href='list.do?str=nb&pageNum=1'">목록으로</button>
</c:when>
<c:otherwise>
<h2>갤러리 글쓰기</h2>
<form action="flPro.do" method="post" encType="multipart/form-data">
<table>
	<tr>
		<td>글 제목&nbsp;<input type="text" name="title" id="title" maxlength="100" style="width:75%;" ><br></td>
	</tr>
	<tr>
		<td>글 내용&nbsp;<textarea cols="100" rows="5" name="content" id="content"></textarea></td>
	</tr>
	<tr>
		<td>
			<ul id="attachlist"></ul>
			<button onclick="addfile(event)" type="button">첨부추가</button>
		</td>
	</tr>
	<tr>
		<td><input type="submit" value="등록하기"/></td>	
	</tr>
	</table>
</form>
<button id="list" onClick="location.href='list.do?str=nb&pageNum=1'">목록으로</button>
</c:otherwise>
</c:choose>
<script type="text/javascript">
function addfile() {
	// ul 을 가져온다.
	var 첨부리스트 = document.querySelector("#attachlist");

	// ul 아래 자식요소(li)가 1개 이상은 있는데, 
	// 마지막 li의 첫번째 자식인 input.file 에 값이 없을 때 
	console.log(첨부리스트.childNodes);
 	if (첨부리스트.childNodes.length > 0
			&& 첨부리스트.childNodes[첨부리스트.childNodes.length - 1].childNodes[0].value == "") {
		return 0;
	}

	// ul 아래 들어갈 li, input.file, input.button 을 만든다.
	var li = document.createElement("li");
	var fileInput = document.createElement("input");
	fileInput.type = "file";
	var btnDelete = document.createElement("input");
	btnDelete.type = "button";
	btnDelete.value = "삭제";

	// containerLi 라는 속성을 직접 만들어 li 객체를 기억한다.
	// 과거의 li를 지목해서 가져올려면 어딘가에 저장해야 하기 때문이다.
	btnDelete.containerLi = li;

	btnDelete.addEventListener("click", function(event) {
		// 현재 클릭 이벤트가 발생한 btnDelete 의 containerLi속성에 저장한 li를 가져온다.
		var li=event.target.containerLi;
		// 저장한 li 정보의 부모인 ul 을 가져온다.
		var ul=li.parentNode;
		// ul 의 자식요소인 li 를 지운다.
		ul.removeChild(li);
	})

	// li 아래에 input.file, input.button 을 넣는다.
	li.appendChild(fileInput);
	li.appendChild(btnDelete);

	// ul 아래에 위의 li 를 넣는다.
	첨부리스트.appendChild(li);
	
	if(첨부리스트.childNodes != null){
		// 이 부분 따로 변수로 만들지 않고 name에 바로 넣으면 오류난다.
		var i = 첨부리스트.childNodes.length - 1;
		// li 가 아니라 input.file 에다가 name 넣어야 함(근데 왜 li는 name속성이 undefinded뜨고 안되지..?)
		var fileInput = 첨부리스트.childNodes[i].childNodes[0];
		fileInput.name = "attachFiles[" + i +  "]";
	}
}

function deletesrc()
{
	var url = $('#url').val();
	$.ajax({
		type: "POST",
 		url: "deleteimg.do",
 		data: JSON.stringify({'url':url}),
 		contentType:"application/json; charset=utf-8",
 		success:function(result){window.location.reload();},
 		
	});
	}

</script>

</body>
</html>