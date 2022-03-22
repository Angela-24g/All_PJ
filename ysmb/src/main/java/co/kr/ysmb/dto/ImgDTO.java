package co.kr.ysmb.dto;

import java.util.Date;

public class ImgDTO { //list로 담으면서 imgdto를 담을 dto를 만들기!
	private int no;
	private int bno;
	private String url;
	private Integer document;
	private String thumb;
	private Date iwdate;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getDocument() {
		return document;
	}
	public void setDocument(Integer document) {
		this.document = document;
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	public Date getIwdate() {
		return iwdate;
	}
	public void setIwdate(Date iwdate) {
		this.iwdate = iwdate;
	}
	
	
}
