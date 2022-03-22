package co.kr.ysmb.dto;

import java.util.Date;

public class MembDTO {
	private int no;
	private String name;
	private String id;
	private String email;
	private String tel;
	private String addr;
	private String addr2;
	private String zipcode;
	private Date regdate;
	
	public MembDTO() {
		
	}
	public MembDTO(MemberDTO memberDTO, int no) {
		this.no = no;
		this.name = memberDTO.getName();
		this.id = memberDTO.getId();
		this.email = memberDTO.getEmail();
		this.tel = memberDTO.getTel();
		this.addr = memberDTO.getAddr();
		this.addr2 = memberDTO.getAddr2();
		this.zipcode = memberDTO.getZipcode();
		this.regdate = memberDTO.getRegdate();
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
}
