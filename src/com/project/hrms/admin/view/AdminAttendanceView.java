package com.project.hrms.admin.view;

import java.util.Calendar;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.project.hrms.dao.AttendanceDao;
import com.project.hrms.member.view.MemberAttendanceView;

public class AdminAttendanceView {
	
	public static void printAttendanceView() {
		
		Scanner scan = new Scanner(System.in);

		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.print("날짜(월)를 입력하세요: ");
		String month = scan.nextLine();
		System.out.print("사번을 입력하세요: ");
		String id = scan.nextLine();
		
		String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		
		boolean isExist = MemberAttendanceView.checkExistence(year, month, id);
		
		if (!isExist) {
			
			System.out.println("------------------------------------------------------------------------------------------------");
			System.out.println("근태 이력이 존재하지 않습니다.");
			
		} else {

			MemberAttendanceView.printList(year, month, id);
			
			System.out.println("\nEnter 키를 누르면 계속 진행할 수 있습니다.");
			scan.nextLine();
			
			printMenu(year, month, id);
			
		}

		System.out.println("\nEnter 키를 누르면 계속 진행할 수 있습니다.");
		scan.nextLine();
		
		return;
		
	}

	public static void printMenu(String year, String month, String id) {
		
		while (true) {
			
			Scanner scan = new Scanner(System.in);
			
			System.out.println("================================================================================================");
			System.out.println("1. 출/퇴근 시간 수정");
			System.out.println("2. 이전 화면");
			System.out.print("번호를 입력하세요: ");
			String input = scan.nextLine();
			
			switch (input) {
			
				case "1": 
	
					printSubView(year, month, id);
					
					return;
					
				case "2": 
					
					return;
					
				default:
					
					System.out.println("------------------------------------------------------------------------------------------------");
					System.out.println("입력값이 유효하지 않습니다.\n");
					System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
					scan.nextLine();
				
			}

		}

	}
	
	public static void printSubView(String year, String month, String id) {
			
		Scanner scan = new Scanner(System.in);
		
		while (true) {
			
			System.out.println("------------------------------------------------------------------------------------------------");
			System.out.print("날짜(일)를 입력하세요: ");
			String date = scan.nextLine();
			
			boolean isValid = checkValidity(year, month, date);
			
			if (isValid) {
				
				printSubMenu(year, month, date, id);
				
				return;
				
			}
			
		}
		
	}
	
	private static boolean checkValidity(String year, String month, String date) {
		
		Scanner scan = new Scanner(System.in);
		
		int thisMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
		int today = Calendar.getInstance().get(Calendar.DATE);
		
		int inputMonth = Integer.parseInt(month);
		int inputDate = Integer.parseInt(date);
		
		Calendar c = Calendar.getInstance();
		
		c.set(Calendar.YEAR, Integer.parseInt(year));
		c.set(Calendar.MONTH, inputMonth);
		
		int lastDate = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		if ((inputMonth != thisMonth && (inputDate > 0 && inputDate <= lastDate)) ||
			(inputMonth == thisMonth && (inputDate > 0 && inputDate < today))) {
			
			return true;
			
		} else {
			
			System.out.println("------------------------------------------------------------------------------------------------");
			System.out.println("입력값이 유효하지 않습니다.\n");
			System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
			scan.nextLine();
			
			return false;
			
		}
		
	}
	
	public static void printSubMenu(String year, String month, String date, String id) {
		
		while (true) {
			
			Scanner scan = new Scanner(System.in);
			
			System.out.println("================================================================================================");
			System.out.println("1. 출근 시간");
			System.out.println("2. 퇴근 시간");
			System.out.print("번호를 입력하세요: ");
			String input = scan.nextLine();
			
			switch (input) {
			
				case "1": 	
				case "2": 
	
					modifyTime(year, month, date, id, input);
					
					return;
					
				default:
	
					System.out.println("------------------------------------------------------------------------------------------------");
					System.out.println("입력값이 유효하지 않습니다.\n");
					System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
					scan.nextLine();
				
			}
			
		}

	}

	public static void modifyTime(String year, String month, String date, String id, String input) {
		
		while (true) {
			
			Scanner scan = new Scanner(System.in);
			
			System.out.println("--------------------------------------------------------------------------------");
			System.out.print("시간을 입력하세요(X:XX): ");
			String time = scan.nextLine();
			
			Matcher matcher = Pattern.compile("^([01]?[0-9]|2[0-3]):[0-5][0-9]$").matcher(time);
			
			if (matcher.matches()) {
				
				boolean isModified = AttendanceDao.modifyTime(year, month, date, time, id, input);
				
				if (isModified) {
					
					printLastMenu(year, month, input);

					return;
					
				} else {
					
					System.out.println("------------------------------------------------------------------------------------------------");
					System.out.println("입력값이 유효하지 않습니다.\n");
					System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
					scan.nextLine();

				}
				
			}
			
		}
		
	}
	
	public static void printLastMenu(String year, String month, String input) {
		
		Scanner scan = new Scanner(System.in);
		
		if (input.equals("1")) {
			
			input = "출근";
			
		} else {
			
			input = "퇴근";
			
		}
		
		while (true) {
    		
			System.out.println("------------------------------------------------------------------------------------------------");
    		System.out.printf("%s 시간을 수정하시겠습니까?(Y/N): ", input);
        	String answer = scan.nextLine();
        	System.out.println("------------------------------------------------------------------------------------------------");
        	
        	if ("y".equalsIgnoreCase(answer)) {
        		
        		AttendanceDao.save(year, month);

        		System.out.println("수정이 완료되었습니다.");
        		
        		break;
        		
        	} else if ("n".equalsIgnoreCase(answer)) {

        		System.out.println("수정이 취소되었습니다.");
        		
        		return;
        		
        	} else {

				System.out.println("입력값이 유효하지 않습니다.\n");
				System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
				scan.nextLine();
        		
        	}
    		
    	}
		
	}

}
