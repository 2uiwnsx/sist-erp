package com.project.hrms.admin.view;

import java.util.Scanner;

import com.project.hrms.dao.SalaryStepModifyDao;

public class BasicView {
	
	public static void payStepTableSetting() {
		
		Scanner scan = new Scanner(System.in);

		while (true) {
			
			System.out.println("================================================================================================");
			System.out.println("[호봉테이블 등록]");
			System.out.println("※ 단위: 원");
			
			SalaryStepModifyDao.hobongList();
			
			System.out.println("================================================================================================");
			System.out.println("1. 기본급 수정");
			System.out.println("2. 직책수당 수정");
			System.out.println("3. 이전 화면");
			System.out.print("번호를 입력하세요: ");
			String input = scan.nextLine();
			
			switch (input) {
			
				case "1" : HobongModifyView.hobongBasicPayModify(); break;
				
				case "2" : HobongModifyView.hobongPositionPayModify(); break;
				
				case "3" : return;
				
				default :
					
					System.out.println("------------------------------------------------------------------------------------------------");
					System.out.println("입력값이 유효하지 않습니다.\n");
					System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
					scan.nextLine();
			
			}
			
		}
		
	}
	
	public static void deducationSetting() {
		
		Scanner scan = new Scanner(System.in);

		while (true) {
			
			System.out.println("================================================================================================");
			System.out.println("[지급공제항목 등록]");
			System.out.println("1. 지급내역 항목 조회 및 수정");
			System.out.println("2. 공제내역 항목 조회 및 수정");
			System.out.println("3. 이전 화면");
			System.out.print("번호를 입력하세요: ");
			String input = scan.nextLine();
			
			switch (input){
			
				case "1" : DeducationView.payItem(); break;
				
				case "2" : DeducationView.deducation(); break;
				
				case "3" : return;
				
				default : 
					
					System.out.println("------------------------------------------------------------------------------------------------");
					System.out.println("입력값이 유효하지 않습니다.\n");
					System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
					scan.nextLine();
				
			}
			
		}
		
	}

	public static void insureanceSetting() {
		
		Scanner scan = new Scanner(System.in);

		while (true) {
			
			System.out.println("================================================================================================");
			System.out.println("[사회보험 환경등록]");
			System.out.println("1. 건강보험");
			System.out.println("2. 고용보험");
			System.out.println("3. 산재보험");
			System.out.println("4. 국민연금");
			System.out.println("5. 이전 화면");
			System.out.print("번호를 입력하세요: ");
			String input = scan.nextLine();
			
			if (input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4")) {
				
				InsureanceView.insureanceChoice(input);
				
				break;
				
			} else if (input.equals("5")) {
				
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
