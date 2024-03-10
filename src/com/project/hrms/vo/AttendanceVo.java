package com.project.hrms.vo;

public class AttendanceVo {
	
	private String id;
	private String date;
	private String startTime;
	private String endTime;
	private String workHours;
	private String isLate;
	private String isEarlyLeave;
	private String isOnLeave;
	private String overtimeHours;
	private String holidayOvertimeHours;
	private String lateNightOvertimeHours;
	
	public AttendanceVo(String id, String date, String startTime, String endTime, String workHours, String isLate, String isEarlyLeave,
						String isOnLeave, String overtimeHours, String holidayOvertimeHours, String lateNightOvertimeHours) {

		this.id = id;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.workHours = workHours;
		this.isLate = isLate;
		this.isEarlyLeave = isEarlyLeave;
		this.isOnLeave = isOnLeave;
		this.overtimeHours = overtimeHours;
		this.holidayOvertimeHours = holidayOvertimeHours;
		this.lateNightOvertimeHours = lateNightOvertimeHours;
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getWorkHours() {
		return workHours;
	}

	public void setWorkHours(String workHours) {
		this.workHours = workHours;
	}

	public String getIsLate() {
		return isLate;
	}

	public void setIsLate(String isLate) {
		this.isLate = isLate;
	}

	public String getIsEarlyLeave() {
		return isEarlyLeave;
	}

	public void setIsEarlyLeave(String isEarlyLeave) {
		this.isEarlyLeave = isEarlyLeave;
	}

	public String getIsOnLeave() {
		return isOnLeave;
	}

	public void setIsOnLeave(String isOnLeave) {
		this.isOnLeave = isOnLeave;
	}

	public String getOvertimeHours() {
		return overtimeHours;
	}

	public void setOvertimeHours(String overtimeHours) {
		this.overtimeHours = overtimeHours;
	}

	public String getHolidayOvertimeHours() {
		return holidayOvertimeHours;
	}

	public void setHolidayOvertimeHours(String holidayOvertimeHours) {
		this.holidayOvertimeHours = holidayOvertimeHours;
	}

	public String getLateNightOvertimeHours() {
		return lateNightOvertimeHours;
	}

	public void setLateNightOvertimeHours(String lateNightOvertimeHours) {
		this.lateNightOvertimeHours = lateNightOvertimeHours;
	}

	@Override
	public String toString() {
		
		return "AttendanceVo [id=" + id + ", date=" + date + ", startTime=" + startTime + ", endTime=" + endTime + ", workHours=" + workHours +
							", isLate=" + isLate + ", isEarlyLeave=" + isEarlyLeave + ", isOnLeave=" + isOnLeave + ", overtimeHours=" + overtimeHours +
							", holidayOvertimeHours=" + holidayOvertimeHours + ", lateNightOvertimeHours=" + lateNightOvertimeHours + "]";
	
	}

}
