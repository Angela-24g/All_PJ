package co.kr.ysmb.dto;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
//imgdto를 담을거랑 nboard의 데이터를 담아서 입출력할 것!
public class ApplDTO {
	private int no;
	private String title;
	private String ncontent;
	private String nwriter;
	private Date nwdate;
	private List<ImgDTO> imgs; 
	private Integer document;
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
	
	public Date getNwdate() {
		return nwdate;
	}
	public void setNwdate(Date nwdate) {
		this.nwdate = nwdate;
	}
	public List<ImgDTO> getImgs() {
		return imgs;
	}
	public void setImgs(List<ImgDTO> imgs) {
		this.imgs = imgs;
	}
	public String getNcontent() {
		return ncontent;
	}
	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
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
