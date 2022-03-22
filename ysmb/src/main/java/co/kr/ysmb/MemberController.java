package co.kr.ysmb;


import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.kr.ysmb.dto.MemberDTO;



@Controller
public class MemberController {
	@Autowired SqlSession sqlSession;
	
	//회원가입 폼
	@RequestMapping("joinForm.do")
	public String join() {
		return ".main.member.join";
	}
	//id중복확인*ajax
	@RequestMapping(value="confirmID.do", method=RequestMethod.POST)
	@ResponseBody
	public String confirm( 
			@RequestParam("cid") String cid) { //requestBody는 json데이터 안에 있는 값들 다 가져오는 것(그래서 따로 형식받아서 쓴다)
		System.out.println("id= "+cid);
		 MemberDTO memberDTO = sqlSession.selectOne("member.selectOne",cid);
		 String check="1";
		 if(memberDTO==null)
		{
			check="1"; //사용가능한 id
		}
		 else { check="-1";}
		 
		//return ".main.board.confirmID";
		 return check;
	}
	//회원가입처리
	@RequestMapping(value="insertPro.do", method=RequestMethod.POST)
	public String joinPro(Model model, @ModelAttribute("memberDTO") MemberDTO memberDTO,HttpServletRequest request) {
		String tel1 = request.getParameter("tel1");
		String tel2 = request.getParameter("tel2");
		String tel3 = request.getParameter("tel3");
		String tell = tel1 + tel2 + tel3;
		
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String email = email1+email2;
		
		memberDTO.setTel(tell);
		memberDTO.setEmail(email);
		
		sqlSession.insert("member.insertMember", memberDTO);
		model.addAttribute("str","nb");
		model.addAttribute("pageNum",1);
		return "redirect:/list.do";
	}
	//로그인 폼
	@RequestMapping("loginForm.do")
	public String loginF() {
		return ".main.member.login";
	}
	//로그인 처리
	@RequestMapping(value="loginPro.do",method=RequestMethod.POST)
	@ResponseBody
	public String login(@RequestParam("id") String id,@RequestParam("pw") String pw, Model model, HttpSession session) {
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pw", pw);
	
		MemberDTO memberDTO = sqlSession.selectOne("member.selectLogin", map);
		
		String check;
		if(memberDTO == null)
		{
			model.addAttribute("msg","failed");
			check = "-1";
			return check;
		}
		else { 
		model.addAttribute("msg","로그인 성공");
		session.setAttribute("id",id);
		String name = memberDTO.getName();
		session.setAttribute("name",name);
		check = "1";
		return check;} //로그인 가능

	}
	//로그아웃
	@RequestMapping("logOut.do")
	public String logout(HttpSession session, Model model) {
		
		if(session.getAttribute("id")!=null)
		{session.removeAttribute("name");
		session.removeAttribute("id");
		session.removeAttribute("javax.servlet.jsp.jstl.fmt.request.charset");}
		
	
		model.addAttribute("str",session.getAttribute("str"));
		model.addAttribute("pageNum",1);
		return "redirect:/list.do";
	}
	//마이페이지 폼
	@RequestMapping("mypage.do")
	public String mypage() {
		return ".main.member.mypage";
	}
	//회원정보 수정 폼
	@RequestMapping("chageMember.do")
	public String chM(HttpSession session, Model model) {
		String id = (String) session.getAttribute("id");
		MemberDTO mdto = sqlSession.selectOne("member.selectOne", id);
		String tel = mdto.getTel();
		String tel1 = tel.substring(0,3);
		String tel2 = tel.substring(3,7);
		String tel3 = tel.substring(7);
		
		String email = mdto.getEmail();
		int parseNo = email.indexOf("@");
		String email1 = email.substring(0,parseNo);
		String email2 = email.substring(parseNo);
		
		model.addAttribute("data",mdto);
		
		model.addAttribute("tel1",tel1);
		model.addAttribute("tel2",tel2);
		model.addAttribute("tel3",tel3);
		model.addAttribute("email1",email1);		
		model.addAttribute("email2",email2);	
		model.addAttribute("addr1",mdto.getAddr());
		model.addAttribute("addr2",mdto.getAddr2());
		return ".main.member.changeMember";
	}
	//회원 정보 수정 처리
	@RequestMapping(value="changePro.do", method=RequestMethod.POST)
	public String chagePro(@ModelAttribute("memberDTO") MemberDTO memberDTO, Model model,HttpServletRequest request) {
		
		
		String id = memberDTO.getId();
		
		String tel1 = request.getParameter("tel1");
		String tel2 = request.getParameter("tel2");
		String tel3 = request.getParameter("tel3");
		String tell = tel1 + tel2 + tel3;
		
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String email = email1+email2;
		
		memberDTO.setTel(tell);
		memberDTO.setEmail(email);
		
		sqlSession.update("member.memberUpdate",memberDTO);
		//ajax로 보내자
		model.addAttribute("id",id);
		return ".main.member.success";
	}
	//회원 탈퇴
	@RequestMapping("deleteMember.do")
	public String delete(String id) {
		//1. 먼저 id를 찾고
		MemberDTO mdto = sqlSession.selectOne("member.selectOne",id);
		
		int result=0;
		if(mdto==null) {
			result=-1;
			return ".main.member.deleteMember";
		}
		else {//2. id가 있으면 삭제하기(및 메세지 보내기)
			result=1;
			sqlSession.delete("member.memberDelete",id);
			return ".main.member.delete";
		}
	}
	//ID, PW 찾기 폼 나타내기
	@RequestMapping(value="findID.do",method=RequestMethod.GET)
	public String findID() {
		return ".main.member.findID";
	}
	@RequestMapping(value="findPW.do",method=RequestMethod.GET)
	public String findPW() {
		return ".main.member.findPW";
	}
	
	//ID, PW 찾기 수행
	@RequestMapping("idPro.do")
	public String idPro() {
		return "";
	}
	
	
	
}//class-end
