package com.project.hrms.vo;

public class CertificateVo {

	private int no;
	private String rqDate;
	private String id;
	private int type;
	private int certStatus;
	
	public CertificateVo(int no, String rqDate, String id, int type, int certStatus) {
		
		this.no = no;
		this.rqDate = rqDate;
		this.id = id;
		this.type = type;
		this.certStatus = certStatus;
		
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getRqDate() {
		return rqDate;
	}

	public void setRqDate(String rqDate) {
		this.rqDate = rqDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getDepartment() {
//		return department;
//	}
//
//	public void setDepartment(String department) {
//		this.department = department;
//	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getCertStatus() {
		return certStatus;
	}

	public void setCertStatus(int certStatus) {
		this.certStatus = certStatus;
	}

	@Override
	public String toString() {
		
		return "CertificateVo [no=" + no + ", rqDate=" + rqDate + ", id=" + id + ", type=" + type + ", certStatus=" + certStatus + "]";
	
	}
	
}
