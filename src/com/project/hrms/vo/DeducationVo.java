package com.project.hrms.vo;

public class DeducationVo {

	private int num;
	private String deducation;
	private double subscriberRate;
	private double companyRate;
	private boolean toUse;

	public DeducationVo(int num, String deducation, double subscriberRate, double companyRate, boolean toUse) {
		
		this.num = num;
		this.deducation = deducation;
		this.subscriberRate = subscriberRate;
		this.companyRate = companyRate;
		this.toUse = toUse;
		
	}
	
	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	
	public String getDeducation() {
		return deducation;
	}
	
	public void setDeducation(String deducation) {
		this.deducation = deducation;
	}
	
	public double getSubscriberRate() {
		return subscriberRate;
	}
	
	public void setSubscriberRate(double subscriberRate) {
		this.subscriberRate = subscriberRate;
	}
	
	public double getCompanyRate() {
		return companyRate;
	}
	
	public void setCompanyRate(double companyRate) {
		this.companyRate = companyRate;
	}
	
	public boolean isToUse() {
		return toUse;
	}
	
	public void setToUse(boolean toUse) {
		this.toUse = toUse;
	}

	@Override
	public String toString() {
		
		return "DeducationVo [num=" + num + ", deducation=" + deducation + ", subscriberRate=" + subscriberRate
				+ ", companyRate=" + companyRate + ", toUse=" + toUse + "]";
		
	}
	
}
