package com.project.hrms.vo;

public class AnnualVo {

	String id;
	int allotmentAnnual;
	int usedAnnal;
	int leftoverAnnual;
	
	public AnnualVo() {
	}

	public AnnualVo(String id, int allotmentAnnual, int usedAnnal, int leftoverAnnual) {
		
		this.id = id;
		this.allotmentAnnual = allotmentAnnual;
		this.usedAnnal = usedAnnal;
		this.leftoverAnnual = leftoverAnnual;
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAllotmentAnnual() {
		return allotmentAnnual;
	}

	public void setAllotmentAnnual(int allotmentAnnual) {
		this.allotmentAnnual = allotmentAnnual;
	}

	public int getUsedAnnal() {
		return usedAnnal;
	}

	public void setUsedAnnal(int usedAnnal) {
		this.usedAnnal = usedAnnal;
	}

	public int getLeftoverAnnual() {
		return leftoverAnnual;
	}

	public void setLeftoverAnnual(int leftoverAnnual) {
		this.leftoverAnnual = leftoverAnnual;
	}

	@Override
	public String toString() {
		
		return "AnnualVo [id=" + id + ", allotmentAnnual=" + allotmentAnnual + ", usedAnnal=" + usedAnnal
				+ ", leftoverAnnual=" + leftoverAnnual + "]";
		
	}
	
}
