package co.kr.ysmb.dto;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class NFBoardDTO {
	private int no;
	private String title;
	private String content;
	private Date nwdate;
	private int document;
	private List<MultipartFile> attachFiles;
	
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getNwdate() {
		return nwdate;
	}
	public void setNwdate(Date nwdate) {
		this.nwdate = nwdate;
	}
	
	public List<MultipartFile> getAttachFiles() {
		return attachFiles;
	}
	public void setAttachFiles(List<MultipartFile> attachFiles) {
		this.attachFiles = attachFiles;
	}
	public int getDocument() {
		return document;
	}
	public void setDocument(int document) {
		this.document = document;
	}
	
}
