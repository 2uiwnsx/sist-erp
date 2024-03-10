package com.project.hrms.admin.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.project.hrms.dao.DeducationDao;
import com.project.hrms.dao.PayItemDao;

public class DeducationView {
	
	public static void deducation() {
	
		Scanner scan = new Scanner(System.in);
		
		while (true) {
			
			System.out.println("================================================================================================");
			System.out.println("[공제내역 항목 조회 및 수정]\n");
			
			DeducationDao.listShow();

			System.out.println("================================================================================================");
			System.out.println("1. 사용여부 수정");
			System.out.println("2. 항목 등록");
			System.out.println("3. 항목 삭제");
			System.out.println("4. 이전 화면");
			System.out.print("번호를 입력하세요: ");
			String input = scan.nextLine();
			
			switch(input) {
			
				case "1" : 
					
					System.out.println("------------------------------------------------------------------------------------------------");
					System.out.print("수정할 항목의 번호를 입력하세요: ");
					int num = scan.nextInt();
					
					if (!DeducationDao.toUseChange(num)) {
						
						System.out.println(""); 
						scan.nextLine();
						
					}
					
					break;
					
				case "2":
					
					System.out.println("------------------------------------------------------------------------------------------------");
					System.out.print("등록할 항목명을 입력하세요: ");
					String item = scan.nextLine();
					
					try {
						
						System.out.print("사용자부담율을 입력하세요(단위 : %): ");
						double subscriberRate = scan.nextDouble();
						System.out.print("사업자부담율을 입력하세요(단위 : %): ");
						double companyRate = scan.nextDouble();
						
						if (!DeducationDao.itemAdd(item, subscriberRate, companyRate)) {
							
							System.out.println(""); 
							scan.nextLine();
							
						}

					} catch (InputMismatchException e) {
						
						System.out.println("------------------------------------------------------------------------------------------------");
						System.out.println("입력값이 유효하지 않습니다.\n");
						System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
						scan.nextLine();
						
					}
					
					break;
					
				case "3":
					
					System.out.println("------------------------------------------------------------------------------------------------");
					System.out.print("삭제할 항목의 번호를 입력하세요: ");
					num = scan.nextInt();
					
					if (!DeducationDao.delete(num)) {
						
						System.out.println(""); 
						scan.nextLine();
						
					}
					
					break;
					
				case "4": return;
				
				default :

					System.out.println("------------------------------------------------------------------------------------------------");
					System.out.println("입력값이 유효하지 않습니다.\n");
					System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
					scan.nextLine();
					
			}

		}
		
	}

	public static void payItem() {
		
		Scanner scan = new Scanner(System.in);
		
		while (true) {
			
			System.out.println("================================================================================================");
			System.out.println("[지급내역 항목 조회 및 수정]\n");
			
			PayItemDao.listShow();

			System.out.println("================================================================================================");
			System.out.println("1. 사용여부 수정");
			System.out.println("2. 항목 등록");
			System.out.println("3. 항목 삭제");
			System.out.println("4. 이전 화면");
			System.out.print("번호를 입력하세요: ");
			String input = scan.nextLine();
			
			switch(input) {
			
				case "1" : 
					
					while(true) {
						
						System.out.println("------------------------------------------------------------------------------------------------");
						System.out.print("수정할 항목의 번호를 입력하세요: ");
						int num = scan.nextInt();
						
						if (PayItemDao.toUseChange(num)) {
							
							break;
							
						} else {
							
							System.out.println();
							
						}
						
					}
					
					break;
					
				case "2":
					
					System.out.println("------------------------------------------------------------------------------------------------");
					System.out.print("등록할 항목명을 입력하세요: ");
					String item = scan.nextLine();

					PayItemDao.itemAdd(item);
					
					break;
					
				case "3":
					
					System.out.println("------------------------------------------------------------------------------------------------");
					System.out.print("삭제할 항목의 번호를 입력하세요: ");
					int num = scan.nextInt();
					
					if (!PayItemDao.delete(num)) {
						
						System.out.println();
						
					}
					
					break;
					
				case "4": return;
					
				default :
					
					System.out.println("------------------------------------------------------------------------------------------------");
					System.out.println("입력값이 유효하지 않습니다.\n");
					System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
					scan.nextLine();
					
			}
			
		}

	}

}
