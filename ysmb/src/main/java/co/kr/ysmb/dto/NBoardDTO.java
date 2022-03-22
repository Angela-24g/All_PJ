package co.kr.ysmb.dto;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class NBoardDTO {
	private int no;
	private String title;
	private String ncontent;
	private Date nwdate;
	private Integer document;
	private String nwriter;
	private int rcount;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNcontent() {
		return ncontent;
	}
	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}
	public Date getNwdate() {
		return nwdate;
	}
	public void setNwdate(Date nwdate) {
		this.nwdate = nwdate;
	}
	
	public NBoardDTO(NFBoardDTO boardDTO) {
	
		this.ncontent = boardDTO.getContent();
		this.title = boardDTO.getTitle();
		

	}
	
	public NBoardDTO() {
		
	}
	public int getRcount() {
		return rcount;
	}
	public void setRcount(int rcount) {
		this.rcount = rcount;
	}
	public Integer getDocument() {
		return document;
	}
	public void setDocument(Integer document) {
		this.document = document;
	}
	public String getNwriter() {
		return nwriter;
	}
	public void setNwriter(String nwriter) {
		this.nwriter = nwriter;
	}
}
