package com.project.hrms.main;

import java.util.Calendar;
import java.util.Scanner;

import com.project.hrms.admin.view.AdminView;
import com.project.hrms.dao.AnnualDao;
import com.project.hrms.dao.AnnualSubDao;
import com.project.hrms.dao.CertificateDao;
import com.project.hrms.dao.DeducationDao;
import com.project.hrms.dao.PayItemDao;
import com.project.hrms.dao.SalaryStepModifyDao;
import com.project.hrms.member.view.MemberView;
import com.project.hrms.openAPI.OpenAPI;

public class InitialView {
	
	public static int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
    public static int date = Calendar.getInstance().get(Calendar.DATE);
    public static String dateName = "";

	public static void printInitialView() {
		
		Scanner scan = new Scanner(System.in);
		
		load();

		if (dateName.equals("")) {
			
			dateName = OpenAPI.checkIsHoliday(month, date);
			
		}
		
		printLogo();
		
		while (true) {

			System.out.println("================================================================================================");
			System.out.println("안녕하세요, ㈜쌍용아이티 인적자원관리시스템(HRMS)입니다.");
			System.out.println("================================================================================================");
			System.out.println("1. 로그인");
			System.out.println("2. 프로그램 종료");
			System.out.print("번호를 입력하세요: ");
			String input = scan.nextLine();
			
			if (input.equals("1")) {
				
				login();
				
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
	
    private static void load() {
    	
		PersonDao.load();
		UserDao.load();
		AnnualDao.load();
		AnnualSubDao.load();
		CertificateDao.load();
		SalaryStepModifyDao.load();
		DeducationDao.load();
		PayItemDao.load();
    	
    }
    
	private static void printLogo() {
		
		System.out.println("                      ._______________________.");
        System.out.println("                      |□ □ □ □ □ □ □ □ □ □ □ □|");
        System.out.println("                      |_______________________|");
        System.out.println("                  *   |□ □ □ □ □ □ □ □ □ □ □ □|");
        System.out.println("                      |_______________________|");
        System.out.println("           *          |□ □ □ □ □ □ □ □ □ □ □ □|          *");
        System.out.println("              .       |_______________________|");
        System.out.println("                      |□ □ □ □ □ □ □ □ □ □ □ □|                         *");
        System.out.println("      ...       `     |_______________________|   +             ...");
        System.out.println("     /   \\  +         |□ □ □ □ □ □ □ □ □ □ □ □|            +   /   \\");
        System.out.println("     \\   /     ...    |_______________________|     ...     .  \\   /");
        System.out.println("   * /   \\.   /   \\   |□ □ □ □ □ □ □ □ □ □ □ □|    /   \\ *    ./   \\.");
        System.out.println("     \\   /    \\   /   |_______________________|    \\   /   +   \\   /   '");
        System.out.println("  .  /   \\   */   \\   |□ □ □ □ □ □ □ □ □ □ □ □|    /   \\  * .  /   \\");
        System.out.println("     \\   /  . \\   /   |_______________________|    \\   /.      \\   /");
        System.out.println(" +   /   \\    /   \\   |□ □ □ □ □ □ □ □ □ □ □ □|    /   \\       /   \\   +");
        System.out.println("     \\   /    \\   /   |_______________________|    \\   /       \\   /");
        System.out.println("      | |      | |    |□ □ □ □ □ □ □ □ □ □ □ □|     | |         | |");
        System.out.println("      |_|      |_|    |_______________________|     |_|         |_|");
        System.out.println("  ______   _____   ______   _________   ________  _______     _______   ");
        System.out.println(".' ____ \\ |_   _|.' ____ \\ |  _   _  | |_   __  ||_   __ \\   |_   __ \\  ");
        System.out.println("| (___ \\_|  | |  | (___ \\_||_/ | | \\_|   | |_ \\_|  | |__) |    | |__) | ");
        System.out.println(" _.____`.   | |   _.____`.     | |       |  _| _   |  __ /     |  ___/  ");
        System.out.println("| \\____) | _| |_ | \\____) |   _| |_     _| |__/ | _| |  \\ \\_  _| |_     ");
        System.out.println(" \\______.'|_____| \\______.'  |_____|   |________||____| |___||_____|    ");
        
        System.out.println();
        
	}

	private static void login() {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("------------------------------------------------------------------------------------------------");
		System.out.print("아이디를 입력하세요: ");
		String id = scan.nextLine();
		System.out.print("비밀번호를 입력하세요: ");
		String pw = scan.nextLine();
		
		String level = UserDao.getLevel(id, pw);

		if (UserDao.auth == null) {
			
			System.out.println("------------------------------------------------------------------------------------------------");
			System.out.println("로그인에 실패했습니다.\n");
			System.out.println("Enter 키를 누르면 계속 진행할 수 있습니다.");
			scan.nextLine();
			
			return;
			
		} else {

			if (level.equals("1")) {

				MemberView.printMemberView();
				
			} else if (level.equals("2")) {

				AdminView.printAdminView();
				
			}
			
		}
		
	}
	
}
