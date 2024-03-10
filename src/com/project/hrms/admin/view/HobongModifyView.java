package com.project.hrms.admin.view;

import java.util.Scanner;

import com.project.hrms.dao.SalaryStepModifyDao;

public class HobongModifyView {
	
	public static void hobongBasicPayModify() {
		
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			
			System.out.println("================================================================================================");
			System.out.println("[기본급 조회 및 수정]");
			System.out.print("수정할 직급을 입력하세요: ");
			String position = scan.nextLine();
			System.out.print("수정할 호봉을 입력하세요: ");
			String hobong = scan.nextLine();
			
			if (!SalaryStepModifyDao.hobongBasicPayModify(position, hobong)) {
				
				System.out.println("------------------------------------------------------------------------------------------------");
				System.out.println("입력값이 유효하지 않습니다.\n");
				System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
				scan.nextLine();
				
			} else {
				
				return;
				
			}
			
		}
		
	}

	public static void hobongPositionPayModify() {
		
		Scanner scan = new Scanner(System.in);
		
		while(true) {
			
			System.out.println("================================================================================================");
			System.out.println("[직책수당 조회 및 수정]");
			System.out.print("수정할 직급을 입력하세요: ");
			String position = scan.nextLine();
			
			if (!SalaryStepModifyDao.hobongPositionPayModify(position)) {
				
				System.out.println("------------------------------------------------------------------------------------------------");
				System.out.println("입력값이 유효하지 않습니다.\n");
				System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
				scan.nextLine();
				
			} else {
				
				return;
				
			}

		}
		
	}
	
}
