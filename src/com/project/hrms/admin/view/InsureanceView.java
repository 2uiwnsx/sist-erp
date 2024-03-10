package com.project.hrms.admin.view;

import java.util.Scanner;

import com.project.hrms.dao.DeducationDao;

public class InsureanceView {

	public static void subTitle(String str) {
		
		System.out.println("================================================================================================");
		System.out.println("[" + str + "]");
			
	}

	public static void insureanceChoice(String input) {
		
		Scanner scan = new Scanner(System.in);

		while (true) {
			
			switch (input){
			
				case "1" : InsureanceView.subTitle("건강보험"); break;
				
				case "2" : InsureanceView.subTitle("고용보험"); break;
				
				case "3" : InsureanceView.subTitle("산재보험"); break;
				
				case "4" : InsureanceView.subTitle("국민연금"); break;
				
			}

			System.out.println("1. 가입자 부담율 수정");
			System.out.println("2. 사업자 부담율 수정");
			System.out.println("3. 이전 화면");
			System.out.print("번호를 입력하세요: ");
			String input2 = scan.nextLine();
			
			switch (input2) {
			
				case "1" : DeducationDao.subscriberRate(input); break;
				
				case "2" : DeducationDao.companyRate(input); break;
				
				case "3" : return;
				
				default : 
					
					System.out.println("------------------------------------------------------------------------------------------------");
					System.out.println("입력값이 유효하지 않습니다.\n");
					System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
					scan.nextLine();
							
			}
			
		}
		
	}

}
