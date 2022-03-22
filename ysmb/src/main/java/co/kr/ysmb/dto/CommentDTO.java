package co.kr.ysmb.dto;

import java.util.Date;

public class CommentDTO {
	private int no;
	private String contents;
	private Date wdate;
	private MemberDTO writer;
	private BoardDTO board_no;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Date getWdate() {
		return wdate;
	}
	public void setWdate(Date wdate) {
		this.wdate = wdate;
	}
	public MemberDTO getWriter() {
		return writer;
	}
	public void setWriter(MemberDTO writer) {
		this.writer = writer;
	}
	public BoardDTO getBoard_no() {
		return board_no;
	}
	public void setBoard_no(BoardDTO board_no) {
		this.board_no = board_no;
	}
	
	 
/*	public int getWriter() {
		return writer;
	}
	public void setWriter(int writer) {
		this.writer = writer;
	}
	public int getBoard() {
		return board_no;
	}
	public void setBoard(int board_no) {
		this.board_no = board_no;
	} */
}
