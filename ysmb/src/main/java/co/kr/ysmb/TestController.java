package co.kr.ysmb;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import co.kr.ysmb.dto.CmtDTO;
import co.kr.ysmb.dto.CommentDTO;
import co.kr.ysmb.dto.MembDTO;
import co.kr.ysmb.dto.MemberDTO;


@Controller
public class TestController {
	//����Ʈ�� �߰��� �������ִ� ��
	
	 @Autowired SqlSession sqlSession;
	 	
	 @RequestMapping(value="test",method = RequestMethod.GET,produces="application/json;charset=UTF-8")
	 public @ResponseBody List<CmtDTO> listadd(
	     	 @RequestParam("start") String start, 
			 @RequestParam("cnt") String cnt,
			 @RequestParam("board_no") String board_no) {
		 
		 
		 
		 Map<String,Object> map = new HashMap<String,Object>();
		 map.put("start", Integer.parseInt(start));
		 map.put("cnt",Integer.parseInt(cnt));
		 map.put("board_no", board_no);
	   //  List<CommentDTO> cdto =  sqlSession.selectList("comment.getApplList", map);
		 List<CommentDTO> cdto =  sqlSession.selectList("comment.listDao",map);
		 List<CmtDTO> cmddto = new ArrayList<CmtDTO>();
		for(int i = 0 ; i < cdto.size() ; i++) {
			CmtDTO cmdto = new CmtDTO();

		int j = sqlSession.selectOne("comment.selectOne",cdto.get(i).getNo());
		MemberDTO name = sqlSession.selectOne("member.findDtobyNo",j);
		cmdto.setNo(cdto.get(i).getNo());
		cmdto.setWwriter(new MembDTO(name, j));
		cmdto.setContents(cdto.get(i).getContents());
		cmdto.setWdate(cdto.get(i).getWdate());
		//cmdto��� �׸��� ����� �ű⿡ �����͸� �߰��Ѵ�(membdto)
		System.out.println(name.getNo());
		//	int writer =cdto.get(i).getWriter();
		//	String name = (String)sqlSession.selectOne("member.findbyNo", writer);
		cmddto.add(cmdto);
		}
		
//		cdto - no, bno, writer(int), content
//		cmdto - no, bno, writer(membdto(name)), contentno, bno, writer(membdto(name)), conte
		 
		return cmddto;
		
	 }
	
}
