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
	
	//����Ʈ �̱�(10����)
	@RequestMapping("list.do")
	public String listForm(@RequestParam("str") String str, Model model, String pageNum, HttpSession session) {
		
		//1. �������� ���� ���, 1�� �������ش�.
		if(pageNum==null)
		{
			pageNum ="1";
		}
		//2. �������� �� ���� ������ ����
		int pageSize = 10;
		int currentPage=Integer.parseInt(pageNum); //���� ������!(�������� ��ȣ!)
	
		//3. db�� �� ���� �� ���ϱ�
		int startNo = (currentPage-1)*pageSize+1; //currentPage��°� ���� �������� ��ȣ!
		int lastNo = currentPage*pageSize; //������ �������� ��Ÿ��!

		int pageBlock = 10; //10���� ������ ���� ��Ÿ���ڴ�.
		
		//�� ���� ������ ���Ͽ� �������� �ִ� �ۿ������� ������ ��ȣ�� 10�� ���� �۱��� ��Ÿ���� ���� ���ϴ� ��(�̴� ���Ŀ� �� ��ȣ�� �ȴ�.)
		int count=0;
		 if(str.equals("comm")) {count = sqlSession.selectOne("board.countDao");} //�� ���� ����
		 else if(str.equals("nb")) {count = sqlSession.selectOne("nboard.countDao",0);} //�� ���� ����
		 else {count = sqlSession.selectOne("nboard.countDao",1);} //�� ���� ����
		
		int number = count - (currentPage-1)*pageSize; //10���� ���°� �ƴ϶�, �ش� ��������ŭ�� 10�谡 �� ���� �������� 10���� �ȴٴ� �� 37�̸� 20�� ���� 17��!
		
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("start", startNo-1); //0,10,20..
		map.put("cnt", 10); //10����
		map.put("document", 0);
		int pageCount = count/pageSize + (count/pageSize==0?0:1); //�� �ؿ� ��Ÿ���� ������ ��!
		
		int startPage = (currentPage/pageBlock)*10+1;
		int lastPage = startPage+pageBlock-1;
		//�������װ� �����κ� ������ ������ ������ ��, ������ �κе� �Ű澲��
		if(str.equals("comm"))
		{List<BoardDTO> List = sqlSession.selectList("board.listDao",map);
		model.addAttribute("list", List);}
		else if(str.equals("nb")) {List<NBoardDTO> nblist = sqlSession.selectList("nboard.nlistDao",map);model.addAttribute("nblist", nblist);}
		else {
		map.put("cnt", 9);
		map.put("document", 1);
		List<ApplDTO> fllist = sqlSession.selectList("nboard.ImgList",map);
		
		//���� 1�̸� ������̴� �ش� �׸��� �̾Ƽ� list�� �߰��ؼ� ������
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
	//�������� ����Ʈ ���̱�
	@RequestMapping("noticeList.do")
	public String noticeList() {
		return ".main.board.nblist";
	}
//-------------------------------------------------------------------------------------------------------------------------------------list-end-----------------------------------------
	//�۾��� ��
	@RequestMapping("writeForm.do")
	public String writeForm(Model model, String num, String ref, String re_level, String re_step, String pageNum, HttpSession session, String subject) {
		
	
		if(num==null) { //���۾���
			num="0"; //html������ num�� ���δ� ���ڿ��̴�! (�� ��ȣ)
			ref="1"; //ref�� string���̾ �ֵ���ǥ��.  (�� �׷�)
			re_step = "0";  //(�� ����)
			re_level="0";   //(�� ����)
		}
		 //����̸�   (�Ʒ��� ����� �볪 ���� �� ���� �� �־���� ��)
			
			if(subject != null)
			{
				model.addAttribute("subject",subject);
			}
			model.addAttribute("pageNum",pageNum); //������ ��ȣ�� ���� ��������
			model.addAttribute("num",new Integer(num));
			model.addAttribute("ref",new Integer(ref));
			model.addAttribute("re_step",new Integer(re_step));
			model.addAttribute("re_level",new Integer(re_level));	
			System.out.println(session.getAttribute("str"));
		//	model.addAttribute("str",session.getAttribute("str"));
		//�� ���� writeForm.jsp
			return ".main.board.writeForm"; //tiles���� ������ �̷��� �ȴ�. tiles_def.xml ����!!
	} //writeForm-end  
	
	//�� ���� ��
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
	
	//community �� ����
			@SuppressWarnings("null")
			@RequestMapping("viewForm.do")
			public String view(@ModelAttribute("boardDTO") BoardDTO boardDTO, Model model, HttpSession session,HttpServletResponse response) throws IOException
			{	//modelattribute�� model�� ��ü�� ����ִ� ������ �Ѵ�!
				//��б��ΰ��� ��Ÿ���°Ÿ� if������!
			if(session.getAttribute("id")==null)
			{
				 ScriptUtils scriptUtils = new ScriptUtils();
				 scriptUtils.alertAndMovePage(response, "�α��� �� �̿��� �����մϴ�.", "loginForm.do");
				 
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
				//cdto�κ����� �ۼ��� ��ȣ�� �̸����� �ٲ㼭 ������
				
				Map<String, Object> cmap = new HashMap<String, Object>();
				List<String> blist = new ArrayList<String>();
				
				 List<CmtDTO> cmddto = new ArrayList<CmtDTO>();
				 List<String> cmtnameList = new ArrayList<String>();
				for(int i=0;i<cdto.size();i++)
				{
					CmtDTO cmdto = new CmtDTO();
					//�̸��� ����Ʈȭ�ؼ� ���� �����´�.
					int no = sqlSession.selectOne("comment.selectOne",cdto.get(i).getNo());
					MemberDTO name = sqlSession.selectOne("member.findDtobyNo",no);
					cmdto.setWwriter(new MembDTO(name, no));
					cmddto.add(cmdto);
					//int Mno = cdto.get(i).getWriter();
				//	String Mwriter = (String)sqlSession.selectOne("member.findbyNo", Mno);
					//�̸��� ���� ���� ����Ʈȭ �Ѵ�! 
			//		blist.add(Mwriter);
					String Name = cmddto.get(i).getWwriter().getName();
					cmtnameList.add(Name);
				}
				
				//�׷��� �������� �״�� ����Ѵ�!
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
	
	//�������� �󼼺���	
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
			//GET:�������� & ������ �� 
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
				//�ش� ��ȣ�� ������ �����;� ��
				sqlSession.
				return ".main.board.fl_gift";
			}*/
			//POST:�������� �� ����(������ ���� ���� �Լ�)
			@RequestMapping("updPronb.do")
			@ResponseBody
			public String updnb(@ModelAttribute NFBoardDTO nboard, @RequestParam("no") int no, Model model, @ModelAttribute("nboardDTO") NBoardDTO nboardDTO,@ModelAttribute("imgDTO") ImgDTO imgDTO, String title, String ncontent){
				//�� ����
				nboardDTO.setTitle(title);
				nboardDTO.setNcontent(ncontent);
				sqlSession.update("nboard.nupdateDao",nboardDTO);
				
					//�̹��� ����

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
			//�������� �� ����
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
		
	//�۾���(���۰� ���)

		@RequestMapping(value="writePro.do" , method=RequestMethod.POST)
		public String writePro(Model model, @ModelAttribute("boardDTO") BoardDTO boardDTO, HttpServletRequest request, HttpSession session) {
			int maxNum = 0; //�ִ� �� ��ȣ ���� ���� (�� �׷쿡 ������ �־�� �Ŵ�!)

			System.out.println("prowriter= "+boardDTO.getWriter());
			
			if(sqlSession.selectOne("board.numMax")!=null) //���� ������! = �۹�ȣ�� �ִ�.
			{
				maxNum = sqlSession.selectOne("board.numMax"); //�ִ� �۹�ȣ(board.xml �����ϱ�!)�� numMax�� �Ҵ�(������ �ִ� �� ��ȣ�� ������ ��)
			}
			if(maxNum != 0) {//���� �����ϸ�
				maxNum = maxNum+1; //ref ���� �׷쿡 ��������
			}
			else { 
				//ó�� ���̸�,
				maxNum=1; //ref(�� �׷�)�� 1�� �ִ´�.
			}
			
			//ip������(���� ��Ƴ��� ��!)
			String ip = request.getRemoteAddr(); //���� ip�� ��� ����
			boardDTO.setIp(ip);
			
			//���� ��� ó��
			if(boardDTO.getNum()!=0) //����̸�
			{
				//��� �����ֱ� ��ġ Ȯ��!
				sqlSession.update("board.reStep", boardDTO);
				
				boardDTO.setRe_step(boardDTO.getRe_step()+1); //�ۼ���+1
				boardDTO.setRe_level(boardDTO.getRe_level()+1); //�۱���+1
				
			}else {//�����̸�
					
				boardDTO.setRef(new Integer(maxNum)); //�� �׷�
				boardDTO.setRe_step(new Integer(0)); //�� ���̴ϱ� �� ������ 0�� �ȴ�.
				boardDTO.setRe_level(new Integer(0)); //������ 0�̵ȴ�.
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
				boardDTO.setPassword(0); //integer �� �ٲٴ� �ذ��(Ÿ����!) 
				boardDTO.setPstate("f"); 
			}
			sqlSession.insert("board.insertDao", boardDTO);		
			model.addAttribute("str",session.getAttribute("str"));
			model.addAttribute("pageNum",1);
		return "redirect:/list.do"; //�� ����(��Ʈ�ѷ����� ��û���� ȣ���ҰŶ�!)
		
		}
		
		//�ۼ���(ó��)
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
		//��� �ޱ�(dbó��)
		
		//��� �ޱ�(���ο� ���̺� ������Ʈ)
		
		
		/*public String writePro(@ModelAttribute("boardDTO")BoardDTO boardDTO, HttpServletRequest request) {
			
			int maxNum = 0; //�ִ� �� ��ȣ ���� ���� (�� �׷쿡 ������ �־�� �Ŵ�!)
			
			if(sqlSession.selectOne("board.numMax")!=null) //���� ������! = �۹�ȣ�� �ִ�.
			{
				maxNum = sqlSession.selectOne("board.numMax"); //�ִ� �۹�ȣ(board.xml �����ϱ�!)�� numMax�� �Ҵ�(������ �ִ� �� ��ȣ�� ������ ��)
			}
			if(maxNum != 0) {//���� �����ϸ�
				maxNum = maxNum+1; //ref ���� �׷쿡 ��������
			}
			else { 
				//ó�� ���̸�,
				maxNum=1; //ref(�� �׷�)�� 1�� �ִ´�.
			}
			
			//ip������(���� ��Ƴ��� ��!)
			String ip = request.getRemoteAddr(); //���� ip�� ��� ����
			boardDTO.setIp(ip);
			
			//���� ��� ó��
			if(boardDTO.getNum()!=0) //����̸�
			{
				//��� �����ֱ� ��ġ Ȯ��!
				sqlSession.update("board.reStep", boardDTO);
				
				boardDTO.setRe_step(boardDTO.getRe_step()+1); //�ۼ���+1
				boardDTO.setRe_level(boardDTO.getRe_level()+1); //�۱���+1
				
			}else {//�����̸�
					
				boardDTO.setRef(new Integer(maxNum)); //�� �׷�
				boardDTO.setRe_step(new Integer(0)); //�� ���̴ϱ� �� ������ 0�� �ȴ�.
				boardDTO.setRe_level(new Integer(0)); //������ 0�̵ȴ�.
				}
			
			
			sqlSession.insert("board.insertDao", boardDTO);
					
			return "redirect:/list.do"; //�� ����(��Ʈ�ѷ����� ��û���� ȣ���ҰŶ�!)
		
		}*/
		//ip�� ���� ������ ���� ��
/*		String ip = request.getRemoteAddr();
		boardDTO.setIp(ip);
		
		//�� ��ȣ�� ������Ʈ�ϱ�
		int numMax=0;
		int refno=0;
		if(sqlSession.selectOne("board.numMax") != null) //�� ��ü�� �������� ���� ������Ʈ �� Ȯ��
		{
			numMax = sqlSession.selectOne("board.numMax");
		}
		//���� ù ���̸� 1�� �־��ְ�(numMax�� 0(���� ���ٸ�.))
		if(numMax==0)
		{ refno=1; }
		else//���� �ϳ��� �ִٴ� ��
		{ refno = numMax+1; }
		boardDTO.setRef(refno);
			if(boardDTO.getNum()!=0) //����̸�
		{
			//��� �����ֱ� ��ġ Ȯ��!
			sqlSession.update("board.reStep", boardDTO);
			
			boardDTO.setRe_step(boardDTO.getRe_step()+1); //�ۼ���+1
			boardDTO.setRe_level(boardDTO.getRe_level()+1); //�۱���+1
			
		}else {//�����̸�
				
			boardDTO.setRef(new Integer(refno)); //�� �׷�
			boardDTO.setRe_step(new Integer(0)); //�� ���̴ϱ� �� ������ 0�� �ȴ�.
			boardDTO.setRe_level(new Integer(0)); //������ 0�̵ȴ�.
			}
		
		
		sqlSession.insert("board.insertDao", boardDTO);
		//ref re_level, re_step ��� �����ֱ�(�׷���, �ش� �� �׷쿡 ���� ����� ���� ��)
		//step�� ����� ��� ���� ���ӽ�ų���� ���� ���� �ܰ��.(��� 1 , ���2, ��� 1�� 1�� ���, ��� 1�� 2�� ��� ��), level�� �� �����ε� ������ ����?�� ��Ÿ����.
	/*	 if(boardDTO.getNum() !=0 ) {//����̸�
             //��� ��ġ Ȯ��
			 sqlSession.update("board.reStep",boardDTO);
             boardDTO.setRe_step(boardDTO.getRe_step()+1);//�ۼ���+1
             boardDTO.setRe_level(boardDTO.getRe_level()+1);//�� ����+1

       }else {//�����̸�

             boardDTO.setRef(refno);//�۱׷�
             boardDTO.setRe_step(new Integer(0));//�ۼ���
             boardDTO.setRe_level(new Integer(0));//�� ����
       }
		
		sqlSession.insert("board.insertDao", boardDTO);
		
		return "redirect:/list.do";
	}*/
//------------------------------------------------------------comment----------------------------------------------------------------------------------------------------------
	//content ����ϱ�(��ϸ��ϸ�Ǵ°Ŷ� ���� �̵��� �ʿ� ����.)
	@RequestMapping(value="comment.do", method=RequestMethod.POST)
	@ResponseBody
	public int addcomment(@RequestBody Map<String,Object> map )
	{	
	//resultmap���� �����͸� �����ͼ� �־�� ��(����)
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
	//��� ����
	@RequestMapping(value="commentDelete.do",method=RequestMethod.POST)
	@ResponseBody
	public void delcomment(@RequestParam("no") String no) {
		sqlSession.delete("comment.deleteDao",Integer.parseInt(no));	
	}
	//��� ����
	@RequestMapping(value="commentsUpdate.do",method=RequestMethod.POST)
	@ResponseBody
	public void updcomment(@RequestParam("no") String no,@RequestParam("contents") String contents) {
		CommentDTO cdto = new CommentDTO();
		cdto.setContents(contents);
		cdto.setNo(Integer.parseInt(no));
		sqlSession.update("comment.updateDao",cdto);
	}
//--------------------------------------------------------------------------------------------------------------------------------------------------------write-end-------------------------	
	//��й�ȣ �����Ұ��� post üũ �� �ֱ�
/*	@RequestMapping("chkpwd.do")
	public String chkpwd(@RequestParam("chk") boolean chk, Model model) {
		System.out.println("gogo");
		System.out.println(chk);
		model.addAttribute("chpw",chk);
	return ".main.board.writeForm";
	
	}
*/	
		
	//��б� üũ ��
	@RequestMapping("chkform.do")
	public String chkform(Model model,@RequestParam("num") int num) {
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setNum(num);
		model.addAttribute("num",num);
		return ".main.board.chk_title";
	}
	//��б� üũ
	@RequestMapping(value="chktitle.do", method=RequestMethod.POST)
	public String chktitle(Model model, @RequestParam("password") String password,@RequestParam("num") String num, HttpServletRequest request, HttpSession session) {
	//	HttpServletResponse response
	//���� ��ȣ�� �����;� ��! �׷��� �ش� ��ȣ�� ����� ã�� ���� ���� ���Ͽ� ������ �ش� �۷� �̵�
		System.out.println("pwd "+password);
		System.out.println("num "+num);
		
		int result=0;
		BoardDTO boardDTO = new BoardDTO();
		boardDTO = sqlSession.selectOne("board.getBoard",Integer.parseInt(num));
		int pwd = Integer.parseInt(password);
		int pw = boardDTO.getPassword();
		//��� ��
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
	//��б� ���� �α��� ���� �����
	@RequestMapping(value="delscrt.do", method=RequestMethod.DELETE)
	public void deleteSecret(HttpSession session) {
		session.removeAttribute("secret"); //��бۿ� �α����ߵ��� ���� ���� secret�� ����ڴ�!
	}
	//�� ����
	@RequestMapping("delete.do")
	public String delete(int num) {
		sqlSession.delete("board.deleteDao",num);
		return "redirect:list.do";
	}
//----------------------------------------------------------------------------------------file---------
	//���� �����ϱ�
	@RequestMapping(value="file.do", method=RequestMethod.POST)
	public void file(@RequestParam("fileup") MultipartFile file) {	
		Fileupload fileupload = new Fileupload();
		Map<String, String> save = fileupload.Save(file);
		String imgurl = save.get("PATH"); //�̰� blob��ſ� db�� �����ϱ�(db���� varcharó��! string���·� ������ �Ǵ� ��)
		//jsp�� �����Ҷ��� �̸� localhost:8081�� ���� �ٿ��� ���� controller�� �Ѱܼ� �����ų ��!
	}
	//�׸��� ���Ե� �۾��� ��(��������, ������ ��)
	@RequestMapping("flgift.do")
	public String flw(HttpSession session,HttpServletResponse response) throws Exception {
		if(session.getAttribute("id")==null)
		{
			 ScriptUtils scriptUtils = new ScriptUtils();
			 scriptUtils.alertAndMovePage(response, "�α��� �� �̿��� �����մϴ�.", "loginForm.do");
			 
		}
		if(session.getAttribute("str").equals("nb"))
		{session.setAttribute("btype", "nboard");}
		else {session.setAttribute("btype", "gallery");}
		return ".main.board.fl_gift";
	}
	//�׸� �����ϱ� 
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
			
			//��ȣ�� �־ ã�� ù��°�� �����;� �� 	
			List<ImgDTO> slist = sqlSession.selectList("imgs.selectImg",images.getBno());
			
			int cno = sqlSession.selectOne("imgs.selectCount",images.getBno());
			int totalcount = sqlSession.selectOne("imgs.selectAllCount");
			int num=totalcount-cno+1;
			
			images.setNo(num);
			images.setThumb(slist.get(0).getUrl());
			System.out.println("���� �۰���"+cno);
			System.out.println("������� ������ ��ȣ"+num);
			sqlSession.update("imgs.updateImg",images);
		}
		
		//������� ������ ��, ���� ù ������ ����Ϸ� �����ؾ� ��
		//�����͸� ���� ���� ���߿� ù��°���� ��󳻼� �����ϱ�
		model.addAttribute("str",session.getAttribute("str"));
		model.addAttribute("pageNum",1);
		return "redirect:/list.do";
	}
	//�׸� ����Ʈ ��Ÿ����
	@RequestMapping("fllist.do")
	public String fllist(Model model) {
		List<ApplDTO> selectList = sqlSession.selectList("nboard.findImgList",1);
		
		model.addAttribute("fllist",selectList);
		return ".main.board.fllist";
	}
	//�׸� ����
	@RequestMapping(value="deleteimg.do",method=RequestMethod.POST)
	public @ResponseBody void deleteImg(@RequestBody Map<String, String> url) {
		System.out.println(url);
	    String urladdress = url.get("url");
		sqlSession.delete("imgs.deleteImg",urladdress);
		
	}
	//�� �׸� ������ ����(��������/ community ���� �����ؼ� ��)
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
	
	

