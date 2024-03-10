package com.project.hrms.member.view;

import java.util.Scanner;

import com.project.hrms.dao.PayDao;
import com.project.hrms.main.UserDao;

public class MemberPayView {
	
	public static void subTitle() {
		
		System.out.println("================================================================================================");
		System.out.println("[급여명세서 확인]");
		
	}
	
	public static void paycheck() {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.print("연도를 입력하세요: ");
		String year = scan.nextLine();
		System.out.print("월을 입력하세요: ");
		String month = scan.nextLine();

		if (!PayDao.isExistence(UserDao.auth.getId(), month)) {
			
			System.out.println("------------------------------------------------------------------------------------------------");
			System.out.println("해당일의 급여명세서가 존재하지 않습니다.\n");
			System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
			scan.nextLine();
			
		} else {
			
			PayDao.listShow(month, UserDao.auth.getId());
			
		}

	}
	
}
