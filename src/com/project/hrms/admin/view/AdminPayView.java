package com.project.hrms.admin.view;

import java.util.Calendar;
import java.util.Scanner;

import com.project.hrms.dao.PayDao;

public class AdminPayView {

	public static void subTitle() {
		
		System.out.println("================================================================================================");
		System.out.println("[급여관리정보]");
		
	}

	public static void listAdd() {
		
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			
			Calendar now = Calendar.getInstance();
			String month = now.get(Calendar.MONTH) + 1 + "";
			
			System.out.printf("%s월 근로소득을 일괄 등록하시겠습니까?(Y/N): ", month);
			String input = scan.nextLine().toLowerCase();
			
			if (input.equals("y")) {
				
				PayDao.listAdd(month);
				
				return;
				
			} else if (input.equals("n")) {
				
				AdminView.printPayMenu();
				
				return;
				
			} else {
				
				System.out.println("------------------------------------------------------------------------------------------------");
				System.out.println("입력값이 유효하지 않습니다.\n");
				System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
				scan.nextLine();
			
			}
			
		}

	}

	public static void listCheck() {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("================================================================================================");
		System.out.println("[직원별 급여명세서 조회]");
		System.out.print("사번을 입력하세요: ");
		String id = scan.nextLine();
		System.out.print("날짜(월)을 입력하세요: ");
		String month = scan.nextLine();
		
		if (PayDao.isExistence(id, month)) {
			
			PayDao.listShow(month, id);
			
			System.out.println("\nEnter 키를 누르면 계속 진행할 수 있습니다.");
			scan.nextLine();
			
			return;
			
		} else {
			
			System.out.println("------------------------------------------------------------------------------------------------");
			System.out.println("입력값이 유효하지 않습니다.\n");
			System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
			scan.nextLine();
			
		}
		
	}
	
}
