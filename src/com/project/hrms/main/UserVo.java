package com.project.hrms.main;

public class UserVo {

	String id;
	String pw;
	String level;
	
	public UserVo() {
		
	}

	public UserVo(String id, String pw, String level) {
		
		this.id = id;
		this.pw = pw;
		this.level = level;
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Override
	public String toString() {
		
		return "UserVo [id=" + id + ", pw=" + pw + ", level=" + level + "]";
	
	}
	
}
