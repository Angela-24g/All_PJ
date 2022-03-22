package co.kr.ysmb.dto;

import java.util.Date;

public class CmtDTO {
	private int no;
	private String contents;
	private Date wdate;
	private MembDTO wwriter;
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
	public MembDTO getWwriter() {
		return wwriter;
	}
	public void setWwriter(MembDTO wwriter) {
		this.wwriter = wwriter; 
	}
	
}
