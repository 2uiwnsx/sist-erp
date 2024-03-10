package com.project.hrms.admin.view;

import java.util.Scanner;

import com.project.hrms.dao.AnnualDao;
import com.project.hrms.dao.AnnualSubDao;
import com.project.hrms.dao.CertificateDao;
import com.project.hrms.dao.DeducationDao;
import com.project.hrms.dao.RetireDao;
import com.project.hrms.dao.SalaryStepModifyDao;
import com.project.hrms.main.InitialView;
import com.project.hrms.main.PersonDao;
import com.project.hrms.main.UserDao;

public class AdminView {

	public static void printAdminView() {
		
		while (true) {
			
			Scanner scan = new Scanner(System.in);
			
			System.out.println("================================================================================================");
			System.out.print("안녕하세요, 관리자님.");
			
			if (!InitialView.dateName.equals("")) {
				
				System.out.printf("오늘은 %d월 %d일(%s)입니다.\n", InitialView.month, InitialView.date, InitialView.dateName);
				
			} else {
				
				System.out.printf("오늘은 %d월 %d일입니다.\n", InitialView.month, InitialView.date);
				
			}

			System.out.println("================================================================================================");
			System.out.println("1. 기초환경설정");
			System.out.println("2. 인사관리정보");
			System.out.println("3. 근태관리정보");
			System.out.println("4. 급여관리정보");
			System.out.println("5. 퇴직관리");
			System.out.println("6. 증명서 발급 승인 및 조회");
			System.out.println("7. 로그아웃");
			System.out.print("번호를 입력하세요: ");
			String input = scan.nextLine();			
			
			switch (input) {
			
				case "1" : printBasicMenu(); break;
				
				case "2" : printUserinfoMenu(); break;
				
				case "3" : printAttendanceMenu(); break;
				
				case "4" : printPayMenu(); break;
				
				case "5" : printRetireMenu(); break;
				
				case "6" : printCertificateMenu(); break;
				
				case "7" : save(); return;
				
				default  : 
					
					System.out.println("------------------------------------------------------------------------------------------------");
					System.out.println("입력값이 유효하지 않습니다.\n");
					System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
					scan.nextLine();
			
			}
			
		}

	}
	
	private static void save() {

		PersonDao.save();
		UserDao.save();
		AnnualDao.save();
		AnnualSubDao.save();
		CertificateDao.save();
		SalaryStepModifyDao.save();
		DeducationDao.save();

	}

	public static void printBasicMenu() {
		
		Scanner scan = new Scanner(System.in);

		while (true) {
			
			System.out.println("================================================================================================");
			System.out.println("[기초환경설정]");
			System.out.println("1. 호봉테이블 등록");
			System.out.println("2. 지급공제항목 등록");
			System.out.println("3. 사회보험 환경등록");
			System.out.println("4. 이전 화면");
			System.out.print("번호를 입력하세요: ");
			String input = scan.nextLine();
			
			switch (input) {
			
				case "1": BasicView.payStepTableSetting(); break;
					
				case "2": BasicView.deducationSetting(); break;
				
				case "3": BasicView.insureanceSetting(); break;
				
				case "4": return;
				
				default:
					
					System.out.println("------------------------------------------------------------------------------------------------");
					System.out.println("입력값이 유효하지 않습니다.\n");
					System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
					scan.nextLine();
				
			}
			
		}

	}
	
	public static void printUserinfoMenu() {
		
		PersonDepartmentView.PersonDepartmentChView();
		
	}
	
	public static void printAttendanceMenu() {
		
		Scanner scan = new Scanner(System.in);
		
		while (true) {

			System.out.println("================================================================================================");
			System.out.println("[근태관리정보]");
			System.out.println("1. 조회 및 출/퇴근 시간 수정");
			System.out.println("2. 이전 화면");
			System.out.print("번호를 입력하세요: ");
			String input = scan.nextLine();
			
			switch (input) {

				case "1":
					
					AdminAttendanceView.printAttendanceView();
					
					break;
	
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
	
	public static void printPayMenu() {
		
		Scanner scan = new Scanner(System.in);

		while (true) {
			
			AdminPayView.subTitle();
			
			System.out.println("1. 근로소득 일괄 입력");
			System.out.println("2. 급여명세서 조회");
			System.out.println("3. 이전 화면");
			System.out.print("번호를 입력하세요: ");
			int input = scan.nextInt();
			
			switch (input) {
			
				case 1: AdminPayView.listAdd(); break;
				
				case 2: AdminPayView.listCheck(); break;
				
				case 3: return;
				
				default:
					
					System.out.println("------------------------------------------------------------------------------------------------");
					System.out.println("입력값이 유효하지 않습니다.\n");
					System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
					scan.nextLine();
					
			}
			
		}
		
	}
	
	public static void printRetireMenu() {

		Scanner scan = new Scanner(System.in);

		while (true) {
			
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
				
				return;
				
			} else {
				
				System.out.println("------------------------------------------------------------------------------------------------");
				System.out.println("입력값이 유효하지 않습니다.\n");
				System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
				scan.nextLine();
				
			}
			
		}
		
	}

	public static void printCertificateMenu() {

		Scanner scan = new Scanner(System.in);

		while (true) {

			System.out.println("================================================================================================");
			System.out.println("[증명서 발급 승인 및 조회]\n");
			System.out.println("[신청 내역]");
			
			CertificateDao.adminCertiRqList();

			System.out.println("================================================================================================");
			System.out.println("1. 승인 처리");
			System.out.println("2. 이전 화면");
			System.out.print("번호를 입력하세요: "); 
			String input = scan.nextLine();

			switch (input) {
			
				case "1": CertificateDao.adminApprovalRq(); break;
	
				case "2": return;
	
				default:
					
					System.out.println("------------------------------------------------------------------------------------------------");
					System.out.println("입력값이 유효하지 않습니다.\n");
					System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
					scan.nextLine();
					
			}

		}

	}

}
