package com.project.hrms.member.view;

import java.util.Arrays;
import java.util.Scanner;

import com.project.hrms.dao.AttendanceDao;
import com.project.hrms.main.PersonDao;
import com.project.hrms.main.PersonVo;
import com.project.hrms.main.UserDao;
import com.project.hrms.vo.AttendanceVo;

public class MemberAttendanceView {
	
	private static int totalWorkDays = 0;
	private static int totalOffDays = 0;
	private static int totalWorkHours = 0;
	private static int totalWorkMinutes = 0;
	private static int totalLateDays = 0;
	private static int totalEarlyLeaveDays = 0;
	private static int totalLeaveDays = 0;
	
	private static int[] totalOvertimeHours = {0, 0};

	public static void printAttendanceView() {
		
		Scanner scan = new Scanner(System.in);

		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.print("연도를 입력하세요: ");
		String year = scan.nextLine();
		System.out.print("월을 입력하세요: ");
		String month = scan.nextLine();
			
		String id = UserDao.auth.getId();
		
		boolean isExist = checkExistence(year, month, id);
		
		if (!isExist) {
			
			System.out.println("------------------------------------------------------------------------------------------------");
			System.out.println("근태 이력이 존재하지 않습니다.");
			
		} else {

			printList(year, month, id);
			
			printMonthlySummary(totalWorkDays, totalOffDays, totalWorkHours, totalWorkMinutes, totalOvertimeHours[0], totalOvertimeHours[1],
								totalLateDays, totalEarlyLeaveDays, totalLeaveDays);
			
		}

		System.out.println("\nEnter 키를 누르면 계속 진행할 수 있습니다.");
		scan.nextLine();
		
		return;
		
	}
	
	public static boolean checkExistence(String year, String month, String id) {
	
		boolean isLoaded = AttendanceDao.load(year, month);
		
		boolean isExist = false;
		
		if (isLoaded) {

			for (AttendanceVo  a : AttendanceDao.list) {
				
				if (a.getId().equals(id)) {
					
					isExist = true;
					
					break;
					
				}
				
			}
			
		}
			
		return isExist;
		
	}
	
	public static void printList(String year, String month, String id) {
		
		initializeVariable();
		
		String name = "";

		for (PersonVo p : PersonDao.list) {
				
			if (p.getId().equals(id)) {
					
				name = p.getName();
				
				break;
					
			}
				
		}

		System.out.println("================================================================================================");
	    System.out.printf("[%s님의 %s년 %s월 근태 조회 결과]\n\n", name, year, month);
	    System.out.printf("%s%s%s%s%s%s%s%s%s%s\n", "[일자]", "[출근시간]", "[퇴근시간]", "[근무시간]", "[지각]", "[조퇴]", "[휴가]", "[OT시간(일반)]", "[OT시간(휴일)]", "[OT시간(심야)]");
		
		for (AttendanceVo a : AttendanceDao.list) {
			
			if (a.getId().equals(id)) {
				
			    String isLate = a.getIsLate().equals("0") ? "X" : "O";
			    String isEarlyLeave = a.getIsEarlyLeave().equals("0") ? "X" : "O";
			    String isOnLeave = a.getIsOnLeave().equals("0") ? "X" : "O";

			    System.out.printf("%3s일%8s%10s%10s%7s%6s%6s%10s%14s%14s\n",
					            a.getDate(), a.getStartTime(), a.getEndTime(), a.getWorkHours(), isLate, isEarlyLeave, isOnLeave,
					            a.getOvertimeHours(), a.getHolidayOvertimeHours(), a.getLateNightOvertimeHours());
			    
			    calculateTotal(a);
				
			}
			
		}

	}
	
	private static void initializeVariable() {
		
		totalWorkDays = 0;
		totalOffDays = 0;
		totalWorkHours = 0;
		totalWorkMinutes = 0;
		totalLateDays = 0;
		totalEarlyLeaveDays = 0;
		totalLeaveDays = 0;
		
		Arrays.fill(totalOvertimeHours, 0);
        
    }
	
	private static void calculateTotal(AttendanceVo a) {
	    
		totalWorkDays += !a.getWorkHours().equals("0:00") ? 1 : 0;
		totalOffDays += a.getWorkHours().equals("0:00") ? 1 : 0;
		
		totalWorkHours += Integer.parseInt(a.getWorkHours().split(":")[0]);
		totalWorkMinutes += Integer.parseInt(a.getWorkHours().split(":")[1]);
		
		while (totalWorkMinutes >= 60) {
			
			totalWorkHours++;
			totalWorkMinutes -= 60;
		    
		}
		
		getTotalOvertimeHours(a.getOvertimeHours(), a.getHolidayOvertimeHours(), a.getLateNightOvertimeHours());
		
		totalLateDays += Integer.parseInt(a.getIsLate());
		totalEarlyLeaveDays += Integer.parseInt(a.getIsEarlyLeave());
		totalLeaveDays += Integer.parseInt(a.getIsOnLeave());

	}

	private static void getTotalOvertimeHours(String... overtimes) {
		
	    for (String overtime : overtimes) {
	    	
	        if (!overtime.equals("0")) {
	        	
	        	totalOvertimeHours[0] += Integer.parseInt(overtime.split(":")[0]);
	        	totalOvertimeHours[1] += Integer.parseInt(overtime.split(":")[1]);
	            
	        }
	        
	    }

	    while (totalOvertimeHours[1] >= 60) {
	    	
	    	totalOvertimeHours[0]++;
	    	totalOvertimeHours[1] -= 60;
	        
	    }
	    
	}
	
	private static void printMonthlySummary(int totalWorkDays, int totalOffDays, int totalWorkHours, int totalWorkMinutes, int totalOvertimeHours,
											int totalOvertimeMinutes, int totalLateDays, int totalEarlyLeaveDays, int totalLeaveDays) {
		
		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.println("[월 소계]\n");
		System.out.println("[근무일수]    [휴무일수]    [근무시간]     [OT시간]     [지각건수]     [조퇴건수]     [휴가건수]");
		System.out.printf("%5s일%12s일%9s시간%3s분%5s시간%3s분%8s건%13s건%13s건\n",
						totalWorkDays, totalOffDays, totalWorkHours, totalWorkMinutes, totalOvertimeHours, totalOvertimeMinutes, totalLateDays,
						totalEarlyLeaveDays, totalLeaveDays);
		
	}
	
}
