package com.project.hrms.member.view;

import java.util.Scanner;

import com.project.hrms.dao.AnnualDao;
import com.project.hrms.dao.AnnualSubDao;
import com.project.hrms.dao.CertificateDao;
import com.project.hrms.dao.DeducationDao;
import com.project.hrms.dao.MypageDao;
import com.project.hrms.dao.PersonDepartmentCh;
import com.project.hrms.dao.SalaryStepModifyDao;
import com.project.hrms.main.InitialView;
import com.project.hrms.main.PersonDao;
import com.project.hrms.main.UserDao;
import com.project.hrms.vo.AnnualVo;

public class MemberView {

	public static void printMemberView(){
		
		Scanner scan = new Scanner(System.in);
		
		boolean loop = true;
		
		while (loop) {
			
			System.out.println("================================================================================================");
			System.out.printf("안녕하세요, %s님. ", UserDao.auth.getName());
			
			if (!InitialView.dateName.equals("")) {
				
				System.out.printf("오늘은 %d월 %d일(%s)입니다.\n", InitialView.month, InitialView.date, InitialView.dateName);
				
			} else {
				
				System.out.printf("오늘은 %d월 %d일입니다.\n", InitialView.month, InitialView.date);
				
			}
			
			System.out.println("================================================================================================");
			System.out.println("1. 근태 조회");
			System.out.println("2. 연차 조회 및 신청");
			System.out.println("3. 급여명세서 확인");
			System.out.println("4. 조직도 및 부서별 현황 조회");
			System.out.println("5. 마이페이지");
			System.out.println("6. 증명서 발급 신청");
			System.out.println("7. 로그아웃");
			System.out.print("번호를 입력하세요: ");
			String input = scan.nextLine();
			
			switch (input) {
			
				case "1": printAttendanceMenu(); break;
				
				case "2": printAnnualMenu(); break;
				
				case "3": printPayMenu(); break;
				
				case "4": printOrganizationMenu(); break;
				
				case "5":
					
					if (printMyMenu() == 1) {
						
						break;
						
					} else {
						
						return;
						
					}
	
				case "6": printCertificateMenu(); break;
				
				case "7": save(); return;
				
				default:
					
					System.out.println("------------------------------------------------------------------------------------------------");
					System.out.println("입력값이 유효하지 않습니다.\n");
					System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
					scan.nextLine();

			}

		}
		
	}

	public static void printAttendanceMenu() {
		
		Scanner scan = new Scanner(System.in);
		
		while (true) {
			
			System.out.println("================================================================================================");
			System.out.println("[근태 조회]");
			System.out.println("1. 조회");
			System.out.println("2. 이전 화면");
			System.out.print("번호를 입력하세요: ");
			String input = scan.nextLine();
			
			switch (input) {
			
				case "1": MemberAttendanceView.printAttendanceView(); break;
					
				case "2":
					
					System.out.println("\nEnter 키를 누르면 계속 진행할 수 있습니다.");
					scan.nextLine();
					
					return;
					
				default:
					
					System.out.println("------------------------------------------------------------------------------------------------");
					System.out.println("입력값이 유효하지 않습니다.\n");
					System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
					scan.nextLine();
			
			}
		
		}

	}
	
	public static void printAnnualMenu() {
		
		Scanner scan = new Scanner(System.in);
		
		while (true) {
			
			AnnualVo temp = AnnualDao.checkAnnualApply();
			
			System.out.println("================================================================================================");
			System.out.println("[연차 조회 및 신청]\n");
			System.out.println("[할당 연차]\t[잔여 연차]\t[사용 연차]");
			System.out.printf("%5s\t\t%5s\t\t%5s\n", temp.getAllotmentAnnual(), temp.getLeftoverAnnual(), temp.getUsedAnnal());
			System.out.println("------------------------------------------------------------------------------------------------");
			System.out.println("1. 연차 신청");
			System.out.println("2. 연차 신청 내역 조회");
			System.out.println("3. 이전 화면");
			System.out.print("번호를 입력하세요: ");
			String input = scan.nextLine();
			
			switch (input) {
			
			case "1" :
				
				if (temp.getLeftoverAnnual() == 0) {
					
					System.out.println("------------------------------------------------------------------------------------------------");
					System.out.println("잔여 연차가 없습니다");
					
					break;
					
				} else {
					
					annualMenu1();
					
				}
				
				break;
				
			case "2" : 
				
				if (temp.getLeftoverAnnual() == temp.getAllotmentAnnual()) {

					
					System.out.println("------------------------------------------------------------------------------------------------");
					System.out.println("연차 내역이 없습니다.\n");
					System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
					scan.nextLine();
					
					break;
					
				} else {
					
					System.out.println("================================================================================================");
					System.out.println("  [시작일]\t  [종료일]\t[사용 일수]\t[신청사유]");
					System.out.println(AnnualSubDao.checkAnnualApply());
					System.out.println("\nEnter 키를 누르면 계속 진행할 수 있습니다.");
					scan.nextLine();
					
					break;
					
				}
				
			case "3" : return;
			
			default : 
				
				System.out.println("------------------------------------------------------------------------------------------------");
				System.out.println("입력값이 유효하지 않습니다.\n");
				System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
				scan.nextLine();
				
				break;
			
			}

		}
		
	}
	
	public static void annualMenu1() {
		
		Scanner scan = new Scanner(System.in);

		System.out.println("================================================================================================");
		System.out.print("시작일을 입력하세요(20XX-XX-XX): ");
		String startAnnual = scan.nextLine();
		System.out.print("종료일을 입력하세요(20XX-XX-XX): ");
		String endAnnual = scan.nextLine();
		System.out.print("신청 사유를 입력하세요: ");
		String reason = scan.nextLine();

		while (true) {
			
			System.out.println("------------------------------------------------------------------------------------------------");
			System.out.print("연차를 신청하시겠습니까?(Y/N): ");
			String input = scan.nextLine();
			System.out.println("------------------------------------------------------------------------------------------------");
			
			if (input.toLowerCase().equals("y")) {

				if (AnnualSubDao.annualCheck(startAnnual, endAnnual)) {
					
					int result = AnnualSubDao.annualSubApply(startAnnual, endAnnual, reason);
					
					if (result == -1) {

						System.out.println("잔여 연차가 없습니다.\n");
						System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
						scan.nextLine();
						
						break;
						
					} else if (result == 1) {
						
						System.out.println("신청이 완료되었습니다.\n");
						System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
						scan.nextLine();
						
						break;
						
					} else {
						
						System.out.println("평일을 입력하세요.\n");
						System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
						scan.nextLine();
						
						break;
						
					}
					
				} else {
					
					System.out.println("입력값이 유효하지 않습니다.\n");
					System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
					scan.nextLine();
					
					break;
					
				}
				
			} else if (input.toLowerCase().equals("n")) {
				
				System.out.println("신청이 취소되었습니다.\n");
				System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
				scan.nextLine();
				
				break;
				
			} else {
				
				System.out.println("입력값이 유효하지 않습니다.\n");
				System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
				scan.nextLine();
				
			}
			
		}
		
	}

	public static void printPayMenu() {

		while (true) {
			
			Scanner scan = new Scanner(System.in);
			
			MemberPayView.subTitle();
			
			System.out.println("1. 급여명세서 조회");
			System.out.println("2. 이전 화면");
			System.out.print("번호를 입력하세요: ");
			String input = scan.nextLine();
			
			switch (input) {
			
				case "1" : MemberPayView.paycheck(); break;
				
				case "2" : return;
				
				default : 
					
					System.out.println("------------------------------------------------------------------------------------------------");
					System.out.println("입력값이 유효하지 않습니다.\n");
					System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
					scan.nextLine();
					
				}
			
		}
		
	}
	
	public static void printOrganizationMenu() {

		while (true) {
			
			Scanner scan = new Scanner(System.in);
			
			System.out.println("================================================================================================");
			System.out.println("\t\t쌍용 주식회사");
			System.out.println("\t\t     |");
			System.out.println("    ┌──────┬──────┬──────┬──────┬─────┐");
			System.out.println("  인사팀   구매팀  기획팀   영업팀  개발팀  재무팀");
			System.out.println("------------------------------------------------------------------------------------------------");
			System.out.println("1. 부서별 현황 조회");
			System.out.println("2. 이전 화면");
			System.out.print("번호를 입력하세요: ");
			String input = scan.nextLine();
			
			if (input.equals("1")) {
				
				System.out.print("부서를 입력하세요: ");
				String dp = scan.nextLine();
				
				if (dp.equals("인사팀") || dp.equals("구매팀") || dp.equals("기획팀") ||
					dp.equals("영업팀") || dp.equals("개발팀") || dp.equals("재무팀")) {
					
					System.out.println("================================================================================================");
					System.out.println("[부서]\t[직급]\t[이름]\t[전화번호]");
					System.out.println(PersonDepartmentCh.checkDepartmentMember(dp));
					System.out.println("\nEnter 키를 누르면 계속 진행할 수 있습니다.");
					scan.nextLine();
					
				} else {
					
					System.out.println("------------------------------------------------------------------------------------------------");
					System.out.println("입력값이 유효하지 않습니다.\n");
					System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
					scan.nextLine();
					
				}

			} else if (input.equals("2")) {
				
				return;
				
			} else {
				
				System.out.println("------------------------------------------------------------------------------------------------");
				System.out.println("입력값이 유효하지 않습니다.\n");
				System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
				scan.nextLine();
				
			}
			
		}
		
	}
	
	public static int printMyMenu() {
		
		Scanner scan = new Scanner(System.in);

		while (true) {
			
			System.out.println("================================================================================================");
			System.out.println("[마이페이지]");
			System.out.println("1. 인사 현황 조회");
			System.out.println("2. 비밀번호 변경");
			System.out.println("3. 이전 화면");
			System.out.print("번호를 입력하세요: ");
			String input = scan.nextLine();
			
			switch (input) {
			
				case "1":
					
					MypageDao.checkUserInfo();
					
					break;
					
				case "2":
					
					if (MypageDao.changePw() == 1) {
						
						break;
						
					} else {

						return 0;

					}
					
				case "3":

					return 1;
					
				default:
					
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
			System.out.println("[증명서 발급 신청]\n");
			System.out.println("《신청 내역》\n");

			CertificateDao.memberCertiRqList();
			
			System.out.println("================================================================================================");
			System.out.println("1. 재직증명서 발급 신청");
			System.out.println("2. 경력증명서 발급 신청");
			System.out.println("3. 이전 화면");
			System.out.print("번호를 입력하세요: ");
			String input = scan.nextLine();
			
			switch (input) {
			
				case "1" :
					
					CertificateDao.employmentCertiRq();
					
					break;
					
				case "2" :
					
					CertificateDao.careerCertiRq();
					
					break;
					
				case "3" :
					
					return;
					
				default : 
					
					System.out.println("------------------------------------------------------------------------------------------------");
					System.out.println("입력값이 유효하지 않습니다.\n");
					System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
					scan.nextLine();
			
			}
			
		}

	}
	
	private static void save() {
		
		PersonDao.save();
		AnnualDao.save();
		AnnualSubDao.save();
		UserDao.save();
		CertificateDao.save();
		SalaryStepModifyDao.save();
		DeducationDao.save();
		
	}

}
