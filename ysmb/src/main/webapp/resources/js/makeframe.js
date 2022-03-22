/**
 * no만 들어가면 다른 데이터는 들어갈 수 있으니 인자는 하나만 들어가도록 함수쓴다.
 */
 function more(result){
 
	const comments = document.getElementById("comments"); 	
	const div = document.createElement("div");
	div.id = "commentsbox";
 
	const name = document.createElement("span");
	name.innerText = result.wwriter.name;
	
	const time = document.createElement("span");
    time.innerText = moment(result.wdate).format("YYYY-MM-DD HH:mm:ss");
	//날짜 포맷으로할때는 지금 taglib가 안되니 moment를 쓴 것!
	const nbsp = document.createElement("&nbsp");

	const nb = document.createElement("\n");
	
	const textarea = document.createElement("textarea");
    textarea.cols = 230; textarea.rows = 3;
    textarea.name = "ccont" ; textarea.id = "ccont"+result.no
    textarea.value = result.contents;

    const updateBtn = document.createElement("input");
    updateBtn.type = "button"; updateBtn.value = "수정"; updateBtn.style.backgroundColor = "transparent";
    updateBtn.style.border = 0; updateBtn.className="upbtn"
    updateBtn.onclick = ()=> updateComment(result.no);

    const deleteBtn = document.createElement("input");
    deleteBtn.type = "button"; deleteBtn.value = "삭제"; deleteBtn.style.backgroundColor = "transparent";
    deleteBtn.style.border = 0; deleteBtn.className="delbtn"
    deleteBtn.onclick = ()=> deleteComment(result.no); 
    
	div.append(name);	
    div.append(time);
    div.append(nbsp);
 	div.append(br);
    div.append(textarea);
    div.append(updateBtn);
    div.append(deleteBtn);		
    comments.appendChild(div);
    //위에것들 만들고 div안에 넣는 것!
	}