package co.kr.ysmb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.kr.ysmb.dto.ApplDTO;
import co.kr.ysmb.dto.BoardDTO;
import co.kr.ysmb.dto.CmtDTO;
import co.kr.ysmb.dto.CommentDTO;
import co.kr.ysmb.dto.ImgDTO;
import co.kr.ysmb.dto.MembDTO;
import co.kr.ysmb.dto.MemberDTO;
import co.kr.ysmb.dto.NBoardDTO;
import co.kr.ysmb.dto.NFBoardDTO;
import co.kr.ysmb.service.Fileupload;
import co.kr.ysmb.service.ScriptUtils;



@Controller
public class BoardController {
	
	@Autowired
	SqlSession sqlSession;
	
	//리스트 뽑기(10개씩)
	@RequestMapping("list.do")
	public String listForm(@RequestParam("str") String str, Model model, String pageNum, HttpSession session) {
		
		//1. 페이지가 없을 경우, 1로 설정해준다.
		if(pageNum==null)
		{
			pageNum ="1";
		}
		//2. 페이지는 몇 개씩 볼건지 설정
		int pageSize = 10;
		int currentPage=Integer.parseInt(pageNum); //현재 페이지!(페이지의 번호!)
	
		//3. db에 들어갈 시작 값 정하기
		int startNo = (currentPage-1)*pageSize+1; //currentPage라는게 현재 페이지의 번호!
		int lastNo = currentPage*pageSize; //마지막 페이지를 나타냄!

		int pageBlock = 10; //10개씩 페이지 블럭을 나타나겠다.
		
		//총 글의 개수를 구하여 역순으로 최대 글에서부터 페이지 번호의 10개 전의 글까지 나타내는 것을 정하는 곳(이는 향후에 글 번호가 된다.)
		int count=0;
		 if(str.equals("comm")) {count = sqlSession.selectOne("board.countDao");} //총 글의 개수
		 else if(str.equals("nb")) {count = sqlSession.selectOne("nboard.countDao",0);} //총 글의 개수
		 else {count = sqlSession.selectOne("nboard.countDao",1);} //총 글의 개수
		
		int number = count - (currentPage-1)*pageSize; //10개씩 빼는게 아니라, 해당 페이지만큼의 10배가 곧 현재 페이지의 10개가 된다는 것 37이면 20을 빼서 17로!
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", startNo-1); //0,10,20..
		map.put("cnt", 10); //10개씩
		map.put("document", 0);
		int pageCount = count/pageSize + (count/pageSize==0?0:1); //총 밑에 나타나는 페이지 수!
		
		int startPage = (currentPage/pageBlock)*10+1;
		int lastPage = startPage+pageBlock-1;
		//공지사항과 질문부분 나눠서 데이터 가져올 것, 갤러리 부분도 신경쓰기
		if(str.equals("comm"))
		{List<BoardDTO> List = sqlSession.selectList("board.listDao",map);
		model.addAttribute("list", List);}
		else if(str.equals("nb")) {List<NBoardDTO> nblist = sqlSession.selectList("nboard.nlistDao",map);model.addAttribute("nblist", nblist);}
		else {
		map.put("cnt", 9);
		map.put("document", 1);
		List<ApplDTO> fllist = sqlSession.selectList("nboard.ImgList",map);
		
		//만약 1이면 썸네일이니 해당 그림만 뽑아서 list로 추가해서 보내기
		model.addAttribute("fllist", fllist);
		}
		session.setAttribute("str", str);
		
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", lastPage);
		model.addAttribute("startNo", startNo);
		model.addAttribute("lastNo", lastNo);
		model.addAttribute("count", count);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageCount", pageCount);
		model.addAttribute("number", number);
		// String match = "\"[^\\uAC00-\\uD7A30-9a-zA-Z]\"";
		// str.replaceAll(match, " ");
		System.out.println(str);
		model.addAttribute("str",str);
		model.addAttribute("admin",session.getAttribute("id"));
		if(str.equals("gall"))
		{return ".main.board.fllist";}
		else {return ".main.board.list";}
	}
	//공지사항 리스트 보이기
	@RequestMapping("noticeList.do")
	public String noticeList() {
		return ".main.board.nblist";
	}
//-------------------------------------------------------------------------------------------------------------------------------------list-end-----------------------------------------
	//글쓰기 폼
	@RequestMapping("writeForm.do")
	public String writeForm(Model model, String num, String ref, String re_level, String re_step, String pageNum, HttpSession session, String subject) {
		
	
		if(num==null) { //원글쓰기
			num="0"; //html에서의 num은 전부다 문자열이다! (글 번호)
			ref="1"; //ref가 string형이어서 쌍따옴표다.  (글 그룹)
			re_step = "0";  //(글 순서)
			re_level="0";   //(글 깊이)
		}
		 //답글이면   (아래는 답글일 대나 원글 일 때나 다 넣어줘야 함)
			
			if(subject != null)
			{
				model.addAttribute("subject",subject);
			}
			model.addAttribute("pageNum",pageNum); //페이지 번호는 꼐속 보내야함
			model.addAttribute("num",new Integer(num));
			model.addAttribute("ref",new Integer(ref));
			model.addAttribute("re_step",new Integer(re_step));
			model.addAttribute("re_level",new Integer(re_level));	
			System.out.println(session.getAttribute("str"));
		//	model.addAttribute("str",session.getAttribute("str"));
		//뷰 리턴 writeForm.jsp
			return ".main.board.writeForm"; //tiles에서 형식은 이렇게 된다. tiles_def.xml 참고!!
	} //writeForm-end  
	
	//글 수정 폼
			@RequestMapping("updateForm.do")
			public String updateForm(@ModelAttribute("boardDTO") BoardDTO boardDTO, Model model, int num)
			{
				
				  boardDTO = sqlSession.selectOne("board.getBoard", num);
				  model.addAttribute("num",boardDTO.getNum());
				  model.addAttribute("writer",boardDTO.getWriter());
				  model.addAttribute("subject",boardDTO.getSubject());
				  model.addAttribute("content",boardDTO.getContent());
				
				  return ".main.board.updateForm";
			}
	
	//community 상세 보기
			@SuppressWarnings("null")
			@RequestMapping("viewForm.do")
			public String view(@ModelAttribute("boardDTO") BoardDTO boardDTO, Model model, HttpSession session,HttpServletResponse response) throws IOException
			{	//modelattribute는 model에 객체를 담아주는 역할을 한다!
				//비밀글인가를 나타내는거를 if문으로!
			if(session.getAttribute("id")==null)
			{
				 ScriptUtils scriptUtils = new ScriptUtils();
				 scriptUtils.alertAndMovePage(response, "로그인 후 이용이 가능합니다.", "loginForm.do");
				 
			}
				MemberDTO mdto = sqlSession.selectOne("member.selectOne", session.getAttribute("id"));
				System.out.println(session.getAttribute("id"));
				if(/*mdto.getName()!=null*/session.getAttribute("id")!=null )
				{	
				model.addAttribute("cno",mdto.getNo());
				model.addAttribute("cname",mdto.getName());}
				sqlSession.update("board.readcountDao", boardDTO.getNum());
				boardDTO = sqlSession.selectOne("board.getBoard", boardDTO.getNum());
				model.addAttribute("num",boardDTO.getNum());
				model.addAttribute("subject",boardDTO.getSubject());
				model.addAttribute("content",boardDTO.getContent());
				model.addAttribute("writer",boardDTO.getWriter());
				model.addAttribute("date",boardDTO.getRegdate());
				model.addAttribute("readcount",boardDTO.getReadcount());
				model.addAttribute("bdto",boardDTO);
				model.addAttribute("name",session.getAttribute("name"));
				 Map<String,Object> map = new HashMap<String,Object>();
				 map.put("start",0);
				 map.put("cnt",6);
				 map.put("board_no",boardDTO.getNum());
				 System.out.println(map.get("start"));
				 System.out.println(map.get("cnt"));
				 System.out.println(map.get("board_no"));
		         List<CommentDTO> cdto =  sqlSession.selectList("comment.listDao", map);
				//cdto로부터의 작성자 번호를 이름으로 바꿔서 보내기
				
				Map<String, Object> cmap = new HashMap<String, Object>();
				List<String> blist = new ArrayList<String>();
				
				 List<CmtDTO> cmddto = new ArrayList<CmtDTO>();
				 List<String> cmtnameList = new ArrayList<String>();
				for(int i=0;i<cdto.size();i++)
				{
					CmtDTO cmdto = new CmtDTO();
					//이름을 리스트화해서 따로 가져온다.
					int no = sqlSession.selectOne("comment.selectOne",cdto.get(i).getNo());
					MemberDTO name = sqlSession.selectOne("member.findDtobyNo",no);
					cmdto.setWwriter(new MembDTO(name, no));
					cmddto.add(cmdto);
					//int Mno = cdto.get(i).getWriter();
				//	String Mwriter = (String)sqlSession.selectOne("member.findbyNo", Mno);
					//이름을 따로 만들어서 리스트화 한다! 
			//		blist.add(Mwriter);
					String Name = cmddto.get(i).getWwriter().getName();
					cmtnameList.add(Name);
				}
				
				//그러고 폼에서는 그대로 출력한다!
				model.addAttribute("blist",blist);
				model.addAttribute("clist",cdto);
				model.addAttribute("cmdlist",cmtnameList);
				try {
					model.addAttribute("secret", session.getAttribute("secret").toString());
				}catch(Exception e) {
					model.addAttribute("secret", "false");
				}
				return ".main.board.viewForm";
			} 
	
	//공지사항 상세보기	
			@RequestMapping("viewNbForm.do")
			public String nbViewForm(@RequestParam("no") int no, Model model,HttpSession session) {
			 System.out.println(no);
			 List<NBoardDTO> list = sqlSession.selectList("nboard.selectData",no);
			 List<ImgDTO> imglist = sqlSession.selectList("imgs.selectImg", no);
			 NBoardDTO nbdto = sqlSession.selectOne("nboard.selectData", no);
			 nbdto.setRcount(nbdto.getRcount());
			 nbdto.setNo(no);
			 sqlSession.update("nboard.upcount",nbdto);
			 model.addAttribute("list",list);
			 model.addAttribute("imglist",imglist);
			 System.out.println(session.getAttribute("id"));
			 model.addAttribute("id",session.getAttribute("id"));
			 
				return ".main.board.nbviewForm";
			}
			//GET:공지사항 & 갤러리 글 
			@RequestMapping(value="udpnb.do",method=RequestMethod.GET)
			public String nbwriteForm(@RequestParam("no") int no, Model model, HttpSession session) {
				/*if(session.getAttribute("str").equals("nb"))
				{ */
				System.out.println(session.getAttribute("str"));
				 NBoardDTO nbdto= sqlSession.selectOne("nboard.selectData",no);
				 List<ImgDTO> imglist = sqlSession.selectList("imgs.selectImg", no);
				 model.addAttribute("selection","updnew");
				 model.addAttribute("no",nbdto.getNo());
				 model.addAttribute("title",nbdto.getTitle());
				 model.addAttribute("ncontent",nbdto.getNcontent());
				 model.addAttribute("imglist",imglist);
				
					/* } */
			
				 return ".main.board.fl_gift";
				}
			
			
			/*	public String updnb(@RequestParam("no") int no){
				//해당 번호의 데이터 가져와야 함
				sqlSession.
				return ".main.board.fl_gift";
			}*/
			//POST:공지사항 글 수정(수정한 후의 동작 함수)
			@RequestMapping("updPronb.do")
			@ResponseBody
			public String updnb(@ModelAttribute NFBoardDTO nboard, @RequestParam("no") int no, Model model, @ModelAttribute("nboardDTO") NBoardDTO nboardDTO,@ModelAttribute("imgDTO") ImgDTO imgDTO, String title, String ncontent){
				//글 수정
				nboardDTO.setTitle(title);
				nboardDTO.setNcontent(ncontent);
				sqlSession.update("nboard.nupdateDao",nboardDTO);
				
					//이미지 수정

				List<MultipartFile> attachFiles = nboard.getAttachFiles();
				Fileupload fileupload = new Fileupload();
				if(attachFiles!=null)
				{	for(int i = 0 ; i < attachFiles.size() ; i++) {
					Map<String, String> save = fileupload.Save(attachFiles.get(i));
					imgDTO.setBno(no);
					imgDTO.setUrl(save.get("PATH"));
					imgDTO.setDocument(0);
				
					System.out.println(imgDTO.getBno());
					sqlSession.insert("imgs.saveImg",imgDTO);	
				}}
				model.addAttribute("str","nb");
				model.addAttribute("pageNum",1);
				return "redirect:list.do";
			}				
			//공지사항 글 삭제
			@RequestMapping("delnb.do")
			public String deletenb(@RequestParam("no") int no, Model model) {
				System.out.println(no);
				sqlSession.delete("nboard.ndeleteDao",no);
				sqlSession.delete("imgs.delImgbyNo",no);
				model.addAttribute("str","nb");
				model.addAttribute("pageNum",1);
				return "redirect:list.do";
			}
//--------------------------------------------------------------------------------------------------------------------------------view & form-end--------------------------------------------
		
	//글쓰기(원글과 답글)

		@RequestMapping(value="writePro.do" , method=RequestMethod.POST)
		public String writePro(Model model, @ModelAttribute("boardDTO") BoardDTO boardDTO, HttpServletRequest request, HttpSession session) {
			int maxNum = 0; //최대 글 번호 넣을 변수 (글 그룹에 쓰려고 넣어둔 거다!)

			System.out.println("prowriter= "+boardDTO.getWriter());
			
			if(sqlSession.selectOne("board.numMax")!=null) //글이 있으면! = 글번호가 있다.
			{
				maxNum = sqlSession.selectOne("board.numMax"); //최대 글번호(board.xml 참조하기!)를 numMax에 할당(기존의 최대 글 번호를 가져온 것)
			}
			if(maxNum != 0) {//글이 존재하면
				maxNum = maxNum+1; //ref 글을 그룹에 넣으려고
			}
			else { 
				//처음 글이면,
				maxNum=1; //ref(글 그룹)에 1을 넣는다.
			}
			
			//ip얻어오기(악플 잡아내기 용!)
			String ip = request.getRemoteAddr(); //실제 ip를 얻는 것임
			boardDTO.setIp(ip);
			
			//원글 답글 처리
			if(boardDTO.getNum()!=0) //답글이면
			{
				//답글 끼워넣기 위치 확보!
				sqlSession.update("board.reStep", boardDTO);
				
				boardDTO.setRe_step(boardDTO.getRe_step()+1); //글순서+1
				boardDTO.setRe_level(boardDTO.getRe_level()+1); //글깊이+1
				
			}else {//원글이면
					
				boardDTO.setRef(new Integer(maxNum)); //글 그룹
				boardDTO.setRe_step(new Integer(0)); //원 글이니까 글 순서가 0이 된다.
				boardDTO.setRe_level(new Integer(0)); //레벨도 0이된다.
				}
			if((String) session.getAttribute("name")!=null)
			{
				String name = (String) session.getAttribute("name");
				boardDTO.setWriter(name);
			}
			boardDTO.setWriter(boardDTO.getWriter());
			
			if(boardDTO.getPassword()!= null)
			{
				boardDTO.setPassword(boardDTO.getPassword());
				boardDTO.setPstate("t");
				
			}
			else {
				boardDTO.setPassword(0); //integer 로 바꾸니 해결됨(타입을!) 
				boardDTO.setPstate("f"); 
			}
			sqlSession.insert("board.insertDao", boardDTO);		
			model.addAttribute("str",session.getAttribute("str"));
			model.addAttribute("pageNum",1);
		return "redirect:/list.do"; //뷰 리턴(컨트롤러에서 요청명을 호출할거라서!)
		
		}
		
		//글수정(처리)
		@RequestMapping("updatePro.do")
		public String updatePro(HttpSession session, BoardDTO boardDTO, int num, Model model) {
			boardDTO.setNum(num);
			sqlSession.update("board.updateDao",boardDTO);
			model.addAttribute("str",session.getAttribute("str"));
			model.addAttribute("pageNum",1);
			return "redirect:/list.do";
		}
		
		@RequestMapping(value="nbwritePro.do", method=RequestMethod.POST)
		@ResponseBody
		public String nbwritePro(@RequestParam("title") String title, @RequestParam("ncontent") String ncontent, HttpSession session) {			
			NBoardDTO nbdto = new NBoardDTO();
			String name = (String)session.getAttribute("name");
			nbdto.setNwriter(name);
			nbdto.setTitle(title);
			nbdto.setNcontent(ncontent);
			nbdto.setDocument(0);
			sqlSession.insert("nboard.ninsertDao",nbdto);
			return "redirect:list.do";
		}
		//답글 달기(db처리)
		
		//댓글 달기(새로운 테이블에 업데이트)
		
		
		/*public String writePro(@ModelAttribute("boardDTO")BoardDTO boardDTO, HttpServletRequest request) {
			
			int maxNum = 0; //최대 글 번호 넣을 변수 (글 그룹에 쓰려고 넣어둔 거다!)
			
			if(sqlSession.selectOne("board.numMax")!=null) //글이 있으면! = 글번호가 있다.
			{
				maxNum = sqlSession.selectOne("board.numMax"); //최대 글번호(board.xml 참조하기!)를 numMax에 할당(기존의 최대 글 번호를 가져온 것)
			}
			if(maxNum != 0) {//글이 존재하면
				maxNum = maxNum+1; //ref 글을 그룹에 넣으려고
			}
			else { 
				//처음 글이면,
				maxNum=1; //ref(글 그룹)에 1을 넣는다.
			}
			
			//ip얻어오기(악플 잡아내기 용!)
			String ip = request.getRemoteAddr(); //실제 ip를 얻는 것임
			boardDTO.setIp(ip);
			
			//원글 답글 처리
			if(boardDTO.getNum()!=0) //답글이면
			{
				//답글 끼워넣기 위치 확보!
				sqlSession.update("board.reStep", boardDTO);
				
				boardDTO.setRe_step(boardDTO.getRe_step()+1); //글순서+1
				boardDTO.setRe_level(boardDTO.getRe_level()+1); //글깊이+1
				
			}else {//원글이면
					
				boardDTO.setRef(new Integer(maxNum)); //글 그룹
				boardDTO.setRe_step(new Integer(0)); //원 글이니까 글 순서가 0이 된다.
				boardDTO.setRe_level(new Integer(0)); //레벨도 0이된다.
				}
			
			
			sqlSession.insert("board.insertDao", boardDTO);
					
			return "redirect:/list.do"; //뷰 리턴(컨트롤러에서 요청명을 호출할거라서!)
		
		}*/
		//ip에 관한 데이터 넣을 것
/*		String ip = request.getRemoteAddr();
		boardDTO.setIp(ip);
		
		//글 번호를 업데이트하기
		int numMax=0;
		int refno=0;
		if(sqlSession.selectOne("board.numMax") != null) //글 자체가 없는지에 대한 오브젝트 값 확인
		{
			numMax = sqlSession.selectOne("board.numMax");
		}
		//글이 첫 글이면 1을 넣어주고(numMax는 0(글이 없다면.))
		if(numMax==0)
		{ refno=1; }
		else//글이 하나라도 있다는 뜻
		{ refno = numMax+1; }
		boardDTO.setRef(refno);
			if(boardDTO.getNum()!=0) //답글이면
		{
			//답글 끼워넣기 위치 확보!
			sqlSession.update("board.reStep", boardDTO);
			
			boardDTO.setRe_step(boardDTO.getRe_step()+1); //글순서+1
			boardDTO.setRe_level(boardDTO.getRe_level()+1); //글깊이+1
			
		}else {//원글이면
				
			boardDTO.setRef(new Integer(refno)); //글 그룹
			boardDTO.setRe_step(new Integer(0)); //원 글이니까 글 순서가 0이 된다.
			boardDTO.setRe_level(new Integer(0)); //레벨도 0이된다.
			}
		
		
		sqlSession.insert("board.insertDao", boardDTO);
		//ref re_level, re_step 답글 끼워넣기(그러면, 해당 글 그룹에 대한 답글을 넣을 것)
		//step은 답글을 어디에 먼저 종속시킬지에 대한 하위 단계다.(답글 1 , 답글2, 답글 1의 1번 답글, 답글 1의 2번 답글 등), level은 글 깊이인데 쓰여진 순서?를 나타낸다.
	/*	 if(boardDTO.getNum() !=0 ) {//답글이면
             //답글 위치 확보
			 sqlSession.update("board.reStep",boardDTO);
             boardDTO.setRe_step(boardDTO.getRe_step()+1);//글순서+1
             boardDTO.setRe_level(boardDTO.getRe_level()+1);//글 깊이+1

       }else {//원글이면

             boardDTO.setRef(refno);//글그룹
             boardDTO.setRe_step(new Integer(0));//글순서
             boardDTO.setRe_level(new Integer(0));//글 깊이
       }
		
		sqlSession.insert("board.insertDao", boardDTO);
		
		return "redirect:/list.do";
	}*/
//------------------------------------------------------------comment----------------------------------------------------------------------------------------------------------
	//content 등록하기(등록만하면되는거라서 따로 이동은 필요 없다.)
	@RequestMapping(value="comment.do", method=RequestMethod.POST)
	@ResponseBody
	public int addcomment(@RequestBody Map<String,Object> map )
	{	
	//resultmap으로 데이터를 가져와서 넣어야 함(저장)
	CommentDTO cdto = new CommentDTO();
	BoardDTO bdto = new BoardDTO();
	MemberDTO mdto = new MemberDTO();
	
	//int mno = sqlSession.selectOne("member.findbyName",map.get("writer"));
	bdto.setNum(Integer.parseInt((String) map.get("board_no")));
	mdto.setNo((Integer) sqlSession.selectOne("member.findbyName",map.get("writer")));
	cdto.setContents((String) map.get("contents"));
	cdto.setWriter(mdto);
	cdto.setBoard_no(bdto);
	sqlSession.insert("comment.insertDao",cdto);

	return cdto.getNo();
	}	
	//댓글 삭제
	@RequestMapping(value="commentDelete.do",method=RequestMethod.POST)
	@ResponseBody
	public void delcomment(@RequestParam("no") String no) {
		sqlSession.delete("comment.deleteDao",Integer.parseInt(no));	
	}
	//댓글 수정
	@RequestMapping(value="commentsUpdate.do",method=RequestMethod.POST)
	@ResponseBody
	public void updcomment(@RequestParam("no") String no,@RequestParam("contents") String contents) {
		CommentDTO cdto = new CommentDTO();
		cdto.setContents(contents);
		cdto.setNo(Integer.parseInt(no));
		sqlSession.update("comment.updateDao",cdto);
	}
//--------------------------------------------------------------------------------------------------------------------------------------------------------write-end-------------------------	
	//비밀번호 설정할건지 post 체크 답 주기
/*	@RequestMapping("chkpwd.do")
	public String chkpwd(@RequestParam("chk") boolean chk, Model model) {
		System.out.println("gogo");
		System.out.println(chk);
		model.addAttribute("chpw",chk);
	return ".main.board.writeForm";
	
	}
*/	
		
	//비밀글 체크 폼
	@RequestMapping("chkform.do")
	public String chkform(Model model,@RequestParam("num") int num) {
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setNum(num);
		model.addAttribute("num",num);
		return ".main.board.chk_title";
	}
	//비밀글 체크
	@RequestMapping(value="chktitle.do", method=RequestMethod.POST)
	public String chktitle(Model model, @RequestParam("password") String password,@RequestParam("num") String num, HttpServletRequest request, HttpSession session) {
	//	HttpServletResponse response
	//글의 번호를 가져와야 함! 그래서 해당 번호로 비번을 찾고 둘의 값을 비교하여 맞으면 해당 글로 이동
		System.out.println("pwd "+password);
		System.out.println("num "+num);
		
		int result=0;
		BoardDTO boardDTO = new BoardDTO();
		boardDTO = sqlSession.selectOne("board.getBoard",Integer.parseInt(num));
		int pwd = Integer.parseInt(password);
		int pw = boardDTO.getPassword();
		//비번 비교
		if(pwd != pw)
		{
			result = -1;
			model.addAttribute("num",num);
			return "redirect:/chkform.do";
		}
		else { 
			result=1;
			session.setAttribute("secret", true);
			model.addAttribute("num",num);
			return "redirect:/viewForm.do";
		}
		
	}
	//비밀글 관련 로그인 내용 지우기
	@RequestMapping(value="delscrt.do", method=RequestMethod.DELETE)
	public void deleteSecret(HttpSession session) {
		session.removeAttribute("secret"); //비밀글에 로그인했따는 뜻을 가진 secret을 지우겠다!
	}
	//글 삭제
	@RequestMapping("delete.do")
	public String delete(int num) {
		sqlSession.delete("board.deleteDao",num);
		return "redirect:list.do";
	}
//----------------------------------------------------------------------------------------file---------
	//파일 저장하기
	@RequestMapping(value="file.do", method=RequestMethod.POST)
	public void file(@RequestParam("fileup") MultipartFile file) {	
		Fileupload fileupload = new Fileupload();
		Map<String, String> save = fileupload.Save(file);
		String imgurl = save.get("PATH"); //이걸 blob대신에 db에 저장하기(db에는 varchar처럼! string형태로 저장이 되는 것)
		//jsp에 저장할때는 미리 localhost:8081을 먼저 붙여준 다음 controller로 넘겨서 저장시킬 것!
	}
	//그림이 포함된 글쓰기 폼(공지사항, 갤러리 글)
	@RequestMapping("flgift.do")
	public String flw(HttpSession session,HttpServletResponse response) throws Exception {
		if(session.getAttribute("id")==null)
		{
			 ScriptUtils scriptUtils = new ScriptUtils();
			 scriptUtils.alertAndMovePage(response, "로그인 후 이용이 가능합니다.", "loginForm.do");
			 
		}
		if(session.getAttribute("str").equals("nb"))
		{session.setAttribute("btype", "nboard");}
		else {session.setAttribute("btype", "gallery");}
		return ".main.board.fl_gift";
	}
	//그림 저장하기 
	@RequestMapping(value="flPro.do",method=RequestMethod.POST)
	public String flPro(@ModelAttribute NFBoardDTO nboard, HttpSession session, Model model) {
		NBoardDTO nBoardDTO = new NBoardDTO(nboard);
		if(session.getAttribute("btype")=="nboard")
		{nBoardDTO.setDocument(0);}
		else {nBoardDTO.setDocument(1);}
		String name = (String)session.getAttribute("name");
		nBoardDTO.setNwriter(name);
		sqlSession.insert("nboard.ninsertDao",nBoardDTO);
		
		List<MultipartFile> attachFiles = nboard.getAttachFiles();
		Fileupload fileupload = new Fileupload();
		ImgDTO images = new ImgDTO();
		if(attachFiles != null)
		{	 
			for(int i = 0 ; i < attachFiles.size() ; i++) {
			Map<String, String> save = fileupload.Save(attachFiles.get(i));
			images.setBno(nBoardDTO.getNo());
			images.setUrl(save.get("PATH"));
			images.setThumb(null);
			if(session.getAttribute("btype")=="nboard")
			{images.setDocument(0);}
			else {images.setDocument(1);}
			sqlSession.insert("imgs.saveImg",images);}
			
			//번호를 넣어서 찾고 첫번째꺼 가져와야 함 	
			List<ImgDTO> slist = sqlSession.selectList("imgs.selectImg",images.getBno());
			
			int cno = sqlSession.selectOne("imgs.selectCount",images.getBno());
			int totalcount = sqlSession.selectOne("imgs.selectAllCount");
			int num=totalcount-cno+1;
			
			images.setNo(num);
			images.setThumb(slist.get(0).getUrl());
			System.out.println("현재 글개수"+cno);
			System.out.println("썸네일을 저장할 번호"+num);
			sqlSession.update("imgs.updateImg",images);
		}
		
		//썸네일을 저장할 때, 제일 첫 사진을 썸네일로 저장해야 함
		//데이터를 쌓은 다음 나중에 첫번째꺼만 골라내서 저장하기
		model.addAttribute("str",session.getAttribute("str"));
		model.addAttribute("pageNum",1);
		return "redirect:/list.do";
	}
	//그림 리스트 나타내기
	@RequestMapping("fllist.do")
	public String fllist(Model model) {
		List<ApplDTO> selectList = sqlSession.selectList("nboard.findImgList",1);
		
		model.addAttribute("fllist",selectList);
		return ".main.board.fllist";
	}
	//그림 삭제
	@RequestMapping(value="deleteimg.do",method=RequestMethod.POST)
	public @ResponseBody void deleteImg(@RequestBody Map<String, String> url) {
		System.out.println(url);
	    String urladdress = url.get("url");
		sqlSession.delete("imgs.deleteImg",urladdress);
		
	}
	//상세 그림 페이지 들어가기(공지사항/ community 따로 구분해서 들)
	@RequestMapping("viewgallery.do")
	public String gallery(@RequestParam("no") int no, Model model, HttpSession session)
	{
		ApplDTO apdto = new ApplDTO();
		apdto.setNo(no);
		apdto.setDocument(1);
		List<ApplDTO> selectList = sqlSession.selectList("nboard.findImgListCondition",apdto);
		List<ApplDTO> imgList = sqlSession.selectList("nboard.getImgList",no);
		NBoardDTO ndto = sqlSession.selectOne("nboard.infoDao",no);
	
		model.addAttribute("ndto",ndto);
		model.addAttribute("imgList",imgList);

		return ".main.board.galleryForm";
	}
	//FAQ
	@RequestMapping("faq.do")
	public String FAQ() {
		return ".main.board.faq";
	}
	
}// class-end
	
	

