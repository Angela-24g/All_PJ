<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>viewForm.jsp</title>
<style>
	#delete, #edit, #list{float:right; margin-right:20px; padding:5px; padding-left:10px; padding-right:10px;}
	
</style>
<script>
var pageCount=6;
var startNum = 0;
function addcomment(){
	//로그인하지않아 writer_no 가 없을 수도 있다. 그때는 댓글등록을 막는다.
	<c:choose>
		<c:when test="${empty writer}">
			alert("로그인 후 이용가능합니다.");
			location.href="loginForm.do";
		</c:when>
	<c:otherwise> 
		
		var comment = {"contents":$("#contents").val(),"writer":$('#writer').val(),"board_no":$('#board_no').val()};
		console.log(comment);
		$.ajax({
			url:"comment.do",
			type:"POST",
			data:JSON.stringify(comment),
			contentType:"application/json; charset=utf-8",
		//	dataType:"String", //이거는 서버에서 읽는 방법에서 읽을게 없어서 에러가 난 것이었따.
			success:function(result){
				const comments = document.getElementById("comments"); //인자값이어서 ""로 넣기!
				const div = document.createElement("div");
				div.id = "commentsbox";	//div의 id를 가져온 것(앞으로 여기로 넣을 거를 html형식으로 넣을건지 다 가져와서 넣을건지)	- 이벤트를 위한 틀
			
				const name = document.createElement("span");
				name.innerText = result.wwriter.name;
				
				const textarea = document.createElement("textarea");
		        textarea.cols = 230; textarea.rows = 3;
		        textarea.name = "ccont" ; textarea.id = "ccont"+result
		        textarea.value = comment.contents;

		        const updateBtn = document.createElement("input");
		        updateBtn.type = "button"; updateBtn.value = "수정"; updateBtn.style.backgroundColor = "transparent";
		        updateBtn.style.border = 0;
		        updateBtn.onclick = ()=> updateComment(result);

		        const deleteBtn = document.createElement("input");
		        deleteBtn.type = "button"; deleteBtn.value = "삭제"; deleteBtn.style.backgroundColor = "transparent";
		        deleteBtn.style.border = 0;
		        deleteBtn.onclick = ()=> deleteComment(result); //onclick이라는 함수 안에 deleteComment가 들어간 것!

		        div.append(name);
		        div.append(textarea);
		        div.append(updateBtn);
		        div.append(deleteBtn);		
				comments.prepend(div);	//어떤 값을 넣어줄 건지를 나타내는것!		
				
			}, //값을 넣어준 것!
			error:function(result){
			   console.log("error= "+result);
			}
		});
		</c:otherwise>
	</c:choose>
	window.location.reload();
	}
	
function deleteComment(no)	
{
//	var no = $('#ccno').val();
	console.log(no);
	$.ajax({
		url:"commentDelete.do",
		type:"POST",
		data:{'no':no}
		
		});
		window.location.reload();
}	
function updateComment(no)	
{
	var contents = $('#ccont'+no).val(); //해당 id의 해당하는 textarea value가 들어옴!(id값에 해당하는 데이터를 가져오려는 예제에 쓸 수 있음)
	$.ajax({
		url:"commentsUpdate.do",
		type:"POST",
		data:{'no':no, 'contents':contents}
		,error:console.log
		});
	window.location.reload();
}	
</script>
</head>
<body>
<input type="hidden" id="num" value="${num}"/>
<c:choose>

	<c:when test="${(secret == 'true' && bdto.pstate == 't' ) || bdto.pstate == 'f'}">
		<h1>${subject}</h1><br>
		<table>
		<tr>
			<td>
				${writer}&nbsp;&nbsp;<fmt:formatDate value="${date}" pattern="yyyy-MM-dd"/>&nbsp;&nbsp;조회&nbsp;${readcount}
				<input type="hidden" id="board_no" value="${num}">
				<c:choose>
					<c:when test="${name eq writer}">
						<button id="delete" onClick="location.href='delete.do?num=${num}'">삭제</button>&nbsp;&nbsp;
						<button id="edit" onClick="location.href='updateForm.do?num=${num}'">수정</button>&nbsp;&nbsp;
					</c:when>
				</c:choose>
				
				<input type="button" value="답글쓰기" onClick="location.href='writeForm.do?num=${num}&pageNum=${pageNum}&ref=${bdto.ref}&re_step=${bdto.re_step}&re_level=${bdto.re_level}&subject=${subject}'">&nbsp;&nbsp;
				<button id="list" onClick="location.href='list.do?str=comm&pageNum=1'">목록으로</button>			
			</td>
		</tr>
		<tr>
			<td><textarea rows="15" cols="230">${content}</textarea></td>
		</tr>
		</table>
		<br><br>
		<!--댓글 쓰기 부분  -->
		<input type="hidden" name="writer" id="writer" value="${cname}">${cname}
		<br>
		<textarea cols=230 rows=5 id="contents"></textarea>
		<button type="button" onclick="addcomment()" style="background-color:transparent;">댓글등록</button><br>
		<br>
		<!-- 댓글들 출력 부분 -->
		<div id="comments">
			<c:forEach var="cdto" items="${clist}" end="4" varStatus="status">
			
			 <div id="commentsbox">
			 
				<input type="hidden" name="ccno" id="ccno" value="${cdto.no}"> 
				<c:out value="${blist[status.index]}"/>
				<c:out value="${cmdlist[status.index]}"/>
				<fmt:formatDate value="${cdto.wdate}" pattern="yyyy-MM-dd hh:mm"/><br>	
				<textarea cols="230" rows="3" name="ccont" id="ccont${cdto.no}">${cdto.contents}</textarea>
			<input type="button" onclick="updateComment('${cdto.no}')" value="수정" style="background-color:transparent;border:0;">&nbsp;|
			<input type="button" onclick="deleteComment('${cdto.no}')" value="삭제" style="background-color:transparent;border:0;"> 
				<br><br>	
			 </div> <!-- 여기의 데이터를 js에서 어떻게 묶어 보내줄 지를 알아야 함 -->
			
			</c:forEach>
			
		</div>
	
		<div id="morebtn_wrap">
			<c:if test="${fn:length(clist) > 5}">
				<button onclick="moreHandler()" id="morebtn">더보기</button>
			</c:if>
			
		</div>
		
	</c:when>
	
	<c:otherwise>
		<script>
			var num = $('#num').val();
			window.location.replace("chkform.do?num="+num);
		</script>
	</c:otherwise>
</c:choose>
<script type="text/javascript" src="resources/js/makeframe.js"></script>
<script>
		
		$(window).on("beforeunload", function() {
		    $.ajax({
		    	type:"delete",
		    	url:"delscrt.do"
		    });
		   });
		
		function moreHandler(){
			startNum += 5;
			var a = "${num}";
			$.ajax({
				url:"test?start="+startNum+'&cnt='+pageCount + "&board_no="+a,
				type:"get",
				success:(result)=>{
					console.log(result);
					var html = "";
					if(result.length>5)
						{
							$('#morebtn').css("display","block");		
							
						}
					else{
						$('button').remove("#morebtn");
					
					}
					
					result.forEach((item, index)=>{
						more(item);
						
				//		if( index !== (result.length-1) ){ //0이 아니면 출력하라!
				//			html += 
				//			"<br><div>"+item.wwriter.name+"&nbsp;<br><textarea cols='230' rows='3'>"+item.contents+"</textarea></div><br>";
					//		html += "<input type='button' onclick='' value='수정'>";
							
					//	}	
					});
				//	$("#comments").append(html);
				
				}, 
				error:(result)=>console.log(result),
				
			});
		}
		</script>

</body>
</html>