package com.project.hrms.admin.view;

import com.project.hrms.dao.RetireDao;

import java.util.Scanner;

public class RetireView {

	public static void retire() {

		Scanner scan = new Scanner(System.in);
		
		boolean loop = true;

		while (loop) {
			
			System.out.println("================================================================================================");
			System.out.println("[퇴직관리]");
			System.out.println("1. 퇴직자 처리");
			System.out.println("2. 퇴직금 계산");
			System.out.println("3. 이전 화면");
			System.out.print("번호를 입력하세요: ");
			String input = scan.nextLine();

			if (input.equals("1")) {
				
				RetireDao.retireeProcess();
				
			} else if (input.equals("2")) {
				
				RetireDao.calcSeverancePay();
				
			} else if (input.equals("3")) {
				
				loop = false;
				
				AdminView.printAdminView();

			}
			
		}
		
	}
	
}
